%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    
    mov eax, 1 ; Premier op�rateur d'addition
    mov ebx, 1 ; Deuxi�me op�rateur d'addition
    mov ecx, 0 ; R�sultat addition
    
boucle:

    cmp eax, 6 ; On compare la valeur du 1e op�rateur � 6
    je fin ; Si la valeur est 6, on va � la fin

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
    
        cmp ebx, 10 ; On compare la valeur du 2e op�rateur � 10
        je finProcedure ; Si la valeur est 10, on finit la boucle
    
        PRINT_UDEC 1, eax ; On affiche le 1e op�rateur d'addition sur 1 octet
        PRINT_CHAR '+' ; On affiche le caract�re '+'
        PRINT_UDEC 1, ebx ; On affiche le 2e op�rateur d'addition sur 1 octet
        PRINT_CHAR '=' ; On affiche le caract�re '='
        
        mov ecx, eax ; Cl prend la valeur de al
        
        add ecx, ebx ; On ajoute bl � cl
        
        PRINT_UDEC 1, ebx ; On affiche le r�sultat d'addition sur 1 octet
        
        NEWLINE ; On passe � la ligne
        
        inc ebx ; On augmente de 1 la valeur du 2e op�rateur
        
        jmp boucleTableAddition ; SAUT au d�but de la boucle
    
    finProcedure:
        pop ecx
        pop ebx
        pop eax
        ret