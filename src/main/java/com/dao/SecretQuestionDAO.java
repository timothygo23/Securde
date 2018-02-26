package com.dao;

import java.util.List;

import com.beans.SecretQuestion;

public interface SecretQuestionDAO {
	public void add(SecretQuestion secretQuestion);
	public void edit(SecretQuestion secretQuestion);
	public void delete(int secret_id);
	public SecretQuestion getCart(int secret_id);
	public List<SecretQuestion> getAllSecretQuestions();	
}
