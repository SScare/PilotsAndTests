/*
 * Created by Sergey Sopin on 06.05.15 12:44
 */

package kernel.service;

import akka.actor.UntypedActor;
import com.typesafe.config.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class ServiceActor extends UntypedActor{

    public static Map<String, Object> readConfig(String configPath) {
        return (HashMap)(ConfigFactory.load().getAnyRef(configPath));
    }

    public static Object readConfig(String configPath, String paramName, Object defaultValue) {
        Object result = (((HashMap)(ConfigFactory.load().getAnyRef(configPath))).get(paramName));

        return (result!=null)?result:defaultValue;
    }
}
