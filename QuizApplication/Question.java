package AhmadSaleh;

import java.io.*;

public class Question implements Serializable{

	String Question;
	String Choices[];
	int AnsIdx;
	Question(String Question , String Choices[] , int AnsIdx)
	{
		this.Question = Question;
		this.Choices = Choices;
		this.AnsIdx = AnsIdx;
	}
	Question()
	{
		this.Question = "Unknown";
		this.Choices = null;
		this.AnsIdx = -1;
	}
	
	boolean CheckAnswer(int Idx)
	{
		return Idx == AnsIdx;
	}
	
	void Print()
	{
		System.out.println("Question : " + this.Question);
		System.out.println("AnsIdx : " + this.AnsIdx);
	}
	
}
