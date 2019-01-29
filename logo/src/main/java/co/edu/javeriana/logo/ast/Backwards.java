package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;
import co.edu.javeriana.logo.Turtle;

public class Backwards implements ASTNode {
	private ASTNode number;
	private Turtle turtle;
	

	public Backwards(ASTNode number,Turtle turtle) {
		super();
		this.number = number;
		this.turtle = turtle;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		turtle.backwards((float)number.execute(symbolTable));
		return null;
	}

}