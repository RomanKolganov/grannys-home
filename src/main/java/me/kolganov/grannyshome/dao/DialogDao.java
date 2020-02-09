package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.DialogDaoJdbc;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogDao extends JpaRepository<Dialog, Long>, DialogDaoJdbc {
    List<Dialog> findAllByUserOrUserTo(AppUser user, AppUser userTo);
}
