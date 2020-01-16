package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.repository.RoleRepository;
import se.iths.complexjavaproject.mudders.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
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

    @Override
    public void deleteUserAccount(User user) {
        userRepository.delete(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
