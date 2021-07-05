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
public class HoodieFSPermission extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -3946434119916465777L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieFSPermission> ENCODER;
    private static final BinaryMessageDecoder<HoodieFSPermission> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public String userAction;
    @Deprecated
    public String groupAction;
    @Deprecated
    public String otherAction;
    @Deprecated
    public Boolean stickyBit;
    private static final DatumWriter<HoodieFSPermission> WRITER$;
    private static final DatumReader<HoodieFSPermission> READER$;
    
    public static Schema getClassSchema() {
        return HoodieFSPermission.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieFSPermission> getDecoder() {
        return HoodieFSPermission.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieFSPermission> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieFSPermission>)new BinaryMessageDecoder((GenericData)HoodieFSPermission.MODEL$, HoodieFSPermission.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieFSPermission.ENCODER.encode((HoodieFSPermission) this);
    }
    
    public static HoodieFSPermission fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieFSPermission)HoodieFSPermission.DECODER.decode(b);
    }
    
    public HoodieFSPermission() {
    }
    
    public HoodieFSPermission(final Integer version, final String userAction, final String groupAction, final String otherAction, final Boolean stickyBit) {
        this.version = version;
        this.userAction = userAction;
        this.groupAction = groupAction;
        this.otherAction = otherAction;
        this.stickyBit = stickyBit;
    }
    
    public Schema getSchema() {
        return HoodieFSPermission.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.userAction;
            }
            case 2: {
                return this.groupAction;
            }
            case 3: {
                return this.otherAction;
            }
            case 4: {
                return this.stickyBit;
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
                this.userAction = (String)value$;
                break;
            }
            case 2: {
                this.groupAction = (String)value$;
                break;
            }
            case 3: {
                this.otherAction = (String)value$;
                break;
            }
            case 4: {
                this.stickyBit = (Boolean)value$;
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
    
    public String getUserAction() {
        return this.userAction;
    }
    
    public void setUserAction(final String value) {
        this.userAction = value;
    }
    
    public String getGroupAction() {
        return this.groupAction;
    }
    
    public void setGroupAction(final String value) {
        this.groupAction = value;
    }
    
    public String getOtherAction() {
        return this.otherAction;
    }
    
    public void setOtherAction(final String value) {
        this.otherAction = value;
    }
    
    public Boolean getStickyBit() {
        return this.stickyBit;
    }
    
    public void setStickyBit(final Boolean value) {
        this.stickyBit = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieFSPermission other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieFSPermission.WRITER$.write((HoodieFSPermission) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieFSPermission.READER$.read((HoodieFSPermission) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieFSPermission\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"userAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"groupAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"otherAction\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"null\"},{\"name\":\"stickyBit\",\"type\":[\"null\",\"boolean\"],\"default\":\"null\"}]}");
        HoodieFSPermission.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieFSPermission.MODEL$, HoodieFSPermission.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieFSPermission.MODEL$, HoodieFSPermission.SCHEMA$);
        WRITER$ = HoodieFSPermission.MODEL$.createDatumWriter(HoodieFSPermission.SCHEMA$);
        READER$ = HoodieFSPermission.MODEL$.createDatumReader(HoodieFSPermission.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieFSPermission> implements RecordBuilder<HoodieFSPermission>
    {
        private Integer version;
        private String userAction;
        private String groupAction;
        private String otherAction;
        private Boolean stickyBit;
        
        private Builder() {
            super(HoodieFSPermission.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.userAction)) {
                this.userAction = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.userAction);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.groupAction)) {
                this.groupAction = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.groupAction);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.otherAction)) {
                this.otherAction = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.otherAction);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.stickyBit)) {
                this.stickyBit = (Boolean)this.data().deepCopy(this.fields()[4].schema(), (Object)other.stickyBit);
                this.fieldSetFlags()[4] = true;
            }
        }
        
        private Builder(final HoodieFSPermission other) {
            super(HoodieFSPermission.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.userAction)) {
                this.userAction = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.userAction);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.groupAction)) {
                this.groupAction = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.groupAction);
                this.fieldSetFlags()[2] = true;
            }
            if (isValidValue(this.fields()[3], (Object)other.otherAction)) {
                this.otherAction = (String)this.data().deepCopy(this.fields()[3].schema(), (Object)other.otherAction);
                this.fieldSetFlags()[3] = true;
            }
            if (isValidValue(this.fields()[4], (Object)other.stickyBit)) {
                this.stickyBit = (Boolean)this.data().deepCopy(this.fields()[4].schema(), (Object)other.stickyBit);
                this.fieldSetFlags()[4] = true;
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
        
        public String getUserAction() {
            return this.userAction;
        }
        
        public Builder setUserAction(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.userAction = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasUserAction() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearUserAction() {
            this.userAction = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getGroupAction() {
            return this.groupAction;
        }
        
        public Builder setGroupAction(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.groupAction = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasGroupAction() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearGroupAction() {
            this.groupAction = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public String getOtherAction() {
            return this.otherAction;
        }
        
        public Builder setOtherAction(final String value) {
            this.validate(this.fields()[3], (Object)value);
            this.otherAction = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasOtherAction() {
            return this.fieldSetFlags()[3];
        }
        
        public Builder clearOtherAction() {
            this.otherAction = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public Boolean getStickyBit() {
            return this.stickyBit;
        }
        
        public Builder setStickyBit(final Boolean value) {
            this.validate(this.fields()[4], (Object)value);
            this.stickyBit = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasStickyBit() {
            return this.fieldSetFlags()[4];
        }
        
        public Builder clearStickyBit() {
            this.stickyBit = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public HoodieFSPermission build() {
            try {
                final HoodieFSPermission record = new HoodieFSPermission();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                record.userAction = (String)(this.fieldSetFlags()[1] ? this.userAction : this.defaultValue(this.fields()[1]));
                record.groupAction = (String)(this.fieldSetFlags()[2] ? this.groupAction : this.defaultValue(this.fields()[2]));
                record.otherAction = (String)(this.fieldSetFlags()[3] ? this.otherAction : this.defaultValue(this.fields()[3]));
                record.stickyBit = (Boolean)(this.fieldSetFlags()[4] ? this.stickyBit : this.defaultValue(this.fields()[4]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
