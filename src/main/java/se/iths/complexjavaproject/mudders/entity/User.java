package se.iths.complexjavaproject.mudders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    /*@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @Column(name = "character_id")
    private List<PlayerCharacter> characters;*/

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PlayerCharacter character;

    /*public UserModel toModel(){
        UserModel userModel = new UserModel();
        userModel.setEmail(getEmail());
        userModel.setFullName(getFullName());
        userModel.setPassword(getPassword());
//        userModel.setRoles(getRoles());
        return userModel;
    }*/

}
