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
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieBootstrapIndexInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -5871235112572129524L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieBootstrapIndexInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieBootstrapIndexInfo> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public String bootstrapBasePath;
    @Deprecated
    public Long createdTimestamp;
    @Deprecated
    public Integer numKeys;
    private static final DatumWriter<HoodieBootstrapIndexInfo> WRITER$;
    private static final DatumReader<HoodieBootstrapIndexInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieBootstrapIndexInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapIndexInfo> getDecoder() {
        return HoodieBootstrapIndexInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapIndexInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieBootstrapIndexInfo>)new BinaryMessageDecoder((GenericData)HoodieBootstrapIndexInfo.MODEL$, HoodieBootstrapIndexInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieBootstrapIndexInfo.ENCODER.encode((HoodieBootstrapIndexInfo) this);
    }
    
    public static HoodieBootstrapIndexInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieBootstrapIndexInfo)HoodieBootstrapIndexInfo.DECODER.decode(b);
    }
    
    public HoodieBootstrapIndexInfo() {
    }
    
    public HoodieBootstrapIndexInfo(final Integer version, final String bootstrapBasePath, final Long createdTimestamp, final Integer numKeys) {
        this.version = version;
        this.bootstrapBasePath = bootstrapBasePath;
        this.createdTimestamp = createdTimestamp;
        this.numKeys = numKeys;
    }
    
    public Schema getSchema() {
        return HoodieBootstrapIndexInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.bootstrapBasePath;
            }
            case 2: {
                return this.createdTimestamp;
            }
            case 3: {
                return this.numKeys;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.version = (Integer)value$;
                break;
            }
            case 1: {
                this.bootstrapBasePath = (String)value$;
                break;
            }
            case 2: {
                this.createdTimestamp = (Long)value$;
                break;
            }
            case 3: {
                this.numKeys = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public String getBootstrapBasePath() {
        return this.bootstrapBasePath;
    }
    
    public void setBootstrapBasePath(final String value) {
        this.bootstrapBasePath = value;
    }
    
    public Long getCreatedTimestamp() {
        return this.createdTimestamp;
    }
    
    public void setCreatedTimestamp(final Long value) {
        this.createdTimestamp = value;
    }
    
    public Integer getNumKeys() {
        return this.numKeys;
    }
    
    public void setNumKeys(final Integer value) {
        this.numKeys = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieBootstrapIndexInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieBootstrapIndexInfo.WRITER$.write((HoodieBootstrapIndexInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieBootstrapIndexInfo.READER$.read((HoodieBootstrapIndexInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieBootstrapIndexInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapBasePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"createdTimestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numKeys\",\"type\":[\"null\",\"int\"],\"default\":null}]}");
        HoodieBootstrapIndexInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieBootstrapIndexInfo.MODEL$, HoodieBootstrapIndexInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieBootstrapIndexInfo.MODEL$, HoodieBootstrapIndexInfo.SCHEMA$);
        WRITER$ = HoodieBootstrapIndexInfo.MODEL$.createDatumWriter(HoodieBootstrapIndexInfo.SCHEMA$);
        READER$ = HoodieBootstrapIndexInfo.MODEL$.createDatumReader(HoodieBootstrapIndexInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieBootstrapIndexInfo> implements RecordBuilder<HoodieBootstrapIndexInfo>
    {
        private Integer version;
        private String bootstrapBasePath;
        private Long createdTimestamp;
        private Integer numKeys;
        
        private Builder() {
            super(HoodieBootstrapIndexInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapBasePath)) {
                this.bootstrapBasePath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapBasePath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.createdTimestamp)) {
                this.createdTimestamp = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.createdTimestamp);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.numKeys)) {
                this.numKeys = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.numKeys);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieBootstrapIndexInfo other) {
            super(HoodieBootstrapIndexInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapBasePath)) {
                this.bootstrapBasePath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapBasePath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.createdTimestamp)) {
                this.createdTimestamp = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.createdTimestamp);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.numKeys)) {
                this.numKeys = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.numKeys);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[0], (Object)value);
            this.version = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public String getBootstrapBasePath() {
            return this.bootstrapBasePath;
        }
        
        public Builder setBootstrapBasePath(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.bootstrapBasePath = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasBootstrapBasePath() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearBootstrapBasePath() {
            this.bootstrapBasePath = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Long getCreatedTimestamp() {
            return this.createdTimestamp;
        }
        
        public Builder setCreatedTimestamp(final Long value) {
            this.validate(this.fields()[2], (Object)value);
            this.createdTimestamp = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasCreatedTimestamp() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearCreatedTimestamp() {
            this.createdTimestamp = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Integer getNumKeys() {
            return this.numKeys;
        }
        
        public Builder setNumKeys(final Integer value) {
            this.validate(this.fields()[3], (Object)value);
            this.numKeys = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasNumKeys() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearNumKeys() {
            this.numKeys = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieBootstrapIndexInfo build() {
            try {
                final HoodieBootstrapIndexInfo record = new HoodieBootstrapIndexInfo();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                record.bootstrapBasePath = (String)(this.fieldSetFlags()[1] ? this.bootstrapBasePath : this.defaultValue(this.fields()[1]));
                record.createdTimestamp = (Long)(this.fieldSetFlags()[2] ? this.createdTimestamp : this.defaultValue(this.fields()[2]));
                record.numKeys = (Integer)(this.fieldSetFlags()[3] ? this.numKeys : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
