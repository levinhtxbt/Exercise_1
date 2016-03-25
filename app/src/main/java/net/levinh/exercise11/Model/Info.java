package net.levinh.exercise11.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Levin on 25/03/2016.
 */
public class Info implements Serializable {
    String FirstName;
    String LastName;
    String Email;
    String PhoneNumber;
    Boolean Gender;
    long Salary;
    ArrayList<String> arrSport;

    public Info() {
        arrSport = new ArrayList<String>();
    }

    public long getSalary() {
        return Salary;
    }

    public void setSalary(long salary) {
        Salary = salary;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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
