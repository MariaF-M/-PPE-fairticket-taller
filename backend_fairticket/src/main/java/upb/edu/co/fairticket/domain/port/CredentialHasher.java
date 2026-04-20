package upb.edu.co.fairticket.domain.port;

public interface CredentialHasher {
    String hash(String rawPassword); 
    boolean matches(String rawPassword, String hashedPassword); 
}
