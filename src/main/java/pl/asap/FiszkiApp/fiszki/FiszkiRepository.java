package pl.asap.FiszkiApp.fiszki;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.asap.FiszkiApp.users.User;

import java.util.List;
import java.util.Optional;

public interface FiszkiRepository extends JpaRepository<Fiszki, Long> {

    List<Fiszki> findByUser(Optional<User> user);

    Optional<Fiszki> findById(int id);

}
