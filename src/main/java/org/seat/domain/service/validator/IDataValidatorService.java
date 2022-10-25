package org.seat.domain.service.validator;

import org.seat.domain.exceptions.CustomException;

import java.util.List;

/**
 * Validates input data according to the specified input patterns
 */
public interface IDataValidatorService {

    void validateInput(List<String> inputData) throws CustomException;
}
