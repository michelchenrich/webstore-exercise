package main.persistence;

import main.domain.Entity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractRepositoryTest<FakeEntity extends Entity> {
    protected Repository<FakeEntity> abstractRepository;

    protected abstract Repository<FakeEntity> getAbstractRepository();
    protected abstract FakeEntity makeNewEntity();
    protected abstract FakeEntity makeEntityWithId(String id);
    protected abstract void changeEntity(FakeEntity entity);
    protected abstract void assertEntityHasSameValues(FakeEntity original, FakeEntity saved);
    protected abstract void assertEntityDoesNotHaveSameValues(FakeEntity original, FakeEntity saved);

    @Before
    public void setUp() throws Exception {
        abstractRepository = getAbstractRepository();
    }

    @Test
    public void givenNewEntity_repositoryMustSetItsId() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        assertEquals("1", entity.getId());
    }

    @Test
    public void givenEntityWithId_repositoryMustKeepItsId() {
        FakeEntity entity = makeEntityWithId("existent id");
        abstractRepository.save(entity);
        assertEquals("existent id", entity.getId());
    }

    @Test
    public void beforeSavingTheEntity_repositoryDoesNotHaveIt() {
        assertFalse(abstractRepository.hasWithId("1"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void attemptingToGetAnEntityThatDoesNotExist_resultsInException() {
        abstractRepository.getById("1");
    }

    @Test
    public void afterSavingANewEntity_repositoryNowHasIt() {
        abstractRepository.save(makeNewEntity());
        assertTrue(abstractRepository.hasWithId("1"));
    }

    @Test
    public void afterSavingAnEntityWithId_repositoryThenHasIt() {
        abstractRepository.save(makeEntityWithId("existent id"));
        assertTrue(abstractRepository.hasWithId("existent id"));
    }

    @Test
    public void afterDeletingAnEntity_repositoryDoesNotHaveItAnymore() {
        FakeEntity entity = makeEntityWithId("existent id");
        abstractRepository.save(entity);
        abstractRepository.deleteById(entity.getId());
        assertFalse(abstractRepository.hasWithId("existent id"));
    }

    @Test
    public void theReturnedEntityMustEqualTheSavedOne_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        FakeEntity savedEntity = abstractRepository.getById(entity.getId());
        assertNotSame(entity, savedEntity);
        assertEntityHasSameValues(entity, savedEntity);
    }

    @Test
    public void theReturnedEntityMustEqualTheLastReturnedOne_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        FakeEntity savedEntity1 = abstractRepository.getById(entity.getId());
        FakeEntity savedEntity2 = abstractRepository.getById(entity.getId());
        assertNotSame(savedEntity1, savedEntity2);
        assertEntityHasSameValues(savedEntity1, savedEntity2);
    }

    @Test
    public void whenGettingAllEntities_theyMustEqualTheOnesSaved_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        int count = 0;
        for (FakeEntity savedEntity : abstractRepository.getAll()) {
            count++;
            assertNotSame(entity, savedEntity);
            assertEntityHasSameValues(entity, savedEntity);
        }
        assertEquals(1, count);
    }

    @Test
    public void changesAreKeptAfterSaving() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        changeEntity(entity);
        abstractRepository.save(entity);
        assertEntityHasSameValues(entity, abstractRepository.getById(entity.getId()));
    }

    @Test
    public void changesAreNotKeptWithoutSaving() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        changeEntity(entity);
        assertEntityDoesNotHaveSameValues(entity, abstractRepository.getById(entity.getId()));
    }

    @Test
    public void changesToTheReturnedEntityAreNotKeptWithoutSaving() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        FakeEntity savedEntity = abstractRepository.getById(entity.getId());
        changeEntity(savedEntity);
        assertEntityDoesNotHaveSameValues(savedEntity, abstractRepository.getById(entity.getId()));
    }
}