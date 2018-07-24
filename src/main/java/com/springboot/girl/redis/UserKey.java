package com.springboot.girl.redis;

public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");

	public  static void main(String[] args) {
		UserKey uk = new UserKey("GUAN");
		System.out.println(uk.getPrefix());
	}
}
