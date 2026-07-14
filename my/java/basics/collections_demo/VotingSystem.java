package my.java.basics.collections_demo;

import java.util.LinkedHashMap;
import java.lang.Integer;
public class VotingSystem {
    private LinkedHashMap<String, Integer> votes = new LinkedHashMap<>();
    //HashMap is unordered, LinedHashMap is ordered
    public static void main(String[] args) {
        //HashMap is unordered, LinedHashMap is ordered
    }
    public LinkedHashMap getVoters(){
        return votes;
    }
    void vote(String contestant){
        votes.merge(contestant, 1, Integer::sum);
    }
}
