package com.example.myexamprep.model.view;

public class UserViewModel {

    private String name;
    private int countOfOrders;

    public UserViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfOrders() {
        return countOfOrders;
    }

    public void setCountOfOrders(int countOfOrders) {
        this.countOfOrders = countOfOrders;
    }
}
