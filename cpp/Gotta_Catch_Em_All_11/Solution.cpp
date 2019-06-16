#include <iostream>
using namespace std;

int main() {
    int r,c;
    cin >> r >> c;
    int points[r][c];
    int dp[r][c];
    for(int i=0;i<r;i++)
    {
        for(int j=0;j<c;j++)
        {
            cin >> points[i][j];
        }
    }
    dp[r-1][c-1]=1;
    for (int i = r-2; i >= 0; i--)
         dp[i][c-1] = max(dp[i+1][c-1] - points[i][c-1], 1);
    for (int j = c-2; j >= 0; j--)
         dp[r-1][j] = max(dp[r-1][j+1] - points[r-1][j], 1);
    for (int i=r-2; i>=0; i--)
    {
        for (int j=c-2; j>=0; j--)
        {
            int min_points_on_exit = min(dp[i+1][j], dp[i][j+1]);
            dp[i][j] = max(min_points_on_exit - points[i][j], 1);
        }
     }
    cout << dp[0][0];
}