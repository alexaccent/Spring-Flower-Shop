package com.accenture.flowershop.exception;

public class OrderPaymentException extends Exception {

    public OrderPaymentException() {
    }

    public OrderPaymentException(String message) {
        super(message);
    }

    public String ordersAmountException() {
        return "Такого колличества цветов в данный момент нет";
    }

    public String balanceException() {
        return "На вашем счете недостаточно средств";
    }
}
