package com.uepb.semantic;

import com.uepb.LangBaseVisitor;
import com.uepb.LangParser;

public class SemanticAnalyzer extends LangBaseVisitor<Void> {

    private final SymbolTable symbolTable = new SymbolTable();

    // var x = 5; ou var y;
    @Override
    public Void visitVarDecl(LangParser.VarDeclContext ctx) {
        String name = ctx.ID().getText();
        symbolTable.declare(name);
        if (ctx.expr() != null) {
            visit(ctx.expr()); // verifica se a expressão é válida
        }
        return null;
    }

    // x = 10;
    @Override
    public Void visitAssign(LangParser.AssignContext ctx) {
        String name = ctx.ID().getText();
        if (!symbolTable.isDeclared(name)) {
            throw new RuntimeException("Erro semântico: variável '" + name + "' não foi declarada.");
        }
        visit(ctx.expr());
        return null;
    }

    // uso de variável dentro de expressão: x + 1
    @Override
    public Void visitExpr(LangParser.ExprContext ctx) {
        if (ctx.ID() != null) {
            String name = ctx.ID().getText();
            if (!symbolTable.isDeclared(name)) {
                throw new RuntimeException("Erro semântico: variável '" + name + "' não foi declarada.");
            }
        }
        return visitChildren(ctx);
    }

    // input(val);
    @Override
    public Void visitInputStmt(LangParser.InputStmtContext ctx) {
        String name = ctx.ID().getText();
        if (!symbolTable.isDeclared(name)) {
            throw new RuntimeException("Erro semântico: variável '" + name + "' não foi declarada.");
        }
        return null;
    }
}