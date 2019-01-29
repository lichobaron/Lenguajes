grammar Logo;

@parser::header {
	import co.edu.javeriana.logo.ast.*;
	import java.util.Map;
	import java.util.HashMap;
}

@parser::members {

private Turtle turtle;
private SymbolTable symbolTable= new SymbolTable();

public LogoParser(TokenStream input, Turtle turtle) {
    this(input);
    this.turtle = turtle;
}

}

program
:
	{
		List<ASTNode> body= new ArrayList<ASTNode>();
		symbolTable.addTable();
	}
    (sentence {body.add($sentence.node);})*
  	{
  		for(ASTNode n : body){
  			n.execute(symbolTable);
  		}
  		symbolTable.popTable();
  	}
;

sentence returns [ASTNode node]
:
	color {$node= $color.node;}
    | forward {$node= $forward.node;}
    | backwards {$node= $backwards.node;}
    | right {$node= $right.node;}
    | left {$node= $left.node;}
    | writeln {$node= $writeln.node;}
    | var_decl {$node= $var_decl.node;}
    | var_assign {$node= $var_assign.node;}
    | var_mix {$node= $var_mix.node;}
    | conditional {$node= $conditional.node;}
    | bucle {$node= $bucle.node;}
;

conditional returns [ASTNode node]
:
	{
		List<ASTNode> body= new ArrayList<ASTNode>();
		List<ASTNode> elseBody= new ArrayList<ASTNode>();
	}
	IF logicOr THEN  
    (sentence {body.add($sentence.node);})*
	(ELSE
    (sentence {elseBody.add($sentence.node);})*)?
	FI SEMICOLON
	{
		$node= new Conditional($logicOr.node,body,elseBody);
	}
;

bucle returns [ASTNode node]
:
	{
		List<ASTNode> body= new ArrayList<ASTNode>();
	}
	WHILE logicOr DO
	(sentence {body.add($sentence.node);})*
	ELIHW SEMICOLON	
	{
		$node= new Bucle($logicOr.node,body);
	}
;

var_decl returns [ASTNode node]
:
	VAR ID SEMICOLON {$node = new VarDecl($ID.text);}
;

var_assign returns [ASTNode node]
:
	ID ASSING expression SEMICOLON {$node = new VarAssign($ID.text, $expression.node);}
;

var_mix returns [ASTNode node]
:
	VAR ID ASSING expression SEMICOLON {$node = new VarMix($ID.text, $expression.node);}
;


color returns [ASTNode node]
:
	{
		ASTNode[] var= new ASTNode[4];
	}
    COLOR n1= NUMBER {var[0]= new Constant(Float.parseFloat($n1.text));}
    COMMA n2= NUMBER {var[1]= new Constant(Float.parseFloat($n2.text));}
    COMMA n3= NUMBER {var[2]= new Constant(Float.parseFloat($n3.text));}
    COMMA n4= NUMBER {var[3]= new Constant(Float.parseFloat($n4.text));}
    SEMICOLON
    {$node = new Color(var[0], var[1], var[2], var[3],turtle);}
;

forward returns [ASTNode node]
:
    FORWARD NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
    SEMICOLON
    {$node = new Forward($node,turtle);}
;

backwards returns [ASTNode node]
:
    BACKWARDS NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
    SEMICOLON
    {$node = new Backwards($node,turtle);}
;

right returns [ASTNode node]
:
    RIGHT NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
    SEMICOLON
    {$node = new Right($node,turtle);}
;

left returns [ASTNode node]
:
    LEFT NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
    SEMICOLON
    {$node = new Left($node,turtle);}
;

writeln returns [ASTNode node]
:
    WRITELN expression SEMICOLON
  	{$node = new Writeln($expression.node);}
;

expression returns [ASTNode node]
:
    t1= factor{$node = $t1.node;}
    (PLUS t2= factor{$node = new Addition($node, $t2.node);}
    | MINUS t2= factor{$node = new Subtraction($node, $t2.node);}

    )*
;

logicOr returns [ASTNode node]
:
	l1= logicAnd {$node = $l1.node;}
	(OR l2= logicAnd {$node = new Or($node, $l2.node);}
	)*
;

logicAnd returns [ASTNode node]
:
	l1= comparation {$node = $l1.node;}
	(AND l2= comparation {$node = new And($node, $l2.node);}	
	)*
;

comparation returns [ASTNode node]
:
	c1= expression {$node = $c1.node;}
	(GREATHER c2= expression {$node = new Greather($node, $c2.node);}
	| GET  c2= expression {$node = new GreatherEqual($node, $c2.node);} 
	| LESS c2= expression {$node = new Less($node, $c2.node);}
	| LET c2= expression {$node = new LessEqual($node, $c2.node);}
	| EQUAL c2= expression {$node = new Equal($node, $c2.node);}
	| NEQUAL c2= expression {$node = new NEqual($node, $c2.node);}
	)*
;

factor returns [ASTNode node]
:
    t1= term {$node = $t1.node;}
    (MULT t2=term {$node = new Multiplication($node, $t2.node);}
    | DIV t2=term {$node = new Division($node, $t2.node);})*
;

term returns [ASTNode node]
:
    NUMBER {$node = new Constant(Float.parseFloat($NUMBER.text));}
    | NOT t1= term {$node = new Not($t1.node);}
    | MINUS t1= term {$node = new Multiplication(new Constant(Float.parseFloat("-1")) ,$t1.node);}
    | BRACKET_OPEN expression BRACKET_CLOSE {$node= $expression.node;}
   	| ID {$node = new VarRef($ID.text);}
   	| BOOLEAN {$node = new Bool($BOOLEAN.text);}
   	| BRACKET_OPEN logicOr BRACKET_CLOSE {$node= $logicOr.node;}
;


WS
:
    [ \t\r\n]+ -> skip
;

FORWARD: 'forward';
BACKWARDS: 'backwards';
LEFT: 'left';
RIGHT: 'right';
COLOR: 'color';

VAR: 'var';
ASSING: '=';

STRING: '"'('.')'"';
BOOLEAN: 'TRUE'|'FALSE';
IF: 'if';
FI: 'fi';
THEN: 'then';
ELSE: 'else';
WHILE: 'while';
DO: 'do';
ELIHW: 'elihw';
READ: 'read';
WRITELN: 'writeln';

BRACKET_OPEN: '(';
BRACKET_CLOSE: ')';
COLON: ':';
COMMA: ',';
SEMICOLON: ';';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

AND: 'and';
OR: 'or';
NOT: 'not';

GREATHER: '>';
LESS: '<';
GET: '>=';
LET: '<=';
EQUAL: '==';
NEQUAL: '<>';

FUN: 'fun';
NUF: 'nuf';

ID: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
NUMBER: ('0'..'9')+('.'('0'..'9')+)?;