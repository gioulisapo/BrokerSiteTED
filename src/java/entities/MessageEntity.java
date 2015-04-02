/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.UUID;

/**
 *
 * @author Apostolis
 */
public class MessageEntity {
    private String recipient;
    private String sender_mail;
    private String subject;
    private String message;
    private boolean read;
    private String unique_id;

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }
    
    public void GenerateUnique_Id()
    {
        unique_id = UUID.randomUUID().toString();
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the sender_mail
     */
    public String getSender_mail() {
        return sender_mail;
    }

    /**
     * @param sender_mail the sender_mail to set
     */
    public void setSender_mail(String sender_mail) {
        this.sender_mail = sender_mail;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @return the unique_id
     */
    public String getUnique_id() {
        return unique_id;
    }

    /**
     * @param unique_id the unique_id to set
     */
    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;/* UUID class generates a universally unique identifier used to identify
                                        uniquely each house in database(PK) and system in general*/
    }
}
