package subscribers;

import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.subscription.*;
import position.PositionDataReader;
import position.PositionSeq;

class OperatorListener extends DataReaderAdapter {
    PositionSeq _dataSeq = new PositionSeq();
    SampleInfoSeq _infoSeq = new SampleInfoSeq();
    public void on_data_available(DataReader reader) {
        PositionDataReader positionDataReader =
                (PositionDataReader)reader;
        try {
            positionDataReader.take(
                    _dataSeq, _infoSeq,
                    ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
                    SampleStateKind.ANY_SAMPLE_STATE,
                    ViewStateKind.ANY_VIEW_STATE,
                    InstanceStateKind.ANY_INSTANCE_STATE);
            for(int i = 0; i < _dataSeq.size(); ++i) {
                SampleInfo info = _infoSeq.get(i);
                if (info.valid_data) {
                    System.out.println(_dataSeq.get(i).toString("Received",0));
                }
            }
        } catch (RETCODE_NO_DATA noData) {
            // No data to process
        } finally {
            positionDataReader.return_loan(_dataSeq, _infoSeq);
        }
    }
}