package finalmission.presentation.reservation;

import finalmission.application.ReservationFacade;
import finalmission.presentation.login.dto.LoginMember;
import finalmission.presentation.reservation.dto.ReservationRequest;
import finalmission.presentation.reservation.dto.ReservationResponse;
import finalmission.presentation.reservation.dto.ReservationUpdateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
public class ReservationController {

    private final ReservationFacade reservationFacade;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody final ReservationRequest request) {
        final ReservationResponse response = reservationFacade.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservations(
            final LoginMember member,
            @PathVariable final Long id) {
        reservationFacade.delete(member, id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ReservationResponse> updateReservations(
            final LoginMember member,
            @PathVariable final Long id,
            @RequestBody final ReservationUpdateRequest request) {
        final ReservationResponse response = reservationFacade.updateReservation(id, member, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-situations")
    public ResponseEntity<List<ReservationResponse>> getCurrentSituations() {
        final List<ReservationResponse> responses = reservationFacade.getCurrentReservations();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/mine/{id}")
    public ResponseEntity<List<ReservationResponse>> getMyReservations(@PathVariable final Long id) {
        final List<ReservationResponse> responses = reservationFacade.getMyReservations(id);

        return ResponseEntity.ok(responses);
    }
}
