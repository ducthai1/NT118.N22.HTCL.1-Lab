package com.example.lab1;

public class ModelColor {

    private static String text;
    private static int img;

    public ModelColor (String text, int img) {
        this.text = text;
        this.img = img;
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        ModelColor.text = text;
    }

    public static int getImage() {
        return img;
    }

    public static void setImage(int img) {
        ModelColor.img = img;
    }
}
