package pl.asap.FiszkiApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/fiszki")
//    public ResponseEntity getFiszki(@RequestBody int fiszka_id) throws JsonProcessingException {
//
//        Optional<Fiszki> fiszkaFromDb = fiszkiRepository.findById(fiszka_id);
//
//        String body = objectMapper.writeValueAsString(fiszkaFromDb.get().getBody());
//
//        if(fiszkaFromDb.isEmpty()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        return ResponseEntity.ok(body);
//    }
}
