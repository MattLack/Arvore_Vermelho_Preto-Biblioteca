package models;

public class User {
	
	private String name;
	private String id;
	private boolean limitedLoan;
	
	public User(String name, String id, boolean limitedLoan) {
		this.name = name;
		this.id = id;
		this.limitedLoan = limitedLoan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isLimitedLoan() {
		return limitedLoan;
	}

	public void setLimitedLoan(boolean limitedLoan) {
		this.limitedLoan = limitedLoan;
	}


	public String toString() {
		return "User [name=" + name + ", id=" + id + ", limitedLoan=" + limitedLoan + "]";
	}
	
	
	

}
