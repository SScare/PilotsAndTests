package kernel.managers;

import akka.actor.ActorRef;
import akka.actor.Props;
import kernel.data.messages.ConfiguredSmallProcessingRequest;
import kernel.data.messages.SmallProcessingRequest;
import kernel.service.LoggingActor;
import kernel.workers.SmallProcessorActor;

/**
 * Created by Sergey Sopin on 06.05.2015 12:18.
 * Project: PlayAkka
 */
public class SmallProcessorSupervisor extends LoggingActor{
    @Override
    public void onReceive(Object message) throws Exception {
        logger.info("SmallProcessorSupervisor : Message received...");
        if(message instanceof SmallProcessingRequest) {
            SmallProcessingRequest msg = (SmallProcessingRequest)message;

            ActorRef pathProcessorActor = context().actorOf(Props.create(SmallProcessorActor.class));
            pathProcessorActor.tell(new ConfiguredSmallProcessingRequest(msg.smallObject), sender());
            //pathProcessorActor.forward(new ConfiguredPathProcessingRequest(msg.path, minCoverElementSize, minSegmentSize), context());
        } else {
            unhandled(message);
        }
    }
}
