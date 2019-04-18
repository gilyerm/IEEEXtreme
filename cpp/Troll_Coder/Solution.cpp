#include <iostream>
using namespace std;

int n;
int* q;

int tellTroll(char c) {
    int tCur;
    cout << c << " ";
        for (int i=0; i<n; i++) {
            cout << q[i] << " ";
        }
    cout << endl;
    cin >> tCur;
    return tCur;
}

void initQ(int n) {
    q = new int[n];
    for (int i=0; i<n; i++)
        q[i]=0;
}

int main() {
    cin >> n;
    initQ(n);

    int tPrev = 0, tCur;
    tPrev = tellTroll('Q');
    for (int i=0; i<n; i++) {
        q[i] = !q[i];
        tCur = tellTroll('Q');
        if (tCur == n)
            break;

        if (tCur <= tPrev)
            q[i] = !q[i];

        tPrev = tCur;

    }
    tellTroll('A');

    return 0;
}