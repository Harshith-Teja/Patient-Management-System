import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOkWithValidToken() {
        // 1.Arrange - set up the data that is needed for the test
        // 2.Act - perform actions like triggering a flow, making a req to an endpoint
        // 3.Assert - check if the actual response and expected response are same or not

        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = given() //Arrange
                .contentType("application/json")
                .body(loginPayload)
                .when()             //Act
                .post("/auth/login")
                .then()             //Assert
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        System.out.println("Generated token: " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidToken() {

        String loginPayload = """
                {
                    "email": "invalid_user@test.com",
                    "password": "wrongPassword"
                }
                """;

        given()
            .contentType("application/json")
            .body(loginPayload)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(401);
    }
}
