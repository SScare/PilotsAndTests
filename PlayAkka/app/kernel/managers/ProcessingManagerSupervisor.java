/*
 * Created by Sergey Sopin on 29.04.15 21:26
 *
 * This actor added in order to maintain scalability.
 * It would be created with router and it could be possible to
 * define remote deployment of this actor.
 */

package kernel.managers;

import akka.actor.ActorRef;
import akka.actor.Props;
import kernel.data.messages.BigObjectProcessingRequest;
import kernel.service.LoggingActor;

public class ProcessingManagerSupervisor extends LoggingActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof BigObjectProcessingRequest) {
            ActorRef processingManager = context().actorOf(Props.create(ProcessingManager.class));
            processingManager.forward(message, context());
        } else {
            unhandled(message);
        }
    }
}
