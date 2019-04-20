#include <iostream>

using namespace std;

void swap(int* a, int* b) {
	int t = *a;
	*a = *b;
	*b = t;
}

int X,Y,n,d1,d2;

int value(int y, int x) {
	return y*X+x+n;
}

int main() {
	int tests;
	cin >> tests;
	for (int t=0; t<tests; t++) {
		cin >> X >> Y >> n >> d1 >> d2;

		int d1_x, d1_y, d2_x, d2_y;
		d1_y = (d1-n)/X;
		d1_x = (d1-n)%X;
		d2_y = (d2-n)/X;
		d2_x = (d2-n)%X;

		return 0;

		cout <<
			 "d1=" << d1 << " " <<
				"[" << d1_y << "," << d1_x << "]" << "\t\t" <<
			 "d2=" << d2 << " " <<
				"[" << d2_y << "," << d2_x << "]" << endl;

		if (d1_y > d2_y)
			swap(&d1_y, &d2_y);
		if (d1_x > d2_x)
			swap(&d1_x, &d2_x);

		int xOR = 0;
		for (int y=0; y<Y; y++) {
			for (int x=0; x<X; x++) {
				if (d1_y>=y && y<=d2_y &&
					d1_x>=x && x<=d2_x) {
						cout << value(y,x) << " ";
						continue;
					}
				xOR ^= value(y,x);
			}
		}
		cout << xOR << endl;

		// int dont_xOR = 0;
		// for (int ; h++) {
		//     for (int l=d1_l; l<=d2_l; l++) {
		//         int num = value(l,h);
		//         dont_xOR ^= num;
		//     }
		// }
		// cout << "dont_xOR: " << dont_xOR << endl;
		// cout << "result: ";
		// cout << (do_xOR ^ dont_xOR) << endl;
		// cout << endl << endl;
	}
	return 0;
}
