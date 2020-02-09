package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.DialogDao;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.rest.dto.DialogDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService {
    private final DialogDao dialogDao;
    private final AppUserDao userDao;

    @Override
    public Dialog create(Dialog dialog) {
        Optional<AppUser> userTo = userDao.findById(dialog.getUserTo().getId());
        Optional<AppUser> userFrom = userDao.findByLogin(dialog.getUser().getLogin());

        if (userTo.isPresent() && userFrom.isPresent()) {
            Dialog dialogFromDb = dialogDao.findForTwoUsers(userTo.get(), userFrom.get());
            if (dialogFromDb.getId() != 0L) {
                return dialogFromDb;
            }
            return dialogDao.save(Dialog.builder().userTo(userTo.get()).user(userFrom.get()).build());
        }
        return null;
    }
}
