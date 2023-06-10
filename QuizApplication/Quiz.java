package AhmadSaleh;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Quiz extends JFrame implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent E)
	{
		if(E.getSource() instanceof JRadioButton)
		{
			Next.setEnabled(true);
			Skip.setEnabled(false);
		}
		else
		{
			this.Clicked = true;
			
		}
	}
	
	
	Container C;
	boolean Clicked = false;
	JLabel LblTimer;
	JButton Next , Skip;
	ArrayList<Question>Questions = new ArrayList<Question>();
	ArrayList<JLabel>CntQuestions = new ArrayList<JLabel>();
	JRadioButton Choices[];
	JLabel TitleQuestion;
	JPanel Center;
	ButtonGroup G;
	Quiz() throws Exception
	{
		/* Start Helper Variables */
		
		Component Arr[];
		
		/* End Helper Variables */
		
		this.setTitle("Main Page");
		this.setBounds(300 , 150 , 500 , 500);
		C = this.getContentPane();
		C.setLayout(new BorderLayout());
		
		/* Start Get Data From File */
		
		File F = new File("Questions.txt");
		FileInputStream In = new FileInputStream(F);
		ObjectInputStream In2 = new ObjectInputStream(In);
		Questions =(ArrayList<Question>)In2.readObject();
		In2.close();
		/* End Get Data From File */
		
		
		/* Start Top */
		
		JPanel Top = new JPanel(new BorderLayout());
		Top.setBorder(BorderFactory.createTitledBorder("Progress Bar"));
		Arr = new Component[Questions.size() + 1];		
		for(int i=0;i<Questions.size();i++)
		{
			
			JLabel CurrLbl = AddLbl(Integer.toString(i + 1));
			CurrLbl.setBackground(Color.gray);
			CurrLbl.setForeground(Color.white);
			CurrLbl.setPreferredSize(new Dimension(30,30));
			CntQuestions.add(CurrLbl);
			Arr[i] = CurrLbl;
			if(i == Questions.size() - 1)
			{
				CurrLbl = AddLbl("Final Result !");
				CurrLbl.setBackground(Color.gray);
				CurrLbl.setForeground(Color.white);
				CurrLbl.setPreferredSize(new Dimension(80,30));
				CntQuestions.add(CurrLbl);
				Arr[i + 1] = CurrLbl;
			}
		}
		
		
		JPanel ForQuestions = Panel(null , Arr , "FlowLayout");
		
		this.LblTimer = AddLbl("15");
		this.LblTimer.setBackground(Color.red);
		this.LblTimer.setForeground(Color.white);
		this.LblTimer.setPreferredSize(new Dimension(30,30));
		Arr = new Component[1];
		Arr[0] = this.LblTimer;
		JPanel ForTimer = Panel(null , Arr , "FlowLayout");
		
		Top.add(ForQuestions , BorderLayout.CENTER);
		Top.add(ForTimer , BorderLayout.EAST);
		
		/* End Top */
		
		/* Start Center */
		
		Arr = new Component[5];
		Arr[0] = TitleQuestion = AddLbl("");
		G = new ButtonGroup();
		Choices = new JRadioButton[4];
		for(int i=1;i<5;i++)
		{
			Choices[i - 1] = AddRadioButton("" , G);
			Arr[i] = Choices[i - 1];
			
		}
		Center = Panel("Question" , Arr , "GridLayout");
		/* End Center */
		
		Arr = new Component[2];
		Arr[0] = AddBtn("Skip");
		Arr[1] = AddBtn("Next");
		Skip = (JButton)Arr[0];
		Next = (JButton)Arr[1];
		Arr[1].setEnabled(false);
		JPanel Bottom = Panel("Controls" , Arr , "FlowLayout");
		
		
		
		C.add(Top , BorderLayout.NORTH);
		C.add(Center, BorderLayout.CENTER);
		C.add(Bottom, BorderLayout.SOUTH);
		this.setVisible(true);
		this.Skip.setEnabled(true);
		this.Next.setEnabled(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		RunQuiz();
		
	}
	void RunQuiz()
	{
		int FinalAns = 0;
		int Size = this.Questions.size();
		for(int i=0;i<Size;i++)
		{
			TitleQuestion.setText(Questions.get(i).Question);
			for(int j=0;j<Choices.length;j++)Choices[j].setText(Questions.get(i).Choices[j]);
			if(i > 0)this.CntQuestions.get(i-1).setBackground(Color.gray);
			
			this.CntQuestions.get(i).setBackground(Color.green);
			
			Run();
			
			int Idx = -1;
			for(int j=0;j<Choices.length;j++)
			{
				if(Choices[j].isSelected())
				{
					Idx = j;
					break;
				}
			}
			this.Clicked = false;
			this.LblTimer.setText("15");
			this.Skip.setEnabled(true);
			this.Next.setEnabled(false);
			FinalAns+=Idx==Questions.get(i).AnsIdx?1:0;
			G.clearSelection();
		}
		this.CntQuestions.get(Size - 1).setBackground(Color.gray);
		this.CntQuestions.get(Size).setBackground(Color.green);
		for(Component C : this.Center.getComponents())
		{
			if(!(C instanceof JLabel))Center.remove(C);
		}
		this.revalidate();
		this.repaint();
		this.LblTimer.setText("-");
		this.TitleQuestion.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 18));
		this.TitleQuestion.setText("Your Final Grade " + FinalAns + "/" + this.Questions.size());
		this.Skip.setEnabled(false);
		
	}
	void Run()
	{	
		Thread MyThread = new Thread()
		{
			@Override
			public void run()
			{
				int Cnt = 0;
				while(true)
				{
					
					try
					{
						Thread.sleep(1000);
						if(Clicked == true)return;
					}
					catch(InterruptedException Ex)
					{
						
					}
					int Value = Integer.parseInt(LblTimer.getText());
					
					LblTimer.setText(Integer.toString(--Value));
					Cnt+=Value<=5?1:0;
					if(Cnt > 1)
					{
						if(Cnt%2 == 0)
						{
							LblTimer.setBackground(Color.white);
							LblTimer.setForeground(Color.red);
						}
						else
						{
							LblTimer.setBackground(Color.red);
							LblTimer.setForeground(Color.white);
						}
					}
					if(Value == 0)
					{
						LblTimer.setBackground(Color.red);
						LblTimer.setForeground(Color.white);
						return;
					}
				}
			}
			
		};
		MyThread.run();
	}
	
	JPanel Panel(String Title , Component Arr[] , String Layout)
	{
		JPanel J;
		if(Layout.equalsIgnoreCase("BorderLayout"))J = new JPanel(new BorderLayout());
		else if(Layout.equalsIgnoreCase("GridLayout"))J = new JPanel(new GridLayout(Arr.length , 0));
		else J = new JPanel(new FlowLayout());
		
		if(Title!=null)J.setBorder(BorderFactory.createTitledBorder(Title));
		
		for(Component C : Arr)J.add(C);
		return J;
	}
	JButton AddBtn(String S)
	{
		JButton B = new JButton(S);
		B.addActionListener(this);
		return B;
	}
	JLabel AddLbl(String S)
	{
		JLabel L = new JLabel(S);
		L.setOpaque(true);
		
		L.setHorizontalAlignment(SwingConstants.CENTER);
		return L;
	}
	
	JRadioButton AddRadioButton(String S , ButtonGroup G)
	{
		JRadioButton B = new JRadioButton(S);
		B.addActionListener(this);
		G.add(B);
		
		
		return B;
	}
}
