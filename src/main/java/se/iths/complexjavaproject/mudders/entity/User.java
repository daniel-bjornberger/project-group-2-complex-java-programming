package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.UserModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Table(name = "user")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public UserModel toModel(){
        UserModel userModel = new UserModel();
        userModel.setEmail(getEmail());
        userModel.setFullName(getFullName());
        userModel.setPassword(getPassword());
        return userModel;
    }

}
