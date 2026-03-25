package com.uepb.compiler;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.uepb.LangLexer;
import com.uepb.LangParser;
import com.uepb.gui.GuiVizualizerTask;
import com.uepb.interfaces.CompilerEngine;

public class Antlr4BasicExample implements CompilerEngine{

    @Override
    public void execute(File input, File output, boolean verbose) throws IOException {
        var charStream = CharStreams.fromPath(input.toPath());
        var lexer = new LangLexer(charStream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new LangParser(tokens);
        var tree = parser.program();

        if(verbose){
            var guiTask = new GuiVizualizerTask(parser, tree);
            guiTask.run();
        }
    }

}
