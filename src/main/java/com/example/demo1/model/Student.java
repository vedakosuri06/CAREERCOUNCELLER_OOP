package com.example.demo1.model;

public class Student {
    private String name;
    private String gmail;
    private String rollno;

    public Student() {}

    public Student(String name, String gmail, String rollno) {
        this.name = name;
        this.gmail = gmail;
        this.rollno = rollno;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }
}
