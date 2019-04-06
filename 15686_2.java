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
    static boolean[] check;

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

        
        check = new boolean[chickens.size()]; // �츱 ġŲ�� �ߺ����� ���ϰ� ��ŷ�� ����
        dfs(new int[M], 0, 0);

        System.out.println(answer);
    }

    // DFS ������ ���ȣ���ϴ� �Լ�
    
    // �Լ��� ����� sel �迭�� sel_idx ��°�� ��� �� ���Ҹ� �ݺ����ȿ��� ���ȣ���ϸ� �� �־�°�.
    // 
    public static void dfs(int[] sel, int sel_idx, int arr_idx) {
        // ������ �ĺ��ڸ� �� ������ ���
        if(sel_idx == M) {
            int sum = 0;

            // �� ������ ���õ� ġŲ�������� �Ÿ� �� �ּҸ� ���ؼ� sum�� ����
            for(List distance : home_to_chicken) {
                int min = Integer.MAX_VALUE;
                for(int idx : sel)
                    min = Integer.min(min, (Integer)distance.get(idx));

                sum += min;
            }

            answer = Integer.min(answer, sum);
            return; // ���� ������� �ȵ�...
        }


        // 2���� �ʿ����� dx,dy�� �������� ���⼱ 1���� �迭�� ��� ���Ұ� ���� �� �ִ� �ĺ�����
        // �����̹Ƿ� �ڱ⺸�� �տ� �ִ°� �� �ʿ� �����Ƿ� arr_idx �����Ἥ �ڿ������� ������
        for(int i=arr_idx; i<chickens.size(); i++) {
            if(check[i]) // �ߺ� ���� ����
                continue;

            sel[sel_idx] = i; // sel_idx ��°�� i(�ݺ����ε���) ��°�� �ĺ��ڸ� �־���
            
            check[i] = true; // �ش� �ĺ��ڴ� �ߺ���� x  <<- �̰� �߿�!!
            dfs(sel, sel_idx+1, i+1); // ���� sel_idx+1 ��°�� ������ �����ϱ� ���� ���ȣ��, ������ ������� �����̹Ƿ� ���粨���� �ڿ����� ���� ��
            check[i] = false; // �ٸ� Case���� �ٽ� �ĺ��� �� �� �����Ƿ� false�� ����
        }

    }

}