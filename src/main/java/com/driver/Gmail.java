package com.driver;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.Triple;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity;
    private ArrayList<Triple<Date, String, String>> Inbox; //triple of date (Date), sender (String), message (String)
    private ArrayList<Triple<Date, String, String>> Trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new ArrayList<>();
        this.Trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size() == inboxCapacity){
            Triple<Date, String, String> oldestMail = Inbox.get(0);
            Inbox.remove(0);
            Trash.add(oldestMail);
        }
        Triple<Date, String, String> mail = Triple.of(date, sender, message);
        Inbox.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in the inbox, move the mail to trash, else do nothing
        int index = -1;
        for(int i = 0; i<Inbox.size(); i++){
            if(message.equals(Inbox.get(i).getRight())){
                index = i;
                break;
            }
        }

        if(index != -1){
            Trash.add(Inbox.get(index));
            Inbox.remove(index);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the latest message present in the inbox
        if(Inbox.isEmpty())
            return null;
        return Inbox.get(Inbox.size()-1).getRight();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the oldest message present in the inbox
        if(Inbox.isEmpty())
            return null;
        return Inbox.get(0).getRight();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of emails between given dates
        //It is guaranteed that start date <= end date
        int cnt = 0;
        for(int i = 0; i<Inbox.size(); i++){
            if((Inbox.get(i).getLeft().compareTo(start) >= 0) && (Inbox.get(i).getLeft().compareTo(end) <= 0)){
                cnt += 1;
            }
        }
        return cnt;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }
}

/*

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {
    private String emailId;
    private String password;

    private ArrayList<Mail> inboxMailList;
    private ArrayList<Mail> trashMailList;

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    @Override
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.emailId = emailId;
        this.inboxCapacity = inboxCapacity;
        this.password = "Accio@123";

        this.inboxMailList = new ArrayList<>();
        this.trashMailList = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxMailList.size() >= inboxCapacity){
            Mail oldEmail = inboxMailList.get(0);
            inboxMailList.remove(0);
            trashMailList.add(oldEmail);
        }
        Mail newMail = new Mail(date, sender, message);
        inboxMailList.add(newMail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<inboxMailList.size();i++){
            if(inboxMailList.get(i).message.equals(message)){
                trashMailList.add(inboxMailList.get(i));
                inboxMailList.remove(i);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inboxMailList.isEmpty()) return null;
        return inboxMailList.get(inboxMailList.size() - 1).message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inboxMailList.isEmpty()) return null;
        return inboxMailList.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date, both dates included
//        int s = 0;
//        int e = inboxMailList.size()-1;
//        for(int i=0;i<inboxMailList.size();i++){
//            Mail m = inboxMailList.get(i);
//            if(start.equals(m.date)){
//                s = i;
//            }
//            if(end.equals(m.date)){
//                e = i;
//            }
//        }
//        return (e - s + 1);
        int count = 0;
        for(int i = 0; i<inboxMailList.size(); i++){
            if((inboxMailList.get(i).date.compareTo(start) >= 0) && (inboxMailList.get(i).date.compareTo(end) <= 0)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxMailList.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashMailList.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashMailList.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }

}

class Mail {
    public Date date;
    public String sender;
    public String message;

    public Mail(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}

*/
