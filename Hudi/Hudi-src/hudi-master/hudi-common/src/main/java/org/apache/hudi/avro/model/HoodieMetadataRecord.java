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
public class HoodieMetadataRecord extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 7254239056586950646L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieMetadataRecord> ENCODER;
    private static final BinaryMessageDecoder<HoodieMetadataRecord> DECODER;
    @Deprecated
    public String key;
    @Deprecated
    public int type;
    @Deprecated
    public Map<String, HoodieMetadataFileInfo> filesystemMetadata;
    private static final DatumWriter<HoodieMetadataRecord> WRITER$;
    private static final DatumReader<HoodieMetadataRecord> READER$;
    
    public static Schema getClassSchema() {
        return HoodieMetadataRecord.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieMetadataRecord> getDecoder() {
        return HoodieMetadataRecord.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieMetadataRecord> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieMetadataRecord>)new BinaryMessageDecoder((GenericData)HoodieMetadataRecord.MODEL$, HoodieMetadataRecord.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieMetadataRecord.ENCODER.encode((HoodieMetadataRecord) this);
    }
    
    public static HoodieMetadataRecord fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieMetadataRecord)HoodieMetadataRecord.DECODER.decode(b);
    }
    
    public HoodieMetadataRecord() {
    }
    
    public HoodieMetadataRecord(final String key, final Integer type, final Map<String, HoodieMetadataFileInfo> filesystemMetadata) {
        this.key = key;
        this.type = type;
        this.filesystemMetadata = filesystemMetadata;
    }
    
    public Schema getSchema() {
        return HoodieMetadataRecord.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.key;
            }
            case 1: {
                return this.type;
            }
            case 2: {
                return this.filesystemMetadata;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.key = (String)value$;
                break;
            }
            case 1: {
                this.type = (int)value$;
                break;
            }
            case 2: {
                this.filesystemMetadata = (Map<String, HoodieMetadataFileInfo>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String value) {
        this.key = value;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(final Integer value) {
        this.type = value;
    }
    
    public Map<String, HoodieMetadataFileInfo> getFilesystemMetadata() {
        return this.filesystemMetadata;
    }
    
    public void setFilesystemMetadata(final Map<String, HoodieMetadataFileInfo> value) {
        this.filesystemMetadata = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieMetadataRecord other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieMetadataRecord.WRITER$.write((HoodieMetadataRecord) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieMetadataRecord.READER$.read((HoodieMetadataRecord) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieMetadataRecord\",\"namespace\":\"org.apache.hudi.avro.model\",\"doc\":\"A record saved within the Metadata Table\",\"fields\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":\"int\",\"doc\":\"Type of the metadata record\"},{\"name\":\"filesystemMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieMetadataFileInfo\",\"fields\":[{\"name\":\"size\",\"type\":\"long\",\"doc\":\"Size of the file\"},{\"name\":\"isDeleted\",\"type\":\"boolean\",\"doc\":\"True if this file has been deleted\"}]},\"avro.java.string\":\"String\"}],\"doc\":\"Contains information about partitions and files within the dataset\"}]}");
        HoodieMetadataRecord.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieMetadataRecord.MODEL$, HoodieMetadataRecord.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieMetadataRecord.MODEL$, HoodieMetadataRecord.SCHEMA$);
        WRITER$ = HoodieMetadataRecord.MODEL$.createDatumWriter(HoodieMetadataRecord.SCHEMA$);
        READER$ = HoodieMetadataRecord.MODEL$.createDatumReader(HoodieMetadataRecord.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieMetadataRecord> implements RecordBuilder<HoodieMetadataRecord>
    {
        private String key;
        private int type;
        private Map<String, HoodieMetadataFileInfo> filesystemMetadata;
        
        private Builder() {
            super(HoodieMetadataRecord.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.key)) {
                this.key = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.key);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.type)) {
                this.type = (int)this.data().deepCopy(this.fields()[1].schema(), (Object)other.type);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.filesystemMetadata)) {
                this.filesystemMetadata = (Map<String, HoodieMetadataFileInfo>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.filesystemMetadata);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        private Builder(final HoodieMetadataRecord other) {
            super(HoodieMetadataRecord.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.key)) {
                this.key = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.key);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.type)) {
                this.type = (int)this.data().deepCopy(this.fields()[1].schema(), (Object)other.type);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.filesystemMetadata)) {
                this.filesystemMetadata = (Map<String, HoodieMetadataFileInfo>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.filesystemMetadata);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        public String getKey() {
            return this.key;
        }
        
        public Builder setKey(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.key = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasKey() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearKey() {
            this.key = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Integer getType() {
            return this.type;
        }
        
        public Builder setType(final int value) {
            this.validate(this.fields()[1], (Object)value);
            this.type = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasType() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearType() {
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Map<String, HoodieMetadataFileInfo> getFilesystemMetadata() {
            return this.filesystemMetadata;
        }
        
        public Builder setFilesystemMetadata(final Map<String, HoodieMetadataFileInfo> value) {
            this.validate(this.fields()[2], (Object)value);
            this.filesystemMetadata = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasFilesystemMetadata() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearFilesystemMetadata() {
            this.filesystemMetadata = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public HoodieMetadataRecord build() {
            try {
                final HoodieMetadataRecord record = new HoodieMetadataRecord();
                record.key = (String)(this.fieldSetFlags()[0] ? this.key : this.defaultValue(this.fields()[0]));
                record.type = (int)(this.fieldSetFlags()[1] ? this.type : this.defaultValue(this.fields()[1]));
                record.filesystemMetadata = (Map<String, HoodieMetadataFileInfo>)(this.fieldSetFlags()[2] ? this.filesystemMetadata : this.defaultValue(this.fields()[2]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
