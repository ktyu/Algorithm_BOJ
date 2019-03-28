import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {this.x=x; this.y=y;}
}

class Turn {
    int time;
    char d;
    Turn(int time, char d) {this.time=time; this.d=d;}
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        boolean[][] apple = new boolean[n][n];
        int apple_cnt = sc.nextInt();
        for(int i=0; i<apple_cnt; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            apple[x-1][y-1] = true;
        }


        int turn_cnt = sc.nextInt();
        Queue<Turn> turns = new LinkedList<>();
        sc.nextLine();
        for(int i=0; i<turn_cnt; i++) {
            String str = sc.nextLine().trim();
            turns.add(new Turn(Integer.parseInt(str.substring(0, str.length()-2)), str.charAt(str.length()-1)));
        }

        boolean[][] snack = new boolean[n][n];
        snack[0][0] = true;
        Deque<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));


        int time = 0;
        int direction = 0; // 0���� ��,��,��,��
        while(true) {
            time++;
            Point head = q.peek();

            // �Ӹ��� �̵��� ��ġ ���
            int x = -1;
            int y = -1;
            switch(direction) {
                case 0:
                    x = head.x;
                    y = head.y + 1;
                    break;
                case 1:
                    x = head.x + 1;
                    y = head.y;
                    break;
                case 2:
                    x = head.x;
                    y = head.y-1;
                    break;
                case 3:
                    x = head.x-1;
                    y = head.y;
                    break;
            }

            // ���� ����
            if(x<0 || x>=n || y<0 || y>=n || snack[x][y])
                break;

            // �� �Ӹ� �̵�
            snack[x][y] = true;
            q.addFirst(new Point(x, y));

            // ����� ������ ����Ծ� ����
            if(apple[x][y])
                apple[x][y] = false;

            // ����� ������ ���� ����
            else {
                Point p = q.removeLast();
                snack[p.x][p.y] = false;
            }

            // ȸ������ Ȯ��
            Turn t = turns.peek();
            if(t != null && t.time==time) {
                if(t.d == 'L') {
                    direction -= 1;
                    if(direction == -1)
                        direction = 3;
                }

                else if(t.d == 'D') {
                    direction = (direction + 1) % 4;
                }
                turns.remove();
            }
        }

        System.out.println(time);
    }
}