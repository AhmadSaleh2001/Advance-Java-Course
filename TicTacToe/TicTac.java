package TicTac;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class TicTac extends JFrame implements ActionListener{

	JPanel Center , Right , InRight;
	int Cnt;
	int Player1 , Player2;
	int Arr[][] = new int[3][3];
	TicTac()
	{
		ResetAllGame();
	}
	void InitRightPanel()
	{
		Right = new JPanel(new BorderLayout());
		InRight = new JPanel(new GridLayout(2 , 1));
		InRight.setBorder(BorderFactory.createTitledBorder("Scores"));
		AddLabel(InRight , "Player 1 : 0");
		AddLabel(InRight , "Player 2 : 0");
		
		
		AddBtn(Right , "New Game" , BorderLayout.SOUTH);
		Right.add(InRight , BorderLayout.NORTH);
	}
	void UpdateScores()
	{
		Component Comps[] = InRight.getComponents();
		
		boolean First = false;
		for(Component X : Comps)
		{
			
			if(X instanceof JLabel)
			{
				JLabel L = (JLabel)X;
				
				L.setText("Player " + (First?2:1) + " : " + (First?Player2:Player1));
				First|=true;
			}
		}
	}
	void ResetGame()
	{
		if(Center!=null)this.remove(Center);
		Center = new JPanel(new GridLayout(3 , 3));
		Center.setBorder(BorderFactory.createLoweredBevelBorder());
		this.Cnt = 0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				Arr[i][j] = 0;
				AddBtnWithImage(Center , "blank.gif" , i + "," + j);
			}
		}	
		this.add(Center , BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	void ResetAllGame()
	{
		
		this.Cnt = this.Player1 = this.Player2 = 0;
		this.setTitle("Tic Tac Game");
		this.setSize(500 , 500);
		this.setLayout(new BorderLayout());
		this.setLocation(380 , 100);
		
		Component Arr[] = this.getContentPane().getComponents();
		for(Component X : Arr)
		{
			if(X instanceof JRootPane)continue;
			this.remove(X);
		}
		
		
		
		/* -------- Start Add To Center Panel -------- */
		
		ResetGame();
		
		/* -------- End Add To Center Panel -------- */
		
		
		
		/* -------- Start Add To Right Panel -------- */
		
		InitRightPanel();
		/* -------- End Add To Right Panel -------- */
		
		
		/* -------- Start Add To Main Frame -------- */
		
		
		this.getContentPane().add(Right, BorderLayout.EAST);
		
		/* -------- End Add To Main Frame -------- */
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	void AddBtn(JPanel To , String Txt , String Position)
	{
		JButton B = new JButton(Txt);
		B.addActionListener(this);
		To.add(B , Position);
	}
	void AddLabel(JPanel To , String Txt)
	{
		JLabel L = new JLabel(Txt);
		To.add(L);
	}

	
	void AddBtnWithImage(JPanel To , String Src , String Name)
	{
		JButton B = new JButton(new ImageIcon(Src));
		B.setFocusable(false);
		B.setBorder(BorderFactory.createLineBorder(Color.black));
		B.setSize(50 , 50);
		B.addActionListener(this);
		B.setName(Name);
		To.add(B);
	}
	
	@Override
	public void actionPerformed(ActionEvent E)
	{
		if(E.getSource() instanceof JButton)
		{
			JButton Curr = (JButton)E.getSource();
			if(Curr.getText().equals("New Game"))
			{
				ResetAllGame();
			}
			else
			{
				String Position[] = Curr.getName().split(",");
				int i = Integer.parseInt(Position[0]);
				int j = Integer.parseInt(Position[1]);
				if(Arr[i][j]!=0)
				{
					JOptionPane.showMessageDialog(this, "This Position Is Used :)\nPlease Choice Another One");
					return;
				}
				Arr[i][j] = Cnt%2==0?1:2;
				
				if(Cnt%2 == 0)Curr.setIcon(new ImageIcon("cross.gif"));
				else Curr.setIcon(new ImageIcon("circle.gif"));
//				Curr.setEnabled(false);
				Cnt++;
				
				Check();
			}
			
		}
	}
	void Check()
	{
		
		/* ------------- Validate Rows ------------- */ 
		for(int i=0;i<3;i++)
		{
			int Row = 0;
			for(int j=0;j<3;j++)Row+=Arr[i][j] == 0?100:Arr[i][j];
			if(Row == 3 || Row == 6)
			{
				if(Row == 3)Player1++;
				else ++Player2;
				JOptionPane.showMessageDialog(null , "Player " + (Cnt%2==1?"1":"2") + " Has Won :)");
				UpdateScores();
				ResetGame();
				return;
			}
		}
		
		/* ------------- Validate Rows ------------- */
		
		
		/* ------------- Validate Columns ------------- */ 
		for(int i=0;i<3;i++)
		{
			int Col = 0;
			for(int j=0;j<3;j++)Col+=Arr[j][i] == 0?100:Arr[j][i];
			if(Col == 3 || Col == 6)
			{
				if(Col == 3)Player1++;
				else ++Player2;
				JOptionPane.showMessageDialog(null , "Player " + (Cnt%2==1?"1":"2") + " Has Won :)");
				UpdateScores();
				ResetGame();
				return;
			}
		}
		
		/* ------------- Validate Columns ------------- */
		
		
		/* ------------- Validate Diagonals ------------- */ 
			boolean Left = Arr[0][0] == Arr[1][1] & Arr[1][1] == Arr[2][2] & Arr[0][0]!=0;
			boolean Right = Arr[0][2] == Arr[1][1] & Arr[1][1] == Arr[2][0]  & Arr[0][2]!=0;
			if(Left|Right)
			{
				if(Arr[0][0] == 1)Player1++;
				else Player2++;
				JOptionPane.showMessageDialog(null , "Player " + (Cnt%2==1?"1":"2") + " Has Won :)");
				UpdateScores();
				ResetGame();
				return;
			}
			
		/* ------------- Validate Diagonals ------------- */
			
			
			
			
		/* ------------- Check If Game Will Be Draw ------------- */
			if(Cnt == 9)
			{
				JOptionPane.showMessageDialog(null , "Draw !!!");
				ResetGame();
				return;
			}
		/* ------------- Check If Game Will Be Draw ------------- */
		
		
		
	}
	
}
