import java.util.*;

// 2/9 �ۼ� (�ڹ�)

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // �Է� �ޱ�
        int n = sc.nextInt();
        sc.nextLine();

        int[][] map = new int[n][n];
        for(int i=0; i<n; i++) {
            String[] nums = sc.nextLine().split("");
            for(int j=0; j<n; j++) {
                map[j][i] = Integer.valueOf(nums[j]);
            }
        }

        // map�� ���� 1�̸� ���������� �ȵ� ����Ʈ, 2���ʹ� ������ȣ
        int groupNum = 1;
        List<Integer> list = new ArrayList<>();

        // map�� �� ��ǥ�� �� ���鼭 ����Ʈ�� �����ϸ� dfs�� ���� ��� ��ȣ �ο�
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[j][i] == 1) {
                    aptCnt = 0;
                    dfs(map, ++groupNum, j, i);
                    list.add(aptCnt);
                }
            }
        }

        // ���� ���� ���
        System.out.println(groupNum-1);

        // �����ؼ� �� ������ ���
        Collections.sort(list);
        for(int num : list) {
            System.out.println(num);
        }
    }

    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};
    public static int aptCnt;

    public static void dfs(int[][]map, int groupNum, int x, int y) {

        // ���� ��ǥ�� ����Ʈ 1�� ����
        map[x][y] = groupNum;
        aptCnt++;

        // �����¿� �˻�
        for(int i=0; i<4; i++) {
            int xx = x+dx[i];
            int yy = y+dy[i];

            if(xx < 0 || xx >= map.length)
                continue;
            if(yy < 0 || yy >= map.length)
                continue;

            if(map[xx][yy] == 1) {
                dfs(map, groupNum, xx, yy);
            }
        }
    }

}
