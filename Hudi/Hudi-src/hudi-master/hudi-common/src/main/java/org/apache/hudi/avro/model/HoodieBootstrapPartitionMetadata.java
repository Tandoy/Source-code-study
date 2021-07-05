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
public class HoodieBootstrapPartitionMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -4947496544470001002L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieBootstrapPartitionMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieBootstrapPartitionMetadata> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public String bootstrapPartitionPath;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public Map<String, HoodieFileStatus> fileIdToBootstrapFile;
    private static final DatumWriter<HoodieBootstrapPartitionMetadata> WRITER$;
    private static final DatumReader<HoodieBootstrapPartitionMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieBootstrapPartitionMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapPartitionMetadata> getDecoder() {
        return HoodieBootstrapPartitionMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapPartitionMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieBootstrapPartitionMetadata>)new BinaryMessageDecoder((GenericData)HoodieBootstrapPartitionMetadata.MODEL$, HoodieBootstrapPartitionMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieBootstrapPartitionMetadata.ENCODER.encode((HoodieBootstrapPartitionMetadata) this);
    }
    
    public static HoodieBootstrapPartitionMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieBootstrapPartitionMetadata)HoodieBootstrapPartitionMetadata.DECODER.decode(b);
    }
    
    public HoodieBootstrapPartitionMetadata() {
    }
    
    public HoodieBootstrapPartitionMetadata(final Integer version, final String bootstrapPartitionPath, final String partitionPath, final Map<String, HoodieFileStatus> fileIdToBootstrapFile) {
        this.version = version;
        this.bootstrapPartitionPath = bootstrapPartitionPath;
        this.partitionPath = partitionPath;
        this.fileIdToBootstrapFile = fileIdToBootstrapFile;
    }
    
    public Schema getSchema() {
        return HoodieBootstrapPartitionMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.bootstrapPartitionPath;
            }
            case 2: {
                return this.partitionPath;
            }
            case 3: {
                return this.fileIdToBootstrapFile;
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
                this.bootstrapPartitionPath = (String)value$;
                break;
            }
            case 2: {
                this.partitionPath = (String)value$;
                break;
            }
            case 3: {
                this.fileIdToBootstrapFile = (Map<String, HoodieFileStatus>)value$;
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
    
    public String getBootstrapPartitionPath() {
        return this.bootstrapPartitionPath;
    }
    
    public void setBootstrapPartitionPath(final String value) {
        this.bootstrapPartitionPath = value;
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public Map<String, HoodieFileStatus> getFileIdToBootstrapFile() {
        return this.fileIdToBootstrapFile;
    }
    
    public void setFileIdToBootstrapFile(final Map<String, HoodieFileStatus> value) {
        this.fileIdToBootstrapFile = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieBootstrapPartitionMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieBootstrapPartitionMetadata.WRITER$.write((HoodieBootstrapPartitionMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieBootstrapPartitionMetadata.READER$.read((HoodieBootstrapPartitionMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieBootstrapPartitionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"fileIdToBootstrapFile\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieFileStatus\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodiePath\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"uri\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"length\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"isDir\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"blockReplication\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"blockSize\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"modificationTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"accessTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"permission\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFSPermission\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"userAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"groupAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"otherAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"stickyBit\",\"type\":[\"null\",\"boolean\"],\"default\":\"null\"}]}],\"default\":null},{\"name\":\"owner\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"group\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"symlink\",\"type\":[\"null\",\"HoodiePath\"],\"default\":null}]},\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodieBootstrapPartitionMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieBootstrapPartitionMetadata.MODEL$, HoodieBootstrapPartitionMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieBootstrapPartitionMetadata.MODEL$, HoodieBootstrapPartitionMetadata.SCHEMA$);
        WRITER$ = HoodieBootstrapPartitionMetadata.MODEL$.createDatumWriter(HoodieBootstrapPartitionMetadata.SCHEMA$);
        READER$ = HoodieBootstrapPartitionMetadata.MODEL$.createDatumReader(HoodieBootstrapPartitionMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieBootstrapPartitionMetadata> implements RecordBuilder<HoodieBootstrapPartitionMetadata>
    {
        private Integer version;
        private String bootstrapPartitionPath;
        private String partitionPath;
        private Map<String, HoodieFileStatus> fileIdToBootstrapFile;
        
        private Builder() {
            super(HoodieBootstrapPartitionMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapPartitionPath)) {
                this.bootstrapPartitionPath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapPartitionPath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.fileIdToBootstrapFile)) {
                this.fileIdToBootstrapFile = (Map<String, HoodieFileStatus>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.fileIdToBootstrapFile);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieBootstrapPartitionMetadata other) {
            super(HoodieBootstrapPartitionMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapPartitionPath)) {
                this.bootstrapPartitionPath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapPartitionPath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.fileIdToBootstrapFile)) {
                this.fileIdToBootstrapFile = (Map<String, HoodieFileStatus>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.fileIdToBootstrapFile);
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
        
        public String getBootstrapPartitionPath() {
            return this.bootstrapPartitionPath;
        }
        
        public Builder setBootstrapPartitionPath(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.bootstrapPartitionPath = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasBootstrapPartitionPath() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearBootstrapPartitionPath() {
            this.bootstrapPartitionPath = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Map<String, HoodieFileStatus> getFileIdToBootstrapFile() {
            return this.fileIdToBootstrapFile;
        }
        
        public Builder setFileIdToBootstrapFile(final Map<String, HoodieFileStatus> value) {
            this.validate(this.fields()[3], (Object)value);
            this.fileIdToBootstrapFile = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasFileIdToBootstrapFile() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearFileIdToBootstrapFile() {
            this.fileIdToBootstrapFile = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieBootstrapPartitionMetadata build() {
            try {
                final HoodieBootstrapPartitionMetadata record = new HoodieBootstrapPartitionMetadata();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                record.bootstrapPartitionPath = (String)(this.fieldSetFlags()[1] ? this.bootstrapPartitionPath : this.defaultValue(this.fields()[1]));
                record.partitionPath = (String)(this.fieldSetFlags()[2] ? this.partitionPath : this.defaultValue(this.fields()[2]));
                record.fileIdToBootstrapFile = (Map<String, HoodieFileStatus>)(this.fieldSetFlags()[3] ? this.fileIdToBootstrapFile : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
