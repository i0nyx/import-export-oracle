package by.intexsoft.importexport.shell;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.service.IConvertService;
import by.intexsoft.importexport.service.IEventService;
import by.intexsoft.importexport.service.IExportEventService;
import by.intexsoft.importexport.service.IImportEventService;
import by.intexsoft.importexport.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class ShellCommand implements CommandMarker {
    private final IImportEventService importService;
    private final IExportEventService exportService;
    private final IConvertService convertService;

    @CliCommand(value = "import", help = "import events to database")
    public String importCsv(@CliOption(key = {"p"}, mandatory = true, help = "enter path to file") final String path) throws IOException {
        importService.checkAndImport(path);
        log.info("import file to path {} success", path);
        return "import success";
    }

    @CliCommand(value = "export", help = "export events of database")
    public String exportToCsv(@CliOption(key = {"e"}, mandatory = true, help = "specify event type for export") final String eventType) throws IOException {
        exportService.exportToCsv(eventType);
        log.info("export event {} success", eventType);
        return "export success";
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
    public String generateData(@CliOption(key = {"t"}, mandatory = true, help = "enter event type")final String eventType,
                               @CliOption(key = {"c"}, help = "count of data")final int count){

        return "generate data success";
    }
}
