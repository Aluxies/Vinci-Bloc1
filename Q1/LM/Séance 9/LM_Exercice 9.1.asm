%include "io.inc"

section .text

section .date

tableau    dd 1,2,3,4,5,6,7,8,9,10

global CMAIN
CMAIN:

    mov ebp, esp
    
    mov ecx, 0    ; On vide le registre ecx
    
    mov eax, tableau    ; On met l'adresse du premier élément du tableau dans eax
    
    mov cl, 0    ; CL sera le compteur d'éléments du tableau déjà traîtés et on l'initialise à 0
    
boucleAfficheEtEmpile:

    cmp cl, 10    ; On compare le nombre d'éléments du tableau déjà traîtés à 10 pour savoir si tout le tableau a été parcouru
    
    je suite    ; Si le nombre d'éléments du tableau déjà traîté est égal à 10, on va à la suite de la boucle
    
    PRINT_UDEC 4, [eax]    ; On affiche l'élément du tableau en cours de traitement
    PRINT_CHAR ' '    ; On affiche un blanc
    
    push DWORD [eax]    ; On met l'élément du tableau en cours de traitement au sommet de la pile
    
    inc cl    ; On incrémente de 1 le nombre d'éléments déjà traîtés du tableau
    
    add eax, 4    ; On augmente l'adresse de 4 pour aller sur l'élément suivant
    
    jmp boucleAfficheEtEmpile    ; SAUT pour revenir au début de la boucle

suite:
    
    NEWLINE    ; On passe à la ligne dans l'affichage
    
    mov eax, tableau    ; On se remet au début du tableau
    
    mov cl, 0    ; On remet le compteur du nombre d'éléments du tableau déjà traîtés à 0
    
boucleDepileEtAffiche:

    cmp cl, 10    ; On compare le nombre d'éléments du tableau déjà traîtés à 10 pour savoir si tout le tableau a été parcouru
    
    je fin    ; Si le nombre d'éléments du tableau déjà traîté est égal à 10, on va à la suite de la boucle
    PRINT_STRING 'ici'
    PRINT_HEX 4,[EAX]
    pop edx    ; On prend l'élément du tableau en cours de traitement
    PRINT_STRING 'ici0'
    PRINT_UDEC 4,edx
    MOV DWORD [eax],edx
    PRINT_STRING 'ici2'
    PRINT_UDEC 4, [eax]    ; On affiche l'élément du tableau en cours de traitement
    PRINT_STRING 'ici3'
    PRINT_CHAR ' '    ; On affiche un blanc
    PRINT_STRING 'ici4'
    inc cl    ; On incrémente de 1 le nombre d'éléments déjà traîtés du tableau
    PRINT_STRING 'ici5'
    add eax, 4    ; On augmente l'adresse de 4 pour aller sur l'élément suivant
    
    jmp boucleDepileEtAffiche    ; SAUT pour revenir au début de la boucle
    
    
fin:
    
   
    xor eax, eax
    ret