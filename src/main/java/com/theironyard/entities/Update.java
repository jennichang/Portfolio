package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "updates")
public class Update {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    LocalDateTime dateTime;

    public Update() {
    }

    public Update(int id, String text, LocalDateTime dateTime) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}