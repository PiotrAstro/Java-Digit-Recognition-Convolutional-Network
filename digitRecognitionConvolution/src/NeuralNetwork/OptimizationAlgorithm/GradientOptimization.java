package NeuralNetwork.OptimizationAlgorithm;

import java.io.Serializable;

public class GradientOptimization implements OptimizationAlgorithm, Serializable {

    @Override
    public double calculate(double derivative, int parameterNumber, int layerNumber) {
        return derivative;
    }

    @Override
    public void commitNextRound() {

    }

    @Override
    public int addLayer(int parametresNumber) {
        return 0;
    }
}
