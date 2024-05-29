package com.clock.clockexample;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ClockPane extends Pane {
    
    private int clockWidth = 300;
    private int clockHeight = 300;
    
    private int hours, minutes, seconds;
    
    private Timeline timeline;
    
    public ClockPane() {
        setTime();
        setTimeline(1000);
    }

    public ClockPane(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        
        drawClock();
        setTimeline(1000);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        drawClock();
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        drawClock();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        drawClock();
    }

    public int getClockWidth() {
        return clockWidth;
    }

    public void setClockWidth(int clockWidth) {
        this.clockWidth = clockWidth;
        drawClock();
    }

    public int getClockHeight() {
        return clockHeight;
    }

    public void setClockHeight(int clockHeight) {
        this.clockHeight = clockHeight;
        drawClock();
    }

    
    

    private void setTime() {
        Calendar cal = new GregorianCalendar();
        
        this.hours = cal.get(Calendar.HOUR_OF_DAY);
        this.minutes = cal.get(Calendar.MINUTE);
        this.seconds = cal.get(Calendar.SECOND);
        drawClock();
    }

    private void setTimeline(double millis) {
        timeline = new Timeline(new KeyFrame(Duration.millis(millis), e -> clock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void drawClock() {
        
        double radius = Math.min(clockHeight, clockWidth) * 0.4;
        
        double x = clockWidth / 2;
        double y = clockHeight / 2;
        
        Circle circle = new Circle(x ,y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
                
        Group group = new Group();
        
        for(int i = 0; i <= 12; i++) {
            double centerX = x + radius * 0.8 * Math.sin(i * 2 * Math.PI / 12);
            double centerY = y - radius * 0.8 * Math.cos(i * 2 * Math.PI / 12);
            Text text = new Text(centerX - 5, centerY + 5, String.valueOf(i == 0 ? 12 : i));
            
            group.getChildren().add(text);
        }
        
        double sLength = radius * 0.8;
        double secondX = x + sLength *
                Math.sin(seconds * (2 * Math.PI / 60));
        double secondY = y - sLength *
                Math.cos(seconds * (2 * Math.PI / 60));
        Line sLine = new Line(x, y, secondX, secondY);
        sLine.setStroke(Color.RED);
                
        double mLength = radius * 0.65;
        double xMinute = x + mLength *
                Math.sin(minutes * (2 * Math.PI / 60));
        double minuteY = y - mLength *
                Math.cos(minutes * (2 * Math.PI / 60));
        Line mLine = new Line(x, y, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);
        
        double hLength = radius * 0.5;
        double hourX = x + hLength *
                Math.sin((hours % 12 + minutes / 60.0) * (2 * Math.PI / 12));
        double hourY = y - hLength * 
                Math.cos((hours % 12 + minutes / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(x, y, hourX, hourY);
        hLine.setStroke(Color.GREEN);
        
        getChildren().clear();
        getChildren().addAll(circle, group, sLine, mLine, hLine);
    }

    private void clock() {
        if (minutes == 60) {
            
            hours += 1; 
        }	
        if (seconds == 60) {
            minutes += 1;
        }
        seconds = (seconds < 60 ? seconds + 1 : 1);
        drawClock();
    }
    
    
}
