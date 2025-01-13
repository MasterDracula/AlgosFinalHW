package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;
import java.util.List;
import java.util.stream.Collectors;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog; // Позволяет логировать. Использовать после каждой атаки юнита

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {

        List<Unit> userUnits = playerArmy.getUnits().stream().filter(Unit::isAlive).collect(Collectors.toList());
        List<Unit> compUnits = computerArmy.getUnits().stream().filter(Unit::isAlive).collect(Collectors.toList());
        while (!userUnits.isEmpty() && !compUnits.isEmpty()) {
            userUnits.sort((u1, u2) -> Integer.compare(u2.getBaseAttack(), u1.getBaseAttack()));
            compUnits.sort((u1, u2) -> Integer.compare(u2.getBaseAttack(), u1.getBaseAttack()));
            for (int i = 0; i < Math.max(userUnits.size(), compUnits.size()); i++) {
                if (i < userUnits.size() && userUnits.get(i).isAlive()) {
                    Unit unit = userUnits.get(i).getProgram().attack();
                    printBattleLog.printBattleLog(userUnits.get(i), unit);
                }
                if (i < compUnits.size() && compUnits.get(i).isAlive()) {
                    Unit unit = compUnits.get(i).getProgram().attack();
                    printBattleLog.printBattleLog(compUnits.get(i), unit);
                }
                userUnits.removeIf(unit -> !unit.isAlive());
                compUnits.removeIf(unit -> !unit.isAlive());
            }
        }
    }
}
/*
При одинаковом количестве юнитов в армиях, итоговая сложность алгоритма будет O(n log n),
где n — количество юнитов в армии.
Это связано с тем, что сортировка выполняется за O(n log n),
а основной цикл выполняется пропорциональное количеству юнитов время.
Сложность алгоритма получилась лучше заявленного в ТЗ (O(n2*log n))
 */