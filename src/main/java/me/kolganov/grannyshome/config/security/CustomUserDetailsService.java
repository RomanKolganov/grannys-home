package me.kolganov.grannyshome.config.security;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.domain.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.findByLogin(login).map(user -> User.withUsername(login)
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getRole).collect(Collectors.joining()))
        ).orElseThrow(() -> new UsernameNotFoundException(login)).build();
    }
}
