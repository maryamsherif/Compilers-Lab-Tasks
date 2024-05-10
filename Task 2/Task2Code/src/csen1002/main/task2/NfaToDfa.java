package csen1002.main.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

public class NfaToDfa {

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	Map<Set<Integer>, Map<String, Set<Integer>>> finalfinal = new HashMap<>();
    String alphabet; //A
    String transitions; //T 0,a,1;
    String initialState; //I
    String acceptStates; //F
    
    String initialStateFinal; //I
    String acceptStatesFinal; //F
    
	
	public NfaToDfa(String input) {
		
		 String[] splitInput = input.split("#");
	     String states = splitInput[0]; //Q
	     alphabet = splitInput[1]; //A
	     transitions = splitInput[2]; //T 0,a,1;
	     initialState = splitInput[3]; //I
	     acceptStates = splitInput[4]; //F
	     
	     String [] splitTransitions = transitions.split(";");
	     
	     List<List<String>> allStatesList = new ArrayList<>();
	     List<List<String>> allNfaStates = new ArrayList<>();
	     
	     //List of states
	     String [] nfaStates = states.split(";");
	     
	     for (String s: nfaStates) {
	    	 String [] nfaStates2=s.split(",");
	    	 List<String> splitStatesParts=new ArrayList<>();
	    	 for (String part:nfaStates2) {
	    		 splitStatesParts.add(part);
	    	 }
	    	 allNfaStates.add(splitStatesParts);
	     }
	     
	    //List of transitions
	     for (String s: splitTransitions) {
	    	 String [] splitTransitions2=s.split(",");
	    	 List<String> splitParts=new ArrayList<>();
	    	 for (String part:splitTransitions2) {
	    		 splitParts.add(part);
	    	 }
	    	 allStatesList.add(splitParts);
	     }

	     
	     //Handling the epsilon closure  
	     Map<Integer,List<Integer>> table =new HashMap<>();
	     
	     //Add the states list to the hash map
	     for (List<String> list : allNfaStates) {
             int k =Integer.parseInt(list.get(0));
             List<Integer> initialValues=new ArrayList<>();
             initialValues.add(k);
             table.put(k,initialValues);
            
         }
	     
	     //check if there's a state with no transition, add it to the hashmap
	     String[] stateNumbers = states.split(";");
	     for (String numState: stateNumbers) {
	    	 int num=Integer.parseInt(numState);
	    	 if (!table.containsKey(num)) {
	    		 List<Integer> values0=table.getOrDefault(num, new ArrayList<>());
	    		 values0.add(num);
	    		 table.put(num, values0);
	    	 }
	     }
	     
	     
	     //Handling 1st epsilon tranisitons (second step after adding the states themselves to their e-closure)
	     for(List<String> l : allStatesList) {
	    	 if (l.get(1).equals("e")) {
	    		 int epKey=Integer.parseInt(l.get(0));
	    		 int epValue=Integer.parseInt(l.get(2));
	    	     List<Integer> values1 = table.getOrDefault(epKey, new ArrayList<>());
	    	     values1.add(epValue);
	    	     table.put(epKey, values1);
	    	 }
	     }
	     
	     
	    for (Map.Entry<Integer, List<Integer>> e:table.entrySet()) {
	    		 Integer keey=e.getKey();
	    		 List<Integer> lis=e.getValue();
	    		 List<Integer> cellsToAdd=new ArrayList<>();
	    		 Set<Integer> foundValues=new HashSet<>();
	    		 Set<Integer> foundKeys=new HashSet<>();
	    		 Iterator<Integer> iterr=lis.iterator();
	    		 
	    		while(iterr.hasNext()) {
	    			Integer currVal=iterr.next();
	    			
	    			if(!foundValues.contains(currVal) && !foundKeys.contains(currVal)) {
	    				cellsToAdd.add(currVal);
	    				foundValues.add(currVal);
	    			}
	    			
	    		 }
	    		foundKeys.add(keey);
	    		
	    		boolean changeOccurred = true;

	    		while (changeOccurred) {
	    		    changeOccurred = false; // Reset the flag before each iteration

	    		    for (Map.Entry<Integer, List<Integer>> ent : table.entrySet()) {
	    		        Integer currKey = ent.getKey();
	    		        List<Integer> currLis = ent.getValue();

	    		        if (foundValues.contains(currKey)) {
	    		            for (Integer va : currLis) {
	    		                if (!foundValues.contains(va) && !foundKeys.contains(va)) {
	    		                    cellsToAdd.add(va);
	    		                    foundValues.add(va);
	    		                    changeOccurred = true; // Set flag to true if elements are added
	    		                }
	    		            }
	    		        }
	    		    }
	    		}
	    		
	    		
	    		lis.addAll(cellsToAdd);
	    }
	    
	    
	    for (List<Integer> l : table.values()) {
	    	Set<Integer> s=new LinkedHashSet<>(l);
	    	l.clear();
	    	l.addAll(s);
	    	Collections.sort(l);
	    }
		finalfinal=HandleTransitions(allStatesList,table); 
		
	}



	public Map<Set<Integer>, Map<String, Set<Integer>>> HandleTransitions(List<List<String>> allTransitions, Map<Integer, List<Integer>> map) {

	    Map<Set<Integer>, Map<String, Set<Integer>>> dfaTransitions = new HashMap<>();
	    Queue<Set<Integer>> stateQueue = new LinkedList<>();
	    // Create the initial state of the DFA by taking the epsilon closure of the initial state of the NFA
	    int init=Integer.parseInt(initialState);
	    
	    Set<Integer> initialState = new HashSet<>(map.get(init)); 
	    stateQueue.add(initialState);
	    List<Integer> initialStateF=map.get(init);
	   
	   for(Integer bbb:initialStateF) {
		   if (initialStateFinal!=null) {
		   initialStateFinal=initialStateFinal+"/"+bbb.toString();
		   }
		   else {
			   initialStateFinal=bbb.toString();
		   }
	   }	   

	    while (!stateQueue.isEmpty()) {
	    	
	        Set<Integer> currentState = stateQueue.poll();
	        Map<String, Set<Integer>> transitions = new HashMap<>();
	        
	        for (String inputSymbol : getInputSymbols(allTransitions)) {
	           
	        	Set<Integer> nextState = new HashSet<>();
	            boolean foundTransition = false; // Flag to track if any transition is found

	            // Compute the epsilon closure of the next state
	            for (int state : currentState) {
	                for (List<String> transition : allTransitions) {
	                    int fromState = Integer.parseInt(transition.get(0));
	                    String symbol = transition.get(1);
	                    int toState = Integer.parseInt(transition.get(2));

	                    if (state == fromState && symbol.equals(inputSymbol)) {
	                        nextState.addAll(map.get(toState));
	                        foundTransition = true; // Mark that a transition is found
	                    }
	                }
	            }

	            // If no transition is found, add the dead state
	            if (!foundTransition) {
	                nextState.add(-1);
	            }

	            // Add to transitions and stateQueue if nextState is not empty
	            if (!nextState.isEmpty()) {
	                transitions.put(inputSymbol, nextState);
	                if (!dfaTransitions.containsKey(nextState) && !stateQueue.contains(nextState)) {
	                    stateQueue.add(nextState);
	                }
	            }
	        }

	        dfaTransitions.put(currentState, transitions);
	       // System.out.println(dfaTransitions);
	    }

	 // Checking for missing transitions to the dead state
	    Set<String> inputSymbols = getInputSymbols(allTransitions);
	    Map<String, Set<Integer>> deadStateTransitions = new HashMap<>();
	    for (String inputSymbol : inputSymbols) {
	        deadStateTransitions.put(inputSymbol, new HashSet<>(Collections.singletonList(-1)));
	    }
	    boolean deadStateExists = false; // Flag to check if dead state already exists
	    for (Set<Integer> currentState : dfaTransitions.keySet()) {
	        if (currentState.size() == 1 && currentState.contains(-1)) {
	            deadStateExists = true;
	            break;
	        }
	    }


	    return dfaTransitions;
	}

    private Set<String> getInputSymbols(List<List<String>> allTransitions) {
        Set<String> inputSymbols = new HashSet<>();
        for (List<String> transition : allTransitions) {
            if (!transition.get(1).equals("e")) {
                inputSymbols.add(transition.get(1));
            }
        }
        return inputSymbols;
    }
    
    private Map<Set<Integer>, Map<String, Set<Integer>>> sortMapsLex(Map<Set<Integer>, Map<String, Set<Integer>>> map) {
        // Iterate over each entry in the outer map
        for (Map.Entry<Set<Integer>, Map<String, Set<Integer>>> entry : map.entrySet()) {
            // Get the inner map for the current entry
            Map<String, Set<Integer>> innerMap = entry.getValue();

            // Sort the inner map by key alphabetically
            Map<String, Set<Integer>> sortedInnerMap = new TreeMap<>(innerMap);

            // Replace the unsorted inner map with the sorted one
            entry.setValue(sortedInnerMap);
        }

        return map;
    }
    
    private static Map<Set<Integer>, Map<String, Set<Integer>>> sortInnerMap(Map<Set<Integer>, Map<String, Set<Integer>>> map) {
        Map<Set<Integer>, Map<String, Set<Integer>>> sortedMap = new LinkedHashMap<>(); // Maintain insertion order
        
        for (Map.Entry<Set<Integer>, Map<String, Set<Integer>>> entry : map.entrySet()) {
            Set<Integer> key = entry.getKey();
            Map<String, Set<Integer>> innerMap = entry.getValue();
            
            Map<String, Set<Integer>> sortedInnerMap = new TreeMap<>(Comparator.naturalOrder());
            sortedInnerMap.putAll(innerMap);
            
            sortedMap.put(key, sortedInnerMap);
        }
        
        return sortedMap;
    }

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// returns Q#A#T#I#F
		StringBuilder finalString = new StringBuilder();
		
		List<List<Integer>> finalfinalStates=new ArrayList<>();
		
		for (Map.Entry<Set<Integer>, Map<String, Set<Integer>>> entry : finalfinal.entrySet()) {
		    Set<Integer> stateSet = entry.getKey();
		    List<Integer> sortedStates = new ArrayList<>(stateSet);
		    finalfinalStates.add(sortedStates);
		    
		    //sorting
		    Collections.sort(finalfinalStates, new Comparator<List<Integer>>() {
	            @Override
	            public int compare(List<Integer> list1, List<Integer> list2) {
	                // Compare elements of lists based on their values at each index
	                for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
	                    int cmp = Integer.compare(list1.get(i), list2.get(i));
	                    if (cmp != 0) {
	                        return cmp;
	                    }
	                }
	                // If lists are equal up to their size, the shorter list comes first
	                return Integer.compare(list1.size(), list2.size());
	            }
	        });
		    
		}
		
		// Convert the sorted lists into the desired string format
		for (List<Integer> sortedStates : finalfinalStates) {
		    boolean firstElement = true;
		    for (int state : sortedStates) {
		        if (!firstElement) {
		            finalString.append("/");
		        }
		        finalString.append(state);
		        firstElement = false;
		    }
		    finalString.append(";");
		}
		if (finalString.length() > 0) {
		    finalString.deleteCharAt(finalString.length() - 1);
		}
		finalString.append("#");
		finalString.append(alphabet);
		finalString.append("#");
		//------------------------------------------------------------Transitions--------------------------------------------------------------------
		  // Sort finalfinal according to its key 
        Map<Set<Integer>, Map<String, Set<Integer>>> sortedFinalFinal = new TreeMap<>(new Comparator<Set<Integer>>() {
            @Override
            public int compare(Set<Integer> key1, Set<Integer> key2) {
                // Compare elements of the key sets one by one
                Iterator<Integer> iterator1 = key1.iterator();
                Iterator<Integer> iterator2 = key2.iterator();
                while (iterator1.hasNext() && iterator2.hasNext()) {
                    int compare = Integer.compare(iterator1.next(), iterator2.next());
                    if (compare != 0) {
                        return compare;
                    }
                }
                    
                    return Integer.compare(key1.size(), key2.size());
            }
        });
        sortedFinalFinal.putAll(finalfinal);
        sortedFinalFinal=sortMapsLex(sortedFinalFinal);
        sortedFinalFinal=sortInnerMap(sortedFinalFinal);
        
        for (Map.Entry<Set<Integer>, Map<String, Set<Integer>>> entry : sortedFinalFinal.entrySet()) {
            for (Map.Entry<String, Set<Integer>> transition : entry.getValue().entrySet()) {
                List<Integer> sortedOuterKey = new ArrayList<>(entry.getKey());
                Collections.sort(sortedOuterKey); // Sort the outer key

                List<Integer> sortedInnerValues = new ArrayList<>(transition.getValue());
                Collections.sort(sortedInnerValues); // Sort the inner values

                String xx = String.join("/", sortedOuterKey.stream().map(Object::toString).collect(Collectors.toList())) + "," +
                            transition.getKey() + "," +
                            String.join("/", sortedInnerValues.stream().map(Object::toString).collect(Collectors.toList()));

                finalString.append(xx);
                finalString.append(";");
            }
        }

		if (finalString.length() > 0 && finalString.charAt(finalString.length() - 1) == ';') {
			finalString.deleteCharAt(finalString.length() - 1);
		}
		//--------------------------------------------------------------------------------------------------------------------------------------

		finalString.append("#");
		finalString.append(initialStateFinal);
		
		finalString.append("#");
		List<List<Integer>> Z = new ArrayList<>();
        String[] elements = acceptStates.split(";");
        Set<Integer> elementsSet = new HashSet<>();
        for (String element : elements) {
            elementsSet.add(Integer.parseInt(element));
        }

        for (List<Integer> list : finalfinalStates) {
            boolean found = false;
            for (int num : list) {
                if (elementsSet.contains(num)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                Z.add(list);
            }
        }
        List<String> formattedSublists = new ArrayList<>();
        for (List<Integer> sublist : Z) {
            String formattedSublist = sublist.stream()
                    .map(String::valueOf) 
                    .reduce((a, b) -> a + "/" + b) 
                    .orElse("");
            formattedSublists.add(formattedSublist);
        }
        String formattedZ = String.join(";", formattedSublists);
        finalString.append(formattedZ);

		return finalString.toString();
	}

	public static void main(String[] args) {
		
		
		NfaToDfa nf=new NfaToDfa("0;1;2;3;4;5;6;7;8#b;w#"
				+ "0,e,1;1,w,2;2,e,3;3,e,0;3,e,5;4,e,0;4,e,5;5,e,4;5,e,7;6,e,4;6,e,7;7,b,8"
				+ "#6#8");
		//System.out.print(nf.toString());
	}

}

