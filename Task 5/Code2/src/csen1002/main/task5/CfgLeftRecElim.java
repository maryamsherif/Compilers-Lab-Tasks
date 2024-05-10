package csen1002.main.task5;
import java.util.*;

/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5892
 * @labNumber 17
 */

public class CfgLeftRecElim {

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
	
	Map<String,Set<String>> alreadyLR =new LinkedHashMap<>();
	Map<String,Integer> alreadyLRCount =new LinkedHashMap<>();
	List<String> variableOrder = new ArrayList<>(); // Maintain order of variables
	Map<String,Integer> variablesWithOrders=new LinkedHashMap<>();

	
	public CfgLeftRecElim(String cfg) {
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
	                    variableOrder.add(vVariable); // Add variable to order list
	                    break;
	                }
	            }
	        }
	        
	        int count=0;
	        for(String xxx:variableOrder) {
	        	count++;
	        	variablesWithOrders.put(xxx,count);
	        	
	        }
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String k : rulesMap.keySet()){
			sb.append(k);
			sb.append(";");
		}
		sb.deleteCharAt(sb.length() - 1); 
		sb.append("#");
		sb.append(t);
		sb.append("#");

		for (Map.Entry<String, Set<String>> entry : rulesMap.entrySet()) {

			sb.append(entry.getKey()).append("/");
			Set<String> rules = entry.getValue();
			for (String rule : rules) {
				sb.append(rule).append(",");
			}
			sb.deleteCharAt(sb.length() - 1); // Remove the last comma
			sb.append(";");
			
		}
		sb.deleteCharAt(sb.length() - 1); // Remove the last #
		

		return sb.toString();
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
	    
	    List<String> keys = new ArrayList<>(rulesMap.keySet()); //keys list
	    int lastKeyIndex = keys.size() - 1; // Index of the last key in the list

	    for (int i = 0; i < keys.size(); i++) {
	        String key = keys.get(i);
	        Map<String, Set<String>>   modifiedEntries= new LinkedHashMap<>();
	        Set<String> currRules = rulesMap.get(key);
 
	        if (key.equals("S")) {
	        	boolean sFound=false;
	        	for (String value : currRules) {
	        		if (value.startsWith("S")) {
	        			sFound=true;
	        			break;
	        		}
	        	}
	        	if (sFound) {
					modifiedEntries.putAll(leftRecrMyself(key,currRules));
	        	}
	        	rulesMap.putAll(modifiedEntries);
	            continue;
	        }

	        // handling left recursion for the other variables (not immediate)
	        for (int j = 0; j < keys.size(); j++) {
	            String prevKey = keys.get(j);

	            if (prevKey.equals(key)) {
	                break; 
	            }
	            Set<String> prevRules = rulesMap.get(prevKey);

	            for (String rule : currRules) {
	                if (rule.startsWith(prevKey)) {
						 Set<String> test= checkLRandReplace(key,currRules,prevKey,prevRules);
						 modifiedEntries.put(key, test);
	                    
	                }
	            }
	        }
	         rulesMap.putAll(modifiedEntries);
	         
			
			//eliminate immediate left recursion
	        currRules=rulesMap.get(key);
	        Map<String,Set<String>>xx=new LinkedHashMap<>();
	        if(currRules!=null) {
	        for(String w:currRules) {

	        	if(w.startsWith(key)) {
	        		xx=leftRecrMyself(key,currRules);
	        		}
	        	}
	        }

	        rulesMap.putAll(xx);


	        if (i == lastKeyIndex) { 
	            break; 
	        }
	    }
	}

					 
	
	
	
	
	public Map<String, Set<String>> leftRecrMyself(String key, Set<String> values) {
	    Map<String, Set<String>> newMap = new LinkedHashMap<>();
	    Set<String> nonLeftRecursiveRules = new LinkedHashSet<>();
	    Set<String> leftRecursiveRules = new LinkedHashSet<>();

	    String newVariable = key + "'";
	    for (String s : values) {
	        if (s.startsWith(key)) {
	            String restOfTheString = s.substring(key.length()) + newVariable;
	            
	            leftRecursiveRules.add(restOfTheString);

	        } else {
	            String rest = s + newVariable;
	            nonLeftRecursiveRules.add(rest);
	        }
	    }
        leftRecursiveRules.add("e");
        
        
	    newMap.put(key, nonLeftRecursiveRules);
	    newMap.put(newVariable, leftRecursiveRules);
	    
	    return newMap;
	}

	
	public Set<String> checkLRandReplace(String key,Set<String>values,String variableFound,Set<String> varRules) {
		Set<String> modifiedValues = new LinkedHashSet<>();
		
	    for (String rule : values) {
	        if (rule.startsWith(variableFound)) {
	            // Handle left recursion by replacing the left-recursive variable
	            String restOfTheRule = rule.substring(variableFound.length());
	            for (String replacement : varRules) {
	                modifiedValues.add(replacement + restOfTheRule);
	            }
	        } else {
	            modifiedValues.add(rule); // Add unchanged rule if not left-recursive
	        }
	    }
   
	   String lR= checkForLeftRecursion(key,modifiedValues);
	   if(lR!=null) {
		   modifiedValues =  checkLRandReplace(key,modifiedValues,lR,rulesMap.get(lR));
	   }
        return modifiedValues;
	}
	
	  public String checkForLeftRecursion(String key,Set<String> modifications) {
			  for(String x: modifications) {
						 for(String varr:variables) {
							 if(x.startsWith(varr)) {
								 if(variablesWithOrders.get(varr)<variablesWithOrders.get(key)) {
									 return varr;
								 }
							 }
						 }
			  }
			
		  return null;
	  }

	
	public static void main(String[] args) {
		//CfgLeftRecElim cfg= new CfgLeftRecElim("S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
		CfgLeftRecElim cfg=new CfgLeftRecElim("S;I;D;W;K#c;g;n;t#S/nKS,gDKS,nIg,ScWSW;I/WSWK,DcWn,IcSgS,InS;D/IIcWS,gKWS,g;W/DK,WWnI,t,cS,DtIt,WKtD;K/gStSI,WIg,Sn");
		cfg.eliminateLeftRecursion();
		cfg.toString();
		

	}

}
