package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DailyEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Daily Report";

    @Autowired
    private DailyEmailService dailyEmailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *") //once a day
    //@Scheduled(fixedDelay = 60000) //once a minute
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String END_SENTENCE = size>1? " tasks" : " task";
        dailyEmailService.sendDailyReport(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + END_SENTENCE));
    }
}
