package by.intexsoft.importexport.service;

import by.intexsoft.importexport.pojo.TypeEvent;

import java.text.ParseException;

/**
 * Generate data for table
 */
public interface IGenerateDataService {
    /**
     * Method generate data for table by special type
     *
     * @param type      event type
     * @param startYear initial value
     * @param endYear   end value
     * @param count     count data
     * @throws ParseException opportunity to get an exception
     */
    void generateDataByType(final TypeEvent type, final String startYear, final String endYear, Integer count) throws ParseException;
}
