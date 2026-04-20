package upb.edu.co.fairticket.domain.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import upb.edu.co.fairticket.domain.model.valueobjects.Email;
import upb.edu.co.fairticket.domain.model.User;
import upb.edu.co.fairticket.domain.port.UserRepository;
import upb.edu.co.fairticket.domain.exception.UserNotFoundException;

@Service
public class LoginUserUseCase {
    @Autowired 
    private UserRepository userRepository;

    // Se instancia directamente por regla estricta del framework en el taller
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User execute(String emailStr, String rawPassword) {
        Email email = new Email(emailStr);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + emailStr));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }
}
