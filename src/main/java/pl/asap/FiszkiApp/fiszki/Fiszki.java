package pl.asap.FiszkiApp.fiszki;

import lombok.*;
import pl.asap.FiszkiApp.users.User;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "fiszki")
@Getter
@Setter
@ToString
public class Fiszki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    private String name;

    @NonNull
    private String body;
}
