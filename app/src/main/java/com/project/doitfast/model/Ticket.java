package com.project.doitfast.model;

public class Ticket {


    private int queue;
    private String name;
    private String service;
    private int minutes;

    public Ticket() {
    }

    public Ticket(int queue, String name, String service, int minutes) {
        this.queue = queue;
        this.name = name;
        this.service = service;
        this.minutes = minutes;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "queue=" + queue +
                ", name='" + name + '\'' +
                ", service='" + service + '\'' +
                ", minutes=" + minutes +
                '}';
    }
}
