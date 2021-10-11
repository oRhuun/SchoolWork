//guess the number game
#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

int main()
{
	int randomN, guess, low, high, tries = 0;
    
    cout << "What is the highest number you want to guess through?";
    cin >> high;
    
    srand(time(0));
    randomN = 1 + (rand() % high);
    
    cout << "Guess the Number(1-" << high << ")\n";
    cin >> guess;
          tries++;
    
    while(guess != randomN)
    {
        while(guess > (high) || guess < 1)
        {
            cout << "Number is not within the range, guess again.\n";
            cin >> guess;
        }
        
            if(guess > randomN)
            {
            cout << "Lower!\n";
                tries++;
            cin >> guess;
            }
        
            else
            {
            cout << "Higher!\n";
                tries++;
            cin >> guess;

            }
    }
        cout << "Congratulations! The number was " << randomN << endl;
        cout << "It took you " << tries << " tries to guess the number!\n";
	
    return 0;
}
/*
Things to do for this:
make lowest number to guess through
make play again function
 */