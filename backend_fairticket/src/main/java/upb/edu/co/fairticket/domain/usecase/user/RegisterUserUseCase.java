package upb.edu.co.fairticket.domain.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import upb.edu.co.fairticket.domain.model.User;
import upb.edu.co.fairticket.domain.model.valueobjects.Email;
import upb.edu.co.fairticket.domain.port.CredentialHasher;
import upb.edu.co.fairticket.domain.port.UserRepository;
import upb.edu.co.fairticket.domain.exception.DomainException;

@RequiredArgsConstructor
@Service
public class RegisterUserUseCase {

    @Autowired
    private final UserRepository userRepository;

    @Autowired 
    private CredentialHasher credentialHasher;

    public User registerBuyer(String name, String emailStr, String rawPassword) {
        Email email = new Email(emailStr);
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DomainException("Email already registered: " + emailStr);
        }
        String hash = credentialHasher.hash(rawPassword);
        // Guarda tanto el hash como la contraseña en texto plano
        User user = User.createBuyer(name, email, hash, rawPassword);
        return userRepository.save(user);
    }

    public User registerOrganizer(String name, String emailStr, String rawPassword) {
        Email email = new Email(emailStr);
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DomainException("Email already registered: " + emailStr);
        }
        String hash = credentialHasher.hash(rawPassword);
        // Guarda tanto el hash como la contraseña en texto plano
        User user = User.createOrganizer(name, email, hash, rawPassword);
        return userRepository.save(user);
    }
}
