N, M, S = ( int(s) for s in input().split())    
import math; print(int( (S *  math.ceil( math.log( N , 2.0) ) ) + ( (N-1) * M ) ))