package pl.lodz.p.zzpj.gitignore;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import pl.lodz.p.zzpj.gitignore.dialog.ConnectionProblemDialog;
import pl.lodz.p.zzpj.gitignore.notification.Notification;
import pl.lodz.p.zzpj.gitignore.file.ContentCreator;
import pl.lodz.p.zzpj.gitignore.webapi.GetData;
import pl.lodz.p.zzpj.gitignore.webapi.GetDataInterface;
import pl.lodz.p.zzpj.gitignore.webapi.exception.FileCreationException;
import pl.lodz.p.zzpj.gitignore.webapi.exception.WebApiException;

import java.util.ArrayList;
import java.util.Arrays;

public class GenerateGitIgnoreAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        GetDataInterface gitIgnoreData;
        try {
            gitIgnoreData = new GetData();
//            ContentCreator contentCreator = new ContentCreator(event.getProject(), gitIgnoreData);
//            contentCreator.setList(Arrays.asList("java", "symfony", "arcanist"));
//
//            contentCreator.createGitIgnoreFile();
//
//            PluginFrame pluginFrame = new PluginFrame(new ArrayList<>(gitIgnoreData.getTechnologyMap().keySet()));

            DialogWrapper dialogWrapper = new ConnectionProblemDialog(gitIgnoreData);
            dialogWrapper.show();
            
            Notification.showInfo(event.getProject(), "Successfully created .gitignore");
        } catch (WebApiException e) {
            Notification.showError(event.getProject(), "There is a connection problem with remote api");
        } //catch (FileCreationException e) {
//            Notification.showError(event.getProject(), "There was a problem during file generation");
//        }
    }
}
