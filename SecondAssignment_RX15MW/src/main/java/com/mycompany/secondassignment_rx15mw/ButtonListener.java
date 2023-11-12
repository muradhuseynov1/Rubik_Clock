/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.secondassignment_rx15mw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author huseynov
 */
public class ButtonListener implements ActionListener {
    private int buttonIndex;
    private RubikClockGUI rubikClockGUI;

    public ButtonListener(RubikClockGUI rubikClockGUI, int buttonIndex) {
        this.rubikClockGUI = rubikClockGUI;
        this.buttonIndex = buttonIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rubikClockGUI.adjustClocksBasedOnButton(buttonIndex);
    }
}