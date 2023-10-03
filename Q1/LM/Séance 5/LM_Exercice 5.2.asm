%include "io.inc"

section .text

    
global CMAIN
CMAIN:

    MOV EBP, ESP ; for correct debugging
    MOV EAX, 0
    
    PRINT_STRING 'Entre ton age : '
    GET_UDEC 1, AL
    NEWLINE
    
    CMP AL, 18
    
    JAE casMajeur
    
    ; Cas mineur
    
    PRINT_STRING 'Tu es mineur '
    
    JMP fin
    
casMajeur:

    PRINT_STRING 'Tu es majeur '
    NEWLINE
    PRINT_STRING 'Ton age en hexadecimal est '
    PRINT_HEX 1, AL
    
fin:
   
    xor eax, eax
    ret