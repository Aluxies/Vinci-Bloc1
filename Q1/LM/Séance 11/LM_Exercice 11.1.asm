%include "io.inc"

SECTION    .bss

    ; réserver de la mémoire dans la RAM pour la phrase
    ; 100 + 1 pour le caractère de passage à la ligne
    
    phrase resb 101

section .text

global CMAIN
CMAIN:

    mov ebp, esp
    
    PRINT_STRING 'Entrez une phrase (max 10 car.) : '
    GET_STRING phrase, 101    ; Lecture et sauvegarde de la phrase dans la memoire
    PRINT_STRING phrase
    
    NEWLINE
    NEWLINE
    
    ; Etape 1 : recherche de la fin de la phrase
    mov eax, phrase    ; On met l'adresse du premier caractère de la phrase dans eax
    
boucleRechercheFinPhrase:

    cmp byte [eax], 0    ; On compare le caractère en cours de traitement au caractère vide (fin de phrase)
    je debutVerifPalindrome    ; Si le caractère en cours de traitement est le caractère vide, SAUT à debutVerificationPalindrome
    
    cmp byte [eax], 10    ; On compare le caractère en cours de traitement au caractère de retour à la ligne
    je debutVerifPalindrome    ; Si le caractère en cours de traitement est le caractère de retour à la ligne, SAUT à debutVerificationPalindrome
    
    inc eax    ; On incrémente l'adrese de 1 pour passer au caractère suivant
    
    jmp boucleRechercheFinPhrase    ; SAUT au début de la boucle
    
debutVerifPalindrome:

    dec eax    ; On décrémente l'adresse de 1 pour passer au caractère précédent
    mov edx, eax    ; On 
    mov eax, phrase
    mov bl, 1
    
boucleVerifPalindrome:

    cmp bl, 0
    je finBoucleVerifPalindrome
    
    cmp eax, edx
    jae finBoucleVerifPalindrome
    
    cmp byte [eax], ' ' ; On compare le caractère en cours de traitement au début de la phrase avec un caractère blanc
    je blancAGauche
    
    cmp byte [edx], ' ' ;
    je blancADroite
    
    mov cl, [eax]
    cmp cl, [eax]
    jne pasPalindrome
    
    inc eax
    dec edx
    
    jmp suiteVerifPalindrome
    
blancAGauche:

    inc eax
    jmp suiteVerifPalindrome
    

blancADroite:

    dec edx
    jmp suiteVerifPalindrome


pasPalindrome:

    mov bl, 0    ; On met le booléen bl à 0 pour signaler que la phrase n'est pas un palindrome
    
suiteVerifPalindrome:

    jmp boucleVerifPalindrome

finBoucleVerifPalindrome:

    cmp bl, 0    ; On compare bl à 0
    jne finPasPalindrome    ; Si bl = 0, alors la phrase n'est pas un palindrome
    
    PRINT_STRING "Cette phrase est un palindrome"
    
    jmp fin    ; Saut à la fin du programme
 
finPasPalindrome:

    PRINT_STRING "Cette phrase n'est pas un palindrome"
    
fin:

    ; Fin correcte de CMAIN dans l'IDE SASM
    xor eax, eax
    ret    
    