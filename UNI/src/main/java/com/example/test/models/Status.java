package com.example.test.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "S_STATUS")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<StatusTask> statusTasks;


    public Status() {}



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

    public Set<StatusTask> getStatusTasks() {
        return statusTasks;
    }

    public void setStatusTasks(Set<StatusTask> statusTasks) {
        this.statusTasks = statusTasks;
    }

    public Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "rowId=" + rowId +
                ", name='" + name + '\'' +
                ", statusTasks=" + statusTasks +
                '}';
    }
}