package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Best regards");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("if_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/create-trello-card-mail", context);
    }

    public String dailyReportEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Best regards");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_mail", adminConfig.getCompanyMail());
        context.setVariable("show_button", true);
        return templateEngine.process("mail/daily-report-mail", context);
    }
}
