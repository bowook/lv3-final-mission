package finalmission.domain.time.entity;

import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Date date;

    @Embedded
    private StartAt startAt;

    public Time(final Date date, final StartAt startAt) {
        this.date = date;
        this.startAt = startAt;
    }
}
