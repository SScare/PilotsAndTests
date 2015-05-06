package kernel.data;

import java.util.UUID;

/**
 * Created by SCAR on 06.05.2015 12:06.
 * Project: PlayAkka
 */
public class SmallObject {
    private UUID uuid;

    public SmallObject() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
