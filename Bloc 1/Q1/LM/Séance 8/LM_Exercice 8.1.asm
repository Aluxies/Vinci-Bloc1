%include "io.inc"
SECTION    .bss

    ; r�server de la m�moire dans la RAM
    
    phrase resb 255
section .text
global CMAIN
CMAIN:

    mov ebp, esp 
    
    mov eax, 0
    
    ; ETAPE 1 : LECTURE ET SAUVEGARDE DE LA PHRASE
    
    PRINT_STRING 'Entre une phrase (max. 254 car.) : '
    GET_STRING phrase, 255 ; 255 car il y a le caract�re 'Enter' en plus
    
    ; ETAPE 2 : Conversion minuscule --> majuscule
    
    mov eax, phrase ; On met l'adresse du d�but de la phrase dans EAX
    
boucle:

    CMP BYTE [eax], 0 ; On compare le caract�re tra�t� avec le caract�re vide (0)
    
    JE fin ; si le carac�re tra�t� est le caract�re vide, alors on a fini
    
    CMP BYTE [eax], 10 ; On compare le caract�re tra�t� avec le caract�re de retour � la ligne (10)

    JE fin10 ; si le caract�re tra�t� est le caract�re de retour � la ligne, alors on a fini
    
    CMP BYTE [eax], 'a' ; On compare le carac�re tra�t� � 'a'
    JB suiteBoucle ; Si caract�re tra�t� < que 'a', alors ce n'est pas une minuscule
    
    CMP BYTE [eax], 'z' ;On compare le carac�re tra�t� � 'z'
    JA suiteBoucle ; Si caract�re tra�t� > 'z', alors ce n'est pas une minuscule

    ; ICI on est dans le cas o� le caract�re tra�t� est une minuscule    
    
    XOR BYTE [eax], 00100000b ; On converti la minuscule en majuscule
    
suiteBoucle:

    INC eax ; On incr�mente l'adresse de 1 pour passer au caract�re suivant
    JMP boucle ; SAUT pour revenir au d�but de la boucle

fin10: ; fin diff�rente pour le 10 car on veut retirer le caract�re de retour � la ligne

    MOV BYTE [eax], 0 ; On remplace le caract�re de retour � la ligne (10) par un caract�re vide (0)

fin:

    NEWLINE
    PRINT_STRING phrase

    xor eax, eax
    ret