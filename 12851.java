import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] map = new int[200002];

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        map[n] = 1;

        int minCost = Integer.MAX_VALUE;
        int cnt = 0;

        while(!q.isEmpty()) {
            int now = q.poll();

            // ���� ��� �湮����� ã�Ƶ� �ּҺ���� �Ѿ�ٸ� �׸� ������
            if(map[now] + 1 > minCost)
                break;

            // �ּҺ���� ã�� ��� ���ʸ� ��ϵ� ��
            if(now == k) {
                cnt++;
                if(map[k] == 0)
                    minCost = map[now];
                continue;
            }

            // �湮������ ���̰�, ���� �湮�� ���߰ų� �� ���ų� ���� ������� �湮�� �� ������ �湮
            if(now+1 < 200002 && (map[now+1] == 0 || (map[now+1] != 0 && map[now]+1 <= map[now+1]))) {
                map[now+1] = map[now] + 1;
                q.offer(now+1);
            }

            if(now-1 >= 0 && (map[now-1] == 0 || (map[now-1] != 0 && map[now]+1 <= map[now-1]))) {
                map[now-1] = map[now] + 1;
                q.offer(now-1);
            }

            if(now*2 < 200002 && (map[now*2] == 0 || (map[now*2] != 0 && map[now]+1 <= map[now*2]))) {
                map[now*2] = map[now] + 1;
                q.offer(now*2);
            }
        }

        System.out.println(map[k]-1);
        System.out.println(cnt);
    }
}
