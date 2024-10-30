package com.evently.user.service.mail;

import lombok.Data;

@Data
public class MailMessage {

    private String to;
    private String subject;
    private String body;
}
