#Booth's Algo
.data 
	ask: .asciiz  "Input a positive or negative integer:\n"
	newline: .asciiz "\n"
	buffer: .space 20
	line: .asciiz "____________________________________\n"
.text
#Ask for input and store it for first variable
	la $a0, ask 		#Load and print string asking for input string
        li $v0,4
        syscall
        
        li $v0, 8 		#service code for taking an input
        la $a0, buffer
        li $a1, 20 		
        la $s0, ($a0)		#load input into $s0
        syscall
    	
    	add $s1, $zero, $zero 	#initialize $s1 to zero
    	lb $t1, 0($s0)		#load first char of string
    	bne $t1, 45, toInt1	#go to loop if it isn't '-'
    	addi $t6, $zero, 1	#set flag if it is '-'
    	addi $s0, $s0, 1	#increment pointer if there was a '-'
    	
toInt1:	
	lb $t1, 0($s0)		#load the digit into a register
	mfhi $t2
	bnez $t2, error1	#check for overflow
	beq $t1, 10 negCheck1	#exit if newline found
	blt $t1, 48, error1
	bgt $t1, 57, error1	#check if ASCII digit or not
	subi $t1, $t1, 48	#convert digit from ascii to int
	mul $s1, $s1, 10	#sum *= 10
	add $s1, $s1, $t1	#add digit to sum
	addi $s0, $s0, 1	#increment address pointer by 1
	j toInt1
    	
error1: 	
	subi $s1, $zero, 1	#set output to -1 if an error is thrown
    	j print1
    	
negCheck1:
	bne $t6, 1, print1	#exit if there was no negative sign
	sub $s1, $zero, $s1	#make integer negative if there was a negative sign

print1: 	
        li $v0, 4       	#print a newline before the next ask
	la $a0, newline       	#load address of the string
	syscall	
    	
#Ask for input and store it for second variable
	la $a0, ask 		#Load and print string asking for input string
        li $v0,4
        syscall
        
        li $v0, 8 		#service code for taking an input
        la $a0, buffer
        li $a1, 20 		
        la $s0, ($a0)		#load input into $s0
        syscall
    	
    	add $s2, $zero, $zero 	#initialize $s2 to zero
    	lb $t1, 0($s0)		#load first char of string
    	bne $t1, 45, toInt2	#go to loop if it isn't '-'
    	addi $t7, $zero, 1	#set flag if it is '-'
    	addi $s0, $s0, 1	#increment pointer if there was a '-'
    	
toInt2:	
	lb $t1, 0($s0)		#load the digit into a register
	mfhi $t2
	bnez $t2, error2	#check for overflow
	beq $t1, 10 negCheck2	#exit if newline found
	blt $t1, 48, error2
	bgt $t1, 57, error2	#check if ASCII digit or not
	subi $t1, $t1, 48	#convert digit from ascii to int
	mul $s2, $s2, 10	#sum *= 10
	add $s2, $s2, $t1	#add digit to sum
	addi $s0, $s0, 1	#increment address pointer by 1
	j toInt2
    	
error2: 	
	subi $s2, $zero, 1	#set output to -1 if an error is thrown
    	j print2
    	
negCheck2:
	bne $t7, 1, print2	#exit if there was no negative sign
	sub $s2, $zero, $s2	#make integer negative if there was a negative sign

print2: 	
 	la $a0, ($s1)  		#load mcand into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall
        
	la $a0, newline       	#load address of the newline string
	li $v0, 4       	#print a newline before the next ask
	syscall	
	
        la $a0, ($s2)  		#load mplier into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall
        
        la $a0, newline       	#load address of the newline string
	li $v0, 4       	#print a newline before the next ask
	syscall	
	
        li $v0, 4       	#print a line 
	la $a0, line       	#load address of the string
	syscall	

#start of booth's algorithm
	add $s3, $zero, $zero	#set pl to 0 $s3
	add $s4, $zero, $s2	#set pr to mplier $s4
	add $t0, $zero, $zero	#set hidden bit to 0
	add $t1, $zero, $zero	#set counter to 0
	
booth:	beq $t1, 32, exit	#check if 32, jump to exit if need be
	andi $t3, $s4, 1	
	beq $t0, $t3, shiftp	#check if hidden and ls bit of pr =, jump to shift
	beq $t0, 1, addpl	#check what the hidden bit is, jump to + or -
	sub $s3, $s3, $s1	#subtract Pl and mcand
	j shiftp		#jump to the shift
	
addpl:	add $s3, $s3, $s1	#add Pl and mcand and fall to the shift			
	
shiftp:	andi $t0, $s4, 1	#set hidden bit
	srl $s4, $s4, 1		#srl pr
	andi $t4, $s3, 1	#check lsb of pl and set msb of pr to that
	beq $t4, $zero, skip 
	addi $s4, $s4, 2147483648 #add lsb of pl to pr
skip:	sra $s3, $s3, 1		#sra pl
	addi $t1, $t1, 1	#increment counter
	
	la $a0, ($s3)  		#load pl into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall
        
        la $a0, ($s4)  		#load pr into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall
        
        la $a0, newline       	#load address of the newline string
	li $v0, 4       	#print a newline before the next ask
	syscall	
	
	j booth			#jump to start of loop
	
exit:	li $v0, 4       	#print a line 
	la $a0, line       	#load address of the string
	syscall	
	
	la $a0, ($s3)  		#load pl into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall
        
        la $a0, ($s4)  		#load pr into $a0 to be printed out
        li $v0, 35		#load syscall for printing in binary
        syscall