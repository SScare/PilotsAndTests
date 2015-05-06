package controllers;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinGroup;
import com.typesafe.config.ConfigFactory;
import kernel.data.BigObject;
import kernel.data.SmallObject;
import kernel.data.messages.BigObjectProcessingRequest;
import kernel.managers.ProcessingManagerSupervisor;
import play.*;
import play.libs.Akka;
import play.mvc.*;

import views.html.*;

import java.util.*;

public class Application extends Controller {

    private static ActorRef processorsRouter = null;

    public static Result index() {

        if(processorsRouter == null) {
            processorsRouter = getProcessorsList();
        }

        processorsRouter.tell(new BigObjectProcessingRequest(createObject()), ActorRef.noSender());

        return ok(index.render("Your new application is ready."));
    }

    /**
     * This procedure reads list of processor actors names
     * and creates them according to actor configurations
     * mapped by names in config. After that it creates
     * RoundRobinGroup with processors as workers.
     *
     * @return RoundRobinGroup ActorRef.
     */
    private static ActorRef getProcessorsList() {
        List<String> processors = new ArrayList<>();
        Map<String, Object> config = (HashMap)(ConfigFactory.load().getAnyRef("akka.actor.deployment.processors"));

        Iterator iterator = config.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            ActorRef actorRef = Akka.system().actorOf(Props.create(ProcessingManagerSupervisor.class), entry.getValue().toString());
            String str = actorRef.path().toSerializationFormat();
            processors.add(str);
        }

        return Akka.system().actorOf(new RoundRobinGroup(processors).props(), "processingManagerRouter");
    }

    private static BigObject createObject() {
        SmallObject object1 = new SmallObject();
        SmallObject object2 = new SmallObject();
        BigObject bigObject = new BigObject();
        bigObject.addSmallObject(object1);
        bigObject.addSmallObject(object2);
        return bigObject;
    }

}
