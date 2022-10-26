package org.seat;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;
import org.seat.application.usecases.loader.IDataLoaderService;
import org.seat.application.usecases.loader.TextFileDataLoaderService;
import org.seat.application.usecases.execution.IMowerService;
import org.seat.application.usecases.execution.SeatStandardMowerService;


public class Main {
    public static void main(String[] args) throws CustomException {

        IDataLoaderService dataLoaderService = new TextFileDataLoaderService("input.txt");
        ExecuteDataCommand instructionsList = dataLoaderService.loadData();

        IMowerService mowerService = new SeatStandardMowerService(instructionsList);
        mowerService.executeMowerMovements();
    }
}