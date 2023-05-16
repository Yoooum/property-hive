package com.prprv.property.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @author phj233
 * @since 2023/5/16 8:28
 */
@Data
public class EmailMessage implements Serializable{
    private String recipientEmail;
    private String activationCode;

    public EmailMessage(String recipientEmail, String activationCode) {
        this.recipientEmail = recipientEmail;
        this.activationCode = activationCode;
    }
}
