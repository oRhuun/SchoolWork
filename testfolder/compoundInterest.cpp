//calculate compound interest
#include<iostream>
#include<math.h>
using namespace std;

int main()
{
	//declare variables
	float principle, rate, time, timesCompounded, amount, interest, rateDisplay;

	//get input
	cout << "What is the principle value being invested?";
	cin >> principle;
	cout << "What is the interest rate in decimal form?";
	cin >> rate;
	cout << "How many times is it compounded anually?";
	cin >> timesCompounded;
	cout << "How long (in years) is the money being invested?";
	cin >> time;

	//process
	amount = principle * pow((1 + rate / timesCompounded), (timesCompounded * time));
	interest = amount - principle;
    rateDisplay = rate * 100;

	//display output
    cout << endl;
	cout << "You invested $" << principle << " for " << time << " years at %" << rateDisplay << " interest." << endl;
	cout << "Your interest was $" << interest << endl;
	cout << "Your total amount was $" << amount << endl;

	return 0;
}