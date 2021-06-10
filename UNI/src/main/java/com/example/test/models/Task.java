package com.example.test.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "S_TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    private String title;
    private String body;


    public Task(){

    }

    public Task(String title, String body){
        this.title = title;
        this.body = body;
    }



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "developer_id", nullable = true)
    private User developer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "analyst_id", nullable = true)
    private User analyst;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tester_id", nullable = true)
    private User tester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
    private StatusTask statusTask;

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public User getAnalyst() {
        return analyst;
    }

    public void setAnalyst(User analyst) {
        this.analyst = analyst;
    }

    public User getTester() {
        return tester;
    }

    public void setTester(User tester) {
        this.tester = tester;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }


    @Override
    public String toString() {
        return "Task{" +
                "rowId=" + rowId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", developer=" + developer +
                ", analyst=" + analyst +
                ", tester=" + tester +
                ", statusTask=" + statusTask +
                '}';
    }
}