### BOJ���� python3���� ����ȵǰ� pypy3�� �����

import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

# points�� idx��° ���Ҹ� ��������� �ؼ�, points �� �ִ� ��� ���鿡 ���� ����� �����ϴ� �Լ�
def getCost(map, idx, points):
    q = deque()
    cost = [[-1]*w for _ in range(h)] # bfs Ž���Ҷ� �� ����
    points_cost = [-1]*len(points) # ������ ����Ʈ (�־��� ���鿡 ���µ� �ʿ��� ���)

    q.append(points[idx])
    cost[points[idx][0]][points[idx][1]] = 0
    points_cost[idx] = 0
    find_cnt = 1

    # �̹� ���������� ä��� cnt �����̶� �ø���
    for i in range(idx):
        points_cost[i] = graph[i][idx]
        find_cnt += 1

    # bfs�� map ��ü Ž��
    while len(q) != 0:
        # �ʿ��� ���� �� ã������ �׳� ������
        if find_cnt == len(points):
            break

        now = q.popleft()

        for i in range(4):
            x = now[0] + dx[i]
            y = now[1] + dy[i]

            if 0<=x<h and 0<=y<w and map[x][y] != 'x' and cost[x][y] == -1:
                cost[x][y] = cost[now[0]][now[1]] + 1
                q.append((x, y))

                if (x, y) in points:
                    ii = points.index((x, y))
                    if points_cost[ii] != -1:
                        continue
                    find_cnt += 1
                    points_cost[ii] = cost[x][y]

    return points_cost


def dfs(start, dest, sum, point_cnt, visited_cnt):
    global answer
    global check

    if graph[start][dest] == -1:
        return

    # �� �湮 ������ �ּҰ� �����ϰ� ����
    if visited_cnt == point_cnt:
        answer = min(answer, sum)
        return

    # ���� �湮 ���Ѱ��� ������ ��ͷ� �湮
    for next in range(1, point_cnt+1):
        if not(check[next]) and graph[start][dest] != -1:

            check[next] = True

            if answer >= sum: # ���� ��� �� ��ġ�� ������(�̹� �ּҰ��� �ƴѰ� �ȴٸ�) ���� ����
                dfs(dest, next, sum+graph[dest][next], point_cnt, visited_cnt+1)

            check[next] = False


# ���� �Լ�
while True:
    l = sys.stdin.readline().strip().split()
    w = int(l[0])
    h = int(l[1])
    if w == 0 and h == 0:
        exit(0)

    # ���� �Է¹ް� ������, �������� ����
    map = []
    dirty_points = []

    for r in range(h):
        row = sys.stdin.readline().strip()
        for c in range(w):
            if row[c] == '*':
                dirty_points.append((r, c))
            elif row[c] == 'o':
                start = (r, c)
        map.append(row)

    # ������, ���������� �� ��� ����� graph�� ���� (bfs Ž�����)
    points = [start] + dirty_points
    graph = []
    for idx in range(len(points)):
        graph.append(getCost(map, idx, points))

    # ��� ���̽��� �غ��� �ּҺ���� ���ϱ�(���ǿ� ��ȸ ������ ����)
    # DFS�� ��� ȣ��������, ���� ���� ���ϴ� �Լ��� �ص� ����
    global answer
    global check
    answer = 2000000000
    for dest in range(1, len(dirty_points)+1):
        check = [True] + [False] * len(dirty_points)
        dfs(0, dest, graph[0][dest], len(dirty_points), 0)

    if answer == 2000000000:
        print(-1)
    else:
        print(answer)
