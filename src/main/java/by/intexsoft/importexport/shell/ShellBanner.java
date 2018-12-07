package by.intexsoft.importexport.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShellBanner extends DefaultBannerProvider {
    public String getBanner(){
        return  "=======================================" +
                OsUtils.LINE_SEPARATOR +
                "*        Import/Export events         *" +
                OsUtils.LINE_SEPARATOR +
                "=======================================" +
                OsUtils.LINE_SEPARATOR +
                "Version:" +
                this.getVersion();
    }

    public String getVersion() {
        return "1.0.0";
    }

    public String getWelcomeMessage() {
        return "";
    }

    public String getProviderName() {
        return "";
    }
}