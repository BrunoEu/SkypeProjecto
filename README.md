# SkypeProjecto

Mestrado Integrado em Engenharia Informatica
Introdução à Programação
Trabalho Prático 2015/2016
1 Introdução
Este documento descreve a segunda fase do trabalho prático da disciplina de Introdução à
Programação do 1º ano do Mestrado Integrado em Engenharia Informatica.
Reiterase
que o trabalho deve ser realizado em grupos de 2 alunos. Excepções têm que ser
explicitamente autorizadas pelo regente da cadeira.
Os alunos podem e devem tirar todas as dúvidas com os docentes da disciplina, bem como
conversar com os colegas e discutir soluções, sempre respeitando os princípios do Código de
Ética (disponível em http://di.fct.unl.pt/mei/codigoEticaDIv1.0MEI.pdf ).
2 Descrição do Problema
Pretendese
agora estender o emulador de c hat ou t alk , permitindo não limitar o número de
utilizadores. Nesta versão do projecto, podemse
criar tantos utilizadores quantos o utilizador
final (quem usa a aplicação) quiser. Os utilizadores são identificados por meio dum simples
número inteiro, que começa em um e é incrementado em uma unidade quando se cria mais um
utilizador. Além desse número, o sistema guarda o nome de cada utilizador (uma sequência de
letras) que deve também ser único. A criação dos utilizadores já não é feita no início, mas sim
através de um comando específico.
O sistema continua a guardar conversas entre apenas dois utilizadores. Cada conversa
(sempre só entre dois utilizadores) tem agora que ser explicitamente iniciada, através de um
comando (onde se identificam os utilizadores envolvidos pelo
seu i d inteiro e
o fator de
translação). Note que não se quer distinguir a conversa entre o utilizador 2 e 5 da entre o 5 e o
2 (i.e., a ordem não é relevante).
Todos os comandos anteriores (excepto Ajuda e Sair) passam a identificar explicitamente os
dois utilizadores envolvidos, através dos seus i ds . Pretendese
também validar os argumentos
fornecidos, permitindo ao utilizador da aplicação corrigir argumentos incorrectos ou terminar o
comando.
Nas novas funcionalidades, está a possibilidade de listar todos os utilizadores com quem dado
utilizador já conversou e a de listar todos os utilizadores do sistema.
Deve ainda ser possível gravar o estado do sistema para um ficheiro (o que acontecerá sempre
que se sair da aplicação) e carregar um estado previamente gravado para o sistema
(substituindo o estado corrente). Entendese
por estado o conjunto dos utilizadores e conversas
mantidas entre eles.
3 Comandos
Além dos comandos referidos na fase anterior, pretendese
que sejam implementados os
seguintes comandos adicionais.
CNU Criar
Novo Utilizador
INC Iniciar
Nova Conversa
LUC Listar
Utilizadores Contactados
LTU Listar
Todos os Utilizadores
GEA Gravar
Estado da Aplicação para ficheiro
CAF Carregar
Aplicação de Ficheiro
3.1 CNU:Criar Novo Utilizador
O comando tem como argumento o nome do utilizador a criar. Se o nome já existe, o sistema
responde:
Nome em utilização. Escolha um novo nome.
Se o nome não existe, é criado o utilizador e a aplicação retorna o seu id :
Utilizador nome com id id criado com sucesso.
3.2 INC: Iniciar Nova Conversa
> INC id1 id2 factor
Este comando inclui três argumentos: (1) o i d do utilizador que inicia a conversa; (2) o i d do
outro utilizador que participará na conversa e (3) o factor de translação a usar para encriptar
mensagens na conversa.
Se algum dos utilizadores não existe, voltase
a pedir o seu i d (até ser dado um válido ou um
caractér):
O utilizador id não existe. Dê por favor um identificador válido.
Se for então dado um carácter (ou o espaço em branco) em vez de um inteiro, o comando não
tem efeito e voltase
a apresentar o menú de comandos. Este é o comportamento de todos os
comandos com argumentos.
O factor de translação deve também ser validado, pois tem que ser um natural não nulo menor
que 27.
Quando são identificados dois utilizadores existentes e um factor válido, verificase
uma de
duas situações: ou não há conversa em progresso entre esses dois utilizadores, e o sistema
emite uma mensagem de sucesso, ou já há uma conversa em progresso entre esses dois
utilizadores, e o sistema emite uma mensagem de insucesso e termina.
3.3 LUC:Listar Utilizadores Contactados
O comando tem como argumento o id dum utilizador. Se o id não existe, o sistema responde:
Utilizador desconhecido. Escolha um novo id.
Se o utilizador introduzir um algarismo, o comando termina. Se o i d existe, a aplicação retorna
os nomes e ids dos utilizadores com quem falou o utilizador dado em argumento do comando:
Utilizadores contactados por nome (id) : nome1 id1, …, nomek idk .
3.4 LTU: Listar Todos os Utilizadores
O comando devolve a lista dos nomes dos utilizadores do sistema, Se não houver utilizadores,
o sistema emite uma mensagem a dizêlo
explicitamente.
4 A Entrega
Devem ser entregues todos os ficheiros .java, num único arquivo ZIP, e a listagem completa
desses ficheiros em papel. O arquivo ZIP deve ser entregue através do moodle. O arquivo
deverá ter um nome com o seguinte formato T urnoPrático_NAluno1_NAluno2 ou
TurnoPrático_NAluno1_TurnoPrático_NAluno2 .
Por exemplo dois alunos do turno teóricoprático
TP2, com os números 12345 e 13456 deverão
entregar no moodle um zip com o nome TP2_12345_13456. Se os alunos forem de turnos
diferentes, por exemplo do TP1 o 12345 e do TP2 o 13456, o ficheiro tem o nome
TP1_12345_TP2_13456, devendo os turnos e os números dos alunos figurar no nome do ZIP
por ordem crescente.
Data Limite de Entrega: O trabalho deverá ser entregue via moodle até às 0h do dia 14 de
Dezembro de 2015 (de domingo para segunda).
A entrega em papel, na secretaria do DI, deverá ser realizada durante o dia 14 de Dezembro de
2015 (segundafeira),
até às 16:30.

1º Projecto de IP


Mestrado Integrado em Engenharia Informatica
Introdução à Programação
Trabalho Prático 2015/2016


1 Introdução
  Este documento descreve a primeira fase do trabalho prático da disciplina de Introdução à
  Programação do 1º ano do Mestrado Integrado em Engenharia Informatica. Descreve ainda
  uma metodologia de trabalho que os alunos devem seguir para desenvolverem o referido
  projecto.
  O trabalho deve ser realizado em grupos de 2 alunos. Excepções têm que ser explicitamente
  autorizadas pelo regente da cadeira.
  Esta é uma oportunidade para desenvolver um projecto de maior dimensão, aprendendo com a
  prática. A experiência é fundamental, pois a programação é uma actividade que só
  verdadeiramente se treina com a prática continuada. Os alunos podem e devem tirar todas as
  dúvidas com os docentes da disciplina, bem como conversar com os colegas e discutir
  soluções, sempre respeitando os princípios do Código de Ética (disponível em
  http://di.fct.unl.pt/mei/codigoEticaDIv1.0MEI.pdf ).
  
  
2 Descrição do Problema
  O projecto visa o desenvolvimento de um emulador de c hat ou t alk entre dois utilizadores, ao
  estilo do S kype . Nesta versão do projecto, há apenas dois utilizadores. Os utilizadores são
  identificados por meio dum simples número inteiro 1
  e 2. Além desse número, o sistema
  guarda o nome de cada utilizador e deve ser capaz de guardar múltiplas c onversas entre dois
  utilizadores. Cada conversa consiste numa sequência de m ensagens . Cada mensagem ocupa
  uma única linha de texto. Todas as mensagens são terminadas com um n ew line no fim do seu
  texto. Assim, se diversas mensagens estiverem contidas numa única s tring (s = m 1 m 2 … m n ),
  se se sabe o ponto em que determinada mensagem m i começa, é possível detectar o seu fim
  detectando a primeira ocorrência dum n ew line após o início dessa mensagem m i . Cada
  mensagem é precedida dum p refixo ou etiqueta que inclui o número do utilizador que envia a
  mensagem e o número da mensagem (gerado por meio dum contador ver
  detalhes adiante).
  Em cada conversa, o número das mensagens iniciase
  em 1 e cada nova mensagem possui o
  número que se segue ao da mensagem anterior (i.e., se a mensagem mais recente tem
  número 7, a que se segue tem número 8). O prefixo é da forma U SER[n]>MSG[m]:, sendo
  [n] o número do utilizador e [ m] do número da mensagem. Nada impede que um dado
  utilizador envie mensagens consecutivas antes duma resposta do outro utilizador. Assim, uma
  conversa consiste numa sequência de mensagens entre dois utilizadores, cada uma iniciada
  com um prefixo no formato acima descrito e terminando com um n ew line . A Fig.1 apresenta
  um exemplo de listagem de uma conversa.
  USER[1]>MSG[1]: Ola.
  USER[2]>MSG[2]: Nao
  USER[1]>MSG[3]: Perdao?
  USER[2]>MSG[4]: Nao
  USER[1]>MSG[5]: Que queres dizer?
  USER[2]>MSG[6]: Nao
  USER[1]>MSG[7]: Es tu, Fonseca?
  USER[2]>MSG[8]: Nao
  USER[1]>MSG[9]: Quem esta ai?
  USER[2]>MSG[10]: Nao
  USER[1]>MSG[11]: hmmm... Comeco a desconfiar
  USER[2]>MSG[12]: Nao
  USER[1]>MSG[13]: Deixa ver... Es um ser humano?
  USER[2]>MSG[14]: Nao
  USER[1]>MSG[15]: Bem me parecia. Tens cerebro?
  USER[2]>MSG[16]: Nao
  USER[1]>MSG[17]: Logo vi. E um bot que so responde Nao
  USER[2]>MSG[18]: Nao
  Fig.1 – Listagem de uma conversa.
  Uma conversa pode ser encerrada , sendo que nesse caso não podem ser enviadas mais
  mensagens. Ao ser encerrada, é registada no sistema, i.e., guardada no l og ou historial de
  conversas, que o sistema mantém. Uma mensagem registada pode ser posteriormente listada
  integralmente, juntamente com todas as outras mensagens registadas no l og de conversas. Em
  nenhuma circunstância uma conversa (ou mesmo uma mensagem) registada é listada
  separadamente das outras da história global. Para simplificar, em cada momento só existe uma
  conversa em progresso (i.e., ongoing ).
  Este sistema permite, tal como o c hat das versões mais modernas do Skype, editar uma
  mensagem já enviada. Porém, um utilizador não pode editar mensagens do outro utilizador e
  apenas pode editar a mensagem enviada mais recentemente. Caso a mensagem mais recente
  não tenha sido enviada por si, não pode editar nenhuma.
  As mensagens duma conversa podem ser encriptadas. Para isso usase
  um factor de
  translação, que será explicado em mais detalhe na secção seguinte.
  No início da execução, o programa pede o nome de cada um dos dois utilizadores. Os nomes
  devem ser diferentes (se o segundo for igual ao primeiro, a aplicação volta a pedir o segundo
  nome). A seguir, pede o número inteiro relativo ao factor de translação, a ser usado no
  comando PME (para mais detalhes, ver comando PME). Este factor de translação deve ser um
  inteiro positivo e não exceder 26, o número de letras que se podem usar para compor uma
  mensagem. Se for introduzido um valor que não respeita a condição, a aplicação volta a pedir o
  factor de translação.
  Nome do utilizador 1: Felisberto Bota
  Nome do utilizador 2: Joana Angelica do Ceu
  Factor de translacao: 3
  
  
3 Comandos
  Nesta secção indicamse
  e explicamse
  os vários comandos que o sistema deve ser capaz de
  interpretar e executar. Nos exemplos apresentados, o texto nas caixas representa texto escrito
  pelo utilizador e a retroacção escrita pelo programa na consola, ao executar o comando com
  sucesso. Pode assumir que o utilizador não cometerá erros na introdução de argumentos nos
  comandos, para além dos descritos neste enunciado. Ou seja, apenas tem de tratar as
  situações de erro descritas aqui.
  Após a leitura dos nomes dos dois utilizadores e do factor de translação, o programa mostra as
  opções do menu (mensagem equivalente ao comando Ajuda ):
  VCP Ver
  a conversa em progresso
  PNM Publicar
  nova mensagem
  PME Publicar
  mensagem encriptada
  CMA Corrigir
  mensagem anterior
  ECP Encerrar
  conversa em progresso
  MCA Mostrar
  conversas anteriores
  A – Ajuda
  S Sair
  De seguida entra no modo de linha de comandos, que acompanha todo o restante tempo de
  execução. Cada vez que o interpretador aguarda pela introdução dum novo comando, deve
  escrever como p rompt a string ”> ” , colocando o cursor à frente. Na leitura de comandos, o
  interpretador não deve fazer distinção entre maiúsculas e minúsculas. Por exemplo, para o
  comando Sair , o interpretador deve aceitar como válidas as strings “SAIR”, “sair” e “Sair”.
  Em caso de comando inválido, i.e., um comando diferente dos acima apresentados, o
  programa envia a mensagem:
  Opcao inexistente .
  
  3.1 Sair Termina a execução do programa.
    > S
    O comando não necessita de argumentos. O texto a produzir deve ser o seguinte:
    Aplicacao terminada. Ate a proxima.
    Este comando tem sempre sucesso. Após a sua execução, o programa termina a sua
    execução.
    
  3.2 Ajuda Informa sobre os comandos disponíveis no programa
    > A
    Este comando tem como resultado a seguinte mensagem:
    VCP Ver
    a conversa em progresso
    PNM Publicar
    nova mensagem
    PME Publicar
    mensagem encriptada
    CMA Corrigir
    mensagem anterior
    ECP Encerrar
    conversa em progresso
    MCA Mostrar
    conversas anteriores
    A – Ajuda
    S Sair
    
  3.3 Visualizar conversa em progresso
    > VCP
    Permite ver toda a sequência de mensagens da conversa em progresso.
    Se a conversa ainda se encontra sem mensagens, o programa informa desse facto:
    Conversa vazia.
    
  3.4 Publicar nova mensagem
    > PNM
    nº utilizador
    Texto da mensagem
    Este comando inclui dois argumentos: (1) o número do utilizador e (2) uma linha de texto com a
    mensagem.
    Se o número de utilizador corresponde a um dos dois números legais, o programa emite uma
    mensagem confirmando a publicação, que menciona a etiqueta da mensagem publicada.
    Exemplo:
    USER[2]>MSG[2]: publicada.
    Se o número de utilizador não corresponde a um dos dois números legais, o programa emite
    uma mensagem de erro:
    Utilizador desconhecido
    Ao publicar uma nova mensagem, o programa gera um número para essa mensagem, que
    deve ser o número inteiro que se segue ao da mensagem anterior. Esse número faz parte dum
    prefixo a concatenar à mensagem introduzida pelo utilizador.
    Se já tinham sido publicadas 5 mensagens e o utilizador 1 publica a mensagem seguinte, o
    prefixo será: “ USER[1]>MSG[6]: “ (aspas não incluídas na etiqueta).
    A mensagem assim tratada é acrescentada à conversa – sempre com um n ew line
    acrescentado no fim.
    Pode acontecer a mensagem ser a primeira da presente conversa, seja porque é a primeira a
    ser publicada na presente sessão do programa, seja porque a conversa anterior foi encerrada
    (usando o comando ECP – ver adiante). Em qualquer destes dois casos, a publicação da nova
    mensagem dá origem a uma nova conversa. Nesse caso, o programa emite a mensagem
    “Nova conversa iniciada” anunciando o facto antes de mostrar a mensagem publicada.
    Nova conversa iniciada.
    USER1>MSG1: Teixeira, estas ai?
    
  3.5 Publicar mensagem encriptada
    > PME
    nº utilizador
    Texto da mensagem
    Este comando é semelhante a PNM (emitindo uma mensagem semelhante à desse comando,
    se for bem sucedido), com a diferença que a mensagem a publicar é encriptada segundo a
    técnica “Cifra de César”1. Para um factor de translação K , cada letra da mensagem é
    substituída pela letra que fica K posições à frente no alfabeto. Assim, com K = 3, a letra ‘A’ é
    substituída por D, a letra B é substituída pela letra E, e assim por diante. A este processo de
    substituição, chamamos t ranslação . Quando K = 0, as letras permanecem sem alterações. Para
    estes efeitos, há sempre distinção entre maiúsculas e minúsculas: uma letra minúscula é
    sempre substituída por outra minúscula, em função do coeficiente K de translação. O mesmo
    se passa com as maiúsculas. No caso das letras da parte final do alfabeto, o processo de
    cálculo da letra substituta “dá a volta” ao alfabeto. Assim, quando K = 3, a letra ‘y’ é substituída
    pela letra ‘a’ e a letra ‘z’ é substituída pela letra ‘b’.
    Este processo aplicase
    apenas a letras. Para simplificar, não se aplica a letras com caracteres
    especiais especificamente portugueses, nomeadamente vogais com acentos e ‘c’ com cedilha.
    O programa parte do princípio que esses caracteres não são usados. Por exemplo: se o factor
    de translação for 3 e a mensagem for “abcxyz”, a mensagem encriptada é “defabc”. Notese
    que o processo de encriptação não se aplica à etiqueta da mensagem.
    1 https://pt.wikipedia.org/wiki/Cifra_de_C%C3%A9sar
    
  3.6 Corrigir mensagem mais recente
    > CMA
    nº utilizador
    Novo texto da mensagem
    Este comando serve para corrigir a mensagem publicada mais recentemente da conversa em
    progresso. Na prática, permite substituir o texto duma mensagem já publicada por outro. Se a
    mensagem a substituir estava encriptada, a nova será também encriptada, i.e., o programa
    sabe a cada momento se a mensagem publicada mais recentemente foi encriptada ou não.
    Caso o utilizador não corresponda a um dos números válidos, o programa emite uma
    mensagem de erro:
    Utilizador desconhecido
    Caso o número de utilizador não seja o esperado (i.e., do autor da mensagem), o programa
    responde:
    Utilizador nao e autor da mensagem mais recente
    Caso o número de utilizador seja o esperado, o programa substitui o texto da mensagem pelo
    texto lido neste comando – sempre com um n ew line no fim. Emite também uma mensagem
    confirmando a correcção da mensagem, mostrando a etiqueta e o novo texto:
    Mensagem corrigida:
    USER2>MSG16: a formacao do governo e feita pela maioria mas
    podera ser feita pela minoria se a maioria deixar
    
  3.7 Encerrar a conversa em progresso
    > ECP
    Ao executar este comando, o programa guarda todo o texto da conversa, i.e., todas as
    mensagens, na ordem pelas quais foram publicadas. Deixa de ser possível publicar novas
    mensagens nesta conversa. Caso seja executado o comando VCP, o programa devolve:
    Conversa vazia .
    
  3.8 Mostrar conversas anteriores
    > MCA
    Este comando não requer argumentos. O seu efeito é mostrar os nomes dos dois utilizadores,
    seguidos de todas as conversas anteriores à conversa em progresso e sem incluir esta. Por
    exemplo:
    Utilizador 1: Felisberto Bota
    Utilizador 2: Joana Angelica do Ceu
    USER[1]>MSG[1]: Ola.
    USER[2]>MSG[2]: Nao
    USER[1]>MSG[3]: Perdao?
    USER[2]>MSG[4]: Nao
    USER[1]>MSG[5]: Que queres dizer?
    USER[2]>MSG[6]: Nao
    USER[1]>MSG[7]: Es tu, Fonseca?
    USER[2]>MSG[8]: Nao
    USER[1]>MSG[9]: Quem esta ai?
    USER[2]>MSG[10]: Nao
    USER[1]>MSG[11]: hmmm... Começo a desconfiar
    USER[2]>MSG[12]: Nao
    USER[1]>MSG[13]: Deixa ver... És um ser humano?
    USER[2]>MSG[14]: Nao
    USER[1]>MSG[15]: Bem me parecia. Tens cerebro?
    USER[2]>MSG[16]: Nao
    USER[1]>MSG[17]: Logo vi. E um bot que so responde Nao
    USER[2]>MSG[18]: Nao
    Caso não existam conversas anteriores, o programa emite a seguinte mensagem:
    Nao ha conversas anteriores.
    
4. Método de Desenvolvimento do Projeto
  Esta secção fornece algumas recomendações sobre uma metodologia de trabalho adequada
  para um projecto de desenvolvimento dum sistema de s oftware . É muito importante ser
  metódico em qualquer actividade com alguma duração, particularmente num projecto deste
  tipo. Acarreta uma sequência de actividades que devem ser feitas de forma disciplinada (ou
  seja, um processo de desenvolvimento), para se obter um ”produto” final de qualidade,
  minorando a ocorrência de defeitos (b ugs ) e facilitando a sua detecção quando ocorrem,
  facilitando a legibilidade do programa, e... ter uma boa nota.
  
  4.1 ETAPA 1 Compreensão
    e Esclarecimento do Enunciado e dos Objectivos do
    Projecto
    Leia bem o enunciado, anotando todas as questões que lhe ocorram de
    preferência por
    escrito e em papel. Mantenha um documento escrito com as dúvidas sobre este trabalho e com
    as respostas que for obtendo. É essencial saber, em cada momento, que dúvidas tem, e quais
    as respostas às dúvidas que teve, mas já esclareceu.
    Como esclarecer dúvidas? Com os docentes da disciplina. Aproveite os horários de dúvidas e
    as aulas práticas. Nesta fase eliminará as maiores dúvidas sobre o enunciado e sobre o que se
    pretende. Claro, algumas dúvidas podem persistir e podem surgir novas questões durante as
    fases seguintes. Por isso, preparese
    para manter o seu documento de dúvidas até ao fim do
    prazo de entrega.

  4.2 ETAPA 2 Construção
    do Interpretador de Comandos
    Nesta etapa deve focarse
    em construir o interpretador de comandos que servirá para executar
    o seu programa. Deve replicar da forma mais exata que conseguir o enunciado e os comandos
    nele descritos. Desta forma, terá um programa com todas as funcionalidades representadas,
    embora estas ainda não façam nenhuma acção, além de lidarem com o i nput /o utput
    prédefinido.
    O propósito desta fase é que consiga a partir dela melhor realizar a fase seguinte. Ou seja,
    deve basearse nas ações que o programa disponibiliza para na fase seguinte a estruturar.
    Assim, nesta fase terá necessariamente de criar uma classe especial Main que
    contém o programa principal (método m ain ), e diversos métodos auxiliares (por exemplo, gestão do
    input /o utput ), o que permite ao programa interagir com o utilizador. Pode montar um método
    vazio a representar cada comando especificado neste enunciado. Isso é algo que fica feito de
    uma vez por todas e ajudao
    a ter uma primeira visão global do programa. Depois, poderá
    preencher esses métodos à medida que vai desenvolvendo as diversas funcionalidades. O
    objectivo desta fase é um obter um programa que aceita os comandos e os dados associados,
    apesar de não fazer nada (excepto sair, com o comando S air ). Se adoptar esta abordagem, vai
    poder começar a testar as várias funcionalidades usando a linha de comando, à medida que as
    vai implementando.
    
  4.3 ETAPA 3 Análise
    da Estrutura Geral do Programa
    Nesta fase, terá como objectivo definir a estrutura global do seu programa. Como bem sabe,
    um programa em Java consiste num conjunto de classes Java, cujos objectos representam as
    várias entidades do domínio do problema, e talvez uma ou outra classe do domínio da solução
    ou implementação.
    No caso que temos em mão, além da classe M ain já criada na fase anterior, vai existir uma
    classe do domínio para cada entidade necessária para construir a aplicação.
    Os objectos dessas classes representarão os conceitos em actividade durante a execução do
    programa. Manterão a informação relativa a cada instância desse conceito, e fornecerão
    grupos de operações apropriadas. Defina nesta fase quais são as várias classes de que
    necessita e as suas interfaces no contexto do programa em causa. Certifiquese
    de que as
    operações que identificou nas interfaces pertencem mesmo à classe em que as colocou.
    Identifique também as variáveis e constantes que achar necessárias para representar o estado
    de cada objecto de cada classe, assim como o seu tipo.
    Não se esqueça de pensar bem como representar a informação dentro de cada objecto. Tente
    explorar o facto de se poderem usar objectos de uma classe como valores de variáveis de
    outros objectos. Note que nesta fase não terá que programar, mas apenas que pensar e definir,
    para cada classe, a interface (conjunto de operações), assim como os constructores, as
    variáveis de instância e constantes que achar necessárias na classe.
    Quando tiver dúvidas sobre onde colocar uma determinada operação (em que classe), consulte
    os docentes da disciplina. Para que tudo fique bem documentado, para cada variável indique o
    seu tipo e explique a finalidade. Para cada método indique o tipo do seu resultado e o tipo dos
    seus parâmetros (se existirem). Explique ainda a sua finalidade (para que serve) de forma clara
    e intuitiva. Indique ainda que métodos são modificadores e que métodos são de consulta.
    O resultado desta fase é um ''esqueleto'' do programa em Java, em que as classes só têm
    variáveis, métodos (cujo corpo é vazio), e comentários explicativos. É um programa que
    provavelmente não passa no compilador (que dá erros, pois faltam coisas), mas que é o
    esqueleto da sua solução. E o esqueleto é o mais importante. Se for sólido e bem pensado, o
    programa vaise
    manter de pé quando concluído, caso contrário, vai sair uma ''alforreca''.
    No final desta fase, mostre o resultado da sua planificação aos docentes da disciplina. Estes
    poderão dar alguns conselhos, ou transmitirlhe
    confiança sobre a adequação da sua proposta.
    Apenas depois desta fase estar concluída é que deve começar a programar.
  4.4 ETAPA 4 Construção
    do programa
    Nesta fase, vai a programar as classes do domínio da sua aplicação. Desenvolva cada classe
    separadamente e testea
    bem (por exemplo, no BLUEJ) antes de a integrar na aplicação.
    Comece por fazer um levantamento das constantes úteis, dos métodos da interface
    (observadores e modificadores(/observadores) e das variáveis de instância. Desenvolva depois
    o algoritmo e finalmente o código de cada método.
  4.5 Recomendações Gerais
    É muito importante tentar ter sempre um programa funcional, que compile sem erros e faça
    alguma coisa, mesmo que ainda não tudo o que é requerido. Assim terá mais segurança e uma
    base sólida para partir para a fase seguinte. É a regra da v ersão estável , que deve ser seguida
    por qualquer desenvolvedor de s oftware . Tenha sempre uma versão estável do seu sistema.
    Nunca comece a trabalhar em novas fases, sem que as anteriores estejam robustas, bem
    testadas. Programe com o seu parceiro de grupo, sentados à volta do mesmo computador e
    debatendo opções e aspectos da solução. Não tente dividir o trabalho em partes muito
    separadas: depois as coisas não ''colam'', e ''martelar'' peças para encaixarem ''à força'' não
    costuma ser boa ideia. No entanto, não deve ser sempre o mesmo aluno a programar; devem
    revezarse
    para que ambos possam aprender.
    Para facilitar o desenvolvimento do seu programa, e eliminar erros desnecessários, deverá ter
    em atenção os seguintes aspectos:
    ● Não se esqueça de mostrar o resultado da ETAPA 1 a um docente ou monitor. Não
    comece a programar antes de terminar a ETAPA 1.
    ● Teste as suas classes método a método. Se for preciso crie pequenos programas
    principais para testar (os métodos de) cada classe.
    ● Cada método deve realizar uma (e só uma) tarefa bem definida. Defina métodos
    auxiliares (privados) sempre que tal clarificar a divisão de tarefas e legibilidade do seu
    programa.
    ● Use bons nomes para os identificadores. Siga as regras de estilo adoptadas nos slides
    da disciplina.
    ● Quando definir a classe M ain e o respectivo método m ain , escreva um programa que
    execute toda a interacção com o utilizador, mas que não realize nenhuma tarefa na
    realidade, tal como já foi sugerido acima na ETAPA 1. Vá adicionando funcionalidades à
    classe M ain conforme for desenvolvendo as várias funcionalidades do sistema. A classe
    Main deverá ser organizada em vários métodos estáticos e privados, que implementam
    cada comando do sistema, podendo também haver métodos auxiliares. O estilo da
    programação é MESMO muito importante.
    ● A classe M ain é a única que pode utilizar operações de i nput /o utput de ou para o ecrã
    (p rintln , etc.). Nenhum método de uma classe auxiliar pode conter operações de
    input / output .
    ● Todas as variáveis de estado dos objectos (variáveis de instância) TÊM que ser
    privadas (declaradas private ).
    ● Não se esqueça da regra da ''versão estável''. No fim de cada fase de desenvolvimento
    ou alteração ao programa, ele deve compilar e funcionar como se espera nessa fase.
    Nunca esteja muito tempo sem que o seu programa compile sem erros, ou não faça o
    que era previsto fazer nessa fase! As versões estáveis são uma rede de segurança.
    Nunca apague o código de cada versão estável, para poder voltar a ela, se se perder no
    desenvolvimento ou se começar a ficar com um programa muito confuso.
    ● Se não percebe como o SEU programa funciona, dificilmente os docentes o vão
    perceber quando o forem avaliar! Não escreva código que não entende como funciona.
    Em caso de dúvida, estude, consulte livros, os docentes, os colegas. MUITO
    IMPORTANTE: comente sempre o seu código, incluindo précondições.
    ● Quando, ao compilar, o seu programa der um erro, tente perceber bem porque isso
    aconteceu, para não repetir o mesmo erro muitas vezes. Leia com atenção as
    mensagens que lhe são mostradas.
    
5 A Entrega
  Devem ser entregues todos os ficheiros .java, num único arquivo ZIP, e a listagem completa
  desses ficheiros em papel. O arquivo ZIP deve ser entregue através do moodle. O arquivo
  deverá ter um nome com o seguinte formato TurnoPrático\_NAluno1\_NAluno2 .
  Por exemplo dois alunos do turno teóricoprático
  TP2, com os números 12345 e 13456 deverão
  entregar no moodle um zip com o nome TP2\_12345\_13456.
  A listagem em papel, devidamente comentada, será entregue na secretaria do DI, bem
  identificada. Essa indicação deve incluir, além dos números e nomes dos alunos autores, o
  turno prático a que pertencem e o nome do docente das aulas teoricopráticas.
  O trabalho é de
  grupo e está dimensionado para ser realizado por grupos de 2 alunos, não sendo admitidos
  grupos com mais que 2 alunos. Só serão aceites trabalhos individuais com autorização prévia
  do docente. Serão realizadas discussões com TODOS os grupos de alunos. A discussão é um
  elemento fundamental na avaliação do trabalho prático.
  Embora a entrega do projeto só esteja completa quando ambas as partes forem entregues
  (versão digital e versão em papel), a versão que servirá de base à avaliação, será a que for
  entregue no moodle.
  Data Limite de Entrega: O trabalho deverá ser entregue via moodle até às 0h do dia 16 de
  Novembro de 2015 (de domingo para segunda).
  A entrega em papel, na secretaria do DI, deverá ser realizada durante o dia 16 de Novembro de
  2015 (segundafeira).
