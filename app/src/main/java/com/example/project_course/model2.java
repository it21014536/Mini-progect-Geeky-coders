package com.example.project_course;

public class model2 {
    String id,Title,sub,desc,fee,Time ,phone,Conducted;
    public model2(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConducted() {
        return Conducted;
    }

    public void setConducted(String conducted) {
        Conducted = conducted;
    }

    public model2(String id, String title, String sub, String fee, String desc){

        this.id=id;
        this.Title=title;
        this.sub=sub;
        this.fee=fee;
        this.desc=desc;


    }
}
