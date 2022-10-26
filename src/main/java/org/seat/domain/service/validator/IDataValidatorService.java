package org.seat.domain.service.validator;

import org.seat.application.command.ExecuteDataCommand;
import org.seat.domain.exceptions.CustomException;

/**
 * Validates input data according to the specified input patterns
 */
public interface IDataValidatorService {

    void validateInput(ExecuteDataCommand inputData) throws CustomException;
}
