import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][m];

        sc.nextLine();
        for(int i=0; i<n; i++) {
            String str = sc.nextLine().trim();
            String[] arr = str.split("");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.valueOf(arr[j]);
            }
        }

        int maxSum = 0;

        // MAIN idea == ��� ĭ�� ���η� �������ų� ���η� �������ų� 2���� ��츦 ����


        // ��Ʈ����ũ Ȱ��. n x m �� ����� 1���� �迭�� ��Ÿ����
        // 0�� ���Γ��� ��, 1�� ���ι��� ������ ����
        for(int tc=0; tc < (1 << n*m); tc++) {
            String tcStr = String.format("%"+ Integer.toString(m*n) + "s", Integer.toBinaryString(tc)).replace(" ", "0");

            // ���ι��� �������� �� ���ϱ�
            int rowTotalSum = 0;
            for(int rowIdx=0; rowIdx<n; rowIdx++) {
                int rowSum = 0;
                for(int colIdx=0; colIdx<m; colIdx++) {
                    if(tcStr.charAt(m*rowIdx + colIdx) == '0')
                        rowSum = rowSum * 10 + map[rowIdx][colIdx];
                    else {
                        rowTotalSum += rowSum;
                        rowSum = 0;
                    }
                }
                rowTotalSum += rowSum;
            }

            // ���ι��� �������� �� ���ϱ�
            int colTotalSum = 0;
            for(int colIdx=0; colIdx<m; colIdx++) {
                int colSum = 0;
                for(int rowIdx=0; rowIdx<n; rowIdx++) {
                    if(tcStr.charAt(m*rowIdx + colIdx) == '1')
                        colSum = colSum * 10 + map[rowIdx][colIdx];
                    else {
                        colTotalSum += colSum;
                        colSum = 0;
                    }
                }
                colTotalSum += colSum;
            }

            // ��ü ��
            int sum = rowTotalSum + colTotalSum;
            if(maxSum < sum)
                maxSum = sum;
        }

        System.out.println(maxSum);
    }
}
