package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;
import co.edu.javeriana.logo.Turtle;

public class Color implements ASTNode {
	private ASTNode number1;
	private ASTNode number2;
	private ASTNode number3;
	private ASTNode number4;
	private Turtle turtle;
	
	
	
	public Color(ASTNode number1, ASTNode number2, ASTNode number3, ASTNode number4, Turtle turtle) {
		super();
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.number4 = number4;
		this.turtle = turtle;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		// TODO Auto-generated method stub
		turtle.color((float) number1.execute(symbolTable), (float) number2.execute(symbolTable), (float) number3.execute(symbolTable), (float) number4.execute(symbolTable));
		return null;
	}

}
