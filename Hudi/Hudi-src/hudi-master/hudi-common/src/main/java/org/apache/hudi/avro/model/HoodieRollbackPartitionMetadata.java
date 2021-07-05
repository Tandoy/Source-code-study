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
public class HoodieRollbackPartitionMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -1406815912854746168L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieRollbackPartitionMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieRollbackPartitionMetadata> DECODER;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public List<String> successDeleteFiles;
    @Deprecated
    public List<String> failedDeleteFiles;
    @Deprecated
    public Map<String, Long> rollbackLogFiles;
    @Deprecated
    public Map<String, Long> writtenLogFiles;
    private static final DatumWriter<HoodieRollbackPartitionMetadata> WRITER$;
    private static final DatumReader<HoodieRollbackPartitionMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieRollbackPartitionMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieRollbackPartitionMetadata> getDecoder() {
        return HoodieRollbackPartitionMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieRollbackPartitionMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieRollbackPartitionMetadata>)new BinaryMessageDecoder((GenericData)HoodieRollbackPartitionMetadata.MODEL$, HoodieRollbackPartitionMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieRollbackPartitionMetadata.ENCODER.encode((HoodieRollbackPartitionMetadata) this);
    }
    
    public static HoodieRollbackPartitionMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieRollbackPartitionMetadata)HoodieRollbackPartitionMetadata.DECODER.decode(b);
    }
    
    public HoodieRollbackPartitionMetadata() {
    }
    
    public HoodieRollbackPartitionMetadata(final String partitionPath, final List<String> successDeleteFiles, final List<String> failedDeleteFiles, final Map<String, Long> rollbackLogFiles, final Map<String, Long> writtenLogFiles) {
        this.partitionPath = partitionPath;
        this.successDeleteFiles = successDeleteFiles;
        this.failedDeleteFiles = failedDeleteFiles;
        this.rollbackLogFiles = rollbackLogFiles;
        this.writtenLogFiles = writtenLogFiles;
    }
    
    public Schema getSchema() {
        return HoodieRollbackPartitionMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionPath;
            }
            case 1: {
                return this.successDeleteFiles;
            }
            case 2: {
                return this.failedDeleteFiles;
            }
            case 3: {
                return this.rollbackLogFiles;
            }
            case 4: {
                return this.writtenLogFiles;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.partitionPath = (String)value$;
                break;
            }
            case 1: {
                this.successDeleteFiles = (List<String>)value$;
                break;
            }
            case 2: {
                this.failedDeleteFiles = (List<String>)value$;
                break;
            }
            case 3: {
                this.rollbackLogFiles = (Map<String, Long>)value$;
                break;
            }
            case 4: {
                this.writtenLogFiles = (Map<String, Long>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public List<String> getSuccessDeleteFiles() {
        return this.successDeleteFiles;
    }
    
    public void setSuccessDeleteFiles(final List<String> value) {
        this.successDeleteFiles = value;
    }
    
    public List<String> getFailedDeleteFiles() {
        return this.failedDeleteFiles;
    }
    
    public void setFailedDeleteFiles(final List<String> value) {
        this.failedDeleteFiles = value;
    }
    
    public Map<String, Long> getRollbackLogFiles() {
        return this.rollbackLogFiles;
    }
    
    public void setRollbackLogFiles(final Map<String, Long> value) {
        this.rollbackLogFiles = value;
    }
    
    public Map<String, Long> getWrittenLogFiles() {
        return this.writtenLogFiles;
    }
    
    public void setWrittenLogFiles(final Map<String, Long> value) {
        this.writtenLogFiles = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieRollbackPartitionMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieRollbackPartitionMetadata.WRITER$.write((HoodieRollbackPartitionMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieRollbackPartitionMetadata.READER$.read((HoodieRollbackPartitionMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieRollbackPartitionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"rollbackLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"writtenLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodieRollbackPartitionMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieRollbackPartitionMetadata.MODEL$, HoodieRollbackPartitionMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieRollbackPartitionMetadata.MODEL$, HoodieRollbackPartitionMetadata.SCHEMA$);
        WRITER$ = HoodieRollbackPartitionMetadata.MODEL$.createDatumWriter(HoodieRollbackPartitionMetadata.SCHEMA$);
        READER$ = HoodieRollbackPartitionMetadata.MODEL$.createDatumReader(HoodieRollbackPartitionMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieRollbackPartitionMetadata> implements RecordBuilder<HoodieRollbackPartitionMetadata>
    {
        private String partitionPath;
        private List<String> successDeleteFiles;
        private List<String> failedDeleteFiles;
        private Map<String, Long> rollbackLogFiles;
        private Map<String, Long> writtenLogFiles;
        
        private Builder() {
            super(HoodieRollbackPartitionMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.successDeleteFiles)) {
                this.successDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.successDeleteFiles);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.failedDeleteFiles)) {
                this.failedDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.failedDeleteFiles);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.rollbackLogFiles)) {
                this.rollbackLogFiles = (Map<String, Long>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.rollbackLogFiles);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.writtenLogFiles)) {
                this.writtenLogFiles = (Map<String, Long>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.writtenLogFiles);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        private Builder(final HoodieRollbackPartitionMetadata other) {
            super(HoodieRollbackPartitionMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.successDeleteFiles)) {
                this.successDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.successDeleteFiles);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.failedDeleteFiles)) {
                this.failedDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.failedDeleteFiles);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.rollbackLogFiles)) {
                this.rollbackLogFiles = (Map<String, Long>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.rollbackLogFiles);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.writtenLogFiles)) {
                this.writtenLogFiles = (Map<String, Long>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.writtenLogFiles);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public List<String> getSuccessDeleteFiles() {
            return this.successDeleteFiles;
        }
        
        public Builder setSuccessDeleteFiles(final List<String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.successDeleteFiles = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasSuccessDeleteFiles() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearSuccessDeleteFiles() {
            this.successDeleteFiles = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public List<String> getFailedDeleteFiles() {
            return this.failedDeleteFiles;
        }
        
        public Builder setFailedDeleteFiles(final List<String> value) {
            this.validate(this.fields()[2], (Object)value);
            this.failedDeleteFiles = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasFailedDeleteFiles() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearFailedDeleteFiles() {
            this.failedDeleteFiles = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Map<String, Long> getRollbackLogFiles() {
            return this.rollbackLogFiles;
        }
        
        public Builder setRollbackLogFiles(final Map<String, Long> value) {
            this.validate(this.fields()[3], (Object)value);
            this.rollbackLogFiles = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasRollbackLogFiles() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearRollbackLogFiles() {
            this.rollbackLogFiles = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Map<String, Long> getWrittenLogFiles() {
            return this.writtenLogFiles;
        }
        
        public Builder setWrittenLogFiles(final Map<String, Long> value) {
            this.validate(this.fields()[4], (Object)value);
            this.writtenLogFiles = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasWrittenLogFiles() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearWrittenLogFiles() {
            this.writtenLogFiles = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieRollbackPartitionMetadata build() {
            try {
                final HoodieRollbackPartitionMetadata record = new HoodieRollbackPartitionMetadata();
                record.partitionPath = (String)(this.fieldSetFlags()[0] ? this.partitionPath : this.defaultValue(this.fields()[0]));
                record.successDeleteFiles = (List<String>)(this.fieldSetFlags()[1] ? this.successDeleteFiles : this.defaultValue(this.fields()[1]));
                record.failedDeleteFiles = (List<String>)(this.fieldSetFlags()[2] ? this.failedDeleteFiles : this.defaultValue(this.fields()[2]));
                record.rollbackLogFiles = (Map<String, Long>)(this.fieldSetFlags()[3] ? this.rollbackLogFiles : this.defaultValue(this.fields()[3]));
                record.writtenLogFiles = (Map<String, Long>)(this.fieldSetFlags()[4] ? this.writtenLogFiles : this.defaultValue(this.fields()[4]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
