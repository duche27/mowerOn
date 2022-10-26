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


public class SeatStandardMowerService implements IMowerService {

    private final Plateau plateau;
    private final IDataValidatorService<ExecuteDataCommand> dataValidator = new SeatDataValidatorService();

    public SeatStandardMowerService(ExecuteDataCommand inputData) throws CustomException {

        dataValidator.validateInput(inputData);

        plateau = new Plateau(inputData);
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
}
