package by.intexsoft.importexport.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
public final class CSVUtil {

    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, final Object values) {
        try {
            writeLine(w, values, DEFAULT_SEPARATOR, ' ');
        } catch (IOException e) {
            log.error("file write error: {}", e);
        }
    }

    public static void writeLine(Writer w, final Object values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCSVFormat(final String value) {
        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    public static void writeLine(Writer w, final Object values, char separators, final char customQuote) throws IOException {
        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : (List<String>) values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCSVFormat(value));
            } else {
                sb.append(customQuote).append(followCSVFormat(value)).append(customQuote);
            }
            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }
}