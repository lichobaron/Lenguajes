package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class Less implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;

	
	public Less(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	public Object execute(SymbolTable symbolTable) {
		return (float)operand1.execute(symbolTable) < (float)operand2.execute(symbolTable);
	}

}
