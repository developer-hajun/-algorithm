import java.util.TreeSet;

class UserSolution
{
	// 기존 TreeSet 배열 선언은 그대로 유지합니다.
	static TreeSet<Integer>[] arr = new TreeSet[101];
	
	public void init()
	{
		for(int i=0;i<101;i++) arr[i]=new TreeSet<>();
	}

	public void add(int mX, int mY)
	{
		arr[mX].add(mY);
	}

	public void remove(int mX, int mY)
	{
		arr[mX].remove(mY);
	}

	public int numberOfCross(int mID)
	{
		int crossCount = 0;
		int x = mID;
		int y = 0;
		
		while(true) {
			Integer right_ceiling = (x < 100) ? arr[x].higher(y) : null;
			Integer left_ceiling = (x > 1) ? arr[x-1].higher(y) : null;

			if(left_ceiling == null && right_ceiling == null) {
				return crossCount;
			} else if(left_ceiling == null) {
				x++;
				y = right_ceiling;
			} else if(right_ceiling == null) {
				x--;
				y = left_ceiling;
			} else {
				if(left_ceiling < right_ceiling) {
					x--;
					y = left_ceiling;
				} else {
					x++;
					y = right_ceiling;
				}
			}
			crossCount++;
		}
	}

	public int participant(int mX, int mY)
	{
	    int x = mX;
	    int y = mY;

	    while(y > 0) {
	        Integer right_floor = (x < 100) ? arr[x].floor(y - 1) : null;
	        Integer left_floor = (x > 1) ? arr[x-1].floor(y - 1) : null;

	        if(left_floor == null && right_floor == null) {
	            break;
	        } else if (left_floor == null) {
	            x++;
	            y = right_floor; 
	        } else if (right_floor == null) {
	            x--;
	            y = left_floor;
	        } else {
	            if (left_floor > right_floor) {
	                x--;
	                y = left_floor;
	            } else {
	                x++;
	                y = right_floor;
	            }
	        }
	    }
	    return x;
	}
}
