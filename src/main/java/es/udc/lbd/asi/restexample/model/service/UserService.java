package es.udc.lbd.asi.restexample.model.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.MovieUser;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.lbd.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    public List<UserDTOPublic> findAll() {
        return userDAO.findAll().stream().map(user -> new UserDTOPublic(user)).collect(Collectors.toList());
    }
    
    public UserDTOPrivate findByLogin(String login) {
        return new UserDTOPrivate(userDAO.findByLogin(login));
    }
    
    @Transactional(readOnly = false)
    public UserDTOPrivate registerUser(String login, String password, String email) throws UserLoginExistsException {
        return registerUser(login, password,email, false);
    }

    @Transactional(readOnly = false)
    public UserDTOPrivate registerUser(String login, String password,String email, boolean isAdmin) throws UserLoginExistsException {
        if (userDAO.findByLogin(login) != null) {
            throw new UserLoginExistsException("User login " + login + " already exists");
           
        }
        
        User user = new User(login,password,email, LocalDate.now());
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);

        user.setAuthority(UserAuthority.USER);
        if (isAdmin) {
            user.setAuthority(UserAuthority.ADMIN);
        }

        userDAO.save(user);
        return new UserDTOPrivate(user);
    }

    public UserDTOPrivate getCurrentUserWithAuthority() {
        String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin != null) {
            return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
        }
        return null;
    }
}
