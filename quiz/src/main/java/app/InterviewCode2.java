package app;

import java.util.Stack;

public class InterviewCode2 {
    public static void main(String[] args) {
        String s = "[12{}3]";
        String s1 = "[12[{}]3]}";
        System.out.println(isValid(s));
        System.out.println(isValid(s1));
    }

    private static boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();
        for (char c :
                s.toCharArray()) {
            if (c == '[' || c == '{') {
                stack.push(c);
            }

            if(c == ']'){
                if(stack.isEmpty() || stack.peek() != '['){
                    return false;
                }
                stack.pop();
            }

            if(c == '}'){
                if(stack.isEmpty() || stack.peek() != '{'){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
