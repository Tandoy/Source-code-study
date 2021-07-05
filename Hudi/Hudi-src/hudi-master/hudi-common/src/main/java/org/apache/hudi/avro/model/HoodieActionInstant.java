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
public class HoodieActionInstant extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = 8235099814567308118L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieActionInstant> ENCODER;
    private static final BinaryMessageDecoder<HoodieActionInstant> DECODER;
    @Deprecated
    public String timestamp;
    @Deprecated
    public String action;
    @Deprecated
    public String state;
    private static final DatumWriter<HoodieActionInstant> WRITER$;
    private static final DatumReader<HoodieActionInstant> READER$;
    
    public static Schema getClassSchema() {
        return HoodieActionInstant.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieActionInstant> getDecoder() {
        return HoodieActionInstant.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieActionInstant> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieActionInstant>)new BinaryMessageDecoder((GenericData)HoodieActionInstant.MODEL$, HoodieActionInstant.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieActionInstant.ENCODER.encode((HoodieActionInstant) this);
    }
    
    public static HoodieActionInstant fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieActionInstant)HoodieActionInstant.DECODER.decode(b);
    }
    
    public HoodieActionInstant() {
    }
    
    public HoodieActionInstant(final String timestamp, final String action, final String state) {
        this.timestamp = timestamp;
        this.action = action;
        this.state = state;
    }
    
    public Schema getSchema() {
        return HoodieActionInstant.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.timestamp;
            }
            case 1: {
                return this.action;
            }
            case 2: {
                return this.state;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.timestamp = (String)value$;
                break;
            }
            case 1: {
                this.action = (String)value$;
                break;
            }
            case 2: {
                this.state = (String)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(final String value) {
        this.timestamp = value;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void setAction(final String value) {
        this.action = value;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(final String value) {
        this.state = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieActionInstant other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieActionInstant.WRITER$.write((HoodieActionInstant) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieActionInstant.READER$.read((HoodieActionInstant) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieActionInstant\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"state\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
        HoodieActionInstant.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieActionInstant.MODEL$, HoodieActionInstant.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieActionInstant.MODEL$, HoodieActionInstant.SCHEMA$);
        WRITER$ = HoodieActionInstant.MODEL$.createDatumWriter(HoodieActionInstant.SCHEMA$);
        READER$ = HoodieActionInstant.MODEL$.createDatumReader(HoodieActionInstant.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieActionInstant> implements RecordBuilder<HoodieActionInstant>
    {
        private String timestamp;
        private String action;
        private String state;
        
        private Builder() {
            super(HoodieActionInstant.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.timestamp)) {
                this.timestamp = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.timestamp);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.action)) {
                this.action = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.action);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.state)) {
                this.state = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.state);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        private Builder(final HoodieActionInstant other) {
            super(HoodieActionInstant.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.timestamp)) {
                this.timestamp = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.timestamp);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.action)) {
                this.action = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.action);
                this.fieldSetFlags()[1] = true;
            }
            if (isValidValue(this.fields()[2], (Object)other.state)) {
                this.state = (String)this.data().deepCopy(this.fields()[2].schema(), (Object)other.state);
                this.fieldSetFlags()[2] = true;
            }
        }
        
        public String getTimestamp() {
            return this.timestamp;
        }
        
        public Builder setTimestamp(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.timestamp = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasTimestamp() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearTimestamp() {
            this.timestamp = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public String getAction() {
            return this.action;
        }
        
        public Builder setAction(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.action = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasAction() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearAction() {
            this.action = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public String getState() {
            return this.state;
        }
        
        public Builder setState(final String value) {
            this.validate(this.fields()[2], (Object)value);
            this.state = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasState() {
            return this.fieldSetFlags()[2];
        }
        
        public Builder clearState() {
            this.state = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public HoodieActionInstant build() {
            try {
                final HoodieActionInstant record = new HoodieActionInstant();
                record.timestamp = (String)(this.fieldSetFlags()[0] ? this.timestamp : this.defaultValue(this.fields()[0]));
                record.action = (String)(this.fieldSetFlags()[1] ? this.action : this.defaultValue(this.fields()[1]));
                record.state = (String)(this.fieldSetFlags()[2] ? this.state : this.defaultValue(this.fields()[2]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
