package controller;

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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.*;
import model.abilities.*;
import model.effects.*;
import model.world.*;
import view.GameView;
import exceptions.*;

public class Controller implements GameListener, ActionListener, MouseListener {
	private Game model;
	private GameView view;
	private boolean GameEnded;
	private boolean removePressed = false;
	private boolean move = false;
	private boolean attack = false;
	private boolean ability = false;
	private boolean singletarget=false;
	private ArrayList<JButton> grid=new ArrayList();
	private ArrayList<JButton> extra=new ArrayList();
	private Ability a;

	private Player p1;
	private Player p2;

	public Controller() throws CloneNotSupportedException, IOException {

		view = new GameView();
		view.getStartGameButton().addActionListener(this);

	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		new Controller();

	}

	public void updateBoard() {
		if(model.checkGameOver()!=null) {
			view.win("the game ended, "+model.checkGameOver().getName()+" won!!");
		}
		for (int i = 0; i < 5; i++) {
			int bj = 0;
			for (int j = 4; j >= 0; j--) {
				view.getGridman()[j][i].addActionListener(this);
				if (model.getBoard()[bj][i] != null) {
					if (model.getBoard()[bj][i] instanceof Champion) {
						view.getGridman()[j][i].setText(((Champion) model.getBoard()[bj][i]).getName());

					} else {
						view.getGridman()[j][i].setText("Cover" + ((Cover) model.getBoard()[bj][i]).getCurrentHP());
					}
				}
				else {
					view.getGridman()[j][i].setText("");
					view.getGridman()[j][i].setBorder(null);
				}
				bj++;
			}
		}

		view.revalidate();
		view.repaint();
		}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("Start Game")) {
			if((view.getFirstt().getText().isBlank()||view.getSecondt().getText().isBlank())) {
				view.message("please enter a name");
			}
			else {
				if((view.getFirstt().getText().equals(view.getSecondt().getText()))) {
					view.message("this name is already taken, please enter a differect name");
				}
					else {
						String firstname = view.getFirstt().getText();
						String secondname = view.getFirstt().getText();
						p1 = new Player(firstname);
						p2 = new Player(secondname);
						

						try {
							model.loadAbilities("Abilities.csv");
							model.loadChampions("Champions.csv");

						} catch (IOException e1) {

						}

						view.clearView();
						view.chooseChamp();
						view.getRemoveb().addActionListener(this);
						for (int i = 0; i < 15; i++) {

							((JButton) view.getPnlChampions().getComponent(i))
									.setText(model.getAvailableChampions().get(i).getName());
							((JButton) view.getPnlChampions().getComponent(i)).addMouseListener(this);
							((JButton) view.getPnlChampions().getComponent(i)).addActionListener(this);
							((JButton) view.getPnlChampions().getComponent(i)).setActionCommand("choose");

						}
						view.getStart().addActionListener(this);
					}
				}
			}
			
		if(action.equals("choose")) {
			JButton b = (JButton) e.getSource();
			
				int n = view.getButChamp().indexOf(b);
				for (int i = 0; i < model.getAvailableChampions().size(); i++) {
					if (n == i) {
						if (p1.getTeam().size() != 3) {
							if (p1.getTeam().size() == 2) {

								view.chooseleader();
							}
							JButton unit1 = new JButton();
							unit1.setText(model.getAvailableChampions().get(i).getName());
							unit1.setBorder(BorderFactory.createLineBorder(Color.red,3));
							p1.getTeam().add(model.getAvailableChampions().get(i));
							view.getTeam1().add(unit1);
							unit1.addMouseListener(this);
							view.getTeam1b().add(unit1);
							b.setEnabled(false);

						}

						else {

							if (p2.getTeam().size() != 3&&p1.getLeader()!=null) {
								if (p2.getTeam().size() == 2) {

									view.chooseleader();
								}
								JButton unit2 = new JButton();
								unit2.setText(model.getAvailableChampions().get(i).getName());
								unit2.setBorder(BorderFactory.createLineBorder(Color.blue,3));
								p2.getTeam().add(model.getAvailableChampions().get(i));
								view.getTeam2().add(unit2);
								view.getTeam2b().add(unit2);
								unit2.addMouseListener(this);
								b.setEnabled(false);
							}

						}

					}
				}
			
		}
		
		
	
		if (action.equals("Remove")) {
			removePressed = true;

		}
		if (action.equals("start") && p1.getLeader() != null && p2.getLeader() != null) {
			model = new Game(p1, p2);
			view.clearView();
			view.intiateGame();
			Comparable o  =model.getTurnOrder().remove();
			String s = ((Champion)model.getTurnOrder().peekMin()).getName();
			model.getTurnOrder().insert(o);
			view.getNxt().setText("Current Champion: "+((Champion)model.getTurnOrder().peekMin()).getName()+" "+"Next:"+s);
			for(int i=0;i<3;i++) {
				extra.add(view.getExtra()[i]);
				view.getExtra()[i].addMouseListener(this);
			}
			for (int i = 0; i < 5; i++) {
				int bj = 0;
				for (int j = 4; j >= 0; j--) {
					view.getGridman()[j][i].addActionListener(this);
					grid.add(view.getGridman()[j][i]);
					view.getGridman()[j][i].addMouseListener(this);
					
					if (model.getBoard()[bj][i] != null) {
						if (model.getBoard()[bj][i] instanceof Champion) {
							view.getGridman()[j][i].setText(((Champion) model.getBoard()[bj][i]).getName());
							view.getGridman()[j][i].setActionCommand(((Champion) model.getBoard()[bj][i]).getName());
							if(j==4&&i<4&&i>0) {
								view.getGridman()[j][i].setBorder(BorderFactory.createLineBorder(Color.red,3));
							}
							if(j==0&&i<4&&i>0) {
								view.getGridman()[j][i].setBorder(BorderFactory.createLineBorder(Color.blue,3));
								
							}

						} else {
							view.getGridman()[j][i].setText("Cover" + ((Cover) model.getBoard()[bj][i]).getCurrentHP());
						}
					}
					bj++;
					
					
				}

			}
			for (int i = 0; i < 7; i++) {
				view.getExtra()[i].addActionListener(this);
			}
			view.getUp().addActionListener(this);
			view.getDown().addActionListener(this);
			view.getLeft().addActionListener(this);
			view.getRight().addActionListener(this);
			Champion c = model.getCurrentChampion();
			view.getExtra()[0].setText(c.getAbilities().get(0).getName());
			view.getExtra()[1].setText(c.getAbilities().get(1).getName());
			view.getExtra()[2].setText(c.getAbilities().get(2).getName());
		}
		if (action.equals("Move")) {
			view.getUp().setEnabled(true);
			view.getDown().setEnabled(true);
			view.getLeft().setEnabled(true);
			view.getRight().setEnabled(true);
			move = true;
			attack = false;
			ability = false;

		}
		if (action == "Attack") {
			move = false;
			ability = false;
			attack = true;
			view.getUp().setEnabled(true);
			view.getDown().setEnabled(true);
			view.getLeft().setEnabled(true);
			view.getRight().setEnabled(true);

		}
		if (action == "Up") {
			if (move == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				Champion c = model.getCurrentChampion();
				int x = c.getLocation().x;
				int y = c.getLocation().y;

				try {

					model.move(Direction.UP);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					for (int i = 0; i < 5; i++) {
						
						for (int j = 4; j >= 0; j--) {

							if (view.getGridman()[j][i].getText() == c.getName()) {
								view.getGridman()[j - 1][i].setText(view.getGridman()[j][i].getText());
								view.getGridman()[j - 1][i].setActionCommand(view.getGridman()[j][i].getText());
								view.getGridman()[j - 1][i].setBorder(view.getGridman()[j][i].getBorder());
								view.getGridman()[j][i].setBorder(null);
								view.getGridman()[j][i].setText("");
								
								break;

							}

						}
					}
					move = false;
					

				} catch (UnallowedMovementException | NotEnoughResourcesException e2) {
					String s = "";
					if (e2 instanceof NotEnoughResourcesException) {
						s="No Action Points";
					}
					if (e2 instanceof UnallowedMovementException) {
						s="Cant move";
					}
					
					view.message(s);

				}

	
			}
			if (attack == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					model.attack(Direction.UP);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					attack = false;
					
					updateBoard();
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e2) {
					String s = "";
					if(e2 instanceof NotEnoughResourcesException) {
				
						s=e2.getMessage();
					}
					if(e2 instanceof ChampionDisarmedException) {
						s="Champion is disarmed";
					}
					if(e2 instanceof InvalidTargetException) {
						s="invalid target";
					}
					view.message(s);
				}
				
			}
			if(ability==true) {
				attack = false;
				move = false;
				ability = false;
				
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
				model.castAbility(a,Direction.UP);
				view.getStats2().setText(model.getCurrentChampion().toString2());
				updateBoard();
				}catch(NotEnoughResourcesException | AbilityUseException |CloneNotSupportedException e2) {
					
					String s = e2.getMessage();
				
					view.message(s);
				}
			}

		}
		if (action == "Down") {
			if (move == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					model.move(Direction.DOWN);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					Champion c = model.getCurrentChampion();
					for (int i = 0; i < 5; i++) {

						for (int j = 4; j >= 0; j--) {

							if (view.getGridman()[j][i].getText() == c.getName()) {
								view.getGridman()[j + 1][i].setText(view.getGridman()[j][i].getText());
								view.getGridman()[j + 1][i].setActionCommand(view.getGridman()[j][i].getText());
								view.getGridman()[j + 1][i].setBorder(view.getGridman()[j][i].getBorder());
								view.getGridman()[j][i].setBorder(null);
								view.getGridman()[j][i].setText("");

								break;

							}

						}
					}

					
					
				

				} catch (UnallowedMovementException | NotEnoughResourcesException e2) {
					String s = e2.getMessage();
				
					
					view.message(s);
					
				}
			}
			if (attack == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					model.attack(Direction.DOWN);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					
					updateBoard();
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e2) {
					String s = e2.getMessage();
					
					view.message(s);
				}
			}
			if(ability==true) {
				
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
				model.castAbility(a,Direction.DOWN);
				view.getStats2().setText(model.getCurrentChampion().toString2());
				
				updateBoard();
				}catch(NotEnoughResourcesException | AbilityUseException |CloneNotSupportedException e2) {
					
					String s = e2.getMessage();
		
					view.message(s);
				}
			}

		}
		if (action == "Left") {
			if (move == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					System.out.println("here");
					model.move(Direction.LEFT);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					Champion c = model.getCurrentChampion();
					System.out.println(c.getLocation().x+" "+c.getLocation().y);
					boolean flag=false;
					for (int i = 0; i < 5; i++) {

						for (int j = 4; j >= 0; j--) {

							if (view.getGridman()[j][i].getText() == c.getName()) {
								System.out.println(i+" "+j);
								view.getGridman()[j][i - 1].setText(view.getGridman()[j][i].getText());
								view.getGridman()[j ][i-1].setActionCommand(view.getGridman()[j][i].getText());
								view.getGridman()[j ][i-1].setBorder(view.getGridman()[j][i].getBorder());
								view.getGridman()[j][i].setBorder(null);
								view.getGridman()[j][i].setText("");
								flag=true;
								break;
							}
							if(flag) {
								break;
							}

						}
					}

				
					move = false;
					
					updateBoard();
				} catch (UnallowedMovementException | NotEnoughResourcesException e2) {
					String s = e2.getMessage();
					
					
					view.message(s);

				}

			}
			if (attack == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					model.attack(Direction.LEFT);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					attack = false;
					
					updateBoard();
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e2) {
					String s = e2.getMessage();
					
					view.message(s);
				}
			}
			if(ability==true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
				model.castAbility(a,Direction.LEFT);
				view.getStats2().setText(model.getCurrentChampion().toString2());
				
				updateBoard();
				}catch(NotEnoughResourcesException | AbilityUseException |CloneNotSupportedException e2) {
					
					String s = e2.getMessage();
			
					view.message(s);
				}
			}
		}
		if (action == "Right") {
			if (move == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					System.out.println("here");
					model.move(Direction.RIGHT);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					Champion c = model.getCurrentChampion();
					System.out.println(c.getLocation().x+" "+c.getLocation().y);
					boolean flag=false;
					for (int i = 0; i < 5; i++) {

						for (int j = 4; j >= 0; j--) {

							if (view.getGridman()[j][i].getText() == c.getName()) {
								
								view.getGridman()[j][i + 1].setText(view.getGridman()[j][i].getText());
								view.getGridman()[j ][i+1].setActionCommand(view.getGridman()[j][i].getText());
								view.getGridman()[j ][i+1].setBorder(view.getGridman()[j][i].getBorder());
								view.getGridman()[j][i].setBorder(null);
								view.getGridman()[j][i].setText("");
								flag=true;
								break;
							}
							if(flag) {
								break;
							}

						}
					}

					
				
					
					updateBoard();
				} catch (UnallowedMovementException | NotEnoughResourcesException e2) {
					String s = e2.getMessage();
				
					
					view.message(s);
					
				}

			}
			if (attack == true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
					model.attack(Direction.RIGHT);
					view.getStats2().setText(model.getCurrentChampion().toString2());
					
					
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e2) {
				String s = e2.getMessage();
			
				view.message(s);
				}
			}
			if(ability==true) {
				attack = false;
				move = false;
				ability = false;
				view.getUp().setEnabled(false);
				view.getDown().setEnabled(false);
				view.getLeft().setEnabled(false);
				view.getRight().setEnabled(false);
				try {
				model.castAbility(a,Direction.RIGHT);
				view.getStats2().setText(model.getCurrentChampion().toString2());
			
				updateBoard();
				}catch(NotEnoughResourcesException | AbilityUseException |CloneNotSupportedException e2) {
					
					String s = e2.getMessage();
				
					view.message(s);
				}
			}
		}
		if (action == "leader") {
			Champion c = model.getCurrentChampion();
			try {
				model.useLeaderAbility();
				view.getStats2().setText(model.getCurrentChampion().toString2());
				view.getExtra()[3].setEnabled(false);
				view.getExtra()[3].setText("Use leader ability (already used)");
				updateBoard();

			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e2) {
				if(e2 instanceof LeaderNotCurrentException) {
					if(e2 instanceof LeaderNotCurrentException) {
						view.message("current champ isnt the leader");
					}
					
				}

			}
		}
		if (action == "End turn") {
			model.endTurn();
			Champion c = model.getCurrentChampion();
			
			view.getStats2().setText(model.getCurrentChampion().toString2());
			boolean disarm=false;
			for(int i =0;i<c.getAppliedEffects().size();i++) {
				if(c.getAppliedEffects().get(i) instanceof Disarm) {
					disarm = true;
				}
			}
			if(disarm ==true) {
				view.getExtra()[4].setActionCommand("punch");
				view.getExtra()[4].setText("punch");
			}
			else {
				view.getExtra()[4].setActionCommand("Attack");
				view.getExtra()[4].setText("Attack");
			}
			if (model.getFirstPlayer().getTeam().contains(c)) {
				if (model.isFirstLeaderAbilityUsed() != true) {
					view.getExtra()[3].setEnabled(true);
					view.getExtra()[3].setText("Use leader ability ");
				}
				else {
					view.getExtra()[3].setEnabled(false);
					view.getExtra()[3].setText("Use leader ability (already used)");
				}

			}
			if (model.getSecondPlayer().getTeam().contains(c)) {
				if (model.isSecondLeaderAbilityUsed() != true) {
					view.getExtra()[3].setEnabled(true);
					view.getExtra()[3].setText("Use leader ability ");
				}
				else {
					view.getExtra()[3].setEnabled(false);
					view.getExtra()[3].setText("Use leader ability (already used)");
				}

			}
		
			c = model.getCurrentChampion();
			view.getExtra()[0].setText(c.getAbilities().get(0).getName());
			view.getExtra()[1].setText(c.getAbilities().get(1).getName());
			view.getExtra()[2].setText(c.getAbilities().get(2).getName());
			if(model.getTurnOrder().size()>1) {
				Comparable o  =model.getTurnOrder().remove();
				String s = ((Champion)model.getTurnOrder().peekMin()).getName();
				model.getTurnOrder().insert(o);
				view.getNxt().setText("Current Champion: "+((Champion)model.getTurnOrder().peekMin()).getName()+" "+"Next:"+s);
				
			}
			else {
				if(model.getTurnOrder().size()==1) {
					view.getNxt().setText("Current Champion: "+((Champion)model.getTurnOrder().peekMin()).getName());
				}
			}
		}
		if(action=="punch") {
			Champion c = model.getCurrentChampion();
			singletarget=true;
			a=c.getAbilities().get(3);
			
		}
		if(action=="ability") {
			Champion c=model.getCurrentChampion();
			for(int i=0;i<3;i++) {
				  Ability atemp = c.getAbilities().get(i);
				
					if(atemp.getName()==((JButton)e.getSource()).getText()){
						if(atemp.getCastArea().equals(AreaOfEffect.DIRECTIONAL)){
							a=atemp;
							ability=true;
							singletarget=false;
							view.getUp().setEnabled(true);
							view.getDown().setEnabled(true);
							view.getLeft().setEnabled(true);
							view.getRight().setEnabled(true);
							
						}
						if(atemp.getCastArea().equals(AreaOfEffect.TEAMTARGET)||atemp.getCastArea().equals(AreaOfEffect.SURROUND)||
								atemp.getCastArea().equals(AreaOfEffect.SELFTARGET)) {
							a=atemp;
							try {
								
								model.castAbility(a);
								ability=false;
								singletarget=false;
								updateBoard();
								view.getStats2().setText(model.getCurrentChampion().toString2());
							}catch( NotEnoughResourcesException| AbilityUseException| CloneNotSupportedException e2) {
								String s=e2.getMessage();
						
								view.message(s);
							}
							
							
						}
						if(atemp.getCastArea().equals(AreaOfEffect.SINGLETARGET)){
							a=atemp;
							singletarget=true;
							ability=false;
						}
						
					}
				
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JButton b = (JButton) e.getSource();
//		if (view.getButChamp().contains(b)) {
//			int n = view.getButChamp().indexOf(b);
//			for (int i = 0; i < model.getAvailableChampions().size(); i++) {
//				if (n == i) {
//					if (p1.getTeam().size() != 3) {
//						if (p1.getTeam().size() == 2) {
//
//							view.chooseleader();
//						}
//						JButton unit1 = new JButton();
//						unit1.setText(model.getAvailableChampions().get(i).getName());
//						unit1.setBorder(BorderFactory.createLineBorder(Color.red,3));
//						p1.getTeam().add(model.getAvailableChampions().get(i));
//						view.getTeam1().add(unit1);
//						unit1.addMouseListener(this);
//						view.getTeam1b().add(unit1);
//						b.setEnabled(false);
//
//					}
//
//					else {
//
//						if (p2.getTeam().size() != 3&&p1.getLeader()!=null) {
//							if (p2.getTeam().size() == 2) {
//
//								view.chooseleader();
//							}
//							JButton unit2 = new JButton();
//							unit2.setText(model.getAvailableChampions().get(i).getName());
//							unit2.setBorder(BorderFactory.createLineBorder(Color.blue,3));
//							p2.getTeam().add(model.getAvailableChampions().get(i));
//							view.getTeam2().add(unit2);
//							view.getTeam2b().add(unit2);
//							unit2.addMouseListener(this);
//							b.setEnabled(false);
//						}
//
//					}
//
//				}
//			}
//		}
		if (view.getTeam1b().contains(b) && p1.getTeam().size() == 3 && removePressed != true) {
			view.getTitleName().setText(view.getSecondt().getText() + " Choose you champions");
			for (int i = 0; i < p1.getTeam().size(); i++) {
				if (p1.getTeam().get(i).getName() == b.getText()) {
					p1.setLeader(p1.getTeam().get(i));
					for (int j = 0; j < view.getTeam1b().size(); j++) {
						view.getTeam1b().get(j).setEnabled(false);
					}
				}
			}
		}
		if (view.getTeam2b().contains(b) && p2.getTeam().size() == 3 && removePressed != true) {
			for (int i = 0; i < p2.getTeam().size(); i++) {
				if (p2.getTeam().get(i).getName() == b.getText()) {
					p2.setLeader(p2.getTeam().get(i));
					for (int j = 0; j < view.getTeam2b().size(); j++) {
						view.getTeam2b().get(j).setEnabled(false);
					}
				}
			}
		}

		if (view.getTeam1b().contains(b) && p1.getLeader() == null && removePressed == true) {
			for (int i = 0; i < p1.getTeam().size(); i++) {
				if (p1.getTeam().get(i).getName() == b.getText()) {
					p1.getTeam().remove(p1.getTeam().get(i));
					view.getTeam1().remove(b);
					view.revalidate();
					view.repaint();
					removePressed = false;
					for (int j = 0; j < 15; j++) {
						if (b.getText() == view.getButChamp().get(j).getText()) {
							view.getButChamp().get(j).setEnabled(true);
							break;
						}
					}
					return;
				}
			}
		}
		if (view.getTeam2b().contains(b) && p2.getLeader() == null && removePressed == true) {
			for (int i = 0; i < p2.getTeam().size(); i++) {
				if (p2.getTeam().get(i).getName() == b.getText()) {
					p2.getTeam().remove(p2.getTeam().get(i));
					view.getTeam2().remove(b);
					view.revalidate();
					view.repaint();
					removePressed = false;
					for (int j = 0; j < 15; j++) {
						if (b.getText() == view.getButChamp().get(j).getText()) {
							view.getButChamp().get(j).setEnabled(true);
							break;
						}
					}
					return;
				}
			}
		}
		if(grid.contains(b)&&singletarget==true) {
			Champion c = model.getCurrentChampion();
			singletarget=false;
			int x = 0;
			int y = 0;
			for (int i = 0; i < 5; i++) {
				int bj = 0;
				
				for (int j = 4; j >= 0; j--) {
					
					if (model.getBoard()[bj][i] != null) {
						if (view.getGridman()[j][i].getText()==b.getText()) {
							
								x=((Damageable)model.getBoard()[bj][i]).getLocation().x;
								y=((Damageable)model.getBoard()[bj][i]).getLocation().y;
							
							

						} 
					}

					bj++;
				}
			}
			try {
				model.castAbility(a, x, y);
				ability=false;
				singletarget=false;
				view.getStats2().setText(model.getCurrentChampion().toString2());
				updateBoard();
			}catch( NotEnoughResourcesException| AbilityUseException| CloneNotSupportedException | InvalidTargetException e2) {
				String s=e2.getMessage();
	
				view.message(s);
			}
		
			
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		
		if (view.getButChamp().contains(b)) {
			int n = view.getButChamp().indexOf(b);
			for (int i = 0; i < model.getAvailableChampions().size(); i++) {
				if (n == i) {

					view.getStats().setText(model.getAvailableChampions().get(i).toString());

				}
			}
		}
		if(grid.contains(b)) {
			for(int i=0;i<model.getFirstPlayer().getTeam().size();i++) {
				
				view.getStats2().setText(model.getCurrentChampion().toString2());
				if(b.getText()==model.getFirstPlayer().getTeam().get(i).getName()) {
					
						view.getStats2().setText(model.getFirstPlayer().getTeam().get(i).toString2()
								+"\n"+"\n"+"leader: "+model.getFirstPlayer().getTeam().get(i).equals(model.getFirstPlayer().getLeader()));
					
					
					break;
				}}
//					else {
			for(int i=0;i<model.getSecondPlayer().getTeam().size();i++) {
					if(b.getText()==model.getSecondPlayer().getTeam().get(i).getName()) {
						view.getStats2().setText(model.getSecondPlayer().getTeam().get(i).toString2()
								+"\n"+"\n"+"leader: "+model.getSecondPlayer().getTeam().get(i).equals(model.getSecondPlayer().getLeader()));
						break;
						
					}
					
				
//					else {
//						if(view.getStats2()!=null)
//						
//					}
					
//				}
				
			}
			
		}
	
		
		if(extra.contains(b)) {
			for(int i=0;i<model.getCurrentChampion().getAbilities().size();i++) {
				if(b.getText()==model.getCurrentChampion().getAbilities().get(i).getName()) {
					view.getStats2().setText(model.getCurrentChampion().getAbilities().get(i).toString());
				}
			
			}
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
