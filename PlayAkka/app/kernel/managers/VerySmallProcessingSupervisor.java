package kernel.managers;

import akka.actor.ActorRef;
import akka.actor.Props;
import kernel.data.messages.VerySmallProcessingRequest;
import kernel.service.LoggingActor;
import kernel.workers.VerySmallFinderActor;

/**
 * Created by Sergey Sopin on 06.05.2015 12:29.
 * Project: PlayAkka
 */
public class VerySmallProcessingSupervisor extends LoggingActor {

    @Override
    public void onReceive(Object o) throws Exception {
        logger.info("VerySmallProcessingSupervisor : Message received...");
        if(o instanceof VerySmallProcessingRequest) {
            ActorRef subPathProcessorActor = context().actorOf(Props.create(VerySmallFinderActor.class));
            subPathProcessorActor.forward(o, context());
        } else {
            unhandled(o);
        }
    }
}
