package NeuralNetwork.LossFunction;

import java.io.Serializable;

public class CrossEntropy implements LossFunction, Serializable {
    private final double smallNumber = Math.pow(10, -15);

//    @Override
//    public double calculate(int goodAnswer, double predicted) {
//        return -goodAnswer * Math.log(smallNumber + predicted);
//    }
//
//    @Override
//    public double derivative(int goodAnswer, double predicted) {
//        return -goodAnswer / (smallNumber + predicted);
//    }

    @Override
    public double calculate(int goodAnswer, double predicted) {
        return -Math.log(smallNumber + (goodAnswer == 1 ? predicted : (1 - predicted)));
    }

    @Override
    public double derivative(int goodAnswer, double predicted) {
        return -1 / (smallNumber + (goodAnswer == 1 ? predicted : (1 - predicted))) * (goodAnswer == 1 ? 1 : -1);
    }
}
