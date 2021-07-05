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
public class HoodieCompactionWriteStat extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 8927392485045241479L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCompactionWriteStat> ENCODER;
    private static final BinaryMessageDecoder<HoodieCompactionWriteStat> DECODER;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public Long totalLogRecords;
    @Deprecated
    public Long totalLogFiles;
    @Deprecated
    public Long totalUpdatedRecordsCompacted;
    @Deprecated
    public HoodieWriteStat hoodieWriteStat;
    private static final DatumWriter<HoodieCompactionWriteStat> WRITER$;
    private static final DatumReader<HoodieCompactionWriteStat> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCompactionWriteStat.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionWriteStat> getDecoder() {
        return HoodieCompactionWriteStat.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionWriteStat> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCompactionWriteStat>)new BinaryMessageDecoder((GenericData)HoodieCompactionWriteStat.MODEL$, HoodieCompactionWriteStat.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCompactionWriteStat.ENCODER.encode((HoodieCompactionWriteStat) this);
    }
    
    public static HoodieCompactionWriteStat fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCompactionWriteStat)HoodieCompactionWriteStat.DECODER.decode(b);
    }
    
    public HoodieCompactionWriteStat() {
    }
    
    public HoodieCompactionWriteStat(final String partitionPath, final Long totalLogRecords, final Long totalLogFiles, final Long totalUpdatedRecordsCompacted, final HoodieWriteStat hoodieWriteStat) {
        this.partitionPath = partitionPath;
        this.totalLogRecords = totalLogRecords;
        this.totalLogFiles = totalLogFiles;
        this.totalUpdatedRecordsCompacted = totalUpdatedRecordsCompacted;
        this.hoodieWriteStat = hoodieWriteStat;
    }
    
    public Schema getSchema() {
        return HoodieCompactionWriteStat.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionPath;
            }
            case 1: {
                return this.totalLogRecords;
            }
            case 2: {
                return this.totalLogFiles;
            }
            case 3: {
                return this.totalUpdatedRecordsCompacted;
            }
            case 4: {
                return this.hoodieWriteStat;
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
                this.totalLogRecords = (Long)value$;
                break;
            }
            case 2: {
                this.totalLogFiles = (Long)value$;
                break;
            }
            case 3: {
                this.totalUpdatedRecordsCompacted = (Long)value$;
                break;
            }
            case 4: {
                this.hoodieWriteStat = (HoodieWriteStat)value$;
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
    
    public HoodieWriteStat getHoodieWriteStat() {
        return this.hoodieWriteStat;
    }
    
    public void setHoodieWriteStat(final HoodieWriteStat value) {
        this.hoodieWriteStat = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCompactionWriteStat other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCompactionWriteStat.WRITER$.write((HoodieCompactionWriteStat) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCompactionWriteStat.READER$.read((HoodieCompactionWriteStat) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionWriteStat\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hoodieWriteStat\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}],\"default\":null}]}");
        HoodieCompactionWriteStat.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCompactionWriteStat.MODEL$, HoodieCompactionWriteStat.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCompactionWriteStat.MODEL$, HoodieCompactionWriteStat.SCHEMA$);
        WRITER$ = HoodieCompactionWriteStat.MODEL$.createDatumWriter(HoodieCompactionWriteStat.SCHEMA$);
        READER$ = HoodieCompactionWriteStat.MODEL$.createDatumReader(HoodieCompactionWriteStat.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCompactionWriteStat> implements RecordBuilder<HoodieCompactionWriteStat>
    {
        private String partitionPath;
        private Long totalLogRecords;
        private Long totalLogFiles;
        private Long totalUpdatedRecordsCompacted;
        private HoodieWriteStat hoodieWriteStat;
        private HoodieWriteStat.Builder hoodieWriteStatBuilder;
        
        private Builder() {
            super(HoodieCompactionWriteStat.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.totalLogRecords)) {
                this.totalLogRecords = (Long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.totalLogRecords);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.totalLogFiles)) {
                this.totalLogFiles = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.totalLogFiles);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.totalUpdatedRecordsCompacted)) {
                this.totalUpdatedRecordsCompacted = (Long)this.data().deepCopy(this.fields()[3].schema(), (Object)other.totalUpdatedRecordsCompacted);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.hoodieWriteStat)) {
                this.hoodieWriteStat = (HoodieWriteStat)this.data().deepCopy(this.fields()[4].schema(), (Object)other.hoodieWriteStat);
                this.fieldSetFlags()[4] = true;
            }
            if (other.hasHoodieWriteStatBuilder()) {
                this.hoodieWriteStatBuilder = HoodieWriteStat.newBuilder(other.getHoodieWriteStatBuilder());
            }
        }
        
        private Builder(final HoodieCompactionWriteStat other) {
            super(HoodieCompactionWriteStat.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.totalLogRecords)) {
                this.totalLogRecords = (Long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.totalLogRecords);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.totalLogFiles)) {
                this.totalLogFiles = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.totalLogFiles);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.totalUpdatedRecordsCompacted)) {
                this.totalUpdatedRecordsCompacted = (Long)this.data().deepCopy(this.fields()[3].schema(), (Object)other.totalUpdatedRecordsCompacted);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.hoodieWriteStat)) {
                this.hoodieWriteStat = (HoodieWriteStat)this.data().deepCopy(this.fields()[4].schema(), (Object)other.hoodieWriteStat);
                this.fieldSetFlags()[4] = true;
            }
            this.hoodieWriteStatBuilder = null;
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
        
        public Long getTotalLogRecords() {
            return this.totalLogRecords;
        }
        
        public Builder setTotalLogRecords(final Long value) {
            this.validate(this.fields()[1], (Object)value);
            this.totalLogRecords = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasTotalLogRecords() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearTotalLogRecords() {
            this.totalLogRecords = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Long getTotalLogFiles() {
            return this.totalLogFiles;
        }
        
        public Builder setTotalLogFiles(final Long value) {
            this.validate(this.fields()[2], (Object)value);
            this.totalLogFiles = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasTotalLogFiles() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearTotalLogFiles() {
            this.totalLogFiles = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Long getTotalUpdatedRecordsCompacted() {
            return this.totalUpdatedRecordsCompacted;
        }
        
        public Builder setTotalUpdatedRecordsCompacted(final Long value) {
            this.validate(this.fields()[3], (Object)value);
            this.totalUpdatedRecordsCompacted = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasTotalUpdatedRecordsCompacted() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearTotalUpdatedRecordsCompacted() {
            this.totalUpdatedRecordsCompacted = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieWriteStat getHoodieWriteStat() {
            return this.hoodieWriteStat;
        }
        
        public Builder setHoodieWriteStat(final HoodieWriteStat value) {
            this.validate(this.fields()[4], (Object)value);
            this.hoodieWriteStatBuilder = null;
            this.hoodieWriteStat = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasHoodieWriteStat() {
            return this.fieldSetFlags()[4];
        }
        
        public HoodieWriteStat.Builder getHoodieWriteStatBuilder() {
            if (this.hoodieWriteStatBuilder == null) {
                if (this.hasHoodieWriteStat()) {
                    this.setHoodieWriteStatBuilder(HoodieWriteStat.newBuilder(this.hoodieWriteStat));
                }
                else {
                    this.setHoodieWriteStatBuilder(HoodieWriteStat.newBuilder());
                }
            }
            return this.hoodieWriteStatBuilder;
        }
        
        public Builder setHoodieWriteStatBuilder(final HoodieWriteStat.Builder value) {
            this.clearHoodieWriteStat();
            this.hoodieWriteStatBuilder = value;
            return this;
        }
        
        public boolean hasHoodieWriteStatBuilder() {
            return this.hoodieWriteStatBuilder != null;
        }
        
        public Builder clearHoodieWriteStat() {
            this.hoodieWriteStat = null;
            this.hoodieWriteStatBuilder = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieCompactionWriteStat build() {
            try {
                final HoodieCompactionWriteStat record = new HoodieCompactionWriteStat();
                record.partitionPath = (String)(this.fieldSetFlags()[0] ? this.partitionPath : this.defaultValue(this.fields()[0]));
                record.totalLogRecords = (Long)(this.fieldSetFlags()[1] ? this.totalLogRecords : this.defaultValue(this.fields()[1]));
                record.totalLogFiles = (Long)(this.fieldSetFlags()[2] ? this.totalLogFiles : this.defaultValue(this.fields()[2]));
                record.totalUpdatedRecordsCompacted = (Long)(this.fieldSetFlags()[3] ? this.totalUpdatedRecordsCompacted : this.defaultValue(this.fields()[3]));
                if (this.hoodieWriteStatBuilder != null) {
                    record.hoodieWriteStat = this.hoodieWriteStatBuilder.build();
                }
                else {
                    record.hoodieWriteStat = (HoodieWriteStat)(this.fieldSetFlags()[4] ? this.hoodieWriteStat : this.defaultValue(this.fields()[4]));
                }
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
