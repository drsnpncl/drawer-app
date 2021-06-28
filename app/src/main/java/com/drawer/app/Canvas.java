package com.drawer.app;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Canvas {
    int width;
    int height;
    ArrayList<ArrayList<String>> canvas;
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public ArrayList<ArrayList<String>> getCanvas() {
        return canvas;
    }
    public void setCanvas(ArrayList<ArrayList<String>> canvas) {
        this.canvas = canvas;
    }
    public void initialize() {
        this.canvas = new ArrayList<>();
        for(int i=0;i<height;i++){
            ArrayList<String> row = new ArrayList<>();
            for(int j=0;j<width;j++){
                if(i==0 || i==height-1){
                    row.add("-");
                } else {
                    row.add(j==0 || j==width-1 ? "|" : " ");
                }
            }            
            this.canvas.add(row);
        }
    }
    public void print() {
        for(int i=0;i<height;i++){
            ArrayList<String> t = this.canvas.get(i);
            for(int j=0;j<t.size();j++){
                System.out.print(t.get(j));
            }
            System.out.println("");            
        }
    }
    public ArrayList<ArrayList<String>> drawLine(int x1, int y1, int x2, int y2){
        if(x1==x2){
            for(int i=y1;i<=y2;i++){
                ArrayList<String> temp = this.canvas.get(i);
                temp.set(x1, "X");
                canvas.set(i, temp);
            }
        } else {
            ArrayList<String> temp = this.canvas.get(y1);
            for(int i=x1; i<=x2; i++){
                temp.set(i, "X");
            }
            this.canvas.set(y1, temp);
        }
        return this.canvas;
    } 
    public ArrayList<ArrayList<String>> drawRectangle(int x1, int y1, int x2, int y2){
        for(int i=y1;i<=y2;i++){
            ArrayList<String> temp = this.canvas.get(i);
            for(int j=x1;j<=x2;j++){
                if(i==y1 || i==y2 || j==x1 || j==x2) temp.set(j, "X");
                else temp.set(j, " ");
            }
            this.canvas.set(i, temp);
        }
        return this.canvas;
    }
    public ArrayList<ArrayList<String>> fillCanvas(int x, int y, String value){
        for(int i=y-1;i<=y+1;i++){
            if(i>0 && i<this.height-1){
                for(int j=x-1;j<=x+1;j++){
                    if(j>0 && j<this.width-1){
                        if(i==y && j==x){
                        } else {
                            String val = this.canvas.get(i).get(j);
                            if(val == "X" || val == value) continue;
                            else {
                                ArrayList<String> temp = this.canvas.get(i);
                                temp.set(j, value);
                                this.canvas.set(i, temp);
                                this.fillCanvas(j, i, value);
                            }
                        }
                    }
                }
            }
        }
        return this.canvas;
    }
}
