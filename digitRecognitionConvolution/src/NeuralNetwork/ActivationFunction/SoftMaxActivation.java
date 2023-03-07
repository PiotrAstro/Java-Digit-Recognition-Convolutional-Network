package NeuralNetwork.ActivationFunction;

import java.io.Serializable;

public class SoftMaxActivation implements ActivationFunction, Serializable {
    @Override
    public double calculate(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double derivative(double x) {
        double sigmoid = calculate(x);
        return sigmoid * (1 - sigmoid);
    }
}
