package pl.asap.FiszkiApp;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

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
