%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    
    mov eax, 1 ; Premier opérateur d'addition
    mov ebx, 1 ; Deuxième opérateur d'addition
    mov ecx, 0 ; Résultat addition
    
boucle:

    cmp eax, 6 ; On compare la valeur du 1e opérateur à 6
    je fin ; Si la valeur est 6, on va à la fin

    call afficheUneTableAddition
    inc eax
    
    NEWLINE
    NEWLINE
    
    jmp boucle
    
fin:

    xor eax, eax
    ret
    
afficheUneTableAddition:

        push eax
        push ebx
        push ecx
    
    boucleTableAddition:
    
        cmp ebx, 10 ; On compare la valeur du 2e opérateur à 10
        je finProcedure ; Si la valeur est 10, on finit la boucle
    
        PRINT_UDEC 1, eax ; On affiche le 1e opérateur d'addition sur 1 octet
        PRINT_CHAR '+' ; On affiche le caractère '+'
        PRINT_UDEC 1, ebx ; On affiche le 2e opérateur d'addition sur 1 octet
        PRINT_CHAR '=' ; On affiche le caractère '='
        
        mov ecx, eax ; Cl prend la valeur de al
        
        add ecx, ebx ; On ajoute bl à cl
        
        PRINT_UDEC 1, ebx ; On affiche le résultat d'addition sur 1 octet
        
        NEWLINE ; On passe à la ligne
        
        inc ebx ; On augmente de 1 la valeur du 2e opérateur
        
        jmp boucleTableAddition ; SAUT au début de la boucle
    
    finProcedure:
        pop ecx
        pop ebx
        pop eax
        ret