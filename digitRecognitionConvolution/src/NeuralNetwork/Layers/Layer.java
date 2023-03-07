package NeuralNetwork.Layers;

import NeuralNetwork.ActivationFunction.ActivationFunction;
import GeneralMath.Matrix;
import NeuralNetwork.OptimizationAlgorithm.OptimizationAlgorithm;

import java.io.Serializable;

public abstract class Layer implements Serializable {
    protected double initialWeightsStart;
    protected double initialWeightsRange;
    protected double initialBiasStart;
    protected double initialBiasRange;

    protected ActivationFunction activationFunction;
    protected OptimizationAlgorithm optimizationAlgorithm;

    protected double[][][] input; // [channels] [rows] [columns]

    protected double[][][] inputDerivatives; // [channels] [rows] [columns]

    protected double[][][] output;

    protected int[] inputSize;
    protected int[] outputSize;

    //abstract methods

    public abstract void calculate(double[][][] input);

    public abstract void calculateDerivatives(double[][][] outputDerivatives);

    public abstract void commitDerivatives(double learningRate);

    //methods


    public Layer(double initialWeightsStart, double initialWeightsRange, double initialBiasStart, double initialBiasRange, ActivationFunction activationFunction, int[] inputSize, OptimizationAlgorithm optimizationAlgorithm) {
        this.initialWeightsStart = initialWeightsStart;
        this.initialWeightsRange = initialWeightsRange;
        this.initialBiasStart = initialBiasStart;
        this.initialBiasRange = initialBiasRange;
        this.activationFunction = activationFunction;
        this.inputSize = inputSize;
        this.optimizationAlgorithm = optimizationAlgorithm;

        inputDerivatives = new double[inputSize[0]][inputSize[1]][inputSize[2]];
        Matrix.Double_Set(inputDerivatives, 0);
    }

    public Layer(int[] inputSize) {
        this.inputSize = inputSize;
        inputDerivatives = new double[inputSize[0]][inputSize[1]][inputSize[2]];
        Matrix.Double_Set(inputDerivatives, 0);
    }

    public int[] getOutputSize() {
        return outputSize;
    }

    public double[][][] getInputDerivatives() {
        return inputDerivatives;
    }

    public double[][][] getOutput() {
        return output;
    }

}
