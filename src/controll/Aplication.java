package controll;

import java.util.Scanner;
import arbor.ArvoreBook;
import arbor.ArvoreUser;
import models.Book;
import models.User;
import nodes.NodeBook;
import nodes.NodeUser;

public class Aplication{
	
	NodeBook nod = new NodeBook();
	NodeUser nodU = new NodeUser();
	
	//----- CONSTRUTOR DA CLASSE -----//
	
	public Aplication(){
		
	}
	
	//--------------------------------//
	
	
	//------------------- METODO PARA CADASTRAR LIVROS ------------------//
	
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
	
	//--------------------------------------------------------------------//
	
	
	//------------------ METODO PARA CADASTRAR USUARIOS ------------------//
	
	public boolean CadastrarUsuario(ArvoreUser arb, User user){
		nodU.setUsuario(user);
		if(arb.TreeSearch(arb, arb.getRaiz(arb), nodU).equals(arb.getNone(arb))){
			arb.RBInsert(arb, nodU);
			return true;
		}
		else{
			return false;
		}
	}
	
	//---------------------------------------------------------------------//
	
	
	//------------- METODO PARA REMOVER LIVROS ------------//
	
	public boolean RemoverLivro(ArvoreBook T, Book book){
		nod.setLivro(book);
		return T.TreeSearchRemove(T, T.getRaiz(T), nod);
	}
	
	//-----------------------------------------------------//
	
	
	//-------------- METODO PARA REMOVER USUARIOS-----------//
	
	public boolean RemoverUsuario(ArvoreUser T, User user){
		nodU.setUsuario(user);
		return T.TreeSearchRemove(T, T.getRaiz(T), nodU);
	}
	
	//------------------------------------------------------//
	
	
	//--------------- METODO PARA EFETUAR E VALIDAR O EMPRESTIMO DE LIVRO ----------------//
	
	public boolean EmprestimoLivro(ArvoreBook AB, ArvoreUser AU, Book livro, User usuario){
		nod.setLivro(livro);
		nodU.setUsuario(usuario);
		NodeBook nodoB = AB.TreeSearch(AB, AB.getRaiz(AB), nod);
		NodeUser nodoU = AU.TreeSearch(AU, AU.getRaiz(AU), nodU);
		if(nodoB.equals(AB.getNone(AB)) || nodoU.equals(AU.getNone(AU)))
			return false;
		else if(nodoB.getLivro().getLoan() == false && nodoU.getUsuario().getLimitedLoan() == false){
			nodoB.getLivro().setUserLoan(usuario);
			nodoU.getUsuario().setBookLoan(livro);
			nodoB.getLivro().setLoan(true);
			nodoU.getUsuario().setLimitedLoan(true);
			return true;
		}else
			return false;
	}
	
	//------------------------------------------------------------------------------------//
	
	
	//------------------ METODO PARA EFETUAR E VALIDAR A DEVOLUCAO DE UM LIVRO ---------------------//
	
	public boolean DevolucaoLivro(ArvoreBook AB, ArvoreUser AU, Book livro, User usuario){
		nod.setLivro(livro);
		nodU.setUsuario(usuario);
		NodeBook nodoB = AB.TreeSearch(AB, AB.getRaiz(AB), nod);
		NodeUser nodoU = AU.TreeSearch(AU, AU.getRaiz(AU), nodU);
		if(nodoB.equals(AB.getNone(AB)) || nodoU.equals(AU.getNone(AU)))
			return false;
		else if(nodoB.getLivro().getLoan() == true && nodoU.getUsuario().getLimitedLoan() == true){
				if((nodoB.getLivro().getUserLoan().equals(usuario)) && nodoU.getUsuario().getBookLoan().equals(livro)){
					nodoB.getLivro().setUserLoan(null);
					nodoU.getUsuario().setBookLoan(null);
					nodoB.getLivro().setLoan(false);
					nodoU.getUsuario().setLimitedLoan(false);
					return true;
				}else
					return false;
		}else
			return false;
	}
	
	//------------------------------------------------------------------------------------------------//
	
	
	//---------------- MENU DE OPÇÕES --------------//
	
	public void menu(){
		
		System.out.println("1 - cadastrar livro");
		System.out.println("2 - cadastrar usuario");
		System.out.println("3 - emprestimo de livro");
		System.out.println("4 - devolucao de livro");
		System.out.println("5 - remocao livro");
		System.out.println("6 - remocao usuario");
		System.out.println("7 - ");
		System.out.println("0 - sair");
	}
	
	//----------------------------------------------//
	
	
	public static void main(String[] args){
		
		Aplication apl = new Aplication();
		
		ArvoreBook arvore = new ArvoreBook();
		ArvoreUser arvoreU = new ArvoreUser();
		
		Scanner teclado = new Scanner(System.in);
		
		int n = 1;
		
		while(n!=0){

			apl.menu();
			
			int op = teclado.nextInt();
			teclado.nextLine();

			switch(op){
			
			case 0:
				n = 0;
				teclado.nextLine();
				break;
				
			case 1:
				System.out.println("Digite o titulo do livro:");
				String titulo = teclado.nextLine();
				System.out.println("Digite o autor do livro:");
				String autor = teclado.nextLine();
				System.out.println("Digite o preco do livro:");
				double preco = teclado.nextDouble();
				Book livro = new Book(titulo, autor, preco);
				if(apl.CadastroLivro(arvore, livro))
					System.out.println("\nLivro cadastrado com sucesso\n");
				else
					System.out.println("\nLivro ja existe\n");
				teclado.nextLine();

				break;

			case 2:
				System.out.println("Digite o nome do usuario:");
				String nome = teclado.nextLine();
				System.out.println("Digite a identidade do usuario:");
				int id = teclado.nextInt();
				User usuario = new User(nome, id);
				if(apl.CadastrarUsuario(arvoreU, usuario))
					System.out.println("\nUsuario cadastrado com sucesso\n");
				else
					System.out.println("\nUsuario ja existe");
				teclado.nextLine();
				
				break;
				
			case 3:

				break;

			case 4:

				break;

			case 5:
				
				System.out.println("Digite titulo do livro a ser removido:");
				String tituloRemover = teclado.nextLine();
				System.out.println("Digite o autor do livro a ser removido:");
				String autorRemover = teclado.nextLine();
				System.out.println("Digite o preco do livro a ser removido:");
				double precoRemover = teclado.nextDouble();
				Book livroRemover = new Book(tituloRemover, autorRemover, precoRemover);
				if(apl.RemoverLivro(arvore, livroRemover))
					System.out.println("Livro removido com sucesso");
				else
					System.out.println("Livro nao encontrado");
				teclado.nextLine();
				
				break;

			case 6:
				
				System.out.println("Digite o nome do usuario a ser removido:");
				String nomeRemover = teclado.nextLine();
				System.out.println("Digite a identidade do usuario a ser removido:");
				int idRemover = teclado.nextInt();
				User usuarioRemover = new User(nomeRemover, idRemover);
				if(apl.RemoverUsuario(arvoreU, usuarioRemover))
					System.out.println("Usuario removido com sucesso");
				else
					System.out.println("Usuario nao encontrado");
				teclado.nextLine();

				break;

			case 7:

				break;

			default:
				System.out.println("Opcao Invalida");
				break;
			}
		}
	}

}
