package kernel.workers;

import akka.dispatch.Futures;
import akka.pattern.Patterns;
import kernel.data.messages.VerySmallProcessingRequest;
import kernel.data.messages.VerySmallProcessingResponse;
import kernel.service.LoggingActor;

/**
 * Created by Sergey Sopin on 06.05.2015 12:44.
 * Project: PlayAkka
 */
public class VerySmallFinderActor extends LoggingActor {

    @Override
    public void onReceive(Object message) throws Exception {
        logger.info("VerySmallFinderActor : Message received...");

        if(message instanceof VerySmallProcessingRequest) {
            Patterns.pipe(Futures.successful(new VerySmallProcessingResponse()), context().dispatcher()).to(sender());
        }
    }
}
