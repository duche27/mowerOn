package org.seat.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.seat.domain.exceptions.CustomException;
import org.seat.domain.service.IMowerService;
import org.seat.domain.service.StandardMowerService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StandardMowerServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    List<String> inputOK = List.of("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM");
    List<String> inputWithout5LinesNotOK = List.of("3 4", "1 4 S");
    List<String> inputFirstMowerOutOfBoundariesNotOK = List.of("5 5", "6 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM");
    List<String> inputIncorrectMovementsNotOK = List.of("5 5", "6 2 N", "ZXY", "3 3 E", "AAA");

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Should return the specific result for the provided movements")
    void executeMovements_dataOK_expectResultOK() throws CustomException {

        IMowerService mowerService = new StandardMowerService(inputOK);

        mowerService.executeMovements();

        assertThat(outputStreamCaptor.toString().trim()).contains("1 3 N");
        assertThat(outputStreamCaptor.toString().trim()).contains("5 1 E");
    }

    @Test
    @DisplayName("Should return exception due to insufficient number input data lines")
    void executeMovements_dataWithout5LinesNotOK_expectException() {

        CustomException exception = assertThrows(CustomException.class, () ->
            new StandardMowerService(inputWithout5LinesNotOK).executeMovements()
        );

        String expectedMessage = "only 5 lines are needed";
        String actualMessage = exception.getFault().getResultDescription();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should return exception due to mower out of boundaries")
    void executeMovements_firstMowerOutOfBoundariesNotOK_expectException() {

        CustomException exception = assertThrows(CustomException.class, () ->
                new StandardMowerService(inputFirstMowerOutOfBoundariesNotOK).executeMovements()
        );

        String expectedMessage = "Initial position of the mower number 1 is outside the plateau";
        String actualMessage = exception.getFault().getResultDescription();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should return exception due incorrect movements informed")
    void executeMovements_incorrectMovementsNotOK_expectException() {

        CustomException exception = assertThrows(CustomException.class, () ->
                new StandardMowerService(inputIncorrectMovementsNotOK).executeMovements()
        );

        String expectedMessage = "line 3 and 5 can only have the values “L” for left turn, “R” for right turn and ”M” for movement";
        String actualMessage = exception.getFault().getResultDescription();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}