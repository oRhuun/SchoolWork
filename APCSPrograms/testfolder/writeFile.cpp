//write to a document
#include<iostream>
#include<fstream>
using namespace std;

int main()
{
	ofstream outputFile;
	outputFile.open("Sample.txt");
	
	cout << "Now writing to the file\n";
	
	outputFile << "Chris is cool\n";
	outputFile << "so is Steve\n";
	outputFile << "^these are true^";
	
	cout << "Done writing to file\n";
	
	outputFile.close();
	
	return 0;
}