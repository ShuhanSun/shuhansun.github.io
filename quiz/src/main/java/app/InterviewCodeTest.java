package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InterviewCodeTest {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<String>();
        lines.add("foobarbaz");
        lines.add("foob");
        lines.add("foo");
        lines.add("ba");
        lines.add("ba");
        lines.add("r");
        lines.add("z");
        List<String> lines1 = new ArrayList<String>();
        lines1.add("foobarbaz");
        lines1.add("foo");
        lines1.add("fooz");
        lines1.add("ba");
        lines1.add("r");
        lines1.add("z");
        List<String> lines3 = new ArrayList<String>();
        lines3.add("catsanddogs");
        lines3.add("ddogs");
        lines3.add("cat");
        lines3.add("san");

        isWord(lines);
        isWord(lines1);
        isWord(lines3);
    }

    private static void isWord(List<String> lines) {
        String word = lines.get(0);
        boolean r = isWord(word, lines.subList(1, lines.size()));
        System.out.println(r);
    }

    static boolean isWord(String word, List<String> tiles){
        if(word == null || word.length() == 0){
            return true;
        }
        if(tiles.size() == 0){
            return false;
        }

        boolean r = false;
        for (int i = 0; i < tiles.size(); i++) {
            String tile = tiles.get(i);
            if(word.startsWith(tile)){
                String substring = word.substring(tile.length());
                System.out.println(tile +":"+substring);
                r = isWord(substring, newSubList(tiles, i)) || r;
            }
        }
        return r;
    }

    static List<String> newSubList(List<String> tiles, int i){
        List<String> subList = new ArrayList<String>(tiles.size() - 1);
        for (int j = 0; j < i; j++) {
            subList.add(tiles.get(j));
        }
        for (int j = i + 1; j < tiles.size(); j++) {
            subList.add(tiles.get(j));
        }
        return subList;
    }
}
