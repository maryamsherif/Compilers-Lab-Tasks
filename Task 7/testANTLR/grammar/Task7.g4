/**
 * Write your info here
 *
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

grammar Task7;

test: (ZERO | ONE | ERROR )+ EOF;
ZERO: '001' | '010' | '100' | '101' | '110';
ONE: '000' | '111' | '011';
ERROR: '00' | '01' | '10' | '11' | '0' | '1';
WS: [ \t\r\n]+ -> skip;


