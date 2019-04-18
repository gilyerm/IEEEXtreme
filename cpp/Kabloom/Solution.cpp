#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int val (char up, char down)
{
    if (up=='R') {
        if (down == 'R') return 100;
        if (down == 'A') return 40;
        if ((down == 'K') || (down == 'Q') || (down == 'J')) return 30;
        if (down == 'T') return 20;
    return (down - '0') * 2;
    }
    if (up == 'A') return 40;
    if ((up == 'K') || (up == 'Q') || (up == 'J')) return 30;
    if (up == 'T') return 20;
    return (up - '0') * 2;
}
int clacSum(char * up,char * down,int size)
{
    int ** temp = new int * [size+1];
    for (int loop=0; loop <= size ; ++loop) temp[loop] = new int[size+1];
    for (int loop=0; loop <= size ; ++loop) (temp[loop][0]=0) || (temp[0][loop]=0);
    for (int i=1 ; i <= size ; ++i)
    for (int j=1 ; j <= size ; ++j) {
        temp[i][j]=max(temp[i-1][j],temp[i][j-1]);
        if ( up[i-1]==down[j-1] || up[i-1]=='R' || down [j-1]=='R')
            temp[i][j] = max(temp[i][j], temp[i-1][j-1]+val(up[i-1],down[j-1]));
    }
    return temp[size][size];
}


int main() {
    int cards=0;
    cin>>cards;
    char * up = new char[cards];
    char * down = new char[cards];
    while(cards!=0){
        for (int loop = 0 ; loop < cards ; ++loop)
            cin >> up[loop];
        for (int loop = 0 ; loop < cards ; ++loop)
            cin >> down[loop];
        cout << clacSum(up, down, cards) << endl;
        cin>>cards;
    }
    return 0;
}
