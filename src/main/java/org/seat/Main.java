package org.seat;

import org.seat.domain.exceptions.CustomException;
import org.seat.domain.service.StandardMowerService;
import org.seat.domain.service.IMowerService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, CustomException {

        List<String> instructionsList = inputLoader();

        IMowerService mowerService = new StandardMowerService(instructionsList);

        mowerService.executeMovements();
    }

    /**
     * Loads the data from the input file
     * The file must be placed in the path src/main/resouce
     *
     * @return List<String> with the lines that have been read
     */
    private static List<String> inputLoader() throws FileNotFoundException {

        ClassLoader classLoader = Main.class.getClassLoader();
        File inputFile;
        try {
            inputFile = new File(Objects.requireNonNull(classLoader.getResource("input.txt")).getPath());
        } catch (NullPointerException e) {
            throw new NullPointerException("Input file not found in the resources folder");
        }

        String str;
        List<String> instructionList = new ArrayList<>();

        // the following line means the try block takes care of closing the resource
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            while ((str = br.readLine()) != null) {
                instructionList.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instructionList;
    }
}