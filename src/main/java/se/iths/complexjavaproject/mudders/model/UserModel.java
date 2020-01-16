package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import se.iths.complexjavaproject.mudders.security.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@PasswordMatches
public class UserModel {

    @NotEmpty
    @NotNull
    private String fullName;

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private String matchingPassword;

}
