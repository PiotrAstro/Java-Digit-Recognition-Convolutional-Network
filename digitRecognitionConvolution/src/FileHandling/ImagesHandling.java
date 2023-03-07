package FileHandling;

public class ImagesHandling {
    public static double[][][] getLastImages(double[][][] trainImages, int number) {
        double[][][] result = new double[number][trainImages[0].length][trainImages[0][0].length];

        for(int i = 0; i < number; i++) {
            result[i] = trainImages[trainImages.length - number + i];
        }

        return result;
    }

    public static int[] getLastLabels(int[] trainLabels, int number) {
        int[] result = new int[number];

        for(int i = 0; i < number; i++) {
            result[i] = trainLabels[trainLabels.length - number + i];
        }

        return result;
    }

    public static double[][][] removeLastImages(double[][][] trainImages, int number) {
        double[][][] result = new double[trainImages.length - number][trainImages[0].length][trainImages[0][0].length];

        for(int i = 0; i < trainImages.length - number; i++) {
            result[i] = trainImages[i];
        }

        return result;
    }

    public static int[] removeLastLabels(int[] trainLabels, int number) {
        int[] result = new int[trainLabels.length - number];

        for(int i = 0; i < trainLabels.length - number; i++) {
            result[i] = trainLabels[i];
        }

        return result;
    }
}
