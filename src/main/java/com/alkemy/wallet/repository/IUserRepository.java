package com.alkemy.wallet.repository;

<<<<<<< HEAD
import com.alkemy.wallet.model.User;
=======
>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6
import com.alkemy.wallet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
public interface IUserRepository extends JpaRepository<UserEntity,Long> {
=======
@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUsername(String username);
>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6

    UserEntity findByUsername(String username);
}
