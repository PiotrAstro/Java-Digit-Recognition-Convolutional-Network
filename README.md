# Java-Digit-Recognition-Convolutional-Network
 Java MNIST digit recognition with Convolutional Neural Network

 Quite generic version. One could build NN using custom layers, e.g. building Convolutional NN:
```
public static NeuralNetwork NeuralNetworkCreatorConvolutional_v01() {
    double initialWeightsStart = -0.2; // bottom value, then range is added
    double initialWeightsRange = 0.4;
    double initialBiasStart = 0;
    double initialBiasRange = 0;
    double learningRate = 0.001;
    int batchSize = 16;
    int[] inputSize = new int[] {1, 28, 28};
    LossFunction lossFunction = new CrossEntropy();
    OptimizationAlgorithm optimizationAlgorithm = new GradientOptimization();

    NeuralNetwork network = new NeuralNetwork(initialWeightsStart, initialWeightsRange, initialBiasStart, initialBiasRange, learningRate, batchSize, inputSize, optimizationAlgorithm, lossFunction);

    network.addConvolutionalLayer(10, 3, new ReLuActivation());
    network.addMaxPoolingLayer(2, 2);
    network.addConvolutionalLayer(20, 3, new ReLuActivation());
    network.addMaxPoolingLayer(2, 2);
    network.addFlatteningLayer();
    network.addDenseLayer(16, new ReLuActivation());
    network.addDenseLayer(10, new SoftMaxActivation());

    return network;
}
```

There are several loss functions implemented and even ADAM optimiser.

To try it yourself you should look at Main.java. Here you can find loading images for guessing (used to print image into terminal with NN output). There is also commented part of loading and preparing train and validation data and training itself. NN is saved to file.
