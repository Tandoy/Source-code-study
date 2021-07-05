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
public class HoodieClusteringGroup extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -6298493631461674682L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieClusteringGroup> ENCODER;
    private static final BinaryMessageDecoder<HoodieClusteringGroup> DECODER;
    @Deprecated
    public List<HoodieSliceInfo> slices;
    @Deprecated
    public Map<String, Double> metrics;
    @Deprecated
    public Integer numOutputFileGroups;
    @Deprecated
    public Integer version;
    private static final DatumWriter<HoodieClusteringGroup> WRITER$;
    private static final DatumReader<HoodieClusteringGroup> READER$;
    
    public static Schema getClassSchema() {
        return HoodieClusteringGroup.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringGroup> getDecoder() {
        return HoodieClusteringGroup.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieClusteringGroup> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieClusteringGroup>)new BinaryMessageDecoder((GenericData)HoodieClusteringGroup.MODEL$, HoodieClusteringGroup.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieClusteringGroup.ENCODER.encode((HoodieClusteringGroup) this);
    }
    
    public static HoodieClusteringGroup fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieClusteringGroup)HoodieClusteringGroup.DECODER.decode(b);
    }
    
    public HoodieClusteringGroup() {
    }
    
    public HoodieClusteringGroup(final List<HoodieSliceInfo> slices, final Map<String, Double> metrics, final Integer numOutputFileGroups, final Integer version) {
        this.slices = slices;
        this.metrics = metrics;
        this.numOutputFileGroups = numOutputFileGroups;
        this.version = version;
    }
    
    public Schema getSchema() {
        return HoodieClusteringGroup.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.slices;
            }
            case 1: {
                return this.metrics;
            }
            case 2: {
                return this.numOutputFileGroups;
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
                this.slices = (List<HoodieSliceInfo>)value$;
                break;
            }
            case 1: {
                this.metrics = (Map<String, Double>)value$;
                break;
            }
            case 2: {
                this.numOutputFileGroups = (Integer)value$;
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
    
    public List<HoodieSliceInfo> getSlices() {
        return this.slices;
    }
    
    public void setSlices(final List<HoodieSliceInfo> value) {
        this.slices = value;
    }
    
    public Map<String, Double> getMetrics() {
        return this.metrics;
    }
    
    public void setMetrics(final Map<String, Double> value) {
        this.metrics = value;
    }
    
    public Integer getNumOutputFileGroups() {
        return this.numOutputFileGroups;
    }
    
    public void setNumOutputFileGroups(final Integer value) {
        this.numOutputFileGroups = value;
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
    
    public static Builder newBuilder(final HoodieClusteringGroup other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieClusteringGroup.WRITER$.write((HoodieClusteringGroup) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieClusteringGroup.READER$.read((HoodieClusteringGroup) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}");
        HoodieClusteringGroup.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieClusteringGroup.MODEL$, HoodieClusteringGroup.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieClusteringGroup.MODEL$, HoodieClusteringGroup.SCHEMA$);
        WRITER$ = HoodieClusteringGroup.MODEL$.createDatumWriter(HoodieClusteringGroup.SCHEMA$);
        READER$ = HoodieClusteringGroup.MODEL$.createDatumReader(HoodieClusteringGroup.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieClusteringGroup> implements RecordBuilder<HoodieClusteringGroup>
    {
        private List<HoodieSliceInfo> slices;
        private Map<String, Double> metrics;
        private Integer numOutputFileGroups;
        private Integer version;
        
        private Builder() {
            super(HoodieClusteringGroup.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.slices)) {
                this.slices = (List<HoodieSliceInfo>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.slices);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.metrics)) {
                this.metrics = (Map<String, Double>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.metrics);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.numOutputFileGroups)) {
                this.numOutputFileGroups = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.numOutputFileGroups);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieClusteringGroup other) {
            super(HoodieClusteringGroup.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.slices)) {
                this.slices = (List<HoodieSliceInfo>)this.data().deepCopy(this.fields()[0].schema(), (Object)other.slices);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.metrics)) {
                this.metrics = (Map<String, Double>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.metrics);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.numOutputFileGroups)) {
                this.numOutputFileGroups = (Integer)this.data().deepCopy(this.fields()[2].schema(), (Object)other.numOutputFileGroups);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[3].schema(), (Object)other.version);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public List<HoodieSliceInfo> getSlices() {
            return this.slices;
        }
        
        public Builder setSlices(final List<HoodieSliceInfo> value) {
            this.validate(this.fields()[0], (Object)value);
            this.slices = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasSlices() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearSlices() {
            this.slices = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public Map<String, Double> getMetrics() {
            return this.metrics;
        }
        
        public Builder setMetrics(final Map<String, Double> value) {
            this.validate(this.fields()[1], (Object)value);
            this.metrics = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasMetrics() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearMetrics() {
            this.metrics = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Integer getNumOutputFileGroups() {
            return this.numOutputFileGroups;
        }
        
        public Builder setNumOutputFileGroups(final Integer value) {
            this.validate(this.fields()[2], (Object)value);
            this.numOutputFileGroups = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasNumOutputFileGroups() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearNumOutputFileGroups() {
            this.numOutputFileGroups = null;
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
        
        public HoodieClusteringGroup build() {
            try {
                final HoodieClusteringGroup record = new HoodieClusteringGroup();
                record.slices = (List<HoodieSliceInfo>)(this.fieldSetFlags()[0] ? this.slices : this.defaultValue(this.fields()[0]));
                record.metrics = (Map<String, Double>)(this.fieldSetFlags()[1] ? this.metrics : this.defaultValue(this.fields()[1]));
                record.numOutputFileGroups = (Integer)(this.fieldSetFlags()[2] ? this.numOutputFileGroups : this.defaultValue(this.fields()[2]));
                record.version = (Integer)(this.fieldSetFlags()[3] ? this.version : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
