package Nodes;

import Models.User;

public class NodeUser {
	
	private String cor;
	private NodeUser NoEsquerdo;
	private NodeUser NoDireito;
	private User usuario;
	
	public NodeUser(String cor, NodeUser noEsquerdo, NodeUser noDireito, User usuario) {
		this.cor = cor;
		NoEsquerdo = noEsquerdo;
		NoDireito = noDireito;
		this.usuario = usuario;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public NodeUser getNoEsquerdo() {
		return NoEsquerdo;
	}

	public void setNoEsquerdo(NodeUser noEsquerdo) {
		NoEsquerdo = noEsquerdo;
	}

	public NodeUser getNoDireito() {
		return NoDireito;
	}

	public void setNoDireito(NodeUser noDireito) {
		NoDireito = noDireito;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		return "NodeUser [cor=" + cor + ", NoEsquerdo=" + NoEsquerdo + ", NoDireito=" + NoDireito + ", usuario="
				+ usuario + "]";
	}
		
	

}
