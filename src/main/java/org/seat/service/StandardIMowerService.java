package org.seat.service;

import org.seat.enums.Movement;
import org.seat.enums.Direction;
import org.seat.model.Mower;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StandardIMowerService implements IMowerService {

    private final List<String> inputData;
    private Integer rightBoundary;
    private Integer topBoundary;
    private List<Mower> mowers = new ArrayList<>();

    public StandardIMowerService(List<String> inputData) {

        this.inputData = inputData;

        inputValidation(inputData);

        dataInitialization(inputData);
    }

    /**
     * “L” and “R” make the mower spin 90 degrees left or
     * right respectively, without moving from its current spot. “M” means to move forward one
     * grid point and maintain the same Heading
     */
    public void executeMovements() {

        mowers.forEach(mower -> {

            for (String movement : mower.getMovements().split("")) {

                if (movement.equals(Movement.M.toString())) moveMower(mower);
                else spinMower(mower, Movement.valueOf(movement));
            }

            System.out.println(mower.getX() + " " + mower.getY() + " " + mower.getDirection());
        });
    }

    /**
     * move forward one grid point and maintain the same Heading
     * Assume that the square directly North from (X, Y) is (X, Y + 1).
     */
    private void moveMower(Mower mower) {

        switch (mower.getDirection()) {

            case N -> {
                if (mower.getY() < topBoundary) mower.setY(mower.getY() + 1);
            }

            case S -> {
                if (mower.getY() > 0) mower.setY(mower.getY() - 1);
            }

            case E -> {
                if (mower.getX() < rightBoundary) mower.setX(mower.getX() + 1);
            }

            case W -> {
                if (mower.getX() > 0) mower.setX(mower.getX() - 1);
            }
        }
    }

    /**
     * Changes the direction of the given mower
     * “L” and “R” make the mower spin 90 degrees left or
     * right respectively, without moving from its current spot
     *
     * @param movement “L” or “R”
     */
    private void spinMower(Mower mower, Movement movement) {

        if (Movement.L.equals(movement)) {
            switch (mower.getDirection()) {
                case N -> mower.setDirection(Direction.W);
                case S -> mower.setDirection(Direction.E);
                case E -> mower.setDirection(Direction.N);
                case W -> mower.setDirection(Direction.S);
            }
        } else {
            switch (mower.getDirection()) {
                case N -> mower.setDirection(Direction.E);
                case S -> mower.setDirection(Direction.W);
                case E -> mower.setDirection(Direction.S);
                case W -> mower.setDirection(Direction.N);
            }
        }
    }

    /**
     * Initializes the data related to both mowers
     *
     * @param inputData with the lines that have been read
     */
    private void dataInitialization(List<String> inputData) {

        String[] plateauBoundaries = inputData.get(0).split(" ");
        String[] firstMowerData = inputData.get(1).split(" ");
        String firstMowerMovements = inputData.get(2);
        String[] secondMowerData = inputData.get(3).split(" ");
        String secondMowerMovements = inputData.get(4);

        rightBoundary = Integer.valueOf(plateauBoundaries[0]);
        topBoundary = Integer.valueOf(plateauBoundaries[1]);
        mowers.add(new Mower(Integer.parseInt(firstMowerData[0]), Integer.parseInt(firstMowerData[1]), Direction.valueOf(firstMowerData[2]), firstMowerMovements));
        mowers.add(new Mower(Integer.parseInt(secondMowerData[0]), Integer.parseInt(secondMowerData[1]), Direction.valueOf(secondMowerData[2]), secondMowerMovements));

        int index = 1;

        for (Mower mower : mowers) {
            if (mower.getX() > rightBoundary || mower.getY() > topBoundary)
                throw new IllegalArgumentException("Initial position of the mower number " + index + " is outside the plateau");
            index++;
        }
    }

    /**
     * Validates input data
     *
     * @param inputData with the lines that have been read
     */
    private void inputValidation(List<String> inputData) {

        String errorMessage = "";
        int index = 1;

        for (String line : inputData) {

            switch (index) {
                case 1 -> {
                    if (!Pattern.matches("\\b\\d{1,2}\\s\\d{1,2}\\b", line))
                        errorMessage = errorMessage + "\nline 1 can only have two coordinates";
                }
                case 2, 4 -> {
                    if (!Pattern.matches("\\b\\d{1,2}\\s\\d{1,2}\\s[NESW]\\b", line))
                        errorMessage = errorMessage + "\nline 2 and 4 can only have two coordinates and one direction in capital letters (N, E, S, W)";
                }
                case 3, 5 -> {
                    if (!Pattern.matches("\\b[MRL]{1,99}\\b", line))
                        errorMessage = errorMessage + "\nline 3 and 5 can only have the values “L” for left turn, “R”  for right turn and ”M” for movement in capital letters";
                }
                default ->
                        errorMessage = errorMessage + "\nonly 5 lines are needed -> upper-right coordinates of the plateau, the bottom-left\n" +
                                "coordinates, initial position of the first mower, movement instructions of the first mower, initial position of the second mower and movement instructions of the second mower";
            }

            index++;
        }

        if (!errorMessage.equals("")) throw new IllegalArgumentException("\nIncorrect input format: " + errorMessage);
    }
}