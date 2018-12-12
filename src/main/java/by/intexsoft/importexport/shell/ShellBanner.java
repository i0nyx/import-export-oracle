package by.intexsoft.importexport.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.FileUtils;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * {@inheritDoc}
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShellBanner extends DefaultBannerProvider {
    public String getBanner() {
        StringBuilder sb = new StringBuilder();
        sb.append(FileUtils.readBanner(this.getClass(), "/shell_banner.txt"));
        sb.append(OsUtils.LINE_SEPARATOR);
        sb.append(getVersion()).append(OsUtils.LINE_SEPARATOR);
        return sb.toString();
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