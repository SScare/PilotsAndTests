package kernel.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Sergey Sopin on 06.05.2015 12:05.
 * Project: PlayAkka
 */

public class BigObject {
    private UUID uuid;
    private Map<UUID, SmallObject> smallObjectMap;

    public BigObject() {
        this.uuid = UUID.randomUUID();
        smallObjectMap = new HashMap<>();
    }

    public void addSmallObject(SmallObject object) {
        smallObjectMap.put(object.getUuid(), object);
    }

    public Map<UUID, SmallObject> getSmallObjectMap() {
        return this.smallObjectMap;
    }
}
