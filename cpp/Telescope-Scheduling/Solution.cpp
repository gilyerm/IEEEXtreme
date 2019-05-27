#include <iostream>
#include <algorithm>
using namespace std;

class Observation {
    public:
    int s, f, des;
}; typedef Observation Obs;

istream& operator>> (istream& in, Obs& o) {
    in >> o.s >> o.f >> o.des;  return in;
}
// ostream& operator<<(ostream& out, const Obs& o) {
//     out << '[' << o.s << ',' << o.f << ',' << o.des << ']'; return out;
// }

int maxDesirability(const Obs arr[], int n)
{
    int memo[n];
    memo[0] = arr[0].des;
    for (int i=1; i<n; i++)
    {
        const Obs& pick = arr[i];
        int withPick = pick.des;
        for (int j=i-1; j>=0; j--) // find latest non-conflicting obs.
            if (arr[j].f < pick.s) {
                withPick += memo[j];
                break;
            }
        memo[i] = max(withPick, memo[i-1]);
    }
    return memo[n-1];
}

int main() {
    int n; cin >> n;
    Obs arr[n];
    for (int i=0; i<n; i++)
        cin >> arr[i];
    auto finComp = [](const Obs& s1, const Obs& s2) {return (s1.f < s2.f);};
    sort(arr, arr+n, finComp);
    cout << maxDesirability(arr,n);
    return 0;
}
