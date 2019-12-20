package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
/*
    public static User convertToEntity (String playerJson) throws BadDataException {
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            user = objectMapper.readValue(playerJson, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (user.getFullName().isBlank()) {
            throw new BadDataException("No name entered!");
        }
        if (user.getPassword().isBlank()){
            throw new BadDataException("No password entered!");
        }
        if (user.getEmail().isBlank()){
            throw new BadDataException("No email entered");
        }
        return user;
    }
*/

    @Transactional
    @Override
    public User registerNewUserAccount(UserModel userModel) throws EmailExistsException {

        if (emailExist(userModel.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + userModel.getEmail());
        } else {
            final User user = new User();
            user.setFullName(userModel.getFullName());
            user.setEmail(userModel.getEmail());
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
            return userRepository.save(user);
            // the rest of the registration operation
        }
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
