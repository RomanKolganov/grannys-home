package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.config.security.CustomUserDetailsService;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.service.AnimalService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
    @MockBean
    private AppUserRepository userRepository;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    private static List<Animal> animals = new ArrayList<>();

    @BeforeAll
    static void fillAnimals() {
        Animal animal1 = Animal.builder().id(1L).name("Test animal name 1").type("type1").build();
        Animal animal2 = Animal.builder().id(2L).name("Test animal name 2").type("type2").build();
        Animal animal3 = Animal.builder().id(3L).name("Test animal name 3").type("type3").build();

        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getAnimalsListTest() throws Exception {
        given(animalService.getAll()).willReturn(animals);

        this.mockMvc.perform(get("/animal"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Test animal name 1', 'type': type1}, {'id': 2, 'name': 'Test animal name 2', 'type': type2}, {'id': 3, 'name': 'Test animal name 3', 'type': type3}]"));
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postAnimalTest() throws Exception {
        this.mockMvc.perform(post("/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"Test animal name 4\", \"type\": \"type4\"}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putAnimalTest() throws Exception {
        this.mockMvc.perform(put("/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"New Test animal name 4\", \"type\": \"type4\"}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteAnimalTest() throws Exception {
        this.mockMvc.perform(delete("/animal/1"))
                .andExpect(status().isOk());
    }
}
