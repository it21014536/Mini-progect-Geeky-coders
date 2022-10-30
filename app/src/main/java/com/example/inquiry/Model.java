package com.example.inquiry;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Model implements Serializable {

    @Exclude String id;
    String  Name,Email,phone,subject,message;

    public Model(){}

    public Model( String id,String Name,String Email,String phone,String subject,String message){

        this.id = id;
        this.Name =Name;
        this.Email = Email;
        this.phone = phone;
        this.subject = subject;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
