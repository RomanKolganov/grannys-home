package me.kolganov.grannyshome.rest;

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
    private static List<Comment> comments = new ArrayList<>();

    @BeforeAll
    static void fillComments() {
        AppUser user1 = AppUser.builder().id(1L).name("Name 1").login("login 1").password("password 1").build();
        AppUser user2 = AppUser.builder().id(2L).name("Name 2").login("login 2").password("password 2").build();

        Comment comment1 = Comment.builder().id(1L).title("Test title 1").text("Test text 1").user(user1).build();
        Comment comment2 = Comment.builder().id(2L).title("Test title 2").text("Test text 2").user(user1).build();
        Comment comment3 = Comment.builder().id(3L).title("Test title 3").text("Test text 3").user(user2).build();

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getCommentsListTest() throws Exception {
        given(commentService.getAll()).willReturn(comments);

        this.mockMvc.perform(get("/comment"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'title': 'Test title 1', 'text': 'Test text 1', 'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}, " +
                        "{'id': 2, 'title': 'Test title 2', 'text': 'Test text 2', 'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}, " +
                        "{'id': 3, 'title': 'Test title 3', 'text': 'Test text 3', 'user': {'id': 2, 'name': 'Name 2', 'login': 'login 2', 'password': 'password 2'}}]"));
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (one)")
    void getOneCommentTest() throws Exception {
        given(commentService.getById(1)).willReturn(comments.get(0));
        this.mockMvc.perform(get("/comment/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'title': 'Test title 1', 'text': 'Test text 1', 'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}"));
    }

    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postCommentTest() throws Exception {
        this.mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"title\": \"Test title 4\", \"text\": \"Test text 4\", \"user\": {\"id\": 1, \"name\": \"Name 1\", \"login\": \"login 1\", \"password\": \"password 1\"}}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putCommentTest() throws Exception {
        this.mockMvc.perform(put("/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"title\": \"Test title 4\", \"text\": \"Test text 4\", \"user\": {\"id\": 1, \"name\": \"Name 1\", \"login\": \"login 1\", \"password\": \"password 1\"}}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteCommentTest() throws Exception {
        this.mockMvc.perform(delete("/comment/1"))
                .andExpect(status().isOk());
    }
}
