package com.marqueserick.cloudparking.controller;

import com.marqueserick.cloudparking.controller.dto.ParkingDTOCreate;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase{

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = port;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .auth().basic("user","erick@121212")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void whenCreateThenCheckIsCreated(){
        ParkingDTOCreate parkingDTO = new ParkingDTOCreate();
        parkingDTO.setLicense("EBC-1995");
        parkingDTO.setColor("Vermelho");
        parkingDTO.setModel("VW");
        parkingDTO.setModel("Gol");
        parkingDTO.setState("SP");
        RestAssured.given()
                .auth().basic("user","erick@121212")
                .when()
                .body(parkingDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("license", equalTo("EBC-1995"))
                .body("color", equalTo("Vermelho"));
    }
}