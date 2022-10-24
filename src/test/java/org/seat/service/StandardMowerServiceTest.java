package org.seat.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StandardMowerServiceTest {

    private IMowerService mowerService;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        mowerService = new StandardMowerService(generateTestData());
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Should return the specific result for the provided movements")
    void executeMovements_dataOK_expectResultOK() {

        IMowerService mowerService1 = mowerService;
        mowerService1.executeMovements();

        assertThat(outputStreamCaptor.toString().trim()).contains("1 3 N");
        assertThat(outputStreamCaptor.toString().trim()).contains("5 1 E");
    }

    private List<String> generateTestData() {

        return List.of("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM");
    }
}