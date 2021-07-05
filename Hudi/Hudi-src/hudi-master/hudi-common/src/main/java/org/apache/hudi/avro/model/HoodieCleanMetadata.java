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
public class HoodieCleanMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 5032429008405240198L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCleanMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieCleanMetadata> DECODER;
    @Deprecated
    public String startCleanTime;
    @Deprecated
    public long timeTakenInMillis;
    @Deprecated
    public int totalFilesDeleted;
    @Deprecated
    public String earliestCommitToRetain;
    @Deprecated
    public Map<String, HoodieCleanPartitionMetadata> partitionMetadata;
    @Deprecated
    public Integer version;
    @Deprecated
    public Map<String, HoodieCleanPartitionMetadata> bootstrapPartitionMetadata;
    private static final DatumWriter<HoodieCleanMetadata> WRITER$;
    private static final DatumReader<HoodieCleanMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCleanMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCleanMetadata> getDecoder() {
        return HoodieCleanMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCleanMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCleanMetadata>)new BinaryMessageDecoder((GenericData)HoodieCleanMetadata.MODEL$, HoodieCleanMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCleanMetadata.ENCODER.encode((HoodieCleanMetadata) this);
    }
    
    public static HoodieCleanMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCleanMetadata)HoodieCleanMetadata.DECODER.decode(b);
    }
    
    public HoodieCleanMetadata() {
    }
    
    public HoodieCleanMetadata(final String startCleanTime, final Long timeTakenInMillis, final Integer totalFilesDeleted, final String earliestCommitToRetain, final Map<String, HoodieCleanPartitionMetadata> partitionMetadata, final Integer version, final Map<String, HoodieCleanPartitionMetadata> bootstrapPartitionMetadata) {
        this.startCleanTime = startCleanTime;
        this.timeTakenInMillis = timeTakenInMillis;
        this.totalFilesDeleted = totalFilesDeleted;
        this.earliestCommitToRetain = earliestCommitToRetain;
        this.partitionMetadata = partitionMetadata;
        this.version = version;
        this.bootstrapPartitionMetadata = bootstrapPartitionMetadata;
    }
    
    public Schema getSchema() {
        return HoodieCleanMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.startCleanTime;
            }
            case 1: {
                return this.timeTakenInMillis;
            }
            case 2: {
                return this.totalFilesDeleted;
            }
            case 3: {
                return this.earliestCommitToRetain;
            }
            case 4: {
                return this.partitionMetadata;
            }
            case 5: {
                return this.version;
            }
            case 6: {
                return this.bootstrapPartitionMetadata;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.startCleanTime = (String)value$;
                break;
            }
            case 1: {
                this.timeTakenInMillis = (long)value$;
                break;
            }
            case 2: {
                this.totalFilesDeleted = (int)value$;
                break;
            }
            case 3: {
                this.earliestCommitToRetain = (String)value$;
                break;
            }
            case 4: {
                this.partitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)value$;
                break;
            }
            case 5: {
                this.version = (Integer)value$;
                break;
            }
            case 6: {
                this.bootstrapPartitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getStartCleanTime() {
        return this.startCleanTime;
    }
    
    public void setStartCleanTime(final String value) {
        this.startCleanTime = value;
    }
    
    public Long getTimeTakenInMillis() {
        return this.timeTakenInMillis;
    }
    
    public void setTimeTakenInMillis(final Long value) {
        this.timeTakenInMillis = value;
    }
    
    public Integer getTotalFilesDeleted() {
        return this.totalFilesDeleted;
    }
    
    public void setTotalFilesDeleted(final Integer value) {
        this.totalFilesDeleted = value;
    }
    
    public String getEarliestCommitToRetain() {
        return this.earliestCommitToRetain;
    }
    
    public void setEarliestCommitToRetain(final String value) {
        this.earliestCommitToRetain = value;
    }
    
    public Map<String, HoodieCleanPartitionMetadata> getPartitionMetadata() {
        return this.partitionMetadata;
    }
    
    public void setPartitionMetadata(final Map<String, HoodieCleanPartitionMetadata> value) {
        this.partitionMetadata = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public Map<String, HoodieCleanPartitionMetadata> getBootstrapPartitionMetadata() {
        return this.bootstrapPartitionMetadata;
    }
    
    public void setBootstrapPartitionMetadata(final Map<String, HoodieCleanPartitionMetadata> value) {
        this.bootstrapPartitionMetadata = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCleanMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCleanMetadata.WRITER$.write((HoodieCleanMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCleanMetadata.READER$.read((HoodieCleanMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCleanMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"startCleanTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"earliestCommitToRetain\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieCleanPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"deletePathPatterns\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"HoodieCleanPartitionMetadata\",\"avro.java.string\":\"String\",\"default\":null}],\"default\":null}]}");
        HoodieCleanMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCleanMetadata.MODEL$, HoodieCleanMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCleanMetadata.MODEL$, HoodieCleanMetadata.SCHEMA$);
        WRITER$ = HoodieCleanMetadata.MODEL$.createDatumWriter(HoodieCleanMetadata.SCHEMA$);
        READER$ = HoodieCleanMetadata.MODEL$.createDatumReader(HoodieCleanMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCleanMetadata> implements RecordBuilder<HoodieCleanMetadata>
    {
        private String startCleanTime;
        private long timeTakenInMillis;
        private int totalFilesDeleted;
        private String earliestCommitToRetain;
        private Map<String, HoodieCleanPartitionMetadata> partitionMetadata;
        private Integer version;
        private Map<String, HoodieCleanPartitionMetadata> bootstrapPartitionMetadata;
        
        private Builder() {
            super(HoodieCleanMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.startCleanTime)) {
                this.startCleanTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startCleanTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.timeTakenInMillis)) {
                this.timeTakenInMillis = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.timeTakenInMillis);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.totalFilesDeleted)) {
                this.totalFilesDeleted = (int)this.data().deepCopy(this.fields()[2].schema(), (Object)other.totalFilesDeleted);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.earliestCommitToRetain)) {
                this.earliestCommitToRetain = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.earliestCommitToRetain);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.bootstrapPartitionMetadata)) {
                this.bootstrapPartitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)this.data().deepCopy(this.fields()[6].schema(), (Object)other.bootstrapPartitionMetadata);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        private Builder(final HoodieCleanMetadata other) {
            super(HoodieCleanMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.startCleanTime)) {
                this.startCleanTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startCleanTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.timeTakenInMillis)) {
                this.timeTakenInMillis = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.timeTakenInMillis);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.totalFilesDeleted)) {
                this.totalFilesDeleted = (int)this.data().deepCopy(this.fields()[2].schema(), (Object)other.totalFilesDeleted);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.earliestCommitToRetain)) {
                this.earliestCommitToRetain = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.earliestCommitToRetain);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.bootstrapPartitionMetadata)) {
                this.bootstrapPartitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)this.data().deepCopy(this.fields()[6].schema(), (Object)other.bootstrapPartitionMetadata);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        public String getStartCleanTime() {
            return this.startCleanTime;
        }
        
        public Builder setStartCleanTime(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.startCleanTime = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasStartCleanTime() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearStartCleanTime() {
            this.startCleanTime = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Long getTimeTakenInMillis() {
            return this.timeTakenInMillis;
        }
        
        public Builder setTimeTakenInMillis(final long value) {
            this.validate(this.fields()[1], (Object)value);
            this.timeTakenInMillis = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasTimeTakenInMillis() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearTimeTakenInMillis() {
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Integer getTotalFilesDeleted() {
            return this.totalFilesDeleted;
        }
        
        public Builder setTotalFilesDeleted(final int value) {
            this.validate(this.fields()[2], (Object)value);
            this.totalFilesDeleted = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasTotalFilesDeleted() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearTotalFilesDeleted() {
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public String getEarliestCommitToRetain() {
            return this.earliestCommitToRetain;
        }
        
        public Builder setEarliestCommitToRetain(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.earliestCommitToRetain = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasEarliestCommitToRetain() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearEarliestCommitToRetain() {
            this.earliestCommitToRetain = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Map<String, HoodieCleanPartitionMetadata> getPartitionMetadata() {
            return this.partitionMetadata;
        }
        
        public Builder setPartitionMetadata(final Map<String, HoodieCleanPartitionMetadata> value) {
            this.validate(this.fields()[4], (Object)value);
            this.partitionMetadata = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasPartitionMetadata() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearPartitionMetadata() {
            this.partitionMetadata = null;
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
        
        public Map<String, HoodieCleanPartitionMetadata> getBootstrapPartitionMetadata() {
            return this.bootstrapPartitionMetadata;
        }
        
        public Builder setBootstrapPartitionMetadata(final Map<String, HoodieCleanPartitionMetadata> value) {
            this.validate(this.fields()[6], (Object)value);
            this.bootstrapPartitionMetadata = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasBootstrapPartitionMetadata() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearBootstrapPartitionMetadata() {
            this.bootstrapPartitionMetadata = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public HoodieCleanMetadata build() {
            try {
                final HoodieCleanMetadata record = new HoodieCleanMetadata();
                record.startCleanTime = (String)(this.fieldSetFlags()[0] ? this.startCleanTime : this.defaultValue(this.fields()[0]));
                record.timeTakenInMillis = (long)(this.fieldSetFlags()[1] ? this.timeTakenInMillis : this.defaultValue(this.fields()[1]));
                record.totalFilesDeleted = (int)(this.fieldSetFlags()[2] ? this.totalFilesDeleted : this.defaultValue(this.fields()[2]));
                record.earliestCommitToRetain = (String)(this.fieldSetFlags()[3] ? this.earliestCommitToRetain : this.defaultValue(this.fields()[3]));
                record.partitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)(this.fieldSetFlags()[4] ? this.partitionMetadata : this.defaultValue(this.fields()[4]));
                record.version = (Integer)(this.fieldSetFlags()[5] ? this.version : this.defaultValue(this.fields()[5]));
                record.bootstrapPartitionMetadata = (Map<String, HoodieCleanPartitionMetadata>)(this.fieldSetFlags()[6] ? this.bootstrapPartitionMetadata : this.defaultValue(this.fields()[6]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
