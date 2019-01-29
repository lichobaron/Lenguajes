package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class VarMix implements ASTNode {

	private String name;
	private ASTNode expression;
	
	
	
	public VarMix(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		symbolTable.putSymbol(name, expression.execute(symbolTable));
		return null;
	}

}
