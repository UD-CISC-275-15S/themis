package edu.udel.cisc275_15S.themis;


import com.badlogic.gdx.utils.Array;

//	This class is used for creating Questions
// 	Each Question has four possible Answers: 3 Wrong Answers and 1 Correct Answer 
//	A Question is a string that ends with a "?" mark
public class Question {
	private Array<Answer> answers;
	private String question;
	
	public Question(Array<Answer> answers, String questions) {
		this.answers = answers;
	}
	public String toString() {
		return question;
	}
	public String getQ(){ return question;}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Array<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Array<Answer> answers) {
		this.answers = answers;
	}
	public void addAnswer(Answer ans) { answers.add(ans); }
	public void createQA() {
		
	}
}
