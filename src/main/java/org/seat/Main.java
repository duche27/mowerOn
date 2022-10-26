package org.seat;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;
import org.seat.application.usecases.loader.TextFileDataLoaderService;
import org.seat.application.usecases.execution.SeatStandardMowerService;
import org.seat.domain.usecases.execution.IMowerService;
import org.seat.domain.usecases.loader.IDataLoaderService;


public class Main {
    public static void main(String[] args) throws CustomException {

        IDataLoaderService<ExecuteDataCommand> dataLoaderService = new TextFileDataLoaderService("input.txt");
        ExecuteDataCommand instructionsList = dataLoaderService.loadData();

        IMowerService mowerService = new SeatStandardMowerService(instructionsList);
        mowerService.executeMowerMovements();
    }
}