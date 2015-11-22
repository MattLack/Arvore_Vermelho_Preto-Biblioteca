package models;


public class Book {
    
    private String title;
    private String author;
    private boolean loan;
    private double preco;
    private User userLoan;
    
    public Book(String title, String author, double preco) {
        this.title = title;
        this.author = author;
        this.preco = preco;
    }


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
	public boolean getLoan() {
		return loan;
	}


	public void setLoan(boolean loan) {
		this.loan = loan;
	}

	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}
		

	public User getUserLoan() {
		return userLoan;
	}


	public void setUserLoan(User userLoan) {
		this.userLoan = userLoan;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (loan != other.loan)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", loan=" + loan + ", preco=" + preco + ", userLoan="
				+ userLoan.getName() + "]";
	}

}
