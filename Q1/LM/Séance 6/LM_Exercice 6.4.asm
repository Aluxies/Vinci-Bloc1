%include "io.inc"

section .bss

    password resb 11
    
section .data

    passwordOK db 'vinci2022', 0

section .text
global CMAIN
CMAIN:

    mov ebp, esp; for correct debugging
    
    mov eax, 0 ; vider le registre de l'accumulateur
    mov ebx, 0 ; vider le registre
    mov ecx, 0 ; vider le registre du compteur
    
    mov bl, 0 ; bl utilisé comme booléen : bl == 1 signifie password correct
    
boucleDemandePassword:

    cmp bl, 1 ; on regarde si le booléen est true
    je fin ; on va à la fin du programme
    
    PRINT_STRING "Entre ton mot de passe (max. 10 car.) : "
    GET_STRING password, 11
    PRINT_STRING password
    
    NEWLINE

    mov esi, passwordOK
    mov edi, password
    mov cl, 0
    
    mov al,[edi] ; On met le premier caractère de password dans al
    
boucleVerification:

    cmp cl, 9
    je longueurOK
    
    cmp byte al, [esi] ; byte : on regarde au niveau d'un caractère
    jne passwordIncorrect
    
    inc cl 
    inc esi ; on passe au caractère suivant de passwordOK
    inc edi ; on passe au caractère suivant de password
    
    mov al, [edi] ; on met le caractère suivant de password dans al
    
    jmp boucleVerification 

longueurOK:

    cmp al, 0
    je passwordCorrect
    
    cmp al, 10
    je passwordCorrect


passwordIncorrect:

    PRINT_STRING "Mot de passe incorrect"
    NEWLINE
    
    jmp boucleDemandePassword


passwordCorrect: 
    
    mov bl, 1
    jmp boucleDemandePassword
            
    
fin:

    PRINT_STRING "Mot de passe correct !"
    
    xor eax, eax
    ret