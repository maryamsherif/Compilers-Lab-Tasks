package csen1002.main.task4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

public class CfgEpsUnitElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *             representation follows the one in the task description
	 */
	String v;
	List<String> variables = new ArrayList<>();
	String t;
	List<String> terminals = new ArrayList<>();

	List<String> rules = new ArrayList<>();

	Map<String,Set<String>> rulesMap =new LinkedHashMap<>();
	Set<String> alreadyDone = new HashSet<>();

	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		String [] input = cfg.split("#");
		v = input[0];
		variables = List.of(v.split(";"));
		t = input[1];
		terminals = List.of(t.split(";"));
		String r = input[2];
		rules= List.of(r.split(";"));

		for (String key : variables) {
			for (String s : rules) {
				String[] splitInput = s.split("/");
				String vVariable = splitInput[0];
				if (vVariable.equals(key)) {
					Set<String> sortedStrings = Set.of(splitInput[1].split(","));
					rulesMap.put(vVariable, sortedStrings);
					break; 
				}
			}
		}
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(v);
		sb.append("#");
		sb.append(t);
		sb.append("#");

		for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
			Set<String> set = entry.getValue();
			Set<String> sortedSet = new TreeSet<>(new CustomComparator());
			sortedSet.addAll(set);
			rulesMap.put(entry.getKey(), sortedSet);
		}

		for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {

			sb.append(entry.getKey()).append("/");
			Set<String> rules = entry.getValue();
			for (String rule : rules) {
				sb.append(rule).append(",");
			}
			sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
			sb.append(";");
		}
		sb.deleteCharAt(sb.length() - 1); // Remove the trailing #
		return sb.toString();
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		System.out.print(rulesMap);
		boolean changesMade = true;
		
	   while(changesMade) { 
		   changesMade=false;
		   Set<String> epsVariables = findEpsilons();

		    for (String eps : epsVariables) {
		    	epsVariables = findEpsilons();
		        
		        for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
		            Set<String> updatedProductions = new HashSet<>();
		            
		            for (String production : entry.getValue()) {
		                // Skip epsilon-producing productions
		                if (production.equals("e")) {
		                	continue;
		                }
		                
		                else if (production.equals(eps)) {
		                	updatedProductions.add("e");
		                	changesMade = true;
		                }
	
		                // If the production contains epsilon-producing variable --> generate combinations
		                 if (production.contains(eps)) {
		                	 Set<String> combinations = generateCombinations(production, eps);
		                    updatedProductions.addAll(combinations);
		                    changesMade = true;
		                 } else {
		                	updatedProductions.add(production);
		                }
		            }
		            
		            entry.setValue(updatedProductions);
		        }
		      if (changesMade) {
		        alreadyDone.add(eps);
		        if (rulesMap.get(eps)!=null) {
		        	rulesMap.get(eps).remove("e");
		        }
		      }
		    }
	  }
	   for (Map.Entry<String, Set<String>> yy : rulesMap.entrySet()) {
		    Iterator<String> iterator = yy.getValue().iterator();
		    while (iterator.hasNext()) {
		        String p = iterator.next();
		        if (p.equals("e")) {
		            iterator.remove();
		        }
		    }
		}  
	}

	public Set<String> findEpsilons (){
		Set<String> epsVariables = new HashSet<>();
		for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
			if (!alreadyDone.contains(entry.getKey())) {
			for (String s: entry.getValue()) {
					if (s.equals("e")) {
						epsVariables.add(entry.getKey());
						break;
					}
			}
		}
		}
		return epsVariables;
	}

	public Set<String> generateCombinations(String input, String characterToRemove) {
	    System.out.println("Input: " + input + " Character to remove: " + characterToRemove);
	    Set<String> combinations = new HashSet<>();
	    
	    if (!input.contains(characterToRemove)) {
	    	System.out.println("false");
	        //combinations.add(input); // If not, simply add the input to combinations and return
	        return combinations;
	    } else {
	    
	    if (input.equals(characterToRemove)) {
	        combinations.add(characterToRemove);
	    } else {
	        int index = input.indexOf(characterToRemove);
	        while (index >= 0) {
	            String combination = input.substring(0, index) + input.substring(index + 1);
	            combinations.add(combination);
	            index = input.indexOf(characterToRemove, index + 1);
	        }

	        if (!input.equals(characterToRemove)) {
	            combinations.add(input);
	        }
	        String tempInput = input.replaceAll(characterToRemove, "");
	        combinations.add(tempInput);
	    }
	    
		    combinations.remove("");
		    
		    // Check if any additional combinations can be generated
		    Set<String> additionalCombinations = new HashSet<>();
		    for (String combo : combinations) {
		        if (!combo.equals(input) && !combo.equals(characterToRemove)) { // Prevent infinite recursion and skip the original character
		            System.out.println("----------------------------------------");
		            additionalCombinations.addAll(generateCombinations(combo, characterToRemove));
		        }
		    }
		    combinations.addAll(additionalCombinations);
		    System.out.println("Combinations ---->  " + combinations);
	    }
	    return combinations;
	    
	}

	

	 class CustomComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
		    // Check for empty strings
		    if (s1.isEmpty() && s2.isEmpty()) {
		        return 0; 
		    } else if (s1.isEmpty()) {
		        return -1; 
		    } else if (s2.isEmpty()) {
		        return 1; 
		    }
		    boolean isLowercase1 = Character.isLowerCase(s1.charAt(0));
		    boolean isLowercase2 = Character.isLowerCase(s2.charAt(0));
		    
		    if (isLowercase1 && !isLowercase2) {
		        return 1; 
		    } else if (!isLowercase1 && isLowercase2) {
		        return -1; 
		    } else {
		        return s1.compareTo(s2);
		    }
		}

	}



	/**
	 * Eliminates Unit Rules from the grammar
	 */
	
	  public void eliminateUnitRules() { 
		  removeVariableRules();
	  for (String variable:variables) {
		  Set<String> variableSet = rulesMap.get(variable);
          for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
              String key = entry.getKey();
              Set<String> value = entry.getValue();
              if (!key.equals(variable) && value.contains(variable)) {
                  // Replace variable with content of variableSet
                  value.remove(variable);
                  value.addAll(variableSet);
              }
          }
      }
	  while (containsUnitRule()) {
		  eliminateUnitRules();
		  }
	  }
	  
	  public void removeVariableRules() {
		  for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {
				  Set<String> copySet = new HashSet<>(entry.getValue()); for (String right :
				  entry.getValue()) { if (entry.getKey().equals(right)) {
				  copySet.remove(right); } } entry.setValue(copySet); }
	    }
	  
	  public boolean containsUnitRule() {
	        for (Set<String> rules : rulesMap.values()) {
	            for (String rule : rules) {
	                if (rule.length() == 1 && Character.isUpperCase(rule.charAt(0))) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }
	 
	public static void main(String[] args) {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;O;T;L;K;V#b;m;q#S/KTObT,S,Sm,qTLLS;O/KqT,VLbT,VmLK,b,e;T/KqO,bLLq,bVO,e;L/L,VbK,bSmV,m;K/KbSTK,L,O,bSq;V/OOK,OOOmV");
		cfgEpsUnitElim.eliminateEpsilonRules();
		//cfgEpsUnitElim.eliminateUnitRules();
		System.out.println(cfgEpsUnitElim.toString());

	}

}
