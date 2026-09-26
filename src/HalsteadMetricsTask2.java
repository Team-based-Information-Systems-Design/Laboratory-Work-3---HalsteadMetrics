import java.util.Scanner;
import java.util.Locale;

public class HalsteadMetricsTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Введите параметры для расчёта метрик Холстеда:"); // Ввод занчений из расчетной таблицы по вариантам

        System.out.print("Количество отслеживаемых параметров: "); // 8
        int trackedParameters = scanner.nextInt();

        System.out.print("Количество рассчитываемых параметров: "); // 3
        int calculatedParameters = scanner.nextInt();

        System.out.print("Число одновременно сопровождаемых целей: "); // 25
        int a = scanner.nextInt();

        System.out.print("Количество измерений каждого отслеживаемого параметра: "); // 28
        int b = scanner.nextInt();

        System.out.print("Количество программистов: "); // 5
        int m = scanner.nextInt();

        System.out.print("Производительность (команд/день): "); // 20
        int v = scanner.nextInt();

        System.out.print("Продолжительность рабочего дня в часах: "); // 8
        int hoursPerDay = scanner.nextInt();

        scanner.close();

        // Минимальное число операндов (n2*)
        int n2Star = trackedParameters * a * b + calculatedParameters * a;

        // а) Расчёт структурных параметров
        double k = n2Star / 8.0;
        double K;
        if (k > 8) {
            // многоуровневая (иерархическая) структура
            K = n2Star / 8.0 + n2Star / Math.pow(8, 2);
        } else {
            K = Math.ceil(k);
        }

        // б) Длина программы
        double N = 220 * K + K * (Math.log(K) / Math.log(2));

        // в) Объём программного обеспечения
        double V = K * 220 * (Math.log(48) / Math.log(2));

        // г) Количество команд ассемблера
        double P = 3 * N / 8;

        // д) Календарное время программирования
        double TkDays = 3 * N / (8 * m * v);
        double TkHours = TkDays * hoursPerDay;

        // е) Потенциальное количество ошибок
        double B = V / 3000;

        // ж) Начальная надёжность ПО
        double tn = TkHours / (2 * Math.abs(Math.log(B)));

        System.out.println("Результаты расчёта для задания №2 (вариант 2):");
        System.out.printf("Число модулей (K): %.2f%n", K);
        System.out.printf("Длина программы (N): %.2f%n", N);
        System.out.printf("Объем программного обеспечения (V): %.2f%n", V);
        System.out.printf("Количество команд ассемблера (P): %.2f%n", P);
        System.out.printf("Календарное время программирования (Tk): %.2f дней или %.2f часов%n",
                TkDays, TkHours);
        System.out.printf("Потенциальное количество ошибок (B): %.2f%n", B);
        System.out.printf("Начальная надежность ПО (tn): %.2f часов%n", tn);
    }
}
