import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.onlinecasinobackend.dto.UserRegistrationDTO;
import com.example.onlinecasinobackend.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.onlinecasinobackend.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        User newUser = new User();
        newUser.setUsername(registrationDTO.getUsername());
        newUser.setEmail(registrationDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }
}