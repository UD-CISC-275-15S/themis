package edu.udel.cisc275_15S.themis;

//	Since there are only two types of Answer's right now, not bothering with creating wrong and right answer classes
//	Instead set a boolean value to true if its a right answer, and to false if its incorrect
//	Every answer has a string and a bool
//		A string containing the answer 
//		^ bool

public class Answer {
	private String answer;
	private boolean bool;
	
	public Answer(String answer,  boolean bool) {
		this.answer = answer;
		this.bool = bool;
	}
	public String toString(){ return answer;}
	public boolean getBool() { return bool;}
}
