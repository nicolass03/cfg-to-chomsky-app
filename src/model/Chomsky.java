package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Chomsky {

	private Map<Character, ArrayList<String>> grammar;

	public Chomsky(Map gram) {
		grammar = gram;
		convertToChomsky();
	}

	private void convertToChomsky() {
		// removeTerminals();
		removeUnreachables();
	}

	/**
	 * private void initGrammar(String[][] gram) { grammar = new Hashtable<>(); for
	 * (int i = 0; i < gram.length; i++) { Character var = gram[i][0].charAt(0);
	 * ArrayList prods = new ArrayList<>(); for (int j = 1; j < gram.length; j++) {
	 * prods.add(gram[i][j]); } grammar.put(var, prods); } }
	 */
	private void removeTerminals() {
		// Initialize terms arraylist
		ArrayList terms = new ArrayList<>();
		// goes down all the entry set
		for (Entry e : grammar.entrySet()) {
			// gets variable and its productions
			Character v = (Character) e.getKey();
			ArrayList<String> p = (ArrayList<String>) e.getValue();
			// the loop searchs if has any terminal prod and adds it to the terms collection
			for (String c : p) {
				if (isStringLowerCase(c)) {
					terms.add(v);
					break;
				}
			}
		}
		// Gets all variables that produce the initial term variables
		ArrayList allTerms = getTerminals(terms);
		// get all the variables
		Set<Character> keysToRemove = grammar.keySet();
		// Removes common keys, leaving only non terminal variables on the array
		keysToRemove.removeAll(allTerms);
		// loop that eliminates the variable and the productions in other variables that
		// calls it
		for (Character q : keysToRemove) {
			grammar.remove(q);
			for (Entry e : grammar.entrySet()) {
				Character v = (Character) e.getKey();
				ArrayList<String> p = (ArrayList<String>) e.getValue();
				for (String c : p) {
					if (c.contains(q + "")) {
						p.remove(c);
					}
				}
			}
		}
	}

	private ArrayList getTerminals(ArrayList terms) {
		ArrayList newTerms = terms;
		/////////////////////////////////////
		/// algorithm that adds the new terms
		/////////////////////////////////////
		for (Entry e : grammar.entrySet()) {
			// gets variable and its productions
			Character v = (Character) e.getKey();
			ArrayList<String> p = (ArrayList<String>) e.getValue();
			// the loop searchs if has any terminal prod and adds it to the terms collection
			boolean terminal = false;
			for (String c : p) {
				// if verified terminal already, adds the variable to the newTerms and continues
				// with the next var
				// get prod as array
				char[] strArray = c.toCharArray();
				// break production into symbols and goes all over it
				for (int i = 0; i < strArray.length; i++) {
					// if is a terminal symbol or a terminal variable, set terminal on true and wait
					// till the
					// prod finish
					if (isStringLowerCase(strArray[i] + "") || terms.contains(strArray[i])) {
						terminal = true;
					}
					// if doesnt, breaks and tries with the next production
					else {
						terminal = false;
						break;
					}
				}
				if (terminal) {
					newTerms.add(v);
					break;
				}
			}
		}

		if (terms.equals(newTerms)) {
			return newTerms;
		} else {
			return getTerminals(newTerms);
		}
	}

	private void removeUnreachables() {
		ArrayList<Character> initial = new ArrayList<Character>();
		initial.add((Character) grammar.keySet().toArray()[0]);
		ArrayList<Character> reachables = findReachables(initial);
		Set<Character> variables = grammar.keySet();
		variables.removeAll(reachables);
		for (Character key : variables) {
			grammar.remove(key);
		}
	}

	private ArrayList<Character> findReachables(ArrayList<Character> reachables) {
		ArrayList<Character> newReachables = (ArrayList<Character>) reachables.clone();
		ArrayList<String> productions = null;

		for (int i = 0; i < reachables.size(); i++) {
			productions = grammar.get(reachables.get(i));
			for (int j = 0; j < productions.size(); j++) {
				char[] units = productions.get(j).toCharArray();
				for (int k = 0; k < units.length; k++) {
					if (units[k] != '-') {
						if (isStringUpperCase("" + units[k])) {
							if (!newReachables.contains(units[k])) {
								newReachables.add(units[k]);
							}

						}
					}
				}

			}

		}

		if (reachables.equals(newReachables)) {
			return reachables;
		} else {
			return findReachables(newReachables);
		}
	}

	private static void simulateNullProductions() {

	}

	private static void simulateUnitProductions() {

	}

	private static boolean isStringLowerCase(String str) {
		// convert String to char array
		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			// if any character is not in lower case, return false
			if (!Character.isLowerCase(charArray[i]))
				return false;
		}
		return true;
	}

	private static boolean isStringUpperCase(String str) {
		// converts String to char array
		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			// if any character is not in upper case, returns false
			if (!Character.isUpperCase(charArray[i]))
				return false;
		}
		return true;
	}

	private ArrayList<Character> getGrammarVariables() {
		ArrayList<Character> variables = new ArrayList<Character>();;
		for (Entry e : grammar.entrySet()) {
			variables.add((Character) e.getKey());
		}

		return variables;
	}
}
