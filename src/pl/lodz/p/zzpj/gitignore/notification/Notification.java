package pl.lodz.p.zzpj.gitignore.notification;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

public class Notification {

    public static void showError(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("Custom Notification Group")
                .createNotification(content, NotificationType.ERROR)
                .setTitle("Generator gitignore")
                .notify(project);
    }

    public static void showInfo(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("Custom Notification Group")
                .createNotification(content, NotificationType.INFORMATION)
                .setTitle("Generator gitignore")
                .notify(project);
    }

}
