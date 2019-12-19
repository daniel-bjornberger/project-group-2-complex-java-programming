package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import se.iths.complexjavaproject.mudders.entity.Role;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.security.PasswordMatches;
import se.iths.complexjavaproject.mudders.security.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@PasswordMatches
public class UserModel {

    @NotEmpty
    @NotNull
    private String fullName;

    //TODO: Fråga björn om hjälp, ger error
    //@ValidEmail
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private String matchingPassword;

    public User toEntity(){
        User user = new User();
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setFullName(getFullName());
        return user;
    }

}
