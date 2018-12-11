package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

import java.io.IOException;

public interface IImportEventService {
    void importOfCsv(final String path, final TypeEvent typeEvent) throws IOException;
}
