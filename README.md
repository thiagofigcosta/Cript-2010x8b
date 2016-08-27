# Cript-2010x8b

(implementar argc e argv)
Entrada:
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
