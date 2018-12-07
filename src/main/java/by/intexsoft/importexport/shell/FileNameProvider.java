package by.intexsoft.importexport.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FileNameProvider extends DefaultHistoryFileNameProvider {
    public String getHistoryFileName() {
        return "import-export-shell.log";
    }
}
