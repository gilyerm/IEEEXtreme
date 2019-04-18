#include <iostream>
using namespace std;

// A0 digit loops after 60 first fib numbers!
int arr[] = {
    1, 1, 2, 3, 5, 8, 3, 1, 4, 5,
    9, 4, 3, 7, 0, 7, 7, 4, 1, 5,
    6, 1, 7, 8, 5, 3, 8, 1, 9, 0,
    9, 9, 8, 7, 5, 2, 7, 9, 6, 5,
    1, 6, 7, 3, 0, 3, 3, 6, 9, 5,
    4, 9, 3, 2, 5, 7, 2, 9, 1
};

int main() {
    int cases;
    cin >> cases;
    long m;
    for (int i=0; i<cases; i++) {
        cin >> m;
        cout << arr[m%60] << endl;
    }
    return 0;
}