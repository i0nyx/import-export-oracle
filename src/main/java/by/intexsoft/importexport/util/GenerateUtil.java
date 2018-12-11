package by.intexsoft.importexport.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public final class GenerateUtil {
    private static final String YEAR_PATTERN = "YYYY";
    private static final String DEFAULT_START_YEAR = "2000";
    private static final String DEFAULT_END_YEAR = "2018";

    public static LocalDate dateGenerator(String startYear, String endYear) throws ParseException {
        if(!StringUtil.checkString(startYear)){
            startYear = DEFAULT_START_YEAR;
        }
        if(!StringUtil.checkString(endYear)){
            endYear = DEFAULT_END_YEAR;
        }
        DateFormat dateFormat = new SimpleDateFormat(YEAR_PATTERN);
        Date dateFrom = dateFormat.parse(startYear);
        long timestampFrom = dateFrom.getTime();
        Date dateTo = dateFormat.parse(endYear);
        long timestampTo = dateTo.getTime();
        Random random = new Random();
        long timeRange = timestampTo - timestampFrom;
        long randomTimestamp = timestampFrom + (long) (random.nextDouble() * timeRange);
        return Instant.ofEpochMilli(randomTimestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String codeGenerator(){
        return UUID.randomUUID().toString();
    }
}
