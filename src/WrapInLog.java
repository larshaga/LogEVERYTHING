import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class WrapInLog extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        BrowserUtil.browse("https://google.com");
    }
}
