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
public class HoodieRequestedReplaceMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -6941934923711027842L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieRequestedReplaceMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieRequestedReplaceMetadata> DECODER;
    @Deprecated
    public String operationType;
    @Deprecated
    public HoodieClusteringPlan clusteringPlan;
    @Deprecated
    public Map<String, String> extraMetadata;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieRequestedReplaceMetadata> WRITER$;
    private static final DatumReader<HoodieRequestedReplaceMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieRequestedReplaceMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieRequestedReplaceMetadata> getDecoder() {
        return HoodieRequestedReplaceMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieRequestedReplaceMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieRequestedReplaceMetadata>)new BinaryMessageDecoder((GenericData)HoodieRequestedReplaceMetadata.MODEL$, HoodieRequestedReplaceMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieRequestedReplaceMetadata.ENCODER.encode((HoodieRequestedReplaceMetadata) this);
    }
    
    public static HoodieRequestedReplaceMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieRequestedReplaceMetadata)HoodieRequestedReplaceMetadata.DECODER.decode(b);
    }
    
    public HoodieRequestedReplaceMetadata() {
    }
    
    public HoodieRequestedReplaceMetadata(final String operationType, final HoodieClusteringPlan clusteringPlan, final Map<String, String> extraMetadata, final Integer version) {
        this.operationType = operationType;
        this.clusteringPlan = clusteringPlan;
        this.extraMetadata = extraMetadata;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieRequestedReplaceMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.operationType;
            }
            case 1: {
                return this.clusteringPlan;
            }
            case 2: {
                return this.extraMetadata;
            }
            case 3: {
                return this.version;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.operationType = (String)value$;
                break;
            }
            case 1: {
                this.clusteringPlan = (HoodieClusteringPlan)value$;
                break;
            }
            case 2: {
                this.extraMetadata = (Map<String, String>)value$;
                break;
            }
            case 3: {
                this.version = (Integer)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(final String value) {
        this.operationType = value;
    }
    
    public HoodieClusteringPlan getClusteringPlan() {
        return this.clusteringPlan;
    }
    
    public void setClusteringPlan(final HoodieClusteringPlan value) {
        this.clusteringPlan = value;
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
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieRequestedReplaceMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieRequestedReplaceMetadata.WRITER$.write((HoodieRequestedReplaceMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieRequestedReplaceMetadata.READER$.read((HoodieRequestedReplaceMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieRequestedReplaceMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"\"},{\"name\":\"clusteringPlan\",\"type\":[{\"type\":\"record\",\"name\":\"HoodieClusteringPlan\",\"fields\":[{\"name\":\"inputGroups\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"strategy\",\"type\":[{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]},\"null\"],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]},\"null\"],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieRequestedReplaceMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieRequestedReplaceMetadata.MODEL$, HoodieRequestedReplaceMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieRequestedReplaceMetadata.MODEL$, HoodieRequestedReplaceMetadata.SCHEMA$);
        WRITER$ = HoodieRequestedReplaceMetadata.MODEL$.createDatumWriter(HoodieRequestedReplaceMetadata.SCHEMA$);
        READER$ = HoodieRequestedReplaceMetadata.MODEL$.createDatumReader(HoodieRequestedReplaceMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieRequestedReplaceMetadata> implements RecordBuilder<HoodieRequestedReplaceMetadata>
    {
        private String operationType;
        private HoodieClusteringPlan clusteringPlan;
        private HoodieClusteringPlan.Builder clusteringPlanBuilder;
        private Map<String, String> extraMetadata;
        private Integer version;
        
        private Builder() {
            super(HoodieRequestedReplaceMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.operationType)) {
                this.operationType = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.operationType);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.clusteringPlan)) {
                this.clusteringPlan = (HoodieClusteringPlan)this.data().deepCopy(this.fields()[1].schema(), (Object)other.clusteringPlan);
                this.fieldSetFlags()[1] = true;
            }
            if (other.hasClusteringPlanBuilder()) {
                this.clusteringPlanBuilder = HoodieClusteringPlan.newBuilder(other.getClusteringPlanBuilder());
            }
            if (isValidValue(this.fields()[2], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieRequestedReplaceMetadata other) {
            super(HoodieRequestedReplaceMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.operationType)) {
                this.operationType = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.operationType);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.clusteringPlan)) {
                this.clusteringPlan = (HoodieClusteringPlan)this.data().deepCopy(this.fields()[1].schema(), (Object)other.clusteringPlan);
                this.fieldSetFlags()[1] = true;
            }
            this.clusteringPlanBuilder = null;
            if (isValidValue(this.fields()[2], (Object)other.extraMetadata)) {
                this.extraMetadata = (Map<String, String>)this.data().deepCopy(this.fields()[2].schema(), (Object)other.extraMetadata);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public String getOperationType() {
            return this.operationType;
        }
        
        public Builder setOperationType(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.operationType = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasOperationType() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearOperationType() {
            this.operationType = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public HoodieClusteringPlan getClusteringPlan() {
            return this.clusteringPlan;
        }
        
        public Builder setClusteringPlan(final HoodieClusteringPlan value) {
            this.validate(this.fields()[1], (Object)value);
            this.clusteringPlanBuilder = null;
            this.clusteringPlan = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasClusteringPlan() {
            return this.fieldSetFlags()[1];
        }
        
        public HoodieClusteringPlan.Builder getClusteringPlanBuilder() {
            if (this.clusteringPlanBuilder == null) {
                if (this.hasClusteringPlan()) {
                    this.setClusteringPlanBuilder(HoodieClusteringPlan.newBuilder(this.clusteringPlan));
                }
                else {
                    this.setClusteringPlanBuilder(HoodieClusteringPlan.newBuilder());
                }
            }
            return this.clusteringPlanBuilder;
        }
        
        public Builder setClusteringPlanBuilder(final HoodieClusteringPlan.Builder value) {
            this.clearClusteringPlan();
            this.clusteringPlanBuilder = value;
            return this;
        }
        
        public boolean hasClusteringPlanBuilder() {
            return this.clusteringPlanBuilder != null;
        }
        
        public Builder clearClusteringPlan() {
            this.clusteringPlan = null;
            this.clusteringPlanBuilder = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Map<String, String> getExtraMetadata() {
            return this.extraMetadata;
        }
        
        public Builder setExtraMetadata(final Map<String, String> value) {
            this.validate(this.fields()[2], (Object)value);
            this.extraMetadata = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasExtraMetadata() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearExtraMetadata() {
            this.extraMetadata = null;
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
        
        public HoodieRequestedReplaceMetadata build() {
            try {
                final HoodieRequestedReplaceMetadata record = new HoodieRequestedReplaceMetadata();
                record.operationType = (String)(this.fieldSetFlags()[0] ? this.operationType : this.defaultValue(this.fields()[0]));
                if (this.clusteringPlanBuilder != null) {
                    record.clusteringPlan = this.clusteringPlanBuilder.build();
                }
                else {
                    record.clusteringPlan = (HoodieClusteringPlan)(this.fieldSetFlags()[1] ? this.clusteringPlan : this.defaultValue(this.fields()[1]));
                }
                record.extraMetadata = (Map<String, String>)(this.fieldSetFlags()[2] ? this.extraMetadata : this.defaultValue(this.fields()[2]));
                record.version = (Integer)(this.fieldSetFlags()[3] ? this.version : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
