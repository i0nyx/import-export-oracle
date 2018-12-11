package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Sms;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repository.SmsRepository;
import by.intexsoft.importexport.service.IEventService;
import by.intexsoft.importexport.util.StringUtil;
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
public class SmsServiceI implements IEventService<Sms> {
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
    public void clearTable() {
        smsRepository.deleteAll();
        log.info("clear sms table success");
    }

    @Override
    public void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list) {
        Optional.ofNullable(list).orElseThrow(() -> new IllegalArgumentException("List<CSVRecords> should not be null!"));
        saveList(list.stream()
                .filter(record -> smsRepository.findSmsByCode(record.get(EVENT_FIELD_CODE)) == null)
                .map(this::buildEventByTypeOfCsvRecord)
                .collect(Collectors.toList()));
    }

    @Override
    public Sms buildEventByTypeOfCsvRecord(final CSVRecord record) {
        Optional.ofNullable(record).orElseThrow(() -> new IllegalArgumentException("should not be null"));
        return Sms.builder().code(UUID.fromString(record.get(EVENT_FIELD_CODE)).toString())
                .date(LocalDate.parse(record.get(EVENT_FIELD_DATE)))
                .build();
    }

    @Override
    public Sms buildEventByType(String code, final LocalDate localDate) {
        if(!StringUtil.checkString(code)){
            code = UUID.randomUUID().toString();
        }
        return Sms.builder().code(code).date(localDate).build();
    }
}
