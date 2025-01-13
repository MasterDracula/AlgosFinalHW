package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        // Ваше решение
        Army compArmy = new Army();
        Map<String, Integer> unitCounts = new HashMap<>();
        for (Unit unit: unitList){
            unitCounts.put(unit.getUnitType(),0);
        }

        unitList.sort((u1,u2)->{
            int ur1= (u1.getBaseAttack()/u1.getCost()+u1.getHealth()/u1.getCost());
            int ur2= (u1.getBaseAttack()/u1.getCost()+u1.getHealth()/u1.getCost());
            if(ur1==ur2){
                return Integer.compare(u2.getBaseAttack(), u1.getBaseAttack());
            }
            return Integer.compare(ur2,ur1);
        });

        for (Unit unit: unitList) {
            String unitThis = unit.getUnitType();
            int unitVolume = unitCounts.get(unitThis);
            if (unitVolume < 11 && compArmy.getPoints() + unit.getCost() <= maxPoints) {
                compArmy.getUnits().add(unit);
                compArmy.setPoints(compArmy.getPoints() + unit.getCost());
                unitCounts.put(unitThis, unitVolume + 1);
            }
        }
        return compArmy;
    }
}

/*
Итоговая сложность:
Инициализация и заполнение HashMap: O(n)
Сортировка списка: O(nlogn)
Первый цикл по unitList: O(n)
Второй цикл по unitList: O(n)
Общая сложность:
Поскольку все три основные операции выполняются последовательно,
общая сложность будет равна сложности самой затратной из них, то есть O(n)+O(nlogn)+O(n) примерно O(nlogn).
Таким образом, алгоритмическая сложность данного кода составляет O(nlogn).
Итоговая сложность получилась лучше, чем в заявлена в ТЗ, т.к. является более оптимизированной чем О(n*m)
 */
