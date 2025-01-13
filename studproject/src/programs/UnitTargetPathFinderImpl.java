package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.UnitTargetPathFinder;

import java.util.*;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {
    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
        // Ваше решение
        class Note {
            int x, y, distance, heuristic;
            Note parent;

            public Note(int x, int y, int distance, int heuristic, Note parent) {
                this.x = x;
                this.y = y;
                this.distance = distance;
                this.heuristic = heuristic;
                this.parent = parent;
            }
        }
        int WIDTH = 27;
        int HEIGHT = 21;
        boolean[][] visited = new boolean[HEIGHT][WIDTH];
        int[][] distance = new int[HEIGHT][WIDTH];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[attackUnit.getxCoordinate()][attackUnit.getxCoordinate()] = 0;
        PriorityQueue<Note> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance + n.heuristic));
        int heuristic = Math.abs(attackUnit.getxCoordinate() - targetUnit.getxCoordinate()) + Math.abs(attackUnit.getyCoordinate() - targetUnit.getyCoordinate());
        pq.add(new Note(attackUnit.getxCoordinate(), attackUnit.getyCoordinate(), 0, heuristic, null));

        while (!pq.isEmpty()) {
            Note c = pq.poll();
            if (visited[c.y][c.x]) {
                continue;
            }
            visited[c.y][c.x] = true;
            if (c.x == targetUnit.getxCoordinate() && c.y == targetUnit.getyCoordinate()) {
                List<Edge> path = new ArrayList<>();
                while (c != null) {
                    path.add(new Edge(c.x, c.y));
                    c = c.parent;
                }
                Collections.reverse(path);
                return path;
            }
            int[] x = {0, 0, 1, -1};
            int[] y = {-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int x1 = c.x + x[i];
                int y1 = c.y + y[i];
                if (x1 >= 0 && x1 <= WIDTH && y1 >= 0 && y1 <= HEIGHT && !visited[x1][y1]) {
                    int newDistance = c.distance + 1;
                    int newHeuristic = Math.abs(x1 - targetUnit.getxCoordinate()) + Math.abs(y1 - targetUnit.getyCoordinate());
                    if (x1 < distance[y1][x1]) {
                        distance[y1][x1] = newDistance;
                        pq.add(new Note(x1, y1, newDistance, newHeuristic, c));
                    }
                }

            }
        }
        return new ArrayList<>();
    }
}
/*
Итоговая сложность:
Инициализация массивов: O(HEIGHT⋅WIDTH)=O(27⋅21)=O(567).
Начальная установка расстояния: O(1).
Добавление начального узла: O(1).
Основной цикл поиска пути:
Извлечение из очереди: O(log(n)), где n — количество узлов в очереди.
Обработка каждого узла:
Проверка и обновление посещенных состояний: O(1).
Добавление новых узлов: O(1).
Таким образом, общая алгоритмическая сложность данного кода составляет O(nlogn)(
где n — количествo количество возможных состояний на карте), что лучше, от заявленного в ТЗ O(n*m)
 */