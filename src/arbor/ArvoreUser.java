package arbor;

import models.User;
import nodes.NodeUser;

public class ArvoreUser {
	
	private String vermelho = "VERMELHO";
	private String preto = "PRETO";
	private NodeUser None;
	private NodeUser raiz;
	
	//------------- Metodo que retorna a raiz-----------//
	
	public NodeUser getRaiz(ArvoreUser n){
		return n.raiz;
	}
	
	//--------------------------------------------------//
	
	
	//------------- Metodo que retorna o None-----------//
	
	public NodeUser getNone(ArvoreUser n){
		return n.None;
	}
		
	//--------------------------------------------------//
	
	
	//------------- CONSTRUTOR DA ÁRVORE ---------------//
	
	public ArvoreUser() {
		this.None = new NodeUser(preto,null);
		this.None.setNoDireito(None);
		this.None.setNoDireito(None);
		this.None.setNoPai(None);
		this.raiz = this.None;
	}
	
	//--------------------------------------------------//	
	
	
	//-------------------- MÉTODO DE INSERÇÃO -------------------------//
	
	public void RBInsert(ArvoreUser T, NodeUser z){
		
		NodeUser y = T.None;
		NodeUser x = T.raiz;
		while(x != T.None){
			y = x;
			if(z.getUsuario().getId() < x.getUsuario().getId()){
				x = x.getNoEsquerdo(); 
			}else{
				x = x.getNoDireito();
			}
		}
		z.setNoPai(y);
		if(y.equals(T.None)){
			T.raiz = z;
			T.raiz.setNoDireito(T.None);
			T.raiz.setNoEsquerdo(T.None);
			T.raiz.setNoPai(T.None);
		}else if(z.getUsuario().getId() < y.getUsuario().getId()){
			y.setNoEsquerdo(z);
		}else{
			y.setNoDireito(z);
		}
		z.setNoEsquerdo(T.None);
		z.setNoDireito(T.None);
		z.setCor(vermelho);
		this.RBInsertFixup(T,z);
		
	}
	
	//---------------------------------------------------------------//
	
	
	
	// --------------- MÉTODO DE BALANCEAMENTO EM INSERÇÃO ----------------//
	
	private void RBInsertFixup(ArvoreUser T, NodeUser z){
		
		NodeUser y = T.None;
		while(z.getNoPai().getCor().equals(vermelho)){

			if(z.getNoPai() == z.getNoPai().getNoPai().getNoEsquerdo()){
				y = z.getNoPai().getNoPai().getNoEsquerdo(); // <--- Possível bug no pseudocódigo
				
				if(y.getCor().equals(vermelho)){
					z.getNoPai().setCor(preto);
					y.setCor(preto);
					z.getNoPai().getNoPai().setCor(vermelho);
					z = z.getNoPai().getNoPai();
				}
				else if(z == z.getNoPai().getNoDireito()){
						z = z.getNoPai();
						this.leftRotate(T,z); 				
						z.getNoPai().setCor(preto);
						z.getNoPai().getNoPai().setCor(vermelho);
						this.rightRotate(T,z.getNoPai().getNoPai());
					}		
			}else{
				y = z.getNoPai().getNoPai().getNoDireito(); // <-- Erro no pseudocódigo 
				
				if(y.getCor().equals(vermelho)){
					z.getNoPai().setCor(preto);
					y.setCor(preto);
					z.getNoPai().getNoPai().setCor(vermelho);
					z = z.getNoPai().getNoPai();
				}
				else if(z == z.getNoPai().getNoEsquerdo()){
						z = z.getNoPai();	
						this.leftRotate(T,z); 				
						z.getNoPai().setCor(preto);
						z.getNoPai().getNoPai().setCor(vermelho);
						this.rightRotate(T,z.getNoPai().getNoPai());
				}
			}
		}
		T.raiz.setCor(preto);
	}

	//--------------------------------------------------------------------//
	
	
	//---------------------- ROTAÇÃO A ESQUERDA ------------------------//
	
	public void leftRotate(ArvoreUser T,NodeUser x){
		
		NodeUser y = x.getNoDireito();
		x.setNoDireito(y.getNoEsquerdo());		
		y.getNoEsquerdo().setNoPai(x);
		y.setNoPai(x.getNoPai());
		if(x.getNoPai() == null){
			T.raiz = y;
		}else if(x == x.getNoPai().getNoEsquerdo()){
			x.getNoPai().setNoEsquerdo(y);
		}else{
			x.getNoPai().setNoDireito(y);
		}
		y.setNoEsquerdo(x);
		x.setNoPai(y);
	}
	
	//------------------------------------------------------------------//
	
	
	
	//----------------------- ROTAÇÃO A DIREITA -----------------------//
	
	public void rightRotate(ArvoreUser T, NodeUser x){
		
		NodeUser y = x.getNoEsquerdo();
		x.setNoEsquerdo(y.getNoDireito());         
		y.getNoDireito().setNoPai(x);
		y.setNoPai(x.getNoPai());
		if(x.getNoPai() == null){
			T.raiz = y;
		}else if(x == x.getNoPai().getNoDireito()){
			x.getNoPai().setNoDireito(y);
		}else{
			x.getNoPai().setNoEsquerdo(y);
		}
		y.setNoDireito(x);
		x.setNoPai(y);
	}
	
	//-----------------------------------------------------------------//
	
	
	
	//-----------------------------------------------------------------//
	
	public void Transplant(ArvoreUser T, NodeUser nodeA, NodeUser nodeB){
		if(nodeA.getNoPai() == T.None){
			T.raiz = nodeB;
		}else if(nodeA == nodeA.getNoPai().getNoEsquerdo()){
			nodeA.getNoPai().setNoEsquerdo(nodeB);
		}else{
			nodeA.getNoPai().setNoDireito(nodeB);
			nodeB.setNoPai(nodeA.getNoPai());
		}
	}
	
	// ----------------------------------------------------------------//
	
	
	//---------------- MENOR ELEMENTO DA ARVORE ----------//
	
	public NodeUser TreeMinimum(ArvoreUser T,NodeUser node){
		
		while(node.getNoEsquerdo() != T.None){
			node = node.getNoEsquerdo();
		}
		return node;
	}
	
	//---------------------------------------------------//
	
	
	
	
	public NodeUser TreeSearch(ArvoreUser T, NodeUser nodeAtual, NodeUser nodeBusca){
		
		if(nodeAtual.equals(T.None) || nodeAtual.getUsuario() == nodeBusca.getUsuario()){
			return nodeAtual;
		}
		if(nodeAtual.getUsuario().getId() < nodeBusca.getUsuario().getId()){
			return TreeSearch(T,nodeAtual.getNoEsquerdo(), nodeBusca);
		}else{
			return TreeSearch(T,nodeAtual.getNoDireito(), nodeBusca);
		}
	}
	
	
	
	public boolean TreeSearchRemove(ArvoreUser T, NodeUser nodeAtual, NodeUser nodeBusca){
		
		NodeUser aux = this.TreeSearch(T, nodeAtual, nodeBusca);
		
		if(aux.equals(T.None)){
			return false;
		}else{
			this.RBDelete(T, aux);
			return true;
		}
		
	}
	
	
	//------------------------ METODO DE EXCLUSÃO -----------------------//
	
	public void RBDelete(ArvoreUser T, NodeUser z){
		
		NodeUser y,x = T.None;
		y = z;
		String corY = y.getCor();
		if(z.getNoEsquerdo() == T.None){
			x = z.getNoDireito();
			this.Transplant(T, z, z.getNoDireito());
		}else if(z.getNoDireito() == T.None){
			x = z.getNoEsquerdo();
			this.Transplant(T, z, z.getNoEsquerdo());
		}else{
			y = this.TreeMinimum(T,z.getNoDireito());
			corY = y.getCor();
			x = y.getNoDireito();
			if(y.getNoPai().equals(z)){
				x.setNoPai(y);
			}else{
				this.Transplant(T, y, y.getNoDireito());
				y.setNoDireito(z.getNoEsquerdo());
				y.getNoEsquerdo().setNoPai(y);
				y.setCor(z.getCor());
			}
		}
		if(corY.equals(preto)){
			this.RBDeleteFixup(T, x);
		}
	}
	
	//-------------------------------------------------------------------//

	
	
	//-------------- MÉTODO DE BALANCEAMENTO DE EXCLUSÃO ----------------//
		
	public void RBDeleteFixup(ArvoreUser T, NodeUser x){
		
		NodeUser w = T.None;
		while( !(x.equals(T.raiz)) && x.getCor().equals(preto)){
			if(x.equals(x.getNoPai().getNoEsquerdo())){
				w = x.getNoPai().getNoDireito(); 
				if(w.getCor().equals(vermelho)){
					w.setCor(preto);
					x.getNoPai().setCor(vermelho);
					this.leftRotate(T,x.getNoPai());
					w = x.getNoPai().getNoDireito();
				}
				if( (w.getNoEsquerdo().getCor().equals(preto)) && (w.getNoDireito().getCor().equals(preto))){
					w.setCor(vermelho);
					x = x.getNoPai();
				}else if(w.getNoDireito().getCor().equals(preto)){
						w.getNoEsquerdo().setCor(preto);
						w.setCor(vermelho);
						this.rightRotate(T,w);
						w = x.getNoPai().getNoDireito();
					}
					w.setCor(x.getNoPai().getCor());
					x.getNoPai().setCor(preto);
					w.getNoDireito().setCor(preto);
					this.leftRotate(T,x.getNoPai());
					x = T.raiz;
			}else{
				w = x.getNoPai().getNoEsquerdo();
				if(w.getCor().equals(vermelho)){
					w.setCor(preto);
					x.getNoPai().setCor(vermelho);
					this.leftRotate(T,x.getNoPai());
					w = x.getNoPai().getNoEsquerdo();
				}
				if( (w.getNoDireito().getCor().equals(preto)) && (w.getNoEsquerdo().getCor().equals(preto))){
					w.setCor(vermelho);
					x = x.getNoPai();
				}else if(w.getNoEsquerdo().getCor().equals(preto)){
						w.getNoDireito().setCor(preto);
						w.setCor(vermelho);
						this.rightRotate(T,w);
						w = x.getNoPai().getNoEsquerdo();
					}
					w.setCor(x.getNoPai().getCor());
					x.getNoPai().setCor(preto);
					w.getNoEsquerdo().setCor(preto);
					this.leftRotate(T,x.getNoPai());
					x = T.raiz;
			}
		}
		x.setCor(preto);
	}
	
	//-------------------------------------------------------------------//
	
	
	public static void main(String[] args){
		
		ArvoreUser arbo = new ArvoreUser();
		
		User usu1 = new User("aaa", 3);
		NodeUser node1 = new NodeUser();
		node1.setUsuario(usu1);
		
		arbo.RBInsert(arbo, node1);
		
		User usu2 = new User("aa", 2);
		NodeUser node2 = new NodeUser();
		node2.setUsuario(usu2);
		
		arbo.RBInsert(arbo, node2);
		
		User usu3 = new User("aaaa", 5);
		NodeUser node3 = new NodeUser();
		node3.setUsuario(usu3);
		
		arbo.RBInsert(arbo, node3);
		
		User usu4 = new User("aaaa", 1);
		NodeUser node4 = new NodeUser();
		node4.setUsuario(usu4);
		
		arbo.RBInsert(arbo, node4);
		
		User usu5 = new User("aaaa", 4);
		NodeUser node5 = new NodeUser();
		node5.setUsuario(usu5);
		
		arbo.RBInsert(arbo, node5);
		
		User usu6 = new User("aaaa", 8);
		NodeUser node6 = new NodeUser();
		node6.setUsuario(usu6);
		
		arbo.RBInsert(arbo, node6);
		
		
		System.out.println("node1");
		System.out.println(node1);
		System.out.println("node2");
		System.out.println(node2);
		System.out.println("node3");
		System.out.println(node3);
		System.out.println("node4");
		System.out.println(node4);
		System.out.println("node5");
		System.out.println(node5);
		System.out.println("node6");
		System.out.println(node6);
		
		
		
	}
	
}
	