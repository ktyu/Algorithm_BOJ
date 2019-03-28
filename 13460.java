import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {this.x=x; this.y=y;}
}

public class Main {

    public static int answer = Integer.MAX_VALUE;

    public static char[][] copy(char[][] map_origin) {
        int n = map_origin.length;
        int m = map_origin[0].length;

        char[][] map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = map_origin[i][j];
            }
        }
        return map;
    }


    public static boolean up(char[][] map, int moved, Point red, Point blue) {
        int i;

        if(red.y == blue.y && blue.x < red.x) { // blue���� �����ؾ߸� �ϴ� ���
            for(i=blue.x-1; i>=0 && map[i][blue.y] == '.'; i--);
            if(map[i][blue.y] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[i+1][blue.y] = 'B';
                blue.x = i+1;
            }

            for(i=red.x-1; i>=0 && map[i][red.y] == '.'; i--);
            if(map[i][red.y] == 'O') { // red�� ���� ��� ����
                answer = Integer.min(answer, moved);
                return true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[i+1][red.y] = 'R';
                red.x = i+1;
            }

        }

        else { // red���� ����
            boolean redIn = false;

            for(i=red.x-1; i>=0 && map[i][red.y] == '.'; i--);
            if(map[i][red.y] == 'O') { // red�� ���� ��� �����ε� �ڿ� blue���� ������
                redIn = true;
                map[red.x][red.y] = '.';
            }
            else { // '#' �� ��츸 ����
                map[red.x][red.y] = '.';
                map[i+1][red.y] = 'R';
                red.x = i+1;
            }

            for(i=blue.x-1; i>=0 && map[i][blue.y] == '.'; i--);
            if(map[i][blue.y] == 'O') // blue�� ������ ����
                return true;

            else { // '#'�̰ų� 'R'�� ���
                map[blue.x][blue.y] = '.';
                map[i+1][blue.y] = 'B';
                blue.x = i+1;
            }

            // blue���� ������ ���� ��� red�� �������Ƿ� ����
            if(redIn) {
                answer = Integer.min(answer, moved);
                return true;
            }
        }

        return false;
    }



    public static boolean down(char[][] map, int moved, Point red, Point blue) {
        int i;

        if(red.y == blue.y && red.x < blue.x) { // blue���� �����ؾ߸� �ϴ� ���
            for(i=blue.x+1; i<map.length && map[i][blue.y] == '.'; i++);
            if(map[i][blue.y] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[i-1][blue.y] = 'B';
                blue.x = i-1;
            }

            for(i=red.x+1; i<map.length && map[i][red.y] == '.'; i++);
            if(map[i][red.y] == 'O') { // red�� ���� ��� ����
                answer = Integer.min(answer, moved);
                return true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[i-1][red.y] = 'R';
                red.x = i-1;
            }

        }

        else { // red���� ����
            boolean redIn = false;

            for(i=red.x+1; i<map.length && map[i][red.y] == '.'; i++);
            if(map[i][red.y] == 'O') { // red�� ���� ���
                map[red.x][red.y] = '.';
                redIn = true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[i-1][red.y] = 'R';
                red.x = i-1;
            }

            for(i=blue.x+1; i<map.length && map[i][blue.y] == '.'; i++);
            if(map[i][blue.y] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[i-1][blue.y] = 'B';
                blue.x = i-1;
            }

            // blue���� ������ ���� ��� red�� �������Ƿ� ����
            if(redIn) {
                answer = Integer.min(answer, moved);
                return true;
            }
        }

        return false;
    }


    public static boolean left(char[][] map, int moved, Point red, Point blue) {
        int i;

        if(red.x == blue.x && blue.y < red.y) { // blue���� �����ؾ߸� �ϴ� ���
            for(i=blue.y-1; i>=0 && map[blue.x][i] == '.'; i--);
            if(map[blue.x][i] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[blue.x][i+1] = 'B';
                blue.y = i+1;
            }

            for(i=red.y-1; i>=0 && map[red.x][i] == '.'; i--);
            if(map[red.x][i] == 'O') { // red�� ���� ��� ����
                answer = Integer.min(answer, moved);
                return true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[red.x][i+1] = 'R';
                red.y = i+1;
            }

        }

        else { // red���� ����
            boolean redIn = false;

            for(i=red.y-1; i>=0 && map[red.x][i] == '.'; i--);
            if(map[red.x][i] == 'O') { // red�� ���� ���
                map[red.x][red.y] = '.';
                redIn = true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[red.x][i+1] = 'R';
                red.y = i+1;
            }

            for(i=blue.y-1; i>=0 && map[blue.x][i] == '.'; i--);
            if(map[blue.x][i] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[blue.x][i+1] = 'B';
                blue.y = i+1;
            }

            // blue���� ������ ���� ��� red�� �������Ƿ� ����
            if(redIn) {
                answer = Integer.min(answer, moved);
                return true;
            }
        }

        return false;
    }

    public static boolean right(char[][] map, int moved, Point red, Point blue) {
        int i;

        if(red.x == blue.x && red.y < blue.y) { // blue���� �����ؾ߸� �ϴ� ���
            for(i=blue.y+1; i<map[0].length && map[blue.x][i] == '.'; i++);
            if(map[blue.x][i] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[blue.x][i-1] = 'B';
                blue.y = i-1;
            }

            for(i=red.y+1; i<map[0].length && map[red.x][i] == '.'; i++);
            if(map[red.x][i] == 'O') { // red�� ���� ��� ����
                answer = Integer.min(answer, moved);
                return true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[red.x][i-1] = 'R';
                red.y = i-1;
            }

        }

        else { // red���� ����
            boolean redIn = false;

            for(i=red.y+1; i<map[0].length && map[red.x][i] == '.'; i++);
            if(map[red.x][i] == 'O') { // red�� ���� ���
                map[red.x][red.y] = '.';
                redIn = true;
            }
            else { // '#' �̰ų� 'B'�� ���
                map[red.x][red.y] = '.';
                map[red.x][i-1] = 'R';
                red.y = i-1;
            }


            for(i=blue.y+1; i<map[0].length && map[blue.x][i] == '.'; i++);
            if(map[blue.x][i] == 'O') // blue�� ������ ����
                return true;

            else { // ���� '#' �� ��츸 ����
                map[blue.x][blue.y] = '.';
                map[blue.x][i-1] = 'B';
                blue.y = i-1;
            }

            // blue���� ������ ���� ��� red�� �������Ƿ� ����
            if(redIn) {
                answer = Integer.min(answer, moved);
                return true;
            }
        }

        return false;
    }




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        char[][] map = new char[n][m];
        Point red = null;
        Point blue = null;

        for(int i=0; i<n; i++) {
            String str = sc.nextLine();
            for(int j=0; j<m; j++) {
                char c = str.charAt(j);
                if(c == 'R')
                    red = new Point(i, j);
                else if(c == 'B')
                    blue = new Point(i, j);

                map[i][j] = c;
            }
        }

        // ��Ʈ����ũ�� ���Ʈ ����
        for(int bit=(1 << 20)-1; bit>=0; bit--) {

            // �� ���� ���̽� ����
            int tc = bit;

            // �� ����
            char[][] mapNew = copy(map);
            Point redNew = new Point(red.x, red.y);
            Point blueNew = new Point(blue.x, blue.y);

            for(int i=1; i<=10; i++) {
                int r = tc & 3;

                if(r==0) {
                    boolean exit = up(mapNew, i, redNew, blueNew);
                    if(exit) break;
                }

                else if(r==1) {
                    boolean exit = down(mapNew, i, redNew, blueNew);
                    if(exit) break;

                }
                else if(r==2) {
                    boolean exit = left(mapNew, i, redNew, blueNew);
                    if(exit) break;
                }
                else if(r==3) {
                    boolean exit = right(mapNew, i, redNew, blueNew);
                    if(exit) break;
                }

                tc >>= 2;
            }

        }

        if(answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
