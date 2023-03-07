package NeuralNetwork.Layers;

import NeuralNetwork.ActivationFunction.ActivationFunction;
import GeneralMath.Matrix;
import NeuralNetwork.OptimizationAlgorithm.OptimizationAlgorithm;

import java.util.Random;

public class ConvolutionalLayer extends Layer{

    private double weights [][][][]; // [filters] [channels] [rows] [columns]
    private double weightsSummedDerivatives [][][][];
    private double biases [][][]; // [filters] [output rows] [output columns] channels do not matter
    private double biasesSummedDerivatives [][][];
    private int filtersNumber;
    private int filterSize;

    private int margin;
    private int optimizerLayerId;
    private double[][][] outputNoActivationFunction;

    public ConvolutionalLayer(double initialWeightsStart, double initialWeightsRange, double initialBiasStart, double initialBiasRange, ActivationFunction activationFunction, int[] inputSize, int filtersNumber, int filterSize, OptimizationAlgorithm optimizationAlgorithm) {
        super(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, activationFunction, inputSize, optimizationAlgorithm);

        margin = (filterSize / 2);
        this.filterSize = filterSize;
        this.filtersNumber = filtersNumber;
        outputSize = new int[] {filtersNumber, inputSize[1] - margin * 2, inputSize[2] - margin * 2};
        output = new double[outputSize[0]][outputSize[1]][outputSize[2]];
        outputNoActivationFunction = new double[outputSize[0]][outputSize[1]][outputSize[2]];

        generateWeights();
        generateBiases();
        cleanSummedDerivatives();

        optimizerLayerId = optimizationAlgorithm.addLayer(parametresNumber());
    }

    public void generateWeights() {
        Random random = new Random();

        weights = new double[filtersNumber][inputSize[0]][filterSize][filterSize];
        weightsSummedDerivatives = new double[filtersNumber][inputSize[0]][filterSize][filterSize];

        for(int index_0 = 0; index_0 < weights.length; index_0 ++) {
            for(int index_1 = 0; index_1 < weights[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < weights[index_0][index_1].length; index_2 ++) {
                    for (int index_3 = 0; index_3 < weights[index_0][index_1][index_2].length; index_3++) {
                        weights[index_0][index_1][index_2][index_3] = initialWeightsStart + random.nextDouble() * initialWeightsRange;
                    }
                }
            }
        }
    }
    public void generateBiases() {
        Random random = new Random();

        biases = new double[filtersNumber][outputSize[1]][outputSize[2]];
        biasesSummedDerivatives = new double[filtersNumber][outputSize[1]][outputSize[2]];

        for(int index_0 = 0; index_0 < biases.length; index_0 ++) {
            for(int index_1 = 0; index_1 < biases[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < biases[index_0][index_1].length; index_2 ++) {
                    biases[index_0][index_1][index_2] = initialBiasStart + random.nextDouble() * initialBiasRange;
                }
            }
        }
    }
    public void cleanSummedDerivatives() {
        Matrix.Double_Set(weightsSummedDerivatives, 0);
        Matrix.Double_Set(biasesSummedDerivatives, 0);
    }
    public int weightsNumber() {
        return weights.length * weights[0].length * weights[0][0].length * weights[0][0][0].length;
    }
    public int biasesNumber() {
        return biases.length * biases[0].length * biases[0][0].length;
    }
    public int parametresNumber() {
        return weightsNumber() + biasesNumber();
    }
    public int getWeightParameterNumber(int filter, int channel, int row, int column) { // [filters] [channels] [rows] [columns]
        return weights[0][0][0].length * (weights[0][0].length * (filter * weights[0].length  + channel) + row) + column;
    }

    public int getBiasParameterNumber(int filter, int row, int column) {
        return weightsNumber() + biases[0][0].length * (biases[0].length * filter + row) + column;
    }






    // from abstract class

    @Override
    public void calculate(double[][][] input) {
        this.input = input;

        //Drawing.drawImage(input[0]);
        for(int filter = 0; filter < weights.length; filter ++) { // output filters
            for(int y = 0; y < outputSize[1]; y ++) { // y in output
                for(int x = 0; x < outputSize[2]; x ++) { // x in output
                    outputNoActivationFunction[filter][y][x] = biases[filter][y][x];

                    for(int channel = 0; channel < inputSize[0]; channel ++) { // input channels
                        for(int weightY = 0; weightY < weights[filter][channel].length; weightY ++) {
                            for(int weightX = 0; weightX < weights[filter][channel][weightY].length; weightX ++) {
                                outputNoActivationFunction[filter][y][x] += weights[filter][channel][weightY][weightX] * input[channel][y + weightY][x + weightX];
                            }
                        }
                    }

                    output[filter][y][x] = activationFunction.calculate(outputNoActivationFunction[filter][y][x]);
                }
            }
        }
        //Drawing.drawImage(output[0]);
    }

    @Override
    public void calculateDerivatives(double[][][] outputDerivatives) {
        int xShift, yShift;

        Matrix.Double_ApplyDerivative(outputDerivatives, outputNoActivationFunction, activationFunction);

        for(int filter = 0; filter < biases.length; filter ++) {
            for (int y = 0; y < outputSize[1]; y++) { // y in output
                for (int x = 0; x < outputSize[2]; x++) { // x in output
                    biasesSummedDerivatives[filter][y][x] += outputDerivatives[filter][y][x];
                }
            }
        }

        for(int channel = 0; channel < inputSize[0]; channel ++) { // input channels
            for (int y = 0; y < inputSize[1]; y++) { // y in input
                for (int x = 0; x < inputSize[2]; x++) { // x in input
                    inputDerivatives[channel][y][x] = 0;

                    for (int filter = 0; filter < weights.length; filter++) { // output filters
                        for (int weightY = 0; weightY < weights[filter][channel].length; weightY++) {
                            for (int weightX = 0; weightX < weights[filter][channel][weightY].length; weightX++) {
                                xShift = x - weightX;
                                yShift = y - weightY;

                                if(xShift >= 0 && yShift >= 0 && xShift < outputSize[2] && yShift < outputSize[1]) {
                                    inputDerivatives[channel][y][x] += weights[filter][channel][weightY][weightX] * outputDerivatives[filter][yShift][xShift];
                                    weightsSummedDerivatives[filter][channel][weightY][weightX] += outputDerivatives[filter][yShift][xShift] * input[channel][y][x];
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void commitDerivatives(double learningRate) {
        for (int filter = 0; filter < weights.length; filter++) { // output filters
            for (int channel = 0; channel < inputSize[0]; channel++) { // input channels
                for (int weightY = 0; weightY < weights[filter][channel].length; weightY++) {
                    for (int weightX = 0; weightX < weights[filter][channel][weightY].length; weightX++) {
                        weights[filter][channel][weightY][weightX] -=
                                learningRate * optimizationAlgorithm.calculate(
                                        weightsSummedDerivatives[filter][channel][weightY][weightX],
                                        getWeightParameterNumber(filter, channel, weightY, weightX),
                                        optimizerLayerId);
                        weightsSummedDerivatives[filter][channel][weightY][weightX] = 0;
                    }
                }
            }

            for (int y = 0; y < outputSize[1]; y++) { // y in output
                for (int x = 0; x < outputSize[2]; x++) { // x in output
                    biases[filter][y][x] -= learningRate * optimizationAlgorithm.calculate(
                            biasesSummedDerivatives[filter][y][x],
                            getBiasParameterNumber(filter, y, x),
                            optimizerLayerId);
                    biasesSummedDerivatives[filter][y][x] = 0;
                }
            }
        }
    }
}
