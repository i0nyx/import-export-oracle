package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Sms;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repositories.SmsRepository;
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
public class ISmsService implements EventService<Sms> {
    private final SmsRepository smsRepository;

    @Override
    public List<Sms> getAll() {
        return smsRepository.findAll();
    }

    @Override
    public void saveList(final List<Sms> list) {
        smsRepository.saveAll(list);
        log.info("saveList sms {}", list);
    }

    @Override
    public void save(final Sms sms) {
        Optional.ofNullable(sms).orElseThrow(() -> new IllegalArgumentException("Sms should not be null"));
        smsRepository.save(sms);
        log.info("save sms {}", sms);
    }

    @Override
    public TypeEvent getType() {
        return TypeEvent.SMS;
    }

    @Override
    public void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list) {
        Optional.ofNullable(list).orElseThrow(() -> new IllegalArgumentException("List<CSVRecords> should not be null!"));
        saveList(list.stream()
                .filter(record -> smsRepository.findSmsByCode(record.get("code"))==null)
                .map(this::buildEventByType)
                .collect(Collectors.toList()));
    }

    @Override
    public Sms buildEventByType(final CSVRecord record) {
        Optional.ofNullable(record).orElseThrow(() -> new IllegalArgumentException("should not be null"));
        return Sms.builder().code(UUID.fromString(record.get("uuid")).toString())
                .date(LocalDate.parse(record.get("date")))
                .build();
    }

    @Override
    public List<List<String>> convertToListString() {
        final List<Sms> listEvent = getAll();
        List<List<String>> listStr = newArrayList();
        for (Sms sms : listEvent) {
            List<String> strings = newArrayList();
            strings.add(sms.getCode());
            strings.add(sms.getDate().toString());
            listStr.add(strings);
        }
        return listStr;
    }
}
