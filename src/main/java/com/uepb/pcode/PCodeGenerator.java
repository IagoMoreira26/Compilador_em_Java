package com.uepb.pcode;

import com.uepb.LangBaseVisitor;
import com.uepb.LangParser;

public class PCodeGenerator extends LangBaseVisitor<Void> {

    private final PCodeMachine machine;

    public PCodeGenerator(PCodeMachine machine) {
        this.machine = machine;
    }

    // var x = 5; ou var y;
    @Override
    public Void visitVarDecl(LangParser.VarDeclContext ctx) {
        if (ctx.expr() != null) {
            visit(ctx.expr());
            machine.addInstruction("STO", ctx.ID().getText());
        } else {
            machine.addInstruction("LIT", "0");
            machine.addInstruction("STO", ctx.ID().getText());
        }
        return null;
    }

    // x = 10;
    @Override
    public Void visitAssign(LangParser.AssignContext ctx) {
        visit(ctx.expr());
        machine.addInstruction("STO", ctx.ID().getText());
        return null;
    }

    // if (cond) { block }
    @Override
    public Void visitIfStmt(LangParser.IfStmtContext ctx) {
        visit(ctx.cond());

        int jzIndex = machine.getNextIndex();
        machine.addInstruction("JZ", "0"); // placeholder

        visit(ctx.block());

        machine.setInstruction(jzIndex, "JZ", String.valueOf(machine.getNextIndex()));
        return null;
    }

    // while (cond) { block }
    @Override
    public Void visitWhileStmt(LangParser.WhileStmtContext ctx) {
        int loopStart = machine.getNextIndex();

        visit(ctx.cond());

        int jzIndex = machine.getNextIndex();
        machine.addInstruction("JZ", "0"); // placeholder

        visit(ctx.block());

        machine.addInstruction("JMP", String.valueOf(loopStart));
        machine.setInstruction(jzIndex, "JZ", String.valueOf(machine.getNextIndex()));
        return null;
    }

    // print(x); ou print("texto");
    @Override
    public Void visitPrintStmt(LangParser.PrintStmtContext ctx) {
        if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            text = text.substring(1, text.length() - 1); // remove aspas
            machine.addInstruction("PRINT_STR", text);
        } else {
            visit(ctx.expr());
            machine.addInstruction("PRINT");
        }
        return null;
    }

    // input(val);
    @Override
    public Void visitInputStmt(LangParser.InputStmtContext ctx) {
        machine.addInstruction("INPUT", ctx.ID().getText());
        return null;
    }

    // expressões aritméticas
    @Override
    public Void visitExpr(LangParser.ExprContext ctx) {
        if (ctx.NUMBER() != null) {
            machine.addInstruction("LIT", ctx.NUMBER().getText());
        } else if (ctx.ID() != null) {
            machine.addInstruction("LOD", ctx.ID().getText());
        } else if (ctx.expr().size() == 2) {
            visit(ctx.expr(0));
            visit(ctx.expr(1));
            String op = ctx.getChild(1).getText();
            switch (op) {
                case "+" -> machine.addInstruction("ADD");
                case "-" -> machine.addInstruction("SUB");
                case "*" -> machine.addInstruction("MUL");
                case "/" -> machine.addInstruction("DIV");
                case "^" -> machine.addInstruction("PWR");
            }
        } else {
            visitChildren(ctx);
        }
        return null;
    }

    // condições
    @Override
    public Void visitCond(LangParser.CondContext ctx) {
        if (ctx.getText().equals("true")) {
            machine.addInstruction("LIT", "1");
        } else if (ctx.getText().equals("false")) {
            machine.addInstruction("LIT", "0");
        } else if (ctx.cond().size() == 2) {
            visit(ctx.cond(0));
            visit(ctx.cond(1));
            String op = ctx.getChild(1).getText();
            switch (op) {
                case "and" -> machine.addInstruction("AND");
                case "or"  -> machine.addInstruction("OR");
            }
        } else if (ctx.expr().size() == 2) {
            visit(ctx.expr(0));
            visit(ctx.expr(1));
            String op = ctx.compOp().getText();
            switch (op) {
                case "<"  -> machine.addInstruction("LT");
                case ">"  -> machine.addInstruction("GT");
                case "==" -> machine.addInstruction("EQ");
                case "!=" -> machine.addInstruction("NEQ");
            }
        } else {
            visitChildren(ctx);
        }
        return null;
    }
}