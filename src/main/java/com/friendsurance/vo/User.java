package com.friendsurance.vo;

import javax.persistence.*;

/**
 * Encapsulates the information about user's situation.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "EMAIL")
    private String  email;

    @Column(name = "HAS_CONTRACT")
    private boolean hasContract;

    @Column(name = "FRIENDS_NUMBER")
    private int     friendsNumber;

    @Column(name = "SENT_INVITATIONS_NUMBER")
    private int     sentInvitationsNumber;


    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Is has contract boolean.
     *
     * @return the boolean
     */
    public boolean isHasContract() {
        return hasContract;
    }

    /**
     * Sets has contract.
     *
     * @param hasContract the has contract
     */
    public void setHasContract(boolean hasContract) {
        this.hasContract = hasContract;
    }

    /**
     * Sets friends number.
     *
     * @param friendsNumber the friends number
     */
    public void setFriendsNumber(int friendsNumber) {
        this.friendsNumber = friendsNumber;
    }

    /**
     * Sets sent invitations number.
     *
     * @param sentInvitationsNumber the sent invitations number
     */
    public void setSentInvitationsNumber(int sentInvitationsNumber) {
        this.sentInvitationsNumber = sentInvitationsNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Has contract boolean.
     *
     * @return the boolean
     */
    public boolean hasContract() {
        return hasContract;
    }

    /**
     * Gets friends number.
     *
     * @return the friends number
     */
    public int getFriendsNumber() {
        return friendsNumber;
    }

    /**
     * Gets sent invitations number.
     *
     * @return the sent invitations number
     */
    public int getSentInvitationsNumber() {
        return sentInvitationsNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", hasContract=" + hasContract +
                ", friendsNumber=" + friendsNumber +
                ", sentInvitationsNumber=" + sentInvitationsNumber +
                '}';
    }

}
