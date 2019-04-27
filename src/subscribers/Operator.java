package subscribers;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.topic.Topic;
import position.PositionDataReader;
import position.PositionTypeSupport;

public class Operator {
    public static void main(String[] args) {
        // --- Get domain ID --- //
        int domainId = 0;
        if (args.length >= 1) {
            domainId = Integer.valueOf(args[0]);
        }

        /* Uncomment this to turn on additional logging
        Logger.get_instance().set_verbosity_by_category(
            LogCategory.NDDS_CONFIG_LOG_CATEGORY_API,
            LogVerbosity.NDDS_CONFIG_LOG_VERBOSITY_STATUS_ALL);
        */
        // --- Run --- //
        listen(domainId);
    }

    public static void listen(int domainId){
        System.out.println("MessageType\tRoute\t\tVehicle\tTraffic\tStop#\t#Stops\tTimeBetweenStops\tFill%\tTimestamp");

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

            /* To customize topic QoS, use
            the configuration file USER_QOS_PROFILES.xml */
            Topic topic2 = participant.create_topic(
                    "P3464_hmcgough: PT/ALR/ACC",
                    typeName, DomainParticipant.TOPIC_QOS_DEFAULT,
                    null /* listener */, StatusKind.STATUS_MASK_NONE);


            if (topic == null || topic2 == null) {
                System.err.println("create_topic error\n");
                return;
            }

            // --- Create reader --- //
            listener = new OperatorListener();
            OperatorListener listener2 = new OperatorListener();


            /* To customize data reader QoS, use
            the configuration file USER_QOS_PROFILES.xml */
            reader = (PositionDataReader)
                    subscriber.create_datareader(
                            topic, Subscriber.DATAREADER_QOS_DEFAULT, listener,
                            StatusKind.STATUS_MASK_ALL);

            PositionDataReader reader2 = (PositionDataReader) subscriber.create_datareader(topic2, Subscriber.DATAREADER_QOS_DEFAULT, listener, StatusKind.STATUS_MASK_ALL);
            if (reader == null || reader2 == null) {
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
