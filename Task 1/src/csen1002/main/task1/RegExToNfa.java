package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Maryam Sherif Hamdy Amin
 * @id 49-5891
 * @labNumber 17
 */

public class RegExToNfa {

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	static NFA resultNFA;

	public RegExToNfa(String input) {
		// input A#R A: alphabet R: regular expression
		String[] splitInput = input.split("#");
		String alphabet = splitInput[0]; //A
		String regEx = splitInput[1]; //R
		int StateCounter = 0;
		//NFAs stack
		Stack<NFA> nfaStack = new Stack<NFA>();

		for (int i = 0; i < regEx.length(); i++) {
			
			char c = regEx.charAt(i);
			
			if (isOperand(c)) {
			    List<Integer> states = new ArrayList<>(List.of(StateCounter, StateCounter + 1));
			    List<Transition> transitions = new ArrayList<>(List.of(new Transition(StateCounter, c, StateCounter + 1)));
			    NFA nfa = new NFA(StateCounter, StateCounter + 1, states, transitions, alphabet);
			    StateCounter += 2;
			    nfaStack.push(nfa);
			} else {
				switch (c) {
				
					case '*' -> {
					    NFA operand = nfaStack.pop();
					    
					    int newStartState = StateCounter++;
					    int newAcceptState = StateCounter++;
					    
					    List<Integer> states = new ArrayList<>(operand.states);
					    states.addAll(List.of(newStartState, newAcceptState));
					    
					    List<Transition> transitions = new ArrayList<>(operand.transitions);
					    transitions.add(new Transition(newStartState, 'e', operand.startState));
					    transitions.add(new Transition(newStartState, 'e', newAcceptState));
					    transitions.add(new Transition(operand.acceptState, 'e', operand.startState));
					    transitions.add(new Transition(operand.acceptState, 'e', newAcceptState));
					    
					    NFA nfa = new NFA(newStartState, newAcceptState, states, transitions, alphabet);
					    nfaStack.push(nfa);
					}


					case '.' -> {
					    NFA operand2 = nfaStack.pop();
					    NFA operand1 = nfaStack.pop();
					    
					    //Replace N2 start state with N1 accept state
					    int oldStart=operand2.startState;
					    operand2.startState=operand1.acceptState;
					    
					    for (int x=0; x < operand2.transitions.size();x++) {
					    	if (operand2.transitions.get(x).startState==oldStart) {
					    		operand2.transitions.get(x).startState=operand1.acceptState;
					    	}
					    }
					    
					    
					    List<Integer> states = new ArrayList<>(operand1.states);
					    for (int y=0; y < operand2.states.size();y++) {
					    	if(operand2.states.get(y)==oldStart) {
					    		operand2.states.remove(y);
					    	}
					    }
					    states.addAll(operand2.states);
					    
					    List<Transition> transitions = new ArrayList<>(operand1.transitions);
					    transitions.addAll(operand2.transitions);
					    
					    transitions.removeIf(transition -> transition.startState == operand1.acceptState && transition.transition == 'e' && transition.endState == operand2.startState);
					    
					    NFA nfa = new NFA(operand1.startState, operand2.acceptState, states, transitions, alphabet);
					    nfaStack.push(nfa);
					}


					case '|' -> {
					    NFA operand3 = nfaStack.pop();
					    NFA operand4 = nfaStack.pop();
					    
					    int newStartState = StateCounter++;
					    int newAcceptState = StateCounter++;
					    
					    List<Integer> states = new ArrayList<>(operand3.states);
					    states.addAll(operand4.states);
					    states.addAll(List.of(newStartState, newAcceptState));
					    
					    List<Transition> transitions = new ArrayList<>(operand3.transitions); // Add transitions from operand3
					    transitions.addAll(operand4.transitions); // Add transitions from operand4
					    
					    transitions.add(new Transition(newStartState, 'e', operand3.startState));
					    transitions.add(new Transition(newStartState, 'e', operand4.startState));
					    transitions.add(new Transition(operand3.acceptState, 'e', newAcceptState));
					    transitions.add(new Transition(operand4.acceptState, 'e', newAcceptState));
					    
					    NFA nfa = new NFA(newStartState, newAcceptState, states, transitions, alphabet);
					    nfaStack.push(nfa);
					}
				}
			}
		}
		resultNFA = nfaStack.pop();
	}

	//Check if the character is an operator or operand
	public boolean isOperand(char c){
        return c != '*' && c != '.' && c != '|';
    }

	class NFA{
		int startState;
		int acceptState;
		private List<Integer> states;
		private List<Transition> transitions;
		private String alphabet;

		 NFA(int startState, int acceptState, List<Integer>states, List<Transition> transitions, String alphabet) {
			this.startState = startState;
			this.acceptState = acceptState;
			this.transitions=transitions;
			this.states=states;
			this.alphabet=alphabet;

		}
	}

	class Transition{
		int startState;
		char transition;
		int endState;

		 Transition(int startState, char transition, int endState) {
			this.startState = startState;
			this.transition = transition;
			this.endState = endState;
		}

		public int getStartState() {
			return startState;
		}

		public int getEndState() {
			return endState;
		}
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
	    // Q#A#T#I#F

	    // Q: states sorted in ascending order
	    // A: alphabet
	    // T: set of transitions in the form "startState,transition,endState" separated by ";"
	    // I: start state
	    // F: accept state

	    StringBuilder finalString = new StringBuilder();
	    
	    List<Integer> sortedStates = new ArrayList<>(resultNFA.states);
	    Collections.sort(sortedStates); // Sort states in ascending order
	    for (int i = 0; i < sortedStates.size(); i++) {
	    	finalString.append(sortedStates.get(i));
	        if (i < sortedStates.size() - 1) {
	        	finalString.append(";");
	        }
	    }// Q
	    finalString.append("#"); 
	    finalString.append(resultNFA.alphabet); // A
	    finalString.append("#");
	    
	    // Sort transitions by start state and then end state
	    List<Transition> sortedTransitions = new ArrayList<>(resultNFA.transitions);
	    Collections.sort(sortedTransitions, (t1, t2) -> {
	        if (t1.getStartState() == t2.getStartState()) {
	            return Integer.compare(t1.getEndState(), t2.getEndState());
	        } else {
	            return Integer.compare(t1.getStartState(), t2.getStartState());
	        }
	    });

	    
	    for (int i = 0; i < sortedTransitions.size(); i++) {
	        Transition t = sortedTransitions.get(i);
	        finalString.append(t.startState);
	        finalString.append(",");
	        finalString.append(t.transition);
	        finalString.append(",");
	        finalString.append(t.endState);
	        if (i < sortedTransitions.size() - 1) {
	        	finalString.append(";"); 
	        }
	    } // T
	    
	    finalString.append("#");
	    finalString.append(resultNFA.startState); // I
	    finalString.append("#");
	    finalString.append(resultNFA.acceptState); // F
	    return finalString.toString();
	}

}
