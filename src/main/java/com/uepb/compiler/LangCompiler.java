package com.uepb.compiler;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.uepb.LangLexer;
import com.uepb.LangParser;
import com.uepb.gui.GuiVizualizerTask;
import com.uepb.interfaces.CompilerEngine;
import com.uepb.pcode.PCodeGenerator;
import com.uepb.pcode.PCodeMachine;
import com.uepb.semantic.SemanticAnalyzer;

public class LangCompiler implements CompilerEngine {

    @Override
    public void execute(File input, File output, boolean verbose) throws IOException {
        // 1. Análise Léxica
        var charStream = CharStreams.fromPath(input.toPath());
        var lexer = new LangLexer(charStream);
        var tokens = new CommonTokenStream(lexer);

        // 2. Análise Sintática
        var parser = new LangParser(tokens);
        var tree = parser.program();

        // 3. Exibe árvore sintática (se -v true)
        if (verbose) {
            var guiTask = new GuiVizualizerTask(parser, tree);
            guiTask.run();
        }

        // 4. Análise Semântica
        var semantic = new SemanticAnalyzer();
        semantic.visit(tree);

        // 5. Geração de P-Code e execução
        var machine = new PCodeMachine();
        var generator = new PCodeGenerator(machine);
        generator.visit(tree);
        machine.addInstruction("HALT");
        machine.execute();
    }
}