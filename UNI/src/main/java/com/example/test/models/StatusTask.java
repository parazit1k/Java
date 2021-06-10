package com.example.test.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "S_STATUS_WORK")
public class StatusTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String name;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "statusTask")
    private Set<Task> task;

    public StatusTask(String name) {
        this.name = name;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    public StatusTask() {

    }


    @Override
    public String toString() {
        return "StatusTask{" +
                "rowId=" + rowId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", task=" + task +
                '}';
    }
}