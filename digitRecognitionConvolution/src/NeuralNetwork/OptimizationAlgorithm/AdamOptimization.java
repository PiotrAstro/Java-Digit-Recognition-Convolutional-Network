package NeuralNetwork.OptimizationAlgorithm;

import GeneralMath.Matrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdamOptimization implements OptimizationAlgorithm, Serializable {
    private List<double[][]> layers; // [0 -> M, 1 -> V]
    private final double smallNumber = Math.pow(10, -15);
    private double B1, B2;
    private double currentB1, currentB2;

    public AdamOptimization(double b1, double b2) {
        B1 = b1;
        B2 = b2;
        currentB1 = B1;
        currentB2 = B2;
        layers = new ArrayList<double[][]>();
    }
    public AdamOptimization() {
        B1 = 0.8;
        B2 = 0.999;
        currentB1 = B1;
        currentB2 = B2;
        layers = new ArrayList<double[][]>();
    }

    @Override
    public double calculate(double derivative, int parameterNumber, int layerNumber) {
        layers.get(layerNumber)[0][parameterNumber] = B1 * layers.get(layerNumber)[0][parameterNumber] + (1 - B1) * derivative;
        layers.get(layerNumber)[1][parameterNumber] = B2 * layers.get(layerNumber)[1][parameterNumber] + (1 - B2) * derivative * derivative;
        double MHat = layers.get(layerNumber)[0][parameterNumber] / (1 - currentB1);
        double VHat = layers.get(layerNumber)[1][parameterNumber] / (1 - currentB2);



        return MHat / (Math.sqrt(VHat) + smallNumber);
    }

    @Override
    public void commitNextRound() {
        currentB1 *= B1;
        currentB2 *= B2;
    }

    @Override
    public int addLayer(int parametresNumber) {
        layers.add(new double[2][parametresNumber]);
        Matrix.Double_Set(layers.get(layers.size() - 1), 0);
        return layers.size() - 1;
    }
}