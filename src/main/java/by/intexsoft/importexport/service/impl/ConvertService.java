package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * {@inheritDoc}
 */
@Service
public class ConvertService implements by.intexsoft.importexport.service.IConvertService {
    private Map<TypeEvent, IEventService> map;

    @Autowired
    public ConvertService(final List<IEventService> lists) {
        map = newHashMap();
        lists.forEach(eventService -> map.put(eventService.getType(), eventService));
    }

    @Override
    public IEventService chooseEventService(final TypeEvent event) {
        return map.get(event);
    }
}
