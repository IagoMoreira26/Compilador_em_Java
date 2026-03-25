package com.uepb;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class TestParser {
    public static void main(String[] args) {

        String codigo = """
                    var x = 5;
                    var y;
                    y = 10;
                
                    if (x < y) {
                        print(x);
                    }
                """;

        CharStream input = CharStreams.fromString(codigo);
        LangLexer lexer = new LangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LangParser parser = new LangParser(tokens);

        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));
    }
}
