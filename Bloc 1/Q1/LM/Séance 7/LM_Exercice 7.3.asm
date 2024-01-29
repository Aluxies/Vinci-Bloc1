%include "io.inc"

section .bss

    tableau resd 10             ; on reserve 10 bits dans l'espace memoire pour 10 entiers

section .text
global CMAIN
CMAIN:

    mov ebp, esp                ; for correct debugging

    mov ecx, 0                  ; on vide le registre ecx
    
    mov eax, tableau            ; on met l'adresse memoire du premier nombre dans eax
    mov cl, 0                   ; on represente le nombre de nombres lus
    
boucleEncodage:

    cmp cl, 10                  ; on compare cl à 10 pour savoir si on a traité les 10 nombres
    je calculMinMax             ; si cl == 10, on saute à calculMinMax
    
    inc cl                      ; on incrémente de 1 le nombre de nombres déjà lus
    
    PRINT_STRING "Entre le nombre "
    PRINT_UDEC 1, cl
    
    NEWLINE
    
    GET_UDEC 4, [eax]           ; lit un nombre entier non signé sur 4 octets
    
    PRINT_STRING "Nombre entre : "
    PRINT_UDEC 4, [eax]
    
    NEWLINE
    NEWLINE
    
    add eax, 4                  ; on ajoute 4 à eax pour passer à l'adresse suivante
    
    jmp boucleEncodage

calculMinMax:

    mov ebx, [tableau]          ; ebx conserve le min
    mov edx, [tableau]          ; edx conserve le max
    mov eax, tableau            ; on remet l'adresse du début du tableau dans eax
    
    add eax, 4                  ; on augmente l'adresse de 4 pour aller sur le deuxième nombre du tableau
    
    mov cl, 1                   ; on initialise le nombre de nombres traités à 1 car on a déjà traité le 1
    

boucleMinMax:

    cmp cl, 10                  ; on compare cl à 10 pour savoir si on a traité les 10 nombres
    je fin
    
    cmp ebx, [eax]
    ja casMin                   ; si min plus grand que nombre traité, alors le nombre traité devient min
    
    cmp edx, [eax]
    jb casMax                   ; si max plus petit que nombre traité, alors le nombre traité devient max
    
    jmp suiteDeLaBoucle
    
casMin:

    mov ebx, [eax]              ; on remplace l'ancienne valeur de min par la nouvelle plus petite
    
    jmp suiteDeLaBoucle
    
casMax:

    mov edx, [eax]              ; on remplace l'ancienne valeur de max par la nouvelle plus grande

suiteDeLaBoucle:

    inc cl
    
    add eax, 4
    
    jmp boucleMinMax


fin:

    PRINT_STRING "Le plus grand nombre est "
    PRINT_UDEC 4, edx
    
    NEWLINE
    
    PRINT_STRING "Le plus petit nombre est "
    PRINT_UDEC 4, ebx

    
    xor eax, eax
    ret