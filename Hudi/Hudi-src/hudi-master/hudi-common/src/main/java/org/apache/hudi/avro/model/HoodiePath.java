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
public class HoodiePath extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -4483841564785873408L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodiePath> ENCODER;
    private static final BinaryMessageDecoder<HoodiePath> DECODER;
    @Deprecated
    public Integer version;
    @Deprecated
    public String uri;
    private static final DatumWriter<HoodiePath> WRITER$;
    private static final DatumReader<HoodiePath> READER$;
    
    public static Schema getClassSchema() {
        return HoodiePath.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodiePath> getDecoder() {
        return HoodiePath.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodiePath> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodiePath>)new BinaryMessageDecoder((GenericData)HoodiePath.MODEL$, HoodiePath.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodiePath.ENCODER.encode((HoodiePath) this);
    }
    
    public static HoodiePath fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodiePath)HoodiePath.DECODER.decode(b);
    }
    
    public HoodiePath() {
    }
    
    public HoodiePath(final Integer version, final String uri) {
        this.version = version;
        this.uri = uri;
    }
    
    public Schema getSchema() {
        return HoodiePath.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.version;
            }
            case 1: {
                return this.uri;
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
                this.uri = (String)value$;
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
    
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(final String value) {
        this.uri = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodiePath other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodiePath.WRITER$.write((HoodiePath) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodiePath.READER$.read((HoodiePath) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodiePath\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"uri\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
        HoodiePath.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodiePath.MODEL$, HoodiePath.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodiePath.MODEL$, HoodiePath.SCHEMA$);
        WRITER$ = HoodiePath.MODEL$.createDatumWriter(HoodiePath.SCHEMA$);
        READER$ = HoodiePath.MODEL$.createDatumReader(HoodiePath.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodiePath> implements RecordBuilder<HoodiePath>
    {
        private Integer version;
        private String uri;
        
        private Builder() {
            super(HoodiePath.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.uri)) {
                this.uri = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.uri);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        private Builder(final HoodiePath other) {
            super(HoodiePath.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[0].schema(), (Object)other.version);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.uri)) {
                this.uri = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.uri);
                this.fieldSetFlags()[1] = true;
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
        
        public String getUri() {
            return this.uri;
        }
        
        public Builder setUri(final String value) {
            this.validate(this.fields()[1], (Object)value);
            this.uri = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasUri() {
            return this.fieldSetFlags()[1];
        }
        
        public Builder clearUri() {
            this.uri = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodiePath build() {
            try {
                final HoodiePath record = new HoodiePath();
                record.version = (Integer)(this.fieldSetFlags()[0] ? this.version : this.defaultValue(this.fields()[0]));
                record.uri = (String)(this.fieldSetFlags()[1] ? this.uri : this.defaultValue(this.fields()[1]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
