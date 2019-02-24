package com.friendsurance.vo;

import com.friendsurance.mail.EmailService;

import javax.persistence.*;
import java.io.Serializable;

/**
 * com.friendsurance.vo
 *
 * @author Kalidass Mahalingam 2/2/2019
 */
@Entity
@Table(name = "USER_STATUS")
public class UserStatus implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "mail_type")
    private String mailType;

    @Column(name = "time_stamp")
    private String timeStamp;

    private String status;

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets mail type.
     *
     * @return the mail type
     */
    public String getMailType() {
        return mailType;
    }

    /**
     * Sets mail type.
     *
     * @param mailType the mail type
     */
    public void setMailType(String mailType) {
        this.mailType = mailType;
    }


    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
