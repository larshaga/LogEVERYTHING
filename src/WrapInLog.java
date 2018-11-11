import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;

public class WrapInLog extends AnAction
{

    @Override
    public void actionPerformed(AnActionEvent anActionEvent)
    {
        // Gets the programming language
        Language programmingLanguage = anActionEvent.getData(CommonDataKeys.PSI_FILE).getLanguage();
        String languageTag = programmingLanguage.getDisplayName().toLowerCase();

        // Gets the users selected text
        Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedText = caretModel.getCurrentCaret().getSelectedText();

        // Gets the project and document
        final Project project = anActionEvent.getProject();
        final Document document = editor.getDocument();

        // Gets the start and the end of the user selected text
        final SelectionModel selectionModel = editor.getSelectionModel();
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();

        // Console log for Java
        final String JavaConsoleLogStart = "System.out.println(";
        final String JavaConsoleLogEnd = ");";

        // Console log for JavaScript
        final String JavaScriptConsoleLogStart = "console.log(";
        final String JavaScriptConsoleLogEnd = ");";

        if (selectedText == null || StringUtils.isEmpty(selectedText)) {
            return;
        }


        WriteCommandAction.runWriteCommandAction(project, () -> document.replaceString(start,end,JavaConsoleLogStart+selectedText+JavaConsoleLogEnd));

        // TODO: Add correct console log statement for the different languages

        // TODO: Move the console log statement for the different languages to another place

        // TODO: Print out error messages if .getLanguage produces a NullPointerException
    }
}