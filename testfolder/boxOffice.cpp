/*
Task Sheet
Calculate the net profit for a small movie theatre

Input -
movie name				mName
child tickets sold		cSold
adult tickets sold		aSold

Output -
table on page 144
net profit - 20% of gross	net
dFee - money going back to distributor

Processing - 
gross = (aSold * APRICE) + (cSold * CPRICE)
net = gross * .2
dFee = gross - net

Special Concerns -
use string for movie name
#include iomanip and string
getline
line up table at 60, numbers in field of 10

Screen Drawing
What is the name of the movie?xxxxxx
How many Adult tickets were sold?xx
How many Child tickets were sold?xx

Movie name:								xxxxxxxx(60)
Adult Tickets Sold:							  xx
Child Tickets Sold:							  xx
Gross Box Office Profit:				$  xx.xx
Net Box Office Profit:					$  xx.xx
Amount Paid to Distributor:				$  xx.xx

Pseudocode - 
display "What is the name of the movie?"
input mName
display "How many Adult tickets were sold?"
input aSold
display "How many Child tickets were sold?"
input cSold

gross = (aSold * APRICE) + (cSold * CPRICE)
net = gross * .2
dFee = gross - net

display "Movie Name:						xxxxxxxx"(60)
display "Adult Tickets Sold:					  xx"
display "Child Tickets Sold:					  xx"
display "Gross Box Office Profit:			$  xx.xx"
display "Net Box Office Profit:				$  xx.xx"
displat "Amount Piad to Distributor:		$  xx.xx"
*/

//Calculate the net profit for a small movie theatre
#include<iostream>
#include<iomanip>
#include<string>
using namespace std;

int main()
{
    //declare variables
    string      mName;
    int         aSold,
                cSold;
    float       gross,
                net,
                dFee;
    const float APRICE = 6.0,
                CPRICE = 3.0;
    
    //input
    cout << "What is the name of the movie?";
    getline(cin,mName);
    cout << "How many Adult tickets were sold?";
    cin >> aSold;
    cout << "How many Child tickets were sold?";
    cin >> cSold;
    
    //process
    gross = (cSold * CPRICE) + (aSold * APRICE);
    net = gross * .2;
    dFee = gross - net;
    
    //output
    cout << fixed << showpoint << setprecision(2);
    cout << "\nMovie Name:" << setw(49) << mName << endl;
    cout << "Adult Tickets Sold:" << setw(41) << aSold << endl;
    cout << "Child Tickets Sold:" << setw(41) << cSold << endl;
    cout << "Gross Box Office Profit:" << setw(26) << '$' << setw(10) << gross << endl;
    cout << "Net Box Office Profit:" << setw(28) << '$' << setw(10) << net << endl;
    cout << "Amount Paid to Distributor:" << setw(23) << '$' << setw(10) << dFee << endl;
    
    return 0;
    
}

/*
Actual Output -
 What is the name of the movie?American Sniper
 How many Adult tickets were sold?20
 How many Child tickets were sold?15
 
 Movie Name:                                  American Sniper
 Adult Tickets Sold:                                       20
 Child Tickets Sold:                                       15
 Gross Box Office Profit:                         $    165.00
 Net Box Office Profit:                           $     33.00
 Amount Paid to Distributor:                      $    132.00

Trace - 
 mName               aSold       cSold       gross       net         dFee        APRICE      CPRICE
 American Sniper(78) 20(80)      15(82)      165.00(85)  33.00(86)   132.00(87)  6.0(73)     3.0(74)
*/