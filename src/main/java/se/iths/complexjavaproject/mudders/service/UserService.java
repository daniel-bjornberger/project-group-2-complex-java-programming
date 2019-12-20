package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserModel userModel) throws EmailExistsException {

        if (emailExist(userModel.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  userModel.getEmail());
        }
        else {
            // the rest of the registration operation

            final User user = new User();
            user.setFullName(userModel.getFullName());
            user.setEmail(userModel.getEmail());
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));

            return userRepository.save(user);
        }
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
