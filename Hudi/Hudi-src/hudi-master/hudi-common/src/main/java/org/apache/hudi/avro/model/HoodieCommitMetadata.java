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
import java.util.Map;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieCommitMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -621377441954715238L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCommitMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieCommitMetadata> DECODER;
    @Deprecated
    public Map<String, List<HoodieWriteStat>> partitionToWriteStats;
    @Deprecated
    public Map<String, String> extraMetadata;
    @Deprecated
    public Integer version;
    @Deprecated
    public String operationType;
    private static final DatumWriter<HoodieCommitMetadata> WRITER$;
    private static final DatumReader<HoodieCommitMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCommitMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCommitMetadata> getDecoder() {
        return HoodieCommitMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCommitMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCommitMetadata>)new BinaryMessageDecoder((GenericData)HoodieCommitMetadata.MODEL$, HoodieCommitMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCommitMetadata.ENCODER.encode((HoodieCommitMetadata) this);
    }
    
    public static HoodieCommitMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCommitMetadata)HoodieCommitMetadata.DECODER.decode(b);
    }
    
    public HoodieCommitMetadata() {
    }
    
    public HoodieCommitMetadata(final Map<String, List<HoodieWriteStat>> partitionToWriteStats, final Map<String, String> extraMetadata, final Integer version, final String operationType) {
        this.partitionToWriteStats = partitionToWriteStats;
        this.extraMetadata = extraMetadata;
        this.version = version;
        this.operationType = operationType;
    }
    
    public Schema getSchema() {
        return HoodieCommitMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionToWriteStats;
            }
            case 1: {
                return this.extraMetadata;
            }
            case 2: {
                return this.version;
            }
            case 3: {
                return this.operationType;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.partitionToWriteStats = (Map<String, List<HoodieWriteStat>>)value$;
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
            case 3: {
                this.operationType = (String)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public Map<String, List<HoodieWriteStat>> getPartitionToWriteStats() {
        return this.partitionToWriteStats;
    }
    
    public void setPartitionToWriteStats(final Map<String, List<HoodieWriteStat>> value) {
        this.partitionToWriteStats = value;
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
    
    public String getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(final String value) {
        this.operationType = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCommitMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCommitMetadata.WRITER$.write((HoodieCommitMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCommitMetadata.READER$.read((HoodieCommitMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCommitMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionToWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\",\"default\":null}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodieCommitMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCommitMetadata.MODEL$, HoodieCommitMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCommitMetadata.MODEL$, HoodieCommitMetadata.SCHEMA$);
        WRITER$ = HoodieCommitMetadata.MODEL$.createDatumWriter(HoodieCommitMetadata.SCHEMA$);
        READER$ = HoodieCommitMetadata.MODEL$.createDatumReader(HoodieCommitMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCommitMetadata> implements RecordBuilder<HoodieCommitMetadata>
    {
        private Map<String, List<HoodieWriteStat>> partitionToWriteStats;
        private Map<String, String> extraMetadata;
        private Integer version;
        private String operationType;
        
        private Builder() {
            super(HoodieCommitMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionToWriteStats)) {
                this.partitionToWriteStats = (Map<String, List<HoodieWriteStat>>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionToWriteStats);
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
            if (isValidValue(this.fields()[3], (Object)other.operationType)) {
                this.operationType = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.operationType);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieCommitMetadata other) {
            super(HoodieCommitMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionToWriteStats)) {
                this.partitionToWriteStats = (Map<String, List<HoodieWriteStat>>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionToWriteStats);
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
            if (isValidValue(this.fields()[3], (Object)other.operationType)) {
                this.operationType = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.operationType);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public Map<String, List<HoodieWriteStat>> getPartitionToWriteStats() {
            return this.partitionToWriteStats;
        }
        
        public Builder setPartitionToWriteStats(final Map<String, List<HoodieWriteStat>> value) {
            this.validate(this.fields()[0], (Object)value);
            this.partitionToWriteStats = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasPartitionToWriteStats() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearPartitionToWriteStats() {
            this.partitionToWriteStats = null;
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
        
        public String getOperationType() {
            return this.operationType;
        }
        
        public Builder setOperationType(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.operationType = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasOperationType() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearOperationType() {
            this.operationType = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieCommitMetadata build() {
            try {
                final HoodieCommitMetadata record = new HoodieCommitMetadata();
                record.partitionToWriteStats = (Map<String, List<HoodieWriteStat>>)(this.fieldSetFlags()[0] ? this.partitionToWriteStats : this.defaultValue(this.fields()[0]));
                record.extraMetadata = (Map<String, String>)(this.fieldSetFlags()[1] ? this.extraMetadata : this.defaultValue(this.fields()[1]));
                record.version = (Integer)(this.fieldSetFlags()[2] ? this.version : this.defaultValue(this.fields()[2]));
                record.operationType = (String)(this.fieldSetFlags()[3] ? this.operationType : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
