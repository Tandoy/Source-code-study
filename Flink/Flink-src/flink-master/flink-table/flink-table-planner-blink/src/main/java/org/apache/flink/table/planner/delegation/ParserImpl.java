/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.planner.delegation;

import org.apache.flink.table.api.TableException;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.catalog.CatalogManager;
import org.apache.flink.table.catalog.UnresolvedIdentifier;
import org.apache.flink.table.delegation.Parser;
import org.apache.flink.table.expressions.ResolvedExpression;
import org.apache.flink.table.operations.Operation;
import org.apache.flink.table.planner.calcite.FlinkPlannerImpl;
import org.apache.flink.table.planner.calcite.FlinkTypeFactory;
import org.apache.flink.table.planner.calcite.SqlExprToRexConverter;
import org.apache.flink.table.planner.expressions.RexNodeExpression;
import org.apache.flink.table.planner.operations.SqlToOperationConverter;
import org.apache.flink.table.planner.parse.CalciteParser;
import org.apache.flink.table.planner.parse.ExtendedParser;
import org.apache.flink.table.types.logical.LogicalType;
import org.apache.flink.table.types.utils.TypeConversions;

import org.apache.calcite.rex.RexNode;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.advise.SqlAdvisor;
import org.apache.calcite.sql.advise.SqlAdvisorValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/** Implementation of {@link Parser} that uses Calcite. */
public class ParserImpl implements Parser {

    private final CatalogManager catalogManager;

    // we use supplier pattern here in order to use the most up to
    // date configuration. Users might change the parser configuration in a TableConfig in between
    // multiple statements parsing
    private final Supplier<FlinkPlannerImpl> validatorSupplier;
    private final Supplier<CalciteParser> calciteParserSupplier;
    private final Function<TableSchema, SqlExprToRexConverter> sqlExprToRexConverterCreator;
    private static final ExtendedParser EXTENDED_PARSER = ExtendedParser.INSTANCE;

    public ParserImpl(
            CatalogManager catalogManager,
            Supplier<FlinkPlannerImpl> validatorSupplier,
            Supplier<CalciteParser> calciteParserSupplier,
            Function<TableSchema, SqlExprToRexConverter> sqlExprToRexConverterCreator) {
        this.catalogManager = catalogManager;
        this.validatorSupplier = validatorSupplier;
        this.calciteParserSupplier = calciteParserSupplier;
        this.sqlExprToRexConverterCreator = sqlExprToRexConverterCreator;
    }

    /**
     * When parsing statement, it first uses {@link ExtendedParser} to parse statements. If {@link
     * ExtendedParser} fails to parse statement, it uses the {@link CalciteParser} to parse
     * statements.
     *
     * @param statement input statement.
     * @return parsed operations.
     */
    @Override
    public List<Operation> parse(String statement) {
        // 1.创建parse对象
        CalciteParser parser = calciteParserSupplier.get();
        FlinkPlannerImpl planner = validatorSupplier.get();
        // 2.使用缓存中的parser对象进行解析
        Optional<Operation> command = EXTENDED_PARSER.parse(statement);
        if (command.isPresent()) {
            return Collections.singletonList(command.get());
        }

        // parse the sql query
        // 3.使用新创建的parse对象进行SQL解析为SqlNode(是一个未经验证的抽象语法树)
            // 3.0. 把SQL语句转换成为一个抽象语法树（AST），在Calcite中用SqlNode来表示；
            // 3.1. 设计词法和语义，定义SQL中具体的元素；
            // 3.2. 实现词法分析器（Lexer）和语法分析器（Parser），完成对SQL的解析，完成相应的转换。
        SqlNode parsed = parser.parse(statement);

        Operation operation =
                SqlToOperationConverter.convert(planner, catalogManager, parsed)
                        .orElseThrow(() -> new TableException("Unsupported query: " + statement));
        return Collections.singletonList(operation);
    }

    @Override
    public UnresolvedIdentifier parseIdentifier(String identifier) {
        CalciteParser parser = calciteParserSupplier.get();
        SqlIdentifier sqlIdentifier = parser.parseIdentifier(identifier);
        return UnresolvedIdentifier.of(sqlIdentifier.names);
    }

    @Override
    public ResolvedExpression parseSqlExpression(String sqlExpression, TableSchema inputSchema) {
        final SqlExprToRexConverter sqlExprToRexConverter =
                sqlExprToRexConverterCreator.apply(inputSchema);
        final RexNode rexNode = sqlExprToRexConverter.convertToRexNode(sqlExpression);
        final LogicalType logicalType = FlinkTypeFactory.toLogicalType(rexNode.getType());
        // expand expression for serializable expression strings similar to views
        final String sqlExpressionExpanded = sqlExprToRexConverter.expand(sqlExpression);
        return new RexNodeExpression(
                rexNode,
                TypeConversions.fromLogicalToDataType(logicalType),
                sqlExpression,
                sqlExpressionExpanded);
    }

    public String[] getCompletionHints(String statement, int cursor) {
        List<String> candidates =
                new ArrayList<>(
                        Arrays.asList(EXTENDED_PARSER.getCompletionHints(statement, cursor)));

        // use sql advisor
        SqlAdvisorValidator validator = validatorSupplier.get().getSqlAdvisorValidator();
        SqlAdvisor advisor =
                new SqlAdvisor(validator, validatorSupplier.get().config().getParserConfig());
        String[] replaced = new String[1];

        List<String> sqlHints =
                advisor.getCompletionHints(statement, cursor, replaced).stream()
                        .map(item -> item.toIdentifier().toString())
                        .collect(Collectors.toList());

        candidates.addAll(sqlHints);

        return candidates.toArray(new String[0]);
    }

    public CatalogManager getCatalogManager() {
        return catalogManager;
    }
}
