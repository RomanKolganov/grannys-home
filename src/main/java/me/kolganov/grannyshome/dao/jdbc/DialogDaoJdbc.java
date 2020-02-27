package me.kolganov.grannyshome.dao.jdbc;

import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;

import java.util.List;

public interface DialogDaoJdbc {
    Dialog findForTwoUsers(AppUser appUserOne, AppUser appUserTwo);
}
