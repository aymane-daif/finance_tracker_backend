package est.essaouira.finance_tracker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Email format is invalid")
    private String email;

    @Column(nullable = false)
    private String password;

}