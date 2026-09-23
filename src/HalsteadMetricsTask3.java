import java.util.Scanner;
import java.util.Locale;

public class HalsteadMetricsTask3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Введите параметры для оценки программиста, используя метрики Холстеда:");

        System.out.print("Введите объемы программ (через пробел): "); // 4 8 10
        String[] volumesInput = scanner.nextLine().split("\\s+");
        int[] programVolumes = new int[volumesInput.length];
        for (int i = 0; i < volumesInput.length; i++) {
            programVolumes[i] = Integer.parseInt(volumesInput[i]);
        }

        System.out.print("Введите ошибки программ (через пробел): "); // 1 2 4
        String[] errorsInput = scanner.nextLine().split("\\s+");
        int[] programErrors = new int[errorsInput.length];
        for (int i = 0; i < errorsInput.length; i++) {
            programErrors[i] = Integer.parseInt(errorsInput[i]);
        }

        System.out.print("Начальный рейтинг: "); // 2000
        double initialRating = scanner.nextDouble();

        System.out.print("Уровень языка программирования: "); // 1.6
        double lambda = scanner.nextDouble();

        System.out.print("Предполагается написать программу объемом в Кбайт: "); // 14
        int newProgramVolume = scanner.nextInt();

        scanner.close();

        if (programVolumes.length != programErrors.length) {
            System.out.println("Ошибка: массивы разной длины!");
            return;
        }

        for (int coeType = 1; coeType <= 3; coeType++) {
            calculateForCoefficientType(
                    initialRating, lambda,
                    programVolumes, programErrors,
                    newProgramVolume, coeType
            );
        }
    }

    private static void calculateForCoefficientType(
            double initialRating, double lambda,
            int[] volumes, int[] errors,
            int newVolume, int coeType) {

        double rating = initialRating;

        for (int i = 0; i < volumes.length; i++) {
            double c = calculateC(lambda, rating, coeType);
            double term = (errors[i] > 0) ? errors[i] / c : 0;
            rating = rating * (1 + 0.001 * (volumes[i] - term));
        }

        double cNew = calculateC(lambda, rating, coeType);
        double expectedErrors = cNew * newVolume;

        System.out.printf("Результаты для варианта коэффициента %d:%n", coeType);
        System.out.printf("Финальный рейтинг: %.2f%n", rating);
        System.out.printf("Ожидаемое количество ошибок в новой программе: %.2f%n%n", expectedErrors);
    }

    private static double calculateC(double lambda, double rating, int coeType) {
        switch (coeType) {
            case 1:
                return 1.0 / (lambda + rating);
            case 2:
                return 1.0 / (lambda * rating);
            case 3:
                return 1.0 / lambda + 1.0 / rating;
            default:
                return 0.0;
        }
    }
}