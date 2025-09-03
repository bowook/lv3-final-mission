package finalmission.domain.member.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmailTest {

    @Test
    void 이메일이_빈_문자열이면_예외가_발생한다() {
        // given
        final String email = "";

        // when & then
        Assertions.assertThatThrownBy(() -> {
            new Email(email);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이메일에 빈 값은 허용되지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "woogaemail.com",
            "wooga+@.com",
            "@wooga.com",
            "wooga@wooga",
            "wooga@wooga.",
            "wooga@wooga.c"
    }, delimiter = ',')
    void 이메일_형식이_올바르지_않으면_예외가_발생한다(final String email) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
            new Email(email);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이메일 형식이 일치하지 않습니다.");
    }
}
