package NeuralNetwork.LossFunction;

public interface LossFunction {
    public double calculate(int goodAnswer, double predicted);
    public double derivative(int goodAnswer, double predicted);
}
