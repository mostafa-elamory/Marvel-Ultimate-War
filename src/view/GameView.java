package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class GameView extends JFrame {
	private JPanel startGame;

//	private JPanel upperWindow;
//	private JPanel lowerWindow;
//	private JPanel upperLeftWindow;
//	private JPanel upperRightWindow;
//	private JPanel upperCenterWindow;
//	private JPanel lowerLeftWindow;
//	private JPanel lowerCenterWindow;
//	private JPanel lowerRightWindow;
	private JPanel panel;
	private JButton startGameButton;
	private JPanel startGameButtonPanel;
	private JPanel paneltext;
	private JLabel firstname;
	private JLabel secondname;
	private JTextField firstt;
	private JTextField secondt;
	private JPanel starttext;
	private JPanel Big;
	private JPanel txt;
	private JPanel pnlChampions ;
	private ArrayList<JButton> butChamp;
	private JTextArea stats;
	private JPanel statsp;
	private JPanel team1;
	private JPanel team2;
	private JPanel teams;
	private ArrayList<JButton> team1b ;
	private ArrayList<JButton> team2b;
	private JButton removeb;
	private JOptionPane popleader;
    private JButton start;
	private JLabel titleName;
	private JButton[][] gridman = new JButton[5][5];
	private JPanel board;
	private JPanel extras;
	private JButton[] extra = new JButton[7];
	private JButton up;
	private JButton left;
	private JButton right;
	private JButton down;
	private JOptionPane exception;
	private JTextArea stats2;
	private JLabel nxt;


	public GameView() {
		
		this.setTitle("Marvel");
		this.setBounds(8, 5, 1500, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.setPreferredSize(new Dimension(1500, 800));

		starttext = new JPanel();
		starttext.setLayout(null);
		starttext.setPreferredSize(new Dimension(1500, 300));

		firstname = new JLabel("Enter the Name of First Player");

		firstname.setBounds(100, 200, 200, 50);
		starttext.add(firstname);
		firstt = new JTextField(100);
		firstt.setMargin(new Insets(0, 0, 0, 0));
		firstt.setBounds(350, 200, 200, 50);
		starttext.add(firstt, BorderLayout.CENTER);

		secondname = new JLabel("Enter the Name of Second Player");
		secondname.setBounds(100, 300, 200, 50);
		starttext.add(secondname);
		secondt = new JTextField(100);
		secondt.setMargin(new Insets(0, 0, 0, 0));
		secondt.setBounds(350, 300, 200, 50);
		starttext.add(secondt);

		startGameButton = new JButton();
		startGameButton.setPreferredSize(new Dimension(380, 104));
		startGameButton.setText("Start Game");

		startGameButton.setMargin(new Insets(0, 0, 0, 0));
		startGameButton.setFocusPainted(false);

		startGameButton.setActionCommand("Start Game");

		startGameButtonPanel = new JPanel();
		startGameButtonPanel.setPreferredSize(new Dimension(400, 200));

		startGameButtonPanel.add(startGameButton);
		starttext.add(firstname);
		starttext.add(firstt);

		panel.add(startGameButtonPanel, BorderLayout.SOUTH);
		panel.add(starttext, BorderLayout.CENTER);

		this.add(panel);
		

		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		GameView tata=new GameView();
//				tata.win("SS");

	}


	public void clearView() {
		this.getContentPane().removeAll();
		this.revalidate();
		this.repaint();
	}

	public void chooseChamp() {
		removeb = new JButton();
		removeb.setBounds(600, 680, 200, 85);
		removeb.setText("Remove");
		removeb.setActionCommand("Remove");
		start = new JButton();
		start.setBounds(600, 600, 200, 85);
		start.setText("Start");
		start.setActionCommand("start");
		

	
		this.setTitle("Marvel-Ultimate War");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500,800);
		this.setBounds(5, 8, 1500, 800);
		 statsp=new JPanel();
		
		 panel=new JPanel();
		panel.setSize(1500, 800);	
		
		panel.setBounds(750, 50, 1500, 800);
		
		panel.setLayout(null);
		 txt= new JPanel();
		 
		 pnlChampions= new JPanel();
	 	txt.setBounds(600, 10, 600,100 );
		pnlChampions.setBounds(400, 100, 900, 490);
		
		
		panel.add(pnlChampions); 
		panel.add(txt);
		statsp.setSize(100, 800);
		stats = new JTextArea();
		stats.setBounds(5, 8, 395,470);
		Font font1 = new Font("SansSerif", Font.BOLD, 15);
		stats.setFont(font1);
		stats.setColumns(statsp.getWidth());
//		stats.setText("lol");
//		statsp.add(stats);
//		stats.setPreferredSize(new Dimension(statsp.getWidth(),statsp.getHeight() ));
		pnlChampions.setLayout(new GridLayout(5,3));
		
		panel.add(stats,BorderLayout.WEST);
	
		this.add(panel,BorderLayout.CENTER);
		
		
		butChamp = new ArrayList();
		titleName =new JLabel();
		titleName.setFont(new Font("SansSerif", Font.BOLD, 30));
		titleName.setText(firstt.getText()+" choose your champions");
		txt.add(titleName);
		for(int i = 0;i<15;i++) {
			JButton butChamp1 = new JButton();
			butChamp1.setVisible(true);
			
			butChamp1.setPreferredSize(new Dimension(400,10));
			
			getPnlChampions().add(butChamp1);
			butChamp.add(butChamp1);
			
			
			}
//		teams = new JPanel();
//		teams.setLayout();
		team1 = new JPanel();
		team1.setLayout(new GridLayout(1,3));
		team1.setBounds(5, 620, 550,170);
		panel.add(team1);
		team2 = new JPanel();
		team2.setBounds(940,620 ,550,170);
		team2.setLayout(new GridLayout(1,3));
//		
		panel.add(team2);
	     team1b = new ArrayList();
		 team2b = new ArrayList();
		
		 panel.add(removeb);
		 panel.add(start);
		 start.setActionCommand("start");
		stats.setEditable(false);

		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	public void chooseleader() {
		popleader.showMessageDialog(null, "please choose the leader", "error", JOptionPane.PLAIN_MESSAGE);
	}
	public void message(String m) {
		exception.showMessageDialog(null, m, "error", JOptionPane.ERROR_MESSAGE);
	}
	public void intiateGame() {
		clearView();
		this.setTitle("Marvel-Ultimate War");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500, 800);
		this.setBounds(5, 8, 1500, 800);
		panel = new JPanel();
		panel.setSize(1500, 800);
		panel.setBounds(750, 50, 1500, 800);
		panel.setLayout(null);
		board = new JPanel();
		board.setLayout(new GridLayout(5,5));
		board.setBounds(20,10 , 1000, 600);
		extras= new JPanel();
		
		extras.setLayout(new GridLayout(1,0));
		extras.setBounds(20, 650, 1000, 100);
		for (int i = 0; i < 7; i++) {
			JButton butChamp1 = new JButton();
			if(i==5) {
				butChamp1.setText("Move");
				butChamp1.setActionCommand("Move");
			}
			if(i==6) {
				butChamp1.setText("End turn");
				butChamp1.setActionCommand("End turn");
			}
			
			if(i==4) {
				butChamp1.setText("Attack");
				butChamp1.setActionCommand("Attack");
			}
			if(i==3) {
				butChamp1.setText("Use leader ability");
				butChamp1.setActionCommand("leader");
			}
			if(i==2||i==1||i==0) {
				butChamp1.setActionCommand("ability");
			}
			
			butChamp1.setVisible(true);
			butChamp1.setPreferredSize(new Dimension(400,60));
			
			extra[i]=butChamp1;
			extras.add(butChamp1);
		}
		
		
		for (int i = 0; i < 5; i++) {
			for (int j =0 ; j<5;j++) {
			JButton butChamp1 = new JButton();
			butChamp1.setText("");
			butChamp1.setVisible(true);
			butChamp1.setPreferredSize(new Dimension(150, 30));
			board.add(butChamp1);
			gridman[i][j]=butChamp1;
			
			}
		}
		up = new JButton();
		up.setBounds(1150, 580, 150, 60);
		up.setText("up");
		left = new JButton();
		left.setBounds(1075, 640, 150, 60);
		left.setText("left");
		right = new JButton();
		right.setBounds(1225, 640, 150, 60);
		right.setText("right");
		down = new JButton();
		down.setBounds(1150, 700, 150, 60);
		down.setText("down");
		up.setEnabled(false);
		down.setEnabled(false);
		left.setEnabled(false);
		right.setEnabled(false);
		panel.add(up);
		panel.add(down);
		panel.add(left);
		panel.add(right);
		up.setActionCommand("Up");
		down.setActionCommand("Down");
		left.setActionCommand("Left");
		right.setActionCommand("Right");
		stats2 = new JTextArea();
        stats2.setBounds(1050, 8, 395,470);
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        stats2.setFont(font1);

        panel.add(stats2);
         nxt = new JLabel();
        nxt.setBounds(1200, 480, 500,100);
        nxt.setBackground(Color.BLACK);
        nxt.setForeground(Color.RED);
        panel.add(nxt);
        
         font1 = new Font("SansSerif", Font.BOLD, 15);
        stats2.setFont(font1);

        panel.add(stats2);
         nxt = new JLabel();
        nxt.setBounds(1030, 480, 500,100);
        nxt.setFont(font1);
        nxt.setBackground(Color.BLACK);
        nxt.setForeground(Color.RED);
        panel.add(nxt);
//		statsp.setSize(100, 800);
//		stats = new JTextArea();
//		stats.setBounds(1000, 8, 395,470);
//		Font font1 = new Font("SansSerif", Font.BOLD, 20);
//		stats.setFont(font1);
//		stats.setColumns(statsp.getWidth());
//		panel.add(stats);
		
		
		panel.add(board);
		panel.add(extras);
		this.add(panel);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	

	}



	public void win(String s) {
        clearView();
        this.setTitle("Marvel-Ultimate War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 800);
        this.setBounds(0, 0, 1500, 800);
        JLabel final1 = new JLabel();
        
        final1.setText(s);
        final1.setFont(new Font("SansSerif", Font.BOLD, 50));

        final1.setPreferredSize(new Dimension(1500,800));
        final1.setHorizontalAlignment(final1.CENTER);
        final1.setVerticalAlignment(final1.CENTER);

        
//        final1.setIcon(test);
        this.add(final1);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

	public JTextField getFirstt() {
		return firstt;
	}

	public JTextField getSecondt() {
		return secondt;
	}

	public JPanel getStartGame() {
		return startGame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JPanel getStartGameButtonPanel() {
		return startGameButtonPanel;
	}

	public JPanel getPaneltext() {
		return paneltext;
	}

	public JLabel getFirstname() {
		return firstname;
	}

	public JLabel getSecondname() {
		return secondname;
	}

	public JPanel getStarttext() {
		return starttext;
	}

	public JPanel getBig() {
		return Big;
	}

	public JPanel getTxt() {
		return txt;
	}

	public JPanel getPnlChampions() {
		return pnlChampions;
	}

	public ArrayList<JButton> getButChamp() {
		return butChamp;
	}

	public JTextArea getStats() {
		return stats;
	}

	public JPanel getStatsp() {
		return statsp;
	}

	public JPanel getTeam1() {
		return team1;
	}

	public JPanel getTeam2() {
		return team2;
	}

	public JPanel getTeams() {
		return teams;
	}

	

	public JLabel getTitleName() {
		return titleName;
	}

	public ArrayList<JButton> getTeam1b() {
		return team1b;
	}

	public ArrayList<JButton> getTeam2b() {
		return team2b;
	}

	public JButton getRemoveb() {
		return removeb;
	}

	public JOptionPane getPopleader() {
		return popleader;
	}

	public JButton getStart() {
		return start;
	}

	public JButton[][] getGridman() {
		return gridman;
	}

	public JPanel getBoard() {
		return board;
	}

	public JPanel getExtras() {
		return extras;
	}

	public JButton[] getExtra() {
		return extra;
	}

	public JButton getUp() {
		return up;
	}

	public JButton getLeft() {
		return left;
	}

	public JButton getRight() {
		return right;
	}

	public JButton getDown() {
		return down;
	}

	public JOptionPane getException() {
		return exception;
	}

	public JTextArea getStats2() {
		return stats2;
	}

	public JLabel getNxt() {
		return nxt;
	}

	}
