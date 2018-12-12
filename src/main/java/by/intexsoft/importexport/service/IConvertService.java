package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

/**
 * describes the conversion method {@link TypeEvent} in {@link IEventService}
 */
public interface IConvertService {
    /**
     * Method converts {@link TypeEvent} in {@link IEventService}
     *
     * @param event type event
     * @return {@link IEventService}
     */
    IEventService chooseEventService(final TypeEvent event);
}
