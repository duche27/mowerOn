package org.seat;

import org.seat.domain.exceptions.CustomException;
import org.seat.domain.service.loader.IDataLoaderService;
import org.seat.domain.service.loader.TextFileDataLoaderService;
import org.seat.domain.service.execution.IMowerService;
import org.seat.domain.service.execution.SeatStandardMowerService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CustomException {

        IDataLoaderService dataLoaderService = new TextFileDataLoaderService("input.txt");
        List<String> instructionsList = dataLoaderService.loadData();

        IMowerService mowerService = new SeatStandardMowerService(instructionsList);
        mowerService.executeMowerMovements();
    }
}