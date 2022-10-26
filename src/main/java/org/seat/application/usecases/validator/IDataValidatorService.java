package org.seat.application.usecases.validator;

import org.seat.application.commands.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;

/**
 * Validates input data according to the specified input patterns
 */
public interface IDataValidatorService {

    void validateInput(ExecuteDataCommand inputData) throws CustomException;
}
