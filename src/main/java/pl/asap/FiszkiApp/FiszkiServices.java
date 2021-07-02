package pl.asap.FiszkiApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FiszkiServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FiszkiRepository fiszkiRepository;

    @PostMapping("/fiszki")
    public ResponseEntity addFiszki(@RequestHeader("username") String username, @RequestBody String body){

        Optional<User> userFromDb = userRepository.findByUsername(username);

        if(!userFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Fiszki fiszka = new Fiszki(userFromDb.get(), body);
        Fiszki savedFiszka = fiszkiRepository.save(fiszka);

        return ResponseEntity.ok(savedFiszka);
    }
}
