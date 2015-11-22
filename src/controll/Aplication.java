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
	
	public Aplication(){
		
	}
	
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
	
	public boolean RemoverLivro(ArvoreBook T, Book book){
		
	}
	
	public void menu(){
		
		System.out.println("1 - cadastrar livro");
		System.out.println("2 - cadastrar usuario");
		System.out.println("3 - emprestimo de livro");
		System.out.println("4 - devolucao de livro");
		System.out.println("5 - remocao usuario");
		System.out.println("6 - remocao livro");
		System.out.println("7 - ");
		System.out.println("0 - sair");
	}
	
	
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

				break;

			case 6:

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
