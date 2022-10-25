package org.seat;

import org.seat.domain.exceptions.CustomException;
import org.seat.domain.service.dataLoader.IDataLoaderService;
import org.seat.domain.service.dataLoader.TextFileDataLoaderService;
import org.seat.domain.service.movementExecution.IMowerService;
import org.seat.domain.service.movementExecution.SeatStandardMowerService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CustomException {

        IDataLoaderService dataLoaderService = new TextFileDataLoaderService("input.txt");
        List<String> instructionsList = dataLoaderService.loadData();

        IMowerService mowerService = new SeatStandardMowerService(instructionsList);

        mowerService.executeMowerMovements();
    }
}