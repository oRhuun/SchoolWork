#convert an ASCII number string containing positive and negative integer decimal strings, to an integer.
#Convert a user inputted string to an integer (base ten)
.data 
	ask: .asciiz  "Input a positive or negative integer:\n"
	buffer: .space 20
.text
	#Ask for input and store it
	la $a0, ask 	#Load and print string asking for input string
        li $v0,4
        syscall
        
        li $v0, 8 		#service code for taking an input
        la $a0, buffer
        li $a1, 20 		
        la $s0, ($a0)		#load input into $s0
        syscall
    	
    	add $s1, $zero, $zero 	#initialize $s1 to zero
    	lb $t1, 0($s0)		#load first char of string
    	bne $t1, 45, loop	#go to loop if it isn't '-'
    	addi $t6, $zero, 1	#set flag if it is '-'
    	addi $s0, $s0, 1	#increment pointer if there was a '-'
    	
loop:	lb $t1, 0($s0)		#load the digit into a register
	mfhi $t2
	bnez $t2, error		#check for overflow
	beq $t1, 10 finish	#exit if newline found
	blt $t1, 48, error
	bgt $t1, 57, error	#check if ASCII digit or not
	subi $t1, $t1, 48	#convert digit from ascii to int
	mul $s1, $s1, 10	#sum *= 10
	add $s1, $s1, $t1	#add digit to sum
	addi $s0, $s0, 1	#increment address pointer by 1
	j loop
    	
error: 	subi $s1, $zero, 1	#set output to -1 if an error is thrown
    	j exit
    	
finish:	bne $t6, 1, exit	#exit if there was no negative sign
	sub $s1, $zero, $s1	#make integer negative if there was a negative sign

 exit: 	la $a0, ($s1)  		#load integer into $a0 to be printed out
        li $v0, 1		#load syscall for printing an integer
        syscall
    	