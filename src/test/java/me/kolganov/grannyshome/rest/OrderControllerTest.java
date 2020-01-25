package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import me.kolganov.grannyshome.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Rest контроллер для работы с Order ")
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    private static List<Order> orders = new ArrayList<>();
    private static Date date = new Date(System.currentTimeMillis());

    @BeforeAll
    static void fillComments() {
        AppUser user1 = AppUser.builder().id(1L).name("Name 1").login("login 1").password("password 1").build();
        AppUser user2 = AppUser.builder().id(2L).name("Name 2").login("login 2").password("password 2").build();

        Animal animal1 = Animal.builder().id(1L).name("Test animal name 1").build();
        Animal animal2 = Animal.builder().id(2L).name("Test animal name 2").build();
        Animal animal3 = Animal.builder().id(3L).name("Test animal name 3").build();

        Order order1 = Order.builder().id(1L).title("Test title 1").description("Test description 1")
                .animal(animal1).user(user1).build();
        Order order2 = Order.builder().id(2L).title("Test title 2").description("Test description 2")
                .animal(animal2).user(user2).build();
        Order order3 = Order.builder().id(3L).title("Test title 3").description("Test description 3")
                .animal(animal3).user(user1).build();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getOrderListTest() throws Exception {
        given(orderService.getAll()).willReturn(orders);

        this.mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'title': 'Test title 1', 'description': 'Test description 1', 'date_creation': " + date + ", " +
                        "'animal': {'id': 1, 'name': 'Test animal name 1', 'quantity': 1}, " +
                        "'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}, " +
                        "{'id': 2, 'title': 'Test title 2', 'description': 'Test description 2', 'date_creation': " + date + ", " +
                        "'animal': {'id': 2, 'name': 'Test animal name 2', 'quantity': 2}, " +
                        "'user': {'id': 2, 'name': 'Name 2', 'login': 'login 2', 'password': 'password 2'}}, " +
                        "{'id': 3, 'title': 'Test title 3', 'description': 'Test description 3', 'date_creation': " + date + ", " +
                        "'animal': {'id': 3, 'name': 'Test animal name 3', 'quantity': 3}, " +
                        "'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}]"));
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (all)")
    void getOrderListPaginationTest() throws Exception {
        given(orderService.getAll(0, 10, "id")).willReturn(orders);

        this.mockMvc.perform(get("/order/0/10/id"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'title': 'Test title 1', 'description': 'Test description 1', 'date_creation': " + date + ", " +
                        "'animal': {'id': 1, 'name': 'Test animal name 1', 'quantity': 1}, " +
                        "'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}, " +
                        "{'id': 2, 'title': 'Test title 2', 'description': 'Test description 2', 'date_creation': " + date + ", " +
                        "'animal': {'id': 2, 'name': 'Test animal name 2', 'quantity': 2}, " +
                        "'user': {'id': 2, 'name': 'Name 2', 'login': 'login 2', 'password': 'password 2'}}, " +
                        "{'id': 3, 'title': 'Test title 3', 'description': 'Test description 3', 'date_creation': " + date + ", " +
                        "'animal': {'id': 3, 'name': 'Test animal name 3', 'quantity': 3}, " +
                        "'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}]"));
    }

    @Test
    @DisplayName("должен проверять наличие метода GET (one)")
    void getOneOrderTest() throws Exception {
        given(orderService.getById(1)).willReturn(orders.get(0));
        this.mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'title': 'Test title 1', 'description': 'Test description 1', 'date_creation': " + date + ", " +
                        "'animal': {'id': 1, 'name': 'Test animal name 1', 'quantity': 1}, " +
                        "'user': {'id': 1, 'name': 'Name 1', 'login': 'login 1', 'password': 'password 1'}}"));
    }

    @Test
    @DisplayName("должен проверять наличие метода POST")
    void postOrderTest() throws Exception {
        this.mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"title\": \"Test title 4\", \"description\": \"Test description 4\", \"date_creation\": \"2020-17-01\", " +
                        "\"animal\": {\"id\": 2, \"name\": \"Test animal name 2\", \"quantity\": 2}, " +
                        "\"user\": {\"id\": 2, \"name\": \"Name 2\", \"login\": \"login 2\", \"password\": \"password 2\"}}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода PUT")
    void putOrderTest() throws Exception {
        this.mockMvc.perform(put("/order/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 4, \"title\": \"Test title 4\", \"description\": \"Test description 4\", \"date_creation\": \"2020-17-01\", " +
                        "\"animal\": {\"id\": 2, \"name\": \"Test animal name 2\", \"quantity\": 2}, " +
                        "\"user\": {\"id\": 2, \"name\": \"Name 2\", \"login\": \"login 2\", \"password\": \"password 2\"}}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteOrderTest() throws Exception {
        this.mockMvc.perform(delete("/order/1"))
                .andExpect(status().isOk());
    }
}
