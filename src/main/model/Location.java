package main.model;

import java.awt.*;
import java.util.ArrayList;

public class Location {

    private Color color;
    private int xPos;
    private int yPos;

    public Location(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        color = Color.LIGHT_GRAY;
    }

    public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
