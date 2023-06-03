package gr.athtech.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    public Account(String firstName, String lastName, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getRole(){
        return this.role.name();
    }

}
