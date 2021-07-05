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
public class HoodieCleanFileInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 6026630272931428184L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCleanFileInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieCleanFileInfo> DECODER;
    @Deprecated
    public String filePath;
    @Deprecated
    public Boolean isBootstrapBaseFile;
    private static final DatumWriter<HoodieCleanFileInfo> WRITER$;
    private static final DatumReader<HoodieCleanFileInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCleanFileInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCleanFileInfo> getDecoder() {
        return HoodieCleanFileInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCleanFileInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCleanFileInfo>)new BinaryMessageDecoder((GenericData)HoodieCleanFileInfo.MODEL$, HoodieCleanFileInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCleanFileInfo.ENCODER.encode((HoodieCleanFileInfo) this);
    }
    
    public static HoodieCleanFileInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCleanFileInfo)HoodieCleanFileInfo.DECODER.decode(b);
    }
    
    public HoodieCleanFileInfo() {
    }
    
    public HoodieCleanFileInfo(final String filePath, final Boolean isBootstrapBaseFile) {
        this.filePath = filePath;
        this.isBootstrapBaseFile = isBootstrapBaseFile;
    }
    
    public Schema getSchema() {
        return HoodieCleanFileInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.filePath;
            }
            case 1: {
                return this.isBootstrapBaseFile;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.filePath = (String)value$;
                break;
            }
            case 1: {
                this.isBootstrapBaseFile = (Boolean)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public void setFilePath(final String value) {
        this.filePath = value;
    }
    
    public Boolean getIsBootstrapBaseFile() {
        return this.isBootstrapBaseFile;
    }
    
    public void setIsBootstrapBaseFile(final Boolean value) {
        this.isBootstrapBaseFile = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCleanFileInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCleanFileInfo.WRITER$.write((HoodieCleanFileInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCleanFileInfo.READER$.read((HoodieCleanFileInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCleanFileInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"filePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"isBootstrapBaseFile\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}");
        HoodieCleanFileInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCleanFileInfo.MODEL$, HoodieCleanFileInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCleanFileInfo.MODEL$, HoodieCleanFileInfo.SCHEMA$);
        WRITER$ = HoodieCleanFileInfo.MODEL$.createDatumWriter(HoodieCleanFileInfo.SCHEMA$);
        READER$ = HoodieCleanFileInfo.MODEL$.createDatumReader(HoodieCleanFileInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCleanFileInfo> implements RecordBuilder<HoodieCleanFileInfo>
    {
        private String filePath;
        private Boolean isBootstrapBaseFile;
        
        private Builder() {
            super(HoodieCleanFileInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.filePath)) {
                this.filePath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.filePath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.isBootstrapBaseFile)) {
                this.isBootstrapBaseFile = (Boolean)this.data().deepCopy(this.fields()[1].schema(), (Object)other.isBootstrapBaseFile);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        private Builder(final HoodieCleanFileInfo other) {
            super(HoodieCleanFileInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.filePath)) {
                this.filePath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.filePath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.isBootstrapBaseFile)) {
                this.isBootstrapBaseFile = (Boolean)this.data().deepCopy(this.fields()[1].schema(), (Object)other.isBootstrapBaseFile);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        public String getFilePath() {
            return this.filePath;
        }
        
        public Builder setFilePath(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.filePath = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasFilePath() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearFilePath() {
            this.filePath = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Boolean getIsBootstrapBaseFile() {
            return this.isBootstrapBaseFile;
        }
        
        public Builder setIsBootstrapBaseFile(final Boolean value) {
            this.validate(this.fields()[1], (Object)value);
            this.isBootstrapBaseFile = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasIsBootstrapBaseFile() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearIsBootstrapBaseFile() {
            this.isBootstrapBaseFile = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodieCleanFileInfo build() {
            try {
                final HoodieCleanFileInfo record = new HoodieCleanFileInfo();
                record.filePath = (String)(this.fieldSetFlags()[0] ? this.filePath : this.defaultValue(this.fields()[0]));
                record.isBootstrapBaseFile = (Boolean)(this.fieldSetFlags()[1] ? this.isBootstrapBaseFile : this.defaultValue(this.fields()[1]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
