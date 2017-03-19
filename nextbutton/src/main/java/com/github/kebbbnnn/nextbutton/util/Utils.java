package com.github.kebbbnnn.nextbutton.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by kevinladan on 3/19/17.
 */

public class Utils {

    public static boolean isValidExpression(String expression) {
        Map<Character, Character> openClosePair = new HashMap<>();
        openClosePair.put(')', '(');
        openClosePair.put('}', '{');
        openClosePair.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (openClosePair.containsKey(ch)) {
                if (stack.pop() != openClosePair.get(ch)) {
                    return false;
                }
            } else if (openClosePair.values().contains(ch)) {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static String removeClosePair(String s) {
        return s.replaceAll("[\\[\\](){}]", "");
    }

    public static String removeWhiteSpace(String s) {
        return s.replaceAll("\\s+", "");
    }
}
