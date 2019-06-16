#include <iostream>
#include <cmath>
using namespace std;

int main() {
    unsigned long long a, b;
    cin >> a >> b;
    unsigned long long d=((unsigned long long)ceil((pow(2,b))/a));
    cout << d;
    return 0;
}
