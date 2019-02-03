import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int primeCnt = 0;
        boolean[] arr = new boolean[n+1]; // false == �������� ����, true == ������

        for(int i=2; i<=n; i++) {
            if(!(arr[i])) { // �������� �ʾ�����
                primeCnt++;
                if(i>=m)
                    System.out.println(i);

                for(int j=i*2; j<=n; j+=i) { // j=i*i �� �ʱ�ȭ�ص� ������ n^2�� int ������ �Ѿ �� �����Ƿ� 2i�� �ʱ�ȭ
                    arr[j] = true; // ���� ���� ������ true�� �����ʿ� ����
                }
            }
        }
    }
}
