//calculate the total pay for an employee
#include<iostream>
using namespace std;

int main()
{
	//declare variables
	float rate, pay, hours;

	//get input from user
	cout << "How many hours did you work?";
	cin >> hours;
	cout << "What is your rate of pay?";
	cin >> rate;

	//process
	pay = hours * rate;

	//display output
	cout << "Total Wages: $" << pay << endl;


	return 0;
}