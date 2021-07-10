package pl.asap.FiszkiApp;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FiszkiRepository extends JpaRepository<Fiszki, Long> {

    List<Fiszki> findByUser(Optional<User> user);

//    List<Fiszki> getIdAndName()

}
