package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.service.UserService;
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
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private static List<AppUser> users = new ArrayList<>();

    @BeforeAll
    static void fillUsers() {
        AppUser user1 = AppUser.builder().id(1L).name("Name 1").login("login 1").password("password 1").build();
        AppUser user2 = AppUser.builder().id(2L).name("Name 2").login("login 2").password("password 2").build();
        AppUser user3 = AppUser.builder().id(3L).name("Name 3").login("login 3").password("password 3").build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getAllUsersTest() throws Exception {
        given(userService.getAll()).willReturn(users);

        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}, " +
                        "{'id': 2, 'name': 'Name 2', 'login': 'login 2', 'password': 'password 2'}, " +
                        "{'id': 3, 'name': 'Name 3', 'login': 'login 3', 'password': 'password 3'}]"));
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (one)")
    void getOneUserTest() throws Exception {
        given(userService.getById(2)).willReturn(users.get(1));
        this.mockMvc.perform(get("/user/2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 2, 'name': 'Name 2', 'login': 'login 2', 'password': 'password 2'}"));
    }

    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postUserTest() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"Name 4\", \"login\": \"login 4\", \"password\": \"password 4\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putUserTest() throws Exception {
        this.mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"New Test animal name 4\", \"quantity\": 4}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteUserTest() throws Exception {
        this.mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk());
    }
}
