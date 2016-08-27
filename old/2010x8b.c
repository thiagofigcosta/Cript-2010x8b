#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <iostream>

#define cls "clear"
#define version 0001
using namespace std;

char path[50];

void decripthash(char *hash){
    
    
}

void cripthash(char *hash){
    
    
}

char* genhash(){
    
    
}

char* readhash(){
    
    
}


void decript(int n,char *hash){
    
    
}

void cript(int n,char *hash,int includeHash){
    
    
}


int main(){
    system(cls);
    char command[20];
    int nCript=1;
    int includeHash=0;
    char nCriptBuffer[20];
    char hash[20];
    char firstHash[20];
    char option;
    printf("Comando:\n");
    scanf("%s",command);
    printf("Path:\n");
    scanf("%s",path);
    for(int i=0;i<strlen(command);i++){
        if(tolower(command[i])=='c'){
            for(int j=0;j<i;j++){
                nCriptBuffer[j]=command[j];
                nCriptBuffer[j+1]='\0';
            }
            nCript=atoi(nCript);
            if(command[strlen(command)]=="i"){
                includeHash=1;
                int k=0;
                for(int j=i+1;j<strlen(command)-1;j++){
                    hash[k]=command[j];
                    hash[k+1]='\0';
                    k++;
                }
            }else{
                int k=0;
                for(int j=i+1;j<strlen(command);j++){
                    hash[k]=command[j];
                    hash[k+1]='\0';
                    k++;
                }
            }
            option='c';
            break;
        }else if(tolower(command[i])=='d'){
            for(int j=0;j<i;j++){
                nCriptBuffer[j]=command[j];
                nCriptBuffer[j+1]='\0';
            }
            nCript=atoi(nCript);
            int k=0;
            for(int j=i+1;j<strlen(command)-1;j++){
                hash[k]=command[j];
                hash[k+1]='\0';
                k++;
            }
            option='d';
            break;
        }
    }
    if(option=='c'){
        int hashValue=atoi(hash);
        if(strlen(hash)<=1||hashValue==0){
            hash=genhash();
            decripthash(hash);
        }else{
            decripthash(hash);
        }
        firstHash=hash;
        cript(nCript,hash,includeHash);
    }else if(option=='d'){
        int hashValue=atoi(hash);
        if(strlen(hash)<=1||hashValue==0){
            hash=readhash();
            decripthash(hash);
        }else{
            decripthash(hash);
        }
        firstHash=hash;
        decript(nCript,hash);
    }else{
        printf("codigo de entrada errado\n")
        printf("    Criptografar(n=numero de vezes criptografado)\n    ---------------------------------------------------------------------------\n    nC+HASH+i - criptografa\nusando o hash dado e inclui o hash no arquivo/string\n    nC+HASH   - criptografa usando o hash dado e retorna o hash\n    nC+0+i    - criptografa gerando um hash e inclui o hash no arquivo/string\n    nC+0      - criptografa gerando um hash e retorna o hash\n    Descriptografar(n=numero de vezes criptografado)\n---------------------------------------------------------------------------\n    nC+HASH   - descriptografa usando o hash dado\n    nC+0      - descriptografa usando o hash incluido no arquivo/string\n\n");
    }
    
}
