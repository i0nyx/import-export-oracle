package by.intexsoft.importexport.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public final class FolderUtil {
    public static void createIfExist(File file){
        log.info("FOLDER: " + file.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
            log.warn("Created Folder: " + file.getAbsolutePath());
            log.warn("Copy file " + file + " into folder above.. and rerun this class!!");
        }
    }
}
