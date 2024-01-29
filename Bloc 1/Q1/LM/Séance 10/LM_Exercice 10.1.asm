%INCLUDE    'io.inc'        ; procédures d'input/ouput clavier et écran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour déclarer des données non initialisées  

SECTION     .data           ; section pour déclarer des données initialisées

SECTION     .text           ; section pour écrire du code en langage assembleur
                            ; cette section .text contiendra des instructions écrites en NASM 32 bits
GLOBAL      CMAIN           ; le libellé de début de la programmation dans l'IDE SASM doit être CMAIN avec une portée "publique"      
            
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libellé CMAIN: est le point d'entrée de notre programme
; écrivez votre instructions en langage assembleur NASM 32 bits
; à partir de la ligne ci-dessous

            mov     al,'a'
            mov     ecx,26            

boucle:
            ; appel de la procédure <afficheCharAl>
            call afficheCharAl
            inc al
            dec ecx
            jnz boucle

fin:                        
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret
                     
; procédure <afficheCharAl> qui affiche le contenu de al en tant que CHAR à l'écran
afficheCharAl:
            push eax ; sauvegarde de eax car al est utilisé dans la procédure
            
            PRINT_CHAR al
            
            pop eax  ; restauration de eax
            ret            