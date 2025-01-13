package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.ArrayList;
import java.util.List;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        // Ваше решение
        List<Unit> suitableUnits = new ArrayList<>();
        for (List<Unit> su: unitsByRow){
            for (int i = 0; i < su.size(); i++) {
                Unit suUnit=su.get(i);
                if(isLeftArmyTarget){
                    if(i==0 || su.get(i-1).isAlive()==false){
                        suitableUnits.add(suUnit);
                    }
                }else {
                    if(i==su.size()-1|| su.get(i+1).isAlive()==false){
                        suitableUnits.add(suUnit);
                    }
                }

            }
        }
        return suitableUnits;
    }
}
/*
Итоговая сложность
Внешний цикл: Проходит по m рядам.
Внутренний цикл: Проходит по n юнитам в каждом ряду.
Операции во внутреннем цикле: Константное время O(1) за итерацию.
Таким образом, общая сложность алгоритма будет:

Сложность внешнего цикла: O(m).
Сложность внутреннего цикла: O(n).
Итоговая сложность: O(m * n).
Сложность алгоритма получилась такая же как в ТЗ
 */