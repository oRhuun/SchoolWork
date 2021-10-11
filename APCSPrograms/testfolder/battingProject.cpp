//calculate the user’s batting average
#include<iostream>
using namespace std;

int main()
{

	//define variables
	int atBats, hits;
	float avg;

	//get input from user
	cout << "How many times did you bat?";
	cin >> atBats;
	cout << "How many hits did you get?";
	cin >> hits;

	//process
	avg = (float)hits / atBats;

	//output
    cout << endl;
    cout << "You batted " << atBats << " times." << endl;
    cout << "You got " << hits << " hits." << endl;
	cout << "Batting Average: " << avg << endl;

	return 0;
}