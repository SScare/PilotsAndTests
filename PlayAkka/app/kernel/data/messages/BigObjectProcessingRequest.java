package kernel.data.messages;

import kernel.data.BigObject;

/**
 * Created by Sergey Sopin on 06.05.2015 12:11.
 * Project: PlayAkka.
 */
public class BigObjectProcessingRequest {
    public final BigObject bigObject;

    public BigObjectProcessingRequest(BigObject bigObject) {
        this.bigObject = bigObject;
    }
}
