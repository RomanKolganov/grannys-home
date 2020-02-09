package me.kolganov.grannyshome.rest;

import me.kolganov.grannyshome.config.security.CustomUserDetailsService;
import me.kolganov.grannyshome.dao.AppUserRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
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
    @MockBean
    private AppUserRepository userRepository;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
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

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
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

    @WithMockUser(
            username = "test",
            password = "test",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("должен проверять наличие метода DELETE")
    void deleteOrderTest() throws Exception {
        this.mockMvc.perform(delete("/order/1"))
                .andExpect(status().isOk());
    }
}
