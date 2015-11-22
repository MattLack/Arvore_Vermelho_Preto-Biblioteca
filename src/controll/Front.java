package controll;

import arbor.ArvoreBook;
import models.Book;
import nodes.NodeBook;
import nodes.NodeUser;

public class Front {
	
	NodeBook nod = new NodeBook();
	
	public boolean CadastroLivro(ArvoreBook arb, Book book){
		nod.setLivro(book);
		if(arb.TreeSearch(arb, arb.getRaiz(arb), nod).equals(arb.getNone(arb))){
			arb.RBInsert(arb, nod);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void LoanBook(NodeBook nodeLivro, NodeUser nodeUser){
		
		NodeBook node = //(arvore do livro) TreeSearch(raiz, nodeLivro);
		node.getLivro().setLoan(true);
		
		NodeUser User = //(arvore do usuario) TreeSearch(raiz,nodeUser);
		User.getUsuario().setLimitedLoan(true);
	}
	
	
	public void DevolutionBook(NodeBook nodeLivro, NodeUser nodeUser){
		
		NodeBook node = //(arvore do livro) TreeSearch(raiz, nodeLivro);
		node.getLivro().setLoan(false);
		
		NodeUser User = //(arvore do usuario) TreeSearch(raiz,nodeUser);
		User.getUsuario().setLimitedLoan(false);
	}
	
	

}
