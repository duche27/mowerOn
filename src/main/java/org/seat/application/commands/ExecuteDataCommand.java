package org.seat.application.commands;

import java.util.List;

public class ExecuteDataCommand {

    private List<String> inputData;

    public ExecuteDataCommand() { }

    public ExecuteDataCommand(List<String> inputData) {
        this.inputData = inputData;
    }

    public List<String> getInputData() {
        return inputData;
    }

    public void setInputData(List<String> inputData) {
        this.inputData = inputData;
    }

}
