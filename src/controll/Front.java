package controll;

import Nodes.NodeBook;
import Nodes.NodeUser;

public class Front {
	
	
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
