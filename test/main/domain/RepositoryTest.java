package main.domain;

import main.persistence.EntityNotFoundException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class RepositoryTest<FakeEntity extends Entity> {
    private Repository<FakeEntity> abstractRepository;

    protected abstract String getValidId();
    protected abstract String getInvalidId();
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
        String id = entity.getId();
        assertNotNull(id);
        assertFalse(id.isEmpty());
    }

    @Test
    public void givenEntityWithId_repositoryMustKeepItsId() {
        String id = getValidId();
        FakeEntity entity = makeEntityWithId(id);
        abstractRepository.save(entity);
        assertEquals(id, entity.getId());
    }

    @Test
    public void beforeSavingTheEntity_repositoryDoesNotHaveIt() {
        assertFalse(abstractRepository.hasWithId(getValidId()));
    }

    @Test
    public void withNullId_repositoryDoesNotHaveIt() {
        assertFalse(abstractRepository.hasWithId(null));
    }

    @Test
    public void withInvalidId_repositoryDoesNotHaveIt() {
        assertFalse(abstractRepository.hasWithId(getInvalidId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void attemptingToGetAnEntityThatDoesNotExist_resultsInException() {
        abstractRepository.getById(getValidId());
    }

    @Test
    public void afterSavingANewEntity_repositoryNowHasIt() {
        FakeEntity entity = makeNewEntity();
        abstractRepository.save(entity);
        assertTrue(abstractRepository.hasWithId(entity.getId()));
    }

    @Test
    public void afterSavingAnEntityWithId_repositoryThenHasIt() {
        String id = getValidId();
        abstractRepository.save(makeEntityWithId(id));
        assertTrue(abstractRepository.hasWithId(id));
    }

    @Test
    public void afterDeletingAnEntity_repositoryDoesNotHaveItAnymore() {
        FakeEntity entity = makeEntityWithId(getValidId());
        abstractRepository.save(entity);
        abstractRepository.deleteById(entity.getId());
        assertFalse(abstractRepository.hasWithId(getValidId()));
    }

    @Test
    public void deletingNonExistentEntitiesMustNotThrowException() {
        abstractRepository.deleteById(getValidId());
        abstractRepository.deleteById(getInvalidId());
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