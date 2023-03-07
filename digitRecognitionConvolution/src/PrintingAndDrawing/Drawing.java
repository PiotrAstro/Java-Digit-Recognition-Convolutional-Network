package PrintingAndDrawing;

public class Drawing {
    private static boolean draw = true;

    public static void drawImage(double [][] image) {
        if(draw) {
            int columnCounter = 0;
            StringBuilder sBuilder = new StringBuilder();

            for (int i = 0; i < image.length; i++) {
                for(int k = 0; k < image[i].length; k++) {
                    sBuilder.append(sign(image[i][k]));
                }
                sBuilder.append("\n");
            }

            System.out.println(sBuilder.toString());
        }
    }

    public static char sign(double value) {
        char sign = ' ';
        if(value > 0.8) {
            sign = '0';
        }
        else if(value > 0.5) {
            sign = 'o';
        }
        else if(value > 0.1) {
            sign = '.';
        }
        else {
            sign  = ' ';
        }

        return sign;
    }

    public static void drawHR() {
        if(draw) {
            System.out.println("\n\n****************************************************************************************\n\n");
        }
    }

    public static void doDraw() {
        draw = true;
    }

    public static void doNotDraw() {
        draw = false;
    }
}
