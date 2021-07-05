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
public class HoodieCleanerPlan extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -2725205331999389860L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCleanerPlan> ENCODER;
    private static final BinaryMessageDecoder<HoodieCleanerPlan> DECODER;
    @Deprecated
    public HoodieActionInstant earliestInstantToRetain;
    @Deprecated
    public String policy;
    @Deprecated
    public Map<String, List<String>> filesToBeDeletedPerPartition;
    @Deprecated
    public Integer version;
    @Deprecated
    public Map<String, List<HoodieCleanFileInfo>> filePathsToBeDeletedPerPartition;
    private static final DatumWriter<HoodieCleanerPlan> WRITER$;
    private static final DatumReader<HoodieCleanerPlan> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCleanerPlan.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCleanerPlan> getDecoder() {
        return HoodieCleanerPlan.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCleanerPlan> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCleanerPlan>)new BinaryMessageDecoder((GenericData)HoodieCleanerPlan.MODEL$, HoodieCleanerPlan.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCleanerPlan.ENCODER.encode((HoodieCleanerPlan) this);
    }
    
    public static HoodieCleanerPlan fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCleanerPlan)HoodieCleanerPlan.DECODER.decode(b);
    }
    
    public HoodieCleanerPlan() {
    }
    
    public HoodieCleanerPlan(final HoodieActionInstant earliestInstantToRetain, final String policy, final Map<String, List<String>> filesToBeDeletedPerPartition, final Integer version, final Map<String, List<HoodieCleanFileInfo>> filePathsToBeDeletedPerPartition) {
        this.earliestInstantToRetain = earliestInstantToRetain;
        this.policy = policy;
        this.filesToBeDeletedPerPartition = filesToBeDeletedPerPartition;
        this.version = version;
        this.filePathsToBeDeletedPerPartition = filePathsToBeDeletedPerPartition;
    }
    
    public Schema getSchema() {
        return HoodieCleanerPlan.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.earliestInstantToRetain;
            }
            case 1: {
                return this.policy;
            }
            case 2: {
                return this.filesToBeDeletedPerPartition;
            }
            case 3: {
                return this.version;
            }
            case 4: {
                return this.filePathsToBeDeletedPerPartition;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.earliestInstantToRetain = (HoodieActionInstant)value$;
                break;
            }
            case 1: {
                this.policy = (String)value$;
                break;
            }
            case 2: {
                this.filesToBeDeletedPerPartition = (Map<String, List<String>>)value$;
                break;
            }
            case 3: {
                this.version = (Integer)value$;
                break;
            }
            case 4: {
                this.filePathsToBeDeletedPerPartition = (Map<String, List<HoodieCleanFileInfo>>)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public HoodieActionInstant getEarliestInstantToRetain() {
        return this.earliestInstantToRetain;
    }
    
    public void setEarliestInstantToRetain(final HoodieActionInstant value) {
        this.earliestInstantToRetain = value;
    }
    
    public String getPolicy() {
        return this.policy;
    }
    
    public void setPolicy(final String value) {
        this.policy = value;
    }
    
    public Map<String, List<String>> getFilesToBeDeletedPerPartition() {
        return this.filesToBeDeletedPerPartition;
    }
    
    public void setFilesToBeDeletedPerPartition(final Map<String, List<String>> value) {
        this.filesToBeDeletedPerPartition = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public Map<String, List<HoodieCleanFileInfo>> getFilePathsToBeDeletedPerPartition() {
        return this.filePathsToBeDeletedPerPartition;
    }
    
    public void setFilePathsToBeDeletedPerPartition(final Map<String, List<HoodieCleanFileInfo>> value) {
        this.filePathsToBeDeletedPerPartition = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCleanerPlan other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCleanerPlan.WRITER$.write((HoodieCleanerPlan) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCleanerPlan.READER$.read((HoodieCleanerPlan) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCleanerPlan\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"earliestInstantToRetain\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieActionInstant\",\"fields\":[{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"state\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}],\"default\":null},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"filesToBeDeletedPerPartition\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"filePathsToBeDeletedPerPartition\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCleanFileInfo\",\"fields\":[{\"name\":\"filePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"isBootstrapBaseFile\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}},\"avro.java.string\":\"String\"}],\"doc\":\"This field replaces the field filesToBeDeletedPerPartition\",\"default\":null}]}");
        HoodieCleanerPlan.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCleanerPlan.MODEL$, HoodieCleanerPlan.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCleanerPlan.MODEL$, HoodieCleanerPlan.SCHEMA$);
        WRITER$ = HoodieCleanerPlan.MODEL$.createDatumWriter(HoodieCleanerPlan.SCHEMA$);
        READER$ = HoodieCleanerPlan.MODEL$.createDatumReader(HoodieCleanerPlan.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCleanerPlan> implements RecordBuilder<HoodieCleanerPlan>
    {
        private HoodieActionInstant earliestInstantToRetain;
        private HoodieActionInstant.Builder earliestInstantToRetainBuilder;
        private String policy;
        private Map<String, List<String>> filesToBeDeletedPerPartition;
        private Integer version;
        private Map<String, List<HoodieCleanFileInfo>> filePathsToBeDeletedPerPartition;
        
        private Builder() {
            super(HoodieCleanerPlan.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.earliestInstantToRetain)) {
                this.earliestInstantToRetain = (HoodieActionInstant)this.data().deepCopy(this.fields()[0].schema(), (Object)other.earliestInstantToRetain);
                this.fieldSetFlags()[0] = true;
            }
            if (other.hasEarliestInstantToRetainBuilder()) {
                this.earliestInstantToRetainBuilder = HoodieActionInstant.newBuilder(other.getEarliestInstantToRetainBuilder());
            }
            if (isValidValue(this.fields()[1], (Object)other.policy)) {
                this.policy = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.policy);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.filesToBeDeletedPerPartition)) {
                this.filesToBeDeletedPerPartition = (Map<String, List<String>>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.filesToBeDeletedPerPartition);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.filePathsToBeDeletedPerPartition)) {
                this.filePathsToBeDeletedPerPartition = (Map<String, List<HoodieCleanFileInfo>>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.filePathsToBeDeletedPerPartition);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        private Builder(final HoodieCleanerPlan other) {
            super(HoodieCleanerPlan.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.earliestInstantToRetain)) {
                this.earliestInstantToRetain = (HoodieActionInstant)this.data().deepCopy(this.fields()[0].schema(), (Object)other.earliestInstantToRetain);
                this.fieldSetFlags()[0] = true;
            }
            this.earliestInstantToRetainBuilder = null;
            if (isValidValue(this.fields()[1], (Object)other.policy)) {
                this.policy = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.policy);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.filesToBeDeletedPerPartition)) {
                this.filesToBeDeletedPerPartition = (Map<String, List<String>>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.filesToBeDeletedPerPartition);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.filePathsToBeDeletedPerPartition)) {
                this.filePathsToBeDeletedPerPartition = (Map<String, List<HoodieCleanFileInfo>>)this.data().deepCopy(this.fields()[4].schema(), (Object)other.filePathsToBeDeletedPerPartition);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        public HoodieActionInstant getEarliestInstantToRetain() {
            return this.earliestInstantToRetain;
        }
        
        public Builder setEarliestInstantToRetain(final HoodieActionInstant value) {
            this.validate(this.fields()[0], (Object)value);
            this.earliestInstantToRetainBuilder = null;
            this.earliestInstantToRetain = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasEarliestInstantToRetain() {
            return this.fieldSetFlags()[0];
        }
        
        public HoodieActionInstant.Builder getEarliestInstantToRetainBuilder() {
            if (this.earliestInstantToRetainBuilder == null) {
                if (this.hasEarliestInstantToRetain()) {
                    this.setEarliestInstantToRetainBuilder(HoodieActionInstant.newBuilder(this.earliestInstantToRetain));
                }
                else {
                    this.setEarliestInstantToRetainBuilder(HoodieActionInstant.newBuilder());
                }
            }
            return this.earliestInstantToRetainBuilder;
        }
        
        public Builder setEarliestInstantToRetainBuilder(final HoodieActionInstant.Builder value) {
            this.clearEarliestInstantToRetain();
            this.earliestInstantToRetainBuilder = value;
            return this;
        }
        
        public boolean hasEarliestInstantToRetainBuilder() {
            return this.earliestInstantToRetainBuilder != null;
        }
        
        public Builder clearEarliestInstantToRetain() {
            this.earliestInstantToRetain = null;
            this.earliestInstantToRetainBuilder = null;
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
        
        public Map<String, List<String>> getFilesToBeDeletedPerPartition() {
            return this.filesToBeDeletedPerPartition;
        }
        
        public Builder setFilesToBeDeletedPerPartition(final Map<String, List<String>> value) {
            this.validate(this.fields()[2], (Object)value);
            this.filesToBeDeletedPerPartition = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasFilesToBeDeletedPerPartition() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearFilesToBeDeletedPerPartition() {
            this.filesToBeDeletedPerPartition = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[3], (Object)value);
            this.version = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Map<String, List<HoodieCleanFileInfo>> getFilePathsToBeDeletedPerPartition() {
            return this.filePathsToBeDeletedPerPartition;
        }
        
        public Builder setFilePathsToBeDeletedPerPartition(final Map<String, List<HoodieCleanFileInfo>> value) {
            this.validate(this.fields()[4], (Object)value);
            this.filePathsToBeDeletedPerPartition = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasFilePathsToBeDeletedPerPartition() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearFilePathsToBeDeletedPerPartition() {
            this.filePathsToBeDeletedPerPartition = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieCleanerPlan build() {
            try {
                final HoodieCleanerPlan record = new HoodieCleanerPlan();
                if (this.earliestInstantToRetainBuilder != null) {
                    record.earliestInstantToRetain = this.earliestInstantToRetainBuilder.build();
                }
                else {
                    record.earliestInstantToRetain = (HoodieActionInstant)(this.fieldSetFlags()[0] ? this.earliestInstantToRetain : this.defaultValue(this.fields()[0]));
                }
                record.policy = (String)(this.fieldSetFlags()[1] ? this.policy : this.defaultValue(this.fields()[1]));
                record.filesToBeDeletedPerPartition = (Map<String, List<String>>)(this.fieldSetFlags()[2] ? this.filesToBeDeletedPerPartition : this.defaultValue(this.fields()[2]));
                record.version = (Integer)(this.fieldSetFlags()[3] ? this.version : this.defaultValue(this.fields()[3]));
                record.filePathsToBeDeletedPerPartition = (Map<String, List<HoodieCleanFileInfo>>)(this.fieldSetFlags()[4] ? this.filePathsToBeDeletedPerPartition : this.defaultValue(this.fields()[4]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
