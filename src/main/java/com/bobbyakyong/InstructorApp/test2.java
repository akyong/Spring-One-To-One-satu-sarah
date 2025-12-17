package com.bobbyakyong.InstructorApp;

import java.util.*;

public class test2 {

    public static String isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // closing brackets
            else {
                if (stack.isEmpty()) {
                    return "false";
                }
                char open = stack.pop();
                if (!isMatching(open, c)) {
                    return "false";
                }
            }
        }

        return stack.isEmpty() ? "true" : "false";
    }

    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    // contoh test
    public static void main(String[] args) {
        String[] tests1 = {"()", "{}()", "({})"};
        String[] tests2 = {"(", "(}", "(", "}{"};

        for (String s : tests1) {
            System.out.println(s + " -> " + isBalanced(s));
        }

        for (String s : tests2) {
            System.out.println(s + " -> " + isBalanced(s));
        }
    }
}