grammar Lang;

program: statement* EOF;

statement
    : varDecl
    | assign
    | ifStmt
    | whileStmt
    | printStmt
    | inputStmt
    ;

varDecl: 'var' ID ('=' expr)? ';';
assign: ID '=' expr ';';

ifStmt: 'if' '(' cond ')' block;
whileStmt: 'while' '(' cond ')' block;

block: '{' statement* '}';

printStmt: 'print' '(' (expr | STRING) ')' ';';
inputStmt: 'input' '(' ID ')' ';';

cond
    : cond 'or' cond
    | cond 'and' cond
    | expr compOp expr
    | 'true'
    | 'false'
    | '(' cond ')'
    ;

compOp: '<' | '>' | '==' | '!=';

expr
    : expr '^' expr
    | expr '*' expr
    | expr '/' expr
    | expr '+' expr
    | expr '-' expr
    | NUMBER
    | ID
    | '(' expr ')'
    ;

ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '"' .*? '"';

WS: [ \t\r\n]+ -> skip;

COMMENT: '//' ~[\r\n]* -> skip;