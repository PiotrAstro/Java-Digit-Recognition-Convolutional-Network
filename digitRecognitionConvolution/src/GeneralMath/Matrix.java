package GeneralMath;

import NeuralNetwork.ActivationFunction.ActivationFunction;

public class Matrix {


    //different things
    public static void Double_ApplyDerivative(double[][][] derivatives, double[][][] input, ActivationFunction activationFunction) {
        for(int index_0 = 0; index_0 < derivatives.length; index_0 ++) {
            for(int index_1 = 0; index_1 < derivatives[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < derivatives[index_0][index_1].length; index_2 ++) {
                    derivatives[index_0][index_1][index_2] *= activationFunction.derivative(input[index_0][index_1][index_2]);
                }
            }
        }
    }





    //-----------------------------------------------------------

    //1D
    public static void Double_Add(double[] table, double value) {
        for(int i = 0; i < table.length; i++) {
            table[i] += value;
        }
    }

    public static void Double_Add(double[] tableChanged, double[] table) {
        for(int i = 0; i < tableChanged.length; i++) {
            tableChanged[i] += table[i];
        }
    }

    public static void Double_Set(double[] table, double value) {
        for(int i = 0; i < table.length; i++) {
            table[i] = value;
        }
    }

    public static void Double_Multiply(double[] tableChanged, double value) {
        for(int i = 0; i < tableChanged.length; i++) {
            tableChanged[i] *= value;
        }
    }

    public static void Double_Multiply(double[] tableChanged, double[] table) {
        for(int i = 0; i < tableChanged.length; i++) {
            tableChanged[i] *= table[i];
        }
    }

    public static void Double_Divide(double[] tableChanged, double value) {
        for(int i = 0; i < tableChanged.length; i++) {
            tableChanged[i] *= value;
        }
    }

    public static void Double_Divide(double[] tableChanged, double[] table) {
        for(int i = 0; i < tableChanged.length; i++) {
            tableChanged[i] /= table[i];
        }
    }



    //---------------------------------------------------------------------------------------------



    //2D
    public static void Double_Add(double[][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                table[index_0][index_1] += value;
            }
        }
    }

    public static void Double_Add(double[][] tableChanged, double[][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                tableChanged[index_0][index_1] += table[index_0][index_1];
            }
        }
    }

    public static void Double_Set(double[][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                table[index_0][index_1] = value;
            }
        }
    }

    public static void Double_Multiply(double[][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                table[index_0][index_1] *= value;
            }
        }
    }

    public static void Double_Multiply(double[][] tableChanged, double[][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                tableChanged[index_0][index_1] *= table[index_0][index_1];
            }
        }
    }

    public static void Double_Divide(double[][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                table[index_0][index_1] /= value;
            }
        }
    }

    public static void Double_Divide(double[][] tableChanged, double[][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                tableChanged[index_0][index_1] /= table[index_0][index_1];
            }
        }
    }



    //---------------------------------------------------------------------------------------------


    //3D
    public static void Double_Add(double[][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    table[index_0][index_1][index_2] += value;
                }
            }
        }
    }

    public static void Double_Add(double[][][] tableChanged, double[][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2 ++) {
                    tableChanged[index_0][index_1][index_2] += table[index_0][index_1][index_2];
                }
            }
        }
    }

    public static void Double_Set(double[][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    table[index_0][index_1][index_2] = value;
                }
            }
        }
    }

    public static void Double_Multiply(double[][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    table[index_0][index_1][index_2] *= value;
                }
            }
        }
    }

    public static void Double_Multiply(double[][][] tableChanged, double[][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2 ++) {
                    tableChanged[index_0][index_1][index_2] *= table[index_0][index_1][index_2];
                }
            }
        }
    }

    public static void Double_Divide(double[][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    table[index_0][index_1][index_2] /= value;
                }
            }
        }
    }

    public static void Double_Divide(double[][][] tableChanged, double[][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for(int index_1 = 0; index_1 < tableChanged[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2 ++) {
                    tableChanged[index_0][index_1][index_2] /= table[index_0][index_1][index_2];
                }
            }
        }
    }



    //---------------------------------------------------------------------------------------------


    //4D
    public static void Double_Add(double[][][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    for(int index_3 = 0; index_3 < table[index_0][index_1][index_2].length; index_3 ++) {
                        table[index_0][index_1][index_2][index_3] += value;
                    }
                }
            }
        }
    }

    public static void Double_Add(double[][][][] tableChanged, double[][][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for (int index_1 = 0; index_1 < tableChanged[index_0].length; index_1++) {
                for (int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2++) {
                    for (int index_3 = 0; index_3 < tableChanged[index_0][index_1][index_2].length; index_3++) {
                        tableChanged[index_0][index_1][index_2][index_3] += table[index_0][index_1][index_2][index_3];
                    }
                }
            }
        }
    }

    public static void Double_Set(double[][][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    for(int index_3 = 0; index_3 < table[index_0][index_1][index_2].length; index_3 ++) {
                        table[index_0][index_1][index_2][index_3] = value;
                    }
                }
            }
        }
    }

    public static void Double_Multiply(double[][][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    for(int index_3 = 0; index_3 < table[index_0][index_1][index_2].length; index_3 ++) {
                        table[index_0][index_1][index_2][index_3] *= value;
                    }
                }
            }
        }
    }

    public static void Double_Multiply(double[][][][] tableChanged, double[][][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for (int index_1 = 0; index_1 < tableChanged[index_0].length; index_1++) {
                for (int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2++) {
                    for (int index_3 = 0; index_3 < tableChanged[index_0][index_1][index_2].length; index_3++) {
                        tableChanged[index_0][index_1][index_2][index_3] *= table[index_0][index_1][index_2][index_3];
                    }
                }
            }
        }
    }

    public static void Double_Divide(double[][][][] table, double value) {
        for(int index_0 = 0; index_0 < table.length; index_0 ++) {
            for(int index_1 = 0; index_1 < table[index_0].length; index_1 ++) {
                for(int index_2 = 0; index_2 < table[index_0][index_1].length; index_2 ++) {
                    for(int index_3 = 0; index_3 < table[index_0][index_1][index_2].length; index_3 ++) {
                        table[index_0][index_1][index_2][index_3] /= value;
                    }
                }
            }
        }
    }

    public static void Double_Divide(double[][][][] tableChanged, double[][][][] table) {
        for(int index_0 = 0; index_0 < tableChanged.length; index_0 ++) {
            for (int index_1 = 0; index_1 < tableChanged[index_0].length; index_1++) {
                for (int index_2 = 0; index_2 < tableChanged[index_0][index_1].length; index_2++) {
                    for (int index_3 = 0; index_3 < tableChanged[index_0][index_1][index_2].length; index_3++) {
                        tableChanged[index_0][index_1][index_2][index_3] /= table[index_0][index_1][index_2][index_3];
                    }
                }
            }
        }
    }
}
