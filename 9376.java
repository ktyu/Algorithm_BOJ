import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {this.x=x; this.y=y;}
}

public class Main {
    
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    
    public static int[][] bfs(char[][] map, Point start) {
        int h = map.length;
        int w = map[0].length;

        int[][] ret = new int[h][w];
        for(int i=0; i<h; i++)
            for(int j=0; j<w; j++)
                ret[i][j] = -1;


        Deque<Point> q = new LinkedList<>();
        ret[start.x][start.y] = 0;
        q.addLast(start);

        while(!q.isEmpty()) {
            Point p = q.remove();

            for(int i=0; i<4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if(x<0 || x>=h || y<0 || y>=w || map[x][y] == '*')
                    continue;

                if(map[x][y] == '.' && ret[x][y] == -1) {
                    ret[x][y] = ret[p.x][p.y];
                    q.addFirst(new Point(x, y));
                }

                if(map[x][y] == '#' && ret[x][y] == -1) {
                    ret[x][y] = ret[p.x][p.y] + 1;
                    q.addLast(new Point(x, y));
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tcnum = sc.nextInt();
        sc.nextLine();

        while(tcnum-- > 0) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            sc.nextLine();

            char[][] map = new char[h+2][w+2];
            Point p1 = null;
            Point p2 = null;

            
            // �����¿쿡 1�پ� padding �߰�(���� ������ ������ �̵������ϹǷ� �ۿ��� ����� 1������ ��������)
            for(int i=0; i<w+2; i++) {
                map[0][i] = '.';
                map[h+1][i] = '.';
            }
            for(int i=1; i<h+1; i++) {
                map[i][0] = '.';
                map[i][w+1] = '.';
            }


            // �� �Է¹ޱ�
            for(int i=1; i<=h; i++) {
                String str = sc.nextLine().trim();
                for(int j=1; j<=w; j++) {
                    map[i][j] = str.charAt(j-1);
                    if(map[i][j] == '$') {
                        map[i][j] = '.'; // �˼� �ִ� �� . ���� ��ü

                        if(p1 == null)
                            p1 = new Point(i, j);
                        else
                            p2 = new Point(i, j);
                    }
                }
            }
            

            // ���� ����� bfs ������ ���Ϲ���
            int[][] p1Cost = bfs(map, p1);
            int[][] p2Cost = bfs(map, p2);
            int[][] startCost = bfs(map, new Point(0, 0));

            
            // ������ �� �� �ּҺ���� �� ã��
            int answer = Integer.MAX_VALUE;
            for(int i=0; i<h+2; i++) {
                for(int j=0; j<w+2; j++) {
                    if(p1Cost[i][j] == -1 || p2Cost[i][j] == -1 || startCost[i][j] == -1)
                        continue;

                    int sum = p1Cost[i][j] + p2Cost[i][j] + startCost[i][j];

                    // ���̸� ��뿡�� 2�� ���� (������� ���ÿ� �������Ƿ� 1�� ���� ��)
                    if(map[i][j] == '#')
                        sum -= 2;

                    answer = Integer.min(answer, sum);
                }
            }

            System.out.println(answer);
        }

    }
}