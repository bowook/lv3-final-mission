package finalmission.infrastructure.reservation.repository;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.member.entity.Member;
import finalmission.domain.member.model.Email;
import finalmission.domain.member.model.Name;
import finalmission.domain.member.model.Password;
import finalmission.domain.member.model.PhoneNumber;
import finalmission.domain.member.model.Role;
import finalmission.domain.reservation.entity.Reservation;
import finalmission.domain.reservation.repository.ReservationRepository;
import finalmission.domain.time.entity.Time;
import finalmission.domain.time.model.Date;
import finalmission.domain.time.model.StartAt;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FakeReservationRepository implements ReservationRepository {

    private final Map<Long, Reservation> database = new HashMap<>();
    private final AtomicLong id = new AtomicLong(1);

    public FakeReservationRepository() {
        final Reservation reservation = new Reservation(
                new Member(
                        new Email("email@email.com"),
                        new Password("password"),
                        new Name("우가"),
                        new PhoneNumber("010-1234-5678"),
                        Role.USER
                ),
                new Lesson(
                        new finalmission.domain.lesson.model.Name("하체운동")
                ),
                new Time(
                        new Date(LocalDate.of(2025, 6, 18)),
                        new StartAt(LocalTime.of(20, 20))
                )
        );

        database.put(id.getAndIncrement(), reservation);
    }

    @Override
    public Reservation save(final Reservation reservation) {
        database.put(id.get(), reservation);
        final Reservation savedReservation = database.get(id.get());
        id.incrementAndGet();
        return savedReservation;
    }

    @Override
    public void deleteByMemberAndId(final Member member, final Long id) {
        database.entrySet().removeIf(entry ->
                entry.getKey().equals(id) && entry.getValue().getMember().getEmail().equals(member.getEmail()));
    }

    @Override
    public List<Reservation> findAll() {
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public List<Reservation> findByMember(final Member member) {
        return database.values()
                .stream()
                .filter(reservation -> reservation.getMember().getEmail().equals(member.getEmail()))
                .toList();
    }

    @Override
    public Reservation findReservationByIdAndMember(final Long id, final Member member) {
        return database.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(id)
                        && entry.getValue().getMember().getEmail().equals(member.getEmail()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow();
    }
}
