%include "io.inc"

section .text

section .date

tableau    dd 1,2,3,4,5,6,7,8,9,10

global CMAIN
CMAIN:

    mov ebp, esp
    
    mov ecx, 0    ; On vide le registre ecx
    
    mov eax, tableau    ; On met l'adresse du premier �l�ment du tableau dans eax
    
    mov cl, 0    ; CL sera le compteur d'�l�ments du tableau d�j� tra�t�s et on l'initialise � 0
    
boucleAfficheEtEmpile:

    cmp cl, 10    ; On compare le nombre d'�l�ments du tableau d�j� tra�t�s � 10 pour savoir si tout le tableau a �t� parcouru
    
    je suite    ; Si le nombre d'�l�ments du tableau d�j� tra�t� est �gal � 10, on va � la suite de la boucle
    
    PRINT_UDEC 4, [eax]    ; On affiche l'�l�ment du tableau en cours de traitement
    PRINT_CHAR ' '    ; On affiche un blanc
    
    push DWORD [eax]    ; On met l'�l�ment du tableau en cours de traitement au sommet de la pile
    
    inc cl    ; On incr�mente de 1 le nombre d'�l�ments d�j� tra�t�s du tableau
    
    add eax, 4    ; On augmente l'adresse de 4 pour aller sur l'�l�ment suivant
    
    jmp boucleAfficheEtEmpile    ; SAUT pour revenir au d�but de la boucle

suite:
    
    NEWLINE    ; On passe � la ligne dans l'affichage
    
    mov eax, tableau    ; On se remet au d�but du tableau
    
    mov cl, 0    ; On remet le compteur du nombre d'�l�ments du tableau d�j� tra�t�s � 0
    
boucleDepileEtAffiche:

    cmp cl, 10    ; On compare le nombre d'�l�ments du tableau d�j� tra�t�s � 10 pour savoir si tout le tableau a �t� parcouru
    
    je fin    ; Si le nombre d'�l�ments du tableau d�j� tra�t� est �gal � 10, on va � la suite de la boucle
    PRINT_STRING 'ici'
    PRINT_HEX 4,[EAX]
    pop edx    ; On prend l'�l�ment du tableau en cours de traitement
    PRINT_STRING 'ici0'
    PRINT_UDEC 4,edx
    MOV DWORD [eax],edx
    PRINT_STRING 'ici2'
    PRINT_UDEC 4, [eax]    ; On affiche l'�l�ment du tableau en cours de traitement
    PRINT_STRING 'ici3'
    PRINT_CHAR ' '    ; On affiche un blanc
    PRINT_STRING 'ici4'
    inc cl    ; On incr�mente de 1 le nombre d'�l�ments d�j� tra�t�s du tableau
    PRINT_STRING 'ici5'
    add eax, 4    ; On augmente l'adresse de 4 pour aller sur l'�l�ment suivant
    
    jmp boucleDepileEtAffiche    ; SAUT pour revenir au d�but de la boucle
    
    
fin:
    
   
    xor eax, eax
    ret