%include "io.inc"

section .text
global CMAIN
CMAIN:
    
    mov ebp, esp
    
    mov eax, 0 ; Vider le registre
    
    mov al, 'a'
    
    
boucle1:

    cmp al, 'z'
    ja finBoucle1
    
    PRINT_CHAR al
    
    inc al
    
    jmp boucle1
    
finBoucle1: 

    NEWLINE
    mov al, 'z'
    
boucle2:

    cmp al, 'a'
    jb fin
    
    PRINT_CHAR al
    
    dec al
    
    jmp boucle2
    
fin:

    xor eax, eax
    ret