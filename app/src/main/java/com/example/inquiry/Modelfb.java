package com.example.inquiry;

public class Modelfb {

    String  id,Name,message;

    public Modelfb(){}

    public Modelfb(String id, String Name, String message){

        this.id = id;
        this.Name =Name;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
