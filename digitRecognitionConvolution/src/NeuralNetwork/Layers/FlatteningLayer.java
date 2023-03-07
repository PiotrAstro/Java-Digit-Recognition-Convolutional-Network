package NeuralNetwork.Layers;

public class FlatteningLayer extends Layer{

    public FlatteningLayer(int[] inputSize) {
        super(inputSize);

        outputSize = new int[] {1, 1, inputSize[0] * inputSize[1] * inputSize[2]};
        output = new double[outputSize[0]][outputSize[1]][outputSize[2]];
    }


    // from abstract class

    @Override
    public void calculate(double[][][] input) {
        this.input = input;
        int index = 0;

        for(int channel = 0; channel < inputSize[0]; channel ++) { // output channels
            for(int y = 0; y < inputSize[1]; y ++) { // y in output
                for(int x = 0; x < inputSize[2]; x ++) { // x in output
                    output[0][0][index] = input[channel][y][x];
                    index ++;
                }
            }
        }
    }

    @Override
    public void calculateDerivatives(double[][][] outputDerivatives) {
        int index = 0;

        for(int channel = 0; channel < inputSize[0]; channel ++) { // output channels
            for(int y = 0; y < inputSize[1]; y ++) { // y in output
                for(int x = 0; x < inputSize[2]; x ++) { // x in output
                    inputDerivatives[channel][y][x] = outputDerivatives[0][0][index];
                    index ++;
                }
            }
        }
    }

    @Override
    public void commitDerivatives(double learningRate) {

    }
}
