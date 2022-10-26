package org.seat.domain.service.loader;

import org.seat.application.command.ExecuteDataCommand;

public interface IDataLoaderService {

    ExecuteDataCommand loadData();
}