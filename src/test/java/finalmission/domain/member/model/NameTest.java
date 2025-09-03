package finalmission.domain.member.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void 빈_값_검증() {
        // given
        final String value = "";

        // when & then
        Assertions.assertThatThrownBy(
                () -> new Name(value)
        ).isInstanceOf(IllegalArgumentException.class).hasMessage("회원 이름에는 빈 값이 허용되지 않습니다.");
    }
}
