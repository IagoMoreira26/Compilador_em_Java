package com.uepb.semantic;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, Double> symbols = new HashMap<>();

    public void declare(String name) {
        if (symbols.containsKey(name)) {
            throw new RuntimeException("Erro semântico: variável '" + name + "' já foi declarada.");
        }
        symbols.put(name, null);
    }

    public void assign(String name, Double value) {
        if (!symbols.containsKey(name)) {
            throw new RuntimeException("Erro semântico: variável '" + name + "' não foi declarada.");
        }
        symbols.put(name, value);
    }

    public boolean isDeclared(String name) {
        return symbols.containsKey(name);
    }

    public Double getValue(String name) {
        return symbols.get(name);
    }
}