package NeuralNetwork.Layers;

import GeneralMath.Matrix;

public class MeanPoolingLayer extends Layer{


    private int window;
    private int stride;
    private int numberOfCells;

    public MeanPoolingLayer(int[] inputSize, int window, int stride) {
        super(inputSize);

        this.window = window;
        this.stride = stride;
        numberOfCells = window * window;

        outputSize = new int[] {inputSize[0], inputSize[1] / stride, inputSize[2] / stride};
        output = new double[outputSize[0]][outputSize[1]][outputSize[2]];
    }





    // from abstract class

    @Override
    public void calculate(double[][][] input) {
        int xShift, yShift;
        this.input = input;

        for(int channel = 0; channel < inputSize[0]; channel ++) { // output channels
            for(int y = 0; y < outputSize[1]; y ++) { // y in output
                for(int x = 0; x < outputSize[2]; x ++) { // x in output
                    output[channel][y][x] = 0;

                    for(int windowY = 0; windowY < window; windowY ++) {
                        for(int windowX = 0; windowX < window; windowX ++) {
                            xShift = x * stride + windowX;
                            yShift = y * stride + windowY;
                            if(yShift < inputSize[1] && xShift < inputSize[2]) {
                                output[channel][y][x] += input[channel][yShift][xShift];
                            }
                        }
                    }

                    output[channel][y][x] /= numberOfCells;
                }
            }
        }
    }

    @Override
    public void calculateDerivatives(double[][][] outputDerivatives) {
        int xShift, yShift;

        Matrix.Double_Set(inputDerivatives, 0);

        for(int channel = 0; channel < inputSize[0]; channel ++) { // output channels
            for(int y = 0; y < outputSize[1]; y ++) { // y in output
                for(int x = 0; x < outputSize[2]; x ++) { // x in output
                    for(int windowY = 0; windowY < window; windowY ++) {
                        for(int windowX = 0; windowX < window; windowX ++) {
                            xShift = x * stride + windowX;
                            yShift = y * stride + windowY;
                            if(yShift < inputSize[1] && xShift < inputSize[2]) {
                                inputDerivatives[channel][yShift][xShift] += outputDerivatives[channel][y][x] / numberOfCells;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void commitDerivatives(double learningRate) {

    }
}
