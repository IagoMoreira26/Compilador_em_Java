package com.uepb.semantic;

import com.uepb.LangLexer;
import com.uepb.LangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class SemanticTest {

    static void test(String descricao, String codigo, boolean devePassar) {
        try {
            var lexer = new LangLexer(CharStreams.fromString(codigo));
            var tokens = new CommonTokenStream(lexer);
            var parser = new LangParser(tokens);
            var tree = parser.program();

            var analyzer = new SemanticAnalyzer();
            analyzer.visit(tree);

            if (devePassar) {
                System.out.println("✅ PASSOU: " + descricao);
            } else {
                System.out.println("❌ DEVERIA TER FALHADO: " + descricao);
            }
        } catch (RuntimeException e) {
            if (!devePassar) {
                System.out.println("✅ ERRO ESPERADO: " + descricao + " → " + e.getMessage());
            } else {
                System.out.println("❌ FALHOU: " + descricao + " → " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // ✅ casos válidos
        test("Declaração simples",
                "var x;", true);

        test("Declaração com atribuição",
                "var x = 5;", true);

        test("Atribuição após declaração",
                "var x; x = 10;", true);

        test("Expressão aritmética válida",
                "var x = 5; var y = 10; var z; z = x + y;", true);

        test("If válido",
                "var x = 5; if (x < 10) { print(x); }", true);

        test("While válido",
                "var x = 0; while (x < 5) { x = x + 1; }", true);

        test("Input válido",
                "var x; input(x);", true);

        test("Print com string",
                "print(\"olá\");", true);

        // ❌ casos inválidos
        test("Usar variável não declarada",
                "y = 10;", false);

        test("Declarar variável duas vezes",
                "var x; var x;", false);

        test("Usar variável não declarada em expressão",
                "var x; x = y + 1;", false);

        test("Input em variável não declarada",
                "input(val);", false);
    }
}