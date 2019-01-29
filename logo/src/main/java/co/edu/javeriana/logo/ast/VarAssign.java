package co.edu.javeriana.logo.ast;

import co.edu.javeriana.logo.SymbolTable;

public class VarAssign implements ASTNode {

	private String name;
	private ASTNode expression;
	
	
	
	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		if(symbolTable.findSymbol(name)!=null){
			symbolTable.putSymbol(name, expression.execute(symbolTable));
		}
		else{
			System.out.println(name+" no esta declarada.");
		}
		return null;
	}

}
