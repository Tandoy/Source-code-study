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
public class HoodieRestoreMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -6732760604812084940L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieRestoreMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieRestoreMetadata> DECODER;
    @Deprecated
    public String startRestoreTime;
    @Deprecated
    public long timeTakenInMillis;
    @Deprecated
    public List<String> instantsToRollback;
    @Deprecated
    public Map<String, List<HoodieRollbackMetadata>> hoodieRestoreMetadata;
    @Deprecated
    public Integer version;
    @Deprecated
    public List<HoodieInstantInfo> restoreInstantInfo;
    private static final DatumWriter<HoodieRestoreMetadata> WRITER$;
    private static final DatumReader<HoodieRestoreMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieRestoreMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieRestoreMetadata> getDecoder() {
        return HoodieRestoreMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieRestoreMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieRestoreMetadata>)new BinaryMessageDecoder((GenericData)HoodieRestoreMetadata.MODEL$, HoodieRestoreMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieRestoreMetadata.ENCODER.encode((HoodieRestoreMetadata) this);
    }
    
    public static HoodieRestoreMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieRestoreMetadata)HoodieRestoreMetadata.DECODER.decode(b);
    }
    
    public HoodieRestoreMetadata() {
    }
    
    public HoodieRestoreMetadata(final String startRestoreTime, final Long timeTakenInMillis, final List<String> instantsToRollback, final Map<String, List<HoodieRollbackMetadata>> hoodieRestoreMetadata, final Integer version, final List<HoodieInstantInfo> restoreInstantInfo) {
        this.startRestoreTime = startRestoreTime;
        this.timeTakenInMillis = timeTakenInMillis;
        this.instantsToRollback = instantsToRollback;
        this.hoodieRestoreMetadata = hoodieRestoreMetadata;
        this.version = version;
        this.restoreInstantInfo = restoreInstantInfo;
    }
    
    public Schema getSchema() {
        return HoodieRestoreMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.startRestoreTime;
            }
            case 1: {
                return this.timeTakenInMillis;
            }
            case 2: {
                return this.instantsToRollback;
            }
            case 3: {
                return this.hoodieRestoreMetadata;
            }
            case 4: {
                return this.version;
            }
            case 5: {
                return this.restoreInstantInfo;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.startRestoreTime = (String)value$;
                break;
            }
            case 1: {
                this.timeTakenInMillis = (long)value$;
                break;
            }
            case 2: {
                this.instantsToRollback = (List<String>)value$;
                break;
            }
            case 3: {
                this.hoodieRestoreMetadata = (Map<String, List<HoodieRollbackMetadata>>)value$;
                break;
            }
            case 4: {
                this.version = (Integer)value$;
                break;
            }
            case 5: {
                this.restoreInstantInfo = (List<HoodieInstantInfo>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getStartRestoreTime() {
        return this.startRestoreTime;
    }
    
    public void setStartRestoreTime(final String value) {
        this.startRestoreTime = value;
    }
    
    public Long getTimeTakenInMillis() {
        return this.timeTakenInMillis;
    }
    
    public void setTimeTakenInMillis(final Long value) {
        this.timeTakenInMillis = value;
    }
    
    public List<String> getInstantsToRollback() {
        return this.instantsToRollback;
    }
    
    public void setInstantsToRollback(final List<String> value) {
        this.instantsToRollback = value;
    }
    
    public Map<String, List<HoodieRollbackMetadata>> getHoodieRestoreMetadata() {
        return this.hoodieRestoreMetadata;
    }
    
    public void setHoodieRestoreMetadata(final Map<String, List<HoodieRollbackMetadata>> value) {
        this.hoodieRestoreMetadata = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public List<HoodieInstantInfo> getRestoreInstantInfo() {
        return this.restoreInstantInfo;
    }
    
    public void setRestoreInstantInfo(final List<HoodieInstantInfo> value) {
        this.restoreInstantInfo = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieRestoreMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieRestoreMetadata.WRITER$.write((HoodieRestoreMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieRestoreMetadata.READER$.read((HoodieRestoreMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieRestoreMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"startRestoreTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"instantsToRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"hoodieRestoreMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieRollbackMetadata\",\"fields\":[{\"name\":\"startRollbackTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"commitsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieRollbackPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"rollbackLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"writtenLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"instantsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieInstantInfo\",\"fields\":[{\"name\":\"commitTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},\"default\":[]},\"default\":[]}]}],\"default\":null},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"restoreInstantInfo\",\"type\":{\"type\":\"array\",\"items\":\"HoodieInstantInfo\",\"default\":null},\"default\":null}]}");
        HoodieRestoreMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieRestoreMetadata.MODEL$, HoodieRestoreMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieRestoreMetadata.MODEL$, HoodieRestoreMetadata.SCHEMA$);
        WRITER$ = HoodieRestoreMetadata.MODEL$.createDatumWriter(HoodieRestoreMetadata.SCHEMA$);
        READER$ = HoodieRestoreMetadata.MODEL$.createDatumReader(HoodieRestoreMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieRestoreMetadata> implements RecordBuilder<HoodieRestoreMetadata>
    {
        private String startRestoreTime;
        private long timeTakenInMillis;
        private List<String> instantsToRollback;
        private Map<String, List<HoodieRollbackMetadata>> hoodieRestoreMetadata;
        private Integer version;
        private List<HoodieInstantInfo> restoreInstantInfo;
        
        private Builder() {
            super(HoodieRestoreMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.startRestoreTime)) {
                this.startRestoreTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startRestoreTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.timeTakenInMillis)) {
                this.timeTakenInMillis = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.timeTakenInMillis);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.instantsToRollback)) {
                this.instantsToRollback = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.instantsToRollback);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.hoodieRestoreMetadata)) {
                this.hoodieRestoreMetadata = (Map<String, List<HoodieRollbackMetadata>>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.hoodieRestoreMetadata);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.version);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.restoreInstantInfo)) {
                this.restoreInstantInfo = (List<HoodieInstantInfo>)this.data().deepCopy(this.fields()[5].schema(), (Object)other.restoreInstantInfo);
                this.fieldSetFlags()[5] = true;
            }
        }
        
        private Builder(final HoodieRestoreMetadata other) {
            super(HoodieRestoreMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.startRestoreTime)) {
                this.startRestoreTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.startRestoreTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.timeTakenInMillis)) {
                this.timeTakenInMillis = (long)this.data().deepCopy(this.fields()[1].schema(), (Object)other.timeTakenInMillis);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.instantsToRollback)) {
                this.instantsToRollback = (List<String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.instantsToRollback);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.hoodieRestoreMetadata)) {
                this.hoodieRestoreMetadata = (Map<String, List<HoodieRollbackMetadata>>)this.data().deepCopy(this.fields()[3].schema(), (Object)other.hoodieRestoreMetadata);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.version);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.restoreInstantInfo)) {
                this.restoreInstantInfo = (List<HoodieInstantInfo>)this.data().deepCopy(this.fields()[5].schema(), (Object)other.restoreInstantInfo);
                this.fieldSetFlags()[5] = true;
            }
        }
        
        public String getStartRestoreTime() {
            return this.startRestoreTime;
        }
        
        public Builder setStartRestoreTime(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.startRestoreTime = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasStartRestoreTime() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearStartRestoreTime() {
            this.startRestoreTime = null;
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
        
        public List<String> getInstantsToRollback() {
            return this.instantsToRollback;
        }
        
        public Builder setInstantsToRollback(final List<String> value) {
            this.validate(this.fields()[2], (Object)value);
            this.instantsToRollback = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasInstantsToRollback() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearInstantsToRollback() {
            this.instantsToRollback = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Map<String, List<HoodieRollbackMetadata>> getHoodieRestoreMetadata() {
            return this.hoodieRestoreMetadata;
        }
        
        public Builder setHoodieRestoreMetadata(final Map<String, List<HoodieRollbackMetadata>> value) {
            this.validate(this.fields()[3], (Object)value);
            this.hoodieRestoreMetadata = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasHoodieRestoreMetadata() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearHoodieRestoreMetadata() {
            this.hoodieRestoreMetadata = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[4], (Object)value);
            this.version = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public List<HoodieInstantInfo> getRestoreInstantInfo() {
            return this.restoreInstantInfo;
        }
        
        public Builder setRestoreInstantInfo(final List<HoodieInstantInfo> value) {
            this.validate(this.fields()[5], (Object)value);
            this.restoreInstantInfo = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasRestoreInstantInfo() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearRestoreInstantInfo() {
            this.restoreInstantInfo = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public HoodieRestoreMetadata build() {
            try {
                final HoodieRestoreMetadata record = new HoodieRestoreMetadata();
                record.startRestoreTime = (String)(this.fieldSetFlags()[0] ? this.startRestoreTime : this.defaultValue(this.fields()[0]));
                record.timeTakenInMillis = (long)(this.fieldSetFlags()[1] ? this.timeTakenInMillis : this.defaultValue(this.fields()[1]));
                record.instantsToRollback = (List<String>)(this.fieldSetFlags()[2] ? this.instantsToRollback : this.defaultValue(this.fields()[2]));
                record.hoodieRestoreMetadata = (Map<String, List<HoodieRollbackMetadata>>)(this.fieldSetFlags()[3] ? this.hoodieRestoreMetadata : this.defaultValue(this.fields()[3]));
                record.version = (Integer)(this.fieldSetFlags()[4] ? this.version : this.defaultValue(this.fields()[4]));
                record.restoreInstantInfo = (List<HoodieInstantInfo>)(this.fieldSetFlags()[5] ? this.restoreInstantInfo : this.defaultValue(this.fields()[5]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
