package org.seat.application.usecases.validator;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;

import java.util.List;
import java.util.regex.Pattern;


public class SeatDataValidatorService implements IDataValidatorService {

    @Override
    public void validateInput(ExecuteDataCommand inputData) throws CustomException {

        List<String> instructionsList = inputData.getInputData();

        StringBuilder errorMessage = new StringBuilder();
        int index = 1;

        if (instructionsList.size() != 5)
            throw new CustomException("""
                    \nIncorrect input format: 
                     - only 5 lines are needed -> upper-right coordinates of the plateau, the bottom-left
                    coordinates, initial position of the first mower, movement instructions of the first mower,
                    initial position of the second mower and movement instructions of the second mower""");

        for (String line : instructionsList) {

            switch (index) {
                case 1 -> {
                    if (!Pattern.matches("\\b\\d{1,2}\\s\\d{1,2}\\b", line))
                        errorMessage.append("\n - line 1 can only have two coordinates");
                }
                case 2, 4 -> {
                    if (!Pattern.matches("\\b\\d{1,2}\\s\\d{1,2}\\s[NESW]\\b", line))
                        errorMessage.append("\n - line 2 and 4 can only have two coordinates and one direction in capital letters (N, E, S, W)");
                }
                case 3, 5 -> {
                    if (!Pattern.matches("\\b[MRL]{1,99}\\b", line))
                        errorMessage.append("\n - line 3 and 5 can only have the values “L” for left turn, “R” for right turn and ”M” for movement in capital letters");
                }
                default -> errorMessage.append("Unknown error occured");
            }

            index++;
        }

        if (!errorMessage.isEmpty()) throw new CustomException("\nIncorrect input format: " + errorMessage);
    }
}
