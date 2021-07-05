// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.hudi.avro.model;

import org.apache.avro.data.RecordBuilder;
import org.apache.avro.specific.SpecificRecordBuilderBase;
import org.apache.avro.io.Decoder;
import java.io.ObjectInput;
import org.apache.avro.io.Encoder;
import java.io.ObjectOutput;
import org.apache.avro.AvroRuntimeException;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import java.util.Map;
import java.util.List;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieClusteringPlan extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -8391007565809339208L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieClusteringPlan> ENCODER;
    private static final BinaryMessageDecoder<HoodieClusteringPlan> DECODER;
    @Deprecated
    public List<HoodieClusteringGroup> inputGroups;
    @Deprecated
    public HoodieClusteringStrategy strategy;
    @Deprecated
    public Map<String, String> extraMetadata;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieClusteringPlan> WRITER$;
    private static final DatumReader<HoodieClusteringPlan> READER$;
    
    public static Schema getClassSchema() {
        return HoodieClusteringPlan.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringPlan> getDecoder() {
        return HoodieClusteringPlan.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringPlan> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieClusteringPlan>)new BinaryMessageDecoder((GenericData)HoodieClusteringPlan.MODEL$, HoodieClusteringPlan.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieClusteringPlan.ENCODER.encode((HoodieClusteringPlan) this);
    }
    
    public static HoodieClusteringPlan fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieClusteringPlan)HoodieClusteringPlan.DECODER.decode(b);
    }
    
    public HoodieClusteringPlan() {
    }
    
    public HoodieClusteringPlan(final List<HoodieClusteringGroup> inputGroups, final HoodieClusteringStrategy strategy, final Map<String, String> extraMetadata, final Integer version) {
        this.inputGroups = inputGroups;
        this.strategy = strategy;
        this.extraMetadata = extraMetadata;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieClusteringPlan.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.inputGroups;
            }
            case 1: {
                return this.strategy;
            }
            case 2: {
                return this.extraMetadata;
            }
            case 3: {
                return this.version;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.inputGroups = (List<HoodieClusteringGroup>)value$;
                break;
            }
            case 1: {
                this.strategy = (HoodieClusteringStrategy)value$;
                break;
            }
            case 2: {
                this.extraMetadata = (Map<String, String>)value$;
                break;
            }
            case 3: {
                this.version = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public List<HoodieClusteringGroup> getInputGroups() {
        return this.inputGroups;
    }
    
    public void setInputGroups(final List<HoodieClusteringGroup> value) {
        this.inputGroups = value;
    }
    
    public HoodieClusteringStrategy getStrategy() {
        return this.strategy;
    }
    
    public void setStrategy(final HoodieClusteringStrategy value) {
        this.strategy = value;
    }
    
    public Map<String, String> getExtraMetadata() {
        return this.extraMetadata;
    }
    
    public void setExtraMetadata(final Map<String, String> value) {
        this.extraMetadata = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieClusteringPlan other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieClusteringPlan.WRITER$.write((HoodieClusteringPlan) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieClusteringPlan.READER$.read((HoodieClusteringPlan) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieClusteringPlan\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"inputGroups\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"strategy\",\"type\":[{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]},\"null\"],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieClusteringPlan.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieClusteringPlan.MODEL$, HoodieClusteringPlan.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieClusteringPlan.MODEL$, HoodieClusteringPlan.SCHEMA$);
        WRITER$ = HoodieClusteringPlan.MODEL$.createDatumWriter(HoodieClusteringPlan.SCHEMA$);
        READER$ = HoodieClusteringPlan.MODEL$.createDatumReader(HoodieClusteringPlan.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieClusteringPlan> implements RecordBuilder<HoodieClusteringPlan>
    {
        private List<HoodieClusteringGroup> inputGroups;
        private HoodieClusteringStrategy strategy;
        private HoodieClusteringStrategy.Builder strategyBuilder;
        private Map<String, String> extraMetadata;
        private Integer version;
        
        private Builder() {
            super(HoodieClusteringPlan.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.inputGroups)) {
                this.inputGroups = (List<HoodieClusteringGroup>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.inputGroups);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.strategy)) {
                this.strategy = (HoodieClusteringStrategy)this.data().deepCopy(this.fields()[1].schema(), (Object)other.strategy);
                this.fieldSetFlags()[1] = true;
            }
            if (other.hasStrategyBuilder()) {
                this.strategyBuilder = HoodieClusteringStrategy.newBuilder(other.getStrategyBuilder());
            }
            if (isValidValue(this.fields()[2], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieClusteringPlan other) {
            super(HoodieClusteringPlan.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.inputGroups)) {
                this.inputGroups = (List<HoodieClusteringGroup>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.inputGroups);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.strategy)) {
                this.strategy = (HoodieClusteringStrategy)this.data().deepCopy(this.fields()[1].schema(), (Object)other.strategy);
                this.fieldSetFlags()[1] = true;
            }
            this.strategyBuilder = null;
            if (isValidValue(this.fields()[2], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public List<HoodieClusteringGroup> getInputGroups() {
            return this.inputGroups;
        }
        
        public Builder setInputGroups(final List<HoodieClusteringGroup> value) {
            this.validate(this.fields()[0], (Object)value);
            this.inputGroups = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasInputGroups() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearInputGroups() {
            this.inputGroups = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public HoodieClusteringStrategy getStrategy() {
            return this.strategy;
        }
        
        public Builder setStrategy(final HoodieClusteringStrategy value) {
            this.validate(this.fields()[1], (Object)value);
            this.strategyBuilder = null;
            this.strategy = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasStrategy() {
            return this.fieldSetFlags()[1];
        }
        
        public HoodieClusteringStrategy.Builder getStrategyBuilder() {
            if (this.strategyBuilder == null) {
                if (this.hasStrategy()) {
                    this.setStrategyBuilder(HoodieClusteringStrategy.newBuilder(this.strategy));
                }
                else {
                    this.setStrategyBuilder(HoodieClusteringStrategy.newBuilder());
                }
            }
            return this.strategyBuilder;
        }
        
        public Builder setStrategyBuilder(final HoodieClusteringStrategy.Builder value) {
            this.clearStrategy();
            this.strategyBuilder = value;
            return this;
        }
        
        public boolean hasStrategyBuilder() {
            return this.strategyBuilder != null;
        }
        
        public Builder clearStrategy() {
            this.strategy = null;
            this.strategyBuilder = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Map<String, String> getExtraMetadata() {
            return this.extraMetadata;
        }
        
        public Builder setExtraMetadata(final Map<String, String> value) {
            this.validate(this.fields()[2], (Object)value);
            this.extraMetadata = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasExtraMetadata() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearExtraMetadata() {
            this.extraMetadata = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[3], (Object)value);
            this.version = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieClusteringPlan build() {
            try {
                final HoodieClusteringPlan record = new HoodieClusteringPlan();
                record.inputGroups = (List<HoodieClusteringGroup>)(this.fieldSetFlags()[0] ? this.inputGroups : this.defaultValue(this.fields()[0]));
                if (this.strategyBuilder != null) {
                    record.strategy = this.strategyBuilder.build();
                }
                else {
                    record.strategy = (HoodieClusteringStrategy)(this.fieldSetFlags()[1] ? this.strategy : this.defaultValue(this.fields()[1]));
                }
                record.extraMetadata = (Map<String, String>)(this.fieldSetFlags()[2] ? this.extraMetadata : this.defaultValue(this.fields()[2]));
                record.version = (Integer)(this.fieldSetFlags()[3] ? this.version : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
