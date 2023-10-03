%INCLUDE    "io.inc"        ; procédures d'input/ouput clavier et écran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour déclarer des données non initialisées

SECTION     .data           ; section pour déclarer des données initialisées
            t dw  154, 10, 56000, 8, 4568, 65445, 1500, 2, 50252, 25
SECTION     .text           ; section pour écrire du code en langage assembleur
                            ; cette section .text contiendra des instructions écrites en NASM 32 bits
GLOBAL      CMAIN           ; le libellé de début de la programmation dans l'IDE SASM doit être CMAIN avec une portée "publique"
    
CMAIN:                  
            mov     ebp,esp ; pour debugging fonctionnel dans l'IDE SASM               

; le libellé CMAIN: est le point d'entrée de notre programme
; écrivez votre instructions en langage assembleur NASM 32 bits
; à partir de la ligne ci-dessous
            
            mov ebx, 0    ; On vide le registre ebx
            
            mov eax, t    ; On met l'adresse du début du tableau dans EAX
            mov bx, [t]   ; BX va contenir le plus grand nombre et on l'initialise au 1e élément du tableau t
            
            mov cl, 1     ; CL va contenir le nombre d'éléments déjà traîtés et va être initialisé avec le 1e élément du tableau
            
            add eax, 2    ; On incrémente de 2 l'adresse pour aller sur l'élément suivant du tableau (2 car 2 octets par élément)
            
boucle:

            cmp cl, 10    ; On compare le nombre d'éléments du tableau déjà traîtés à 10
            je fin        ; Si le nombre d'éléments du tableau déjà traîtés est égal à 10, alors on a fini            
            
            cmp bx, [eax] ; On compare le plus grand élément à l'élément traîté du tableau
            
            PRINT_UDEC 2, bx
            NEWLINE
            
            PRINT_UDEC 2, [eax]
            NEWLINE
            
            jb casMax     ; Si plus grand élément < élément traîté, alors on passe au casMax
            
            jmp suiteBoucle ; SAUT vers la suite de boucle

casMax:

            mov bx, [eax] ; L'élément traîté devient le plus grand nombre

suiteBoucle:

            inc cl        ; On incrémente de 1 le nombre d'éléments déjà traîtés
            add eax, 2    ; On incrémente de 2 l'adrese pour passer à l'élément suivant
            jmp boucle
            
fin:
            
            PRINT_STRING 'Le plus grand nombre est : '
            PRINT_UDEC 2, bx
                        
            ; fin correcte de CMAIN dans l'IDE SASM            
            xor     eax,eax            
            ret