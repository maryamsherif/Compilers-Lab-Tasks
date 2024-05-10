grammar GUC;

test: (ID| USERNAME| EMAIL)+;
EMAIL: USERNAME DOMAIN;
USERNAME: [a-z A-Z]+ '.' [a-zA-Z-]+;
ID: NONZERODIGIT DIGIT? '-' DIGIT DIGIT DIGIT DIGIT DIGIT? DIGIT?;
fragment DIGIT: [0-9];
fragment NONZERODIGIT: [1-9];
fragment EG: [eE] [gG] | 'eg' | 'EG' | 'eG' | 'Eg';
fragment DOMAIN options{caseInsensitive=true;}:'@student.guc.edu.eg' ;
WS: [ \t\r\n]+ -> skip;




