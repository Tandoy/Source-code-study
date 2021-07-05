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
public class HoodieSavepointMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -5647345812152033769L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieSavepointMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieSavepointMetadata> DECODER;
    @Deprecated
    public String savepointedBy;
    @Deprecated
    public long savepointedAt;
    @Deprecated
    public String comments;
    @Deprecated
    public Map<String, HoodieSavepointPartitionMetadata> partitionMetadata;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieSavepointMetadata> WRITER$;
    private static final DatumReader<HoodieSavepointMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieSavepointMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieSavepointMetadata> getDecoder() {
        return HoodieSavepointMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieSavepointMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieSavepointMetadata>)new BinaryMessageDecoder((GenericData)HoodieSavepointMetadata.MODEL$, HoodieSavepointMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieSavepointMetadata.ENCODER.encode((HoodieSavepointMetadata) this);
    }
    
    public static HoodieSavepointMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieSavepointMetadata)HoodieSavepointMetadata.DECODER.decode(b);
    }
    
    public HoodieSavepointMetadata() {
    }
    
    public HoodieSavepointMetadata(final String savepointedBy, final Long savepointedAt, final String comments, final Map<String, HoodieSavepointPartitionMetadata> partitionMetadata, final Integer version) {
        this.savepointedBy = savepointedBy;
        this.savepointedAt = savepointedAt;
        this.comments = comments;
        this.partitionMetadata = partitionMetadata;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieSavepointMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.savepointedBy;
            }
            case 1: {
                return this.savepointedAt;
            }
            case 2: {
                return this.comments;
            }
            case 3: {
                return this.partitionMetadata;
            }
            case 4: {
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
                this.savepointedBy = (String)value$;
                break;
            }
            case 1: {
                this.savepointedAt = (long)value$;
                break;
            }
            case 2: {
                this.comments = (String)value$;
                break;
            }
            case 3: {
                this.partitionMetadata = (Map<String, HoodieSavepointPartitionMetadata>)value$;
                break;
            }
            case 4: {
                this.version = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getSavepointedBy() {
        return this.savepointedBy;
    }
    
    public void setSavepointedBy(final String value) {
        this.savepointedBy = value;
    }
    
    public Long getSavepointedAt() {
        return this.savepointedAt;
    }
    
    public void setSavepointedAt(final Long value) {
        this.savepointedAt = value;
    }
    
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(final String value) {
        this.comments = value;
    }
    
    public Map<String, HoodieSavepointPartitionMetadata> getPartitionMetadata() {
        return this.partitionMetadata;
    }
    
    public void setPartitionMetadata(final Map<String, HoodieSavepointPartitionMetadata> value) {
        this.partitionMetadata = value;
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
    
    public static Builder newBuilder(final HoodieSavepointMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieSavepointMetadata.WRITER$.write((HoodieSavepointMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieSavepointMetadata.READER$.read((HoodieSavepointMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieSavepointMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"savepointedBy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"savepointedAt\",\"type\":\"long\"},{\"name\":\"comments\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieSavepointPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"savepointDataFile\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieSavepointMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieSavepointMetadata.MODEL$, HoodieSavepointMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieSavepointMetadata.MODEL$, HoodieSavepointMetadata.SCHEMA$);
        WRITER$ = HoodieSavepointMetadata.MODEL$.createDatumWriter(HoodieSavepointMetadata.SCHEMA$);
        READER$ = HoodieSavepointMetadata.MODEL$.createDatumReader(HoodieSavepointMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieSavepointMetadata> implements RecordBuilder<HoodieSavepointMetadata>
    {
        private String savepointedBy;
        private long savepointedAt;
        private String comments;
        private Map<String, HoodieSavepointPartitionMetadata> partitionMetadata;
        private Integer version;
        
        private Builder() {
            super(HoodieSavepointMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.savepointedBy)) {
                this.savepointedBy = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.savepointedBy);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.savepointedAt)) {
                this.savepointedAt = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.savepointedAt);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.comments)) {
                this.comments = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.comments);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieSavepointPartitionMetadata>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.version);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        private Builder(final HoodieSavepointMetadata other) {
            super(HoodieSavepointMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.savepointedBy)) {
                this.savepointedBy = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.savepointedBy);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.savepointedAt)) {
                this.savepointedAt = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.savepointedAt);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.comments)) {
                this.comments = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.comments);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieSavepointPartitionMetadata>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.version);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        public String getSavepointedBy() {
            return this.savepointedBy;
        }
        
        public Builder setSavepointedBy(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.savepointedBy = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasSavepointedBy() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearSavepointedBy() {
            this.savepointedBy = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Long getSavepointedAt() {
            return this.savepointedAt;
        }
        
        public Builder setSavepointedAt(final long value) {
            this.validate(this.fields()[1], (Object)value);
            this.savepointedAt = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasSavepointedAt() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearSavepointedAt() {
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getComments() {
            return this.comments;
        }
        
        public Builder setComments(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.comments = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasComments() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearComments() {
            this.comments = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Map<String, HoodieSavepointPartitionMetadata> getPartitionMetadata() {
            return this.partitionMetadata;
        }
        
        public Builder setPartitionMetadata(final Map<String, HoodieSavepointPartitionMetadata> value) {
            this.validate(this.fields()[3], (Object)value);
            this.partitionMetadata = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasPartitionMetadata() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearPartitionMetadata() {
            this.partitionMetadata = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[4], (Object)value);
            this.version = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieSavepointMetadata build() {
            try {
                final HoodieSavepointMetadata record = new HoodieSavepointMetadata();
                record.savepointedBy = (String)(this.fieldSetFlags()[0] ? this.savepointedBy : this.defaultValue(this.fields()[0]));
                record.savepointedAt = (long)(this.fieldSetFlags()[1] ? this.savepointedAt : this.defaultValue(this.fields()[1]));
                record.comments = (String)(this.fieldSetFlags()[2] ? this.comments : this.defaultValue(this.fields()[2]));
                record.partitionMetadata = (Map<String, HoodieSavepointPartitionMetadata>)(this.fieldSetFlags()[3] ? this.partitionMetadata : this.defaultValue(this.fields()[3]));
                record.version = (Integer)(this.fieldSetFlags()[4] ? this.version : this.defaultValue(this.fields()[4]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
