/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.secondassignment_rx15mw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huseynov
 */
public class RubikClockGUI {
    private int moveCount = 0;
    private List<ClockModel> clockModels;
    private List<JButton> controlButtons;
    private JFrame mainFrame;
    private JPanel clockPanel;

    public RubikClockGUI() {
        clockModels = new ArrayList<>(); 
        controlButtons = new ArrayList<>(); 
    
        setupMainFrame();
        setupMenu();
        startNewGame();
    }    

    private void setupMainFrame() {
        mainFrame = new JFrame("Rubik's Clock Puzzle");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(mainFrame, 
                    "Are you sure you want to exit the game?", "Exit Game?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> startNewGame());
        menuBar.add(newGameItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mainFrame, "Confirm if you want to exit", "Exit Game?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menuBar.add(exitItem);

        mainFrame.setJMenuBar(menuBar);
    }

    private void startNewGame() {
        moveCount = 0;
        clockModels.clear(); 
        controlButtons.clear(); 
        initializeClocks();
        initializeButtons();
        updateClockPanel();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }        

//    private void initializeClocks() {
//        int[] initialTimes = {9,9,12,9,9,12,12,12,12}; 
//    
//        for (int i = 0; i < 9; i++) {
//            ClockModel clock = new ClockModel();
//            clock.setTime(initialTimes[i]); 
//            clockModels.add(clock);
//        }
//    }
    
    private void initializeClocks() {
        for (int i = 0; i < 9; i++) {
            clockModels.add(new ClockModel());
        }
    }

    private void initializeButtons() {
        controlButtons.add(new JButton("Button 1"));
        controlButtons.add(new JButton("Button 2"));
        controlButtons.add(new JButton("Button 3"));
        controlButtons.add(new JButton("Button 4"));

        for (int i = 0; i < controlButtons.size(); i++) {
            JButton button = controlButtons.get(i);
            button.addActionListener(new ButtonListener(i));
        }
    }

    private void updateClockPanel() {
        clockPanel = new JPanel(new GridLayout(5, 5));
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(clockPanel);
    
        int buttonIndex = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 1 || i == 3) && (j == 1 || j == 3)) {
                    if (buttonIndex < controlButtons.size()) {
                        clockPanel.add(controlButtons.get(buttonIndex++));
                    } else {
                        clockPanel.add(new JLabel()); 
                    }
                } else if ((i + j) % 2 == 0) {
                    int index = i / 2 * 3 + j / 2;
                    if (index < clockModels.size()) {
                        clockPanel.add(clockModels.get(index));
                    } else {
                        clockPanel.add(new JLabel()); 
                    }
                } else {
                    clockPanel.add(new JLabel());
                }
            }
        }
    
        mainFrame.revalidate();
        mainFrame.repaint();
    }    

    private boolean isGameFinished() {
        return clockModels.stream().allMatch(clock -> clock.getTime() == 12);
    }

    class ButtonListener implements ActionListener {
        private int buttonIndex;

        public ButtonListener(int buttonIndex) {
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

            updateClockPanel();

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
}
