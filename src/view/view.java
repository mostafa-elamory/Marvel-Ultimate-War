package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import engine.*;
import java.awt.BorderLayout;
import java.awt.*;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.*;
import model.abilities.*;
import model.effects.*;
import model.world.*;
import view.GameView;
import exceptions.*;

public class view extends JFrame implements GameListener, ActionListener, MouseListener{
	private JPanel panel;
	private JPanel main;
	private Game model;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	int q1;
	int q2;
	int q3;
	int counter =1;
	public view() {
		this.setTitle("Quiz");
		this.setBounds(8, 5, 1500, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			model.loadAbilities("Abilities.csv");
			model.loadChampions("Champions.csv");;
		} catch (IOException e) {
			
		}
		main = new JPanel();
		main.setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		 b1 =new JButton();
		 b2 =new JButton();
		 b3 =new JButton();
		 b4=new JButton();
		b3.addActionListener(this);
		b2.addActionListener(this);
		b1.addActionListener(this);
		
		b1.setActionCommand("b1");
		b2.setActionCommand("b2");
		b3.setActionCommand("b3");
		Random rand = new Random();
		 q1 = rand.nextInt(15);
		 q2 = rand.nextInt(15);
		 q3 = rand.nextInt(15);
		b1.setText(model.getAvailableChampions().get(q1).getName());
//		b2.setText(model.getAvailableChampions().get(q2).getName()+"\n"+"\n"+model.getAvailableChampions().get(q2).getCurrentHP());
//		b3.setText(model.getAvailableChampions().get(q3).getName()+"\n"+"\n"+model.getAvailableChampions().get(q3).getCurrentHP());
		main.add(b1,BorderLayout.NORTH);
		
//		main.add(b3,BorderLayout.CENTER);
//		main.add(b3,BorderLayout.EAST);
		
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		main.add(panel,BorderLayout.CENTER);
		this.add(main);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	public static void main(String[]args) {
		view lol =new view();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action=="b1"&&counter==1) {
			
			b2.setText(model.getAvailableChampions().get(q1).getAbilities().get(0).getName());
			counter++;
			return;
		}
	if(action=="b1"&&counter==2) {
			
			b3.setText(model.getAvailableChampions().get(q1).getAbilities().get(1).getName());
			
			counter++;
			return;
		}
	if(action=="b1"&&counter==3) {
		
		b4.setText(model.getAvailableChampions().get(q1).getAbilities().get(2).getName());
		counter++;
		return;
	}
	if(action=="b1"&&counter==4) {
		Random rand = new Random();
		 q1 = rand.nextInt(15);
		 b1.setText(model.getAvailableChampions().get(q1).getName());
		b2.setText("");
		b3.setText("");
		b4.setText("");
		counter=1;
		return;
	}
		
		
	}

}
