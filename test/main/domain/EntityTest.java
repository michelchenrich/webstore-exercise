package main.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Before;
import org.junit.Test;

public abstract class EntityTest<TEntity extends Entity> {
    protected TEntity subject;

    protected abstract TEntity makeNewSubject();
    protected abstract TEntity makeSubjectWithData();
    protected abstract void assertSameData(TEntity entity, TEntity copy);

    @Before
    public void setUp() {
        subject = makeNewSubject();
    }

    @Test
    public void newEntityHasEmptyId() {
        TEntity entity = makeNewSubject();
        assertEquals(false, entity.hasId());
        assertEquals("", entity.getId());
    }

    @Test
    public void afterSettingAnId_itMustHaveTheId() {
        TEntity entity = makeNewSubject();
        entity.setId("id");
        assertEquals(true, entity.hasId());
        assertEquals("id", entity.getId());
    }

    @Test
    public void settingAnIdWithOnlySpacesIsNotValid() {
        TEntity entity = makeNewSubject();
        entity.setId("   ");
        assertEquals(false, entity.hasId());
        assertEquals("", entity.getId());
    }

    @Test
    public void settingANullIdIsNotValid() {
        TEntity entity = makeNewSubject();
        entity.setId(null);
        assertEquals(false, entity.hasId());
        assertEquals("", entity.getId());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void copyMustContainSameDataAsOriginal_butNotBeTheSame() {
        TEntity entity = makeSubjectWithData();
        TEntity copy = (TEntity) entity.copy();
        assertSameData(entity, copy);
        assertNotSame(entity, copy);
    }
}