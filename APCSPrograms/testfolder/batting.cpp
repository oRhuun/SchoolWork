//calculate the batting average of a player
#include<iostream>
using namespace std;

int main()
{
	//declare variables
	int hits, atbats;
    float average;

	//get input from user
	cout << "How many hits did you get? ";
	cin >> hits;

	cout << "How many at bats did you have? ";
	cin >> atbats;

	//process
	average = (float)hits / atbats;

	//display output
	cout << "Batting Average: " << average << endl;

	return 0;
}