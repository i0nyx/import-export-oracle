package by.intexsoft.importexport.util;

import java.io.File;
import java.io.FileNotFoundException;

public final class FolderUtil {
    public static boolean createIfExist(final File file) {
        if (!file.exists()) {
            file.mkdirs();
            return false;
        }
        return true;
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
