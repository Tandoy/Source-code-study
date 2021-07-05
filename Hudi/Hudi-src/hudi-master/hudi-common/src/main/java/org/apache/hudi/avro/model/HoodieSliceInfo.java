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
import java.util.List;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieSliceInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 6188032345461705953L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieSliceInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieSliceInfo> DECODER;
    @Deprecated
    public String dataFilePath;
    @Deprecated
    public List<String> deltaFilePaths;
    @Deprecated
    public String fileId;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public String bootstrapFilePath;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieSliceInfo> WRITER$;
    private static final DatumReader<HoodieSliceInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieSliceInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieSliceInfo> getDecoder() {
        return HoodieSliceInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieSliceInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieSliceInfo>)new BinaryMessageDecoder((GenericData)HoodieSliceInfo.MODEL$, HoodieSliceInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieSliceInfo.ENCODER.encode((HoodieSliceInfo) this);
    }
    
    public static HoodieSliceInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieSliceInfo)HoodieSliceInfo.DECODER.decode(b);
    }
    
    public HoodieSliceInfo() {
    }
    
    public HoodieSliceInfo(final String dataFilePath, final List<String> deltaFilePaths, final String fileId, final String partitionPath, final String bootstrapFilePath, final Integer version) {
        this.dataFilePath = dataFilePath;
        this.deltaFilePaths = deltaFilePaths;
        this.fileId = fileId;
        this.partitionPath = partitionPath;
        this.bootstrapFilePath = bootstrapFilePath;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieSliceInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.dataFilePath;
            }
            case 1: {
                return this.deltaFilePaths;
            }
            case 2: {
                return this.fileId;
            }
            case 3: {
                return this.partitionPath;
            }
            case 4: {
                return this.bootstrapFilePath;
            }
            case 5: {
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
                this.dataFilePath = (String)value$;
                break;
            }
            case 1: {
                this.deltaFilePaths = (List<String>)value$;
                break;
            }
            case 2: {
                this.fileId = (String)value$;
                break;
            }
            case 3: {
                this.partitionPath = (String)value$;
                break;
            }
            case 4: {
                this.bootstrapFilePath = (String)value$;
                break;
            }
            case 5: {
                this.version = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getDataFilePath() {
        return this.dataFilePath;
    }
    
    public void setDataFilePath(final String value) {
        this.dataFilePath = value;
    }
    
    public List<String> getDeltaFilePaths() {
        return this.deltaFilePaths;
    }
    
    public void setDeltaFilePaths(final List<String> value) {
        this.deltaFilePaths = value;
    }
    
    public String getFileId() {
        return this.fileId;
    }
    
    public void setFileId(final String value) {
        this.fileId = value;
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public String getBootstrapFilePath() {
        return this.bootstrapFilePath;
    }
    
    public void setBootstrapFilePath(final String value) {
        this.bootstrapFilePath = value;
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
    
    public static Builder newBuilder(final HoodieSliceInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieSliceInfo.WRITER$.write((HoodieSliceInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieSliceInfo.READER$.read((HoodieSliceInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieSliceInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieSliceInfo.MODEL$, HoodieSliceInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieSliceInfo.MODEL$, HoodieSliceInfo.SCHEMA$);
        WRITER$ = HoodieSliceInfo.MODEL$.createDatumWriter(HoodieSliceInfo.SCHEMA$);
        READER$ = HoodieSliceInfo.MODEL$.createDatumReader(HoodieSliceInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieSliceInfo> implements RecordBuilder<HoodieSliceInfo>
    {
        private String dataFilePath;
        private List<String> deltaFilePaths;
        private String fileId;
        private String partitionPath;
        private String bootstrapFilePath;
        private Integer version;
        
        private Builder() {
            super(HoodieSliceInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.dataFilePath)) {
                this.dataFilePath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.dataFilePath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.deltaFilePaths)) {
                this.deltaFilePaths = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.deltaFilePaths);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.fileId);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.bootstrapFilePath)) {
                this.bootstrapFilePath = (String)this.data().deepCopy(this.fields()[4].schema(), (Object)other.bootstrapFilePath);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
        }
        
        private Builder(final HoodieSliceInfo other) {
            super(HoodieSliceInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.dataFilePath)) {
                this.dataFilePath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.dataFilePath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.deltaFilePaths)) {
                this.deltaFilePaths = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.deltaFilePaths);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.fileId);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.bootstrapFilePath)) {
                this.bootstrapFilePath = (String)this.data().deepCopy(this.fields()[4].schema(), (Object)other.bootstrapFilePath);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
        }
        
        public String getDataFilePath() {
            return this.dataFilePath;
        }
        
        public Builder setDataFilePath(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.dataFilePath = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasDataFilePath() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearDataFilePath() {
            this.dataFilePath = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public List<String> getDeltaFilePaths() {
            return this.deltaFilePaths;
        }
        
        public Builder setDeltaFilePaths(final List<String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.deltaFilePaths = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasDeltaFilePaths() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearDeltaFilePaths() {
            this.deltaFilePaths = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getFileId() {
            return this.fileId;
        }
        
        public Builder setFileId(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.fileId = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasFileId() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearFileId() {
            this.fileId = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public String getBootstrapFilePath() {
            return this.bootstrapFilePath;
        }
        
        public Builder setBootstrapFilePath(final String value) {
            this.validate(this.fields()[4], (Object)value);
            this.bootstrapFilePath = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasBootstrapFilePath() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearBootstrapFilePath() {
            this.bootstrapFilePath = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[5], (Object)value);
            this.version = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public HoodieSliceInfo build() {
            try {
                final HoodieSliceInfo record = new HoodieSliceInfo();
                record.dataFilePath = (String)(this.fieldSetFlags()[0] ? this.dataFilePath : this.defaultValue(this.fields()[0]));
                record.deltaFilePaths = (List<String>)(this.fieldSetFlags()[1] ? this.deltaFilePaths : this.defaultValue(this.fields()[1]));
                record.fileId = (String)(this.fieldSetFlags()[2] ? this.fileId : this.defaultValue(this.fields()[2]));
                record.partitionPath = (String)(this.fieldSetFlags()[3] ? this.partitionPath : this.defaultValue(this.fields()[3]));
                record.bootstrapFilePath = (String)(this.fieldSetFlags()[4] ? this.bootstrapFilePath : this.defaultValue(this.fields()[4]));
                record.version = (Integer)(this.fieldSetFlags()[5] ? this.version : this.defaultValue(this.fields()[5]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
