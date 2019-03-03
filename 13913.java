import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int [][] map = new int[200002][2]; // �ڿ� -> �ε��� 0 :�湮�ϴµ� ��� ���, �ε���1 : ���� ��

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        map[n][0] = 1; // �湮��� 1�� ���� (����� �� 1 �������)
        map[n][1] = -1; // �������� ���� ���� -1�� ǥ��

        while(!q.isEmpty()) {
            int p = q.poll();

            if(p==k)
                break;

            if(p+1 < 200001 && map[p+1][0] == 0) {
                q.offer(p+1);
                map[p+1][0] = map[p][0]+1;
                map[p+1][1] = p;
            }

            if(p-1 >= 0 && map[p-1][0] == 0) {
                q.offer(p-1);
                map[p-1][0] = map[p][0]+1;
                map[p-1][1] = p;
            }

            if(2*p < 200001 && map[2*p][0] == 0) {
                q.offer(2*p);
                map[2*p][0] = map[p][0]+1;
                map[2*p][1] = p;
            }

        }

        // ��� ���
        System.out.println(map[k][0]-1);

        // ������ �� ������
        int before = k;
        List<Integer> l = new ArrayList<>();
        while(before != -1) {
            l.add(before);
            before = map[before][1];
        }
        
        // ����Ʈ �Ųٷ� ���
        for(int i=l.size()-1; i>=0; i--)
            System.out.print(l.get(i) + " ");
    }
}