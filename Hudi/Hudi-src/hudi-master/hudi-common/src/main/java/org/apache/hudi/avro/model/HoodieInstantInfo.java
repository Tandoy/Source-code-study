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
public class HoodieInstantInfo extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -8004479700626631693L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieInstantInfo> ENCODER;
    private static final BinaryMessageDecoder<HoodieInstantInfo> DECODER;
    @Deprecated
    public String commitTime;
    @Deprecated
    public String action;
    private static final DatumWriter<HoodieInstantInfo> WRITER$;
    private static final DatumReader<HoodieInstantInfo> READER$;
    
    public static Schema getClassSchema() {
        return HoodieInstantInfo.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieInstantInfo> getDecoder() {
        return HoodieInstantInfo.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieInstantInfo> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieInstantInfo>)new BinaryMessageDecoder((GenericData)HoodieInstantInfo.MODEL$, HoodieInstantInfo.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieInstantInfo.ENCODER.encode((HoodieInstantInfo) this);
    }
    
    public static HoodieInstantInfo fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieInstantInfo)HoodieInstantInfo.DECODER.decode(b);
    }
    
    public HoodieInstantInfo() {
    }
    
    public HoodieInstantInfo(final String commitTime, final String action) {
        this.commitTime = commitTime;
        this.action = action;
    }
    
    public Schema getSchema() {
        return HoodieInstantInfo.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.commitTime;
            }
            case 1: {
                return this.action;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.commitTime = (String)value$;
                break;
            }
            case 1: {
                this.action = (String)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public String getCommitTime() {
        return this.commitTime;
    }
    
    public void setCommitTime(final String value) {
        this.commitTime = value;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void setAction(final String value) {
        this.action = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieInstantInfo other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieInstantInfo.WRITER$.write((HoodieInstantInfo) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieInstantInfo.READER$.read((HoodieInstantInfo) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieInstantInfo\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"commitTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
        HoodieInstantInfo.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieInstantInfo.MODEL$, HoodieInstantInfo.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieInstantInfo.MODEL$, HoodieInstantInfo.SCHEMA$);
        WRITER$ = HoodieInstantInfo.MODEL$.createDatumWriter(HoodieInstantInfo.SCHEMA$);
        READER$ = HoodieInstantInfo.MODEL$.createDatumReader(HoodieInstantInfo.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieInstantInfo> implements RecordBuilder<HoodieInstantInfo>
    {
        private String commitTime;
        private String action;
        
        private Builder() {
            super(HoodieInstantInfo.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.commitTime)) {
                this.commitTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.commitTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.action)) {
                this.action = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.action);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        private Builder(final HoodieInstantInfo other) {
            super(HoodieInstantInfo.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.commitTime)) {
                this.commitTime = (String)this.data().deepCopy(this.fields()[0].schema(), (Object)other.commitTime);
                this.fieldSetFlags()[0] = true;
            }
            if (isValidValue(this.fields()[1], (Object)other.action)) {
                this.action = (String)this.data().deepCopy(this.fields()[1].schema(), (Object)other.action);
                this.fieldSetFlags()[1] = true;
            }
        }
        
        public String getCommitTime() {
            return this.commitTime;
        }
        
        public Builder setCommitTime(final String value) {
            this.validate(this.fields()[0], (Object)value);
            this.commitTime = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasCommitTime() {
            return this.fieldSetFlags()[0];
        }
        
        public Builder clearCommitTime() {
            this.commitTime = null;
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
        
        public HoodieInstantInfo build() {
            try {
                final HoodieInstantInfo record = new HoodieInstantInfo();
                record.commitTime = (String)(this.fieldSetFlags()[0] ? this.commitTime : this.defaultValue(this.fields()[0]));
                record.action = (String)(this.fieldSetFlags()[1] ? this.action : this.defaultValue(this.fields()[1]));
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
