%include "io.inc"

section .bss

pseudo resb 10 
    ; on réserve 10 octets en octet en mémoire
    ; on réserve 9 octets pour 9 caractères et 1 octet pour 1 caractère indiquant la fin de la string

section .text

global CMAIN

CMAIN:

    mov ebp, esp ; debug
    
    mov ecx, 0 ; vide le registre ecx
   
    mov cl, 0
    
    PRINT_STRING "Entre ton pseudo (max 9 car.) : "
    GET_STRING pseudo, 10
    
    mov eax, pseudo

boucle:
    
    cmp byte [eax], 0
    je fin
    
    cmp byte [eax], 10
    je fin
    
    inc cl
    inc eax
    
    jmp boucle
    
fin10:

    mov byte [eax], 0 ; remplace le 10 par un 0 pour ne plus avoir le caractère de retour à la ligne
    
    
fin:

    NEWLINE
    
    PRINT_STRING "Bonjour "
    PRINT_STRING pseudo
    
    NEWLINE
    
    PRINT_STRING "Nombre de lettres de ton pseudo : "
    PRINT_UDEC 1, cl

    xor eax, eax
    ret