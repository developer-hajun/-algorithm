import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String,TreeMap<Integer,TreeSet<Integer>>> map = new HashMap<>();
        HashMap<String,Integer> genre = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        
        for(int i=0;i<genres.length;i++){
            String g = genres[i];
            int p = plays[i];
            
            if(!genre.containsKey(g)) genre.put(g,p);
            else genre.put(g,genre.get(g)+p);
            
            if(!map.containsKey(g)) map.put(g,new TreeMap<>((o1,o2)->{
            return o2-o1;
        }));
            
            
            TreeMap<Integer,TreeSet<Integer>> nowMap = map.get(g);
            
            if(!nowMap.containsKey(p)) nowMap.put(p,new TreeSet<>());
            
            TreeSet<Integer> play = nowMap.get(p);
            
            play.add(i);
        }
        
        TreeMap<Integer,String> go = new TreeMap<>((o1,o2)->{
            return o2-o1;
        });
        for(String e : genre.keySet()) go.put(genre.get(e),e);
        
        for(int count : go.keySet()){
            
            String now = go.get(count);
            
            TreeMap<Integer,TreeSet<Integer>> nowMap = map.get(now);
            
            int counts = 0;
            
            A: for(int play : nowMap.keySet()){
                for(int number: nowMap.get(play) ){
                    answer.add(number);
                    counts++;
                    if(counts>=2) break A;
                }
            }
            
        }
        int[] ans = new int[answer.size()];
        for(int i=0;i<answer.size();i++){
            ans[i] = answer.get(i);
        }
        
        
        return ans;
    }
}

//속한 노래가 많이 재생 장르 -> 장르내 많이 재생된 노래 ->  고유번호 낮은순