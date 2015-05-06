package kernel.data.messages;

import kernel.data.SmallObject;

/**
 * Created by Sergey Sopin on 06.05.2015 12:21.
 * Project: PlayAkka
 */
public class SmallProcessingRequest {
    public final SmallObject smallObject;

    public SmallProcessingRequest(SmallObject smallObject) {
        this.smallObject = smallObject;
    }
}
