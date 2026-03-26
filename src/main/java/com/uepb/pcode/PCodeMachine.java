package com.uepb.pcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PCodeMachine {

    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> memory = new HashMap<>();
    private final List<String[]> instructions = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int pc = 0; // program counter

    public void addInstruction(String... parts) {
        instructions.add(parts);
    }

    public void execute() {
        pc = 0;
        while (pc < instructions.size()) {
            String[] instr = instructions.get(pc);
            String op = instr[0];

            switch (op) {
                case "LIT" -> stack.push(Double.parseDouble(instr[1]));
                case "LOD" -> stack.push(memory.getOrDefault(instr[1], 0.0));
                case "STO" -> memory.put(instr[1], stack.pop());
                case "ADD" -> { double b = stack.pop(); double a = stack.pop(); stack.push(a + b); }
                case "SUB" -> { double b = stack.pop(); double a = stack.pop(); stack.push(a - b); }
                case "MUL" -> { double b = stack.pop(); double a = stack.pop(); stack.push(a * b); }
                case "DIV" -> { double b = stack.pop(); double a = stack.pop(); stack.push(a / b); }
                case "PWR" -> { double b = stack.pop(); double a = stack.pop(); stack.push(Math.pow(a, b)); }
                case "LT"  -> { double b = stack.pop(); double a = stack.pop(); stack.push(a < b ? 1.0 : 0.0); }
                case "GT"  -> { double b = stack.pop(); double a = stack.pop(); stack.push(a > b ? 1.0 : 0.0); }
                case "EQ"  -> { double b = stack.pop(); double a = stack.pop(); stack.push(a == b ? 1.0 : 0.0); }
                case "NEQ" -> { double b = stack.pop(); double a = stack.pop(); stack.push(a != b ? 1.0 : 0.0); }
                case "AND" -> { double b = stack.pop(); double a = stack.pop(); stack.push((a != 0 && b != 0) ? 1.0 : 0.0); }
                case "OR"  -> { double b = stack.pop(); double a = stack.pop(); stack.push((a != 0 || b != 0) ? 1.0 : 0.0); }
                case "JMP" -> { pc = Integer.parseInt(instr[1]); continue; }
                case "JZ"  -> { if (stack.pop() == 0.0) { pc = Integer.parseInt(instr[1]); continue; } }
                case "PRINT" -> {
                    double val = stack.pop();
                    if (val == Math.floor(val)) System.out.println((int) val);
                    else System.out.println(val);
                }
                case "PRINT_STR" -> System.out.println(instr[1]);
                case "INPUT" -> {
                    System.out.print("Digite um valor: ");
                    memory.put(instr[1], scanner.nextDouble());
                }
                case "HALT" -> { return; }
                default -> throw new RuntimeException("Instrução desconhecida: " + op);
            }
            pc++;
        }
    }

    public int getNextIndex() {
        return instructions.size();
    }

    public void setInstruction(int index, String... parts) {
        instructions.set(index, parts);
    }
}