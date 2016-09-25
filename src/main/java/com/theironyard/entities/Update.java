package com.theironyard.entities;

import javax.persistence.*;

@Entity
@Table(name = "updates")
public class Update {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    public Update() {
    }

    public Update(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}