package com.nongviet201.cinema.core.service;

import java.util.Map;

public interface MailService {
    void sendMailConfirmRegistration(Map<String, String> data);
    void sendMailResetPassword(Map<String, String> data);
}
