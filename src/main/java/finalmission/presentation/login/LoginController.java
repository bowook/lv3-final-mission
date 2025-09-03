package finalmission.presentation.login;

import finalmission.application.AuthService;
import finalmission.presentation.login.dto.LoginRequest;
import finalmission.presentation.login.dto.LoginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/login")
@RestController
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(
            @RequestBody final LoginRequest loginRequest,
            final HttpServletResponse response
    ) {

        final String accessToken = authService.createToken(
                loginRequest.email(),
                loginRequest.password()
        );

        final Cookie cookie = new Cookie("token", accessToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponse(accessToken));
    }
}

