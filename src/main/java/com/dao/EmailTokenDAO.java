package com.dao;

import java.util.List;

import com.beans.EmailToken;

public interface EmailTokenDAO {
	public void add(EmailToken emailToken);
	public void edit(EmailToken emailToken);
	public boolean doesEmailExist(String email);
	public void delete(String email);
	public void deleteExpired();
	public EmailToken get(String token);
}
