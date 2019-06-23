#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

// https://www.geeksforgeeks.org/weighted-job-scheduling/
struct Offer {
    int from, till, profit;
};
bool compByFinish(Offer o1, Offer o2) {
    return (o1.till < o2.till);
}

// Find latest offer (sorted) not conflictint with the offer[i]
int latestNonConflict(Offer arr[], int i) {
    for (int j=i-1; j>=0; j--)
        if (arr[j].till <= arr[i].from)
            return j;
    return -1;
}

// Returns maximum possible profit from given array of offers
int findMaxProfit(Offer arr[], int n) {
    sort(arr, arr+n, compByFinish);

    // Array of subproblem-solutions =>
    //      table[i] = profit for jobs up-to arr[i] (including)
    int *table = new int[n];
    table[0] = arr[0].profit;

    // Fill entries in M[] using recursive property
    for (int i=1; i<n; i++) {
        // Find profit including the current job
        int inclProf = arr[i].profit;
        int l = latestNonConflict(arr, i);
        if (l != -1)
            inclProf += table[l];
        table[i] = max(inclProf, table[i-1]);  // max between incl/excl
    }

    int result = table[n-1];
    delete[] table;

    return result;
}

istream& operator>>(istream& in, Offer& o)
{
    in >> o.from >> o.till >> o.profit;
    return in;
}


int main()
{
    int Ts; cin >> Ts;
    for (int t=0; t<Ts; t++) {
        int N; cin >> N;
        Offer offers[N];
        for (int i=0; i<N; i++)
            cin >> offers[i];
        cout << findMaxProfit(offers, N) << endl;
    }
    return 0;
}
