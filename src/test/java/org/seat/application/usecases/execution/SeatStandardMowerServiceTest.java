package org.seat.application.usecases.execution;

import org.junit.jupiter.api.*;
import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;
import org.seat.application.usecases.execution.SeatStandardMowerService;
import org.seat.domain.usecases.execution.IMowerService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeatStandardMowerServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Should return the specific result for the provided movements")
    void executeMovements_dataOK_expectResultOK() throws CustomException {

        // given
        ExecuteDataCommand executeDataCommandInputOK = new ExecuteDataCommand(
                List.of("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM")
        );

        // when
        IMowerService mowerService = new SeatStandardMowerService(executeDataCommandInputOK);
        mowerService.executeMowerMovements();

        // then
        assertThat(outputStreamCaptor.toString().trim()).contains("1 3 N");
        assertThat(outputStreamCaptor.toString().trim()).contains("5 1 E");
    }

    @Test
    @DisplayName("Should return exception due to insufficient number input data lines")
    void executeMovements_dataWithout5LinesNotOK_expectException() {

        // given
        ExecuteDataCommand executeDataCommandInputWithout5LinesNotOK = new ExecuteDataCommand(
                List.of("3 4", "1 4 S")
        );
        String expectedMessage = "only 5 lines are needed";

        // when
        CustomException exception = assertThrows(CustomException.class, () ->
            new SeatStandardMowerService(executeDataCommandInputWithout5LinesNotOK).executeMowerMovements()
        );

        String actualMessage = exception.getFault().getResultDescription();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should return exception due to mower out of boundaries")
    void executeMovements_firstMowerOutOfBoundariesNotOK_expectException() {

        // given
        ExecuteDataCommand executeDataCommandInputFirstMowerOutOfBoundariesNotOK = new ExecuteDataCommand(
                List.of("5 5", "6 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM")
        );
        String expectedMessage = "Initial position of the mower number 1 is outside the plateau";

        // when
        CustomException exception = assertThrows(CustomException.class, () ->
                new SeatStandardMowerService(executeDataCommandInputFirstMowerOutOfBoundariesNotOK).executeMowerMovements()
        );

        String actualMessage = exception.getFault().getResultDescription();

        // then
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should return exception due incorrect movements informed")
    void executeMovements_incorrectMovementsNotOK_expectException() {

        // given
        ExecuteDataCommand executeDataCommandInputIncorrectMovementsNotOK = new ExecuteDataCommand(
                List.of("5 5", "6 2 N", "ZXY", "3 3 E", "AAA")
        );
        String expectedMessage = "line 3 and 5 can only have the values “L” for left turn, “R” for right turn and ”M” for movement";

        // when
        CustomException exception = assertThrows(CustomException.class, () ->
                new SeatStandardMowerService(executeDataCommandInputIncorrectMovementsNotOK).executeMowerMovements()
        );

        String actualMessage = exception.getFault().getResultDescription();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}