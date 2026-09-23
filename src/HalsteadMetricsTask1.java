import java.util.Scanner;
import java.util.Locale;

public class HalsteadMetricsTask1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // чтобы вводить 1.6 с точкой

        System.out.println("Введите параметры для расчёта метрик Холстеда:");

        System.out.print("Количество отслеживаемых параметров: "); // 8
        int trackedParameters = scanner.nextInt();

        System.out.print("Количество рассчитываемых параметров: "); // 3
        int calculatedParameters = scanner.nextInt();

        System.out.print("Число одновременно сопровождаемых целей: "); // 25
        int a = scanner.nextInt();

        System.out.print("Количество измерений каждого отслеживаемого параметра: "); // 28
        int b = scanner.nextInt();

        System.out.print("Уровень языка программирования: "); // 1.6
        double lambda = scanner.nextDouble();

        scanner.close();

        // Минимальное число операндов (n2*)
        int n2Star = trackedParameters * a * b + calculatedParameters * a;

        // Потенциальный объём программы (V*)
        double vStar = (n2Star + 2) * (Math.log(n2Star + 2) / Math.log(2));

        // Потенциальное число ошибок (B)
        double b1 = Math.pow(vStar, 2) / (3000 * lambda);

        System.out.println("Результаты расчёта для задания 1 (вариант 2):");
        System.out.printf("Минимальное число операндов (n2*): %d%n", n2Star);
        System.out.printf("Потенциальный объём программы (V*): %.2f%n", vStar);
        System.out.printf("Потенциальное число ошибок (B): %.2f%n", b1);
    }
}