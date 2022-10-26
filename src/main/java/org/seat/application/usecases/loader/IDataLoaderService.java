package org.seat.application.usecases.loader;

import org.seat.application.commands.ExecuteDataCommand;

public interface IDataLoaderService {

    ExecuteDataCommand loadData();
}