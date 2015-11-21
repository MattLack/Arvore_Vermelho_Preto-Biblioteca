package arbor;

import models.Book;
import nodes.NodeBook;

public class ArvoreBook {
	
	private String vermelho = "VERMELHO";
	private String preto = "PRETO";
	private NodeBook None;
	private NodeBook raiz;
	
	
	//------------- CONSTRUTOR DA ÁRVORE ---------------//
	
	public ArvoreBook() {
		this.None = new NodeBook(preto,null);
		this.None.setNoDireito(None);
		this.None.setNoDireito(None);
		this.None.setNoPai(None);
		this.raiz = this.None;
	}
	
	//--------------------------------------------------//	
	
	
	//-------------------- MÉTODO DE INSERÇÃO -------------------------//
	
	public void RBInsert(ArvoreBook T, NodeBook z){
		
		NodeBook y = T.None;
		NodeBook x = T.raiz;
		while(x != T.None){
			y = x;
			if(z.getLivro().getPreco() < x.getLivro().getPreco()){
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
		}else if(z.getLivro().getPreco() < y.getLivro().getPreco()){
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
	
	private void RBInsertFixup(ArvoreBook T, NodeBook z){
		
		NodeBook y = T.None;
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
	
	public void leftRotate(ArvoreBook T,NodeBook x){
		
		NodeBook y = x.getNoDireito();
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
	
	public void rightRotate(ArvoreBook T, NodeBook x){
		
		NodeBook y = x.getNoEsquerdo();
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
	
	public void Transplant(ArvoreBook T, NodeBook nodeA, NodeBook nodeB){
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
	
	public NodeBook TreeMinimum(NodeBook node){
		NodeBook none = new NodeBook(preto, null);
		while(node.getNoEsquerdo() != none){
			node = node.getNoEsquerdo();
		}
		return node;
	}
	
	//---------------------------------------------------//
	
	
	
	
	public static NodeBook TreeSearch(NodeBook nodeAtual, NodeBook nodeBusca){
		
		if(isNone(nodeAtual) || nodeAtual.getLivro().getPreco() == nodeBusca.getLivro().getPreco()){
			return nodeAtual;
		}
		if(nodeAtual.getLivro().getPreco() < nodeBusca.getLivro().getPreco()){
			return TreeSearch(nodeAtual.getNoEsquerdo(), nodeBusca);
		}else{
			return TreeSearch(nodeAtual.getNoDireito(), nodeBusca);
		}
	}	
	
	
	
	public static NodeBook TreeSucessor(NodeBook node){
		
		if(!isNone(node)){
			return TreeSucessor(node.getNoDireito());
		}
		NodeBook nodeAux = node.getNoPai();
		while(!isNone(nodeAux) && node == nodeAux.getNoDireito()){
			node = nodeAux;
			nodeAux = nodeAux.getNoPai();
		}
		return nodeAux;
	}
	
	
	
	//------------------------ METODO DE EXCLUSÃO -----------------------//
	
	public void RBDelete(ArvoreBook T, NodeBook z){
		
		NodeBook y,x = T.None;
		y = z;
		String corY = y.getCor();
		if(z.getNoEsquerdo() == T.None){
			x = z.getNoDireito();
			this.Transplant(T, z, z.getNoDireito());
		}else if(z.getNoDireito() == T.None){
			x = z.getNoEsquerdo();
			this.Transplant(T, z, z.getNoEsquerdo());
		}else{
			y = this.TreeMinimum(z.getNoDireito());
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
	//CONCERTAR
	
	public static void RBDeleteFixup(NodeBook x){
		
		NodeBook w = None;
		while(x != raiz && x.getCor() == preto){
			if(x == x.getNoPai().getNoEsquerdo()){
				w = x.getNoPai().getNoDireito();
				if(w.getCor() == vermelho){
					w.setCor(preto);
					x.getNoPai().setCor(vermelho);
					leftRotate(x.getNoPai());
					w = x.getNoPai().getNoDireito();
				}
				if(w.getNoEsquerdo().getCor() == preto && w.getNoDireito().getCor() == preto){
					w.setCor(vermelho);
					x = x.getNoPai();
				}else if(w.getNoDireito().getCor() == preto){
					w.getNoEsquerdo().setCor(preto);
					w.setCor(vermelho);
					rightRotate(w);//
					w = x.getNoPai().getNoDireito();
					w.setCor(x.getNoPai().getCor());
					x.getNoPai().setCor(preto);
					w.getNoDireito().setCor(preto);
					leftRotate(x.getNoPai());
					x = raiz;
				}
			}else{
				w = x.getNoPai().getNoEsquerdo();
				if(w.getCor() == vermelho){
					w.setCor(preto);
					x.getNoPai().setCor(vermelho);
					rightRotate(x.getNoPai());
					w = x.getNoPai().getNoEsquerdo();
				}
				if(w.getNoDireito().getCor() == preto && w.getNoEsquerdo().getCor() == preto){
					w.setCor(vermelho);
					x = x.getNoPai();
				}else if(w.getNoEsquerdo().getCor() == preto){
					w.getNoDireito().setCor(preto);
					w.setCor(vermelho);
					leftRotate(w);
					w = x.getNoPai().getNoEsquerdo();
					w.setCor(x.getNoPai().getCor());
					x.getNoPai().setCor(preto);
					w.getNoEsquerdo().setCor(preto);
					rightRotate(x.getNoPai());
					x = raiz;
				}
			}
		}
		x.setCor(preto);
	}
	
	//-------------------------------------------------------------------//
	
	
	
	public static void main(String[] args) {
				
		Book test1 = new Book("aaa", "xxx", 11);
		NodeBook noTeste1 = new NodeBook(test1);
		
		RBInsert(noTeste1);
		
		Book test2 = new Book("bbb", "xxx", 2);
		NodeBook noTeste2 = new NodeBook(test2);
		
		RBInsert(noTeste2);
		
		/*Book test3 = new Book("ccc", "xxx", 1);
		NodeBook noTeste3= new NodeBook(test3);
		
		RBInsert(noTeste3);*/
		
		/*Book test4 = new Book("ddd", "xxx", 7);
		NodeBook noTeste4= new NodeBook(test4);
		
		RBInsert(noTeste4);
		
		Book test5 = new Book("eee", "xxx", 5);
		NodeBook noTeste5= new NodeBook(test5);
		
		RBInsert(noTeste5);*/
		
		/*Book test6 = new Book("aaa", "xxx", 8);
		NodeBook noTeste6 = new NodeBook(test6);
		
		RBInsert(noTeste6);
		
		Book test7 = new Book("aaa", "xxx", 14);
		NodeBook noTeste7 = new NodeBook(test7);
		
		RBInsert(noTeste7);
		
		Book test8 = new Book("aaa", "xxx", 15);
		NodeBook noTeste8 = new NodeBook(test8);
		
		RBInsert(noTeste8);*/
		
		
		
		System.out.println("raiz");
		System.out.println(raiz);
		System.out.println("noteste1");
		System.out.println(noTeste1);
		System.out.println("noteste2");
		System.out.println(noTeste2);
		/*System.out.println("noteste3");
		System.out.println(noTeste3);*/
		/*System.out.println("noteste4");
		System.out.println(noTeste4);
		System.out.println("noteste5");
		System.out.println(noTeste5);*/
		/*System.out.println("noteste6");
		System.out.println(noTeste6);
		System.out.println("noteste7");
		System.out.println(noTeste7);
		System.out.println("noteste8");
		System.out.println(noTeste8);*/
		
		
		
		
		
	}

}
