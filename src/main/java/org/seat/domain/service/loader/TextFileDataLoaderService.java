package org.seat.domain.service.loader;

import org.seat.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextFileDataLoaderService implements IDataLoaderService {

    private final String inputFileName;

    public TextFileDataLoaderService(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    /**
     * Loads the data from the input file
     * The file must be placed in the path src/main/resouce
     *
     * @return List<String> with the lines that have been read
     */
    @Override
    public List<String> loadData() {

        ClassLoader classLoader = Main.class.getClassLoader();
        File inputFile;
        try {
            inputFile = new File(Objects.requireNonNull(classLoader.getResource(inputFileName).getPath()));
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
