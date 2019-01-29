package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public interface ASTNode {
	
	public Object execute(SymbolTable symbolTable);
}
