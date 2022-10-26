package org.seat.domain.model;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.enums.Direction;
import org.seat.domain.exceptions.CustomException;

import java.util.List;

public class Plateau {

    private Integer rightBoundary;
    private Integer topBoundary;
    private List<Mower> mowers;

    public Plateau(ExecuteDataCommand instructionsData) throws CustomException {
        initializePlateauData(instructionsData);
    }

    public Integer getRightBoundary() {
        return rightBoundary;
    }

    public Integer getTopBoundary() {
        return topBoundary;
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    private void initializePlateauData(ExecuteDataCommand instructionsData) throws CustomException {

        List<String> instructionsList = instructionsData.getInputData();

        String[] plateauBoundaries = instructionsList.get(0).split(" ");
        String[] firstMowerData = instructionsList.get(1).split(" ");
        String firstMowerMovements = instructionsList.get(2);
        String[] secondMowerData = instructionsList.get(3).split(" ");
        String secondMowerMovements = instructionsList.get(4);

        rightBoundary = Integer.valueOf(plateauBoundaries[0]);
        topBoundary = Integer.valueOf(plateauBoundaries[1]);
        mowers = List.of(
                new Mower(Integer.parseInt(firstMowerData[0]), Integer.parseInt(firstMowerData[1]), Direction.valueOf(firstMowerData[2]), firstMowerMovements),
                new Mower(Integer.parseInt(secondMowerData[0]), Integer.parseInt(secondMowerData[1]), Direction.valueOf(secondMowerData[2]), secondMowerMovements)
        );

        int index = 1;

        for (Mower mower : mowers) {
            if (mower.getX() > rightBoundary || mower.getY() > topBoundary)
                throw new CustomException("Initial position of the mower number " + index + " is outside the plateau");
            index++;
        }
    }
}
