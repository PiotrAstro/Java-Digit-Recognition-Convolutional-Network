package NeuralNetwork.OptimizationAlgorithm;

public interface OptimizationAlgorithm {
    public double calculate(double derivative, int parameterNumber, int layerNumber);
    public void commitNextRound();

    public int addLayer(int parametresNumber);
}
