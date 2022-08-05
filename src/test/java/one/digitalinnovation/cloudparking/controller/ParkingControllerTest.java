package one.digitalinnovation.cloudparking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    private void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenfindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("jonh", "root")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        ParkingCreateDTO createDTO = new ParkingCreateDTO();
        createDTO.setColor("VERDE");
        createDTO.setLicense("STS-4821");
        createDTO.setModel("Chevrolet Onix");
        createDTO.setState("PR");



        RestAssured.given()
                .when()
                .auth().basic("jonh", "root")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("STS-4821"))
                .body("color", Matchers.equalTo("VERDE"));
    }
}