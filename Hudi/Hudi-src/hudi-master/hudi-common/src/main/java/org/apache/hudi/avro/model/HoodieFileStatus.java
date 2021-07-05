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
public class HoodieFileStatus extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -3159966031300103229L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieFileStatus> ENCODER;
    private static final BinaryMessageDecoder<HoodieFileStatus> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public HoodiePath path;
    @Deprecated
    public Long length;
    @Deprecated
    public Boolean isDir;
    @Deprecated
    public Integer blockReplication;
    @Deprecated
    public Long blockSize;
    @Deprecated
    public Long modificationTime;
    @Deprecated
    public Long accessTime;
    @Deprecated
    public HoodieFSPermission permission;
    @Deprecated
    public String owner;
    @Deprecated
    public String group;
    @Deprecated
    public HoodiePath symlink;
    private static final DatumWriter<HoodieFileStatus> WRITER$;
    private static final DatumReader<HoodieFileStatus> READER$;
    
    public static Schema getClassSchema() {
        return HoodieFileStatus.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieFileStatus> getDecoder() {
        return HoodieFileStatus.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieFileStatus> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieFileStatus>)new BinaryMessageDecoder((GenericData)HoodieFileStatus.MODEL$, HoodieFileStatus.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieFileStatus.ENCODER.encode((HoodieFileStatus) this);
    }
    
    public static HoodieFileStatus fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieFileStatus)HoodieFileStatus.DECODER.decode(b);
    }
    
    public HoodieFileStatus() {
    }
    
    public HoodieFileStatus(final Integer version, final HoodiePath path, final Long length, final Boolean isDir, final Integer blockReplication, final Long blockSize, final Long modificationTime, final Long accessTime, final HoodieFSPermission permission, final String owner, final String group, final HoodiePath symlink) {
        this.version = version;
        this.path = path;
        this.length = length;
        this.isDir = isDir;
        this.blockReplication = blockReplication;
        this.blockSize = blockSize;
        this.modificationTime = modificationTime;
        this.accessTime = accessTime;
        this.permission = permission;
        this.owner = owner;
        this.group = group;
        this.symlink = symlink;
    }
    
    public Schema getSchema() {
        return HoodieFileStatus.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.path;
            }
            case 2: {
                return this.length;
            }
            case 3: {
                return this.isDir;
            }
            case 4: {
                return this.blockReplication;
            }
            case 5: {
                return this.blockSize;
            }
            case 6: {
                return this.modificationTime;
            }
            case 7: {
                return this.accessTime;
            }
            case 8: {
                return this.permission;
            }
            case 9: {
                return this.owner;
            }
            case 10: {
                return this.group;
            }
            case 11: {
                return this.symlink;
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
                this.path = (HoodiePath)value$;
                break;
            }
            case 2: {
                this.length = (Long)value$;
                break;
            }
            case 3: {
                this.isDir = (Boolean)value$;
                break;
            }
            case 4: {
                this.blockReplication = (Integer)value$;
                break;
            }
            case 5: {
                this.blockSize = (Long)value$;
                break;
            }
            case 6: {
                this.modificationTime = (Long)value$;
                break;
            }
            case 7: {
                this.accessTime = (Long)value$;
                break;
            }
            case 8: {
                this.permission = (HoodieFSPermission)value$;
                break;
            }
            case 9: {
                this.owner = (String)value$;
                break;
            }
            case 10: {
                this.group = (String)value$;
                break;
            }
            case 11: {
                this.symlink = (HoodiePath)value$;
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
    
    public HoodiePath getPath() {
        return this.path;
    }
    
    public void setPath(final HoodiePath value) {
        this.path = value;
    }
    
    public Long getLength() {
        return this.length;
    }
    
    public void setLength(final Long value) {
        this.length = value;
    }
    
    public Boolean getIsDir() {
        return this.isDir;
    }
    
    public void setIsDir(final Boolean value) {
        this.isDir = value;
    }
    
    public Integer getBlockReplication() {
        return this.blockReplication;
    }
    
    public void setBlockReplication(final Integer value) {
        this.blockReplication = value;
    }
    
    public Long getBlockSize() {
        return this.blockSize;
    }
    
    public void setBlockSize(final Long value) {
        this.blockSize = value;
    }
    
    public Long getModificationTime() {
        return this.modificationTime;
    }
    
    public void setModificationTime(final Long value) {
        this.modificationTime = value;
    }
    
    public Long getAccessTime() {
        return this.accessTime;
    }
    
    public void setAccessTime(final Long value) {
        this.accessTime = value;
    }
    
    public HoodieFSPermission getPermission() {
        return this.permission;
    }
    
    public void setPermission(final HoodieFSPermission value) {
        this.permission = value;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(final String value) {
        this.owner = value;
    }
    
    public String getGroup() {
        return this.group;
    }
    
    public void setGroup(final String value) {
        this.group = value;
    }
    
    public HoodiePath getSymlink() {
        return this.symlink;
    }
    
    public void setSymlink(final HoodiePath value) {
        this.symlink = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieFileStatus other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieFileStatus.WRITER$.write((HoodieFileStatus) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieFileStatus.READER$.read((HoodieFileStatus) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieFileStatus\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodiePath\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"uri\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"length\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"isDir\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"blockReplication\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"blockSize\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"modificationTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"accessTime\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"permission\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieFSPermission\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"userAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"groupAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"otherAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"stickyBit\",\"type\":[\"null\",\"boolean\"],\"default\":\"null\"}]}],\"default\":null},{\"name\":\"owner\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"group\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"symlink\",\"type\":[\"null\",\"HoodiePath\"],\"default\":null}]}");
        HoodieFileStatus.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieFileStatus.MODEL$, HoodieFileStatus.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieFileStatus.MODEL$, HoodieFileStatus.SCHEMA$);
        WRITER$ = HoodieFileStatus.MODEL$.createDatumWriter(HoodieFileStatus.SCHEMA$);
        READER$ = HoodieFileStatus.MODEL$.createDatumReader(HoodieFileStatus.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieFileStatus> implements RecordBuilder<HoodieFileStatus>
    {
        private Integer version;
        private HoodiePath path;
        private HoodiePath.Builder pathBuilder;
        private Long length;
        private Boolean isDir;
        private Integer blockReplication;
        private Long blockSize;
        private Long modificationTime;
        private Long accessTime;
        private HoodieFSPermission permission;
        private HoodieFSPermission.Builder permissionBuilder;
        private String owner;
        private String group;
        private HoodiePath symlink;
        private HoodiePath.Builder symlinkBuilder;
        
        private Builder() {
            super(HoodieFileStatus.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.path)) {
                this.path = (HoodiePath)this.data().deepCopy(this.fields()[1].schema(), (Object)other.path);
                this.fieldSetFlags()[1] = true;
            }
            if (other.hasPathBuilder()) {
                this.pathBuilder = HoodiePath.newBuilder(other.getPathBuilder());
            }
            if (isValidValue(this.fields()[2], (Object)other.length)) {
                this.length = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.length);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.isDir)) {
                this.isDir = (Boolean)this.data().deepCopy(this.fields()[3].schema(), (Object)other.isDir);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.blockReplication)) {
                this.blockReplication = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.blockReplication);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.blockSize)) {
                this.blockSize = (Long)this.data().deepCopy(this.fields()[5].schema(), (Object)other.blockSize);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.modificationTime)) {
                this.modificationTime = (Long)this.data().deepCopy(this.fields()[6].schema(), (Object)other.modificationTime);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.accessTime)) {
                this.accessTime = (Long)this.data().deepCopy(this.fields()[7].schema(), (Object)other.accessTime);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.permission)) {
                this.permission = (HoodieFSPermission)this.data().deepCopy(this.fields()[8].schema(), (Object)other.permission);
                this.fieldSetFlags()[8] = true;
            }
            if (other.hasPermissionBuilder()) {
                this.permissionBuilder = HoodieFSPermission.newBuilder(other.getPermissionBuilder());
            }
            if (isValidValue(this.fields()[9], (Object)other.owner)) {
                this.owner = (String)this.data().deepCopy(this.fields()[9].schema(), (Object)other.owner);
                this.fieldSetFlags()[9] = true;
            }
            if (isValidValue(this.fields()[10], (Object)other.group)) {
                this.group = (String)this.data().deepCopy(this.fields()[10].schema(), (Object)other.group);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.symlink)) {
                this.symlink = (HoodiePath)this.data().deepCopy(this.fields()[11].schema(), (Object)other.symlink);
                this.fieldSetFlags()[11] = true;
            }
            if (other.hasSymlinkBuilder()) {
                this.symlinkBuilder = HoodiePath.newBuilder(other.getSymlinkBuilder());
            }
        }
        
        private Builder(final HoodieFileStatus other) {
            super(HoodieFileStatus.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.path)) {
                this.path = (HoodiePath)this.data().deepCopy(this.fields()[1].schema(), (Object)other.path);
                this.fieldSetFlags()[1] = true;
            }
            this.pathBuilder = null;
            if (isValidValue(this.fields()[2], (Object)other.length)) {
                this.length = (Long)this.data().deepCopy(this.fields()[2].schema(), (Object)other.length);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.isDir)) {
                this.isDir = (Boolean)this.data().deepCopy(this.fields()[3].schema(), (Object)other.isDir);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.blockReplication)) {
                this.blockReplication = (Integer)this.data().deepCopy(this.fields()[4].schema(), (Object)other.blockReplication);
                this.fieldSetFlags()[4] = true;
            }
            if (isValidValue(this.fields()[5], (Object)other.blockSize)) {
                this.blockSize = (Long)this.data().deepCopy(this.fields()[5].schema(), (Object)other.blockSize);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.modificationTime)) {
                this.modificationTime = (Long)this.data().deepCopy(this.fields()[6].schema(), (Object)other.modificationTime);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.accessTime)) {
                this.accessTime = (Long)this.data().deepCopy(this.fields()[7].schema(), (Object)other.accessTime);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.permission)) {
                this.permission = (HoodieFSPermission)this.data().deepCopy(this.fields()[8].schema(), (Object)other.permission);
                this.fieldSetFlags()[8] = true;
            }
            this.permissionBuilder = null;
            if (isValidValue(this.fields()[9], (Object)other.owner)) {
                this.owner = (String)this.data().deepCopy(this.fields()[9].schema(), (Object)other.owner);
                this.fieldSetFlags()[9] = true;
            }
            if (isValidValue(this.fields()[10], (Object)other.group)) {
                this.group = (String)this.data().deepCopy(this.fields()[10].schema(), (Object)other.group);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.symlink)) {
                this.symlink = (HoodiePath)this.data().deepCopy(this.fields()[11].schema(), (Object)other.symlink);
                this.fieldSetFlags()[11] = true;
            }
            this.symlinkBuilder = null;
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
        
        public HoodiePath getPath() {
            return this.path;
        }
        
        public Builder setPath(final HoodiePath value) {
            this.validate(this.fields()[1], (Object)value);
            this.pathBuilder = null;
            this.path = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasPath() {
            return this.fieldSetFlags()[1];
        }
        
        public HoodiePath.Builder getPathBuilder() {
            if (this.pathBuilder == null) {
                if (this.hasPath()) {
                    this.setPathBuilder(HoodiePath.newBuilder(this.path));
                }
                else {
                    this.setPathBuilder(HoodiePath.newBuilder());
                }
            }
            return this.pathBuilder;
        }
        
        public Builder setPathBuilder(final HoodiePath.Builder value) {
            this.clearPath();
            this.pathBuilder = value;
            return this;
        }
        
        public boolean hasPathBuilder() {
            return this.pathBuilder != null;
        }
        
        public Builder clearPath() {
            this.path = null;
            this.pathBuilder = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public Long getLength() {
            return this.length;
        }
        
        public Builder setLength(final Long value) {
            this.validate(this.fields()[2], (Object)value);
            this.length = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasLength() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearLength() {
            this.length = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public Boolean getIsDir() {
            return this.isDir;
        }
        
        public Builder setIsDir(final Boolean value) {
            this.validate(this.fields()[3], (Object)value);
            this.isDir = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasIsDir() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearIsDir() {
            this.isDir = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Integer getBlockReplication() {
            return this.blockReplication;
        }
        
        public Builder setBlockReplication(final Integer value) {
            this.validate(this.fields()[4], (Object)value);
            this.blockReplication = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasBlockReplication() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearBlockReplication() {
            this.blockReplication = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public Long getBlockSize() {
            return this.blockSize;
        }
        
        public Builder setBlockSize(final Long value) {
            this.validate(this.fields()[5], (Object)value);
            this.blockSize = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasBlockSize() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearBlockSize() {
            this.blockSize = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public Long getModificationTime() {
            return this.modificationTime;
        }
        
        public Builder setModificationTime(final Long value) {
            this.validate(this.fields()[6], (Object)value);
            this.modificationTime = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasModificationTime() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearModificationTime() {
            this.modificationTime = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public Long getAccessTime() {
            return this.accessTime;
        }
        
        public Builder setAccessTime(final Long value) {
            this.validate(this.fields()[7], (Object)value);
            this.accessTime = value;
            this.fieldSetFlags()[7] = true;
            return this;
        }
        
        public boolean hasAccessTime() {
            return this.fieldSetFlags()[7];
        }
        
        public Builder clearAccessTime() {
            this.accessTime = null;
            this.fieldSetFlags()[7] = false;
            return this;
        }
        
        public HoodieFSPermission getPermission() {
            return this.permission;
        }
        
        public Builder setPermission(final HoodieFSPermission value) {
            this.validate(this.fields()[8], (Object)value);
            this.permissionBuilder = null;
            this.permission = value;
            this.fieldSetFlags()[8] = true;
            return this;
        }
        
        public boolean hasPermission() {
            return this.fieldSetFlags()[8];
        }
        
        public HoodieFSPermission.Builder getPermissionBuilder() {
            if (this.permissionBuilder == null) {
                if (this.hasPermission()) {
                    this.setPermissionBuilder(HoodieFSPermission.newBuilder(this.permission));
                }
                else {
                    this.setPermissionBuilder(HoodieFSPermission.newBuilder());
                }
            }
            return this.permissionBuilder;
        }
        
        public Builder setPermissionBuilder(final HoodieFSPermission.Builder value) {
            this.clearPermission();
            this.permissionBuilder = value;
            return this;
        }
        
        public boolean hasPermissionBuilder() {
            return this.permissionBuilder != null;
        }
        
        public Builder clearPermission() {
            this.permission = null;
            this.permissionBuilder = null;
            this.fieldSetFlags()[8] = false;
            return this;
        }
        
        public String getOwner() {
            return this.owner;
        }
        
        public Builder setOwner(final String value) {
            this.validate(this.fields()[9], (Object)value);
            this.owner = value;
            this.fieldSetFlags()[9] = true;
            return this;
        }
        
        public boolean hasOwner() {
            return this.fieldSetFlags()[9];
        }
        
        public Builder clearOwner() {
            this.owner = null;
            this.fieldSetFlags()[9] = false;
            return this;
        }
        
        public String getGroup() {
            return this.group;
        }
        
        public Builder setGroup(final String value) {
            this.validate(this.fields()[10], (Object)value);
            this.group = value;
            this.fieldSetFlags()[10] = true;
            return this;
        }
        
        public boolean hasGroup() {
            return this.fieldSetFlags()[10];
        }
        
        public Builder clearGroup() {
            this.group = null;
            this.fieldSetFlags()[10] = false;
            return this;
        }
        
        public HoodiePath getSymlink() {
            return this.symlink;
        }
        
        public Builder setSymlink(final HoodiePath value) {
            this.validate(this.fields()[11], (Object)value);
            this.symlinkBuilder = null;
            this.symlink = value;
            this.fieldSetFlags()[11] = true;
            return this;
        }
        
        public boolean hasSymlink() {
            return this.fieldSetFlags()[11];
        }
        
        public HoodiePath.Builder getSymlinkBuilder() {
            if (this.symlinkBuilder == null) {
                if (this.hasSymlink()) {
                    this.setSymlinkBuilder(HoodiePath.newBuilder(this.symlink));
                }
                else {
                    this.setSymlinkBuilder(HoodiePath.newBuilder());
                }
            }
            return this.symlinkBuilder;
        }
        
        public Builder setSymlinkBuilder(final HoodiePath.Builder value) {
            this.clearSymlink();
            this.symlinkBuilder = value;
            return this;
        }
        
        public boolean hasSymlinkBuilder() {
            return this.symlinkBuilder != null;
        }
        
        public Builder clearSymlink() {
            this.symlink = null;
            this.symlinkBuilder = null;
            this.fieldSetFlags()[11] = false;
            return this;
        }
        
        public HoodieFileStatus build() {
            try {
                final HoodieFileStatus record = new HoodieFileStatus();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                if (this.pathBuilder != null) {
                    record.path = this.pathBuilder.build();
                }
                else {
                    record.path = (HoodiePath)(this.fieldSetFlags()[1] ? this.path : this.defaultValue(this.fields()[1]));
                }
                record.length = (Long)(this.fieldSetFlags()[2] ? this.length : this.defaultValue(this.fields()[2]));
                record.isDir = (Boolean)(this.fieldSetFlags()[3] ? this.isDir : this.defaultValue(this.fields()[3]));
                record.blockReplication = (Integer)(this.fieldSetFlags()[4] ? this.blockReplication : this.defaultValue(this.fields()[4]));
                record.blockSize = (Long)(this.fieldSetFlags()[5] ? this.blockSize : this.defaultValue(this.fields()[5]));
                record.modificationTime = (Long)(this.fieldSetFlags()[6] ? this.modificationTime : this.defaultValue(this.fields()[6]));
                record.accessTime = (Long)(this.fieldSetFlags()[7] ? this.accessTime : this.defaultValue(this.fields()[7]));
                if (this.permissionBuilder != null) {
                    record.permission = this.permissionBuilder.build();
                }
                else {
                    record.permission = (HoodieFSPermission)(this.fieldSetFlags()[8] ? this.permission : this.defaultValue(this.fields()[8]));
                }
                record.owner = (String)(this.fieldSetFlags()[9] ? this.owner : this.defaultValue(this.fields()[9]));
                record.group = (String)(this.fieldSetFlags()[10] ? this.group : this.defaultValue(this.fields()[10]));
                if (this.symlinkBuilder != null) {
                    record.symlink = this.symlinkBuilder.build();
                }
                else {
                    record.symlink = (HoodiePath)(this.fieldSetFlags()[11] ? this.symlink : this.defaultValue(this.fields()[11]));
                }
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
