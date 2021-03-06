
/**
 * 
 * UNIVERSIDADE FEDERAL RURAL DE PERNAMBUCO
 * DISCIPLINA: ALGORITMOS E ESTRUTURA DE DADOS
 * CURSO DO DISCENTE: BACHARELADO EM CI�NCIA DA COMPUTA��O
 * CURSO DA DISCIPLINA OFERTADA: BACHARELADO EM SISTEMA DE INFORMA��O
 * EMAIL DO DISCENTE: A.MATEUSLOL@GMAIL.COM
 * DISCENTE: ANDERSON MATEUS DA SILVA
 * @author Matt_lackome
 *
 */

package arbor;

import models.Book;
import nodes.NodeBook;

public class ArvoreBook {
	
	private String vermelho = "VERMELHO";
	private String preto = "PRETO";
	public NodeBook None;
	public NodeBook raiz;
	
	
	//------------- CONSTRUTOR DA �RVORE ---------------//
	
	public ArvoreBook() {
		this.None = new NodeBook(preto,null);
		this.None.setNoDireito(None);
		this.None.setNoDireito(None);
		this.None.setNoPai(None);
		this.raiz = this.None;
	}
	
	//--------------------------------------------------//	
	
	
	//-------------------- M�TODO DE INSER��O -------------------------//
	
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
	
	
	
	// --------------- M�TODO DE BALANCEAMENTO EM INSER��O ----------------//
	
	private void RBInsertFixup(ArvoreBook T, NodeBook z){
		
		NodeBook y = T.None;
		while(z.getNoPai().getCor().equals(vermelho)){

			if(z.getNoPai() == z.getNoPai().getNoPai().getNoEsquerdo()){
				y = z.getNoPai().getNoPai().getNoEsquerdo(); // <--- Poss�vel bug no pseudoc�digo
				
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
				y = z.getNoPai().getNoPai().getNoDireito(); // <-- Erro no pseudoc�digo 
				
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
	
	
	//---------------------- ROTA��O A ESQUERDA ------------------------//
	
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
	
	
	
	//----------------------- ROTA��O A DIREITA -----------------------//
	
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
	
	public NodeBook TreeMinimum(ArvoreBook T,NodeBook node){
		
		while(node.getNoEsquerdo() != T.None){
			node = node.getNoEsquerdo();
		}
		return node;
	}
	
	//---------------------------------------------------//
	
	
	
	public boolean TreeSearchExistNode(ArvoreBook T, NodeBook livro){
		
		NodeBook y = T.None;		
		NodeBook x = T.raiz;
	
		while(x != T.None){
			y = x;
			if(livro.getLivro().getPreco() < x.getLivro().getPreco()){
				x = x.getNoEsquerdo();
				if((x != T.None)){
					if(x.getLivro().equals(livro.getLivro())){
						return true;
					}
				}
			}else{
				x = x.getNoDireito();
				if((x != T.None)){
					if(x.getLivro().equals(livro.getLivro())){
						return true;
					}
				}
			}
		}
		return false;
	}


	public NodeBook TreeSearch(ArvoreBook T, NodeBook book){
	
		if(this.TreeSearchExistNode(T, book)){
			NodeBook y = T.None;		
			NodeBook x = T.raiz;
			while(x != T.None){
				y = x;
				if(book.getLivro().getPreco() < x.getLivro().getPreco()){
					x = x.getNoEsquerdo();
					if(x.getLivro().equals(book.getLivro())){
						return  x;
					}
				}else{
					x = x.getNoDireito();
					if(x.getLivro().equals(book.getLivro())){
						return x;
					}
				}
			}
		}

		return T.None;
	}
	
	
	
	public boolean TreeSearchRemove(ArvoreBook T, NodeBook book){
		
		NodeBook nodeBusca = this.TreeSearch(T, book);
		
		if(nodeBusca.equals(T.None)){
			return false;
		}else{
			this.RBDelete(T, nodeBusca);
			return true;
		}
		
	}
	
	
	public void TreePrint(ArvoreBook T, NodeBook node){
		if(!(node.equals(T.None))){
			System.out.println(node);
			if(!(node.getNoEsquerdo().equals(T.None)))
				TreePrint(T, node.getNoEsquerdo());
			if(!(node.getNoDireito().equals(T.None)))
				TreePrint(T,node.getNoDireito());			
		}
	}
	
	public void TreePrintBook(ArvoreBook T, NodeBook node){
		if(!(node.equals(T.None))){
			System.out.println(node.getLivro());
			if(!(node.getNoEsquerdo().equals(T.None)))
				TreePrintBook(T, node.getNoEsquerdo());
			if(!(node.getNoDireito().equals(T.None)))
				TreePrintBook(T,node.getNoDireito());			
		}
	}
	
	/*public NodeBook fazendoEmprestimo(ArvoreBook T, NodeBook x ){
		
		x = T.TreeSearch(T, x);
		
		x.getLivro().setLoan(true);
		
		return x;
		
	}*/
	
	//------------------------ METODO DE EXCLUS�O -----------------------//
	
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

	
	
	//-------------- M�TODO DE BALANCEAMENTO DE EXCLUS�O ----------------//
		
	public void RBDeleteFixup(ArvoreBook T, NodeBook x){
		
		NodeBook w = T.None;
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
	
	
	
	public static void main(String[] args) {
		
		ArvoreBook arvore = new ArvoreBook();
		ArvoreUser arvoreU = new ArvoreUser();
		
		Book test1 = new Book("aaa", "xxx", 11);
		NodeBook noTeste1 = new NodeBook();
		noTeste1.setLivro(test1);
		
		arvore.RBInsert(arvore, noTeste1);
		
		Book test2 = new Book("bbb", "xxx", 2);
		NodeBook noTeste2 = new NodeBook();
		noTeste2.setLivro(test2);
		
		arvore.RBInsert(arvore,noTeste2);
		
		Book test3 = new Book("ccc", "xxx", 1);
		NodeBook noTeste3= new NodeBook();
		noTeste3.setLivro(test3);
		
		arvore.RBInsert(arvore,noTeste3);
		
		Book test4 = new Book("ddd", "xxx", 7);
		NodeBook noTeste4= new NodeBook();
		noTeste4.setLivro(test4);
		
		arvore.RBInsert(arvore,noTeste4);
		
		Book test5 = new Book("eee", "xxx", 5);
		NodeBook noTeste5= new NodeBook();
		noTeste5.setLivro(test5);
		
		arvore.RBInsert(arvore,noTeste5);
		
		Book test6 = new Book("aaa", "xxx", 8);
		NodeBook noTeste6 = new NodeBook();
		noTeste6.setLivro(test6);
		
		arvore.RBInsert(arvore,noTeste6);
		
		Book test7 = new Book("aaa", "xxx", 14);
		NodeBook noTeste7 = new NodeBook();
		noTeste7.setLivro(test7);
		
		arvore.RBInsert(arvore,noTeste7);
		
		Book test8 = new Book("aaa", "xxx", 15);
		NodeBook noTeste8 = new NodeBook();
		noTeste8.setLivro(test8);
		
		arvore.RBInsert(arvore,noTeste8);
		
		/*User usu5 = new User("aaaa", 4);
		NodeUser node5 = new NodeUser();
		node5.setUsuario(usu5);*/
		
		//arvore.RBDelete(arvore, noTeste8);

		//System.out.println(arvore.TreeSearch(arvore, noTeste5));		
	
		
		/*System.out.println("noteste1");
		System.out.println(noTeste1);		
		System.out.println("noteste2");
		System.out.println(noTeste2);
		System.out.println("noteste3");
		System.out.println(noTeste3);
		System.out.println("noteste4");
		System.out.println(noTeste4);
		System.out.println("noteste5");
		System.out.println(noTeste5);
		System.out.println("noteste6");
		System.out.println(noTeste6);
		System.out.println("noteste7");
		System.out.println(noTeste7);
		System.out.println("noteste8");
		System.out.println(noTeste8);*/
		
		
		
		
		
	}

}
