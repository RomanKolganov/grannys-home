package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.config.security.CustomUserDetailsService;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import me.kolganov.grannyshome.service.CommentService;
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

@DisplayName("Rest контроллер для работы с Comment ")
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @MockBean
    private AppUserRepository userRepository;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    private static List<Comment> comments = new ArrayList<>();

    @BeforeAll
    static void fillComments() {
        AppUser user1 = AppUser.builder().id(1L).name("Name 1").login("login 1").password("password 1").build();
        AppUser user2 = AppUser.builder().id(2L).name("Name 2").login("login 2").password("password 2").build();

        Comment comment1 = Comment.builder().id(1L).text("Test text 1").userTo(user1).build();
        Comment comment2 = Comment.builder().id(2L).text("Test text 2").userTo(user1).build();
        Comment comment3 = Comment.builder().id(3L).text("Test text 3").userTo(user2).build();

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postCommentTest() throws Exception {
        this.mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"title\": \"Test title 4\", \"text\": \"Test text 4\", \"userTo\": {\"id\": 1, \"name\": \"Name 1\", \"login\": \"login 1\", \"password\": \"password 1\"}}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteCommentTest() throws Exception {
        this.mockMvc.perform(delete("/comment/1"))
                .andExpect(status().isOk());
    }
}
