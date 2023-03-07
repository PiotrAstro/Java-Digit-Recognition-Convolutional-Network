import FileHandling.*;
import NeuralNetwork.LossFunction.CrossEntropy;
import NeuralNetwork.LossFunction.LossFunction;
import NeuralNetwork.LossFunction.SquaredError;
import NeuralNetwork.NeuralNetwork;

public class Main {
    public static void main(String[] args) {

        //---------------------------------------------------------------------------------------------------------------------
        // guessing data

        double[][][] guessImages;

        int numberOfImagesGuessing = 1000;
        guessImages = FileHandling.loadChunkOfImages(numberOfImagesGuessing,28,28,255,"test.csv");




        //--------------------------------------------------------------------------------------------------------------------
        // train data

//        double[][][] trainImages, validationImages;
//        int[] trainLabels, validationLabels;
//
//        int numberOfImages = 40000;
//        trainImages = FileHandling.loadChunkOfImages(numberOfImages,28,28,255,"train_correct.txt");
//        trainLabels = FileHandling.loadChunkOfImageLabels(numberOfImages, "train_labels.txt");
//
//        int howMuchImagesForValidation = 1000;
//        validationImages = ImagesHandling.getLastImages(trainImages, howMuchImagesForValidation);
//        validationLabels = ImagesHandling.getLastLabels(trainLabels, howMuchImagesForValidation);
//        trainImages = ImagesHandling.removeLastImages(trainImages, howMuchImagesForValidation);
//        trainLabels = ImagesHandling.removeLastLabels(trainLabels, howMuchImagesForValidation);




        //---------------------------------------------------------------------------------------------------------------------
        // learning

        //NeuralNetwork network = NeuralNetwork.NeuralNetworkCreatorPerceptron_v01();
        //NeuralNetwork network = NeuralNetwork.NeuralNetworkCreatorConvolutional_v01();
        //network.processImageGetOneNumber(trainImages[0]);
        //network.CalculateDerivativesWithValidationEveryStep(trainImages, trainLabels, validationImages, validationLabels, 5000, 13);
        //network.saveNeuralNetworktoFile("convolutionalSave_v01.1.dat");




        //---------------------------------------------------------------------------------------------------------------------
        // looking how it work

        NeuralNetwork network = NeuralNetwork.loadNeuralNetworkFromFile("convolutionalSave_v01.1.dat");
        network.showImagesAndProcess(guessImages, 0);
    }
}