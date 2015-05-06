package kernel.data.messages;

import kernel.data.SmallObject;

/**
 * Created by Sergey Sopin on 06.05.2015 12:37.
 * Project: PlayAkka
 */

public class VerySmallProcessingRequest {
    public final SmallObject smallObject;
    public final int ceSize;
    public final int segSize;

    public VerySmallProcessingRequest(SmallObject smallObject, int ceSize, int segSize) {
        this.smallObject = smallObject;
        this.ceSize = ceSize;
        this.segSize = segSize;
    }
}
