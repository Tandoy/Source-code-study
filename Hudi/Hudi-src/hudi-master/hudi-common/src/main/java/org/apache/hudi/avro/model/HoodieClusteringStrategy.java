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
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieClusteringStrategy extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -2383210426456244756L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieClusteringStrategy> ENCODER;
    private static final BinaryMessageDecoder<HoodieClusteringStrategy> DECODER;
    @Deprecated
    public String strategyClassName;
    @Deprecated
    public Map<String, String> strategyParams;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieClusteringStrategy> WRITER$;
    private static final DatumReader<HoodieClusteringStrategy> READER$;
    
    public static Schema getClassSchema() {
        return HoodieClusteringStrategy.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringStrategy> getDecoder() {
        return HoodieClusteringStrategy.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringStrategy> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieClusteringStrategy>)new BinaryMessageDecoder((GenericData)HoodieClusteringStrategy.MODEL$, HoodieClusteringStrategy.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieClusteringStrategy.ENCODER.encode((HoodieClusteringStrategy) this);
    }
    
    public static HoodieClusteringStrategy fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieClusteringStrategy)HoodieClusteringStrategy.DECODER.decode(b);
    }
    
    public HoodieClusteringStrategy() {
    }
    
    public HoodieClusteringStrategy(final String strategyClassName, final Map<String, String> strategyParams, final Integer version) {
        this.strategyClassName = strategyClassName;
        this.strategyParams = strategyParams;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieClusteringStrategy.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.strategyClassName;
            }
            case 1: {
                return this.strategyParams;
            }
            case 2: {
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
                this.strategyClassName = (String)value$;
                break;
            }
            case 1: {
                this.strategyParams = (Map<String, String>)value$;
                break;
            }
            case 2: {
                this.version = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getStrategyClassName() {
        return this.strategyClassName;
    }
    
    public void setStrategyClassName(final String value) {
        this.strategyClassName = value;
    }
    
    public Map<String, String> getStrategyParams() {
        return this.strategyParams;
    }
    
    public void setStrategyParams(final Map<String, String> value) {
        this.strategyParams = value;
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
    
    public static Builder newBuilder(final HoodieClusteringStrategy other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieClusteringStrategy.WRITER$.write((HoodieClusteringStrategy) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieClusteringStrategy.READER$.read((HoodieClusteringStrategy) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieClusteringStrategy.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieClusteringStrategy.MODEL$, HoodieClusteringStrategy.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieClusteringStrategy.MODEL$, HoodieClusteringStrategy.SCHEMA$);
        WRITER$ = HoodieClusteringStrategy.MODEL$.createDatumWriter(HoodieClusteringStrategy.SCHEMA$);
        READER$ = HoodieClusteringStrategy.MODEL$.createDatumReader(HoodieClusteringStrategy.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieClusteringStrategy> implements RecordBuilder<HoodieClusteringStrategy>
    {
        private String strategyClassName;
        private Map<String, String> strategyParams;
        private Integer version;
        
        private Builder() {
            super(HoodieClusteringStrategy.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.strategyClassName)) {
                this.strategyClassName = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.strategyClassName);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.strategyParams)) {
                this.strategyParams = (Map<String, String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.strategyParams);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.version);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        private Builder(final HoodieClusteringStrategy other) {
            super(HoodieClusteringStrategy.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.strategyClassName)) {
                this.strategyClassName = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.strategyClassName);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.strategyParams)) {
                this.strategyParams = (Map<String, String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.strategyParams);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.version);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        public String getStrategyClassName() {
            return this.strategyClassName;
        }
        
        public Builder setStrategyClassName(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.strategyClassName = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasStrategyClassName() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearStrategyClassName() {
            this.strategyClassName = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Map<String, String> getStrategyParams() {
            return this.strategyParams;
        }
        
        public Builder setStrategyParams(final Map<String, String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.strategyParams = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasStrategyParams() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearStrategyParams() {
            this.strategyParams = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[2], (Object)value);
            this.version = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public HoodieClusteringStrategy build() {
            try {
                final HoodieClusteringStrategy record = new HoodieClusteringStrategy();
                record.strategyClassName = (String)(this.fieldSetFlags()[0] ? this.strategyClassName : this.defaultValue(this.fields()[0]));
                record.strategyParams = (Map<String, String>)(this.fieldSetFlags()[1] ? this.strategyParams : this.defaultValue(this.fields()[1]));
                record.version = (Integer)(this.fieldSetFlags()[2] ? this.version : this.defaultValue(this.fields()[2]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
