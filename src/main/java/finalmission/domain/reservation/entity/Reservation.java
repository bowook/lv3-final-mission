package finalmission.domain.reservation.entity;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.member.entity.Member;
import finalmission.domain.time.entity.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    private Time time;

    public Reservation(final Member member, final Lesson lesson, final Time time) {
        this.member = member;
        this.lesson = lesson;
        this.time = time;
    }

    public void changeTime(final Time time) {
        this.time = time;
    }
}
