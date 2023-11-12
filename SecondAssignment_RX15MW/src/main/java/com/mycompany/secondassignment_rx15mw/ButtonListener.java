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
class ButtonListener implements ActionListener {
    private int buttonIndex;
    private RubikClockGUI rubikClockGUI;

    public ButtonListener(RubikClockGUI rubikClockGUI, int buttonIndex) {
        this.rubikClockGUI = rubikClockGUI;
        this.buttonIndex = buttonIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttonIndex) {
            case 0:
                adjustClocks(0, 1, 3, 4);
                break;
            case 1:
                adjustClocks(1, 2, 4, 5);
                break;
            case 2:
                adjustClocks(3, 4, 6, 7);
                break;
            case 3:
                adjustClocks(4, 5, 7, 8);
                break;
        }
    }


    private void adjustClocks(int... indices) {
        for (int index : indices) {
            ClockModel clock = clockModels.get(index);
            clock.incrementHour();
        }

        rubikClockGUI.updateClockPanelExt();

        if (isGameFinished()) {
            synchronized (this) {
                moveCount++; 
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(mainFrame, "Congratulations! You solved the puzzle in " + moveCount + " moves.");
                    startNewGame();
                });
            }
        } else {
            moveCount++; 
        }
    }
}