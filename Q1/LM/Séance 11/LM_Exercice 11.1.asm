%include "io.inc"

SECTION    .bss

    ; r�server de la m�moire dans la RAM pour la phrase
    ; 100 + 1 pour le caract�re de passage � la ligne
    
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
    mov eax, phrase    ; On met l'adresse du premier caract�re de la phrase dans eax
    
boucleRechercheFinPhrase:

    cmp byte [eax], 0    ; On compare le caract�re en cours de traitement au caract�re vide (fin de phrase)
    je debutVerifPalindrome    ; Si le caract�re en cours de traitement est le caract�re vide, SAUT � debutVerificationPalindrome
    
    cmp byte [eax], 10    ; On compare le caract�re en cours de traitement au caract�re de retour � la ligne
    je debutVerifPalindrome    ; Si le caract�re en cours de traitement est le caract�re de retour � la ligne, SAUT � debutVerificationPalindrome
    
    inc eax    ; On incr�mente l'adrese de 1 pour passer au caract�re suivant
    
    jmp boucleRechercheFinPhrase    ; SAUT au d�but de la boucle
    
debutVerifPalindrome:

    dec eax    ; On d�cr�mente l'adresse de 1 pour passer au caract�re pr�c�dent
    mov edx, eax    ; On 
    mov eax, phrase
    mov bl, 1
    
boucleVerifPalindrome:

    cmp bl, 0
    je finBoucleVerifPalindrome
    
    cmp eax, edx
    jae finBoucleVerifPalindrome
    
    cmp byte [eax], ' ' ; On compare le caract�re en cours de traitement au d�but de la phrase avec un caract�re blanc
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

    mov bl, 0    ; On met le bool�en bl � 0 pour signaler que la phrase n'est pas un palindrome
    
suiteVerifPalindrome:

    jmp boucleVerifPalindrome

finBoucleVerifPalindrome:

    cmp bl, 0    ; On compare bl � 0
    jne finPasPalindrome    ; Si bl = 0, alors la phrase n'est pas un palindrome
    
    PRINT_STRING "Cette phrase est un palindrome"
    
    jmp fin    ; Saut � la fin du programme
 
finPasPalindrome:

    PRINT_STRING "Cette phrase n'est pas un palindrome"
    
fin:

    ; Fin correcte de CMAIN dans l'IDE SASM
    xor eax, eax
    ret    
    