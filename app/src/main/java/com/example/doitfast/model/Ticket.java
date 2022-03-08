package com.example.doitfast.model;

public class Ticket {


    private int queue;
    private int displayQueue;
    private String name;
    private String service;


    public Ticket() {
    }

    public Ticket(int queue, String name, String service) {
        this.queue = queue;
        this.name = name;
        this.service = service;
        this.displayQueue=queue;
    }

    public int getDisplayQueue() {
        return displayQueue;
    }

    public void subtractDisplayQueue()
    {
        this.displayQueue--;
    }

    public void addQueue() {this.queue++;}

    public void setDisplayQueue(int displayQueue) {
        this.displayQueue = displayQueue;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "queue=" + queue +
                ", name='" + name + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
