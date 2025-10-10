import java.io.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
   Queue<String> q = new LinkedList<>();
        int time =0;
        for(int i=0; i<cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	
        	if(!q.contains(city)) {
        		time +=5;
        	}else {
        		q.remove(city);
        		time +=1;
        	}
        	q.add(city);
        	if(q.size()>cacheSize) {
    			q.poll();
    		}
        }
        
        return time;
    }
}