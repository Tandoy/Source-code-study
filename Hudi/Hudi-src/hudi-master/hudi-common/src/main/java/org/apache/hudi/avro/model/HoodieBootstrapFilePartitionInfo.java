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
public class HoodieBootstrapFilePartitionInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 3264734783275825247L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieBootstrapFilePartitionInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public String bootstrapPartitionPath;
    @Deprecated
    public HoodieFileStatus bootstrapFileStatus;
    @Deprecated
    public String partitionPath;
    private static final DatumWriter<HoodieBootstrapFilePartitionInfo> WRITER$;
    private static final DatumReader<HoodieBootstrapFilePartitionInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieBootstrapFilePartitionInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> getDecoder() {
        return HoodieBootstrapFilePartitionInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieBootstrapFilePartitionInfo>)new BinaryMessageDecoder((GenericData)HoodieBootstrapFilePartitionInfo.MODEL$, HoodieBootstrapFilePartitionInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieBootstrapFilePartitionInfo.ENCODER.encode((HoodieBootstrapFilePartitionInfo) this);
    }
    
    public static HoodieBootstrapFilePartitionInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieBootstrapFilePartitionInfo)HoodieBootstrapFilePartitionInfo.DECODER.decode(b);
    }
    
    public HoodieBootstrapFilePartitionInfo() {
    }
    
    public HoodieBootstrapFilePartitionInfo(final Integer version, final String bootstrapPartitionPath, final HoodieFileStatus bootstrapFileStatus, final String partitionPath) {
        this.version = version;
        this.bootstrapPartitionPath = bootstrapPartitionPath;
        this.bootstrapFileStatus = bootstrapFileStatus;
        this.partitionPath = partitionPath;
    }
    
    public Schema getSchema() {
        return HoodieBootstrapFilePartitionInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.bootstrapPartitionPath;
            }
            case 2: {
                return this.bootstrapFileStatus;
            }
            case 3: {
                return this.partitionPath;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.version = (Integer)value$;
                break;
            }
            case 1: {
                this.bootstrapPartitionPath = (String)value$;
                break;
            }
            case 2: {
                this.bootstrapFileStatus = (HoodieFileStatus)value$;
                break;
            }
            case 3: {
                this.partitionPath = (String)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public String getBootstrapPartitionPath() {
        return this.bootstrapPartitionPath;
    }
    
    public void setBootstrapPartitionPath(final String value) {
        this.bootstrapPartitionPath = value;
    }
    
    public HoodieFileStatus getBootstrapFileStatus() {
        return this.bootstrapFileStatus;
    }
    
    public void setBootstrapFileStatus(final HoodieFileStatus value) {
        this.bootstrapFileStatus = value;
    }
    
    public String getPartitionPath() {
        return this.partitionPath;
    }
    
    public void setPartitionPath(final String value) {
        this.partitionPath = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieBootstrapFilePartitionInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieBootstrapFilePartitionInfo.WRITER$.write((HoodieBootstrapFilePartitionInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieBootstrapFilePartitionInfo.READER$.read((HoodieBootstrapFilePartitionInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieBootstrapFilePartitionInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFileStatus\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFileStatus\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodiePath\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"uri\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"length\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"isDir\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"blockReplication\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"blockSize\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"modificationTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"accessTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"permission\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFSPermission\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"userAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"groupAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"otherAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"stickyBit\",\"type\":[\"null\",\"boolean\"],\"default\":\"null\"}]}],\"default\":null},{\"name\":\"owner\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"group\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"symlink\",\"type\":[\"null\",\"HoodiePath\"],\"default\":null}]}],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodieBootstrapFilePartitionInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieBootstrapFilePartitionInfo.MODEL$, HoodieBootstrapFilePartitionInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieBootstrapFilePartitionInfo.MODEL$, HoodieBootstrapFilePartitionInfo.SCHEMA$);
        WRITER$ = HoodieBootstrapFilePartitionInfo.MODEL$.createDatumWriter(HoodieBootstrapFilePartitionInfo.SCHEMA$);
        READER$ = HoodieBootstrapFilePartitionInfo.MODEL$.createDatumReader(HoodieBootstrapFilePartitionInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieBootstrapFilePartitionInfo> implements RecordBuilder<HoodieBootstrapFilePartitionInfo>
    {
        private Integer version;
        private String bootstrapPartitionPath;
        private HoodieFileStatus bootstrapFileStatus;
        private HoodieFileStatus.Builder bootstrapFileStatusBuilder;
        private String partitionPath;
        
        private Builder() {
            super(HoodieBootstrapFilePartitionInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapPartitionPath)) {
                this.bootstrapPartitionPath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapPartitionPath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.bootstrapFileStatus)) {
                this.bootstrapFileStatus = (HoodieFileStatus)this.data().deepCopy(this.fields()[2].schema(), (Object)other.bootstrapFileStatus);
                this.fieldSetFlags()[2] = true;
            }
            if (other.hasBootstrapFileStatusBuilder()) {
                this.bootstrapFileStatusBuilder = HoodieFileStatus.newBuilder(other.getBootstrapFileStatusBuilder());
            }
            if (isValidValue(this.fields()[3], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        private Builder(final HoodieBootstrapFilePartitionInfo other) {
            super(HoodieBootstrapFilePartitionInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.bootstrapPartitionPath)) {
                this.bootstrapPartitionPath = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.bootstrapPartitionPath);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.bootstrapFileStatus)) {
                this.bootstrapFileStatus = (HoodieFileStatus)this.data().deepCopy(this.fields()[2].schema(), (Object)other.bootstrapFileStatus);
                this.fieldSetFlags()[2] = true;
            }
            this.bootstrapFileStatusBuilder = null;
            if (isValidValue(this.fields()[3], (Object)other.partitionPath)) {
                this.partitionPath = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.partitionPath);
                this.fieldSetFlags()[3] = true;
            }
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[0], (Object)value);
            this.version = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public String getBootstrapPartitionPath() {
            return this.bootstrapPartitionPath;
        }
        
        public Builder setBootstrapPartitionPath(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.bootstrapPartitionPath = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasBootstrapPartitionPath() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearBootstrapPartitionPath() {
            this.bootstrapPartitionPath = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodieFileStatus getBootstrapFileStatus() {
            return this.bootstrapFileStatus;
        }
        
        public Builder setBootstrapFileStatus(final HoodieFileStatus value) {
            this.validate(this.fields()[2], (Object)value);
            this.bootstrapFileStatusBuilder = null;
            this.bootstrapFileStatus = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasBootstrapFileStatus() {
            return this.fieldSetFlags()[2];
        }
        
        public HoodieFileStatus.Builder getBootstrapFileStatusBuilder() {
            if (this.bootstrapFileStatusBuilder == null) {
                if (this.hasBootstrapFileStatus()) {
                    this.setBootstrapFileStatusBuilder(HoodieFileStatus.newBuilder(this.bootstrapFileStatus));
                }
                else {
                    this.setBootstrapFileStatusBuilder(HoodieFileStatus.newBuilder());
                }
            }
            return this.bootstrapFileStatusBuilder;
        }
        
        public Builder setBootstrapFileStatusBuilder(final HoodieFileStatus.Builder value) {
            this.clearBootstrapFileStatus();
            this.bootstrapFileStatusBuilder = value;
            return this;
        }
        
        public boolean hasBootstrapFileStatusBuilder() {
            return this.bootstrapFileStatusBuilder != null;
        }
        
        public Builder clearBootstrapFileStatus() {
            this.bootstrapFileStatus = null;
            this.bootstrapFileStatusBuilder = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public String getPartitionPath() {
            return this.partitionPath;
        }
        
        public Builder setPartitionPath(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.partitionPath = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasPartitionPath() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearPartitionPath() {
            this.partitionPath = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieBootstrapFilePartitionInfo build() {
            try {
                final HoodieBootstrapFilePartitionInfo record = new HoodieBootstrapFilePartitionInfo();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                record.bootstrapPartitionPath = (String)(this.fieldSetFlags()[1] ? this.bootstrapPartitionPath : this.defaultValue(this.fields()[1]));
                if (this.bootstrapFileStatusBuilder != null) {
                    record.bootstrapFileStatus = this.bootstrapFileStatusBuilder.build();
                }
                else {
                    record.bootstrapFileStatus = (HoodieFileStatus)(this.fieldSetFlags()[2] ? this.bootstrapFileStatus : this.defaultValue(this.fields()[2]));
                }
                record.partitionPath = (String)(this.fieldSetFlags()[3] ? this.partitionPath : this.defaultValue(this.fields()[3]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
