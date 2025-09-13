package MeetusAR.Task.Service;

import MeetusAR.Task.Entity.User;
import MeetusAR.Task.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode
        return repo.save(user);
    }

    public Optional<User> findById(Long userId){
        return repo.findById(userId);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // verify
    }
}
