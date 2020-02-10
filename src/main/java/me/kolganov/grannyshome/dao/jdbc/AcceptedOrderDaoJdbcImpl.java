package me.kolganov.grannyshome.dao.jdbc;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AcceptedOrderDaoJdbcImpl implements AcceptedOrderDaoJdbc {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AcceptedOrder> findByAcceptedUserLogin(String login) {
        String sql = "select * from user_accepted_orders_view where accepted_user_login = ?";
        return jdbcTemplate.query(sql, new Object[]{login}, new AcceptedOrderMapper());
    }

    public void deleteById(long id) {
        jdbcTemplate.update("delete from user_accepted_orders where id = ?", id);
    }

    private static class AcceptedOrderMapper implements RowMapper<AcceptedOrder> {
        @Override
        public AcceptedOrder mapRow(ResultSet resultSet, int i) throws SQLException {
            Animal animal = Animal.builder()
                    .id(resultSet.getLong("animal_id"))
                    .name(resultSet.getString("animal_name"))
                    .build();
            AppUser createdUser = AppUser.builder()
                    .id(resultSet.getLong("created_user_id"))
                    .name(resultSet.getString("created_user_name"))
                    .build();
            Order order = Order.builder()
                    .id(resultSet.getLong("order_id"))
                    .title(resultSet.getString("title"))
                    .description(resultSet.getString("description"))
                    .user(createdUser)
                    .animal(animal)
                    .build();
            AppUser acceptedUser = AppUser.builder()
                    .id(resultSet.getLong("accepted_user_id"))
                    .login(resultSet.getString("accepted_user_login"))
                    .name(resultSet.getString("accepted_user_name"))
                    .build();
            return AcceptedOrder.builder()
                    .id(resultSet.getLong("id"))
                    .order(order)
                    .user(acceptedUser)
                    .build();
        }
    }
}
