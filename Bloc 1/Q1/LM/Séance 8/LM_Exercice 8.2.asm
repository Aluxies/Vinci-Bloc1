%INCLUDE    "io.inc"        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es

SECTION     .data           ; section pour d�clarer des donn�es initialis�es
            t dw  154, 10, 56000, 8, 4568, 65445, 1500, 2, 50252, 25
SECTION     .text           ; section pour �crire du code en langage assembleur
                            ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"
    
CMAIN:                  
            mov     ebp,esp ; pour debugging fonctionnel dans l'IDE SASM               

; le libell� CMAIN: est le point d'entr�e de notre programme
; �crivez votre instructions en langage assembleur NASM 32 bits
; � partir de la ligne ci-dessous
            
            mov ebx, 0    ; On vide le registre ebx
            
            mov eax, t    ; On met l'adresse du d�but du tableau dans EAX
            mov bx, [t]   ; BX va contenir le plus grand nombre et on l'initialise au 1e �l�ment du tableau t
            
            mov cl, 1     ; CL va contenir le nombre d'�l�ments d�j� tra�t�s et va �tre initialis� avec le 1e �l�ment du tableau
            
            add eax, 2    ; On incr�mente de 2 l'adresse pour aller sur l'�l�ment suivant du tableau (2 car 2 octets par �l�ment)
            
boucle:

            cmp cl, 10    ; On compare le nombre d'�l�ments du tableau d�j� tra�t�s � 10
            je fin        ; Si le nombre d'�l�ments du tableau d�j� tra�t�s est �gal � 10, alors on a fini            
            
            cmp bx, [eax] ; On compare le plus grand �l�ment � l'�l�ment tra�t� du tableau
            
            PRINT_UDEC 2, bx
            NEWLINE
            
            PRINT_UDEC 2, [eax]
            NEWLINE
            
            jb casMax     ; Si plus grand �l�ment < �l�ment tra�t�, alors on passe au casMax
            
            jmp suiteBoucle ; SAUT vers la suite de boucle

casMax:

            mov bx, [eax] ; L'�l�ment tra�t� devient le plus grand nombre

suiteBoucle:

            inc cl        ; On incr�mente de 1 le nombre d'�l�ments d�j� tra�t�s
            add eax, 2    ; On incr�mente de 2 l'adrese pour passer � l'�l�ment suivant
            jmp boucle
            
fin:
            
            PRINT_STRING 'Le plus grand nombre est : '
            PRINT_UDEC 2, bx
                        
            ; fin correcte de CMAIN dans l'IDE SASM            
            xor     eax,eax            
            ret