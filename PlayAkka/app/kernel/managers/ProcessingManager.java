package kernel.managers;

import akka.actor.ActorRef;
import akka.actor.Props;
import kernel.data.messages.BigObjectProcessingRequest;
import kernel.service.LoggingActor;

/**
 * Created by Sergey Sopin on 14.04.2015 18:47.
 * Project: PlayAkka.
 */

public class ProcessingManager extends LoggingActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof BigObjectProcessingRequest) {
            logger.debug("ProcessingManager: Message received.");
            BigObjectProcessingRequest request = (BigObjectProcessingRequest) message;

            ActorRef gtObjectProcessingSupervisor = context().actorOf(Props.create(BigObjectProcessingManagingActor.class)); //No need to make it named, we just need to create new processor per request.
            gtObjectProcessingSupervisor.tell(message, self());
        } else {
            unhandled(message);
        }
    }
}
