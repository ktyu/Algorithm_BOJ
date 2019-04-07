import java.util.*;

public class Main {

    static class Point {
        int x,y;
        Point(int x, int y) {this.x=x; this.y=y;}
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // �Է� �ޱ�
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        map = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int day = 0;
        boolean changed;

        // ���� map�� ���¿��� ���� ������ �̷� �͵��� �˻�
        do {
            changed = false;
            check = new boolean[n][n];
            int[][] map_new = new int[n][n];

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    // �Ʒ� ���ȣ�� ���߿� ������ �̷ﳽ �����̸� �ٽ� �湮�� �ʿ����
                    if(check[i][j])
                        continue;

                    // ������ �̷� �������� ����Ʈ
                    List<Point> union = new ArrayList<>();
                    union.add(new Point(i, j));
                    check[i][j] = true;

                    // ���� ������ ������ DFS�� ã�� ������ ��
                    dfs(union, i, j);
                    if(union.size() == 1)
                        continue;

                    // ������ ������� ������� �����ϴ� ��� map_new�� ���
                    changed = true;
                    int sum = 0;
                    for(Point p : union) {
                        sum += map[p.x][p.y];
                    }
                    sum /= union.size();

                    for(Point p : union) {
                        map_new[p.x][p.y] = sum;
                    }
                }
            }

            // �������� ���� ������ ������ ���� ���
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if (map_new[i][j] == 0)
                        map_new[i][j] = map[i][j];
                }
            }

            // �� ������ �����ϰ�, �ٲ����� �ִ��� üũ
            map = map_new;
            if(changed)
                day++;

        } while(changed);

        
        System.out.println(day);
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int n;
    static int l;
    static int r;

    static boolean[][] check;
    static int[][] map;

    static void dfs(List<Point> union, int x, int y) {

        for(int i=0; i<4; i++) {
            int xx = x+dx[i];
            int yy = y+dy[i];

            if(xx<0 || xx>=n || yy<0 || yy>=n || check[xx][yy])
                continue;

            int val = Math.abs(map[x][y] - map[xx][yy]);
            if(l <= val && val <= r) {
                union.add(new Point(xx, yy));
                check[xx][yy] = true;
                dfs(union, xx, yy);
            }
        }
    }
}