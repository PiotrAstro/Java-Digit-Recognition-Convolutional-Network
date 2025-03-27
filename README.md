# Java MNIST Digit Recognition with Convolutional Neural Network

A Java implementation of a Convolutional Neural Network (CNN) for recognizing handwritten digits from the MNIST dataset. This project demonstrates the implementation of deep learning concepts from scratch in Java.

## Features

- Implementation of a Convolutional Neural Network from scratch
- Support for MNIST dataset processing
- Support for different optimisers (ADAM and SGD)
- Multiple loss functions (Cross Entropy, Squared Error)
- Custom activation functions
- Training and validation capabilities
- Model saving and loading
- Visualization of predictions

## Project Structure

```
digitRecognitionConvolution/
├── src/
│   ├── NeuralNetwork/           # Core neural network implementation
│   │   ├── Layers/             # Different types of neural network layers
│   │   ├── OptimizationAlgorithm/  # Optimization methods
│   │   ├── LossFunction/       # Loss function implementations
│   │   └── ActivationFunction/ # Activation function implementations
│   ├── GeneralMath/            # Mathematical utilities
│   ├── PrintingAndDrawing/     # Visualization utilities
│   └── FileHandling/           # Data loading and processing
├── train_correct.txt           # Training data
├── train_labels.txt           # Training labels
├── test.csv                   # Test data
└── convolutionalSave_v01.3.dat # Pre-trained model
```

## Usage

### Training the Model

1. Uncomment the training section in `Main.java`
2. Adjust the number of training images and validation split as needed
3. Run the program to train the model
4. The trained model will be saved to a file

### Making Predictions

1. Load a pre-trained model using `NeuralNetwork.loadNeuralNetworkFromFile()`
2. Load test images using `FileHandling.loadChunkOfImages()`
3. Run predictions using `network.showImagesAndProcess()`

## Data Format

The MNIST dataset is expected in the following format:
- Training images: 28x28 pixel grayscale images
- Labels: Single digit (0-9) for each image
- Test data: Same format as training images

## License

This project is open source and available under the MIT License.

## Author

Piotr Zatwarnicki
