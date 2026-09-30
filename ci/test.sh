#!/usr/bin/env bash
# Автотесты ЛР3 «Метрики Холстеда», вариант 2.
# Прогоняет три программы на данных варианта и сверяет результат с ручным расчётом.
# Запуск: bash ci/test.sh <папка со скомпилированными классами>   (по умолчанию out)
set -uo pipefail

CP="${1:-out}"
FAILED=0

check() {  # check "<вывод программы>" "<ожидаемая строка>"
  if grep -qF -- "$2" <<< "$1"; then
    echo "  OK    $2"
  else
    echo "  FAIL  ожидалось: $2"
    FAILED=1
  fi
}

run() {  # run <класс> <ввод> — запускает программу, десятичную запятую меняет на точку
  printf "%b" "$2" | java -cp "$CP" "$1" | tr ',' '.'
}

echo "Задание 1"
OUT=$(run HalsteadMetricsTask1 '8\n3\n25\n28\n1.6\n')
check "$OUT" "(n2*): 5675"
check "$OUT" "(V*): 70797.37"
check "$OUT" "(B): 1044222.51"

echo "Задание 2 (m = 5, v = 20, рабочий день 8 ч)"
OUT=$(run HalsteadMetricsTask2 '8\n3\n25\n28\n5\n20\n8\n')
check "$OUT" "(K): 798.05"
check "$OUT" "(N): 183263.75"
check "$OUT" "(V): 980553.61"
check "$OUT" "(P): 68723.91"
check "$OUT" "(Tk): 687.24 дней или 5497.91 часов"
check "$OUT" "(B): 326.85"
check "$OUT" "(tn): 474.82"

echo "Задание 3"
OUT=$(run HalsteadMetricsTask3 '4 8 10\n1 2 4\n2000\n1.6\n14\n')
check "$OUT" "Финальный рейтинг: -407320.66"
check "$OUT" "Финальный рейтинг: -28075518.07"
check "$OUT" "Финальный рейтинг: 2021.69"
check "$OUT" "в новой программе: 8.76"

if [ "$FAILED" -ne 0 ]; then
  echo "Есть расхождения с ожидаемыми значениями"
  exit 1
fi
echo "Все проверки пройдены"
