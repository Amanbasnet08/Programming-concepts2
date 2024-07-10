package com.example.rajin;

public class PizzaOrder {
    private int id;
    private String customername;
    private String mobilenumber;
    private String pizzasize;
    private int numberoftoppings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPizzasize() {
        return pizzasize;
    }

    public void setPizzasize(String pizzasize) {
        this.pizzasize = pizzasize;
    }

    public int getNumberoftoppings() {
        return numberoftoppings;
    }

    public void setNumberoftoppings(int numberoftoppings) {
        this.numberoftoppings = numberoftoppings;
    }

    public PizzaOrder(int id, String customername, String mobilenumber, String pizzasize, int numberoftoppings) {
        this.id = id;
        this.customername = customername;
        this.mobilenumber = mobilenumber;
        this.pizzasize = pizzasize;
        this.numberoftoppings = numberoftoppings;
    }
}