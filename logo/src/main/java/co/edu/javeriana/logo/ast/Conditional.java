package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.logo.SymbolTable;

public class Conditional implements ASTNode{
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public Conditional(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}
	
	public Object execute(SymbolTable symbolTable) {
		if((boolean)condition.execute(symbolTable)){
			symbolTable.addTable();
			for(ASTNode n: body){
				n.execute(symbolTable);
			}
			symbolTable.popTable();
		}
		else{
			symbolTable.addTable();
			for(ASTNode n: elseBody){
				n.execute(symbolTable);
			}
			symbolTable.popTable();
		}
		return null;
	}
}
