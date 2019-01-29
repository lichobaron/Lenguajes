package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;
import co.edu.javeriana.logo.Turtle;

public class Forward implements ASTNode {
	private ASTNode number;
	private Turtle turtle;
	

	public Forward(ASTNode number,Turtle turtle) {
		super();
		this.number = number;
		this.turtle= turtle;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		// TODO Auto-generated method stub
		turtle.forward((float)number.execute(symbolTable));
		return null;
	}

}