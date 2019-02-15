import sys
from collections import deque

r, c = map(int, sys.stdin.readline().strip().split())

water = [[0 for __ in range(c)] for _ in range(r)]
queue = deque()
alive = deque()

for i in range(r):
    row = list(sys.stdin.readline().strip())
    for j in range(c):
        if row[j] == '.':
            pass # ���� ���� ���� 0 �״��

        elif row[j] == '*':
            water[i][j] = 1 # ���� ��� �� ��
            queue.append((i, j)) # ���� �������Ѿ� �ϹǷ� ť�� ����

        elif row[j] == 'X':
            water[i][j] = -1 # ���� ��(��)�� -1

        elif row[j] == 'D':
            water[i][j] = -1 # �������� �� ���忡�� ���� ��
            goal = (i, j) # ������

        elif row[j] == 'S':
            alive.append((i, j)) # ��Ƴ����ִ� ����ġ���� ��ġ

dx = [0, 1, -1, 0]
dy = [1, 0, 0, -1]
moved = 0

# ť�鿡 ���Ұ� �Ѱ��� ������ ����
while len(queue) + len(alive) > 0:

    # �� ���� 1ȸ ����
    nextQueue = deque()
    while len(queue) != 0:
        tu = queue.popleft()
        for i in range(4):
            x = tu[0] + dx[i]
            y = tu[1] + dy[i]

            # �� ���̰�, ���� ������ �� �ִ� ���̸�, ���� �����
            if 0 <= x < r and 0 <= y < c and (water[x][y] == 0 or water[x][y] == 2):
                water[x][y] = 1
                nextQueue.append((x, y))
    queue = nextQueue

    # ����ġ 1�� ����
    if len(alive) == 0:
        print("KAKTUS")
        exit(0)

    moved += 1
    nextAlive = deque()
    while len(alive) != 0:
        tu = alive.popleft()

        for i in range(4):
            x = tu[0] + dx[i]
            y = tu[1] + dy[i]

            # ������ ���� ���� �˻�
            if (x, y) == goal:
                print(moved)
                exit(0)

            # �� ���̰� ���� ������ ����
            if 0 <= x < r and 0 <= y < c and water[x][y] == 0:
                water[x][y] = 2 # �̹� �����°��� �ٽ� ���ʿ� �����Ƿ�
                nextAlive.append((x, y))

    alive = nextAlive

print("KAKTUS")
