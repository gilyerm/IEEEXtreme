#include <iostream>
#include <string.h>
#include <set>
using namespace std;

typedef unsigned long type;
type* team;
int N;
bool checkGoalTeam(type G);

int main() {
    cin >> N;
    team = new type[N];
    // memset(combinations, 0, sizeof(combinations));
    for (int i=0; i<N; i++) {
        type p; cin >> p;
        team[i] = p;
    }

    int q; cin >> q;
    for (int i=0; i<q; i++) {
        type g; cin >> g;
        cout << (checkGoalTeam(g) ? "YES" : "NO") << endl;
    }

    delete(team);
}

bool checkGoalTeam(type mask) {
    type curOr = 0;
    for (int i=0; i<N; i++)
        if ((mask & team[i]) == team[i])
            curOr |= team[i];
    return (curOr == mask);
}
