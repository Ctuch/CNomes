package main.model;

import main.ui.Colors;

import java.awt.*;

public class Location {

    private Color coverColor;
    private Color masterColor;
    private int xPos;
    private int yPos;

    public Location(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        coverColor = Colors.TILE;
        masterColor = Colors.NEUTRAL_MASTER;
    }

    public Location(Color coverColor) {
        this.xPos = 0;
        this.yPos = 0;
        this.coverColor = coverColor;
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

    public Color getCoverColor() {
        return coverColor;
    }

    public void setCoverColor(Color coverColor) {
        this.coverColor = coverColor;
    }

    public Color getMasterColor() {
        return masterColor;
    }

    public void setMasterColor(Color masterColor) {
        this.masterColor = masterColor;
    }
}
