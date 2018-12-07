package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Call;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repositories.CallRepository;
import by.intexsoft.importexport.service.EventService;
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

import static com.google.common.collect.Lists.newArrayList;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ICallService implements EventService<Call> {
    private final CallRepository callRepository;

    @Override
    @Transactional
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
    public void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list) {
        Optional.ofNullable(list).orElseThrow(() -> new IllegalArgumentException("List<CSVRecords> should not be null!"));
        saveList(list.stream()
                .filter(record -> callRepository.findCallByCode(record.get("code"))==null)
                .map(this::buildEventByType)
                .collect(Collectors.toList()));
    }

    @Override
    public Call buildEventByType(final CSVRecord record) {
        Optional.ofNullable(record).orElseThrow(() -> new IllegalArgumentException("should not be null!"));
        return Call.builder().code(UUID.fromString(record.get("code")).toString())
                    .date(LocalDate.parse(record.get("date")))
                    .build();
    }

    @Override
    public List<List<String>> convertToListString() {
        List<List<String>> listStr = newArrayList();
        getAll().forEach(call -> {
            List<String> strings = newArrayList();
            strings.add(call.getCode());
            strings.add(call.getDate().toString());
            listStr.add(strings);
        });
        return listStr;
    }
}