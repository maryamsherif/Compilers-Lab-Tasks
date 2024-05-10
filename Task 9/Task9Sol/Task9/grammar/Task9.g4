/**
 * Write your info here
 *
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

grammar Task9;



@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

//S -> F
s returns [int check]: f {$check = $f.check * $f.m;};
//F -> DT
f returns [int check, int m]: d[1,1] t[2,$d.l] { $check=$d.check*$t.check; $m=$t.m;};
//T -> #N   //T -> epsilon
t[int r, int l] returns [int check, int m]: HASH n[$r,1,$l] {$check=$n.check; $m=$n.m;} | {$check=1; $m=1;};
//N -> DT
n[int r, int c, int l] returns [int check, int m]: d[$r,1] t[$r+1, $l] {$check=$d.check * $t.check;  $m = equals($d.l, $l) * $t.m;};

//D -> 0D1 //D -> 1D1 //D -> 0 //D -> 1
d[int r, int c] returns [int check, int l]:
ZERO d1=d[r,c+1] {$l=$d1.l; $check=(1-equals(c,r))* $d1.check;}
| ONE d1=d[r,c+1] {$l=$d1.l; $check=equals(c,r)* $d1.check;}
| ZERO {$l=c; $check=1-equals(c,r);}
| ONE {$l=c; $check=equals(c,r);};

ZERO : '0';
ONE : '1';
HASH : '#';