package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Call;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repository.CallRepository;
import by.intexsoft.importexport.service.IEventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CallServiceTest {
    @Mock
    private CallRepository callRepository;
    private IEventService eventService;

    @BeforeEach
    void setUp() {
        callRepository = mock(CallRepository.class);
        eventService = new CallService(callRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveList() {
        List list = mock(List.class);
        eventService.saveList(list);
        verify(callRepository, times(1)).saveAll(any());
        assertNotNull(list);
    }

    @Test
    void save() {
        Call call = mock(Call.class);
        assertNotNull(call);
        eventService.save(call);
        verify(callRepository, times(1)).save(any());
    }

    @Test
    void getAll() {
        eventService.getAll();
        List<Call> calls = callRepository.findAll();
        assertNotNull(calls);
        when(callRepository.findAll()).thenReturn(calls);
    }

    @Test
    void getType() {
        TypeEvent type = eventService.getType();
        assertEquals(type, TypeEvent.CALL);

    }

    @Test
    void clearTable() {
        eventService.clearTable();
        verify(callRepository, times(1)).deleteAll();
    }

    @Test
    void convertOfCsvRecordToEventAndSave() {
    }

    @Test
    void buildEventByTypeOfCsvRecord() {
    }

    @Test
    void buildEventByType() {
    }
}