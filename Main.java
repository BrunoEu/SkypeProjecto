// IMPORTAR O SCANNER //
import java.util.Scanner;


public class Main {
	
	/***************** CONSTANTES *****************/

	private static final String EXIT = "S";
	private static final String HELP = "A";
	private static final String SHOW_LOG = "MCA";
	private static final String CLOSE_CHAT = "ECP";
	private static final String CORRECT_MSG = "CMA";
	private static final String PUBLISH_ENC = "PME";
	private static final String PUBLISH_MSG = "PNM";
	private static final String SHOW_CHAT = "VCP";

	/************************************************/
	
	
	
	
	/**********************************/
	
	
	/*** ITERADOR DE COMANDOS ***/

	private static String processCmd(Scanner in){
		System.out.print("> ");
		//ler o que foi introduzido passar a letra maiuscula e 
		//tirar espaços no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
	
	/****************************/
	
	
	/*** APRESENTAR CONVERSA EM PROGRESSO ***/
	
	private static void processShowChat(Chat conversa){
		if (conversa.showChat().isEmpty())
			System.out.println("Conversa vazia.");
		else
			System.out.print(conversa.showChat());
	}
	
	/****************************************/
	
	
	/*** PUBLICAR MENSAGEM ***/
	
	private static void processPubMsg(Chat conversa, Scanner in){
		if (conversa.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		
		int user = getUserNumber(conversa, in);
		conversa.addMsg(user, getMsg(in));
		System.out.println("USER[" + user + "]MSG[" + conversa.getMsgNumber()+"]: Publicada");
	}
	
	/************************/
	
	
	/*** PUBLICAR MENSAGEM ENCRIPTADA ***/
	
	private static void processPubEnc(Chat conversa, Scanner in){
		if (conversa.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		conversa.addEncMsg(getUserNumber(conversa, in), getMsg(in));
	}
	
	/************************************/
	
	
	/*** CORRIGIR MENSAGEM ANTERIOR ***/
	
	private static void processCorMsg(Chat conversa, Scanner in){
		if(conversa.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else{
			int user = getUserNumber(conversa, in);
			if (conversa.canEditLastMessage(user)){
				conversa.editLastMessage(user, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+conversa.getLastMsg());
			}
			else{
				System.out.println("Utilizador " + user + " nao pode editar.");
			}
		}
	}
	
	/***********************************/
	
	
	/*** ENCERRAR CONVERSA EM PROGRESSO ***/
	
	private static void processCloseChat(Chat conversa){
		conversa.closeConversation();
		System.out.println("Conversa terminada.");
	}
	
	/**************************************/
	

	/*** APRESENTAR HISTORICO DE CONVERSAS ***/
	
	private static void processShowLog(Chat conversa) {
		System.out.print(conversa.showLog());
	}
	
	/*****************************************/
	

	/*** APRESENTAR COMANDOS DISPONIVEIS ***/
	
	private static void processHelp() {
		System.out.println("\nVCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "ECP - Encerrar conversa em progresso\n"+
						   "MCA - Mostrar conversas anteriores\n"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}
	
	/***************************************/
	
	
	/*** PEDIR MENSAGEM ***/
	
	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	/**********************/
	
	
	/*** PEDIR NUMERO DE UTILIZADOR ***/
	
	private static int getUserNumber(Chat conversa, Scanner in){
		int user;
		do{
			System.out.print("Utilizador: ");
			user = in.nextInt();
			in.nextLine();
			if (!conversa.validUser(user))
				System.out.println("Utilizador desconhecido.");
		}while(!conversa.validUser(user));
		return user;
	}
	
	/**********************************/

	
	/*** PEDIR NOME DE UTILIZADOR ***/
	
	private static String getUsername(int number, Scanner in){
		System.out.print("Nome do Utilizador "+number+": ");
		return in.nextLine();
	}
	
	/********************************/
	
	
	/*** COMPARAR NOMES DOS UTILIZADORES ***/
	
	private static boolean compareName(String name1, String name2){
		if(name1.equalsIgnoreCase(name2)){
			System.out.println("Nome já em utilização. "
			+ "Por favor introduza um nome diferente.");
			return true;
		}
		else
			return false;
	}
	
	/***************************************/
	
	
	/************************************************/
	
	
	/*** PEDIR FACTOR DE ENCRIPTAÇÃO ***/
	
	private static int getFactor(Scanner in){
		int factor;
		
		do{
			System.out.print("Insira um factor de translacao: ");
			factor = in.nextInt();
			in.nextLine();
			if (!Chat.validFactor(factor))
				System.out.println("Factor invalido. [0, 26]");
		}while(!Chat.validFactor(factor));
		
		return factor;
	}

	/********** MÉTODO MAIN **********/
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int factor;		//Factor de Translação
		
		String name1;	//Nome do utilizador 1
		String name2;	//Nome do utilizador 2
		String cmd;		//Comando introduzido pelo utilizador
		
		//Pede o nome do utilizadores, garantindo
		//que o do segundo não é igual ao do primeiro
		name1 = getUsername(1, in); 
		do{
			name2 = getUsername(2, in);
		}while(compareName(name1, name2));
		
		//Pede o factor de translação
		factor = getFactor(in);
		
		//Cria um novo objecto Chat
		Chat novaConversa = new Chat(name1, name2, factor);
		
		//Apresenta os comandos disponíveis
		processHelp();
		
		//Menu principal que interpreta comandos do utilizador
		do{
			cmd = processCmd(in);
			switch(cmd){
				case SHOW_CHAT: processShowChat(novaConversa); break;
				case PUBLISH_MSG: processPubMsg(novaConversa, in); break;
				case PUBLISH_ENC: processPubEnc(novaConversa, in); break;
				case CORRECT_MSG: processCorMsg(novaConversa, in); break;
				case CLOSE_CHAT: processCloseChat(novaConversa); break;
				case SHOW_LOG: processShowLog(novaConversa); break;
				case HELP: processHelp(); break;
				default: System.out.println("Opcao inexistente.");
			}
		}while(cmd != EXIT);
		
		System.out.println("Aplicacao terminada. Ate a proxima.");
		
	}
	
	/************************************/
	
}
