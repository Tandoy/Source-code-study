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
public class HoodieCompactionMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 5395193606116331778L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCompactionMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieCompactionMetadata> DECODER;
    @Deprecated
    public Map<String, List<HoodieCompactionWriteStat>> partitionToCompactionWriteStats;
    private static final DatumWriter<HoodieCompactionMetadata> WRITER$;
    private static final DatumReader<HoodieCompactionMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCompactionMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionMetadata> getDecoder() {
        return HoodieCompactionMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCompactionMetadata>)new BinaryMessageDecoder((GenericData)HoodieCompactionMetadata.MODEL$, HoodieCompactionMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCompactionMetadata.ENCODER.encode((HoodieCompactionMetadata) this);
    }
    
    public static HoodieCompactionMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCompactionMetadata)HoodieCompactionMetadata.DECODER.decode(b);
    }
    
    public HoodieCompactionMetadata() {
    }
    
    public HoodieCompactionMetadata(final Map<String, List<HoodieCompactionWriteStat>> partitionToCompactionWriteStats) {
        this.partitionToCompactionWriteStats = partitionToCompactionWriteStats;
    }
    
    public Schema getSchema() {
        return HoodieCompactionMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionToCompactionWriteStats;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.partitionToCompactionWriteStats = (Map<String, List<HoodieCompactionWriteStat>>)value$;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public Map<String, List<HoodieCompactionWriteStat>> getPartitionToCompactionWriteStats() {
        return this.partitionToCompactionWriteStats;
    }
    
    public void setPartitionToCompactionWriteStats(final Map<String, List<HoodieCompactionWriteStat>> value) {
        this.partitionToCompactionWriteStats = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCompactionMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCompactionMetadata.WRITER$.write((HoodieCompactionMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCompactionMetadata.READER$.read((HoodieCompactionMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionToCompactionWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCompactionWriteStat\",\"fields\":[{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hoodieWriteStat\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}],\"default\":null}]}},\"avro.java.string\":\"String\"}]}]}");
        HoodieCompactionMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCompactionMetadata.MODEL$, HoodieCompactionMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCompactionMetadata.MODEL$, HoodieCompactionMetadata.SCHEMA$);
        WRITER$ = HoodieCompactionMetadata.MODEL$.createDatumWriter(HoodieCompactionMetadata.SCHEMA$);
        READER$ = HoodieCompactionMetadata.MODEL$.createDatumReader(HoodieCompactionMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCompactionMetadata> implements RecordBuilder<HoodieCompactionMetadata>
    {
        private Map<String, List<HoodieCompactionWriteStat>> partitionToCompactionWriteStats;
        
        private Builder() {
            super(HoodieCompactionMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionToCompactionWriteStats)) {
                this.partitionToCompactionWriteStats = (Map<String, List<HoodieCompactionWriteStat>>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionToCompactionWriteStats);
                this.fieldSetFlags()[0] = true;
            }
        }
        
        private Builder(final HoodieCompactionMetadata other) {
            super(HoodieCompactionMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionToCompactionWriteStats)) {
                this.partitionToCompactionWriteStats = (Map<String, List<HoodieCompactionWriteStat>>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionToCompactionWriteStats);
                this.fieldSetFlags()[0] = true;
            }
        }
        
        public Map<String, List<HoodieCompactionWriteStat>> getPartitionToCompactionWriteStats() {
            return this.partitionToCompactionWriteStats;
        }
        
        public Builder setPartitionToCompactionWriteStats(final Map<String, List<HoodieCompactionWriteStat>> value) {
            this.validate(this.fields()[0], (Object)value);
            this.partitionToCompactionWriteStats = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasPartitionToCompactionWriteStats() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearPartitionToCompactionWriteStats() {
            this.partitionToCompactionWriteStats = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public HoodieCompactionMetadata build() {
            try {
                final HoodieCompactionMetadata record = new HoodieCompactionMetadata();
                record.partitionToCompactionWriteStats = (Map<String, List<HoodieCompactionWriteStat>>)(this.fieldSetFlags()[0] ? this.partitionToCompactionWriteStats : this.defaultValue(this.fields()[0]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
