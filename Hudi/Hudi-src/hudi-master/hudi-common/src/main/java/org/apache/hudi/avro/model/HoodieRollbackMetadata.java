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
public class HoodieRollbackMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 8154764647014070993L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieRollbackMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieRollbackMetadata> DECODER;
    @Deprecated
    public String startRollbackTime;
    @Deprecated
    public long timeTakenInMillis;
    @Deprecated
    public int totalFilesDeleted;
    @Deprecated
    public List<String> commitsRollback;
    @Deprecated
    public Map<String, HoodieRollbackPartitionMetadata> partitionMetadata;
    @Deprecated
    public Integer version;
    @Deprecated
    public List<HoodieInstantInfo> instantsRollback;
    private static final DatumWriter<HoodieRollbackMetadata> WRITER$;
    private static final DatumReader<HoodieRollbackMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieRollbackMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieRollbackMetadata> getDecoder() {
        return HoodieRollbackMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieRollbackMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieRollbackMetadata>)new BinaryMessageDecoder((GenericData)HoodieRollbackMetadata.MODEL$, HoodieRollbackMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieRollbackMetadata.ENCODER.encode((HoodieRollbackMetadata) this);
    }
    
    public static HoodieRollbackMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieRollbackMetadata)HoodieRollbackMetadata.DECODER.decode(b);
    }
    
    public HoodieRollbackMetadata() {
    }
    
    public HoodieRollbackMetadata(final String startRollbackTime, final Long timeTakenInMillis, final Integer totalFilesDeleted, final List<String> commitsRollback, final Map<String, HoodieRollbackPartitionMetadata> partitionMetadata, final Integer version, final List<HoodieInstantInfo> instantsRollback) {
        this.startRollbackTime = startRollbackTime;
        this.timeTakenInMillis = timeTakenInMillis;
        this.totalFilesDeleted = totalFilesDeleted;
        this.commitsRollback = commitsRollback;
        this.partitionMetadata = partitionMetadata;
        this.version = version;
        this.instantsRollback = instantsRollback;
    }
    
    public Schema getSchema() {
        return HoodieRollbackMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.startRollbackTime;
            }
            case 1: {
                return this.timeTakenInMillis;
            }
            case 2: {
                return this.totalFilesDeleted;
            }
            case 3: {
                return this.commitsRollback;
            }
            case 4: {
                return this.partitionMetadata;
            }
            case 5: {
                return this.version;
            }
            case 6: {
                return this.instantsRollback;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.startRollbackTime = (String)value$;
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
                this.commitsRollback = (List<String>)value$;
                break;
            }
            case 4: {
                this.partitionMetadata = (Map<String, HoodieRollbackPartitionMetadata>)value$;
                break;
            }
            case 5: {
                this.version = (Integer)value$;
                break;
            }
            case 6: {
                this.instantsRollback = (List<HoodieInstantInfo>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getStartRollbackTime() {
        return this.startRollbackTime;
    }
    
    public void setStartRollbackTime(final String value) {
        this.startRollbackTime = value;
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
    
    public List<String> getCommitsRollback() {
        return this.commitsRollback;
    }
    
    public void setCommitsRollback(final List<String> value) {
        this.commitsRollback = value;
    }
    
    public Map<String, HoodieRollbackPartitionMetadata> getPartitionMetadata() {
        return this.partitionMetadata;
    }
    
    public void setPartitionMetadata(final Map<String, HoodieRollbackPartitionMetadata> value) {
        this.partitionMetadata = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public List<HoodieInstantInfo> getInstantsRollback() {
        return this.instantsRollback;
    }
    
    public void setInstantsRollback(final List<HoodieInstantInfo> value) {
        this.instantsRollback = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieRollbackMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieRollbackMetadata.WRITER$.write((HoodieRollbackMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieRollbackMetadata.READER$.read((HoodieRollbackMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieRollbackMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"startRollbackTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"commitsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieRollbackPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"rollbackLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"writtenLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"instantsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieInstantInfo\",\"fields\":[{\"name\":\"commitTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},\"default\":[]},\"default\":[]}]}");
        HoodieRollbackMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieRollbackMetadata.MODEL$, HoodieRollbackMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieRollbackMetadata.MODEL$, HoodieRollbackMetadata.SCHEMA$);
        WRITER$ = HoodieRollbackMetadata.MODEL$.createDatumWriter(HoodieRollbackMetadata.SCHEMA$);
        READER$ = HoodieRollbackMetadata.MODEL$.createDatumReader(HoodieRollbackMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieRollbackMetadata> implements RecordBuilder<HoodieRollbackMetadata>
    {
        private String startRollbackTime;
        private long timeTakenInMillis;
        private int totalFilesDeleted;
        private List<String> commitsRollback;
        private Map<String, HoodieRollbackPartitionMetadata> partitionMetadata;
        private Integer version;
        private List<HoodieInstantInfo> instantsRollback;
        
        private Builder() {
            super(HoodieRollbackMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.startRollbackTime)) {
                this.startRollbackTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startRollbackTime);
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
            if (isValidValue(this.fields()[3], (Object)other.commitsRollback)) {
                this.commitsRollback = (List<String>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.commitsRollback);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieRollbackPartitionMetadata>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.instantsRollback)) {
                this.instantsRollback = (List<HoodieInstantInfo>)this.data().deepCopy(this.fields()[6].schema(), (Object)other.instantsRollback);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        private Builder(final HoodieRollbackMetadata other) {
            super(HoodieRollbackMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.startRollbackTime)) {
                this.startRollbackTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startRollbackTime);
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
            if (isValidValue(this.fields()[3], (Object)other.commitsRollback)) {
                this.commitsRollback = (List<String>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.commitsRollback);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionMetadata)) {
                this.partitionMetadata = (Map<String, HoodieRollbackPartitionMetadata>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionMetadata);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[5].schema(), (Object)other.version);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.instantsRollback)) {
                this.instantsRollback = (List<HoodieInstantInfo>)this.data().deepCopy(this.fields()[6].schema(), (Object)other.instantsRollback);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        public String getStartRollbackTime() {
            return this.startRollbackTime;
        }
        
        public Builder setStartRollbackTime(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.startRollbackTime = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasStartRollbackTime() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearStartRollbackTime() {
            this.startRollbackTime = null;
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
        
        public List<String> getCommitsRollback() {
            return this.commitsRollback;
        }
        
        public Builder setCommitsRollback(final List<String> value) {
            this.validate(this.fields()[3], (Object)value);
            this.commitsRollback = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasCommitsRollback() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearCommitsRollback() {
            this.commitsRollback = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Map<String, HoodieRollbackPartitionMetadata> getPartitionMetadata() {
            return this.partitionMetadata;
        }
        
        public Builder setPartitionMetadata(final Map<String, HoodieRollbackPartitionMetadata> value) {
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
        
        public List<HoodieInstantInfo> getInstantsRollback() {
            return this.instantsRollback;
        }
        
        public Builder setInstantsRollback(final List<HoodieInstantInfo> value) {
            this.validate(this.fields()[6], (Object)value);
            this.instantsRollback = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasInstantsRollback() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearInstantsRollback() {
            this.instantsRollback = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public HoodieRollbackMetadata build() {
            try {
                final HoodieRollbackMetadata record = new HoodieRollbackMetadata();
                record.startRollbackTime = (String)(this.fieldSetFlags()[0] ? this.startRollbackTime : this.defaultValue(this.fields()[0]));
                record.timeTakenInMillis = (long)(this.fieldSetFlags()[1] ? this.timeTakenInMillis : this.defaultValue(this.fields()[1]));
                record.totalFilesDeleted = (int)(this.fieldSetFlags()[2] ? this.totalFilesDeleted : this.defaultValue(this.fields()[2]));
                record.commitsRollback = (List<String>)(this.fieldSetFlags()[3] ? this.commitsRollback : this.defaultValue(this.fields()[3]));
                record.partitionMetadata = (Map<String, HoodieRollbackPartitionMetadata>)(this.fieldSetFlags()[4] ? this.partitionMetadata : this.defaultValue(this.fields()[4]));
                record.version = (Integer)(this.fieldSetFlags()[5] ? this.version : this.defaultValue(this.fields()[5]));
                record.instantsRollback = (List<HoodieInstantInfo>)(this.fieldSetFlags()[6] ? this.instantsRollback : this.defaultValue(this.fields()[6]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
