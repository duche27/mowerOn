package org.seat.application.usecases.execution;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;
import org.seat.domain.enums.Movement;
import org.seat.domain.enums.Direction;
import org.seat.domain.model.Mower;
import org.seat.domain.model.Plateau;
import org.seat.application.usecases.validator.SeatDataValidatorService;
import org.seat.domain.usecases.execution.IMowerService;
import org.seat.domain.usecases.validator.IDataValidatorService;

import java.util.List;


public class SeatStandardMowerService implements IMowerService {

    private final Plateau plateau;
    private final IDataValidatorService<ExecuteDataCommand> dataValidator = new SeatDataValidatorService();

    public SeatStandardMowerService(ExecuteDataCommand inputData) throws CustomException {

        dataValidator.validateInput(inputData);

        plateau = initializePlateau(inputData);
    }

    /**
     * “L” and “R” make the mower spin 90 degrees left or
     * right respectively, without moving from its current spot. “M” means to move forward one
     * grid point and maintain the same Heading
     */
    public void executeMowerMovements() {

        plateau.getMowers().forEach(mower -> {

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
                if (mower.getY() < plateau.getTopBoundary()) mower.setY(mower.getY() + 1);
            }

            case S -> {
                if (mower.getY() > 0) mower.setY(mower.getY() - 1);
            }

            case E -> {
                if (mower.getX() < plateau.getRightBoundary()) mower.setX(mower.getX() + 1);
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
     * creates the plateau object for this concrete movement received as an input data
     */
    private Plateau initializePlateau(ExecuteDataCommand inputData) throws CustomException {

        List<String> instructionsList = inputData.getInputData();
        Plateau plateau = new Plateau();

        String[] plateauBoundaries = instructionsList.get(0).split(" ");
        String[] firstMowerData = instructionsList.get(1).split(" ");
        String firstMowerMovements = instructionsList.get(2);
        String[] secondMowerData = instructionsList.get(3).split(" ");
        String secondMowerMovements = instructionsList.get(4);

        plateau.setRightBoundary(Integer.valueOf(plateauBoundaries[0]));
        plateau.setTopBoundary(Integer.valueOf(plateauBoundaries[1]));
        plateau.setMowers(List.of(
                new Mower(Integer.parseInt(firstMowerData[0]), Integer.parseInt(firstMowerData[1]), Direction.valueOf(firstMowerData[2]), firstMowerMovements),
                new Mower(Integer.parseInt(secondMowerData[0]), Integer.parseInt(secondMowerData[1]), Direction.valueOf(secondMowerData[2]), secondMowerMovements)
        ));

        int index = 1;

        for (Mower mower : plateau.getMowers()) {
            if (mower.getX() > plateau.getRightBoundary() || mower.getY() > plateau.getTopBoundary())
                throw new CustomException("Initial position of the mower number " + index + " is outside the plateau");
            index++;
        }

        return plateau;
    }
}
