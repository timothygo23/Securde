package com.utility;

import java.security.SecureRandom;
import java.util.Random;

public class SaltGenerator {
	private static SaltGenerator instance = new SaltGenerator();
	private Random random;
	
	private SaltGenerator(){
		random = new SecureRandom();
	}
	
	public static SaltGenerator getInstance(){
		return instance;
	}
	
	public byte[] generate(){
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
}
