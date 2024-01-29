%include "io.inc"

section .text
global CMAIN
CMAIN:
    
    mov ebp, esp
    
    mov ecx, 0 ; Vider le registre
    
    mov cl, 1
    
    
boucle:

    cmp cl, 11
    je finDeBoucle
    
    PRINT_UDEC 1, cl
    
    inc cl
    
    jmp boucle
    

finDeBoucle:

    PRINT_STRING " Boum !!!"
    
fin:

    xor eax, eax
    ret