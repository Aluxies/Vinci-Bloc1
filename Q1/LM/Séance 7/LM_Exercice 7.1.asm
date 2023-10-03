%include "io.inc"

section .bss

 

section .data

    nombreMystere db 76
    
section .text
global CMAIN
CMAIN:
    
    mov ebp, esp ; pour debug correct
    
    mov cl, 0 ; vider le registre al (compteur)
    mov al, 0 ; vider le registre al (nombre entré)
    
boucle:

    inc cl
    PRINT_STRING "Entre un nombre : "
    GET_UDEC 1, al ; récupère un entier sur 1 octet
    PRINT_UDEC 1, al
    
    NEWLINE
    
    CMP al, [nombreMystere]
    
    je fin ; al est égal à nombre mystère
    
    ja nombreMysterePlusPetit ; al plus grand que nombre mystère
    
    jb nombreMysterePlusGrand ; al plus petit que nombre mystère
    
nombreMysterePlusPetit:
    
    PRINT_STRING "Trop grand, essaie encore !"
    NEWLINE
    NEWLINE
    
    jmp boucle
    
nombreMysterePlusGrand:

    PRINT_STRING "Trop petit, essaie encore !"
    
    NEWLINE
    NEWLINE
    
    jmp boucle
    
fin:

    PRINT_STRING "Bravo, le nombre est bien : "
    PRINT_UDEC 1, nombreMystere ; ecrit un entier sur 1 octet
    NEWLINE
    PRINT_STRING "Nombre d'essai(s) : "
    PRINT_UDEC 1, cl
    
    xor eax, eax
    ret