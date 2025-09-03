package finalmission.presentation.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import finalmission.domain.reservation.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        Long id,
        String name,
        String phoneNumber,
        String lesson,
        LocalDate date,
        @JsonFormat(pattern = "HH:mm") LocalTime time
) {

    public static ReservationResponse from(final Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getMember().getName().getValue(),
                reservation.getMember().getPhoneNumber().getValue(),
                reservation.getLesson().getName().getValue(),
                reservation.getTime().getDate().getValue(),
                reservation.getTime().getStartAt().getValue()
        );
    }
}
