package com.alkemy.wallet.security.service.Impl;


import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.security.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements IUserAuthService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getAuthorities());
    }



    @Override
    public boolean save(UserDto userDTO) throws Exception {
        if (emailExist(userDTO.getEmail())) {
            throw new Exception("There is an account with that email address:" + userDTO.getEmail());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRoleId(userDTO.getRoleId());
        userEntity = this.userRepository.save(userEntity);

        return userEntity != null;
    }

    private boolean emailExist(String email){
       UserEntity user = this.userRepository.findByEmail(email);
       return user!=null;
    }
}
