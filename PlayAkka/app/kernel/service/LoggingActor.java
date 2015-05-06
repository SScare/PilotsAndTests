package kernel.service;

import akka.event.Logging;
import akka.event.LoggingAdapter;

/*
 * Created by Sergey Sopin on 06.05.15 22:57.
 */

public abstract class LoggingActor extends ServiceActor {
    protected final LoggingAdapter logger = Logging.getLogger(context().system(), this);
}
