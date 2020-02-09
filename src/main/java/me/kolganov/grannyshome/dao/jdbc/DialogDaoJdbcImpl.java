package me.kolganov.grannyshome.dao.jdbc;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DialogDaoJdbcImpl implements DialogDaoJdbc {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Dialog findForTwoUsers(AppUser appUserOne, AppUser appUserTwo) {
        try {
            String sql = "select * from dialogs_view " +
                    "where (user_id_to = ? and user_id_from = ?) or " +
                    "(user_id_to = ? and user_id_from = ?)";
            return jdbcTemplate.queryForObject(
                    sql, new Object[]{appUserOne.getId(), appUserTwo.getId(),
                            appUserTwo.getId(), appUserOne.getId()}, new DialogMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }
        return Dialog.builder().build();
    }

    private static class DialogMapper implements RowMapper<Dialog> {
        @Override
        public Dialog mapRow(ResultSet resultSet, int i) throws SQLException {
            AppUser userTo = AppUser.builder()
                    .id(resultSet.getLong("user_id_to"))
                    .name(resultSet.getString("user_to_name"))
                    .build();
            AppUser userFrom = AppUser.builder()
                    .id(resultSet.getLong("user_id_from"))
                    .name(resultSet.getString("user_from_name"))
                    .build();
            return Dialog.builder()
                    .id(resultSet.getLong("id"))
                    .userTo(userTo)
                    .user(userFrom)
                    .build();
        }
    }
}
