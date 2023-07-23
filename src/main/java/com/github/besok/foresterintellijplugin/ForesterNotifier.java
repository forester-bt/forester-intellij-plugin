package com.github.besok.foresterintellijplugin;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class ForesterNotifier {
    public static void notifyError(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("FTree notification group")
                .createNotification(content, NotificationType.ERROR)
                .notify(project);
    }
    public static void notifyWarning(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("FTree notification group")
                .createNotification(content, NotificationType.WARNING)
                .notify(project);
    }
}
