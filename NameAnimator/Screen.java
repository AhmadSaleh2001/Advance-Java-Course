package AhmadSaleh;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Screen extends JFrame implements ActionListener{

	boolean Stop = false;
	@Override
	public void actionPerformed(ActionEvent E)
	{
		String Who = E.getActionCommand();
		if(Who.equals("Ok"))
		{
			Stop = false;
			String Data = Input.getText();
			ForText.setText("");
			Thread T = new Thread("Typing")
			{
				public void run()
				{
					int Idx = 0;
					int L = Data.length();
					int Dir = 1;
					while(true)
					{
						if(Stop)break;
						Idx+=Dir;
						ForText.setText(Data.substring(0 , Idx));
						try
						{
							Thread.sleep(100);
						}
						catch(Exception Ex)
						{
							
						}
						if(Idx == L)
						{
							Color Colors[] = {Color.gray , Color.black};
							int Cnt = 0;
							for(int i=1;i<=6;i++)
							{
								ForPointer.setBackground(Colors[Cnt]);
								Cnt^=1;
								try
								{
									Thread.sleep(100);
								}
								catch(Exception Ex)
								{
									
								}
							}
							Dir = -1;
						}
						else if(Idx == 0)
						{
							try
							{
								Thread.sleep(500);
							}
							catch(Exception Ex)
							{
								
							}
							Dir = 1;
						}
					}
				}
			};
			T.start();
		}
		else 
		{
			Input.setText("");
			ForText.setText("");
			Stop = true;
		}
	}
	Container C;
	JTextField Input;
	JLabel ForText , ForPointer;
	Screen()
	{
		this.setTitle("Main Page");
		this.setBounds(300 , 150 , 500 , 500);
		
		C = this.getContentPane();
		C.setLayout(new BorderLayout());
		
		
		JPanel Top = new JPanel(new FlowLayout());
		AddLbl(Top , "Name");
		Input = AddInput(Top);
		AddBtn(Top , "Ok");
		AddBtn(Top , "Clear");
		
		
		
		JPanel Bottom = new JPanel(new FlowLayout());
		ForText = AddLbl(Bottom , "");
		ForPointer = AddLbl(Bottom , Color.black);
		
		
		
		
		C.add(Top , BorderLayout.NORTH);
		C.add(Bottom , BorderLayout.CENTER);
		
		
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	JLabel AddLbl(JPanel J , String S)
	{
		JLabel L = new JLabel(S);
		L.setFont(new Font(Font.MONOSPACED , Font.BOLD , 15));
		J.add(L);
		return L;
	}
	
	JLabel AddLbl(JPanel J , Color Color)
	{
		JLabel L = new JLabel();
		L.setOpaque(true);
		L.setBackground(Color);
		L.setPreferredSize(new Dimension(2 , 15));
		J.add(L);
		return L;
	}
	JTextField AddInput(JPanel J)
	{
		JTextField Inp = new JTextField(20);
		J.add(Inp);
		return Inp;
	}
	void AddBtn(JPanel J , String Name)
	{
		JButton B = new JButton(Name);
		B.addActionListener(this);
		J.add(B);
	}
}
