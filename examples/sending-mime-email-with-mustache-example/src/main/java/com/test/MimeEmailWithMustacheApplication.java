package com.test;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import jakarta.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEmailTools
public class MimeEmailWithMustacheApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(MimeEmailWithMustacheApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void sendEmail() throws UnsupportedEncodingException, InterruptedException, CannotSendEmailException, URISyntaxException {
        testService.sendMimeEmailWithMustache();

        close();
    }

    private void close() {
        TimerTask shutdownTask = new TimerTask() {
            @Override
            public void run() {
                ((AbstractApplicationContext) applicationContext).close();
            }
        };
        Timer shutdownTimer = new Timer();
        shutdownTimer.schedule(shutdownTask, TimeUnit.SECONDS.toMillis(3));
    }

}