package by.intexsoft.importexport.service.impl;

import by.intexsoft.importexport.pojo.Call;
import by.intexsoft.importexport.pojo.Mms;
import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.repository.MmsRepository;
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
public class MmsServiceI implements IEventService<Mms> {
    private final MmsRepository mmsRepository;

    @Override
    public void saveList(final List<Mms> list) {
        mmsRepository.saveAll(list);
        log.info("saveList mms {}", list);
    }

    @Override
    public void save(final Mms mms) {
        Optional.ofNullable(mms).orElseThrow(() -> new IllegalArgumentException("Mms should not be null"));
        mmsRepository.save(mms);
        log.info("save mms {}", mms);
    }

    @Override
    public TypeEvent getType() {
        return TypeEvent.MMS;
    }

    @Override
    public void clearTable() {
        mmsRepository.deleteAll();
        log.info("truncate mms table success");
    }

    @Override
    public List<Mms> getAll() {
        return mmsRepository.findAll();
    }

    @Override
    public void convertOfCsvRecordToEventAndSave(final List<CSVRecord> list) {
        Optional.ofNullable(list).orElseThrow(() -> new IllegalArgumentException("List<CSVRecords> should not be null!"));
        saveList(list.stream()
                .filter(record -> mmsRepository.findMmsByCode(record.get(EVENT_FIELD_CODE)) == null)
                .map(this::buildEventByTypeOfCsvRecord)
                .collect(Collectors.toList()));
    }

    @Override
    public Mms buildEventByTypeOfCsvRecord(final CSVRecord record) {
        Optional.ofNullable(record).orElseThrow(() -> new IllegalArgumentException("should not be null"));
        return Mms.builder().code(UUID.fromString(record.get(EVENT_FIELD_CODE)).toString())
                .date(LocalDate.parse(record.get(EVENT_FIELD_DATE)))
                .build();
    }

    @Override
    public Mms buildEventByType(String code, LocalDate localDate) {
        if(!StringUtil.checkString(code)){
            code = UUID.randomUUID().toString();
        }
        return Mms.builder().code(code).date(localDate).build();
    }
}
