package NeuralNetwork.Layers;

import NeuralNetwork.ActivationFunction.ActivationFunction;
import GeneralMath.Matrix;
import NeuralNetwork.OptimizationAlgorithm.OptimizationAlgorithm;

import java.util.Random;

public class DenseLayer extends Layer{

    private double weights [][]; // [neurons] [inputs - activation]
    private double weightsSummedDerivatives [][];
    private double biases []; // [neurons]
    private double biasesSummedDerivatives [];
    private int neuronsNumber;
    private int optimizerLayerId;
    private double[][][] outputNoActivationFunction;

    public DenseLayer(double initialWeightsStart, double initialWeightsRange, double initialBiasStart, double initialBiasRange, ActivationFunction activationFunction, int[] inputSize, int neuronsNumber, OptimizationAlgorithm optimizationAlgorithm) {
        super(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, activationFunction, inputSize, optimizationAlgorithm);

        this.neuronsNumber = neuronsNumber;
        outputSize = new int[] {1, 1, neuronsNumber};
        output = new double[outputSize[0]][outputSize[1]][outputSize[2]];
        outputNoActivationFunction = new double[outputSize[0]][outputSize[1]][outputSize[2]];

        generateWeights();
        generateBiases();
        cleanSummedDerivatives();

        optimizerLayerId = optimizationAlgorithm.addLayer(parametresNumber());
    }

    public void generateWeights() {
        Random random = new Random();

        weights = new double[neuronsNumber][inputSize[2]];
        weightsSummedDerivatives = new double[neuronsNumber][inputSize[2]];

        for(int index_0 = 0; index_0 < weights.length; index_0 ++) {
            for(int index_1 = 0; index_1 < weights[index_0].length; index_1 ++) {
                weights[index_0][index_1] = initialWeightsStart + random.nextDouble() * initialWeightsRange;
            }
        }
    }
    public void generateBiases() {
        Random random = new Random();

        biases = new double[neuronsNumber];
        biasesSummedDerivatives = new double[neuronsNumber];

        for(int index_0 = 0; index_0 < biases.length; index_0 ++) {
            biases[index_0] = initialBiasStart + random.nextDouble() * initialBiasRange;
        }
    }
    public void cleanSummedDerivatives() {
        Matrix.Double_Set(weightsSummedDerivatives, 0);
        Matrix.Double_Set(biasesSummedDerivatives, 0);
    }
    public int weightsNumber() {
        return weights.length * weights[0].length;
    }
    public int parametresNumber() {
        return weightsNumber() + biases.length;
    }
    public int getWeightParameterNumber(int neurons, int input) { // [filters] [channels] [rows] [columns]
        return weights[0].length * neurons + input;
    }
    public int getBiasParameterNumber(int neuron) { // [neuron]
        return weightsNumber() + neuron;
    }





    // from abstract class

    @Override
    public void calculate(double[][][] input) {
        this.input = input;

        for(int neurons = 0; neurons < neuronsNumber; neurons ++) { // output neurons
            outputNoActivationFunction[0][0][neurons] = biases[neurons];

            for(int activation = 0; activation < inputSize[2]; activation ++) { // y in output
                outputNoActivationFunction[0][0][neurons] += input[0][0][activation] * weights[neurons][activation];
            }

            output[0][0][neurons] = activationFunction.calculate(outputNoActivationFunction[0][0][neurons]);
        }
    }

    @Override
    public void calculateDerivatives(double[][][] outputDerivatives) {
        Matrix.Double_ApplyDerivative(outputDerivatives, outputNoActivationFunction, activationFunction);
        Matrix.Double_Set(inputDerivatives, 0);

        for(int neurons = 0; neurons < neuronsNumber; neurons ++) { // output neurons
            biasesSummedDerivatives[neurons] = outputDerivatives[0][0][neurons];

            for(int activation = 0; activation < inputSize[2]; activation ++) { // y in output
                weightsSummedDerivatives[neurons][activation] += outputDerivatives[0][0][neurons] * input[0][0][activation];

                inputDerivatives[0][0][activation] += weights[neurons][activation] * outputDerivatives[0][0][neurons];
            }
        }
    }

    @Override
    public void commitDerivatives(double learningRate) {
        for (int neurons = 0; neurons < weights.length; neurons++) { // output filters
            for (int inputs = 0; inputs < inputSize[2]; inputs++) { // input channels
                weights[neurons][inputs] -=
                        learningRate * optimizationAlgorithm.calculate(
                                weightsSummedDerivatives[neurons][inputs],
                                getWeightParameterNumber(neurons, inputs),
                                optimizerLayerId);
                weightsSummedDerivatives[neurons][inputs] = 0;
            }

            biases[neurons] -= learningRate * optimizationAlgorithm.calculate(
                    biasesSummedDerivatives[neurons],
                    getBiasParameterNumber(neurons),
                    optimizerLayerId);
            biasesSummedDerivatives[neurons] = 0;
        }
    }
}

