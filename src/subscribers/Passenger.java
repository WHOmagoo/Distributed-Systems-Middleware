package subscribers;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.StringSeq;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.topic.ContentFilteredTopic;
import com.rti.dds.topic.Topic;
import position.PositionDataReader;
import position.PositionTypeSupport;

import java.util.Arrays;

public class Passenger {
    private int routeNum;
    private int stopNumBegin;
    private int stopNumEnd;
    private int domainId;

    public Passenger(int doaminId, int routeNum, int stopNumBegin, int stopNumEnd){
        this.routeNum = routeNum;
        this.stopNumBegin = stopNumBegin;
        this.stopNumEnd = stopNumEnd;
        this.domainId = doaminId;
    }

    public void Listen(){
        DomainParticipant participant = null;
        Subscriber subscriber = null;
        Topic topic = null;
        DataReaderListener listener = null;
        PositionDataReader reader = null;
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

            topic = participant.create_topic(
                    "P3464_hrmcgough: PT/POS",
                    "route", DomainParticipant.TOPIC_QOS_DEFAULT,
                    null /* listener */, StatusKind.STATUS_MASK_NONE);

            String param_list[] = {"'Express1'"};
            StringSeq params = new StringSeq(Arrays.asList(param_list));

            ContentFilteredTopic filteredTopic = participant.create_contentfilteredtopic(
                    "ContentFilteredTopic", topic, "route MATCH %0", params);

            if (filteredTopic == null) {
                System.err.println("create_topic error\n");
                return;
            }

            // --- Create reader --- //
            listener = new OperatorListener();
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
            while(true){
            }
        } finally {
            // --- Shutdown --- //
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
}
