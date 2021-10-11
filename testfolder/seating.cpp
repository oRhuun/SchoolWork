/*
Task Sheet
Calculate the revenues earned from ticket sales that have 3 different levels/prices

input
	number of A tickets sold aSold
	number of B tickets sold bSold
	number of C tickets sold cSold	int

output
	A Revenue: xx.xx	aRev
	B Revenue: xx.xx	bRev
	C Revenue: xx.xx	cRev
	Total Revenue: xx.xx	tRev	float

processing
	aRev = aSold * APRICE	constant 15.00
	bRev = bSold * BPRICE	constant 12.00
	cRev = cSold * CPRICE	constant 9.00
	tRev = aRev + bRev + cRev

special concerns
	use constants for prices

screen drawing
	How many A tickets were sold?	x
	How many B tickets were sold?	x
	How many C tickets were sold?	x

	A Revenue:                        $  xx.xx
	B Revenue:                        $  xx.xx
	C Revenue:                        $  xx.xx
	Total Revenue:                    $  xx.xx

pseudocode
	const float APRICE = 15.00f
	const float BPRICE = 12.00f
	const float CPRICE = 9.00f
	short aSold, bSold, cSold
	float aRev, bRev, cRev, tRev

	display “How many A tickets were sold?”
	input aSold
	display “How many B tickets were sold?”
	input bSold
	display “How many C tickets were sold?”
	input cSold

	aRev = aSold * APRICE
	bRev = bSold * BPRICE
	cRev = cSold * CPRICE                 
	tRev = aSold + bSold + cSold

	display “A Revenue: $“ + aRev
	display “B Revenue: $“ + bRev
	display “C Revenue: $“ + cRev
	skip line
	display “Total Revenue: $“ + tRev
*/
//Calculate Revenues earned from ticket sales that have 3 different levels/prices
#include<iostream>
#include<iomanip>
using namespace std;

int main()
{
	int aSold, bSold, cSold;
	float aRev, bRev, cRev, tRev;
	const float APRICE = 15.00f, BPRICE = 12.00f, CPRICE = 9.00f;
    
    cout << "How many A tickets were sold?";
    cin >> aSold;
    cout << "How many B tickets were sold?";
    cin >> bSold;
    cout << "How many C tickets were sold?";
    cin >> cSold;

    aRev = aSold * APRICE;
    bRev = bSold * BPRICE;
    cRev = cSold * CPRICE;
    tRev = aRev + bRev + cRev;

    cout << fixed << showpoint << setprecision(2);
    cout << "\nA Revenue:" << setw(40)<< "$" << setw(10) << aRev << endl;
    cout << "B Revenue:" << setw(40) << "$" << setw(10) << bRev << endl;
    cout << "C Revenue:" << setw(40) << "$" << setw(10) << cRev << endl;
    cout << "\nTotal Revenue:" << setw(36) << "$" << setw(10) << tRev << endl;

    return 0;
}


/*
 Trace
 aSold  bSold   cSold   aRev    bRev    cRev    tRev
 10(71) 15(73)  5(75)   150(77) 180(78) 45(79)  375(80)
 
  
Actual ouput
 How many A tickets were sold?10
 How many B tickets were sold?15
 How many C tickets were sold?5
 
 A Revenue:                                       $    150.00
 B Revenue:                                       $    180.00
 C Revenue:                                       $     45.00
 
 Total Revenue:                                   $    375.00

*/