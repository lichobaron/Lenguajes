package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.SymbolTable;

public class Bucle implements ASTNode{
	private ASTNode condition;
	private List<ASTNode> body;
	
	public Bucle(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}
	
	public Object execute(SymbolTable symbolTable) {
		symbolTable.addTable();
		while((boolean)condition.execute(symbolTable)){
			for(ASTNode n: body){
				n.execute(symbolTable);
			}
		}
		symbolTable.popTable();
		return null;
	}
}
