package org.seat.domain.usecases.validator;

import org.seat.domain.exceptions.CustomException;

/**
 * Validates input data according to the specified input patterns
 */
public interface IDataValidatorService<T> {

    void validateInput(T inputData) throws CustomException;
}
