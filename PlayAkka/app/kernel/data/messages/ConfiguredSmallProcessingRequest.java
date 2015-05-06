package kernel.data.messages;

import kernel.data.SmallObject;

/**
 * Created by SergeySopin on 06.05.2015 12:25.
 * Project: PlayAkka
 */
public class ConfiguredSmallProcessingRequest {
    public final SmallObject smallObject;
    public final long something = 1L;

    public ConfiguredSmallProcessingRequest(SmallObject smallObject) {
        this.smallObject = smallObject;
    }

}
