package main.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public abstract class EntityTest {
    protected abstract Entity getEntity();

    protected void setId(Entity entity, String id) {
        entity.setId(id);
    }

    protected void assertId(Entity entity, boolean hasId, String id) {
        assertEquals(hasId, entity.hasId());
        assertEquals(id, entity.getId());
    }

    @Test
    public void newEntityHasEmptyId() {
        Entity entity = getEntity();
        assertId(entity, false, "");
    }

    @Test
    public void afterSettingAnId_itMustHaveTheId() {
        Entity entity = getEntity();
        setId(entity, "id");
        assertId(entity, true, "id");
    }

    @Test
    public void settingAnIdWithOnlySpacesIsNotValid() {
        Entity entity = getEntity();
        setId(entity, "   ");
        assertId(entity, false, "");
    }

    @Test
    public void settingANullIdIsNotValid() {
        Entity entity = getEntity();
        setId(entity, null);
        assertId(entity, false, "");
    }
}
