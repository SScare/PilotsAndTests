package kernel.managers;

import akka.actor.ActorRef;
import akka.actor.Props;
import kernel.data.BigObject;
import kernel.data.messages.BigObjectProcessingRequest;
import kernel.data.messages.SmallProcessingRequest;
import kernel.data.messages.SmallProcessingResponse;
import kernel.service.LoggingActor;

/**
 * Created by SCAR on 06.05.2015 12:16.
 * Project: PlayAkka
 */

public class BigObjectProcessingManagingActor extends LoggingActor{

    private ActorRef smallProcessorSupervisor;

    @Override
    public void preStart() throws Exception{
        super.preStart();
        smallProcessorSupervisor = context().actorOf(Props.create(SmallProcessorSupervisor.class));
    }

    @Override
    public void onReceive(Object message) throws Exception {
        logger.info("BigObjectProcessingManagingActor : MessageReceived...");
        if(message instanceof BigObjectProcessingRequest) {
            BigObject object = ((BigObjectProcessingRequest) message).bigObject;
            logger.info("BigObject processing started...");
            object.getSmallObjectMap().forEach((k, v) -> smallProcessorSupervisor.tell(new SmallProcessingRequest(v), self()));
        } else if(message instanceof SmallProcessingResponse) {
            logger.info("Yappiiii");
      //      context().stop(self());
        } else {
            unhandled(message);
        }
    }
}
