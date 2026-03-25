// Generated from C:/Users/iagom/Downloads/Compilador_em_Java/src/main/antlr4/com/uepb/Lang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(LangParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(LangParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(LangParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(LangParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(LangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(LangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(LangParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(LangParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(LangParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(LangParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void enterInputStmt(LangParser.InputStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void exitInputStmt(LangParser.InputStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(LangParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(LangParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(LangParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(LangParser.CompOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LangParser.ExprContext ctx);
}