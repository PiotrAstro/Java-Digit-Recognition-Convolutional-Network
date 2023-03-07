package NeuralNetwork.LossFunction;

import java.io.Serializable;

public class SquaredError implements LossFunction, Serializable {
    @Override
    public double calculate(int goodAnswer, double predicted) {
        return Math.pow(goodAnswer - predicted, 2);
    }

    @Override
    public double derivative(int goodAnswer, double predicted) {
        return 2 * (goodAnswer - predicted) * (-1);
    }
}
