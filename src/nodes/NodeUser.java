
/**
 * 
 * UNIVERSIDADE FEDERAL RURAL DE PERNAMBUCO
 * DISCIPLINA: ALGORITMOS E ESTRUTURA DE DADOS
 * CURSO DO DISCENTE: BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO
 * CURSO DA DISCIPLINA OFERTADA: BACHARELADO EM SISTEMA DE INFORMAÇÃO
 * EMAIL DO DISCENTE: A.MATEUSLOL@GMAIL.COM
 * DISCENTE: ANDERSON MATEUS DA SILVA
 * @author Matt_lackome
 *
 */

package nodes;

import models.User;

public class NodeUser {
	
	private String cor;
	private NodeUser NoEsquerdo;
	private NodeUser NoDireito;
	private NodeUser NoPai;
	private User usuario;
	
	public NodeUser(){
		
	}
	
	public NodeUser(String cor, User usuario) {
		this.cor = cor;
		this.usuario = usuario;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public NodeUser getNoPai() {
		return NoPai;
	}

	public void setNoPai(NodeUser noPai) {
		NoPai = noPai;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeUser other = (NodeUser) obj;
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public String toString() {
		return "\n NodeUser "
				+ "\n cor :" + cor + 
				"\n NoEsquerdo: " + this.NoEsquerdo.getUsuario() + 
				"\n NoDireito: " + this.NoDireito.getUsuario() + 
				"\n NoPai: " + this.NoPai.getUsuario() + 
				"\n livro: " + usuario + 
				"\n \n \n";
	}

}
