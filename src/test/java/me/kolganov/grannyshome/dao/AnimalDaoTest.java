package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Animal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с Animal ")
class AnimalDaoTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AnimalDao animalDao;

    @Test
    @DisplayName("должен сохранять и получать сущность")
    void saveAndFindTest() {
        Animal animal = new Animal();
        animal.setName("Нана");
        animal.setQuantity(1);
        animalDao.save(animal);

        Animal actualAnimal = em.find(Animal.class, animal.getId());
        assertThat(actualAnimal).isNotNull()
                .matches(s -> s.getName().equals(animal.getName()))
                .matches(s -> s.getQuantity().equals(animal.getQuantity()));
    }
}
