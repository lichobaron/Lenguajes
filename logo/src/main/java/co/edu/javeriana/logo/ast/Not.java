package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class Not implements ASTNode {
	private ASTNode operand1;

	
	public Not(ASTNode operand1) {
		super();
		this.operand1 = operand1;
	}

	public Object execute(SymbolTable symbolTable) {
		return !(boolean)operand1.execute(symbolTable);
	}

}
