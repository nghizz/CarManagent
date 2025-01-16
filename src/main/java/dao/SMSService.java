package dao;

public class SMSService {
    public static void sendSMS(String phone, String message) {
        System.out.println("Sending SMS to " + phone + ": " + message);
    }
}