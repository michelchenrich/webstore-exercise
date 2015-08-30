package main.persistence;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InMemoryRepositoryTest {
    private InMemoryRepository<FakeEntity> repository;

    private FakeEntity makeNewEntity() {
        return new FakeEntity();
    }

    private FakeEntity makeEntityWithId(String id) {
        return new FakeEntity(id);
    }

    @Before
    public void setUp() {
        repository = new InMemoryRepository<>();
    }

    @Test
    public void givenNewEntity_repositoryMustSetItsId() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        assertEquals("1", entity.getId());
    }

    @Test
    public void givenEntityWithId_repositoryMustKeepItsId() {
        FakeEntity entity = makeEntityWithId("existent id");
        repository.save(entity);
        assertEquals("existent id", entity.getId());
    }

    @Test
    public void beforeSavingTheEntity_repositoryDoesNotHaveIt() {
        assertFalse(repository.hasWithId("1"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void attemptingToGetAnEntityThatDoesNotExist_resultsInException() {
        repository.getById("1");
    }

    @Test
    public void afterSavingANewEntity_repositoryNowHasIt() {
        repository.save(makeNewEntity());
        assertTrue(repository.hasWithId("1"));
    }

    @Test
    public void afterSavingAnEntityWithId_repositoryThenHasIt() {
        repository.save(makeEntityWithId("existent id"));
        assertTrue(repository.hasWithId("existent id"));
    }

    @Test
    public void theReturnedEntityMustEqualTheSavedOne_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        FakeEntity savedEntity = repository.getById(entity.getId());
        assertNotSame(entity, savedEntity);
        assertEquals(entity, savedEntity);
    }

    @Test
    public void theReturnedEntityMustEqualTheLastReturnedOne_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        FakeEntity savedEntity1 = repository.getById(entity.getId());
        FakeEntity savedEntity2 = repository.getById(entity.getId());
        assertNotSame(savedEntity1, savedEntity2);
        assertEquals(savedEntity1, savedEntity2);
    }

    @Test
    public void whenGettingAllEntities_theyMustEqualTheOnesSaved_butNotBeTheSame() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        int count = 0;
        for (FakeEntity savedEntity : repository.getAll()) {
            count++;
            assertNotSame(entity, savedEntity);
            assertEquals(entity, savedEntity);
        }
        assertEquals(1, count);
    }

    @Test
    public void changesAreKeptAfterSaving() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        entity.setValue("value");
        repository.save(entity);
        assertEquals(entity, repository.getById(entity.getId()));
    }

    @Test
    public void changesAreNotKeptWithoutSaving() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        entity.setValue("value");
        assertNotEquals(entity, repository.getById(entity.getId()));
    }

    @Test
    public void changesToTheReturnedEntityAreNotKeptWithoutSaving() {
        FakeEntity entity = makeNewEntity();
        repository.save(entity);
        FakeEntity savedEntity = repository.getById(entity.getId());
        savedEntity.setValue("value");
        assertNotEquals(savedEntity, repository.getById(entity.getId()));
    }
}