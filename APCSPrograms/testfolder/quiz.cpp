//calculate average score for 3 quiz scores
#include<iostream>
using namespace std;

int main()
{
	//declare variables
	int quiz1, quiz2, quiz3;
	float avg;

	//get input for quizzes
	cout << "What is the score of your first quiz?";
	cin >> quiz1;
	cout << "What is the score of your second quiz?";
	cin >> quiz2;
	cout << "What is the score of your third quiz?";
	cin >> quiz3;

	//process
	avg = (quiz1 + quiz2 + quiz3) / 3.0;

	//display output
	cout << endl;
	cout << "Average Score: " << avg << endl;
    cout << "Original Scores: " << quiz1 << ", " << quiz2 << ", " << quiz3 << endl;
    

	return 0;
}