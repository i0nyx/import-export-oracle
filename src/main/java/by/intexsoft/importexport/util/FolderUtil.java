package by.intexsoft.importexport.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;

@Slf4j
public final class FolderUtil {
    public static void createIfExist(final File file){
        log.info("CHECK FOLDER: " + file.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
            log.warn("Created Folder: " + file.getAbsolutePath());
            log.warn("Copy file " + file + " into folder above.. and rerun this class!!");
        }
    }

    public static File checkFile(final File folder, final String fileName) throws FileNotFoundException {
        File clientSecretFilePath = new File(folder, fileName);
        if (!clientSecretFilePath.exists()) {
            throw new FileNotFoundException("Please copy " + fileName
                    + " to folder: " + folder.getAbsolutePath());
        }
        return clientSecretFilePath;
    }
}
