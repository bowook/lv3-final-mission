package finalmission.domain.member.model;

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
public class Password {

    @Column(name = "password", nullable = false)
    private String value;

    public Password(final String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("비밀번호에 빈 값은 허용되지 않습니다.");
        }
    }
}
