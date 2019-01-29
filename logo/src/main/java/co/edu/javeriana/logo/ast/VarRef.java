package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class VarRef implements ASTNode {
	private String name;
	
	
	public VarRef(String name) {
		super();
		this.name = name;
	}

	public Object execute(SymbolTable symbolTable) {
		return symbolTable.findSymbol(name);
	}

}
