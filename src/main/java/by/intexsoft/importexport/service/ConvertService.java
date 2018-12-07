package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

public interface ConvertService {
    EventService chooseEventService(final TypeEvent event);
}
