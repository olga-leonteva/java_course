package ru.stqa.cources.mantis.model;

/**
 * Created by leonto on 4/25/2016.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
