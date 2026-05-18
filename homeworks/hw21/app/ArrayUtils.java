package hw21.app;

public class ArrayUtils {


    @Author(firstName = "Yulian", lastName = "Hrytsevych", date = "15.05.2026")
    @MethodInfo(name = "Merge Sort", returnType = "void", description = "Сортує масив алгоритмом злиття")
    public static void mergeSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (left < right) {
            int mid = (left + right) / 2;

            // Рекурсивно сортуємо ліву і праву половини
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Зливаємо відсортовані половини
            merge(arr, left, mid, right);
        }
    }

    @Author(firstName = "ItHillel", lastName = "School", date = "01.01.2026")
    @MethodInfo(name = "Merge", returnType = "void", description = "Додатковий приватний метод для здійснення злиття")
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Створюємо тимчасові підмасиви
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Копіюємо дані в тимчасові підмасиви
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        // Зливаємо тимчасові підмасиви
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Копіюємо залишки L[], якщо такі є
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Копіюємо залишки R[], якщо такі є
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    @Author(firstName = "Alex", lastName = "Lion", date = "2025")
    @MethodInfo(name = "Binary Search", returnType = "int", description = "Шукає індекс елемента в посортованому масиві")
    public static int binarySearch(int[] array, int target) {
            int left = 0;
            int right = array.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                // Перевіряємо, чи знайшли шукане значення
                if (array[mid] == target) {
                    return mid;
                }

                // Якщо шукане значення менше середнього, змінюємо праву границю
                if (array[mid] < target) {
                    left = mid + 1;
                }
                // Інакше змінюємо ліву границю
                else {
                    right = mid - 1;
                }
            }

            // Якщо шукане значення не знайдено, повертаємо -1
            return -1;
    }
}
