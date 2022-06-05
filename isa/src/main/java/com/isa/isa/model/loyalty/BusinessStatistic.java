package com.isa.isa.model.loyalty;

import java.time.LocalDateTime;

public class BusinessStatistic {

    private String entityName;
    private double grade;

    private int numOfResInYear;
    private int numOfResInMonth;
    private int numOfResInWeek;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double income;

    public BusinessStatistic(){}

    public BusinessStatistic(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BusinessStatistic(String entityName, double grade, int numOfResInYear, int numOfResInMonth, int numOfResInWeek, LocalDateTime startTime, LocalDateTime endTime, double income) {
        this.entityName = entityName;
        this.grade = grade;
        this.numOfResInYear = numOfResInYear;
        this.numOfResInMonth = numOfResInMonth;
        this.numOfResInWeek = numOfResInWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.income = income;
    }

    public BusinessStatistic(String entityName, double grade, LocalDateTime startTime, LocalDateTime endTime) {
        this.entityName = entityName;
        this.grade = grade;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getNumOfResInYear() {
        return numOfResInYear;
    }

    public void setNumOfResInYear(int numOfResInYear) {
        this.numOfResInYear = numOfResInYear;
    }

    public int getNumOfResInMonth() {
        return numOfResInMonth;
    }

    public void setNumOfResInMonth(int numOfResInMonth) {
        this.numOfResInMonth = numOfResInMonth;
    }

    public int getNumOfResInWeek() {
        return numOfResInWeek;
    }

    public void setNumOfResInWeek(int numOfResInWeek) {
        this.numOfResInWeek = numOfResInWeek;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void addIncome(double newIncome){
        income += newIncome;
    }

    public void incNumOfResInYear(){
        numOfResInYear++;
    }
    public void incNumOfResInMonth(){
        numOfResInMonth++;
    }
    public void incNumOfResInWeek(){
        numOfResInWeek++;
    }

}
