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
public class HoodieCompactionOperation extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 1376499005954309214L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieCompactionOperation> ENCODER;
    private static final BinaryMessageDecoder<HoodieCompactionOperation> DECODER;
    @Deprecated
    public String baseInstantTime;
    @Deprecated
    public List<String> deltaFilePaths;
    @Deprecated
    public String dataFilePath;
    @Deprecated
    public String fileId;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public Map<String, Double> metrics;
    @Deprecated
    public String bootstrapFilePath;
    private static final DatumWriter<HoodieCompactionOperation> WRITER$;
    private static final DatumReader<HoodieCompactionOperation> READER$;
    
    public static Schema getClassSchema() {
        return HoodieCompactionOperation.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionOperation> getDecoder() {
        return HoodieCompactionOperation.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieCompactionOperation> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieCompactionOperation>)new BinaryMessageDecoder((GenericData)HoodieCompactionOperation.MODEL$, HoodieCompactionOperation.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieCompactionOperation.ENCODER.encode((HoodieCompactionOperation) this);
    }
    
    public static HoodieCompactionOperation fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieCompactionOperation)HoodieCompactionOperation.DECODER.decode(b);
    }
    
    public HoodieCompactionOperation() {
    }
    
    public HoodieCompactionOperation(final String baseInstantTime, final List<String> deltaFilePaths, final String dataFilePath, final String fileId, final String partitionPath, final Map<String, Double> metrics, final String bootstrapFilePath) {
        this.baseInstantTime = baseInstantTime;
        this.deltaFilePaths = deltaFilePaths;
        this.dataFilePath = dataFilePath;
        this.fileId = fileId;
        this.partitionPath = partitionPath;
        this.metrics = metrics;
        this.bootstrapFilePath = bootstrapFilePath;
    }
    
    public Schema getSchema() {
        return HoodieCompactionOperation.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.baseInstantTime;
            }
            case 1: {
                return this.deltaFilePaths;
            }
            case 2: {
                return this.dataFilePath;
            }
            case 3: {
                return this.fileId;
            }
            case 4: {
                return this.partitionPath;
            }
            case 5: {
                return this.metrics;
            }
            case 6: {
                return this.bootstrapFilePath;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.baseInstantTime = (String)value$;
                break;
            }
            case 1: {
                this.deltaFilePaths = (List<String>)value$;
                break;
            }
            case 2: {
                this.dataFilePath = (String)value$;
                break;
            }
            case 3: {
                this.fileId = (String)value$;
                break;
            }
            case 4: {
                this.partitionPath = (String)value$;
                break;
            }
            case 5: {
                this.metrics = (Map<String, Double>)value$;
                break;
            }
            case 6: {
                this.bootstrapFilePath = (String)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getBaseInstantTime() {
        return this.baseInstantTime;
    }
    
    public void setBaseInstantTime(final String value) {
        this.baseInstantTime = value;
    }
    
    public List<String> getDeltaFilePaths() {
        return this.deltaFilePaths;
    }
    
    public void setDeltaFilePaths(final List<String> value) {
        this.deltaFilePaths = value;
    }
    
    public String getDataFilePath() {
        return this.dataFilePath;
    }
    
    public void setDataFilePath(final String value) {
        this.dataFilePath = value;
    }
    
    public String getFileId() {
        return this.fileId;
    }
    
    public void setFileId(final String value) {
        this.fileId = value;
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public Map<String, Double> getMetrics() {
        return this.metrics;
    }
    
    public void setMetrics(final Map<String, Double> value) {
        this.metrics = value;
    }
    
    public String getBootstrapFilePath() {
        return this.bootstrapFilePath;
    }
    
    public void setBootstrapFilePath(final String value) {
        this.bootstrapFilePath = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieCompactionOperation other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieCompactionOperation.WRITER$.write((HoodieCompactionOperation) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieCompactionOperation.READER$.read((HoodieCompactionOperation) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionOperation\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"baseInstantTime\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodieCompactionOperation.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieCompactionOperation.MODEL$, HoodieCompactionOperation.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieCompactionOperation.MODEL$, HoodieCompactionOperation.SCHEMA$);
        WRITER$ = HoodieCompactionOperation.MODEL$.createDatumWriter(HoodieCompactionOperation.SCHEMA$);
        READER$ = HoodieCompactionOperation.MODEL$.createDatumReader(HoodieCompactionOperation.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieCompactionOperation> implements RecordBuilder<HoodieCompactionOperation>
    {
        private String baseInstantTime;
        private List<String> deltaFilePaths;
        private String dataFilePath;
        private String fileId;
        private String partitionPath;
        private Map<String, Double> metrics;
        private String bootstrapFilePath;
        
        private Builder() {
            super(HoodieCompactionOperation.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.baseInstantTime)) {
                this.baseInstantTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.baseInstantTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.deltaFilePaths)) {
                this.deltaFilePaths = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.deltaFilePaths);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.dataFilePath)) {
                this.dataFilePath = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.dataFilePath);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.fileId);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.metrics)) {
                this.metrics = (Map<String, Double>)this.data().deepCopy(this.fields()[5].schema(), (Object)other.metrics);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.bootstrapFilePath)) {
                this.bootstrapFilePath = (String)this.data().deepCopy(this.fields()[6].schema(), (Object)other.bootstrapFilePath);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        private Builder(final HoodieCompactionOperation other) {
            super(HoodieCompactionOperation.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.baseInstantTime)) {
                this.baseInstantTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.baseInstantTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.deltaFilePaths)) {
                this.deltaFilePaths = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.deltaFilePaths);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.dataFilePath)) {
                this.dataFilePath = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.dataFilePath);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.fileId)) {
                this.fileId = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.fileId);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[4].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.metrics)) {
                this.metrics = (Map<String, Double>)this.data().deepCopy(this.fields()[5].schema(), (Object)other.metrics);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.bootstrapFilePath)) {
                this.bootstrapFilePath = (String)this.data().deepCopy(this.fields()[6].schema(), (Object)other.bootstrapFilePath);
                this.fieldSetFlags()[6] = true;
            }
        }
        
        public String getBaseInstantTime() {
            return this.baseInstantTime;
        }
        
        public Builder setBaseInstantTime(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.baseInstantTime = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasBaseInstantTime() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearBaseInstantTime() {
            this.baseInstantTime = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public List<String> getDeltaFilePaths() {
            return this.deltaFilePaths;
        }
        
        public Builder setDeltaFilePaths(final List<String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.deltaFilePaths = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasDeltaFilePaths() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearDeltaFilePaths() {
            this.deltaFilePaths = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getDataFilePath() {
            return this.dataFilePath;
        }
        
        public Builder setDataFilePath(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.dataFilePath = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasDataFilePath() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearDataFilePath() {
            this.dataFilePath = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public String getFileId() {
            return this.fileId;
        }
        
        public Builder setFileId(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.fileId = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasFileId() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearFileId() {
            this.fileId = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[4], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public Map<String, Double> getMetrics() {
            return this.metrics;
        }
        
        public Builder setMetrics(final Map<String, Double> value) {
            this.validate(this.fields()[5], (Object)value);
            this.metrics = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasMetrics() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearMetrics() {
            this.metrics = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public String getBootstrapFilePath() {
            return this.bootstrapFilePath;
        }
        
        public Builder setBootstrapFilePath(final String value) {
            this.validate(this.fields()[6], (Object)value);
            this.bootstrapFilePath = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasBootstrapFilePath() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearBootstrapFilePath() {
            this.bootstrapFilePath = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public HoodieCompactionOperation build() {
            try {
                final HoodieCompactionOperation record = new HoodieCompactionOperation();
                record.baseInstantTime = (String)(this.fieldSetFlags()[0] ? this.baseInstantTime : this.defaultValue(this.fields()[0]));
                record.deltaFilePaths = (List<String>)(this.fieldSetFlags()[1] ? this.deltaFilePaths : this.defaultValue(this.fields()[1]));
                record.dataFilePath = (String)(this.fieldSetFlags()[2] ? this.dataFilePath : this.defaultValue(this.fields()[2]));
                record.fileId = (String)(this.fieldSetFlags()[3] ? this.fileId : this.defaultValue(this.fields()[3]));
                record.partitionPath = (String)(this.fieldSetFlags()[4] ? this.partitionPath : this.defaultValue(this.fields()[4]));
                record.metrics = (Map<String, Double>)(this.fieldSetFlags()[5] ? this.metrics : this.defaultValue(this.fields()[5]));
                record.bootstrapFilePath = (String)(this.fieldSetFlags()[6] ? this.bootstrapFilePath : this.defaultValue(this.fields()[6]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
