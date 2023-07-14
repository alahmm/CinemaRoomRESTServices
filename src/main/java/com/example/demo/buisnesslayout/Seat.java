package com.example.demo.buisnesslayout;

public class Seat {
    private int row;
    private int column;
    private int price;

    public int getPrice() {
        return price;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
