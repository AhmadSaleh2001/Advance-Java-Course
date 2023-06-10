package AhmadSaleh;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Program extends JFrame implements ActionListener , ListSelectionListener{

	DefaultListModel GetAllItemsFrom()
	{
		DefaultListModel L = new DefaultListModel();
		ListModel GetItems = MainList.getModel();
		for(int i=0;i<GetItems.getSize();i++)
		{
			L.addElement(GetItems.getElementAt(i));
		}
		
		return L;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent E)
	{
		JList L = (JList)E.getSource();
		if(L.isSelectionEmpty())
		{
			Up.setEnabled(false);
			Down.setEnabled(false);
			Remove.setEnabled(false);
		}
		else
		{
			Remove.setEnabled(true);
			Up.setEnabled(true);
			Down.setEnabled(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent E)
	{
		
		
		String Who = E.getActionCommand();
		if(Who.equalsIgnoreCase("Generate") || ((E.getSource() instanceof JTextField) && ((JTextField)E.getSource()).getName().equals("Count")))
		{
			int Value;
			try
			{
				Value = Integer.parseInt(Count.getText());
			}
			catch(Exception Ex)
			{
				JOptionPane.showMessageDialog(this, "This Input Must Be Contain Integer !");
				Count.setText("");
				return;
			}
			
			boolean MustInteger = this.Checks[0].isSelected();
			DefaultListModel L = GetAllItemsFrom();
			
			for(int i=1;i<=Value;i++)
			{
				Random R = new Random();
				if(MustInteger)L.addElement(R.nextInt((int)1e6) + 0.0); // Random Integer From 0 To (Million-1)
				else L.addElement(R.nextDouble());
			}
			
			MainList.setModel(L);
			
		}
		else if(Who.equalsIgnoreCase("Clear"))
		{
			MainList.setModel(new DefaultListModel());
			AddNumber.setText("");
		}
		else if(Who.equals("Sort"))
		{
			DefaultListModel L = GetAllItemsFrom();
			double Arr[] = new double[L.getSize()];
			for(int i=0;i<L.getSize();i++)Arr[i] = Double.parseDouble(String.valueOf(L.getElementAt(i)));
			
			for(JRadioButton R : Radios)
			{
				if(R.isSelected())
				{
					String Txt = R.getText();
					if(Txt.equalsIgnoreCase("BubbleSort"))SortingAlgorithms.BubbleSort(Arr);
					else if(Txt.equalsIgnoreCase("SelectionSort"))SortingAlgorithms.SelectionSort(Arr);
					else if(Txt.equalsIgnoreCase("QuickSort"))SortingAlgorithms.QuickSort(Arr);
					else SortingAlgorithms.HeapSort(Arr);
					break;
				}
			}
			L.clear();
			for(double X : Arr)L.addElement(X);
			MainList.setModel(L);
		}
		else if(Who.equals("Add")  || ((E.getSource() instanceof JTextField) && ((JTextField)E.getSource()).getName().equals("AddNumber")))
		{
			double Value;
			try
			{
				Value = Double.parseDouble(AddNumber.getText());
			}
			catch(Exception Ex)
			{
				JOptionPane.showMessageDialog(this, "This Input Must Be Contain Integer !");
				Count.setText("");
				return;
			}
			DefaultListModel L = GetAllItemsFrom();
			boolean MustInteger = this.Checks[0].isSelected();
			if(MustInteger)Value = (int)Value;
			L.addElement(Value);
			
			MainList.setModel(L);
		}
		else if(Who.equals("Remove"))
		{
			DefaultListModel L = GetAllItemsFrom();
			int Selected = MainList.getSelectedIndex();
			L.remove(Selected);
			MainList.setModel(L);
		}
		else if(Who.equals("DOWN"))
		{
			DefaultListModel L = GetAllItemsFrom();
			int Selected = MainList.getSelectedIndex();
			if(L.getSize()-1!=Selected)
			{
				Double One = (Double)L.getElementAt(Selected);
				Double Two = (Double)L.getElementAt(Selected + 1);
				L.set(Selected, Two);
				L.set(Selected + 1, One);
				Selected++;
			}
			MainList.setModel(L);
			MainList.setSelectedIndex(Selected);
		}
		else
		{
			DefaultListModel L = GetAllItemsFrom();
			int Selected = MainList.getSelectedIndex();
			if(Selected!=0)
			{
				Double One = (Double)L.getElementAt(Selected);
				Double Two = (Double)L.getElementAt(Selected - 1);
				L.set(Selected, Two);
				L.set(Selected - 1, One);
				Selected--;
			}
			MainList.setModel(L);
			MainList.setSelectedIndex(Selected);
		}
	}
		
	
	
	final String AUTHOR_NAME = "Ahmad Saleh";
	
	Container C;
	JTextField Count , AddNumber;
	JList MainList;
	JRadioButton Radios[];
	JCheckBox Checks[];
	JButton Up,Down , Remove;
	Program()
	{
		this.setTitle("Sorting Algorithms - Coded By: " + AUTHOR_NAME);
		this.setBounds(320 , 90 , 600 , 300);
		C = this.getContentPane();
		C.setLayout(new BorderLayout(5 , 5));
		JPanel Left = AddPanelLeft();
		JPanel Right = AddPanelRight();
		
		
		
		C.add(Left , BorderLayout.WEST);
		C.add(Right , BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	JPanel AddPanelLeft()
	{
		JPanel Panel = new JPanel(new BorderLayout(5 , 5));
		
		/* Start Adding Algorithms Panel */
		
		String Radios[] = {"Bubble Sort" , "Selection Sort" , "Quick Sort" , "Heap Sort"};
		this.Radios = new JRadioButton[Radios.length];
		String CheckBoxes[] = {"Restrict To Integers"};
		this.Checks = new JCheckBox[CheckBoxes.length];
		Component Comps[] = new Component[Radios.length + CheckBoxes.length];
		
		ButtonGroup Group = new ButtonGroup();
		
		for(int i=0;i<Radios.length;i++)
		{
			Comps[i] = AddRadioButton(Radios[i] , Group);
			this.Radios[i] = (JRadioButton)Comps[i];
			if(i==0)((JRadioButton)(Comps[i])).setSelected(true);
		}
		
		for(int i=Radios.length;i<Comps.length;i++)
		{
			Comps[i] = AddCheckBox(CheckBoxes[i - Radios.length]);
			this.Checks[i - Radios.length] = (JCheckBox)Comps[i];
		}
		
		JPanel Algorithms = AddPanel("Algorithms" , Comps , "GridLayout");
		
		
		/* End Adding Algorithms Panel */
		
		
		/* Start Adding RandomInput And Operations Panels */
		
		JPanel MainForBottom = AddPanel(null , null , "FlowLayout");
		MainForBottom.setLayout(new BorderLayout());
		
		
		Comps = new Component[2];
		Comps[0] = AddLbl("Count");
		
		Comps[1] = AddInput(7);
		Count = (JTextField)Comps[1];
		Count.setName("Count");
		JPanel MinLeft = AddPanel(null , Comps , "FlowLayout");
		Comps = new Component[2];
		Comps[0] = MinLeft;
		Comps[1] = AddBtn("Generate");
		JPanel RandomInput = AddPanel("Random Input" , Comps , "GridLayout");
		
		
		Comps = new Component[2];
		Comps[0] = AddBtn("Clear");
		Comps[1] = AddBtn("Sort");
		
		JPanel Operations = AddPanel("Operations" , Comps , "FlowLayout");
		
		Operations.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		
		
		MainForBottom.add(RandomInput , BorderLayout.CENTER);
		MainForBottom.add(Operations , BorderLayout.EAST);
		
		/* End Adding RandomInput And Operations Panels */
		
		Panel.add(Algorithms , BorderLayout.CENTER);
		Panel.add(MainForBottom , BorderLayout.SOUTH);
		return Panel;
	}
	JPanel AddPanel(String Title , Component Arr[] , String TypeLayout)
	{
		JPanel J;
		if(TypeLayout.equalsIgnoreCase("GridLayout"))J = new JPanel(new GridLayout(Arr.length,0));
		else if(TypeLayout.equals("FlowLayout"))J = new JPanel(new FlowLayout(20));
		else J = new JPanel(new BorderLayout());
		
		if(Title!=null)J.setBorder(BorderFactory.createTitledBorder(Title));
		if(Arr!=null)for(Component C : Arr)J.add(C);
		return J;
	}
	
	JPanel AddPanelRight()
	{
		JPanel J = new JPanel(new BorderLayout(5,5));
		J.setBorder(BorderFactory.createTitledBorder("List Panel"));
		
		Component Comps[] = new Component[2];
		Comps[0] = AddInput(10);
		AddNumber = (JTextField)Comps[0];
		AddNumber.setName("AddNumber");
		Comps[1] = AddBtn("Add");
		JPanel Top = new JPanel(new BorderLayout(5,5));
		Top.add(Comps[0], BorderLayout.CENTER);
		Top.add(Comps[1], BorderLayout.EAST);
		
		
		Comps = new Component[1];
		Comps[0] = AddList();
		MainList = (JList)Comps[0];
		Comps[0] = new JScrollPane(Comps[0]);
		JPanel Center = AddPanel(null , Comps , "GridLayout");
		
		
		JPanel Bottom = AddPanel(null , null , "BorderLayout");
		
		Comps = new Component[2];
		Comps[0] = AddBtn("UP");
		Comps[1] = AddBtn("DOWN");
		
		
		
		((JButton)Comps[0]).setEnabled(false);
		((JButton)Comps[1]).setEnabled(false);
		
		Up = (JButton)Comps[0];
		Down = (JButton)Comps[1];
		
		JPanel LeftBottom = AddPanel(null , Comps , "FlowLayout");
		
		Comps = new Component[1];
		Comps[0] = AddBtn("Remove");
		Remove = (JButton)Comps[0];
		Remove.setEnabled(false);
		JPanel RightBottom = AddPanel(null , Comps , "FlowLayout");
		
		Bottom.add(LeftBottom , BorderLayout.WEST);
		Bottom.add(RightBottom , BorderLayout.EAST);
		
		
		J.add(Top , BorderLayout.NORTH);
		J.add(Center , BorderLayout.CENTER);
		J.add(Bottom , BorderLayout.SOUTH);
		
		return J;
	}
	
	
	
	JLabel AddLbl(String S)
	{
		return new JLabel(S);
	}
	JButton AddBtn(String S)
	{
		JButton B = new JButton(S);
		B.addActionListener(this);
		return B;
	}
	JTextField AddInput(int Cols)
	{
		JTextField Input = new JTextField(Cols);
		Input.addActionListener(this);
		return Input;
	}
	JRadioButton AddRadioButton(String S , ButtonGroup G)
	{
		JRadioButton B = new JRadioButton(S);
		G.add(B);
		return B;
	}
	JCheckBox AddCheckBox(String S)
	{
		JCheckBox B = new JCheckBox(S);		
		return B;
	}
	JList AddList()
	{
		JList L = new JList();
		L.setBorder(BorderFactory.createLineBorder(Color.black));
		L.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		L.addListSelectionListener(this);
		return L;
	}



	
}
