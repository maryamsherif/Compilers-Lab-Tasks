package csen1002.main.task8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	String v;
	Set<String> variables = new HashSet<>();
	String t;
	Set<String> terminals = new HashSet<>();
	Set<String> rules = new LinkedHashSet<>();
	Set<String> firsts = new LinkedHashSet<>();
	Set<String> follows = new LinkedHashSet<>();
	Map<String,Set<String>> rulesMap =new LinkedHashMap<>();
	Map<String,Set<String>> firstMap =new LinkedHashMap<>();
	Map<String,Set<String>> followMap =new LinkedHashMap<>();
	
	Map<String, Map<String, Set<String>>> parseTable = new LinkedHashMap<>();
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		 String[] inp = input.split("#");
	        v = inp[0];
	        variables = new LinkedHashSet<>(Arrays.asList(v.split(";"))); 
	        t = inp[1];
	        terminals = Set.of(t.split(";"));
	        String r = inp[2];
	        rules = Set.of(r.split(";"));

	        for (String key : variables) {
	            for (String s : rules) {
	                String[] splitInput = s.split("/");
	                String vVariable = splitInput[0];
	                if (vVariable.equals(key)) {
	                    Set<String> sortedStrings = new LinkedHashSet<>(Arrays.asList(splitInput[1].split(",")));
	                    rulesMap.put(vVariable, sortedStrings);
	                    break;
	                }
	            }
	        }
	        
	        String fir = inp[3];
	        firsts = Set.of(fir.split(";"));

	        for (String key : variables) {
	            for (String s : firsts) {
	                String[] splitInput = s.split("/");
	                String vVariable = splitInput[0];
	                if (vVariable.equals(key)) {
	                    Set<String> sortedStrings = new LinkedHashSet<>(Arrays.asList(splitInput[1].split(",")));
	                    firstMap.put(vVariable, sortedStrings);
	                    break;
	                }
	            }
	        }
	        
	        String fol = inp[4];
	        follows = Set.of(fol.split(";"));

	        for (String key : variables) {
	            for (String s : follows) {
	                String[] splitInput = s.split("/");
	                String vVariable = splitInput[0];
	                if (vVariable.equals(key)) {
	                    Set<String> sortedStrings = new LinkedHashSet<>();
	                    for (char c : splitInput[1].toCharArray()) {
	                        sortedStrings.add(String.valueOf(c));
	                    }
	                    followMap.put(vVariable, sortedStrings);
	                    break;
	                }
	            }
	        }

	        constructParseTable();
	}
	


	public void constructParseTable() {
	    for (String nonTerminal : variables) {
	        parseTable.put(nonTerminal, new LinkedHashMap<>());
	        Set<String> productions = rulesMap.get(nonTerminal);

	        for (String production : productions) {
	            if (!production.equals("e")) {
	                String firstSymbol = production.substring(0, 1);
	                if (terminals.contains(firstSymbol)) { 
	                    parseTable.get(nonTerminal).putIfAbsent(firstSymbol, new HashSet<>());
	                    parseTable.get(nonTerminal).get(firstSymbol).add(production);
	                } else {
	                    Set<String> firstsOfProduction = firstMap.get(firstSymbol);
	                    for (String terminal : firstsOfProduction) {
	                        if (!terminal.equals("e")) {
	                            parseTable.get(nonTerminal).putIfAbsent(terminal, new HashSet<>());
	                            parseTable.get(nonTerminal).get(terminal).add(production);
	                        }
	                    }
	                }
	            }
	        }

	        if (productions.contains("e")) {
	            Set<String> followsOfNonTerminal = followMap.get(nonTerminal);
	            for (String followTerminal : followsOfNonTerminal) {
	                parseTable.get(nonTerminal).putIfAbsent(followTerminal, new HashSet<>());
	                parseTable.get(nonTerminal).get(followTerminal).add("e");
	            }
	        }
	    }
	}




	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */

	public String parse(String input) {
	    String inputTemp=input;
	    input += "$"; 
	    Stack<String> stack = new Stack<>();
	    List<String> derivation = new ArrayList<>();
	    stack.push("$");
	    stack.push("S");
	    derivation.add("S");

	    String finalOutput="";
	    int i = 0;
	    while (!stack.isEmpty() && i < input.length()) {
	        String stackTop = stack.peek();
	        String currentInput = String.valueOf(input.charAt(i));

	        if (terminals.contains(stackTop) || "$".equals(stackTop)) {
	            if (stackTop.equals(currentInput)) {
	                stack.pop();
	                i++;
	            } else {
	            	finalOutput= derivation.stream().map(s -> s.replace("e", "")).collect(Collectors.joining(";"));
	            	 String lastPart = finalOutput.substring(finalOutput.lastIndexOf(';') + 1);
	     	        if (lastPart.equals(inputTemp)) {
	     	            return finalOutput;
	     	        } else {
	     	          return  finalOutput += ";ERROR";
	     	           
	     	        }
	            }
	        } else if (variables.contains(stackTop)) {
	            Map<String, Set<String>> rules = parseTable.get(stackTop);
	            if (rules != null && rules.containsKey(currentInput)) {
	                Set<String> productions = rules.get(currentInput);
	                if (productions != null && !productions.isEmpty()) {
	                    String production = productions.iterator().next(); 
	                    stack.pop();
	                    if (!production.equals("e")) {
	                        List<String> symbols = new ArrayList<>(Arrays.asList(production.split("")));
	                        Collections.reverse(symbols);
	                        symbols.forEach(symbol -> {
	                            if (!symbol.equals("e")) {
	                                stack.push(symbol);
	                            }
	                        });
	                    }

	                    String currentForm = derivation.get(derivation.size() - 1);
	                    String updatedForm = currentForm.replaceFirst(stackTop, production);
	                    derivation.add(updatedForm);
	                } else {
	                	finalOutput= derivation.stream().map(s -> s.replace("e", "")).collect(Collectors.joining(";"));
	                	 String lastPart = finalOutput.substring(finalOutput.lastIndexOf(';') + 1);
	         	        if (lastPart.equals(inputTemp)) {
	         	        	 return finalOutput;
	         	        } else {
	         	           return finalOutput += ";ERROR";
	         	           
	         	        }
	                }
	            } else {
	            	finalOutput= derivation.stream().map(s -> s.replace("e", "")).collect(Collectors.joining(";"));
	            	 String lastPart = finalOutput.substring(finalOutput.lastIndexOf(';') + 1);
	     	        if (lastPart.equals(inputTemp)) {
	     	        	 return finalOutput;
	     	        } else {
	     	            return finalOutput += ";ERROR";
	     	           
	     	        }

	            }
	        }
	    }

	    if (stack.size() == 1 && stack.peek().equals("$")) {
	        finalOutput= derivation.stream().map(s -> s.replace("e", "")).collect(Collectors.joining(";"));
	        String lastPart = finalOutput.substring(finalOutput.lastIndexOf(';') + 1);
	        if (lastPart.equals(inputTemp)) {
	            return finalOutput;
	        } else {
	           return finalOutput += ";ERROR";
	           
	        }

	    } else {
	        finalOutput= derivation.stream().map(s -> s.replace("e", "")).collect(Collectors.joining(";"));
	        String lastPart = finalOutput.substring(finalOutput.lastIndexOf(';') + 1);
	        if (lastPart.equals(inputTemp)) {
	            return finalOutput;
	        } else {
	         return   finalOutput += ";ERROR";
	           
	        }
	    }

	}

}


