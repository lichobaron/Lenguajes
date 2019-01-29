package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;

public class Writeln implements ASTNode {
	private ASTNode data;
	

	public Writeln(ASTNode data) {
		super();
		this.data = data;
	}



	@Override
	public Object execute(SymbolTable symbolTable) {
		System.out.println(data.execute(symbolTable));
		return null;
	}

}
