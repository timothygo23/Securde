package com.dao;

import com.beans.AttemptLogin;

public interface AttemptLoginDAO {
	public void add(AttemptLogin attemptLogin);
	public void edit(AttemptLogin attemptLogin);
	public AttemptLogin get(int account_id);
}
