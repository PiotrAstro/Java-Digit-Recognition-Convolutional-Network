package NeuralNetwork.ActivationFunction;

import java.io.Serializable;

public class ReLuActivation implements ActivationFunction, Serializable {
    @Override
    public double calculate(double x) {
        return Math.max(0, x);
    }

    @Override
    public double derivative(double x) {
        if(x > 0)
            return 1;
        else
            return 0;
    }
}
