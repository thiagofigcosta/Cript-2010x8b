package test;

import cript.Operations;

public class AssistedTesting {
    
    public static String TestData="Em linguística, a noção de texto é ampla e ainda aberta a uma definição mais precisa. Grosso modo, pode ser entendido como manifestação linguística das ideias de um autor, que serão interpretadas pelo leitor de acordo com seus conhecimentos linguísticos e culturais. Seu tamanho é variável.\n" +
"\n" +
"Conjunto de palavras e frases articuladas, escritas sobre qualquer suporte[1].\n" +
"\n" +
"Obra escrita considerada na sua redação original e autêntica (por oposição a sumário, tradução, notas, comentários, etc.)[2].\n" +
"\n" +
"\"Um texto é uma ocorrência linguística, escrita ou falada de qualquer extensão, dotada de unidade sociocomunicativa, semântica e formal. É uma unidade de linguagem em uso.\"[3]\n" +
"\n" +
"O interesse pelo texto como objeto de estudo gerou vários trabalhos importantes de teóricos da Linguística Textual, que percorreram fases diversas cujas características principais eram transpor os limites da frase descontextualizada da gramática tradicional e ainda incluir os relevantes papéis do autor e do leitor na construção de textos.\n" +
"\n" +
"Um texto pode ser escrito ou oral e, em sentido lato, pode ser também não verbal.\n" +
"\n" +
"Texto crítico é uma produção textual que parte de um processo reflexivo e analítico gerando um conteúdo com crítica construtiva e bem fundamentada.\n" +
"\n" +
"Em artes gráficas, o texto é a parte verbal, linguística, por oposição às ilustrações.\n" +
"\n" +
"Todo texto tem que ter alguns aspectos formais, ou seja, tem que ter estrutura, elementos que estabelecem relação entre si. Dentro dos aspectos formais temos a coesão e a coerência, que dão sentido e forma ao texto. \"A coesão textual é a relação, a ligação, a conexão entre as palavras, expressões ou frases do texto[4]. A coerência está relacionada com a compreensão, a interpretação do que se diz ou escreve. Um texto precisa ter sentido, isto é, precisa ter coerência. Embora a coesão não seja condição suficiente para que enunciados se constituam em textos, são os elementos coesivos que lhes dão maior legibilidade e evidenciam as relações entre seus diversos componentes, a coerência depende da coesão.";

public static void main(String[] args) throws Exception { 
    
    System.out.println("===Assisted Corruption Testing===");
        System.out.println("=SubHash Routines:");
        System.out.print("Add(1,5):"+Operations.add(TestData, 1, 5));
        if(TestData.equals(Operations.add(Operations.add(TestData, 1, 5), 1, -5)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Add(3,-6):"+Operations.add(TestData, 3, -6));
        if(TestData.equals(Operations.add(Operations.add(TestData, 3, -6), 3, 6)))System.out.println("...OK");
                                                                      else System.out.println("...fail");  
        System.out.print("Add(5,10):"+Operations.add(TestData, 5, 10));
        if(TestData.equals(Operations.add(Operations.add(TestData, 5, 10), 5, -10)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("AddB(15,69):"+Operations.add(TestData, 15, 69));
        if(TestData.equals(Operations.add(Operations.add(TestData, 15, 69), 15, -69)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Invert():"+Operations.invert(TestData)); 
        if(TestData.equals(Operations.invert(Operations.invert(TestData))))System.out.println("...OK");
                                                               else System.out.println("...fail");
        System.out.print("Offset(bits,10):"+Operations.offset(TestData, true, 10));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, true, 10), true, -10)))System.out.println("...OK");
                                                                                   else System.out.println("...fail");
        System.out.print("Offset(bytes,-33):"+Operations.offset(TestData, false, -33));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, false, -33), false, 33)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Offset(bits,50):"+Operations.offset(TestData, true, 50));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, true, 50), true, -50)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Offset(bytes,50):"+Operations.offset(TestData, false, 50));
        if(TestData.equals(Operations.offset(Operations.offset(TestData, false, 50), false, -50)))System.out.println("...OK");
                                                                                     else System.out.println("...fail");
        System.out.print("Tables(3):"+Operations.switchTable(TestData, false, 3));
        if(TestData.equals(Operations.switchTable(Operations.switchTable(TestData, false, 3), true, 3)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Tables(7):"+Operations.switchTable(TestData, true, 7));
        if(TestData.equals(Operations.switchTable(Operations.switchTable(TestData, true, 7), false, 7)))System.out.println("...OK");
                                                                                            else System.out.println("...fail");
        System.out.print("Mult(1,7):"+Operations.mult(TestData, 1, 7));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 1, 7), 1, 7)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(2,15):"+Operations.mult(TestData, 2, 15));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 2, 15), 2, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Mult(3,15):"+Operations.mult(TestData, 3, 15));
        if(TestData.equals(Operations.div(Operations.mult(TestData, 3, 15), 3, 15)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(1,7):"+Operations.pow(TestData, 1, 7));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 1, 7), 1, 7)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(1,2):"+Operations.pow(TestData, 5, 2));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 5, 2), 5, 2)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
        System.out.print("Pow(2,2):"+Operations.pow(TestData, 2, 2));
        if(TestData.equals(Operations.nthrt(Operations.pow(TestData, 2, 2), 2, 2)))System.out.println("...OK");
                                                                      else System.out.println("...fail");
    }
}
