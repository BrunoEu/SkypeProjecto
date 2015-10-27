package randoms;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		Chat novaConversa = new Chat(in);
		
		String cmd ;
		
		boolean leave = false;
		
		//loop principal do main
		while(!leave)
		{
			//ler comando
			cmd = processCmd(in);
			
			switch (cmd){
			}
			
		}
	}
	
	public static String processCmd(Scanner in)
	{
		System.out.print("> ");
		//ler o que foi introduzido passar a letra maiuscula e tirar espacos no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
}
