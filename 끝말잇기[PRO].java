import java.util.*;

class UserSolution {
	
	static TreeSet<String>[] words;
	static HashSet<String> word_check = new HashSet<>();
	static int N;
	static boolean visit[];
    public void init(int N, int M, char[][] mWords)
    {
    	words = new TreeSet[26];
    	word_check.clear();
    	for(int i=0;i<26;i++) words[i]= new TreeSet<>();
    	this.N=N;
    	visit=new boolean[N+1];
    	for(int i=0;i<M;i++) {
    		String now = new String(mWords[i]).trim();
    		word_check.add(now);
    		words[now.charAt(0)-97].add(now);
    	}

    }

    public int playRound(int mID, char mCh)
    {
    	List<String> ws = new ArrayList<>();
    	int ans = -1;
        while(true) {
        	int pick = mCh-97;
        	if(words[pick].size()==0) {
        		visit[mID]=true;
        		ans = mID;
        		break;
        	}
        	String now = words[pick].pollFirst();
        	ws.add(new StringBuilder(now).reverse().toString());
        	mCh = now.charAt(now.length()-1);
        	do {
        		mID = mID+1>N ? 1:mID+1;
        	}while(visit[mID]);
        }
        for(String w : ws) {
        	if(word_check.contains(w)) continue;
        	word_check.add(w);
        	words[w.charAt(0)-97].add(w);
        }
        return ans;
    }
}
