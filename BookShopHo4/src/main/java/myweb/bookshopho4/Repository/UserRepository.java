package myweb.bookshopho4.Repository;

import myweb.bookshopho4.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
//    List<Users> findByUserName(String username);
        Users findByEmail(String email);
        Boolean existsUsersByEmail(String email);
        Users findUsersByUserName(String userName);
        Users findById(long id);

//    Users save(Users user);
}
