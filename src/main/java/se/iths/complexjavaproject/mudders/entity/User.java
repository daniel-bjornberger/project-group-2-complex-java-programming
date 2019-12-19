package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.UserModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Table(name = "player_character")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @NotNull
    @NotEmpty
    @Column(name = "full_name")
    private String fullName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;


    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;

    /*TODO: Add roles to user for specific priveleges
    @Column(name = "role")
    private String role;
    */

    public UserModel toModel(){
        UserModel userModel = new UserModel();
        userModel.setEmail(getEmail());
        userModel.setFullName(getFullName());
        userModel.setPassword(getPassword());
        //userModel.setRole(getRole());
        return userModel;
    }

}
