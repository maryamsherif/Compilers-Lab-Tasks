package csen1002.main.task6;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

public class CfgFirstFollow {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	
	String v;
	Set<String> variables = new HashSet<>();
	String t;
	Set<String> terminals = new HashSet<>();
	Set<String> rules = new LinkedHashSet<>();
	Map<String,Set<String>> rulesMap =new LinkedHashMap<>();
	Map<String,Set<String>> firstMap =new LinkedHashMap<>();
	Map<String,Set<String>> followMap =new LinkedHashMap<>();
	
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		 String[] input = cfg.split("#");
	        v = input[0];
	        variables = new LinkedHashSet<>(Arrays.asList(v.split(";"))); // Use LinkedHashSet to preserve order
	        t = input[1];
	        terminals = Set.of(t.split(";"));
	        String r = input[2];
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
	            firstMap.put(key, Set.of("0"));
	            if(key.equals("S")) {
	            	followMap.put(key, Set.of("$"));
	            }
	            else {
	            followMap.put(key, Set.of("0"));
	            }
	        }
	        
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		boolean change=true;
		
		while(change) {
			change=false;
			
			for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
				
	            Set<String> originalFirstSet = firstMap.get(entry.getKey());
	            Set<String> updatedFirstSet = new HashSet<>(originalFirstSet); 

	            for (String production : entry.getValue()) {
	                boolean endsWithEpsilon = true;  
	                for (int i = 0; i < production.length() && endsWithEpsilon; i++) {
	                    String symbol = String.valueOf(production.charAt(i));
	                    Set<String> firstOfSymbol = firstMap.getOrDefault(symbol, Collections.singleton(symbol)); 
	                    endsWithEpsilon = firstOfSymbol.contains("e"); 

	                    Set<String> tempFirst = new HashSet<>(firstOfSymbol);
	                    tempFirst.remove("e");  
	                    updatedFirstSet.addAll(tempFirst);

	                    if (endsWithEpsilon && i == production.length() - 1) {
	                        updatedFirstSet.add("e"); 
						}
					}
					
				}
				if (!updatedFirstSet.equals(originalFirstSet)) {
					change=true;
	                firstMap.put(entry.getKey(), updatedFirstSet);
	            }
				
			}
		}
		
		return firstToString();
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
	    this.first(); 
	    boolean change = true;


	    while (change) {
	        change = false;
	        for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
	            String k = entry.getKey(); 

	            for (String production : entry.getValue()) {
	            	
	                for (int i = 0; i < production.length(); i++) {
	                    char character = production.charAt(i);
	                    String var = String.valueOf(character);
	                    
	                    if (variables.contains(var)) { 
	                        Set<String> followOfCurrent = new HashSet<>(followMap.getOrDefault(var, new HashSet<>()));

	                        if (i + 1 < production.length()) {
	                            String bbb = production.substring(i + 1);
	                            Set<String> firstOfB = getFirstOfSequence(bbb);

	                            boolean epsilonPresent = firstOfB.remove("e");
	                            followOfCurrent.addAll(firstOfB);

	                            if (epsilonPresent) {
	                                followOfCurrent.addAll(followMap.get(k));
	                            }
	                        } else {
	                            followOfCurrent.addAll(followMap.get(k));
	                        }

	                        if (i + 1 < production.length() && terminals.contains(String.valueOf(production.charAt(i + 1)))) {
	                            followOfCurrent.add(String.valueOf(production.charAt(i + 1)));
	                        }

	                        if (!followOfCurrent.equals(followMap.get(var))) {
	                            followMap.put(var, followOfCurrent);
	                            change = true;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return followToString();
	}




	private Set<String> getFirstOfSequence(String sequence) {
	    Set<String> firstSet = new HashSet<>();
	    for (int i = 0; i < sequence.length(); i++) {
	        char z = sequence.charAt(i);
	        Set<String> firstOfV = firstMap.get(String.valueOf(z)); 
	        if (firstOfV == null) {
	        	firstOfV = new HashSet<>(); 
	        }
	        firstSet.addAll(firstOfV);
	        if (!firstOfV.contains("e")) {
	            firstSet.remove("e");
	            break;
	        }
	    }
	    return firstSet;
	}

	
	public String firstToString() {
		    StringBuilder sb = new StringBuilder();
		    for (Map.Entry<String, Set<String>> entry : firstMap.entrySet()) {
		        sb.append(entry.getKey()).append("/");

		        // Sort the rules alphabetically
		        Set<String> rules = new TreeSet<>(entry.getValue());
		        for (String rule : rules) {
		            if (!rule.equals("0")) {
		                sb.append(rule);
		            }
		        }
		        sb.append(";");
		    }

			sb.deleteCharAt(sb.length() - 1); 
		    return sb.toString();
		}
	
	
	public String followToString() {
	    StringBuilder sb = new StringBuilder();
	    for (Map.Entry<String, Set<String>> entry : followMap.entrySet()) {
	        sb.append(entry.getKey()).append("/");

	        // Sort the rules alphabetically
	        Set<String> rules = new TreeSet<>(entry.getValue());
	        for (String rule : rules) {
	            if (!rule.equals("0")) {
	                sb.append(rule);
	            }
	        }
	        sb.append(";");
	    }

		sb.deleteCharAt(sb.length() - 1); 

	    return sb.toString();
	}

	


}
