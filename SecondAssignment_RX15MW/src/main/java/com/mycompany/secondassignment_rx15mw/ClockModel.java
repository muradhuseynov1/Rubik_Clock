/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.secondassignment_rx15mw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author huseynov
 */
public class ClockModel extends JPanel {
    private static final int CENTER_X = 100, CENTER_Y = 70;
    private static final int CLOCK_RADIUS = 55;
    private static final Font CLOCK_FONT = new Font("TimesRoman", Font.BOLD, 20);
    private static final Color HAND_COLOR = Color.RED;
    private static final double PI = Math.PI;

    private int hour;
    private final Random random = new Random();

    public ClockModel() {
        hour = random.nextInt(12) + 1;
    }

    private void drawClockFace(Graphics g) {
        g.setFont(CLOCK_FONT);
        g.setColor(Color.white);
        g.fillOval(CENTER_X - CLOCK_RADIUS, CENTER_Y - CLOCK_RADIUS, 2 * CLOCK_RADIUS, 2 * CLOCK_RADIUS);
        g.setColor(Color.black);
        g.drawString("9", CENTER_X - 45, CENTER_Y);
        g.drawString("3", CENTER_X + 35, CENTER_Y);
        g.drawString("12", CENTER_X - 5, CENTER_Y - 35);
        g.drawString("6", CENTER_X - 5, CENTER_Y + 45);
    }

    @Override
    public void paint(Graphics g) {
        drawClockFace(g);
        int xHour, yHour;
        xHour = (int) (Math.cos(hourToAngle(hour) - PI / 2) * 30 + CENTER_X);
        yHour = (int) (Math.sin(hourToAngle(hour) - PI / 2) * 30 + CENTER_Y);

        g.setColor(HAND_COLOR);
        g.drawLine(CENTER_X, CENTER_Y, xHour, yHour);
    }

    private double hourToAngle(int hour) {
        return hour * 30 * PI / 180;
    }

    public int getTime() {
        return this.hour;
    }

    public void incrementHour() {
        hour = (hour % 12) + 1;
        repaint();
    }

    public void setTime(int time) {
        if (time >= 1 && time <= 12) {
            this.hour = time;
        }
        repaint(); 
    }
    
}