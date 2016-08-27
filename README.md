# Cript-2010x8b
```
#####################
#*  * **** *  *  ** #
#** * *  * ** * *  *#
#* ** **** * ** *  *#
#*  * *  * *  *  ** #
#####################
```
DevelopedBy: nanoTech
email: nanotechbr.corp@gmail.com
youtube: https://www.youtube.com/user/LegiaoGamerBR
-------------------------------------------------------
TODO
-------------------------------------------------------
*big nCript=data corrupted, unstable cript. if solved CHECKHASHFAIL and CHECKDATALOSS can be false to increase speed(priority)
*increase methods speed(add,mult)
*issue if large strings/files

====
ENUS
====
-------------------------------------------------------
ABOUT
-------------------------------------------------------
Program created to protect files, a hash key with 18bytes which is used each nCript, to protect the files, the same key has diferent strings and the same key and data can generate diferent results, and the process to cript/decript depends on the hash.
The MIT License (MIT) read LICENSE.txt;	

-------------------------------------------------------
WARNING
-------------------------------------------------------
Still in development ALWAYS backup your data
Hash key files do not store nCript(just save it in name but you can rename it, the filename is not considered when you open it)
report bugs: nanotechbr.corp@gmail.com

-------------------------------------------------------
HOW TO RUN
-------------------------------------------------------
files in /bin
*Windows/Linux/Mac: run in terminal "java -jar Cript-2010x8b.jar"
or just open "Cript-2010x8b.jar" or run "Cript2010x8b-WINDOWS.CMD"
(you can add java args to increase speed and memory)

-------------------------------------------------------
HOW TO USE
-------------------------------------------------------
Open the program
Select the file you want to cript/decript by pressing "Open" in file panel
Select in the mode panel cript/decript and type nCript(bigger nCript=security,slowness,might fail and currupted data(still in development))
Let secure checkbox checked(slow but less chance of corrupt)
click Run.(The OK checkbox tells you if is still running) (you can stop the process if you wanna to) 
if you cripted your file decript it to test.

extra options:
uncheck "gen random check" to use a hash key from a file, then you need to hit "Open" in hash panel and select your file(cript/decript)
uncheck "hash in file" before cript to save hash key in a diferent file(more secure)


====
PTBR
====
-------------------------------------------------------
Sobre
-------------------------------------------------------
Programa criado para proteger arquivos, um hash key com 18bytes é usado a cada nCript, para proteger os arquivos, a mesma chave pode ter diferentes strings e uma mesma chave e os mesmos dados podem gerar resultados diferentes, o processo depende da chave.
The MIT License (MIT) read LICENSE.txt;	

-------------------------------------------------------
ATENÇÃO
-------------------------------------------------------
Ainda em desenvolvimento sempre backup seus dados
Os arquivos de chave hash não armazenam nCript (basta salvá-lo no nome, mas você pode renomeá-lo, o nome do arquivo não é considerado quando você abri-lo)
reporte erros: nanotechbr.corp@gmail.com

-------------------------------------------------------
Como Abrir
-------------------------------------------------------
arquivos em /bin
* Windows / Linux / Mac: executar no terminal "java -jar Cript-2010x8b.jar"
Ou simplesmente abrir "Cript-2010x8b.jar" ou executar "Cript2010x8b-WINDOWS.CMD"
(Você pode adicionar java args para aumentar a velocidade e memória)

-------------------------------------------------------
COMO USAR
-------------------------------------------------------
Abrir o programa
Selecione o arquivo que você deseja cript / decript pressionando "Abrir" no painel de arquivos
Selecione no painel de modo cript / decript e digite nCript (maior nCript = segurança, lentidão, falha e dados em cascata (ainda em desenvolvimento))
Deixe a caixa de seleção segura marcada (lenta mas menos chance de ser corrompida)
Clique em Executar. (a caixa OK lhe diz se ainda esá rodando)(Você pode parar o processo se você quiser)
Se você cripted seu arquivo decript-lo para testar.

Opções extras:
Desmarque "gen aleatório check" para usar uma chave de hash de um arquivo, então você precisa acertar "Abrir" no painel de hash e selecione o arquivo (cript / decript)
Desmarque "hash in file" antes cript para salvar hash chave em um arquivo diferente (mais seguro)

-------------------------------------------------------
NAME
-------------------------------------------------------
2010(binary)=18(decimal)
x8=18(decimal)
b=bytes
hash has 18 bytes
soo: Cript2010x8b=Cript1818bytes

-------------------------------------------------------
IDEIA
-------------------------------------------------------
Entrada(TERMINAL):
    Criptografar(n=numero de vezes criptografado)
    ---------------------------------------------------------------------------
    nCHASHi  - criptografa usando o hash dado e inclui o hash no arquivo/string
    nCHASH   - criptografa usando o hash dado e retorna o hash
    nC0i     - criptografa gerando um hash e inclui o hash no arquivo/string
    nC0      - criptografa gerando um hash e retorna o hash
    
    Descriptografar(n=numero de vezes criptografado)
    ---------------------------------------------------------------------------
    nDHASH   - descriptografa usando o hash dado
    nDi      - descriptografa usando o hash incluido no arquivo/string
    
HASH:
    formato de bytes;
    ---------------------------------------referente a criptografia do proprio hash
    0)no caso de hash no arquivo o byte 0 contem a função que diz em quais posições de byte do arquivo está o hash
a função tem o formato 2º(3b)^y+3º(4b), onde x vai de 0 a n e y=1b+1.
    1)os primeiros 4b contem a versão do programa e os 4 outros contem o tamanho do conjunto de bytes (1-8)
    2)os primeiros 4b contem o valor a ser somado e os outros 4b contem o valor a ser subtraido do hash a partir do byte 5 do hash
    3)os ultimos 4b+1 representam o numero de casas a ser deslocadas e os primeiros 4b se for par serão deslocados os bytes, se forem impar serão deslocados os bits se for maior que
8 será para a direita, se for menor será para a esquerda e se for 8 não havera deslocamento do hash a partir do byte 5 do hash.
    4)o primeiro 1b reprenta qual switch case será e os seguintes 3b representam o valor switch case da tabela de equivalencia e os ultimos 4b se forem par haverá inversão dos bits 
, se forem impar não havera a partir do byte 5 do hash
    ---------------------------------------referente a criptografia do arquivo/string
    5-I)os primeiros 4b se for impar vai inverter os bits se for par não nao inverter, o 1b seguinte representa qual tabela de equivalencia será usada e os 3b seguintes representam
o valor do switch case
    6-II)representa a ordenação dos bytes no conjunto de byte de 4b em 4b, ou seja, o 6º byte representa o primeiro e segundo byte do conjunto, caso o tamanho do conjunto seja
4bytes os 4b não usados serão numeros random.
    7-II)representa o terceiro e quarto byte do conjunto
    8-II)representa o quinto e sexto byte do conjunto
    9-II)representa o setimo e oitavo byte do conjunto
    10-II)os primeiros 4b são random, o 1b seguinte diz se os seguintes representam "usar" ou "não usar"e os 3b ultimos representam de quantos em quantos conjuntos "usar" a
ordenação acima (os que não usarem terão ordem normal)
    11-III)o primero 1b reprenta o sinal(0=+, 1=-) e os outros 7b representam o valor a ser somado em cada conjunto, se os 8b forem 10000000 será feita uma soma com o tamanho do
arquivo e se for 00000000 sera feita uma subtração com o tamanho do arquivo
    12-IV)3 primeiros b representam a potencia do conjunto e os 5b seguintes representam o produto do conjunto
    13-V)o primeiro 1b represeta se é byte ou bit, o segundo 1b se é para a direita ou esquerda e os outros 6b falam quantas casas serão deslocadas
    14-VI)o primeiro 1b represeta se é byte ou bit, o segundo 1b se é para a direita ou esquerda e os outros 6b falam quantas casas serão deslocadas
    15-VII)função no formato 2º(3b)^y+3º(4b), onde x vai de 0 a n e y=1b+1, a função diz a posição onde serão incluidos bytes random
    16)de 3b em 3b determina a ordem que os passos (I-VII) serão feitos.
    17)de 3b em 3b determina a ordem que os passos (I-VII) serão feitos.
    18)de 3b em 3b determina a ordem que os passos (I-VII) serão feitos.
