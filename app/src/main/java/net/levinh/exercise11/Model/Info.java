package net.levinh.exercise11.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Levin on 25/03/2016.
 */
public class Info implements Serializable {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    boolean gender;
    long salary;
    ArrayList<String> arrSport;

    public Info() {
        arrSport = new ArrayList<String>();
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void  addSport(String sport)
    {
        arrSport.add(sport);
    }
    public ArrayList<String> getArrSport()
    {
        return  arrSport;
    }
}
