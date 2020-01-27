package me.kolganov.grannyshome.dao.jdbc;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageDaoJdbcImpl implements MessageDaoJdbc {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> findAllByDialog(Dialog dialog) {
        String sql = "select * from messages_view " +
                "where dialog_id = ? " +
                "and ((user_id_to = ? and user_id_from = ?) " +
                "or (user_id_to  = ? or user_id_from = ?))";
        return jdbcTemplate.query(
                sql, new Object[]{dialog.getId(),
                        dialog.getUserTo().getId(), dialog.getUserFrom().getId(),
                        dialog.getUserFrom().getId(), dialog.getUserTo().getId()}, new MessageMapper());
    }

    private static class MessageMapper implements RowMapper<Message> {
        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            AppUser userTo = AppUser.builder()
                    .id(resultSet.getLong("user_id_to"))
                    .name(resultSet.getString("user_name_to"))
                    .login(resultSet.getString("user_login_to"))
                    .build();
            AppUser userFrom = AppUser.builder()
                    .id(resultSet.getLong("user_id_from"))
                    .name(resultSet.getString("user_name_from"))
                    .login(resultSet.getString("user_login_from"))
                    .build();
            Dialog dialog = Dialog.builder().id(resultSet.getLong("dialog_id")).build();
            return Message.builder()
                    .id(resultSet.getLong("id"))
                    .dialog(dialog)
                    .text(resultSet.getString("text"))
                    .userTo(userTo)
                    .userFrom(userFrom)
                    .build();
        }
    }
}