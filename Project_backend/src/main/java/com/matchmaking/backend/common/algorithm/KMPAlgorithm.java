package com.matchmaking.backend.common.algorithm;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class KMPAlgorithm {


    public static int kmpSearch(String target, String matchItem, int[] next){
        // ignore case-sensitive
        target = target.toLowerCase(Locale.ROOT);
        matchItem = matchItem.toLowerCase(Locale.ROOT);
        for(int i = 0,  j=0; i < matchItem.length(); i++){
            while (j >0 && matchItem.charAt(i) != target.charAt(j)){
                j = next[j -1];
            }

            // if char match move to next index
            if(matchItem.charAt(i)==target.charAt(j)){
                j++;
            }
            // found the word return default distance
            if( j == target.length()){
                return 1;
            }
        }
        return 2;
    }

    public static int[] kmpNext(String target){

        // init array length
        int[] next = new int[target.length()];

        next[0] = 0;
        for(int i = 1, j=0; i< target.length(); i++){
            while (j > 0 && target.charAt(i) != target.charAt(j)){
                j = next[j -1];
            }

            if(target.charAt(i) == target.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
