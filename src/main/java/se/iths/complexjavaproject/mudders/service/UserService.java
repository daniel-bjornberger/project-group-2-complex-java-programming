package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserModel userModel) throws EmailExistsException {

        if (emailExist(userModel.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "
                            + userModel.getEmail());
        }
        else {
            final User user = new User();
            user.setFullName(userModel.getFullName());
            user.setEmail(userModel.getEmail());
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
            user.setRoles(userModel.getRole());
            return userRepository.save(user);
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) throws BadDataException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new BadDataException("User not found");
        }
        return user;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
