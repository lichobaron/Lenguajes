package co.edu.javeriana.logo.ast;


import co.edu.javeriana.logo.SymbolTable;

public class Bool implements ASTNode {
	
	private Object value;
	
	

	public Bool(Object value) {
		super();
		this.value = value;
	}


	@Override
	public Object execute(SymbolTable symbolTable) {
		if(value.equals("TRUE")){
			value=true;
		}
		if(value.equals("FALSE")){
			value=true;
		}
		return value;
	}

}
