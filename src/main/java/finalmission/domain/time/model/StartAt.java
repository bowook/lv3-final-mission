package finalmission.domain.time.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StartAt {

    @Column(name = "time", nullable = false)
    private LocalTime value;

    public StartAt(final LocalTime value) {
        this.value = value;
    }

    //TODO : 시간 제약 검증 추가
}
