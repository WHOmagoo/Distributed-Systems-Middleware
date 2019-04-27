package subscribers;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.StringSeq;
import com.rti.dds.subscription.*;
import com.rti.dds.topic.ContentFilteredTopic;
import com.rti.dds.topic.Topic;
import model.Position;
import position.PositionDataReader;
import position.PositionSeq;
import position.PositionTypeSupport;

import java.util.Arrays;

public class Passenger {
    private String routeName;
    private int stopNumBegin;
    private int stopNumEnd;
    private int domainId;
    private String busBoarded = "";
    private PositionDataReader reader;
    private StringSeq params;
    private volatile boolean end;
    private DomainParticipant participant;
    private ContentFilteredTopic filteredTopic;
    private Thread t;
    private Runnable r;


    public Passenger(int doaminId, String routerouteName, int stopNumBegin, int stopNumEnd){
        this.routeName = routerouteName;
        this.stopNumBegin = stopNumBegin;
        this.stopNumEnd = stopNumEnd;
        this.domainId = doaminId;
        this.end = false;
        r = () -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ignored) {
            }
        };

        t = new Thread(r);
    }

    public void listen(){
        Subscriber subscriber = null;
        Topic topic = null;
        DataReaderListener listener = null;
        reader = null;
        try {
            // --- Create participant --- //
            /* To customize participant QoS, use
            the configuration file
            USER_QOS_PROFILES.xml */

            participant = DomainParticipantFactory.TheParticipantFactory.
                    create_participant(
                            domainId, DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                            null /* listener */, StatusKind.STATUS_MASK_NONE);
            if (participant == null) {
                System.err.println("create_participant error\n");
                return;
            }
            // --- Create subscriber --- //
            /* To customize subscriber QoS, use
            the configuration file USER_QOS_PROFILES.xml */
            subscriber = participant.create_subscriber(
                    DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null /* listener */,
                    StatusKind.STATUS_MASK_NONE);
            if (subscriber == null) {
                System.err.println("create_subscriber error\n");
                return;
            }
            // --- Create topic --- //
            /* Register type before creating topic */
            String typeName = PositionTypeSupport.get_type_name();
            PositionTypeSupport.register_type(participant, typeName);
            /* To customize topic QoS, use
            the configuration file USER_QOS_PROFILES.xml */
            topic = participant.create_topic(
                    "P3464_hrmcgough: PT/POS",
                    typeName, DomainParticipant.TOPIC_QOS_DEFAULT,
                    null /* listener */, StatusKind.STATUS_MASK_NONE);

            String param_list[] = {"'" + routeName + "'", Integer.toString(stopNumBegin)};
            params = new StringSeq(Arrays.asList(param_list));

            filteredTopic = participant.create_contentfilteredtopic(
                    "ContentFilteredTopic", topic, "route MATCH %0 or stopNumber = %1", params);


            if (filteredTopic == null) {
                System.err.println("create_topic error\n");
                return;
            }

            // --- Create reader --- //
            listener = new PassengerListener(this);
            /* To customize data reader QoS, use
            the configuration file USER_QOS_PROFILES.xml */
            reader = (PositionDataReader)
                    subscriber.create_datareader(
                            filteredTopic, Subscriber.DATAREADER_QOS_DEFAULT, listener,
                            StatusKind.STATUS_MASK_ALL);
            if (reader == null) {
                System.err.println("create_datareader error\n");
                return;
            }

            // --- Wait for data --- //



            t.start();
            try {
                t.join();
            } catch (InterruptedException ignored){

            }


            System.out.println("Boarding bus " + busBoarded);
            params.set(0, "'" + this.busBoarded + "'");
            filteredTopic.set_expression("vehicle MATCH %0", params);

            t = new Thread(r);
            t.start();
            try{
                t.join();
            } catch (InterruptedException ignored){

            }

            System.out.println("Ending");


        } finally {
            if(participant != null) {
                participant.delete_contained_entities();
                DomainParticipantFactory.TheParticipantFactory.
                        delete_participant(participant);
            }
            /* RTI Data Distribution Service provides the finalize_instance()
            method for users who want to release memory used by the
            participant factory singleton. Uncomment the following block of
            code for clean destruction of the participant factory
            singleton. */
            //DomainParticipantFactory.finalize_instance();
        }
    }

    private class PassengerListener extends DataReaderAdapter {
        private final Passenger p;
        private boolean boarded = false;
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
                        System.out.println(_dataSeq.get(i).toString("Received", 0));

                        Position curMessage = _dataSeq.get(i);
                        if(!boarded) {
                            if (p.routeName.equalsIgnoreCase(curMessage.route) && p.stopNumBegin == curMessage.stopNumber) {
                                boarded = true;
                                System.out.println("Boarding bus " + curMessage.vehicle);
                                p.board(curMessage.vehicle);
                            }
                        } else {
                            if (p.stopNumEnd == curMessage.getStopNumber() && curMessage.vehicle.equalsIgnoreCase(p.busBoarded)){
                                System.out.println("Unboarding bus " + curMessage.vehicle + "at stop " + curMessage.stopNumber);
                                p.unboard();
                            }
                        }
                    }
                }
            } catch (RETCODE_NO_DATA noData) {
                // No data to process
            } finally {
                positionDataReader.return_loan(_dataSeq, _infoSeq);
            }
        }

        PassengerListener(Passenger p){
            this.p = p;
        }
    }

    private void unboard() {
        this.end = true;
        t.interrupt();
    }

    private void board(String vehicle) {
        busBoarded = vehicle;
        t.interrupt();
    }
}
