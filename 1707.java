import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tcCnt = sc.nextInt();

        for (int tc = 0; tc < tcCnt; tc++) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Integer>> nodes = new ArrayList<>(n+1);
            for (int i = 0; i < n + 1; i++) {
                nodes.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                nodes.get(a).add(b);
                nodes.get(b).add(a);
            }

            int[] check = new int[nodes.size()];

            boolean isBip = true;
            for(int i=1; isBip && i<nodes.size(); i++) {
                if(check[i] == 0)
                    isBip = bfs(nodes, i, check);
            }

            System.out.println(isBip ? "YES" : "NO");
        }
    }

    public static boolean bfs(List nodes, int start, int[] check) {

        Queue<Integer> q = new LinkedList<>(); // �湮�ؾ��� ����

        q.offer(start); // ������ �߰�
        check[start] = 1; // �������� A�׷�

        while(!(q.isEmpty())) {
            int now = q.poll();

            // ���� ��忡�� �� �� �ִ� ��� ������ ���� ����
            for(Object obj : (List)nodes.get(now)) {
                int num = (int)obj;

                // ���� �湮 ���Ѱ��̶��
                if(check[num] == 0) {
                    check[num] = check[now]==1 ? 2 : 1; // ����ο�
                    q.offer(num); // ť�� �߰�
                }

                // �̹� ���ٿ°��̸�
                else {
                    if((check[now] != check[num])) // ���� �ٸ��� �Ѿ��
                        continue;
                    return false; // ���� Ʋ���� false ����
                }
            }
        }

        return true; // �� �� ��ȸ������ true ����
    }
}
