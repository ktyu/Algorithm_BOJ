import java.util.*;

public class Main {

	static class Point {
		int x,y;
		Point(int x, int y) {this.x=x; this.y=y;}
		
		static int distance(Point a, Point b) {
			return (Math.abs(a.x-b.x) + Math.abs(a.y-b.y));
		}
	}
	
	static int N;
    static int M;
    static ArrayList<Point> chickens; 
    static ArrayList<Point> homes;
    static ArrayList<ArrayList<Integer>> home_to_chicken;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        home_to_chicken = new ArrayList<>();
        
        // �Է¹ޱ�
        for(int x=0; x<N; x++) {
        	for(int y=0; y<N; y++) {
        		int num = sc.nextInt();
        		if(num == 1) {
        			homes.add(new Point(x, y));
        			home_to_chicken.add(new ArrayList<>());
        		}
        		
        		else if(num == 2) {
        			chickens.add(new Point(x, y));
        		}
        	}
        }
        
        // �� ������ �� ġŲ�������� �Ÿ� ����ؼ� ����
        for(int i=0; i<homes.size(); i++) {
        	Point h = homes.get(i);
        	ArrayList<Integer> distance = home_to_chicken.get(i);
        	
        	for(int j=0; j<chickens.size(); j++) {
        		Point c = chickens.get(j);
        		distance.add(Point.distance(h, c));
        	}
        }
        
        
        // next_permu�� �� �迭 (�츱 ġŲ���� 1�� ��ŷ)
        int[] live = new int[chickens.size()];
        for(int i=0; i<M; i++) live[chickens.size()-1-i] = 1;

        
        // ġŲ������ C m �� ��ŭ �ݺ�
        while(live != null) {
        	int sum = 0;
        
        	// ������ �ݺ�
        	for(int i=0; i<homes.size(); i++) {

        		// �ڱ������� ���� ����� ġŲ�Ÿ� ã�� sum�� ���ϱ�
        		List<Integer> list = home_to_chicken.get(i);
        		int min = Integer.MAX_VALUE;
        		
        		for(int j=0; j<list.size(); j++) {
        			if(live[j] == 0)
        				continue;
        			min = Integer.min(min, list.get(j));
        		}
        		
        		sum += min;
        	}
        		
        	answer = Integer.min(answer, sum);
        	live = next_permu(live);
        }
        
        System.out.println(answer);
    }
    
    public static int[] next_permu(int[] arr_origin) {
    	int[] arr = arr_origin.clone();
    	
    	// �ڿ������� �� �� ���������� ������ �� ������ �ε��� i ã��
    	int i = arr.length-1;
    	while(i>0 && arr[i-1] >= arr[i]) i--;
    	if(i==0) return null;
    	
    	// �ڿ������� arr[i-1]���� ū ���߿� ���� ������ ã��
    	// -> (�ڿ������� ���������̹Ƿ� arr[i-1]���� �۰ų� ���� ��쿡�� ��� ����)
    	int j = arr.length-1;
    	while(arr[i-1] >= arr[j]) j--;
    	
    	// i-1�� j �ٲٱ�
    	int temp = arr[j];
    	arr[j] = arr[i-1];
    	arr[i-1] = temp;
    	
    	// i��° ~ �ǳ����� �������ֱ� (������ ������ swap�ϸ��)
    	j = arr.length-1;
    	while(i<j) {
    		temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    		i++; j--;
    	}
    	
    	return arr;
    }
}