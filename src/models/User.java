package models;

public class User {
	
	private String name;
	private int id;
	private boolean limitedLoan;
	private Book bookLoan;
	
	public User(String name, int id) {
		this.name = name;
		this.id = id;
		this.limitedLoan = false;
		this.bookLoan = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getLimitedLoan() {
		return limitedLoan;
	}

	public void setLimitedLoan(boolean limitedLoan) {
		this.limitedLoan = limitedLoan;
	}

	public Book getBookLoan() {
		return bookLoan;
	}

	public void setBookLoan(Book bookLoan) {
		this.bookLoan = bookLoan;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", limitedLoan=" + limitedLoan + ", bookLoan=" + bookLoan + "]";
	}
	

}
