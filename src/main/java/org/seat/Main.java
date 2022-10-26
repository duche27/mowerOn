package org.seat;

import org.seat.application.command.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;
import org.seat.domain.service.loader.IDataLoaderService;
import org.seat.domain.service.loader.TextFileDataLoaderService;
import org.seat.domain.service.execution.IMowerService;
import org.seat.domain.service.execution.SeatStandardMowerService;


public class Main {
    public static void main(String[] args) throws CustomException {

        IDataLoaderService dataLoaderService = new TextFileDataLoaderService("input.txt");
        ExecuteDataCommand instructionsList = dataLoaderService.loadData();

        IMowerService mowerService = new SeatStandardMowerService(instructionsList);
        mowerService.executeMowerMovements();
    }
}