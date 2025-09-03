package finalmission.domain.member.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    @Test
    void 빈_값_검증() {
        // given
        final String value = "";

        // when & then
        Assertions.assertThatThrownBy(
                () -> new PhoneNumber(value)
        ).isInstanceOf(IllegalArgumentException.class).hasMessage("전화번호에 빈 값은 허용되지 않습니다.");
    }
}
