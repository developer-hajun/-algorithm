import java.util.*;

class UserSolution {

	static class Square {
		int id;
		int y, x;
		int h, w;

		public Square(int mID, int mRow, int mCol, int mHeight, int mWidth) {
			id = mID;
			y = mRow;
			x = mCol;
			h = mHeight;
			w = mWidth;
		}
	}

	static final int MAX = 10000;
	static int CELL_SIZE;
	static int[] parent;
	static int[] size;
	static Square[] squareByIdx;
	static Map<Integer, Integer> midToIdx;
	static Map<Long, List<Integer>> grid;
	static Map<Integer, Integer> groups;

	static long getKey(int row, int col) {
		return (long) row * MAX + col; //키 만들기  최대 100이기때문에 10000으로함
	}

	public void init(int L, int N) {
		CELL_SIZE = L; //버킷으로 만들 사이즈 ( 사각형의 최대 사이즈 )
		parent = new int[15001]; //union-find
		size = new int[15001]; //각 그룹이 들어갈 크기
		squareByIdx = new Square[15001]; //idx값으로 가각형 탐색
		midToIdx = new HashMap<>(); //mid값으로 idx가져오기
		grid = new HashMap<>(); //버킷 
		groups = new HashMap<>(); //그룹

		Arrays.fill(parent, -1);
	}

	int find(int x) {
		if (parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}
	//부모 찾기 
	

	void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) return;

		if (pa < pb) {
			parent[pb] = pa;
			size[pa] += size[pb];
			groups.put(pa, size[pa]);
			groups.remove(pb);
		} else {
			parent[pa] = pb;
			size[pb] += size[pa];
			groups.put(pb, size[pb]);
			groups.remove(pa);
		}
		//인덱스 기준으로 작은곳에 그룹 합치기 + 부모로 선정하기
	}
	

	public int draw(int mID, int mRow, int mCol, int mHeight, int mWidth) {
		int id = midToIdx.size();

		Square draw = new Square(mID, mRow, mCol, mHeight, mWidth);
		squareByIdx[id] = draw; //내 id에 만든 사각형 넣기 
		midToIdx.put(mID, id); // 인덱스 압축

		parent[id] = id; //내 아이디 설정
		size[id] = 1; //그룹 만들기( 혼자기때문에 사이즈 1)
		groups.put(id, 1);  // 내 그룹만들기

		int rowStart = mRow / CELL_SIZE; //Y시작(버킷 기준)
		int rowEnd = (mRow + mHeight - 1) / CELL_SIZE;//Y끝(버킷 기준)
		int colStart = mCol / CELL_SIZE; //X시작(버킷 기준)
		int colEnd = (mCol + mWidth - 1) / CELL_SIZE; //X끝(버킷 기준)

		Set<Integer> checked = new HashSet<>(); 

		for (int r = rowStart; r <= rowEnd + 1; r++) {
			for (int c = colStart; c <= colEnd + 1; c++) {
				if (r >= MAX || c >= MAX) continue; //범위 넘어간 경우

				long key = getKey(r, c); // 키 만들기
				if (!grid.containsKey(key)) continue; // 해당 키를 포함하지않는경우 

				for (int otherId : grid.get(key)) { //해당 안에 있는 사각형의 IDX가져옴
					if (checked.contains(otherId)) continue; //이미 한번 본 사각형일 경우
					checked.add(otherId); //방문 처리

					Square cur = squareByIdx[otherId]; //사각형 가져오기


					Square top = draw.y < cur.y ? draw : cur; 
					Square bottom = draw.y < cur.y ? cur : draw;
					Square left = draw.x < cur.x ? draw : cur;
					Square right = draw.x < cur.x ? cur : draw;

					boolean isOverlapping = top.y + top.h > bottom.y &&left.x + left.w > right.x;
					//겹침 처리
					if (!isOverlapping) continue;
					
					//겹치는 경우만 union
					union(id, otherId);
				}
			}
		}
		
		for (int r = rowStart; r <= rowEnd; r++) {
			for (int c = colStart; c <= colEnd; c++) {
				long key = getKey(r, c);
				grid.computeIfAbsent(key, k -> new ArrayList<>()).add(id);
			}
		} // 내가 포함되는 버킷에만 넣기

		return size[find(id)]; //내가 포함된 그룹의 크기
	}

	public int getRectCount(int mID) {
		if (!midToIdx.containsKey(mID)) return 0; //사각형이 존재안하는경우
		int id = midToIdx.get(mID); //있는 경우 압축한 idx가져오기
		return size[find(id)]; //자신이 포함된 그룹의 크기 가져오기
	}

	public int countGroup() {
		return groups.size(); // 존재하는 그룹의 크기
	}
}
