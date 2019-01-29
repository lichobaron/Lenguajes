package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class VarDecl implements ASTNode {
	private String name;
	
	
	public VarDecl(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(SymbolTable symbolTable) {
		symbolTable.putSymbol(name,null);
		return null;
	}

}
