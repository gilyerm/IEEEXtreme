#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */ 
    int t;
    cin>> t;
    for(int i=0;i<t;i++){
        long long shells,meat,rice,beans;
        cin>>shells>>meat>>rice>>beans;

        long long tacos = 
        min (shells, min (meat+rice, min (meat+beans, min(rice+beans, ((rice+meat+beans)/2)))));
        cout<<tacos<<endl;
    }
    return 0;
}