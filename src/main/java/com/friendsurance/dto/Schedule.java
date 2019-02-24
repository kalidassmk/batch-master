package com.friendsurance.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * The type Schedule.
 */
@Entity
@Table(name = "Scheduler")
public class Schedule {

    @Id
    private String id;
    private String name;
    private String description;
    private String frequency;
    @Column(name = "frequency_value")
    private int frequencyValues;
    private Date startDate;
    private String startDay;
    private String time;
    private String delay;
    private String nextRun;
    private Timestamp lastModifiedOn;
    private Timestamp lastTriggeredOn;
    private String status;


    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets frequency.
     *
     * @param frequency the frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets start day.
     *
     * @return the start day
     */
    public String getStartDay() {
        return startDay;
    }

    /**
     * Sets start day.
     *
     * @param startDay the start day
     */
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets delay.
     *
     * @return the delay
     */
    public String getDelay() {
        return delay;
    }

    /**
     * Sets delay.
     *
     * @param delay the delay
     */
    public void setDelay(String delay) {
        this.delay = delay;
    }

    /**
     * Gets next run.
     *
     * @return the next run
     */
    public String getNextRun() {
        return nextRun;
    }

    /**
     * Sets next run.
     *
     * @param nextRun the next run
     */
    public void setNextRun(String nextRun) {
        this.nextRun = nextRun;
    }

    /**
     * Gets last modified on.
     *
     * @return the last modified on
     */
    public Timestamp getLastModifiedOn() {
        return lastModifiedOn;
    }

    /**
     * Sets last modified on.
     *
     * @param lastModifiedOn the last modified on
     */
    public void setLastModifiedOn(Timestamp lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    /**
     * Gets last triggered on.
     *
     * @return the last triggered on
     */
    public Timestamp getLastTriggeredOn() {
        return lastTriggeredOn;
    }

    /**
     * Sets last triggered on.
     *
     * @param lastTriggeredOn the last triggered on
     */
    public void setLastTriggeredOn(Timestamp lastTriggeredOn) {
        this.lastTriggeredOn = lastTriggeredOn;
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

    /**
     * Gets frequency values.
     *
     * @return the frequency values
     */
    public int getFrequencyValues() {
        return frequencyValues;
    }

    /**
     * Sets frequency values.
     *
     * @param frequencyValues the frequency values
     */
    public void setFrequencyValues(int frequencyValues) {
        this.frequencyValues = frequencyValues;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", frequency='" + frequency + '\'' +
                ", frequencyValues=" + frequencyValues +
                ", startDate=" + startDate +
                ", startDay='" + startDay + '\'' +
                ", time='" + time + '\'' +
                ", delay='" + delay + '\'' +
                ", nextRun='" + nextRun + '\'' +
                ", lastModifiedOn=" + lastModifiedOn +
                ", lastTriggeredOn=" + lastTriggeredOn +
                ", status='" + status + '\'' +
                '}';
    }
}
