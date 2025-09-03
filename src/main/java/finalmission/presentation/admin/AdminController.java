package finalmission.presentation.admin;

import finalmission.application.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createHoliday() {
        adminService.saveHoliday();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
