#load 0010 0000 0000 0001 0100 1001 0010 0100 into $t1 then print it out in binary
#lui then ori, then print, how to print in binary?, load the correct system call service 

lui $t1, 0x2001 	#load the upper 4 bytes of the constant into $t1
ori $t1, $t1, 0x4924 	#load the lower 4 bytes of the constant into $t1

la $a0, ($t1) 		#load the constant stored in $t1 to $a0, which the system calls to print
li $v0, 35 		#load the appropriate syscall service to print in binary
syscall 		#make the system call