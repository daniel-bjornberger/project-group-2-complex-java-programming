package se.iths.complexjavaproject.mudders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="towns")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Town implements Serializable {

    private static final long serialVersionUID = -7055707354641685721L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    //player should have a Last Town variable that allows us to save info of the last town
    //player should automatically return to last town the next time they play.
    //Variable allows us to search List of Towns to see where they should go next.

}
