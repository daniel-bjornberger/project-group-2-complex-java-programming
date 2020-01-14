package se.iths.complexjavaproject.mudders.service;

import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;

public interface IUserService {

    User registerNewUserAccount(UserModel userModel) throws EmailExistsException;

    void saveUser(User user);

    User findUserByEmail(String email) throws BadDataException;

    void deleteUserAccount(User user);

}
