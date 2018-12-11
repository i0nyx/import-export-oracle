package by.intexsoft.importexport.shell;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.*;
import by.intexsoft.importexport.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Component
@AllArgsConstructor
public class ShellCommand implements CommandMarker {
    private final IImportEventService importService;
    private final IExportEventService exportService;
    private final IConvertService convertService;
    private final IGenerateDataService generateDataService;

    @CliCommand(value = "import", help = "import events to database")
    public String importCsv(@CliOption(key = {"p"}, mandatory = true, help = "enter path to file") final String path) throws IOException {
        importService.checkAndImport(path);
        log.info("import file to path {} success", path);
        return "import success";
    }

    @CliCommand(value = "export", help = "export events of database")
    public String exportToCsv(@CliOption(key = {"e"}, mandatory = true, help = "specify event type for export") final String eventType) throws IOException {
        if(StringUtil.checkTypeEvent(eventType)) {
            exportService.exportToCsv(eventType);
            return "export success";
        }
        return "export false";
    }

    @CliCommand(value = "truncate", help = "clear table by type event")
    public String truncateTable(@CliOption(key = {"t"}, mandatory = true, help = "enter event type") final String eventType) {
        if(StringUtil.checkTypeEvent(eventType)){
            convertService.chooseEventService(TypeEvent.valueOf(eventType.toUpperCase())).clearTable();
            return "truncate table " + eventType + " success";
        }
        return "truncate false";
    }

    @CliCommand(value = "generate", help = "generate data to table by type")
    public String generateData(@CliOption(key = {"type"}, mandatory = true, help = "enter event type")final String eventType,
                               @CliOption(key = {"start"}, help = "generation start year")final String startYear,
                               @CliOption(key = {"end"}, help = "generation end year")final String endYear,
                               @CliOption(key = {"count"}, help = "count of data")final Integer count) throws ParseException {
        if(StringUtil.checkTypeEvent(eventType)){
            generateDataService.generateDataByType(TypeEvent.valueOf(eventType.toUpperCase()),startYear,endYear,count);
            return "generate data success";
        }
        return "generate data false";
    }
}
