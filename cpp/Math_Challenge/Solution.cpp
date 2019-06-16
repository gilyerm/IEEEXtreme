#include <iostream>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <vector>

#define MOD 1000000007LLU
#define MAXN 3000

#define X first
#define Y second
#define mp make_pair
#define pb push_back

using namespace std;

typedef unsigned long long llu;
typedef pair< llu, llu > pii;

llu Binom[ MAXN + 1 ][ MAXN + 1 ];


llu fact( llu a ) {
    llu factorial = 1LLU;
    for( llu i = 1; i <= a; i++ ){ 
        factorial = ( factorial * i ) % ( MOD - 1LLU );
    }
    return factorial;
}

// Function to find power 
llu power(llu x, llu y, llu p) 
{ 
    llu res = 1; // Initialize result 
    // Update x if it is more than or 
    // equal to p 
    x = x % p;  
  
    while (y > 0) { 
        // If y is odd, multiply x with the result 
        if (y & 1) {
            res *= x;
            res %= p; 
        }
        // y must be even now 
        y = y >> 1; // y = y/2 
        x *= x; 
        x %= p; 

    } 
    return res; 
} 

pii extended_euclidean( llu a, llu b ) {
  if( b == 0llu ){ 
      return mp( 1llu, 0llu );
  }
  pii A = extended_euclidean( b, a % b );
  return mp( A.Y, ( A.X - A.Y * ( a / b ) ) );
}

llu inverse( llu a, llu p ) {
  return ( extended_euclidean( a, p ).X + p ) % p;
}

void calc() {
    for( int i = 0; i <= MAXN; i++ ) {
        Binom[ i ][ 0 ] =1;
    }
    for( int i = 1; i <= MAXN; i++ ) {
        for( int j = 1; j <= i; j++ ) {
            Binom[ i ][ j ] = ( Binom[ i - 1 ][ j ] + Binom[ i - 1 ][ j - 1 ] ) % ( MOD - 1 );
        }
    }
}

void testcase(){
    llu a,b,c;
    cin>>a>>b>>c;
    llu binom;
    if( b <= 3000 && c <= 3000 ) {
        binom = Binom[ b ][ c ];
    }  else {
        // NchooseK = factorial(n)/(factorial((n-k))*(factorial(k)));
        binom = fact( b ) % ( ( MOD - 1LLU )  );
        binom = ( binom * inverse( fact( c ), ( MOD - 1LLU ) / 2LLU ) ) %  ( ( MOD - 1LLU )  );
        binom = ( binom * inverse( fact( b - c ), ( MOD - 1LLU ) / 2LLU ) ) % ( ( MOD - 1LLU ) );
    }
    cout<<power(a,binom,MOD)<<endl;
}

int main() {
    calc();
    int t;
    cin >> t;
    for(int i=0;i<t;i++){
        testcase();
    }
    return 0;
}