import java.io.*;
import java.util.*;

public class BubbleSort {
    public static int[] createRandomArray(int arrayLength) {
        int[] arr = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++){
            arr[i] = random.nextInt(101); //101-1 = 100 integers
        }
        return arr;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (int num : array) {
                writer.println(num);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0];
        }

        int[] result = new int[numbers.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option that is given below:");
        System.out.println("1. Generate random array and save to file");
        System.out.println("2. Read array from file, sort it, and save to another file");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter the length of the random array: ");
            int length = scanner.nextInt();
            int[] array = createRandomArray(length);

            System.out.print("Enter the filename to save the array: ");
            String filename = scanner.next();

            writeArrayToFile(array, filename);
            System.out.println("Array saved to " + filename);
        } else if (choice == 2) {
            System.out.print("Enter the filename to read the array from: ");
            String inputFilename = scanner.next();

            int[] array = readFileToArray(inputFilename);

            System.out.println("Array read from file: " + Arrays.toString(array));

            bubbleSort(array);

            System.out.println("Sorted array: " + Arrays.toString(array));

            System.out.print("Enter the filename to save the sorted array: ");
            String outputFilename = scanner.next();

            writeArrayToFile(array, outputFilename);
            System.out.println("Sorted array saved to " + outputFilename);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}