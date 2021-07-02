package pl.asap.FiszkiApp;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
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
    private String body;
}
