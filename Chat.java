
import java.util.Scanner;

public class Main {

	private static final String EXIT = "S";
	private static final String HELP = "A";
	private static final String SHOW_LOG = "MCA";
	private static final String CLOSE_CHAT = "ECP";
	private static final String CORRECT_MSG = "CMA";
	private static final String PUBLISH_ENC = "PME";
	private static final String PUBLISH_MSG = "PNM";
	private static final String SHOW_CHAT = "VCP";

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		String name1;
		String name2;
		
		System.out.print("Nome do Utilizador 1: ");
		name1 = in.nextLine();
		
		do{	//BRUNO: No enunciado eles dizem que se 
			//forem iguais so tens de pedir o 2º outra vez
			System.out.print("\nNome do Utilizador 2: ");
			name2 = in.nextLine();
			if(name1 == name2){
				System.out.println("\nNome já em utilização. "
				+ "Por favor introduza um nome diferente.");
			}
		}while (name1.equalsIgnoreCase(name2));
		//BRUNO: Na linha de cima temos que ponderar se pomos IgnoreCase ou nao
		
		int factor;
		
		do{
			System.out.print("\nInsira um factor de translaçao: ");
			factor = in.nextInt();
			in.nextLine();
			if (factor <0 || factor > 26)
				System.out.println("Factor invalido. [0, 26]");
		}while(factor < 0 || factor > 26);
		
		//BRUNO: Só cria a conversa quando tudo é aceitável
		Chat novaConversa = new Chat(name1, name2, factor);
		//BRUNO: Confirma a criação
		System.out.println("\nChat criado com sucesso.");
		
		String cmd;
		
		do{
			cmd = processCmd(in);
			switch(cmd){
				case SHOW_CHAT: processShowChat(novaConversa); break;
				case PUBLISH_MSG: processPubMsg(novaConversa, in); break;
				case PUBLISH_ENC: processPubEnc(); break;
				case CORRECT_MSG: processCorMsg(); break;
				case CLOSE_CHAT: processCloseChat(); break;
				case SHOW_LOG: processShowLog(); break;
				case HELP: processHelp(); break;
				case EXIT: break;
				default: System.out.println("\n\nComando introduzido invalido.");
			}
		}while(cmd != EXIT);
		System.out.println("Aplicacao terminada. Ate a proxima.");
	}
	
	public static String processCmd(Scanner in){
		System.out.print("\n> ");
		//ler o que foi introduzido passar a letra maiuscula e tirar espacos no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
	
	public static void processShowChat(Chat conversa){
		System.out.println(conversa.showChat());
	}
	
	public static void processPubMsg(Chat conversa, Scanner in){
		conversa.addMsg(getUser(in), getMsg(in));
	}
	
	
	public static void processPubEnc(Chat conversa, Scanner in){
		conversa.addEncMsg(getUser(in), getMsg(in));
	}
	
	public static String getMsg(Scanner in){
		System.out.print("\nMensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	private static int getUser(Scanner in){
		int user;
		do{
			System.out.print("Utilizador: ");
			user = in.nextInt();
			in.nextLine();
		}while(user != 1 && user != 2);
		return user;
	}
	
	public static void processHelp() {
		System.out.println("VCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "ECP - Encerrar converssa em progresso\n"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}
}
