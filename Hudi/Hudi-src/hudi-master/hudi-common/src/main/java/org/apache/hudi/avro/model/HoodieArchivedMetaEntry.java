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
public class HoodieArchivedMetaEntry extends SpecificRecordBase implements SpecificRecord
{
    private static final long serialVersionUID = -3634521627906106218L;
    public static final Schema SCHEMA$;
    private static SpecificData MODEL$;
    private static final BinaryMessageEncoder<HoodieArchivedMetaEntry> ENCODER;
    private static final BinaryMessageDecoder<HoodieArchivedMetaEntry> DECODER;
    @Deprecated
    public HoodieCommitMetadata hoodieCommitMetadata;
    @Deprecated
    public HoodieCleanMetadata hoodieCleanMetadata;
    @Deprecated
    public HoodieCompactionMetadata hoodieCompactionMetadata;
    @Deprecated
    public HoodieRollbackMetadata hoodieRollbackMetadata;
    @Deprecated
    public HoodieSavepointMetadata hoodieSavePointMetadata;
    @Deprecated
    public String commitTime;
    @Deprecated
    public String actionType;
    @Deprecated
    public Integer version;
    @Deprecated
    public HoodieCompactionPlan hoodieCompactionPlan;
    @Deprecated
    public HoodieCleanerPlan hoodieCleanerPlan;
    @Deprecated
    public String actionState;
    @Deprecated
    public HoodieReplaceCommitMetadata hoodieReplaceCommitMetadata;
    @Deprecated
    public HoodieRequestedReplaceMetadata hoodieRequestedReplaceMetadata;
    private static final DatumWriter<HoodieArchivedMetaEntry> WRITER$;
    private static final DatumReader<HoodieArchivedMetaEntry> READER$;
    
    public static Schema getClassSchema() {
        return HoodieArchivedMetaEntry.SCHEMA$;
    }
    
    public static BinaryMessageDecoder<HoodieArchivedMetaEntry> getDecoder() {
        return HoodieArchivedMetaEntry.DECODER;
    }
    
    public static BinaryMessageDecoder<HoodieArchivedMetaEntry> createDecoder(final SchemaStore resolver) {
        return (BinaryMessageDecoder<HoodieArchivedMetaEntry>)new BinaryMessageDecoder((GenericData)HoodieArchivedMetaEntry.MODEL$, HoodieArchivedMetaEntry.SCHEMA$, resolver);
    }
    
    public ByteBuffer toByteBuffer() throws IOException {
        return HoodieArchivedMetaEntry.ENCODER.encode((HoodieArchivedMetaEntry) this);
    }
    
    public static HoodieArchivedMetaEntry fromByteBuffer(final ByteBuffer b) throws IOException {
        return (HoodieArchivedMetaEntry)HoodieArchivedMetaEntry.DECODER.decode(b);
    }
    
    public HoodieArchivedMetaEntry() {
    }
    
    public HoodieArchivedMetaEntry(final HoodieCommitMetadata hoodieCommitMetadata, final HoodieCleanMetadata hoodieCleanMetadata, final HoodieCompactionMetadata hoodieCompactionMetadata, final HoodieRollbackMetadata hoodieRollbackMetadata, final HoodieSavepointMetadata hoodieSavePointMetadata, final String commitTime, final String actionType, final Integer version, final HoodieCompactionPlan hoodieCompactionPlan, final HoodieCleanerPlan hoodieCleanerPlan, final String actionState, final HoodieReplaceCommitMetadata hoodieReplaceCommitMetadata, final HoodieRequestedReplaceMetadata hoodieRequestedReplaceMetadata) {
        this.hoodieCommitMetadata = hoodieCommitMetadata;
        this.hoodieCleanMetadata = hoodieCleanMetadata;
        this.hoodieCompactionMetadata = hoodieCompactionMetadata;
        this.hoodieRollbackMetadata = hoodieRollbackMetadata;
        this.hoodieSavePointMetadata = hoodieSavePointMetadata;
        this.commitTime = commitTime;
        this.actionType = actionType;
        this.version = version;
        this.hoodieCompactionPlan = hoodieCompactionPlan;
        this.hoodieCleanerPlan = hoodieCleanerPlan;
        this.actionState = actionState;
        this.hoodieReplaceCommitMetadata = hoodieReplaceCommitMetadata;
        this.hoodieRequestedReplaceMetadata = hoodieRequestedReplaceMetadata;
    }
    
    public Schema getSchema() {
        return HoodieArchivedMetaEntry.SCHEMA$;
    }
    
    public Object get(final int field$) {
        switch (field$) {
            case 0: {
                return this.hoodieCommitMetadata;
            }
            case 1: {
                return this.hoodieCleanMetadata;
            }
            case 2: {
                return this.hoodieCompactionMetadata;
            }
            case 3: {
                return this.hoodieRollbackMetadata;
            }
            case 4: {
                return this.hoodieSavePointMetadata;
            }
            case 5: {
                return this.commitTime;
            }
            case 6: {
                return this.actionType;
            }
            case 7: {
                return this.version;
            }
            case 8: {
                return this.hoodieCompactionPlan;
            }
            case 9: {
                return this.hoodieCleanerPlan;
            }
            case 10: {
                return this.actionState;
            }
            case 11: {
                return this.hoodieReplaceCommitMetadata;
            }
            case 12: {
                return this.hoodieRequestedReplaceMetadata;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public void put(final int field$, final Object value$) {
        switch (field$) {
            case 0: {
                this.hoodieCommitMetadata = (HoodieCommitMetadata)value$;
                break;
            }
            case 1: {
                this.hoodieCleanMetadata = (HoodieCleanMetadata)value$;
                break;
            }
            case 2: {
                this.hoodieCompactionMetadata = (HoodieCompactionMetadata)value$;
                break;
            }
            case 3: {
                this.hoodieRollbackMetadata = (HoodieRollbackMetadata)value$;
                break;
            }
            case 4: {
                this.hoodieSavePointMetadata = (HoodieSavepointMetadata)value$;
                break;
            }
            case 5: {
                this.commitTime = (String)value$;
                break;
            }
            case 6: {
                this.actionType = (String)value$;
                break;
            }
            case 7: {
                this.version = (Integer)value$;
                break;
            }
            case 8: {
                this.hoodieCompactionPlan = (HoodieCompactionPlan)value$;
                break;
            }
            case 9: {
                this.hoodieCleanerPlan = (HoodieCleanerPlan)value$;
                break;
            }
            case 10: {
                this.actionState = (String)value$;
                break;
            }
            case 11: {
                this.hoodieReplaceCommitMetadata = (HoodieReplaceCommitMetadata)value$;
                break;
            }
            case 12: {
                this.hoodieRequestedReplaceMetadata = (HoodieRequestedReplaceMetadata)value$;
                break;
            }
            default: {
                throw new AvroRuntimeException("Bad index");
            }
        }
    }
    
    public HoodieCommitMetadata getHoodieCommitMetadata() {
        return this.hoodieCommitMetadata;
    }
    
    public void setHoodieCommitMetadata(final HoodieCommitMetadata value) {
        this.hoodieCommitMetadata = value;
    }
    
    public HoodieCleanMetadata getHoodieCleanMetadata() {
        return this.hoodieCleanMetadata;
    }
    
    public void setHoodieCleanMetadata(final HoodieCleanMetadata value) {
        this.hoodieCleanMetadata = value;
    }
    
    public HoodieCompactionMetadata getHoodieCompactionMetadata() {
        return this.hoodieCompactionMetadata;
    }
    
    public void setHoodieCompactionMetadata(final HoodieCompactionMetadata value) {
        this.hoodieCompactionMetadata = value;
    }
    
    public HoodieRollbackMetadata getHoodieRollbackMetadata() {
        return this.hoodieRollbackMetadata;
    }
    
    public void setHoodieRollbackMetadata(final HoodieRollbackMetadata value) {
        this.hoodieRollbackMetadata = value;
    }
    
    public HoodieSavepointMetadata getHoodieSavePointMetadata() {
        return this.hoodieSavePointMetadata;
    }
    
    public void setHoodieSavePointMetadata(final HoodieSavepointMetadata value) {
        this.hoodieSavePointMetadata = value;
    }
    
    public String getCommitTime() {
        return this.commitTime;
    }
    
    public void setCommitTime(final String value) {
        this.commitTime = value;
    }
    
    public String getActionType() {
        return this.actionType;
    }
    
    public void setActionType(final String value) {
        this.actionType = value;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer value) {
        this.version = value;
    }
    
    public HoodieCompactionPlan getHoodieCompactionPlan() {
        return this.hoodieCompactionPlan;
    }
    
    public void setHoodieCompactionPlan(final HoodieCompactionPlan value) {
        this.hoodieCompactionPlan = value;
    }
    
    public HoodieCleanerPlan getHoodieCleanerPlan() {
        return this.hoodieCleanerPlan;
    }
    
    public void setHoodieCleanerPlan(final HoodieCleanerPlan value) {
        this.hoodieCleanerPlan = value;
    }
    
    public String getActionState() {
        return this.actionState;
    }
    
    public void setActionState(final String value) {
        this.actionState = value;
    }
    
    public HoodieReplaceCommitMetadata getHoodieReplaceCommitMetadata() {
        return this.hoodieReplaceCommitMetadata;
    }
    
    public void setHoodieReplaceCommitMetadata(final HoodieReplaceCommitMetadata value) {
        this.hoodieReplaceCommitMetadata = value;
    }
    
    public HoodieRequestedReplaceMetadata getHoodieRequestedReplaceMetadata() {
        return this.hoodieRequestedReplaceMetadata;
    }
    
    public void setHoodieRequestedReplaceMetadata(final HoodieRequestedReplaceMetadata value) {
        this.hoodieRequestedReplaceMetadata = value;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static Builder newBuilder(final Builder other) {
        return new Builder(other);
    }
    
    public static Builder newBuilder(final HoodieArchivedMetaEntry other) {
        return new Builder(other);
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        HoodieArchivedMetaEntry.WRITER$.write((HoodieArchivedMetaEntry) this, (Encoder)SpecificData.getEncoder(out));
    }
    
    public void readExternal(final ObjectInput in) throws IOException {
        HoodieArchivedMetaEntry.READER$.read((HoodieArchivedMetaEntry) this, (Decoder)SpecificData.getDecoder(in));
    }
    
    static {
        SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieArchivedMetaEntry\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"hoodieCommitMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieCommitMetadata\",\"fields\":[{\"name\":\"partitionToWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieWriteStat\",\"fields\":[{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"path\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"prevCommit\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numDeletes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numUpdateWrites\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteBytes\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalWriteErrors\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"numInserts\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalCorruptLogBlock\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalRollbackBlocks\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"fileSizeInBytes\",\"type\":[\"null\",\"long\"],\"default\":null}]}},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\",\"default\":null}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"hoodieCleanMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieCleanMetadata\",\"fields\":[{\"name\":\"startCleanTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"earliestCommitToRetain\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieCleanPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"deletePathPatterns\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"bootstrapPartitionMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"HoodieCleanPartitionMetadata\",\"avro.java.string\":\"String\",\"default\":null}],\"default\":null}]}],\"default\":null},{\"name\":\"hoodieCompactionMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieCompactionMetadata\",\"fields\":[{\"name\":\"partitionToCompactionWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCompactionWriteStat\",\"fields\":[{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"totalLogRecords\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalLogFiles\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"totalUpdatedRecordsCompacted\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"hoodieWriteStat\",\"type\":[\"null\",\"HoodieWriteStat\"],\"default\":null}]}},\"avro.java.string\":\"String\"}]}]}],\"default\":null},{\"name\":\"hoodieRollbackMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieRollbackMetadata\",\"fields\":[{\"name\":\"startRollbackTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"timeTakenInMillis\",\"type\":\"long\"},{\"name\":\"totalFilesDeleted\",\"type\":\"int\"},{\"name\":\"commitsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieRollbackPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"successDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"failedDeleteFiles\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"rollbackLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"writtenLogFiles\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"long\",\"avro.java.string\":\"String\"}],\"default\":null}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"instantsRollback\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieInstantInfo\",\"fields\":[{\"name\":\"commitTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},\"default\":[]},\"default\":[]}]}],\"default\":null},{\"name\":\"hoodieSavePointMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieSavepointMetadata\",\"fields\":[{\"name\":\"savepointedBy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"savepointedAt\",\"type\":\"long\"},{\"name\":\"comments\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"partitionMetadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"HoodieSavepointPartitionMetadata\",\"fields\":[{\"name\":\"partitionPath\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"savepointDataFile\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]},\"avro.java.string\":\"String\"}},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}],\"default\":null},{\"name\":\"commitTime\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"actionType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"hoodieCompactionPlan\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieCompactionPlan\",\"fields\":[{\"name\":\"operations\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCompactionOperation\",\"fields\":[{\"name\":\"baseInstantTime\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}],\"default\":null},{\"name\":\"hoodieCleanerPlan\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieCleanerPlan\",\"fields\":[{\"name\":\"earliestInstantToRetain\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieActionInstant\",\"fields\":[{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"action\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"state\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}],\"default\":null},{\"name\":\"policy\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"filesToBeDeletedPerPartition\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"avro.java.string\":\"String\"}],\"default\":null},", new String[] { "{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"filePathsToBeDeletedPerPartition\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieCleanFileInfo\",\"fields\":[{\"name\":\"filePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"isBootstrapBaseFile\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}},\"avro.java.string\":\"String\"}],\"doc\":\"This field replaces the field filesToBeDeletedPerPartition\",\"default\":null}]}],\"default\":null},{\"name\":\"actionState\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"hoodieReplaceCommitMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieReplaceCommitMetadata\",\"fields\":[{\"name\":\"partitionToWriteStats\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"HoodieWriteStat\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"partitionToReplaceFileIds\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"hoodieRequestedReplaceMetadata\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieRequestedReplaceMetadata\",\"fields\":[{\"name\":\"operationType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":\"\"},{\"name\":\"clusteringPlan\",\"type\":[{\"type\":\"record\",\"name\":\"HoodieClusteringPlan\",\"fields\":[{\"name\":\"inputGroups\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"strategy\",\"type\":[{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]},\"null\"],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]},\"null\"],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}],\"default\":null}]}" });
        HoodieArchivedMetaEntry.MODEL$ = new SpecificData();
        ENCODER = new BinaryMessageEncoder((GenericData)HoodieArchivedMetaEntry.MODEL$, HoodieArchivedMetaEntry.SCHEMA$);
        DECODER = new BinaryMessageDecoder((GenericData)HoodieArchivedMetaEntry.MODEL$, HoodieArchivedMetaEntry.SCHEMA$);
        WRITER$ = HoodieArchivedMetaEntry.MODEL$.createDatumWriter(HoodieArchivedMetaEntry.SCHEMA$);
        READER$ = HoodieArchivedMetaEntry.MODEL$.createDatumReader(HoodieArchivedMetaEntry.SCHEMA$);
    }
    
    public static class Builder extends SpecificRecordBuilderBase<HoodieArchivedMetaEntry> implements RecordBuilder<HoodieArchivedMetaEntry>
    {
        private HoodieCommitMetadata hoodieCommitMetadata;
        private HoodieCommitMetadata.Builder hoodieCommitMetadataBuilder;
        private HoodieCleanMetadata hoodieCleanMetadata;
        private HoodieCleanMetadata.Builder hoodieCleanMetadataBuilder;
        private HoodieCompactionMetadata hoodieCompactionMetadata;
        private HoodieCompactionMetadata.Builder hoodieCompactionMetadataBuilder;
        private HoodieRollbackMetadata hoodieRollbackMetadata;
        private HoodieRollbackMetadata.Builder hoodieRollbackMetadataBuilder;
        private HoodieSavepointMetadata hoodieSavePointMetadata;
        private HoodieSavepointMetadata.Builder hoodieSavePointMetadataBuilder;
        private String commitTime;
        private String actionType;
        private Integer version;
        private HoodieCompactionPlan hoodieCompactionPlan;
        private HoodieCompactionPlan.Builder hoodieCompactionPlanBuilder;
        private HoodieCleanerPlan hoodieCleanerPlan;
        private HoodieCleanerPlan.Builder hoodieCleanerPlanBuilder;
        private String actionState;
        private HoodieReplaceCommitMetadata hoodieReplaceCommitMetadata;
        private HoodieReplaceCommitMetadata.Builder hoodieReplaceCommitMetadataBuilder;
        private HoodieRequestedReplaceMetadata hoodieRequestedReplaceMetadata;
        private HoodieRequestedReplaceMetadata.Builder hoodieRequestedReplaceMetadataBuilder;
        
        private Builder() {
            super(HoodieArchivedMetaEntry.SCHEMA$);
        }
        
        private Builder(final Builder other) {
            super((SpecificRecordBuilderBase)other);
            if (isValidValue(this.fields()[0], (Object)other.hoodieCommitMetadata)) {
                this.hoodieCommitMetadata = (HoodieCommitMetadata)this.data().deepCopy(this.fields()[0].schema(), (Object)other.hoodieCommitMetadata);
                this.fieldSetFlags()[0] = true;
            }
            if (other.hasHoodieCommitMetadataBuilder()) {
                this.hoodieCommitMetadataBuilder = HoodieCommitMetadata.newBuilder(other.getHoodieCommitMetadataBuilder());
            }
            if (isValidValue(this.fields()[1], (Object)other.hoodieCleanMetadata)) {
                this.hoodieCleanMetadata = (HoodieCleanMetadata)this.data().deepCopy(this.fields()[1].schema(), (Object)other.hoodieCleanMetadata);
                this.fieldSetFlags()[1] = true;
            }
            if (other.hasHoodieCleanMetadataBuilder()) {
                this.hoodieCleanMetadataBuilder = HoodieCleanMetadata.newBuilder(other.getHoodieCleanMetadataBuilder());
            }
            if (isValidValue(this.fields()[2], (Object)other.hoodieCompactionMetadata)) {
                this.hoodieCompactionMetadata = (HoodieCompactionMetadata)this.data().deepCopy(this.fields()[2].schema(), (Object)other.hoodieCompactionMetadata);
                this.fieldSetFlags()[2] = true;
            }
            if (other.hasHoodieCompactionMetadataBuilder()) {
                this.hoodieCompactionMetadataBuilder = HoodieCompactionMetadata.newBuilder(other.getHoodieCompactionMetadataBuilder());
            }
            if (isValidValue(this.fields()[3], (Object)other.hoodieRollbackMetadata)) {
                this.hoodieRollbackMetadata = (HoodieRollbackMetadata)this.data().deepCopy(this.fields()[3].schema(), (Object)other.hoodieRollbackMetadata);
                this.fieldSetFlags()[3] = true;
            }
            if (other.hasHoodieRollbackMetadataBuilder()) {
                this.hoodieRollbackMetadataBuilder = HoodieRollbackMetadata.newBuilder(other.getHoodieRollbackMetadataBuilder());
            }
            if (isValidValue(this.fields()[4], (Object)other.hoodieSavePointMetadata)) {
                this.hoodieSavePointMetadata = (HoodieSavepointMetadata)this.data().deepCopy(this.fields()[4].schema(), (Object)other.hoodieSavePointMetadata);
                this.fieldSetFlags()[4] = true;
            }
            if (other.hasHoodieSavePointMetadataBuilder()) {
                this.hoodieSavePointMetadataBuilder = HoodieSavepointMetadata.newBuilder(other.getHoodieSavePointMetadataBuilder());
            }
            if (isValidValue(this.fields()[5], (Object)other.commitTime)) {
                this.commitTime = (String)this.data().deepCopy(this.fields()[5].schema(), (Object)other.commitTime);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.actionType)) {
                this.actionType = (String)this.data().deepCopy(this.fields()[6].schema(), (Object)other.actionType);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[7].schema(), (Object)other.version);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.hoodieCompactionPlan)) {
                this.hoodieCompactionPlan = (HoodieCompactionPlan)this.data().deepCopy(this.fields()[8].schema(), (Object)other.hoodieCompactionPlan);
                this.fieldSetFlags()[8] = true;
            }
            if (other.hasHoodieCompactionPlanBuilder()) {
                this.hoodieCompactionPlanBuilder = HoodieCompactionPlan.newBuilder(other.getHoodieCompactionPlanBuilder());
            }
            if (isValidValue(this.fields()[9], (Object)other.hoodieCleanerPlan)) {
                this.hoodieCleanerPlan = (HoodieCleanerPlan)this.data().deepCopy(this.fields()[9].schema(), (Object)other.hoodieCleanerPlan);
                this.fieldSetFlags()[9] = true;
            }
            if (other.hasHoodieCleanerPlanBuilder()) {
                this.hoodieCleanerPlanBuilder = HoodieCleanerPlan.newBuilder(other.getHoodieCleanerPlanBuilder());
            }
            if (isValidValue(this.fields()[10], (Object)other.actionState)) {
                this.actionState = (String)this.data().deepCopy(this.fields()[10].schema(), (Object)other.actionState);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.hoodieReplaceCommitMetadata)) {
                this.hoodieReplaceCommitMetadata = (HoodieReplaceCommitMetadata)this.data().deepCopy(this.fields()[11].schema(), (Object)other.hoodieReplaceCommitMetadata);
                this.fieldSetFlags()[11] = true;
            }
            if (other.hasHoodieReplaceCommitMetadataBuilder()) {
                this.hoodieReplaceCommitMetadataBuilder = HoodieReplaceCommitMetadata.newBuilder(other.getHoodieReplaceCommitMetadataBuilder());
            }
            if (isValidValue(this.fields()[12], (Object)other.hoodieRequestedReplaceMetadata)) {
                this.hoodieRequestedReplaceMetadata = (HoodieRequestedReplaceMetadata)this.data().deepCopy(this.fields()[12].schema(), (Object)other.hoodieRequestedReplaceMetadata);
                this.fieldSetFlags()[12] = true;
            }
            if (other.hasHoodieRequestedReplaceMetadataBuilder()) {
                this.hoodieRequestedReplaceMetadataBuilder = HoodieRequestedReplaceMetadata.newBuilder(other.getHoodieRequestedReplaceMetadataBuilder());
            }
        }
        
        private Builder(final HoodieArchivedMetaEntry other) {
            super(HoodieArchivedMetaEntry.SCHEMA$);
            if (isValidValue(this.fields()[0], (Object)other.hoodieCommitMetadata)) {
                this.hoodieCommitMetadata = (HoodieCommitMetadata)this.data().deepCopy(this.fields()[0].schema(), (Object)other.hoodieCommitMetadata);
                this.fieldSetFlags()[0] = true;
            }
            this.hoodieCommitMetadataBuilder = null;
            if (isValidValue(this.fields()[1], (Object)other.hoodieCleanMetadata)) {
                this.hoodieCleanMetadata = (HoodieCleanMetadata)this.data().deepCopy(this.fields()[1].schema(), (Object)other.hoodieCleanMetadata);
                this.fieldSetFlags()[1] = true;
            }
            this.hoodieCleanMetadataBuilder = null;
            if (isValidValue(this.fields()[2], (Object)other.hoodieCompactionMetadata)) {
                this.hoodieCompactionMetadata = (HoodieCompactionMetadata)this.data().deepCopy(this.fields()[2].schema(), (Object)other.hoodieCompactionMetadata);
                this.fieldSetFlags()[2] = true;
            }
            this.hoodieCompactionMetadataBuilder = null;
            if (isValidValue(this.fields()[3], (Object)other.hoodieRollbackMetadata)) {
                this.hoodieRollbackMetadata = (HoodieRollbackMetadata)this.data().deepCopy(this.fields()[3].schema(), (Object)other.hoodieRollbackMetadata);
                this.fieldSetFlags()[3] = true;
            }
            this.hoodieRollbackMetadataBuilder = null;
            if (isValidValue(this.fields()[4], (Object)other.hoodieSavePointMetadata)) {
                this.hoodieSavePointMetadata = (HoodieSavepointMetadata)this.data().deepCopy(this.fields()[4].schema(), (Object)other.hoodieSavePointMetadata);
                this.fieldSetFlags()[4] = true;
            }
            this.hoodieSavePointMetadataBuilder = null;
            if (isValidValue(this.fields()[5], (Object)other.commitTime)) {
                this.commitTime = (String)this.data().deepCopy(this.fields()[5].schema(), (Object)other.commitTime);
                this.fieldSetFlags()[5] = true;
            }
            if (isValidValue(this.fields()[6], (Object)other.actionType)) {
                this.actionType = (String)this.data().deepCopy(this.fields()[6].schema(), (Object)other.actionType);
                this.fieldSetFlags()[6] = true;
            }
            if (isValidValue(this.fields()[7], (Object)other.version)) {
                this.version = (Integer)this.data().deepCopy(this.fields()[7].schema(), (Object)other.version);
                this.fieldSetFlags()[7] = true;
            }
            if (isValidValue(this.fields()[8], (Object)other.hoodieCompactionPlan)) {
                this.hoodieCompactionPlan = (HoodieCompactionPlan)this.data().deepCopy(this.fields()[8].schema(), (Object)other.hoodieCompactionPlan);
                this.fieldSetFlags()[8] = true;
            }
            this.hoodieCompactionPlanBuilder = null;
            if (isValidValue(this.fields()[9], (Object)other.hoodieCleanerPlan)) {
                this.hoodieCleanerPlan = (HoodieCleanerPlan)this.data().deepCopy(this.fields()[9].schema(), (Object)other.hoodieCleanerPlan);
                this.fieldSetFlags()[9] = true;
            }
            this.hoodieCleanerPlanBuilder = null;
            if (isValidValue(this.fields()[10], (Object)other.actionState)) {
                this.actionState = (String)this.data().deepCopy(this.fields()[10].schema(), (Object)other.actionState);
                this.fieldSetFlags()[10] = true;
            }
            if (isValidValue(this.fields()[11], (Object)other.hoodieReplaceCommitMetadata)) {
                this.hoodieReplaceCommitMetadata = (HoodieReplaceCommitMetadata)this.data().deepCopy(this.fields()[11].schema(), (Object)other.hoodieReplaceCommitMetadata);
                this.fieldSetFlags()[11] = true;
            }
            this.hoodieReplaceCommitMetadataBuilder = null;
            if (isValidValue(this.fields()[12], (Object)other.hoodieRequestedReplaceMetadata)) {
                this.hoodieRequestedReplaceMetadata = (HoodieRequestedReplaceMetadata)this.data().deepCopy(this.fields()[12].schema(), (Object)other.hoodieRequestedReplaceMetadata);
                this.fieldSetFlags()[12] = true;
            }
            this.hoodieRequestedReplaceMetadataBuilder = null;
        }
        
        public HoodieCommitMetadata getHoodieCommitMetadata() {
            return this.hoodieCommitMetadata;
        }
        
        public Builder setHoodieCommitMetadata(final HoodieCommitMetadata value) {
            this.validate(this.fields()[0], (Object)value);
            this.hoodieCommitMetadataBuilder = null;
            this.hoodieCommitMetadata = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }
        
        public boolean hasHoodieCommitMetadata() {
            return this.fieldSetFlags()[0];
        }
        
        public HoodieCommitMetadata.Builder getHoodieCommitMetadataBuilder() {
            if (this.hoodieCommitMetadataBuilder == null) {
                if (this.hasHoodieCommitMetadata()) {
                    this.setHoodieCommitMetadataBuilder(HoodieCommitMetadata.newBuilder(this.hoodieCommitMetadata));
                }
                else {
                    this.setHoodieCommitMetadataBuilder(HoodieCommitMetadata.newBuilder());
                }
            }
            return this.hoodieCommitMetadataBuilder;
        }
        
        public Builder setHoodieCommitMetadataBuilder(final HoodieCommitMetadata.Builder value) {
            this.clearHoodieCommitMetadata();
            this.hoodieCommitMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieCommitMetadataBuilder() {
            return this.hoodieCommitMetadataBuilder != null;
        }
        
        public Builder clearHoodieCommitMetadata() {
            this.hoodieCommitMetadata = null;
            this.hoodieCommitMetadataBuilder = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }
        
        public HoodieCleanMetadata getHoodieCleanMetadata() {
            return this.hoodieCleanMetadata;
        }
        
        public Builder setHoodieCleanMetadata(final HoodieCleanMetadata value) {
            this.validate(this.fields()[1], (Object)value);
            this.hoodieCleanMetadataBuilder = null;
            this.hoodieCleanMetadata = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }
        
        public boolean hasHoodieCleanMetadata() {
            return this.fieldSetFlags()[1];
        }
        
        public HoodieCleanMetadata.Builder getHoodieCleanMetadataBuilder() {
            if (this.hoodieCleanMetadataBuilder == null) {
                if (this.hasHoodieCleanMetadata()) {
                    this.setHoodieCleanMetadataBuilder(HoodieCleanMetadata.newBuilder(this.hoodieCleanMetadata));
                }
                else {
                    this.setHoodieCleanMetadataBuilder(HoodieCleanMetadata.newBuilder());
                }
            }
            return this.hoodieCleanMetadataBuilder;
        }
        
        public Builder setHoodieCleanMetadataBuilder(final HoodieCleanMetadata.Builder value) {
            this.clearHoodieCleanMetadata();
            this.hoodieCleanMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieCleanMetadataBuilder() {
            return this.hoodieCleanMetadataBuilder != null;
        }
        
        public Builder clearHoodieCleanMetadata() {
            this.hoodieCleanMetadata = null;
            this.hoodieCleanMetadataBuilder = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }
        
        public HoodieCompactionMetadata getHoodieCompactionMetadata() {
            return this.hoodieCompactionMetadata;
        }
        
        public Builder setHoodieCompactionMetadata(final HoodieCompactionMetadata value) {
            this.validate(this.fields()[2], (Object)value);
            this.hoodieCompactionMetadataBuilder = null;
            this.hoodieCompactionMetadata = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }
        
        public boolean hasHoodieCompactionMetadata() {
            return this.fieldSetFlags()[2];
        }
        
        public HoodieCompactionMetadata.Builder getHoodieCompactionMetadataBuilder() {
            if (this.hoodieCompactionMetadataBuilder == null) {
                if (this.hasHoodieCompactionMetadata()) {
                    this.setHoodieCompactionMetadataBuilder(HoodieCompactionMetadata.newBuilder(this.hoodieCompactionMetadata));
                }
                else {
                    this.setHoodieCompactionMetadataBuilder(HoodieCompactionMetadata.newBuilder());
                }
            }
            return this.hoodieCompactionMetadataBuilder;
        }
        
        public Builder setHoodieCompactionMetadataBuilder(final HoodieCompactionMetadata.Builder value) {
            this.clearHoodieCompactionMetadata();
            this.hoodieCompactionMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieCompactionMetadataBuilder() {
            return this.hoodieCompactionMetadataBuilder != null;
        }
        
        public Builder clearHoodieCompactionMetadata() {
            this.hoodieCompactionMetadata = null;
            this.hoodieCompactionMetadataBuilder = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }
        
        public HoodieRollbackMetadata getHoodieRollbackMetadata() {
            return this.hoodieRollbackMetadata;
        }
        
        public Builder setHoodieRollbackMetadata(final HoodieRollbackMetadata value) {
            this.validate(this.fields()[3], (Object)value);
            this.hoodieRollbackMetadataBuilder = null;
            this.hoodieRollbackMetadata = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }
        
        public boolean hasHoodieRollbackMetadata() {
            return this.fieldSetFlags()[3];
        }
        
        public HoodieRollbackMetadata.Builder getHoodieRollbackMetadataBuilder() {
            if (this.hoodieRollbackMetadataBuilder == null) {
                if (this.hasHoodieRollbackMetadata()) {
                    this.setHoodieRollbackMetadataBuilder(HoodieRollbackMetadata.newBuilder(this.hoodieRollbackMetadata));
                }
                else {
                    this.setHoodieRollbackMetadataBuilder(HoodieRollbackMetadata.newBuilder());
                }
            }
            return this.hoodieRollbackMetadataBuilder;
        }
        
        public Builder setHoodieRollbackMetadataBuilder(final HoodieRollbackMetadata.Builder value) {
            this.clearHoodieRollbackMetadata();
            this.hoodieRollbackMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieRollbackMetadataBuilder() {
            return this.hoodieRollbackMetadataBuilder != null;
        }
        
        public Builder clearHoodieRollbackMetadata() {
            this.hoodieRollbackMetadata = null;
            this.hoodieRollbackMetadataBuilder = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }
        
        public HoodieSavepointMetadata getHoodieSavePointMetadata() {
            return this.hoodieSavePointMetadata;
        }
        
        public Builder setHoodieSavePointMetadata(final HoodieSavepointMetadata value) {
            this.validate(this.fields()[4], (Object)value);
            this.hoodieSavePointMetadataBuilder = null;
            this.hoodieSavePointMetadata = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }
        
        public boolean hasHoodieSavePointMetadata() {
            return this.fieldSetFlags()[4];
        }
        
        public HoodieSavepointMetadata.Builder getHoodieSavePointMetadataBuilder() {
            if (this.hoodieSavePointMetadataBuilder == null) {
                if (this.hasHoodieSavePointMetadata()) {
                    this.setHoodieSavePointMetadataBuilder(HoodieSavepointMetadata.newBuilder(this.hoodieSavePointMetadata));
                }
                else {
                    this.setHoodieSavePointMetadataBuilder(HoodieSavepointMetadata.newBuilder());
                }
            }
            return this.hoodieSavePointMetadataBuilder;
        }
        
        public Builder setHoodieSavePointMetadataBuilder(final HoodieSavepointMetadata.Builder value) {
            this.clearHoodieSavePointMetadata();
            this.hoodieSavePointMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieSavePointMetadataBuilder() {
            return this.hoodieSavePointMetadataBuilder != null;
        }
        
        public Builder clearHoodieSavePointMetadata() {
            this.hoodieSavePointMetadata = null;
            this.hoodieSavePointMetadataBuilder = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }
        
        public String getCommitTime() {
            return this.commitTime;
        }
        
        public Builder setCommitTime(final String value) {
            this.validate(this.fields()[5], (Object)value);
            this.commitTime = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }
        
        public boolean hasCommitTime() {
            return this.fieldSetFlags()[5];
        }
        
        public Builder clearCommitTime() {
            this.commitTime = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }
        
        public String getActionType() {
            return this.actionType;
        }
        
        public Builder setActionType(final String value) {
            this.validate(this.fields()[6], (Object)value);
            this.actionType = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }
        
        public boolean hasActionType() {
            return this.fieldSetFlags()[6];
        }
        
        public Builder clearActionType() {
            this.actionType = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }
        
        public Integer getVersion() {
            return this.version;
        }
        
        public Builder setVersion(final Integer value) {
            this.validate(this.fields()[7], (Object)value);
            this.version = value;
            this.fieldSetFlags()[7] = true;
            return this;
        }
        
        public boolean hasVersion() {
            return this.fieldSetFlags()[7];
        }
        
        public Builder clearVersion() {
            this.version = null;
            this.fieldSetFlags()[7] = false;
            return this;
        }
        
        public HoodieCompactionPlan getHoodieCompactionPlan() {
            return this.hoodieCompactionPlan;
        }
        
        public Builder setHoodieCompactionPlan(final HoodieCompactionPlan value) {
            this.validate(this.fields()[8], (Object)value);
            this.hoodieCompactionPlanBuilder = null;
            this.hoodieCompactionPlan = value;
            this.fieldSetFlags()[8] = true;
            return this;
        }
        
        public boolean hasHoodieCompactionPlan() {
            return this.fieldSetFlags()[8];
        }
        
        public HoodieCompactionPlan.Builder getHoodieCompactionPlanBuilder() {
            if (this.hoodieCompactionPlanBuilder == null) {
                if (this.hasHoodieCompactionPlan()) {
                    this.setHoodieCompactionPlanBuilder(HoodieCompactionPlan.newBuilder(this.hoodieCompactionPlan));
                }
                else {
                    this.setHoodieCompactionPlanBuilder(HoodieCompactionPlan.newBuilder());
                }
            }
            return this.hoodieCompactionPlanBuilder;
        }
        
        public Builder setHoodieCompactionPlanBuilder(final HoodieCompactionPlan.Builder value) {
            this.clearHoodieCompactionPlan();
            this.hoodieCompactionPlanBuilder = value;
            return this;
        }
        
        public boolean hasHoodieCompactionPlanBuilder() {
            return this.hoodieCompactionPlanBuilder != null;
        }
        
        public Builder clearHoodieCompactionPlan() {
            this.hoodieCompactionPlan = null;
            this.hoodieCompactionPlanBuilder = null;
            this.fieldSetFlags()[8] = false;
            return this;
        }
        
        public HoodieCleanerPlan getHoodieCleanerPlan() {
            return this.hoodieCleanerPlan;
        }
        
        public Builder setHoodieCleanerPlan(final HoodieCleanerPlan value) {
            this.validate(this.fields()[9], (Object)value);
            this.hoodieCleanerPlanBuilder = null;
            this.hoodieCleanerPlan = value;
            this.fieldSetFlags()[9] = true;
            return this;
        }
        
        public boolean hasHoodieCleanerPlan() {
            return this.fieldSetFlags()[9];
        }
        
        public HoodieCleanerPlan.Builder getHoodieCleanerPlanBuilder() {
            if (this.hoodieCleanerPlanBuilder == null) {
                if (this.hasHoodieCleanerPlan()) {
                    this.setHoodieCleanerPlanBuilder(HoodieCleanerPlan.newBuilder(this.hoodieCleanerPlan));
                }
                else {
                    this.setHoodieCleanerPlanBuilder(HoodieCleanerPlan.newBuilder());
                }
            }
            return this.hoodieCleanerPlanBuilder;
        }
        
        public Builder setHoodieCleanerPlanBuilder(final HoodieCleanerPlan.Builder value) {
            this.clearHoodieCleanerPlan();
            this.hoodieCleanerPlanBuilder = value;
            return this;
        }
        
        public boolean hasHoodieCleanerPlanBuilder() {
            return this.hoodieCleanerPlanBuilder != null;
        }
        
        public Builder clearHoodieCleanerPlan() {
            this.hoodieCleanerPlan = null;
            this.hoodieCleanerPlanBuilder = null;
            this.fieldSetFlags()[9] = false;
            return this;
        }
        
        public String getActionState() {
            return this.actionState;
        }
        
        public Builder setActionState(final String value) {
            this.validate(this.fields()[10], (Object)value);
            this.actionState = value;
            this.fieldSetFlags()[10] = true;
            return this;
        }
        
        public boolean hasActionState() {
            return this.fieldSetFlags()[10];
        }
        
        public Builder clearActionState() {
            this.actionState = null;
            this.fieldSetFlags()[10] = false;
            return this;
        }
        
        public HoodieReplaceCommitMetadata getHoodieReplaceCommitMetadata() {
            return this.hoodieReplaceCommitMetadata;
        }
        
        public Builder setHoodieReplaceCommitMetadata(final HoodieReplaceCommitMetadata value) {
            this.validate(this.fields()[11], (Object)value);
            this.hoodieReplaceCommitMetadataBuilder = null;
            this.hoodieReplaceCommitMetadata = value;
            this.fieldSetFlags()[11] = true;
            return this;
        }
        
        public boolean hasHoodieReplaceCommitMetadata() {
            return this.fieldSetFlags()[11];
        }
        
        public HoodieReplaceCommitMetadata.Builder getHoodieReplaceCommitMetadataBuilder() {
            if (this.hoodieReplaceCommitMetadataBuilder == null) {
                if (this.hasHoodieReplaceCommitMetadata()) {
                    this.setHoodieReplaceCommitMetadataBuilder(HoodieReplaceCommitMetadata.newBuilder(this.hoodieReplaceCommitMetadata));
                }
                else {
                    this.setHoodieReplaceCommitMetadataBuilder(HoodieReplaceCommitMetadata.newBuilder());
                }
            }
            return this.hoodieReplaceCommitMetadataBuilder;
        }
        
        public Builder setHoodieReplaceCommitMetadataBuilder(final HoodieReplaceCommitMetadata.Builder value) {
            this.clearHoodieReplaceCommitMetadata();
            this.hoodieReplaceCommitMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieReplaceCommitMetadataBuilder() {
            return this.hoodieReplaceCommitMetadataBuilder != null;
        }
        
        public Builder clearHoodieReplaceCommitMetadata() {
            this.hoodieReplaceCommitMetadata = null;
            this.hoodieReplaceCommitMetadataBuilder = null;
            this.fieldSetFlags()[11] = false;
            return this;
        }
        
        public HoodieRequestedReplaceMetadata getHoodieRequestedReplaceMetadata() {
            return this.hoodieRequestedReplaceMetadata;
        }
        
        public Builder setHoodieRequestedReplaceMetadata(final HoodieRequestedReplaceMetadata value) {
            this.validate(this.fields()[12], (Object)value);
            this.hoodieRequestedReplaceMetadataBuilder = null;
            this.hoodieRequestedReplaceMetadata = value;
            this.fieldSetFlags()[12] = true;
            return this;
        }
        
        public boolean hasHoodieRequestedReplaceMetadata() {
            return this.fieldSetFlags()[12];
        }
        
        public HoodieRequestedReplaceMetadata.Builder getHoodieRequestedReplaceMetadataBuilder() {
            if (this.hoodieRequestedReplaceMetadataBuilder == null) {
                if (this.hasHoodieRequestedReplaceMetadata()) {
                    this.setHoodieRequestedReplaceMetadataBuilder(HoodieRequestedReplaceMetadata.newBuilder(this.hoodieRequestedReplaceMetadata));
                }
                else {
                    this.setHoodieRequestedReplaceMetadataBuilder(HoodieRequestedReplaceMetadata.newBuilder());
                }
            }
            return this.hoodieRequestedReplaceMetadataBuilder;
        }
        
        public Builder setHoodieRequestedReplaceMetadataBuilder(final HoodieRequestedReplaceMetadata.Builder value) {
            this.clearHoodieRequestedReplaceMetadata();
            this.hoodieRequestedReplaceMetadataBuilder = value;
            return this;
        }
        
        public boolean hasHoodieRequestedReplaceMetadataBuilder() {
            return this.hoodieRequestedReplaceMetadataBuilder != null;
        }
        
        public Builder clearHoodieRequestedReplaceMetadata() {
            this.hoodieRequestedReplaceMetadata = null;
            this.hoodieRequestedReplaceMetadataBuilder = null;
            this.fieldSetFlags()[12] = false;
            return this;
        }
        
        public HoodieArchivedMetaEntry build() {
            try {
                final HoodieArchivedMetaEntry record = new HoodieArchivedMetaEntry();
                if (this.hoodieCommitMetadataBuilder != null) {
                    record.hoodieCommitMetadata = this.hoodieCommitMetadataBuilder.build();
                }
                else {
                    record.hoodieCommitMetadata = (HoodieCommitMetadata)(this.fieldSetFlags()[0] ? this.hoodieCommitMetadata : this.defaultValue(this.fields()[0]));
                }
                if (this.hoodieCleanMetadataBuilder != null) {
                    record.hoodieCleanMetadata = this.hoodieCleanMetadataBuilder.build();
                }
                else {
                    record.hoodieCleanMetadata = (HoodieCleanMetadata)(this.fieldSetFlags()[1] ? this.hoodieCleanMetadata : this.defaultValue(this.fields()[1]));
                }
                if (this.hoodieCompactionMetadataBuilder != null) {
                    record.hoodieCompactionMetadata = this.hoodieCompactionMetadataBuilder.build();
                }
                else {
                    record.hoodieCompactionMetadata = (HoodieCompactionMetadata)(this.fieldSetFlags()[2] ? this.hoodieCompactionMetadata : this.defaultValue(this.fields()[2]));
                }
                if (this.hoodieRollbackMetadataBuilder != null) {
                    record.hoodieRollbackMetadata = this.hoodieRollbackMetadataBuilder.build();
                }
                else {
                    record.hoodieRollbackMetadata = (HoodieRollbackMetadata)(this.fieldSetFlags()[3] ? this.hoodieRollbackMetadata : this.defaultValue(this.fields()[3]));
                }
                if (this.hoodieSavePointMetadataBuilder != null) {
                    record.hoodieSavePointMetadata = this.hoodieSavePointMetadataBuilder.build();
                }
                else {
                    record.hoodieSavePointMetadata = (HoodieSavepointMetadata)(this.fieldSetFlags()[4] ? this.hoodieSavePointMetadata : this.defaultValue(this.fields()[4]));
                }
                record.commitTime = (String)(this.fieldSetFlags()[5] ? this.commitTime : this.defaultValue(this.fields()[5]));
                record.actionType = (String)(this.fieldSetFlags()[6] ? this.actionType : this.defaultValue(this.fields()[6]));
                record.version = (Integer)(this.fieldSetFlags()[7] ? this.version : this.defaultValue(this.fields()[7]));
                if (this.hoodieCompactionPlanBuilder != null) {
                    record.hoodieCompactionPlan = this.hoodieCompactionPlanBuilder.build();
                }
                else {
                    record.hoodieCompactionPlan = (HoodieCompactionPlan)(this.fieldSetFlags()[8] ? this.hoodieCompactionPlan : this.defaultValue(this.fields()[8]));
                }
                if (this.hoodieCleanerPlanBuilder != null) {
                    record.hoodieCleanerPlan = this.hoodieCleanerPlanBuilder.build();
                }
                else {
                    record.hoodieCleanerPlan = (HoodieCleanerPlan)(this.fieldSetFlags()[9] ? this.hoodieCleanerPlan : this.defaultValue(this.fields()[9]));
                }
                record.actionState = (String)(this.fieldSetFlags()[10] ? this.actionState : this.defaultValue(this.fields()[10]));
                if (this.hoodieReplaceCommitMetadataBuilder != null) {
                    record.hoodieReplaceCommitMetadata = this.hoodieReplaceCommitMetadataBuilder.build();
                }
                else {
                    record.hoodieReplaceCommitMetadata = (HoodieReplaceCommitMetadata)(this.fieldSetFlags()[11] ? this.hoodieReplaceCommitMetadata : this.defaultValue(this.fields()[11]));
                }
                if (this.hoodieRequestedReplaceMetadataBuilder != null) {
                    record.hoodieRequestedReplaceMetadata = this.hoodieRequestedReplaceMetadataBuilder.build();
                }
                else {
                    record.hoodieRequestedReplaceMetadata = (HoodieRequestedReplaceMetadata)(this.fieldSetFlags()[12] ? this.hoodieRequestedReplaceMetadata : this.defaultValue(this.fields()[12]));
                }
                return record;
            }
            catch (Exception e) {
                throw new AvroRuntimeException((Throwable)e);
            }
        }
    }
}
