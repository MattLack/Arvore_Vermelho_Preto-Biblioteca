package Nodes;

import Models.Book;

public class NodeBook {
	
	private String cor;
	private NodeBook NoEsquerdo;
	private NodeBook NoDireito;
	private NodeBook NoPai;
	private Book livro;
	private String preto = "PRETO";

	public NodeBook(Book book){
		this();
		this.livro = book;
	}

	public NodeBook() {
		this.cor = preto;
		this.setLivro(null);
		this.setNoPai(null);
		this.setNoDireito(null);
		this.setNoEsquerdo(null);
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public NodeBook getNoEsquerdo() {
		return NoEsquerdo;
	}


	public void setNoEsquerdo(NodeBook noEsquerdo) {
		NoEsquerdo = noEsquerdo;
	}


	public NodeBook getNoDireito() {
		return NoDireito;
	}


	public void setNoDireito(NodeBook noDireito) {
		NoDireito = noDireito;
	}


	public NodeBook getNoPai() {
		return NoPai;
	}

	public void setNoPai(NodeBook noPai) {
		NoPai = noPai;
	}

	public Book getLivro() {
		return livro;
	}


	public void setLivro(Book livro) {
		this.livro = livro;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeBook other = (NodeBook) obj;
		if (NoDireito == null) {
			if (other.NoDireito != null)
				return false;
		} else if (!NoDireito.equals(other.NoDireito))
			return false;
		if (NoEsquerdo == null) {
			if (other.NoEsquerdo != null)
				return false;
		} else if (!NoEsquerdo.equals(other.NoEsquerdo))
			return false;
		if (NoPai == null) {
			if (other.NoPai != null)
				return false;
		} else if (!NoPai.equals(other.NoPai))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		return true;
	}

	public String toString() {
		return "\n NodeBook "
				+ "\n cor :" + cor + 
				"\n NoEsquerdo: " + this.NoEsquerdo.getLivro() + 
				"\n NoDireito: " + this.NoDireito.getLivro() + 
				"\n NoPai: " + this.NoPai.getLivro() + 
				"\n livro: " + livro + 
				"\n \n \n";
	}
	
}
