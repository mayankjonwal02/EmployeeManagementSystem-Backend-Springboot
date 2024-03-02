package com.mayank.employwise_backend.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {

    @Id
    private String employeeID;

    private String employeeName;
    private String phoneNumber;
    private String email;
    private String reportsTo;
    private String profileImage;
    public Employee() {
    }
    
    public Employee(String employeeID, String employeeName, String phoneNumber, String email, String reportsTo,
            String profileImage) {
        this.employeeID =  UUID.randomUUID().toString();
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportsTo = reportsTo;
        this.profileImage = profileImage;
    }

    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getReportsTo() {
        return reportsTo;
    }
    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }
    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
    

    




}
