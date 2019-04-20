#include <iostream>
using namespace std;

typedef long long my_t;

my_t xOR_Range(my_t n, my_t len) {
    // cout << "Range~ " << n << "," << len << endl;
    if (n%2) {  // odd
        my_t skip = (len/4-1)*4 + n + 1;
        my_t xOR = n;
        // cout<<"skip"<<skip<<endl;
        for (my_t i=skip; i<=n+len-1; i++)
            xOR ^= i;
        //cout << "\t   /" << "n_Odd:" << xOR << "/";
        return xOR;
    }
    else {    // even
        my_t last = len+n-1;
        //cout<<"\tlast="<<last;
        if (last%2) { // last is odd
            return (last%4 == (n+1)%4) ? 1 : 0;
            // int xOR = (((last-n)%4+1)/2)%2;
            // cout << "\t /" << "nE_lstODD:" << xOR << "/";
            // return xOR;
        }
        else {  // last is even
            my_t xOR = last + ((last-1)%4 == (n+1)%4);
                // *((n/4)%2);
            // cout << "\t(" << "2last:" << sec2last
            //     << "," << (sec2last%4 == 1) << "/";
            return xOR;
        }
    }
}

// int t_first = 0;
// int t_last = 120;

void myTest(my_t t_first, my_t t_last)
{
    my_t xOR = 0;
    my_t n = t_first;

    bool foundErr = false;
    my_t total = t_last - t_first, lastPerc=-1;
    my_t showEvery = total / 10000;
    my_t loop = t_first % showEvery;
    int count = 0;

    for (my_t i=n; i<=t_last; i++) {
        xOR ^= i;
        // cout << i << ": " << xOR;
        my_t calc = xOR_Range(n, i-n+1);
        if (xOR!=calc) {
            cout << i << ": " << xOR;
            cout << "\t  {" << calc << "}\t" <<
                ((xOR==calc)?"O":"X");
            if (n%2 == 1 && xOR == n) cout << "\t <<<<<<< " << n;
            if (n%2 == 0 && (xOR == 0 || xOR == 1))
                cout << "\t ~~~~" << xOR;
            cout << endl;
            foundErr = true;
        }
        if (i%showEvery == loop) {
            my_t curPerc = (i-t_first) / (total/100);
            if (count >= 160) {
                cout << endl; count = 0;
            }
            if (lastPerc < curPerc) {
                lastPerc = curPerc; count+=6;
                cout << "\t" << curPerc << "% ";
            }
            cout << '.' << flush; count++;
        }
    }
    cout << "Done check for {n=" << n << ",to=" << t_last << "} "
        << (foundErr ? "WITH (above) " : "with NO ") << "errors.";
    cout << endl;
}


int main()
{
    my_t first_n = 10000000;
    my_t tests = first_n + 2;
    my_t test_range = 1000000000000;
    cout << "start tests" << endl;
    for (int i=first_n; i<tests; i++) {
        myTest(i, i+test_range);
    }
    cout << "finished tests" << endl;
    //cout << xOR_Range(16,17);
    return 0;
}

