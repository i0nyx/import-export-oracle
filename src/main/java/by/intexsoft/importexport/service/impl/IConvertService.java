package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.ConvertService;
import by.intexsoft.importexport.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

@Service
public class IConvertService implements ConvertService {
    private Map<TypeEvent, EventService> map;

    @Autowired
    public IConvertService(final List<EventService> lists) {
        map = newHashMap();
        lists.forEach(eventService -> map.put(eventService.getType(), eventService));
    }

    @Override
    public EventService chooseEventService(final TypeEvent event) {
        return map.get(event);
    }
}
