package pl.asap.FiszkiApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class FiszkiServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FiszkiRepository fiszkiRepository;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/fiszki")
    public ResponseEntity addFiszki(@RequestHeader("username") String username, @RequestParam("name") String name,@RequestParam("body") String body){

        Optional<User> userFromDb = userRepository.findByUsername(username);

        if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Fiszki fiszka = new Fiszki(userFromDb.get(),name, body);
        Fiszki savedFiszka = fiszkiRepository.save(fiszka);

        return ResponseEntity.ok(savedFiszka);
    }

    @GetMapping("/fiszki")
    public ResponseEntity getFiszki(@RequestHeader("username") String username){

        Optional<User> userFromDb = userRepository.findByUsername(username);

        List<Fiszki> fiszkiFromDb = fiszkiRepository.findByUser(userFromDb);

        HashMap<Integer, String> items = new HashMap<>();
        for(Fiszki fiszki : fiszkiFromDb){

            items.put(fiszki.getId(), fiszki.getName());
        }
        return ResponseEntity.ok(items);
    }
}
