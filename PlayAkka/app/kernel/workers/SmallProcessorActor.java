package kernel.workers;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Status;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import kernel.data.messages.ConfiguredSmallProcessingRequest;
import kernel.data.messages.SmallProcessingResponse;
import kernel.data.messages.VerySmallProcessingRequest;
import kernel.managers.VerySmallProcessingSupervisor;
import kernel.service.LoggingActor;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sergey Sopin on 06.05.2015 12:26.
 * Project: PlayAkka
 */
public class SmallProcessorActor extends LoggingActor {

    private ActorRef verySmallProcessingSupervisor;
    private int waitingDuration;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        this.verySmallProcessingSupervisor = context().actorOf(Props.create(VerySmallProcessingSupervisor.class));
        this.waitingDuration = (int)readConfig("akka.actor.timeouts", "verySmallAskDuration", 5000);
    }


    @Override
    public void onReceive(Object message) throws Exception {
        logger.info("SmallProcessorActor : Message received...");

        if(message instanceof ConfiguredSmallProcessingRequest) {
            ConfiguredSmallProcessingRequest request = (ConfiguredSmallProcessingRequest) message;

            ExecutionContext ec = context().system().dispatcher();
            Iterable<Future<Object>> futures = process(request);
            Future<Iterable<Object>> future = Futures.sequence(futures, ec);

            ActorRef sender = sender();
            future.onComplete(new OnComplete<Iterable<Object>>() {
                @Override
                public void onComplete(Throwable throwable, Iterable<Object> o) throws Throwable {
                    if(throwable == null) {
                        o.forEach((ob) -> logger.info("Some object is here!"));
                        sender.tell(new SmallProcessingResponse(), self());
                    } else {
                        logger.error(throwable.getMessage());
                        sender.tell(new Status.Failure(throwable), self());
                    }
                }
            },ec);

        } else {
            unhandled(message);
        }
    }

    private Iterable<Future<Object>> process(ConfiguredSmallProcessingRequest request) {
        Collection<Future<Object>> result = new ArrayList<>();
        for (int ceSize = 3; ceSize <= 5; ceSize += 2) {
            for (int segSize = 1; segSize <= ceSize; segSize += 2) {
                Future<Object> future = Patterns.ask(verySmallProcessingSupervisor,
                        new VerySmallProcessingRequest(request.smallObject, ceSize, segSize), waitingDuration);
                result.add(future);
            }
        }

        return result;
    }


}
