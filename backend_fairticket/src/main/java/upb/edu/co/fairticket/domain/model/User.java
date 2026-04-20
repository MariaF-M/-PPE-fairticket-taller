package upb.edu.co.fairticket.domain.model;

import java.util.UUID;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.AllArgsConstructor;

import upb.edu.co.fairticket.domain.model.enums.Role;
import upb.edu.co.fairticket.domain.model.valueobjects.Email;

import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private Email email;
    private String passwordHash; 
    private String rawPassword;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static User createBuyer(String name, Email email, String passwordHash, String rawPassword) {
        return new User(UUID.randomUUID(), name, email, passwordHash, rawPassword, Role.BUYER, LocalDateTime.now(), LocalDateTime.now()); 
    }

    public static User createOrganizer(String name, Email email, String passwordHash, String rawPassword) {
        return new User(UUID.randomUUID(), name, email, passwordHash, rawPassword, Role.ORGANIZER, LocalDateTime.now(), LocalDateTime.now()); 
    }

    public static User createAdmin(String name, Email email, String passwordHash, String rawPassword) {
        return new User(UUID.randomUUID(), name, email, passwordHash, rawPassword, Role.ADMIN, LocalDateTime.now(), LocalDateTime.now()); 
    }

    // Getter 
    public String getRawPassword() {
        return this.rawPassword; 
    }

    public void updateProfile(String name, Email email) {
        this.name = name;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

    public boolean isOrganizer() {
        return this.role.equals(Role.ORGANIZER);
    }

    public boolean isBuyer() {
        return this.role.equals(Role.BUYER);
    }

}
