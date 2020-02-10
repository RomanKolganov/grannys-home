package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.config.security.CustomUserDetailsService;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import me.kolganov.grannyshome.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
    @MockBean
    private AppUserRepository userRepository;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    private static List<AppUser> users = new ArrayList<>();
    private static List<Comment> comments = new ArrayList<>();

    @BeforeAll
    static void fillUsers() {
        Animal animal1 = Animal.builder().id(1L).name("Test animal name 1").type("type1").build();

        AppUser user1 = AppUser.builder()
                .id(1L)
                .name("Name 1")
                .login("login 1")
                .animals(Collections.singletonList(animal1))
                .build();
        AppUser user2 = AppUser.builder()
                .id(2L)
                .name("Name 2")
                .login("login 2")
                .animals(Collections.singletonList(animal1))
                .build();

        Comment comment1 = Comment.builder()
                .id(1L)
                .text("Test text 1")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .userTo(user1).userFrom(user2)
                .build();
        Comment comment2 = Comment.builder()
                .id(2L)
                .text("Test text 2")
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .userTo(user1)
                .userFrom(user2)
                .build();

        comments.add(comment1);
        comments.add(comment2);

        user1.setCommentsTo(comments);

        users.add(user1);
        users.add(user2);
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода GET (one)")
    void getOneUserTest() throws Exception {
        given(userService.getById(1)).willReturn(users.get(0));
        this.mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'Name 1', 'login': 'login 1'," +
                        "'comments':[{'id':1, 'text':'Test text 1'},{'id':2, 'text':'Test text 2'}]}"));
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postUserTest() throws Exception {
        this.mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"Name 4\", \"login\": \"login 4\", \"password\": \"password 4\"}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putUserTest() throws Exception {
        this.mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"name\": \"New Test animal name 4\", \"quantity\": 4}"))
                .andExpect(status().isOk());
    }
}
