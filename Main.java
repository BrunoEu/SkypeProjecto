
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
		
		String name1;//string que ira conter o nome do utilisador 1
		String name2;//string que ira conter o nome do utilixador 2
		String cmd;//string que ira conter o comando introduzido pelo utilizador
		
		name1 = getUsername(1, in);
		do{
			name2 = getUsername(2, in);
		}while(compareName(name1, name2));
			
		int factor = getFactor(in);
		
		Chat novaConversa = new Chat(name1, name2, factor);
		System.out.println("\nConversa\n");
		
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
				case EXIT: System.out.println("Aplicacao terminada. Ate a proxima.");break;
				default: System.out.println("Opcao inexistente.");
			}
		}while(cmd != EXIT);
		
	}

	private static String processCmd(Scanner in){
		System.out.print("> ");
		//ler o que foi introduzido passar a letra maiuscula e tirar espacos no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
	
	private static void processShowChat(Chat conversa){
		if (conversa.showChat().isEmpty())
			System.out.println("Conversa vazia.");
		else
			System.out.print(conversa.showChat());
	}
	
	private static void processPubMsg(Chat conversa, Scanner in){
		int user = getUserInt(in);
		conversa.addMsg(user , getMsg(in));
		System.out.println("USER[" + user + "]MSG[" + conversa.getMsgNumber()+"]: Publicada");
	}
	
	
	private static void processPubEnc(Chat conversa, Scanner in){
		conversa.addEncMsg(getUserInt(in), getMsg(in));
	}
	
	private static void processCorMsg(Chat conversa, Scanner in){
		if(conversa.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else{
			int user = getUserInt(in);
			if (conversa.canEditLastMessage(user)){
				conversa.editLastMessage(user, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+conversa.getLastMsg());
			}
			else{
				System.out.println("Utilizador " + user + " nao pode editar.");
			}
		}
	}
	
	private static void processShowLog(Chat conversa) {
		System.out.print(conversa.showLog());
	}
	
	private static void processCloseChat(Chat conversa){
		conversa.closeConversation();
		System.out.println("Conversa terminada.");
	}
	
	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	private static int getUserInt(Scanner in){
		int user;
		do{
			System.out.print("Utilizador: ");
			user = in.nextInt();
			in.nextLine();
			if (!Chat.validUser(user))
				System.out.println("Utilizador desconhecido.");
		}while(!Chat.validUser(user));
		return user;
	}
	
	private static void processHelp() {
		System.out.println("VCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "ECP - Encerrar converssa em progresso\n"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}
	
	private static String getUsername(int i, Scanner in){
		System.out.print("Nome do Utilizador "+i+": ");
		return in.nextLine();
	}
	
	private static boolean compareName(String name1, String name2){
		if(name1.equalsIgnoreCase(name2)){
			System.out.println("Nome já em utilização. "
			+ "Por favor introduza um nome diferente.");
			return true;
		}
		else
			return false;
	}
	
	private static int getFactor(Scanner in){
		int factor;
		
		do{
			System.out.print("Insira um factor de translaçao: ");
			factor = in.nextInt();
			in.nextLine();
			if (!Chat.validFactor(factor))
				System.out.println("Factor invalido. [0, 26]");
		}while(!Chat.validFactor(factor));
		
		return factor;
	}
	
}
