package pl.asap.FiszkiApp;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiszkiRepository extends JpaRepository<Fiszki, Long> {

    Optional<Fiszki> findById(int id);

}
