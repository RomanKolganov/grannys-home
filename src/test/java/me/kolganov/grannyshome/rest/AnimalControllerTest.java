package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.service.AnimalService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Rest контроллер для работы с Animal ")
@WebMvcTest(AnimalController.class)
class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnimalService animalService;
    private static List<Animal> animals = new ArrayList<>();

    @BeforeAll
    static void fillAnimals() {
        Animal animal1 = Animal.builder().id(1L).name("Test animal name 1").build();
        Animal animal2 = Animal.builder().id(2L).name("Test animal name 2").build();
        Animal animal3 = Animal.builder().id(3L).name("Test animal name 3").build();

        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getAnimalsListTest() throws Exception {
        given(animalService.getAll()).willReturn(animals);

        this.mockMvc.perform(get("/animal"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Test animal name 1', 'quantity': 1}, {'id': 2, 'name': 'Test animal name 2', 'quantity': 2}, {'id': 3, 'name': 'Test animal name 3', 'quantity': 3}]"));
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (one)")
    void getOneAnimalTest() throws Exception {
        given(animalService.getById(1)).willReturn(animals.get(0));
        this.mockMvc.perform(get("/animal/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'Test animal name 1', 'quantity': 1}"));
    }

    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postAnimalTest() throws Exception {
        this.mockMvc.perform(post("/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"Test animal name 4\", \"quantity\": 4}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putAnimalTest() throws Exception {
        this.mockMvc.perform(put("/animal/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"New Test animal name 4\", \"quantity\": 4}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteAnimalTest() throws Exception {
        this.mockMvc.perform(delete("/animal/1"))
                .andExpect(status().isOk());
    }
}
