package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.UserModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;


    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotNull
    @NotEmpty
    @Column(name = "role")
    private String roles;

    /*@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @Column(name = "character_id")
    private List<PlayerCharacter> characters;*/

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PlayerCharacter character;

    public UserModel toModel(){
        UserModel userModel = new UserModel();
        userModel.setEmail(getEmail());
        userModel.setFullName(getFullName());
        userModel.setPassword(getPassword());
        userModel.setRole(getRoles());
        return userModel;
    }

}
