package main.model;

import main.ui.Colors;

import javax.swing.*;
import java.awt.*;

public class Location extends JLabel {

    private Color coverColor;
    private Color masterColor;
    //private int xPos;
    //private int yPos;

    public Location() {
        //this.xPos = xPos;
        //this.yPos = yPos;
        coverColor = Colors.TILE;
        init();
    }

    private void init() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        setText("     ");
        setOpaque(true);
        setBackground(coverColor);
        masterColor = Colors.NEUTRAL_MASTER;
    }

    public Location(Color coverColor) {
        this.coverColor = coverColor;
        init();
    }

    /*public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }*/

    /*public int getxPos() {
        return xPos;
    }*/

    /*public int getyPos() {
        return yPos;
    }*/

    public Color getCoverColor() {
        return coverColor;
    }

    public void setCoverColor(Color coverColor, int longest) {
        this.coverColor = coverColor;
        setBackground(coverColor);
        String word = "";
        for (int i = 0; i < longest; i++) {
            word += " ";
        }
        setWord(word);
    }

    public Color getMasterColor() {
        return masterColor;
    }

    public void setMasterColor(Color masterColor) {
        this.masterColor = masterColor;
        setBackground(masterColor);
    }

    public void switchTileColor() {
        if (getBackground().equals(coverColor)) {
            setBackground(masterColor);
        } else {
            setBackground(coverColor);
        }
    }

    public void setWord(String word) {
        setText(word);
    }
}
