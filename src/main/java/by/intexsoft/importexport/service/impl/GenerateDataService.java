package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Event;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.IEventService;
import by.intexsoft.importexport.service.IGenerateDataService;
import by.intexsoft.importexport.util.GenerateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class GenerateDataService implements IGenerateDataService {
    private IConvertService convertService;
    private static final int DEFAULT_COUNT = 20;

    @Override
    public void generateDataByType(final TypeEvent type, final String startYear,
                                   final String endYear, Integer count) throws ParseException {
        count = count == null ? DEFAULT_COUNT : count;
        IEventService eventService = convertService.chooseEventService(type);
        List<Event> events = newArrayList();
        for (int i = 0; i < count; i++){
            events.add(eventService.buildEventByType(GenerateUtil.codeGenerator(), GenerateUtil.dateGenerator(startYear, endYear)));
        }
        eventService.saveList(events);
    }
}
