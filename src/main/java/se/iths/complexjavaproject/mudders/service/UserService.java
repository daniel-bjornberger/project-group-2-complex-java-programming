package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.repository.UserRepository;

import javax.transaction.Transactional;

public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
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
                            +  userModel.getEmail());
        }
        else
           return userRepository.save(userModel.toEntity());
        // the rest of the registration operation
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
