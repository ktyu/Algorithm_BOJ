import sys

n, m = map(int, sys.stdin.readline().strip().split())

cost = [[-1 for __ in range(n)] for _ in range(m)]
square = []
for _ in range(m):
    square.append(list(map(int, list(sys.stdin.readline().strip()))))

deque = []
deque.append((0, 0))
cost[0][0] = 0

dx = [0, 1, -1, 0]
dy = [1, 0, 0, -1]

while len(deque) != 0:
    p = deque.pop(0)

    for i in range(4):
        x = p[0] + dx[i]
        y = p[1] + dy[i]

        # ������ �湮�� ���� �����ϴ� ��ǥ�϶��� ����
        if 0 <= x < m and 0 <= y < n:

            # �μž��� ���� ����, ���� ���� ��������, ������ ���� ���� ������� ���� (������)
            if square[x][y] == 0 and cost[x][y] == -1:
                cost[x][y] = cost[p[0]][p[1]]
                deque.insert(0, (x, y))

            # �μž��� ���� �ְ�, ���� ���� ��������, ���ݺ��� 1 ū ������� ���� (�ļ���)
            if square[x][y] == 1 and cost[x][y] == -1:
                cost[x][y] = cost[p[0]][p[1]] + 1
                deque.append((x, y))

print(cost[m-1][n-1])
