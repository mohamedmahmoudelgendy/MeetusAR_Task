package MeetusAR.Task.Controller;

import MeetusAR.Task.Entity.User;
import MeetusAR.Task.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request) {
        User dbUser = userService.findByEmail(request.getEmail());

        if (dbUser != null && userService.passwordMatches(request.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.ok("Login successful ✅");
        }
        return ResponseEntity.status(401).body("Invalid email or password ❌");
    }
}
