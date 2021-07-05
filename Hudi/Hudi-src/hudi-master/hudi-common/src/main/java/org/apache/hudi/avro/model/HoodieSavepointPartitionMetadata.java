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
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.Schema;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

@AvroGenerated
public class HoodieSavepointPartitionMetadata extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 6742396350755043336L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieSavepointPartitionMetadata> ENCODER;
    private static final BinaryMessageDecoder<HoodieSavepointPartitionMetadata> DECODER;
    @Deprecated
    public String partitionPath;
    @Deprecated
    public List<String> savepointDataFile;
    private static final DatumWriter<HoodieSavepointPartitionMetadata> WRITER$;
    private static final DatumReader<HoodieSavepointPartitionMetadata> READER$;
    
    public static Schema getClassSchema() {
        return HoodieSavepointPartitionMetadata.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieSavepointPartitionMetadata> getDecoder() {
        return HoodieSavepointPartitionMetadata.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieSavepointPartitionMetadata> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieSavepointPartitionMetadata>)new BinaryMessageDecoder((GenericData)HoodieSavepointPartitionMetadata.MODEL$, HoodieSavepointPartitionMetadata.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieSavepointPartitionMetadata.ENCODER.encode((HoodieSavepointPartitionMetadata) this);
    }
    
    public static HoodieSavepointPartitionMetadata fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieSavepointPartitionMetadata)HoodieSavepointPartitionMetadata.DECODER.decode(b);
    }
    
    public HoodieSavepointPartitionMetadata() {
    }
    
    public HoodieSavepointPartitionMetadata(final String partitionPath, final List<String> savepointDataFile) {
        this.partitionPath = partitionPath;
        this.savepointDataFile = savepointDataFile;
    }
    
    public Schema getSchema() {
        return HoodieSavepointPartitionMetadata.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.partitionPath;
            }
            case 1: {
                return this.savepointDataFile;
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
                this.savepointDataFile = (List<String>)value$;
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
    
    public List<String> getSavepointDataFile() {
        return this.savepointDataFile;
    }
    
    public void setSavepointDataFile(final List<String> value) {
        this.savepointDataFile = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieSavepointPartitionMetadata other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieSavepointPartitionMetadata.WRITER$.write((HoodieSavepointPartitionMetadata) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieSavepointPartitionMetadata.READER$.read((HoodieSavepointPartitionMetadata) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieSavepointPartitionMetadata\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"savepointDataFile\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");
        HoodieSavepointPartitionMetadata.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieSavepointPartitionMetadata.MODEL$, HoodieSavepointPartitionMetadata.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieSavepointPartitionMetadata.MODEL$, HoodieSavepointPartitionMetadata.SCHEMA$);
        WRITER$ = HoodieSavepointPartitionMetadata.MODEL$.createDatumWriter(HoodieSavepointPartitionMetadata.SCHEMA$);
        READER$ = HoodieSavepointPartitionMetadata.MODEL$.createDatumReader(HoodieSavepointPartitionMetadata.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieSavepointPartitionMetadata> implements RecordBuilder<HoodieSavepointPartitionMetadata>
    {
        private String partitionPath;
        private List<String> savepointDataFile;
        
        private Builder() {
            super(HoodieSavepointPartitionMetadata.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.savepointDataFile)) {
                this.savepointDataFile = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.savepointDataFile);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        private Builder(final HoodieSavepointPartitionMetadata other) {
            super(HoodieSavepointPartitionMetadata.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.savepointDataFile)) {
                this.savepointDataFile = (List<String>)this.data().deepCopy(this.fields()[1].schema(), (Object)other.savepointDataFile);
                this.fieldSetFlags()[1] = true;
            }
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
        
        public List<String> getSavepointDataFile() {
            return this.savepointDataFile;
        }
        
        public Builder setSavepointDataFile(final List<String> value) {
            this.validate(this.fields()[1], (Object)value);
            this.savepointDataFile = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasSavepointDataFile() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearSavepointDataFile() {
            this.savepointDataFile = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodieSavepointPartitionMetadata build() {
            try {
                final HoodieSavepointPartitionMetadata record = new HoodieSavepointPartitionMetadata();
                record.partitionPath = (String)(this.fieldSetFlags()[0] ? this.partitionPath : this.defaultValue(this.fields()[0]));
                record.savepointDataFile = (List<String>)(this.fieldSetFlags()[1] ? this.savepointDataFile : this.defaultValue(this.fields()[1]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
