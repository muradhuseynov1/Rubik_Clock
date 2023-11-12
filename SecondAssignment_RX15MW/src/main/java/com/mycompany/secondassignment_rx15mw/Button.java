/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.secondassignment_rx15mw;

/**
 *
 * @author huseynov
 */
public class Button {
    ClockModel one;
    ClockModel two;
    ClockModel three;
    ClockModel four;

    public Button(ClockModel a, ClockModel b, ClockModel c, ClockModel d){
        this.one = a;
        this.two = b;
        this.three = c;
        this.four = d;
    }
    
    public void click(){
        one.incrementHour();
        two.incrementHour();
        three.incrementHour();
        four.incrementHour();        
    }
    
}