package by.intexsoft.importexport.util;

import by.intexsoft.importexport.pojo.TypeEvent;
import by.intexsoft.importexport.pojo.TypeExtension;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.isNull;

public final class StringUtil {
    public static String getStringType(final String path) {
        String name = new File(path).getName().split("_")[0];
        if(!checkTypeEvent(name)){
            throw new IllegalArgumentException("Error: wrong event -- " + name);
        }
        return name;
    }

    public static boolean checkTypeEvent(final String type){
        AtomicBoolean b = new AtomicBoolean(false);
        Arrays.stream(TypeEvent.values()).forEach(event ->{
            if(!b.get() && event.toString().equalsIgnoreCase(type)){
                b.set(true);
            }
        });
        return b.get();
    }

    private static String getFileExtension(final String path) {
        final int index = path.indexOf('.');
        return index == -1 ? null : path.substring(index + 1).toUpperCase();
    }

    public static void checkFileExtension(final String path) {
        final String extension = getFileExtension(path);
        Optional.ofNullable(extension).orElseThrow(() -> new IllegalArgumentException("file not found"));
        AtomicBoolean b = new AtomicBoolean(false);
        Arrays.stream(TypeExtension.values()).forEach(type -> {
            if (type.toString().equalsIgnoreCase(extension)) {
                b.set(true);
            }
        });
        if (!b.get()) {
            throw new IllegalArgumentException("incorrect file format -- " + extension);
        }
    }

    public static boolean checkString(final String str){
        return !isNull(str) && !str.isEmpty();
    }
}