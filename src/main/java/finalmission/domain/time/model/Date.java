package finalmission.domain.time.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Date {

    @Column(name = "date", nullable = false)
    private LocalDate value;

    public Date(final LocalDate value) {
        this.value = value;
    }
}
