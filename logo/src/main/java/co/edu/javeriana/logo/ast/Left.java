package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;
import co.edu.javeriana.logo.Turtle;

public class Left implements ASTNode {
	private ASTNode number;
	private Turtle turtle;
	

	public Left(ASTNode number,Turtle turtle) {
		super();
		this.number = number;
		this.turtle= turtle;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		turtle.left((float)number.execute(symbolTable));
		return null;
	}

}
