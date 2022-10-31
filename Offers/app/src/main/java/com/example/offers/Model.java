package com.example.offers;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Model implements Serializable {

    @Exclude String id;
    String subject,tutor,amount,description;
    public Model(){}

    public Model(String id,String subject,String tutor,String amount,String description){
        this.id = id;
        this.subject = subject;
        this.tutor = tutor;
        this.amount = amount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
