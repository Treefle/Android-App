package com.example.myapplication.AlarmManager;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.Database.Repository;
import com.example.myapplication.R;
import com.example.myapplication.entities.Assessment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MyIntentService extends IntentService {
    public final String CHANNEL_ID ="Channel";
    Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                    try {
                        checkAssessmentDates();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        };
    /**
     * @param name
     * @deprecated
     */
    public MyIntentService(String name) {
        super(name);
    }
    public MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        createNotificationChannel();
        timer.schedule(hourlyTask, 1,1000*60);
    }

    public void checkAssessmentDates() throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        Repository repository = new Repository(getApplication());
        LocalDate today = LocalDate.now();
        if (!repository.getAllAssessments().isEmpty()) {
            repository.getAllAssessments().stream().iterator().forEachRemaining((Assessment assessment) -> {
                if (assessment.getStartAlerts()) {
                    try {
                        Date startDate = sdf.parse(assessment.getStartDate());
                        assert startDate != null;
                        Instant instant = startDate.toInstant();
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();
                        if (localDate.isEqual(today)) {
                            createNotification("You have an Assessment starting today.");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (assessment.getEndAlerts()) {
                    try {
                        Date endDate = sdf.parse(assessment.getEndDate());
                        assert endDate != null;
                        Instant instant = endDate.toInstant();
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();
                        if (localDate.isEqual(today)) {
                            createNotification("You have an Assessment ending today");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (!repository.getAllClasses().isEmpty()) {
            repository.getAllClasses().stream().iterator().forEachRemaining((classModel) -> {
                if (classModel.getStartAlerts()) {
                    try {
                        Date startDate = sdf.parse(classModel.getClassStart());
                        assert startDate != null;
                        Instant instant = startDate.toInstant();
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();
                        if (localDate.isEqual(today)) {
                            createNotification("You have a Class starting today.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (classModel.getEndAlerts()) {
                    try {
                        Date endDate = sdf.parse(classModel.getClassEnd());
                        assert endDate != null;
                        Instant instant = endDate.toInstant();
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();
                        if (localDate.isEqual(today)) {
                            createNotification("You have a Class ending today.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

    public void createNotification( String text){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notification);
    }


    public void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);


    }
}
