package controll;

import java.util.Scanner;
import arbor.ArvoreBook;
import arbor.ArvoreUser;
import models.Book;
import models.User;
import nodes.NodeBook;
import nodes.NodeUser;

public class Aplication{
	
	//----- CONSTRUTOR DA CLASSE -----//
	
	public Aplication(){
		
	}
	
	//--------------------------------//
	
	
	//------------------- METODO PARA CADASTRAR LIVROS ------------------//
	
	public boolean CadastroLivro(ArvoreBook arb, Book book){		
		NodeBook nod = new NodeBook();
		nod.setLivro(book);
		if(arb.TreeSearch(arb, nod).equals(arb.None)){
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
		NodeUser nodU = new NodeUser();
		nodU.setUsuario(user);
		if(arb.TreeSearch(arb, nodU).equals(arb.None)){
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
		NodeBook nod = new NodeBook();
		nod.setLivro(book);
		return T.TreeSearchRemove(T, nod);
	}
	
	//-----------------------------------------------------//
	
	
	//-------------- METODO PARA REMOVER USUARIOS-----------//
	
	public boolean RemoverUsuario(ArvoreUser T, User user){
		NodeUser nodU = new NodeUser();
		nodU.setUsuario(user);
		return T.TreeSearchRemove(T, nodU);
	}
	
	//------------------------------------------------------//
	
	
	//--------------- METODO PARA EFETUAR E VALIDAR O EMPRESTIMO DE LIVRO ----------------//
	
	public int EmprestimoLivro(ArvoreBook AB, ArvoreUser AU, Book livro, User usuario){
		NodeBook nod = new NodeBook();
		NodeUser nodU = new NodeUser();
		nod.setLivro(livro);
		nodU.setUsuario(usuario);
		NodeBook nodoB = AB.TreeSearch(AB, nod);
		NodeUser nodoU = AU.TreeSearch(AU, nodU);
		if(nodoB.equals(AB.None) || nodoU.equals(AU.None))
			return 1;
		else if(nodoB.getLivro().getLoan() == false && nodoU.getUsuario().getLimitedLoan() == false){
			nodoB.getLivro().setUserLoan(usuario);
			nodoU.getUsuario().setBookLoan(livro);
			nodoB.getLivro().setLoan(true);
			nodoU.getUsuario().setLimitedLoan(true);
			return 0;
		}else
			return 2;
	}
	
	//------------------------------------------------------------------------------------//
	
	
	//------------------ METODO PARA EFETUAR E VALIDAR A DEVOLUCAO DE UM LIVRO ---------------------//
	
	public int DevolucaoLivro(ArvoreBook AB, ArvoreUser AU, Book livro, User usuario){
		NodeBook nod = new NodeBook();
		NodeUser nodU = new NodeUser();
		nod.setLivro(livro);
		nodU.setUsuario(usuario);
		NodeBook nodoB = AB.TreeSearch(AB, nod);
		NodeUser nodoU = AU.TreeSearch(AU, nodU);
		if(nodoB.equals(AB.None) || nodoU.equals(AU.None))
			return 1;
		else if(nodoB.getLivro().getLoan() == true && nodoU.getUsuario().getLimitedLoan() == true){
				if((nodoB.getLivro().getUserLoan().equals(usuario)) && nodoU.getUsuario().getBookLoan().equals(livro)){
					nodoB.getLivro().setUserLoan(null);
					nodoU.getUsuario().setBookLoan(null);
					nodoB.getLivro().setLoan(false);
					nodoU.getUsuario().setLimitedLoan(false);
					return 0;
				}else
					return 2;
		}else
			return 3;
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
				NodeBook p = new NodeBook();
				p.setLivro(livro);
				p = arvore.TreeSearch(arvore, p);
				System.out.println(p);
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
				System.out.println("Digite o titulo do livro a ser emprestado:");
				String tituloE = teclado.nextLine();
				System.out.println("Digite o autor do livro a ser emprestado:");
				String autorE = teclado.nextLine();
				System.out.println("Digite o preco do livro a ser emprestado:");
				double precoE = teclado.nextDouble();
				Book livroE = new Book(tituloE, autorE, precoE);
				teclado.nextLine();
				System.out.println("Digite o nome do usuario:");
				String nomeE = teclado.nextLine();
				System.out.println("Digite a identidade do usuario:");
				int idE = teclado.nextInt();
				User usuarioE = new User(nomeE, idE);
				
				NodeBook nod = new NodeBook();
				NodeUser nodU = new NodeUser();
				nod.setLivro(livroE);
				nodU.setUsuario(usuarioE);
				
				System.out.println(nod);
				System.out.println(nodU);
				
				NodeBook nodoB = arvore.TreeSearch(arvore, nod);
				NodeUser nodoU = arvoreU.TreeSearch(arvoreU, nodU);
				
				
				
				/*if(apl.EmprestimoLivro(arvore, arvoreU, livroE, usuarioE) == 0)
					System.out.println("\nEmprestimo feito com sucesso\n");
				else if(apl.EmprestimoLivro(arvore, arvoreU, livroE, usuarioE) == 1)
					System.out.println("\nErro dados nulos\n");
				else if(apl.EmprestimoLivro(arvore, arvoreU, livroE, usuarioE) == 2)
					System.out.println("\nErro Usuario no limite ou Livro ja emprestado\n");
				teclado.nextLine();
*/
				break;

			case 4:
				System.out.println("Digite o titulo do livro a ser devolvido:");
				String tituloD = teclado.nextLine();
				System.out.println("Digite o autor do livro a ser devolvido:");
				String autorD = teclado.nextLine();
				System.out.println("Digite o preco do livro a ser devolvido:");
				double precoD = teclado.nextDouble();
				Book livroD = new Book(tituloD, autorD, precoD);
				teclado.nextLine();
				System.out.println("Digite o nome do usuario:");
				String nomeD = teclado.nextLine();
				System.out.println("Digite a identidade do usuario:");
				int idD = teclado.nextInt();
				User usuarioD = new User(nomeD, idD);
				if(apl.DevolucaoLivro(arvore, arvoreU, livroD, usuarioD) == 0)
					System.out.println("\nDevolucao feita com sucesso\n");
				else if(apl.DevolucaoLivro(arvore, arvoreU, livroD, usuarioD) == 1)
					System.out.println("\nErro dados nulos\n");
				else if(apl.DevolucaoLivro(arvore, arvoreU, livroD, usuarioD) == 2)
					System.out.println("\nErro dados de usuario e livro não equivalentes\n");
				else if(apl.DevolucaoLivro(arvore, arvoreU, livroD, usuarioD) == 3)
					System.out.println("\nErro dados não equivalentes\n");
				teclado.nextLine();

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
