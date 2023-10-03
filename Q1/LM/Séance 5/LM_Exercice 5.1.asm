%include "io.inc"

section .text
global CMAIN
CMAIN:

    mov EBP, ESP; for correct debugging
    
    mov EAX, 0
    mov EBX, 0

    
    ; Afficher premier texte
    
    PRINT_STRING 'Entre une valeur en hexadecimal : '
    
    ; Récupérer la première valeur
        
        
    GET_HEX 2, AX
    
    ; Écrire la première valeur
    
    PRINT_HEX 2, AX
    
    ; Passer le texte à la ligne
    
    NEWLINE
    
    ; Afficher le deuxième texte
    
    PRINT_STRING 'Entre une deuxieme valeur en hexadecimal : '
    
    ; Récupérer la première valeur
    
    GET_HEX 2, BX
    
    ; Écrire la deuxième valeur
    
    PRINT_HEX 2, BX
    
    ADD AX, BX
    
    JC casDepassement
    JZ casZero
    
    ; Cas CF et ZF désarme
    
    NEWLINE
    PRINT_STRING 'CF et ZF sont desarmes'
    
    NEWLINE
    
    PRINT_STRING 'La somme est '
    
    ; Ecrire la somme
    
    PRINT_HEX 2, AX
    
    NEWLINE
    
    JMP fin
    
    
casDepassement:
    
    PRINT_STRING 'CF est arme'
    
    NEWLINE
    
    PRINT_STRING 'La somme a depasse la capacite'
    
    JMP fin
    
    
casZero:

    PRINT_STRING 'ZF est arme'
    
    NEWLINE
    
    PRINT_STRING 'La somme est nulle'
    
    ; on ne met pas JMP fin car la fin est juste après
    

fin:

    XOR EAX, EAX
    RET