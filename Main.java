import java.util.Scanner;


public class Main {
	
	/***************** CONSTANTES *****************/

	private static final String SHOW_CHAT = "VCP";
	private static final String PUBLISH_MSG = "PNM";
	private static final String PUBLISH_ENC = "PME";
	private static final String CORRECT_MSG = "CMA";
	private static final String CLOSE_CHAT = "ECP";
	private static final String SHOW_LOG = "MCA";
	private static final String HELP = "A";
	private static final String EXIT = "S";
	
	
	/********** MÉTODO MAIN **********/
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int factor;		//Factor de Translação
		
		String name1;	//Nome do utilizador 1
		String name2;	//Nome do utilizador 2
		String command;		//Comando introduzido pelo utilizador
		
		//Pede o nome do utilizadores, garantindo
		//que o do segundo não é igual ao do primeiro
		name1 = getUsername(1, in); 
		do{
			name2 = getUsername(2, in);
			if (name2.trim().equalsIgnoreCase(name1.trim()))
				System.out.println("Nome já em utilização. "
				+ "Por favor introduza um nome diferente.");
		}while(name2.trim().equalsIgnoreCase(name1.trim()));
		
		//Pede o factor de translação
		factor = getFactor(in);
		
		//Cria um novo objecto Chat
		Chat newChat = new Chat(name1, name2, factor);
		
		//Apresenta os comandos disponíveis
		processHelp();
		
		//Menu principal que interpreta comandos do utilizador
		do{
			command = processCommand(in);
			switch(command){
				case SHOW_CHAT: processShowChat(newChat); break;
				case PUBLISH_MSG: processPubMsg(newChat, in); break;
				case PUBLISH_ENC: processPubEnc(newChat, in); break;
				case CORRECT_MSG: processCorrectMsg(newChat, in); break;
				case CLOSE_CHAT: processCloseChat(newChat); break;
				case SHOW_LOG: processShowLog(newChat); break;
				case HELP: processHelp(); break;
				default: System.out.println("Opcao inexistente.");
			}
		}while(command != EXIT);
		
		System.out.println("Aplicacao terminada. Ate a proxima.");
		//Final do programa
		
	}
	
	
	/*** interpretador de comandos ***/

	private static String processCommand(Scanner in){
		System.out.print("> ");
		//ler o que foi introduzido passar a letra maiuscula e 
		//tirar espaços no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
	
	
	/*** APRESENTAR CONVERSA EM PROGRESSO ***/
	
	private static void processShowChat(Chat newChat){
		System.out.print(newChat.showChat());
	}
	
	
	/*** PUBLICAR MENSAGEM ***/
	
	private static void processPubMsg(Chat newChat, Scanner in){
		if (newChat.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		
		int user = getUserNumber(newChat, in);
		newChat.addMsg(user, getMsg(in));
		System.out.println("USER[" + user + "]MSG[" + newChat.getMsgNumber()+"]: Publicada");
	}
	
	
	/*** PUBLICAR MENSAGEM ENCRIPTADA ***/
	
	private static void processPubEnc(Chat newChat, Scanner in){
		if (newChat.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		
		int user = getUserNumber(newChat, in);
		newChat.addEncMsg(user, getMsg(in));
		System.out.println("USER[" + user + "]MSG[" + newChat.getMsgNumber()+"]: Publicada");
	}
	
	
	/*** CORRIGIR MENSAGEM ANTERIOR ***/
	
	private static void processCorrectMsg(Chat newChat, Scanner in){
		if(newChat.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else{
			int user = getUserNumber(newChat, in);
			if (newChat.canEditLastMessage(user)){
				newChat.editLastMessage(user, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+newChat.getLastMsg());
			}
			else{
				System.out.println("Utilizador " + user + " nao e autor da mensagem mais recente.");
			}
		}
	}
	
	
	/*** ENCERRAR CONVERSA EM PROGRESSO ***/
	
	private static void processCloseChat(Chat newChat){
		newChat.closeConversation();
		System.out.println("Conversa terminada.");
	}

	

	/*** APRESENTAR HISTORICO DE CONVERSAS ***/
	
	private static void processShowLog(Chat newChat) {
		System.out.print(newChat.showLog());
	}
	

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
	
	
	/*** PEDIR MENSAGEM ***/
	
	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	
	/*** PEDIR NUMERO DE UTILIZADOR ***/
	
	private static int getUserNumber(Chat newChat, Scanner in){
		int user;
		do{
			System.out.print("Utilizador: ");
			user = in.nextInt();
			in.nextLine();
			if (!newChat.validUserNumber(user))
				System.out.println("Utilizador desconhecido.");
		}while(!newChat.validUserNumber(user));
		return user;
	}

	
	/*** PEDIR NOME DE UTILIZADOR ***/
	
	private static String getUsername(int number, Scanner in){
		System.out.print("Nome do Utilizador "+number+": ");
		return in.nextLine();
	}
	
	
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
	
	
}
