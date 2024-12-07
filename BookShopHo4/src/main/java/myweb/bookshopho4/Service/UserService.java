package myweb.bookshopho4.Service;

import myweb.bookshopho4.Model.Entity.Users;
import myweb.bookshopho4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    public Boolean exsistingUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users saveUser(Users user) {
        user.setUserStatus(Users.status.ACTIVE);
        user.setRole(Users.Role.CUSTOMER);
        return userRepository.save(user);
    }
}
