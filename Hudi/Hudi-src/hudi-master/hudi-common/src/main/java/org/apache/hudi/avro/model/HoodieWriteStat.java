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
public class HoodieWriteStat extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 7119265038390862172L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieWriteStat> ENCODER;
    private static final BinaryMessageDecoder<HoodieWriteStat> DECODER;
    @Deprecated
    public String fileId;
    @Deprecated
    public String path;
    @Deprecated
    public String prevCommit;
    @Deprecated
    public Long numWrites;
    @Deprecated
    public Long numDeletes;
    @Deprecated
    public Long numUpdateWrites;
    @Deprecated
    public Long totalWriteBytes;
    @Deprecated
    public Long totalWriteErrors;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public Long totalLogRecords;
    @Deprecated
    public Long totalLogFiles;
    @Deprecated
    public Long totalUpdatedRecordsCompacted;
    @Deprecated
    public Long numInserts;
    @Deprecated
    public Long totalLogBlocks;
    @Deprecated
    public Long totalCorruptLogBlock;
    @Deprecated
    public Long totalRollbackBlocks;
    @Deprecated
    public Long fileSizeInBytes;
    private static final DatumWriter<HoodieWriteStat> WRITER$;
    private static final DatumReader<HoodieWriteStat> READER$;
    
    public static Schema getClassSchema() {
        return HoodieWriteStat.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieWriteStat> getDecoder() {
        return HoodieWriteStat.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieWriteStat> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieWriteStat>)new BinaryMessageDecoder((GenericData)HoodieWriteStat.MODEL$, HoodieWriteStat.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieWriteStat.ENCODER.encode((HoodieWriteStat) this);
    }
    
    public static HoodieWriteStat fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieWriteStat)HoodieWriteStat.DECODER.decode(b);
    }
    
    public HoodieWriteStat() {
    }
    
    public HoodieWriteStat(final String fileId, final String path, final String prevCommit, final Long numWrites, final Long numDeletes, final Long numUpdateWrites, final Long totalWriteBytes, final Long totalWriteErrors, final String partitionPath, final Long totalLogRecords, final Long totalLogFiles, final Long totalUpdatedRecordsCompacted, final Long numInserts, final Long totalLogBlocks, final Long totalCorruptLogBlock, final Long totalRollbackBlocks, final Long fileSizeInBytes) {
        this.fileId = fileId;
        this.path = path;
        this.prevCommit = prevCommit;
        this.numWrites = numWrites;
        this.numDeletes = numDeletes;
        this.numUpdateWrites = numUpdateWrites;
        this.totalWriteBytes = totalWriteBytes;
        this.totalWriteErrors = totalWriteErrors;
        this.partitionPath = partitionPath;
        this.totalLogRecords = totalLogRecords;
        this.totalLogFiles = totalLogFiles;
        this.totalUpdatedRecordsCompacted = totalUpdatedRecordsCompacted;
        this.numInserts = numInserts;
        this.totalLogBlocks = totalLogBlocks;
        this.totalCorruptLogBlock = totalCorruptLogBlock;
        this.totalRollbackBlocks = totalRollbackBlocks;
        this.fileSizeInBytes = fileSizeInBytes;
    }
    
    public Schema getSchema() {
        return HoodieWriteStat.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.fileId;
            }
            case 1: {
                return this.path;
            }
            case 2: {
                return this.prevCommit;
            }
            case 3: {
                return this.numWrites;
            }
            case 4: {
                return this.numDeletes;
            }
            case 5: {
                return this.numUpdateWrites;
            }
            case 6: {
                return this.totalWriteBytes;
            }
            case 7: {
                return this.totalWriteErrors;
            }
            case 8: {
                return this.partitionPath;
            }
            case 9: {
                return this.totalLogRecords;
            }
            case 10: {
                return this.totalLogFiles;
            }
            case 11: {
                return this.totalUpdatedRecordsCompacted;
            }
            case 12: {
                return this.numInserts;
            }
            case 13: {
                return this.totalLogBlocks;
            }
            case 14: {
                return this.totalCorruptLogBlock;
            }
            case 15: {
                return this.totalRollbackBlocks;
            }
            case 16: {
                return this.fileSizeInBytes;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.fileId = (String)value$;
                break;
            }
            case 1: {
                this.path = (String)value$;
                break;
            }
            case 2: {
                this.prevCommit = (String)value$;
                break;
            }
            case 3: {
                this.numWrites = (Long)value$;
                break;
            }
            case 4: {
                this.numDeletes = (Long)value$;
                break;
            }
            case 5: {
                this.numUpdateWrites = (Long)value$;
                break;
            }
            case 6: {
                this.totalWriteBytes = (Long)value$;
                break;
            }
            case 7: {
                this.totalWriteErrors = (Long)value$;
                break;
            }
            case 8: {
                this.partitionPath = (String)value$;
                break;
            }
            case 9: {
                this.totalLogRecords = (Long)value$;
                break;
            }
            case 10: {
                this.totalLogFiles = (Long)value$;
                break;
            }
            case 11: {
                this.totalUpdatedRecordsCompacted = (Long)value$;
                break;
            }
            case 12: {
                this.numInserts = (Long)value$;
                break;
            }
            case 13: {
                this.totalLogBlocks = (Long)value$;
                break;
            }
            case 14: {
                this.totalCorruptLogBlock = (Long)value$;
                break;
            }
            case 15: {
                this.totalRollbackBlocks = (Long)value$;
                break;
            }
            case 16: {
                this.fileSizeInBytes = (Long)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getFileId() {
        return this.fileId;
    }
    
    public void setFileId(final String value) {
        this.fileId = value;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(final String value) {
        this.path = value;
    }
    
    public String getPrevCommit() {
        return this.prevCommit;
    }
    
    public void setPrevCommit(final String value) {
        this.prevCommit = value;
    }
    
    public Long getNumWrites() {
        return this.numWrites;
    }
    
    public void setNumWrites(final Long value) {
        this.numWrites = value;
    }
    
    public Long getNumDeletes() {
        return this.numDeletes;
    }
    
    public void setNumDeletes(final Long value) {
        this.numDeletes = value;
    }
    
    public Long getNumUpdateWrites() {
        return this.numUpdateWrites;
    }
    
    public void setNumUpdateWrites(final Long value) {
        this.numUpdateWrites = value;
    }
    
    public Long getTotalWriteBytes() {
        return this.totalWriteBytes;
    }
    
    public void setTotalWriteBytes(final Long value) {
        this.totalWriteBytes = value;
    }
    
    public Long getTotalWriteErrors() {
        return this.totalWriteErrors;
    }
    
    public void setTotalWriteErrors(final Long value) {
        this.totalWriteErrors = value;
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public Long getTotalLogRecords() {
        return this.totalLogRecords;
    }
    
    public void setTotalLogRecords(final Long value) {
        this.totalLogRecords = value;
    }
    
    public Long getTotalLogFiles() {
        return this.totalLogFiles;
    }
    
    public void setTotalLogFiles(final Long value) {
        this.totalLogFiles = value;
    }
    
    public Long getTotalUpdatedRecordsCompacted() {
        return this.totalUpdatedRecordsCompacted;
    }
    
    public void setTotalUpdatedRecordsCompacted(final Long value) {
        this.totalUpdatedRecordsCompacted = value;
    }
    
    public Long getNumInserts() {
        return this.numInserts;
    }
    
    public void setNumInserts(final Long value) {
        this.numInserts = value;
    }
    
    public Long getTotalLogBlocks() {
        return this.totalLogBlocks;
    }
    
    public void setTotalLogBlocks(final Long value) {
        this.totalLogBlocks = value;
    }
    
    public Long getTotalCorruptLogBlock() {
        return this.totalCorruptLogBlock;
    }
    
    public void setTotalCorruptLogBlock(final Long value) {
        this.totalCorruptLogBlock = value;
    }
    
    public Long getTotalRollbackBlocks() {
        return this.totalRollbackBlocks;
    }
    
    public void setTotalRollbackBlocks(final Long value) {
        this.totalRollbackBlocks = value;
    }
    
    public Long getFileSizeInBytes() {
        return this.fileSizeInBytes;
    }
    
    public void setFileSizeInBytes(final Long value) {
        this.fileSizeInBytes = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieWriteStat other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieWriteStat.WRITER$.write((HoodieWriteStat) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieWriteStat.READER$.read((HoodieWriteStat) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}");
        HoodieWriteStat.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieWriteStat.MODEL$, HoodieWriteStat.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieWriteStat.MODEL$, HoodieWriteStat.SCHEMA$);
        WRITER$ = HoodieWriteStat.MODEL$.createDatumWriter(HoodieWriteStat.SCHEMA$);
        READER$ = HoodieWriteStat.MODEL$.createDatumReader(HoodieWriteStat.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieWriteStat> implements RecordBuilder<HoodieWriteStat>
    {
        private String fileId;
        private String path;
        private String prevCommit;
        private Long numWrites;
        private Long numDeletes;
        private Long numUpdateWrites;
        private Long totalWriteBytes;
        private Long totalWriteErrors;
        private String partitionPath;
        private Long totalLogRecords;
        private Long totalLogFiles;
        private Long totalUpdatedRecordsCompacted;
        private Long numInserts;
        private Long totalLogBlocks;
        private Long totalCorruptLogBlock;
        private Long totalRollbackBlocks;
        private Long fileSizeInBytes;
        
        private Builder() {
            super(HoodieWriteStat.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.fileId);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.path)) {
                this.path = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.path);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.prevCommit)) {
                this.prevCommit = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.prevCommit);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.numWrites)) {
                this.numWrites = (Long)this.data().deepCopy(this.fields()[3].schema(), (Object)other.numWrites);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.numDeletes)) {
                this.numDeletes = (Long)this.data().deepCopy(this.fields()[4].schema(), (Object)other.numDeletes);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.numUpdateWrites)) {
                this.numUpdateWrites = (Long)this.data().deepCopy(this.fields()[5].schema(), (Object)other.numUpdateWrites);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.totalWriteBytes)) {
                this.totalWriteBytes = (Long)this.data().deepCopy(this.fields()[6].schema(), (Object)other.totalWriteBytes);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.totalWriteErrors)) {
                this.totalWriteErrors = (Long)this.data().deepCopy(this.fields()[7].schema(), (Object)other.totalWriteErrors);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[8].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[8] = true;
            }
            if (isValidValue(this.fields()[9], (Object)other.totalLogRecords)) {
                this.totalLogRecords = (Long)this.data().deepCopy(this.fields()[9].schema(), (Object)other.totalLogRecords);
                this.fieldSetFlags()[9] = true;
            }
            if (isValidValue(this.fields()[10], (Object)other.totalLogFiles)) {
                this.totalLogFiles = (Long)this.data().deepCopy(this.fields()[10].schema(), (Object)other.totalLogFiles);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.totalUpdatedRecordsCompacted)) {
                this.totalUpdatedRecordsCompacted = (Long)this.data().deepCopy(this.fields()[11].schema(), (Object)other.totalUpdatedRecordsCompacted);
                this.fieldSetFlags()[11] = true;
            }
            if (isValidValue(this.fields()[12], (Object)other.numInserts)) {
                this.numInserts = (Long)this.data().deepCopy(this.fields()[12].schema(), (Object)other.numInserts);
                this.fieldSetFlags()[12] = true;
            }
            if (isValidValue(this.fields()[13], (Object)other.totalLogBlocks)) {
                this.totalLogBlocks = (Long)this.data().deepCopy(this.fields()[13].schema(), (Object)other.totalLogBlocks);
                this.fieldSetFlags()[13] = true;
            }
            if (isValidValue(this.fields()[14], (Object)other.totalCorruptLogBlock)) {
                this.totalCorruptLogBlock = (Long)this.data().deepCopy(this.fields()[14].schema(), (Object)other.totalCorruptLogBlock);
                this.fieldSetFlags()[14] = true;
            }
            if (isValidValue(this.fields()[15], (Object)other.totalRollbackBlocks)) {
                this.totalRollbackBlocks = (Long)this.data().deepCopy(this.fields()[15].schema(), (Object)other.totalRollbackBlocks);
                this.fieldSetFlags()[15] = true;
            }
            if (isValidValue(this.fields()[16], (Object)other.fileSizeInBytes)) {
                this.fileSizeInBytes = (Long)this.data().deepCopy(this.fields()[16].schema(), (Object)other.fileSizeInBytes);
                this.fieldSetFlags()[16] = true;
            }
        }
        
        private Builder(final HoodieWriteStat other) {
            super(HoodieWriteStat.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.fileId);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.path)) {
                this.path = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.path);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.prevCommit)) {
                this.prevCommit = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.prevCommit);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.numWrites)) {
                this.numWrites = (Long)this.data().deepCopy(this.fields()[3].schema(), (Object)other.numWrites);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.numDeletes)) {
                this.numDeletes = (Long)this.data().deepCopy(this.fields()[4].schema(), (Object)other.numDeletes);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.numUpdateWrites)) {
                this.numUpdateWrites = (Long)this.data().deepCopy(this.fields()[5].schema(), (Object)other.numUpdateWrites);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.totalWriteBytes)) {
                this.totalWriteBytes = (Long)this.data().deepCopy(this.fields()[6].schema(), (Object)other.totalWriteBytes);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.totalWriteErrors)) {
                this.totalWriteErrors = (Long)this.data().deepCopy(this.fields()[7].schema(), (Object)other.totalWriteErrors);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[8].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[8] = true;
            }
            if (isValidValue(this.fields()[9], (Object)other.totalLogRecords)) {
                this.totalLogRecords = (Long)this.data().deepCopy(this.fields()[9].schema(), (Object)other.totalLogRecords);
                this.fieldSetFlags()[9] = true;
            }
            if (isValidValue(this.fields()[10], (Object)other.totalLogFiles)) {
                this.totalLogFiles = (Long)this.data().deepCopy(this.fields()[10].schema(), (Object)other.totalLogFiles);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.totalUpdatedRecordsCompacted)) {
                this.totalUpdatedRecordsCompacted = (Long)this.data().deepCopy(this.fields()[11].schema(), (Object)other.totalUpdatedRecordsCompacted);
                this.fieldSetFlags()[11] = true;
            }
            if (isValidValue(this.fields()[12], (Object)other.numInserts)) {
                this.numInserts = (Long)this.data().deepCopy(this.fields()[12].schema(), (Object)other.numInserts);
                this.fieldSetFlags()[12] = true;
            }
            if (isValidValue(this.fields()[13], (Object)other.totalLogBlocks)) {
                this.totalLogBlocks = (Long)this.data().deepCopy(this.fields()[13].schema(), (Object)other.totalLogBlocks);
                this.fieldSetFlags()[13] = true;
            }
            if (isValidValue(this.fields()[14], (Object)other.totalCorruptLogBlock)) {
                this.totalCorruptLogBlock = (Long)this.data().deepCopy(this.fields()[14].schema(), (Object)other.totalCorruptLogBlock);
                this.fieldSetFlags()[14] = true;
            }
            if (isValidValue(this.fields()[15], (Object)other.totalRollbackBlocks)) {
                this.totalRollbackBlocks = (Long)this.data().deepCopy(this.fields()[15].schema(), (Object)other.totalRollbackBlocks);
                this.fieldSetFlags()[15] = true;
            }
            if (isValidValue(this.fields()[16], (Object)other.fileSizeInBytes)) {
                this.fileSizeInBytes = (Long)this.data().deepCopy(this.fields()[16].schema(), (Object)other.fileSizeInBytes);
                this.fieldSetFlags()[16] = true;
            }
        }
        
        public String getFileId() {
            return this.fileId;
        }
        
        public Builder setFileId(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.fileId = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasFileId() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearFileId() {
            this.fileId = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public String getPath() {
            return this.path;
        }
        
        public Builder setPath(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.path = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasPath() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearPath() {
            this.path = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getPrevCommit() {
            return this.prevCommit;
        }
        
        public Builder setPrevCommit(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.prevCommit = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasPrevCommit() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearPrevCommit() {
            this.prevCommit = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Long getNumWrites() {
            return this.numWrites;
        }
        
        public Builder setNumWrites(final Long value) {
            this.validate(this.fields()[3], (Object)value);
            this.numWrites = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasNumWrites() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearNumWrites() {
            this.numWrites = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Long getNumDeletes() {
            return this.numDeletes;
        }
        
        public Builder setNumDeletes(final Long value) {
            this.validate(this.fields()[4], (Object)value);
            this.numDeletes = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasNumDeletes() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearNumDeletes() {
            this.numDeletes = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public Long getNumUpdateWrites() {
            return this.numUpdateWrites;
        }
        
        public Builder setNumUpdateWrites(final Long value) {
            this.validate(this.fields()[5], (Object)value);
            this.numUpdateWrites = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasNumUpdateWrites() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearNumUpdateWrites() {
            this.numUpdateWrites = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public Long getTotalWriteBytes() {
            return this.totalWriteBytes;
        }
        
        public Builder setTotalWriteBytes(final Long value) {
            this.validate(this.fields()[6], (Object)value);
            this.totalWriteBytes = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasTotalWriteBytes() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearTotalWriteBytes() {
            this.totalWriteBytes = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public Long getTotalWriteErrors() {
            return this.totalWriteErrors;
        }
        
        public Builder setTotalWriteErrors(final Long value) {
            this.validate(this.fields()[7], (Object)value);
            this.totalWriteErrors = value;
            this.fieldSetFlags()[7] = true;
            return this;
        }
        
        public boolean hasTotalWriteErrors() {
            return this.fieldSetFlags()[7];
        }
        
        public Builder clearTotalWriteErrors() {
            this.totalWriteErrors = null;
            this.fieldSetFlags()[7] = false;
            return this;
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[8], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[8] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[8];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[8] = false;
            return this;
        }
        
        public Long getTotalLogRecords() {
            return this.totalLogRecords;
        }
        
        public Builder setTotalLogRecords(final Long value) {
            this.validate(this.fields()[9], (Object)value);
            this.totalLogRecords = value;
            this.fieldSetFlags()[9] = true;
            return this;
        }
        
        public boolean hasTotalLogRecords() {
            return this.fieldSetFlags()[9];
        }
        
        public Builder clearTotalLogRecords() {
            this.totalLogRecords = null;
            this.fieldSetFlags()[9] = false;
            return this;
        }
        
        public Long getTotalLogFiles() {
            return this.totalLogFiles;
        }
        
        public Builder setTotalLogFiles(final Long value) {
            this.validate(this.fields()[10], (Object)value);
            this.totalLogFiles = value;
            this.fieldSetFlags()[10] = true;
            return this;
        }
        
        public boolean hasTotalLogFiles() {
            return this.fieldSetFlags()[10];
        }
        
        public Builder clearTotalLogFiles() {
            this.totalLogFiles = null;
            this.fieldSetFlags()[10] = false;
            return this;
        }
        
        public Long getTotalUpdatedRecordsCompacted() {
            return this.totalUpdatedRecordsCompacted;
        }
        
        public Builder setTotalUpdatedRecordsCompacted(final Long value) {
            this.validate(this.fields()[11], (Object)value);
            this.totalUpdatedRecordsCompacted = value;
            this.fieldSetFlags()[11] = true;
            return this;
        }
        
        public boolean hasTotalUpdatedRecordsCompacted() {
            return this.fieldSetFlags()[11];
        }
        
        public Builder clearTotalUpdatedRecordsCompacted() {
            this.totalUpdatedRecordsCompacted = null;
            this.fieldSetFlags()[11] = false;
            return this;
        }
        
        public Long getNumInserts() {
            return this.numInserts;
        }
        
        public Builder setNumInserts(final Long value) {
            this.validate(this.fields()[12], (Object)value);
            this.numInserts = value;
            this.fieldSetFlags()[12] = true;
            return this;
        }
        
        public boolean hasNumInserts() {
            return this.fieldSetFlags()[12];
        }
        
        public Builder clearNumInserts() {
            this.numInserts = null;
            this.fieldSetFlags()[12] = false;
            return this;
        }
        
        public Long getTotalLogBlocks() {
            return this.totalLogBlocks;
        }
        
        public Builder setTotalLogBlocks(final Long value) {
            this.validate(this.fields()[13], (Object)value);
            this.totalLogBlocks = value;
            this.fieldSetFlags()[13] = true;
            return this;
        }
        
        public boolean hasTotalLogBlocks() {
            return this.fieldSetFlags()[13];
        }
        
        public Builder clearTotalLogBlocks() {
            this.totalLogBlocks = null;
            this.fieldSetFlags()[13] = false;
            return this;
        }
        
        public Long getTotalCorruptLogBlock() {
            return this.totalCorruptLogBlock;
        }
        
        public Builder setTotalCorruptLogBlock(final Long value) {
            this.validate(this.fields()[14], (Object)value);
            this.totalCorruptLogBlock = value;
            this.fieldSetFlags()[14] = true;
            return this;
        }
        
        public boolean hasTotalCorruptLogBlock() {
            return this.fieldSetFlags()[14];
        }
        
        public Builder clearTotalCorruptLogBlock() {
            this.totalCorruptLogBlock = null;
            this.fieldSetFlags()[14] = false;
            return this;
        }
        
        public Long getTotalRollbackBlocks() {
            return this.totalRollbackBlocks;
        }
        
        public Builder setTotalRollbackBlocks(final Long value) {
            this.validate(this.fields()[15], (Object)value);
            this.totalRollbackBlocks = value;
            this.fieldSetFlags()[15] = true;
            return this;
        }
        
        public boolean hasTotalRollbackBlocks() {
            return this.fieldSetFlags()[15];
        }
        
        public Builder clearTotalRollbackBlocks() {
            this.totalRollbackBlocks = null;
            this.fieldSetFlags()[15] = false;
            return this;
        }
        
        public Long getFileSizeInBytes() {
            return this.fileSizeInBytes;
        }
        
        public Builder setFileSizeInBytes(final Long value) {
            this.validate(this.fields()[16], (Object)value);
            this.fileSizeInBytes = value;
            this.fieldSetFlags()[16] = true;
            return this;
        }
        
        public boolean hasFileSizeInBytes() {
            return this.fieldSetFlags()[16];
        }
        
        public Builder clearFileSizeInBytes() {
            this.fileSizeInBytes = null;
            this.fieldSetFlags()[16] = false;
            return this;
        }
        
        public HoodieWriteStat build() {
            try {
                final HoodieWriteStat record = new HoodieWriteStat();
                record.fileId = (String)(this.fieldSetFlags()[0] ? this.fileId : this.defaultValue(this.fields()[0]));
                record.path = (String)(this.fieldSetFlags()[1] ? this.path : this.defaultValue(this.fields()[1]));
                record.prevCommit = (String)(this.fieldSetFlags()[2] ? this.prevCommit : this.defaultValue(this.fields()[2]));
                record.numWrites = (Long)(this.fieldSetFlags()[3] ? this.numWrites : this.defaultValue(this.fields()[3]));
                record.numDeletes = (Long)(this.fieldSetFlags()[4] ? this.numDeletes : this.defaultValue(this.fields()[4]));
                record.numUpdateWrites = (Long)(this.fieldSetFlags()[5] ? this.numUpdateWrites : this.defaultValue(this.fields()[5]));
                record.totalWriteBytes = (Long)(this.fieldSetFlags()[6] ? this.totalWriteBytes : this.defaultValue(this.fields()[6]));
                record.totalWriteErrors = (Long)(this.fieldSetFlags()[7] ? this.totalWriteErrors : this.defaultValue(this.fields()[7]));
                record.partitionPath = (String)(this.fieldSetFlags()[8] ? this.partitionPath : this.defaultValue(this.fields()[8]));
                record.totalLogRecords = (Long)(this.fieldSetFlags()[9] ? this.totalLogRecords : this.defaultValue(this.fields()[9]));
                record.totalLogFiles = (Long)(this.fieldSetFlags()[10] ? this.totalLogFiles : this.defaultValue(this.fields()[10]));
                record.totalUpdatedRecordsCompacted = (Long)(this.fieldSetFlags()[11] ? this.totalUpdatedRecordsCompacted : this.defaultValue(this.fields()[11]));
                record.numInserts = (Long)(this.fieldSetFlags()[12] ? this.numInserts : this.defaultValue(this.fields()[12]));
                record.totalLogBlocks = (Long)(this.fieldSetFlags()[13] ? this.totalLogBlocks : this.defaultValue(this.fields()[13]));
                record.totalCorruptLogBlock = (Long)(this.fieldSetFlags()[14] ? this.totalCorruptLogBlock : this.defaultValue(this.fields()[14]));
                record.totalRollbackBlocks = (Long)(this.fieldSetFlags()[15] ? this.totalRollbackBlocks : this.defaultValue(this.fields()[15]));
                record.fileSizeInBytes = (Long)(this.fieldSetFlags()[16] ? this.fileSizeInBytes : this.defaultValue(this.fields()[16]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
