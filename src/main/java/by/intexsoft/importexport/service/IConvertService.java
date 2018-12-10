package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

public interface IConvertService {
    IEventService chooseEventService(final TypeEvent event);
}
