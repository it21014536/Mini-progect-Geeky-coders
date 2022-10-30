package com.example.project_course;

public class model1 {
    String id,Title,sub,desc;
    Integer fee;
    public model1(){}


    public model1(String id,String title, String sub, Integer fee, String desc){

        this.id=id;
        this.Title=title;
        this.sub=sub;
        this.fee=fee;
        this.desc=desc;

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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
