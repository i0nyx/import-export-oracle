package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

import java.text.ParseException;

public interface IGenerateDataService {
    void generateDataByType(final TypeEvent type, final String startYear, final String endYear, Integer count) throws ParseException;
}
