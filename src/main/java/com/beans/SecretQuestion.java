package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secret_question")
public class SecretQuestion {

	@Id
	@Column(name="secret_id")
	private int secret_id;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="account_id")
	private int account_id;
	
	public SecretQuestion(int secret_id, String question, String answer, int account_id) {
		this.secret_id = secret_id;
		this.question = question;
		this.answer = answer;
		this.account_id = account_id;
	}

	public int getSecret_id() {
		return secret_id;
	}

	public void setSecret_id(int secret_id) {
		this.secret_id = secret_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
}
