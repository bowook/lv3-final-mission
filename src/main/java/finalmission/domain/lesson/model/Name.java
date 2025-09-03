package finalmission.domain.lesson.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Name {

    @Column(name = "name", nullable = false, unique = true)
    private String value;

    public Name(final String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("수업 이름에는 빈 값이 허용되지 않습니다.");
        }
    }
}
