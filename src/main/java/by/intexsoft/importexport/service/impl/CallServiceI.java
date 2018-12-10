package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Call;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repository.CallRepository;
import by.intexsoft.importexport.service.IEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_CODE;
import static by.intexsoft.importexport.constant.Constant.EVENT_FIELD_DATE;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CallServiceI implements IEventService<Call> {
    private final CallRepository callRepository;

    @Override
    public void saveList(final List<Call> list) {
        callRepository.saveAll(list);
        log.info("saveList call {}", list);
    }

    @Override
    public void save(Call call) {
        Optional.ofNullable(call).orElseThrow(() -> new IllegalArgumentException("Call should not be null"));
        callRepository.save(call);
        log.info("save call {}", call);
    }

    @Override
    public List<Call> getAll() {
        return callRepository.findAll();
    }

    @Override
    public TypeEvent getType() {
        return TypeEvent.CALL;
    }

    @Override
    public void clearTable() {
        callRepository.deleteAll();
        log.info("clear call table success");
    }

    @Override
    public void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list) {
        Optional.ofNullable(list).orElseThrow(() -> new IllegalArgumentException("List<CSVRecords> should not be null!"));
        saveList(list.stream()
                .filter(record -> callRepository.findCallByCode(record.get(EVENT_FIELD_CODE)) == null)
                .map(this::buildEventByType)
                .collect(Collectors.toList()));
    }

    @Override
    public Call buildEventByType(final CSVRecord record) {
        Optional.ofNullable(record).orElseThrow(() -> new IllegalArgumentException("should not be null!"));
        return Call.builder().code(UUID.fromString(record.get(EVENT_FIELD_CODE)).toString())
                .date(LocalDate.parse(record.get(EVENT_FIELD_DATE)))
                .build();
    }
}