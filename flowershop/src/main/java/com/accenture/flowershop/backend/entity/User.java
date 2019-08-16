package com.accenture.flowershop.backend.entity;

/**
 * @id int
 * @login String
 * @password String
 * @address String
 * @phone String
 * @balance String
 * @discount double
 */
public class User {
    private int id;
    private String login;
    private String password;
    private String phone;
    private String address;
    private String balance;
    private double discount;
    private static int count = 0;

    public User(String login, String phone, String address, String password) {
        this.login = login;
        this.phone = phone;
        this.address = address;
        this.password = password;
        setBalance("0.0");
        setDiscount(0.0);
        setId(++count);
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }



    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
