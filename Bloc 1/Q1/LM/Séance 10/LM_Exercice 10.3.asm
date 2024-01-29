%include "io.inc"

section .text
global CMAIN
CMAIN:

    mov al, 'Z' ; On garde 'Z' comme première valeur
    mov cl, 0 ; On initialise le compteur à 0
    
boucle:

    cmp cl, 26 ; On compare la valeur du compteur à 26
    je fin
    
    call afficheCharAl ; On fait appel 
    
    dec al
    inc cl
    
    
    jmp boucle
    
fin:

    xor eax, eax
    ret
    
afficheCharAl:

    push eax ; sauvegarde de eax car al est utilisé dans la procédure
    
    PRINT_CHAR ' '
    PRINT_CHAR al
            
    pop eax  ; restauration de eax
    ret