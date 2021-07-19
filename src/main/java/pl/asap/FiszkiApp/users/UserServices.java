package pl.asap.FiszkiApp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){

        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb.isEmpty() || wrongPassword(userFromDb, user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userFromDb);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){

        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    private boolean wrongPassword(Optional<User> userFromDb, User user){
        return !userFromDb.get().getPassword().equals(user.getPassword());
    }
}
