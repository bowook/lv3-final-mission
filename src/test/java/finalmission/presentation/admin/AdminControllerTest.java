package finalmission.presentation.admin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminControllerTest {

    private String adminToken;
    private String memberToken;

    @BeforeEach
    void setUp() {
        // given
        adminToken = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Map.of("email", "admin@email.com", "password", "password"))
                .when().post("/login").getCookie("token");

        memberToken = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Map.of("email", "wooga@email.com", "password", "password"))
                .when().post("/login").getCookie("token");
    }

//    @Test
//    void 공휴일_생성_요청_테스트() {
//        // when & then
//        RestAssured.given().log().all()
//                .contentType(ContentType.JSON)
//                .cookie("token", adminToken)
//                .when().post("/admin")
//                .then().log().all()
//                .statusCode(HttpStatus.CREATED.value());
//    }

    @Test
    void 관리자가_아닌데_공휴일_생성_요청시_예외_발생() {
        // when & then
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("token", memberToken)
                .when().post("/admin")
                .then().log().all()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}
