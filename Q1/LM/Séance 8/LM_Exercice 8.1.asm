%include "io.inc"
SECTION    .bss

    ; réserver de la mémoire dans la RAM
    
    phrase resb 255
section .text
global CMAIN
CMAIN:

    mov ebp, esp 
    
    mov eax, 0
    
    ; ETAPE 1 : LECTURE ET SAUVEGARDE DE LA PHRASE
    
    PRINT_STRING 'Entre une phrase (max. 254 car.) : '
    GET_STRING phrase, 255 ; 255 car il y a le caractère 'Enter' en plus
    
    ; ETAPE 2 : Conversion minuscule --> majuscule
    
    mov eax, phrase ; On met l'adresse du début de la phrase dans EAX
    
boucle:

    CMP BYTE [eax], 0 ; On compare le caractère traîté avec le caractère vide (0)
    
    JE fin ; si le caracère traîté est le caractère vide, alors on a fini
    
    CMP BYTE [eax], 10 ; On compare le caractère traîté avec le caractère de retour à la ligne (10)

    JE fin10 ; si le caractère traîté est le caractère de retour à la ligne, alors on a fini
    
    CMP BYTE [eax], 'a' ; On compare le caracère traîté à 'a'
    JB suiteBoucle ; Si caractère traîté < que 'a', alors ce n'est pas une minuscule
    
    CMP BYTE [eax], 'z' ;On compare le caracère traîté à 'z'
    JA suiteBoucle ; Si caractère traîté > 'z', alors ce n'est pas une minuscule

    ; ICI on est dans le cas où le caractère traîté est une minuscule    
    
    XOR BYTE [eax], 00100000b ; On converti la minuscule en majuscule
    
suiteBoucle:

    INC eax ; On incrémente l'adresse de 1 pour passer au caractère suivant
    JMP boucle ; SAUT pour revenir au début de la boucle

fin10: ; fin différente pour le 10 car on veut retirer le caractère de retour à la ligne

    MOV BYTE [eax], 0 ; On remplace le caractère de retour à la ligne (10) par un caractère vide (0)

fin:

    NEWLINE
    PRINT_STRING phrase

    xor eax, eax
    ret