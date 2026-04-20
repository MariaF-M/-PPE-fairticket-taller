package upb.edu.co.fairticket.adapter.in.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.edu.co.fairticket.adapter.in.rest.dto.request.LoginRequest;
import upb.edu.co.fairticket.adapter.in.rest.dto.request.RegisterUserRequest;
import upb.edu.co.fairticket.adapter.in.rest.dto.response.AuthResponse;
import upb.edu.co.fairticket.domain.usecase.user.LoginUserUseCase;
import upb.edu.co.fairticket.domain.usecase.user.RegisterUserUseCase;
import upb.edu.co.fairticket.infrastructure.security.JwtService;

@RestController
@RequestMapping("/api/token") 
public class AuthController {

    @Autowired 
    private RegisterUserUseCase registerUserUseCase;

    @Autowired 
    private LoginUserUseCase loginUserUseCase;

    @Autowired 
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserRequest request) {
        var user = switch (request.role().toUpperCase()) {
            case "ORGANIZER" -> registerUserUseCase.registerOrganizer(request.name(), request.email(), request.password());
            default -> registerUserUseCase.registerBuyer(request.name(), request.email(), request.password());
        };

        String token = jwtService.generateToken(user.getId(), user.getEmail().value(), user.getRole().name());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(user.getId(), user.getName(), user.getEmail().value(), user.getRole().name(), token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var user = loginUserUseCase.execute(request.email(), request.password());
        String token = jwtService.generateToken(user.getId(), user.getEmail().value(), user.getRole().name());

        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getEmail().value(), user.getRole().name(), token));
    }
}