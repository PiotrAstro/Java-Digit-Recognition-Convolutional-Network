package NeuralNetwork;

import NeuralNetwork.ActivationFunction.*;
import FileHandling.FileHandling;
import NeuralNetwork.Layers.*;
import NeuralNetwork.LossFunction.CrossEntropy;
import NeuralNetwork.LossFunction.LossFunction;
import NeuralNetwork.LossFunction.SquaredError;
import NeuralNetwork.OptimizationAlgorithm.AdamOptimization;
import NeuralNetwork.OptimizationAlgorithm.GradientOptimization;
import NeuralNetwork.OptimizationAlgorithm.OptimizationAlgorithm;
import PrintingAndDrawing.Drawing;
import PrintingAndDrawing.PrintingNormalText;
import PrintingAndDrawing.PrintingWorkingMessages;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeuralNetwork implements Serializable {
    private static final long serialVersionUID = 1;

    // static methods

    public static NeuralNetwork NeuralNetworkCreatorConvolutional_v01() {
        double initialWeightsStart = -0.2; // bottom value, then range is added
        double initialWeightsRange = 0.4;
        double initialBiasStart = 0;
        double initialBiasRange = 0;
        double learningRate = 0.001;
        int batchSize = 16;
        int[] inputSize = new int[] {1, 28, 28};
        LossFunction lossFunction = new CrossEntropy();
        OptimizationAlgorithm optimizationAlgorithm = new GradientOptimization();

        NeuralNetwork network = new NeuralNetwork(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, learningRate, batchSize, inputSize, optimizationAlgorithm, lossFunction);

//        network.addMaxPoolingLayer(2, 2);
        network.addConvolutionalLayer(10, 3, new ReLuActivation());
        network.addMaxPoolingLayer(2, 2);
        network.addConvolutionalLayer(20, 3, new ReLuActivation());
        network.addMaxPoolingLayer(2, 2);
        network.addFlatteningLayer();
        network.addDenseLayer(16, new ReLuActivation());
        network.addDenseLayer(10, new SoftMaxActivation());

        return network;
    }

    public static NeuralNetwork NeuralNetworkCreatorPerceptron_v01() {
        double initialWeightsStart = -1; // bottom value, then range is added
        double initialWeightsRange = 2;
        double initialBiasStart = 0;
        double initialBiasRange = 0;
        double learningRate = 0.001;
        int batchSize = 10;
        int[] inputSize = new int[] {1, 28, 28};
        LossFunction lossFunction = new CrossEntropy();
        OptimizationAlgorithm optimizationAlgorithm = new AdamOptimization();

        NeuralNetwork network = new NeuralNetwork(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, learningRate, batchSize, inputSize, optimizationAlgorithm, lossFunction);

        network.addFlatteningLayer();
        network.addDenseLayer(32, new ReLuActivation());
        network.addDenseLayer(16, new ReLuActivation());
        network.addDenseLayer(10, new SoftMaxActivation());

        return network;
    }

    public static NeuralNetwork loadNeuralNetworkFromFile(String FileName) {
        return (NeuralNetwork) FileHandling.openObjectFromFile(FileName);
    }

    // normal things


    private List<Layer> layers;
    private LossFunction lossFunction;
    private OptimizationAlgorithm optimizationAlgorithm;
    private double initialWeightsStart;
    private double initialWeightsRange;
    private double initialBiasStart;
    private double initialBiasRange;
    private double learningRate;
    private int batchSize;
    private int[] inputSize;

    public NeuralNetwork(double initialWeightsStart, double initialWeightsRange, double initialBiasStart, double initialBiasRange, double learningRate, int batchSize, int[] inputSize, OptimizationAlgorithm optimizationAlgorithm, LossFunction lossFunction) {
        this.initialWeightsStart = initialWeightsStart;
        this.initialWeightsRange = initialWeightsRange;
        this.initialBiasStart = initialBiasStart;
        this.initialBiasRange = initialBiasRange;
        this.learningRate = learningRate;
        this.batchSize = batchSize;
        this.inputSize = inputSize;
        this.lossFunction = lossFunction;
        this.optimizationAlgorithm = optimizationAlgorithm;

        layers = new ArrayList<Layer>();
    }

    // safe to file
    public void saveNeuralNetworktoFile(String fileName) {
        FileHandling.saveObjectToFile(this, fileName);
    }

    // show and guess
    public void showImagesAndProcess(double[][][] images, int startFrom) {
        Scanner scan = new Scanner(System.in);
        int guessedNumber;

        for(int i = startFrom; i < images.length; i++) {
            guessedNumber = processImageGetOneNumber(images[i]);

            PrintingNormalText.printTextNL("\nImage number " + i);
            Drawing.drawImage(images[i]);
            PrintingNormalText.printTextNL("Neural Network guess:\n" + guessedNumber);

            scan.nextLine();
        }
    }

    // calculating images
    public int processImageGetOneNumber(double[][] image) {
        double[][][] passingData = processImageGetOutputs(image);

        int number = 0;
        for(int i = 0; i < passingData[0][0].length; i++) {
            if(passingData[0][0][i] > passingData[0][0][number]) {
                number = i;
            }
        }

        return number;
    }

    public double[][][] processImageGetOutputs(double[][] image) {
        double[][][] passingData = new double[][][] {image};

        for (Layer layer : layers) {
            layer.calculate(passingData);
            passingData = layer.getOutput();
        }

        return passingData;
    }

    public double processImagesGetHitRatio(double[][][] images, int[] labels) {
        int sum = 0;

        for(int i = 0; i < labels.length; i++) {
            if(processImageGetOneNumber(images[i]) == labels[i]) {
                sum ++;
            }
        }

        return (double) sum / (double) labels.length;
    }



    // calculating derivatives
    public void CalculateDerivativesWithValidationEveryStep(double[][][] images, int[] labels, double[][][] imagesValidation, int[] labelsValidation, int imagesBetweenValidation, int numberOfEpochs) {
        int imagesDoneInEpoch, lastImage;
        double hitRatio;

        for(int epoch = 0; epoch < numberOfEpochs; epoch ++) {
            imagesDoneInEpoch = 0;

            while(imagesDoneInEpoch <= labels.length) {
                lastImage = imagesDoneInEpoch + imagesBetweenValidation - 1;

                if(lastImage >= labels.length) {
                    lastImage = labels.length - 1;
                }

                CalculateDerivativesManyImages(images, labels, imagesDoneInEpoch, lastImage);
                hitRatio = processImagesGetHitRatio(imagesValidation, labelsValidation);

                PrintingWorkingMessages.showNLMessage("Epoch " + epoch + "\nLearned images from " + imagesDoneInEpoch + " to " + lastImage + "\nHit ratio (from " + labelsValidation.length + "): " + hitRatio + "\n");

                imagesDoneInEpoch += imagesBetweenValidation;
            }
        }
    }

    public void CalculateDerivativesManyImages(double[][][] images, int[] labels, int firstImage, int lastImage) {
        int batchSizeHere = 0;

        for(int i = firstImage; i <= lastImage; i++) {
            CalculateSingleDerivatives(images[i], labels[i]);
            batchSizeHere ++;

            if(batchSizeHere == batchSize) {
                batchSizeHere = 0;
                CommitAllDerivatives(learningRate);
            }
        }

        CommitAllDerivatives( 0);
    }

    public void CalculateDerivativesManyImages(double[][][] images, int[] labels) {
        CalculateDerivativesManyImages(images, labels, 0, labels.length - 1);
    }

    public void CalculateSingleDerivatives(double[][] image, int label) {
        double[][][] passingData = processImageGetOutputs(image);
        double[][][] passingDerivatives = new double[1][1][passingData[0][0].length];
        int expectedOutput;

        for(int i = 0; i < passingData[0][0].length; i++) {
            passingDerivatives[0][0][i] = lossFunction.derivative((i == label ? 1 : 0), passingData[0][0][i]);
        }

        for(int i = layers.size() - 1; i >= 0; i--) {
            layers.get(i).calculateDerivatives(passingDerivatives);
            passingDerivatives = layers.get(i).getInputDerivatives();
        }
    }

    public void CommitAllDerivatives(double learningRateHere) {
        for (Layer layer : layers) {
            layer.commitDerivatives(learningRateHere);
        }

        optimizationAlgorithm.commitNextRound();
    }


    // adding layers
    public void addConvolutionalLayer(int filtersNumber, int filterSize, ActivationFunction activationFunction) {
        layers.add(new ConvolutionalLayer(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, activationFunction, inputSize, filtersNumber, filterSize, optimizationAlgorithm));
        ActualiseInputSize();
    }

    public void addDenseLayer(int neuronsNumber, ActivationFunction activationFunction) {
        layers.add(new DenseLayer(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, activationFunction, inputSize, neuronsNumber, optimizationAlgorithm));
        ActualiseInputSize();
    }

    public void addFlatteningLayer() {
        layers.add(new FlatteningLayer(inputSize));
        ActualiseInputSize();
    }

    public void addMaxPoolingLayer(int window, int stride) {
        layers.add(new MaxPoolingLayer(inputSize, window, stride));
        ActualiseInputSize();
    }

    public void addMeanPoolingLayer(int window, int stride) {
        layers.add(new MeanPoolingLayer(inputSize, window, stride));
        ActualiseInputSize();
    }
    public void ActualiseInputSize() {
        inputSize = layers.get(layers.size() - 1).getOutputSize();
    }
}
