package pl.lodz.p.zzpj.gitignore;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import pl.lodz.p.zzpj.gitignore.dialog.Dialog;
import pl.lodz.p.zzpj.gitignore.dialog.TechnologySelectionDialog;
import pl.lodz.p.zzpj.gitignore.file.ContentCreator;
import pl.lodz.p.zzpj.gitignore.notification.Notification;
import pl.lodz.p.zzpj.gitignore.webapi.GetData;
import pl.lodz.p.zzpj.gitignore.webapi.GetDataInterface;
import pl.lodz.p.zzpj.gitignore.webapi.exception.FileCreationException;
import pl.lodz.p.zzpj.gitignore.webapi.exception.WebApiException;

import java.util.List;

public class GenerateGitIgnoreAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        GetDataInterface gitIgnoreData;
        try {
            gitIgnoreData = new GetData();
        } catch (WebApiException e) {
            Notification.showError(event.getProject(), e.getMessage());
            return;
        }

        Dialog dialogWrapper = new TechnologySelectionDialog(gitIgnoreData);
        dialogWrapper.show();

        List<String> selectedTechnologies = dialogWrapper.getOkList();
        if (selectedTechnologies != null) {
            ContentCreator contentCreator = new ContentCreator(event.getProject(), gitIgnoreData);
            contentCreator.setList(selectedTechnologies);

            try {
                contentCreator.createGitIgnoreFile();
                Notification.showInfo(event.getProject(), "Successfully created .gitignore");
            } catch (FileCreationException e) {
                Notification.showError(event.getProject(), e.getMessage());
            }
        }
    }
}
