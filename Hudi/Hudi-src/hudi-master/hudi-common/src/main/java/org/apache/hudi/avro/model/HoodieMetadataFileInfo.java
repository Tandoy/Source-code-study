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
public class HoodieMetadataFileInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 7809948218412971595L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieMetadataFileInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieMetadataFileInfo> DECODER;
    @Deprecated
    public long size;
    @Deprecated
    public boolean isDeleted;
    private static final DatumWriter<HoodieMetadataFileInfo> WRITER$;
    private static final DatumReader<HoodieMetadataFileInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieMetadataFileInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieMetadataFileInfo> getDecoder() {
        return HoodieMetadataFileInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieMetadataFileInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieMetadataFileInfo>)new BinaryMessageDecoder((GenericData)HoodieMetadataFileInfo.MODEL$, HoodieMetadataFileInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieMetadataFileInfo.ENCODER.encode((HoodieMetadataFileInfo) this);
    }
    
    public static HoodieMetadataFileInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieMetadataFileInfo)HoodieMetadataFileInfo.DECODER.decode(b);
    }
    
    public HoodieMetadataFileInfo() {
    }
    
    public HoodieMetadataFileInfo(final Long size, final Boolean isDeleted) {
        this.size = size;
        this.isDeleted = isDeleted;
    }
    
    public Schema getSchema() {
        return HoodieMetadataFileInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.size;
            }
            case 1: {
                return this.isDeleted;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.size = (long)value$;
                break;
            }
            case 1: {
                this.isDeleted = (boolean)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public Long getSize() {
        return this.size;
    }
    
    public void setSize(final Long value) {
        this.size = value;
    }
    
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }
    
    public void setIsDeleted(final Boolean value) {
        this.isDeleted = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieMetadataFileInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieMetadataFileInfo.WRITER$.write((HoodieMetadataFileInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieMetadataFileInfo.READER$.read((HoodieMetadataFileInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieMetadataFileInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"size\",\"type\":\"long\",\"doc\":\"Size of the file\"},{\"name\":\"isDeleted\",\"type\":\"boolean\",\"doc\":\"True if this file has been deleted\"}]}");
        HoodieMetadataFileInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieMetadataFileInfo.MODEL$, HoodieMetadataFileInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieMetadataFileInfo.MODEL$, HoodieMetadataFileInfo.SCHEMA$);
        WRITER$ = HoodieMetadataFileInfo.MODEL$.createDatumWriter(HoodieMetadataFileInfo.SCHEMA$);
        READER$ = HoodieMetadataFileInfo.MODEL$.createDatumReader(HoodieMetadataFileInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieMetadataFileInfo> implements RecordBuilder<HoodieMetadataFileInfo>
    {
        private long size;
        private boolean isDeleted;
        
        private Builder() {
            super(HoodieMetadataFileInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.size)) {
                this.size = (long)this.data().deepCopy(this.fields()[0].schema(), (Object)other.size);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.isDeleted)) {
                this.isDeleted = (boolean)this.data().deepCopy(this.fields()[1].schema(), (Object)other.isDeleted);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        private Builder(final HoodieMetadataFileInfo other) {
            super(HoodieMetadataFileInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.size)) {
                this.size = (long)this.data().deepCopy(this.fields()[0].schema(), (Object)other.size);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.isDeleted)) {
                this.isDeleted = (boolean)this.data().deepCopy(this.fields()[1].schema(), (Object)other.isDeleted);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        public Long getSize() {
            return this.size;
        }
        
        public Builder setSize(final long value) {
            this.validate(this.fields()[0], (Object)value);
            this.size = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasSize() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearSize() {
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Boolean getIsDeleted() {
            return this.isDeleted;
        }
        
        public Builder setIsDeleted(final boolean value) {
            this.validate(this.fields()[1], (Object)value);
            this.isDeleted = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasIsDeleted() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearIsDeleted() {
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodieMetadataFileInfo build() {
            try {
                final HoodieMetadataFileInfo record = new HoodieMetadataFileInfo();
                record.size = (long)(this.fieldSetFlags()[0] ? this.size : this.defaultValue(this.fields()[0]));
                record.isDeleted = (boolean)(this.fieldSetFlags()[1] ? this.isDeleted : this.defaultValue(this.fields()[1]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
