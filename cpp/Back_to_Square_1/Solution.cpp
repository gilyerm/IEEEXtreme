#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int n;
    cin >> n;
    while (n != 0) {
        double probs[n-1];
        for (int i=0; i<n-1; i++) {
            cin >> probs[i];
        }
        double mean = 0.0;
        double step = 1.0;
        for (int i=n-2; i>=0; i--) {
            mean += step;
            step *= 1.0 / probs[i];
        }
        mean += step;
        cout << lround(mean) << endl;
        cin >> n;
    }
    return 0;
}
