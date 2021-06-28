package com.drawer.app;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    
	@Autowired
	Canvas c;
    
    public void start(String[] commands){
        int width = Integer.parseInt(commands[1]);
		int height = Integer.parseInt(commands[2]);
		c.setWidth(width+2);
		c.setHeight(height+2);
		c.initialize();
		c.print();
	}

    public void line(String[] commands){
        int x1 = Integer.parseInt(commands[1]);
        int y1 = Integer.parseInt(commands[2]);
        int x2 = Integer.parseInt(commands[3]);
        int y2 = Integer.parseInt(commands[4]);
        if(x1==x2 || y1==y2){
            c.drawLine(x1, y1, x2, y2);
            c.print();
        } else {
            System.err.println("Only horizontal & vertical lines are supported.");
        }
    }

    public void rectangle(String[] commands){
        int x1 = Integer.parseInt(commands[1]);
        int y1 = Integer.parseInt(commands[2]);
        int x2 = Integer.parseInt(commands[3]);
        int y2 = Integer.parseInt(commands[4]);
        if(x1!=x2 && y1!=y2){
            c.drawRectangle(x1, y1, x2, y2);
            c.print();
        } else {
            System.err.println("Only rectangle coordinates are supported.");
        }
    }

    public void fill(String[] commands){
        int x = Integer.parseInt(commands[1]);
        int y = Integer.parseInt(commands[2]);
        String val = commands[3];
        c.fillCanvas(x, y, val);
		c.print();
    }

	@Bean
	public CommandLineRunner run(){
		Scanner scanner = new Scanner(System.in);
		return (args) -> {
			boolean flag = true;
			while(flag){
				System.out.println("Please enter a command:");
				String input = scanner.nextLine();
				String[] commands = input.split(" ");
				if(commands[0].equals("C")){
					start(commands);
				} else if(commands[0].equals("L")) {
                    line(commands);
				} else if(commands[0].equals("R")) {
                    rectangle(commands);
				} else if(commands[0].equals("F")) {
                    fill(commands);
				} else if(commands[0].equals("Q")) {
					flag = false;
				}
			}
			scanner.close();
		};
	}
}
