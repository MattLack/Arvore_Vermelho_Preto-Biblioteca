package controll;

import java.util.Scanner;
import arbor.ArvoreBook;
import models.Book;
import nodes.NodeBook;

public class Aplication{
	
	NodeBook nod = new NodeBook();
	
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
					System.out.println("\nLivro já existe\n");
				teclado.nextLine();

				break;

			case 2:
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
