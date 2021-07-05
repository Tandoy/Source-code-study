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
public class HoodieCompactionPlan extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -4303349821552047105L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCompactionPlan> ENCODER;
    private static final BinaryMessageDecoder<HoodieCompactionPlan> DECODER;
    @Deprecated
    public List<HoodieCompactionOperation> operations;
    @Deprecated
    public Map<String, String> extraMetadata;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieCompactionPlan> WRITER$;
    private static final DatumReader<HoodieCompactionPlan> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCompactionPlan.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionPlan> getDecoder() {
        return HoodieCompactionPlan.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionPlan> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCompactionPlan>)new BinaryMessageDecoder((GenericData)HoodieCompactionPlan.MODEL$, HoodieCompactionPlan.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCompactionPlan.ENCODER.encode((HoodieCompactionPlan) this);
    }
    
    public static HoodieCompactionPlan fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCompactionPlan)HoodieCompactionPlan.DECODER.decode(b);
    }
    
    public HoodieCompactionPlan() {
    }
    
    public HoodieCompactionPlan(final List<HoodieCompactionOperation> operations, final Map<String, String> extraMetadata, final Integer version) {
        this.operations = operations;
        this.extraMetadata = extraMetadata;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieCompactionPlan.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.operations;
            }
            case 1: {
                return this.extraMetadata;
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
                this.operations = (List<HoodieCompactionOperation>)value$;
                break;
            }
            case 1: {
                this.extraMetadata = (Map<String, String>)value$;
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
    
    public List<HoodieCompactionOperation> getOperations() {
        return this.operations;
    }
    
    public void setOperations(final List<HoodieCompactionOperation> value) {
        this.operations = value;
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
    
    public static Builder newBuilder(final HoodieCompactionPlan other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCompactionPlan.WRITER$.write((HoodieCompactionPlan) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCompactionPlan.READER$.read((HoodieCompactionPlan) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionPlan\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"operations\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCompactionOperation\",\"fields\":[{\"name\":\"baseInstantTime\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieCompactionPlan.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCompactionPlan.MODEL$, HoodieCompactionPlan.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCompactionPlan.MODEL$, HoodieCompactionPlan.SCHEMA$);
        WRITER$ = HoodieCompactionPlan.MODEL$.createDatumWriter(HoodieCompactionPlan.SCHEMA$);
        READER$ = HoodieCompactionPlan.MODEL$.createDatumReader(HoodieCompactionPlan.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCompactionPlan> implements RecordBuilder<HoodieCompactionPlan>
    {
        private List<HoodieCompactionOperation> operations;
        private Map<String, String> extraMetadata;
        private Integer version;
        
        private Builder() {
            super(HoodieCompactionPlan.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.operations)) {
                this.operations = (List<HoodieCompactionOperation>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.operations);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.version);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        private Builder(final HoodieCompactionPlan other) {
            super(HoodieCompactionPlan.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.operations)) {
                this.operations = (List<HoodieCompactionOperation>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.operations);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.version);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        public List<HoodieCompactionOperation> getOperations() {
            return this.operations;
        }
        
        public Builder setOperations(final List<HoodieCompactionOperation> value) {
            this.validate(this.fields()[0], (Object)value);
            this.operations = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasOperations() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearOperations() {
            this.operations = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Map<String, String> getExtraMetadata() {
            return this.extraMetadata;
        }
        
        public Builder setExtraMetadata(final Map<String, String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.extraMetadata = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasExtraMetadata() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearExtraMetadata() {
            this.extraMetadata = null;
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
        
        public HoodieCompactionPlan build() {
            try {
                final HoodieCompactionPlan record = new HoodieCompactionPlan();
                record.operations = (List<HoodieCompactionOperation>)(this.fieldSetFlags()[0] ? this.operations : this.defaultValue(this.fields()[0]));
                record.extraMetadata = (Map<String, String>)(this.fieldSetFlags()[1] ? this.extraMetadata : this.defaultValue(this.fields()[1]));
                record.version = (Integer)(this.fieldSetFlags()[2] ? this.version : this.defaultValue(this.fields()[2]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
