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
public class HoodieCleanPartitionMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 2246764558687583941L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCleanPartitionMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieCleanPartitionMetadata> DECODER;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public String policy;
    @Deprecated
    public List<String> deletePathPatterns;
    @Deprecated
    public List<String> successDeleteFiles;
    @Deprecated
    public List<String> failedDeleteFiles;
    private static final DatumWriter<HoodieCleanPartitionMetadata> WRITER$;
    private static final DatumReader<HoodieCleanPartitionMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCleanPartitionMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCleanPartitionMetadata> getDecoder() {
        return HoodieCleanPartitionMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCleanPartitionMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCleanPartitionMetadata>)new BinaryMessageDecoder((GenericData)HoodieCleanPartitionMetadata.MODEL$, HoodieCleanPartitionMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCleanPartitionMetadata.ENCODER.encode((HoodieCleanPartitionMetadata) this);
    }
    
    public static HoodieCleanPartitionMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCleanPartitionMetadata)HoodieCleanPartitionMetadata.DECODER.decode(b);
    }
    
    public HoodieCleanPartitionMetadata() {
    }
    
    public HoodieCleanPartitionMetadata(final String partitionPath, final String policy, final List<String> deletePathPatterns, final List<String> successDeleteFiles, final List<String> failedDeleteFiles) {
        this.partitionPath = partitionPath;
        this.policy = policy;
        this.deletePathPatterns = deletePathPatterns;
        this.successDeleteFiles = successDeleteFiles;
        this.failedDeleteFiles = failedDeleteFiles;
    }
    
    public Schema getSchema() {
        return HoodieCleanPartitionMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionPath;
            }
            case 1: {
                return this.policy;
            }
            case 2: {
                return this.deletePathPatterns;
            }
            case 3: {
                return this.successDeleteFiles;
            }
            case 4: {
                return this.failedDeleteFiles;
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
                this.policy = (String)value$;
                break;
            }
            case 2: {
                this.deletePathPatterns = (List<String>)value$;
                break;
            }
            case 3: {
                this.successDeleteFiles = (List<String>)value$;
                break;
            }
            case 4: {
                this.failedDeleteFiles = (List<String>)value$;
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
    
    public String getPolicy() {
        return this.policy;
    }
    
    public void setPolicy(final String value) {
        this.policy = value;
    }
    
    public List<String> getDeletePathPatterns() {
        return this.deletePathPatterns;
    }
    
    public void setDeletePathPatterns(final List<String> value) {
        this.deletePathPatterns = value;
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
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCleanPartitionMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCleanPartitionMetadata.WRITER$.write((HoodieCleanPartitionMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCleanPartitionMetadata.READER$.read((HoodieCleanPartitionMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCleanPartitionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"deletePathPatterns\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");
        HoodieCleanPartitionMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCleanPartitionMetadata.MODEL$, HoodieCleanPartitionMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCleanPartitionMetadata.MODEL$, HoodieCleanPartitionMetadata.SCHEMA$);
        WRITER$ = HoodieCleanPartitionMetadata.MODEL$.createDatumWriter(HoodieCleanPartitionMetadata.SCHEMA$);
        READER$ = HoodieCleanPartitionMetadata.MODEL$.createDatumReader(HoodieCleanPartitionMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCleanPartitionMetadata> implements RecordBuilder<HoodieCleanPartitionMetadata>
    {
        private String partitionPath;
        private String policy;
        private List<String> deletePathPatterns;
        private List<String> successDeleteFiles;
        private List<String> failedDeleteFiles;
        
        private Builder() {
            super(HoodieCleanPartitionMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.policy)) {
                this.policy = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.policy);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.deletePathPatterns)) {
                this.deletePathPatterns = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.deletePathPatterns);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.successDeleteFiles)) {
                this.successDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.successDeleteFiles);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.failedDeleteFiles)) {
                this.failedDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.failedDeleteFiles);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        private Builder(final HoodieCleanPartitionMetadata other) {
            super(HoodieCleanPartitionMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.policy)) {
                this.policy = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.policy);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.deletePathPatterns)) {
                this.deletePathPatterns = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.deletePathPatterns);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.successDeleteFiles)) {
                this.successDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.successDeleteFiles);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.failedDeleteFiles)) {
                this.failedDeleteFiles = (List<String>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.failedDeleteFiles);
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
        
        public String getPolicy() {
            return this.policy;
        }
        
        public Builder setPolicy(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.policy = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasPolicy() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearPolicy() {
            this.policy = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public List<String> getDeletePathPatterns() {
            return this.deletePathPatterns;
        }
        
        public Builder setDeletePathPatterns(final List<String> value) {
            this.validate(this.fields()[2], (Object)value);
            this.deletePathPatterns = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasDeletePathPatterns() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearDeletePathPatterns() {
            this.deletePathPatterns = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public List<String> getSuccessDeleteFiles() {
            return this.successDeleteFiles;
        }
        
        public Builder setSuccessDeleteFiles(final List<String> value) {
            this.validate(this.fields()[3], (Object)value);
            this.successDeleteFiles = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasSuccessDeleteFiles() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearSuccessDeleteFiles() {
            this.successDeleteFiles = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public List<String> getFailedDeleteFiles() {
            return this.failedDeleteFiles;
        }
        
        public Builder setFailedDeleteFiles(final List<String> value) {
            this.validate(this.fields()[4], (Object)value);
            this.failedDeleteFiles = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasFailedDeleteFiles() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearFailedDeleteFiles() {
            this.failedDeleteFiles = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieCleanPartitionMetadata build() {
            try {
                final HoodieCleanPartitionMetadata record = new HoodieCleanPartitionMetadata();
                record.partitionPath = (String)(this.fieldSetFlags()[0] ? this.partitionPath : this.defaultValue(this.fields()[0]));
                record.policy = (String)(this.fieldSetFlags()[1] ? this.policy : this.defaultValue(this.fields()[1]));
                record.deletePathPatterns = (List<String>)(this.fieldSetFlags()[2] ? this.deletePathPatterns : this.defaultValue(this.fields()[2]));
                record.successDeleteFiles = (List<String>)(this.fieldSetFlags()[3] ? this.successDeleteFiles : this.defaultValue(this.fields()[3]));
                record.failedDeleteFiles = (List<String>)(this.fieldSetFlags()[4] ? this.failedDeleteFiles : this.defaultValue(this.fields()[4]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
