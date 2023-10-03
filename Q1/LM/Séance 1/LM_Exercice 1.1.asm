%INCLUDE    "io.inc"        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es

SECTION     .data           ; section pour d�clarer des donn�es initialis�es

SECTION     .text           ; section pour �crire du code en langage assembleur
                            ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"
    
CMAIN:                  
            mov     ebp,esp;

            mov     eax,27
            mov     ebx,200
            mov     ecx,10
            mov     edx,1048576

            
            
            
            xor     eax,eax
            ret