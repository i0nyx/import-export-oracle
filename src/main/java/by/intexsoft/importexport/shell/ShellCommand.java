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

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import static by.intexsoft.importexport.constant.Constant.*;
import static by.intexsoft.importexport.util.FolderUtil.createIfExist;

/**
 * A class that describes commands for working with the command line
 */
@Slf4j
@Component
@AllArgsConstructor
public class ShellCommand implements CommandMarker {
    private final IImportEventService importService;
    private final IExportEventService exportService;
    private final IConvertService convertService;
    private final IGenerateDataService generateDataService;
    private final IGoogleService googleService;

    /**
     * Method that describes the command to import data from a file into the database
     *
     * @param path specifies the path to the imported file
     * @return {@link String} message about successful or not successful import
     * @throws IOException opportunity to get an exception
     * @see #importService
     */
    @CliCommand(value = "import", help = "import events to database")
    public String importCsv(@CliOption(key = {"p"}, mandatory = true, help = "path to file") final String path) throws IOException {
        StringUtil.checkFileExtension(path);
        String typeStr = StringUtil.getStringType(path).toUpperCase();
        if (StringUtil.checkTypeEvent(typeStr)) {
            importService.importOfCsv(path, TypeEvent.valueOf(typeStr));
            return "import success";
        }
        return "import false";
    }

    /**
     * The method exports data from the database to a file
     *
     * @param eventType exported event
     * @return {@link String} message about successful or not successful export
     * @throws IOException opportunity to get an exception
     * @see #exportService
     */
    @CliCommand(value = "export", help = "export events of database")
    public String exportToCsv(@CliOption(key = {"e"}, mandatory = true, help = "specify event type for export") final String eventType,
                              @CliOption(key = {"google"}, unspecifiedDefaultValue = "false", help = "save data in google drive") final boolean b) throws IOException, GeneralSecurityException {
        if (StringUtil.checkTypeEvent(eventType)) {
            exportService.exportToCsv(TypeEvent.valueOf(eventType.toUpperCase()), b);
            return "export success";
        }
        return "export false";
    }

    /**
     * The method will clean up the table by type
     *
     * @param eventType type of table being cleared
     * @return {@link String} message about successful or not successful clean
     * @see #convertService
     */
    @CliCommand(value = "truncate", help = "clear table by type event")
    public String truncateTable(@CliOption(key = {"t"}, mandatory = true, help = "event type") final String eventType) {
        if (StringUtil.checkTypeEvent(eventType)) {
            convertService.chooseEventService(TypeEvent.valueOf(eventType.toUpperCase())).clearTable();
            return "truncate table " + eventType + " success";
        }
        return "truncate false";
    }

    /**
     * The method generates data to fill the table by type.
     *
     * @param eventType type event for specified table
     * @param startYear initial value
     * @param endYear   end value
     * @param count     count of data
     * @return {@link String} message about successful or not successful generate data
     * @throws ParseException opportunity to get an exception
     * @see #generateDataService
     */
    @CliCommand(value = "generate", help = "generate data to table by type")
    public String generateData(@CliOption(key = {"type"}, mandatory = true, help = "enter event type") final String eventType,
                               @CliOption(key = {"start"}, help = "generation start year") final String startYear,
                               @CliOption(key = {"end"}, help = "generation end year") final String endYear,
                               @CliOption(key = {"count"}, help = "count of data") final Integer count) throws ParseException {
        if (StringUtil.checkTypeEvent(eventType)) {
            generateDataService.generateDataByType(TypeEvent.valueOf(eventType.toUpperCase()), startYear, endYear, count);
            return "generate data success";
        }
        return "generate data false";
    }

    /**
     * Method check exists folder and secret file, and authorized in google
     *
     * @return {@link String} message for console
     * @throws GeneralSecurityException opportunity to get an exception
     * @throws IOException              opportunity to get an exception
     */
    @CliCommand(value = "google", help = "create CREDENTIAL and check that file 'client_secret.json' exists")
    public String checkGoogleCredential() throws GeneralSecurityException, IOException {
        File credentialFolder = new File(System.getProperty(USER_FOLDER), CREDENTIAL_FOLDER);
        if (!createIfExist(credentialFolder)) {
            return "Created Folder: " + credentialFolder.getAbsolutePath() +
                    " Copy file " + CLIENT_SECRET_FILE_NAME + " into folder above.. and rerun this command!!";
        }
        googleService.createAuthorized();
        return "File exists";
    }
}
