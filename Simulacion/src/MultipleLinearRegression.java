public class MultipleLinearRegression extends  DataB {

    public MultipleLinearRegression(double a, double b, double c, double d, double e) {
        super(a, b, c,d,e);
    }
    public static void main(String[]args){
        DataB.dato1s();
        metodo2();

    }
    public static void mostrarMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }
    public static void metodo2() {
       // int columnas = 4;
        int columnas =3;

        double[][] matriz1 = new double[datosxy.size()][columnas];
        for (int i = 0; i < datosxy.size(); i++) {
            matriz1[i][0] = datosxy.get(i).getA();
            matriz1[i][1] = datosxy.get(i).getB();
            matriz1[i][2] = datosxy.get(i).getC();
       //    matriz1[i][3] = datosxy.get(i).getD();


        }


        mostrarMatriz(matriz1);
        double[][] matriz2 = new double[datosxy.size()][1];

        for (int i = 0; i < datosxy.size(); i++) {
            matriz2[i][0] = datosxy.get(i).getE();
        }
        System.out.println();
        mostrarMatriz(matriz2);

        //transpuestaaa
        double[][] Trans = new double[columnas][datosxy.size()];
        for (int i = 0; i < datosxy.size(); i++) {
            for (int j = 0; j < matriz1[0].length; j++) {
            Trans[j][i]=matriz1[i][j];
            }



        }
        System.out.println();
        mostrarMatriz(Trans);

        //multiplicar transpuesta por la matriz
        double[][] MpT=new double[columnas][columnas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < columnas; j++) {
                double v=0;
                for (int k=0; k<datosxy.size();k++){

                    v+=Trans[i][k]*matriz1[k][j];
                }
                MpT[i][j]=v;
            }

            }
        System.out.println();
        mostrarMatriz(MpT);

            //se agregan los 10
        System.out.println( "Se agregan 0 y 1");


        double[][] matriId = new double[MpT.length][MpT.length];
        for (int i=0; i<MpT.length; i++){
            matriId[i][i]=1.0;
        }
        double[][] matrizCompleta= new double[MpT.length][MpT.length*2];
        for(int i=0; i<MpT.length;i++){
            for (int j=0; j<MpT.length;j++){
                matrizCompleta[i][j]=MpT[i][j];
                matrizCompleta[i][j+MpT.length]=matriId[i][j];

            }
        }
        System.out.println();
        mostrarMatriz(matrizCompleta);

        for (int i=0;i<matrizCompleta.length;i++){
            double pivote = matrizCompleta[i][i];
            for (int j=0;j<matrizCompleta.length*2;j++){

                matrizCompleta[i][j]/=pivote;
            }
            for (int k=0;k<matrizCompleta.length;k++){
                if (k != i){
                    double fac= matrizCompleta[k][i];
                    for (int j=0;j<matrizCompleta.length*2;j++){
                        matrizCompleta[k][j]-=fac*matrizCompleta[i][j];
                    }

                }

            }

        }
        System.out.println();
        double[][] Resultado1= new double[MpT.length][MpT.length];

        mostrarMatriz(matrizCompleta);
        for(int i=0; i<MpT.length;i++){
            for (int j=0; j<MpT.length;j++){
                Resultado1[i][j]=matrizCompleta[i][j+MpT.length];


            }
        }
        System.out.println();
        mostrarMatriz(Resultado1);
        double[][] resultado = new double[columnas][1];
        for (int i = 0; i < columnas; i++) {
            double sum = 0;
            for (int j = 0; j < datosxy.size(); j++) {
                sum += Trans[i][j] * matriz2[j][0];
            }
            resultado[i][0] = sum;
        }


    double[][] resultado3 = new double [columnas][1];
    for (int i=0; i < Resultado1.length;i++){
        for (int j=0;j< resultado[0].length;j++){
            double suma = 0;
            for (int k=0; k< Resultado1[0].length; k++){
                suma+= Resultado1[i][k]*resultado[k][j];


            }
            resultado3[i][j]=suma;

        }

    }
        System.out.println();
mostrarMatriz(resultado3);

if (columnas == 4){
    System.out.println("4");
    Double B0=resultado3[0][0];
    Double B1=resultado3[1][0];
    Double B2=resultado3[2][0];
     Double B3=resultado3[3][0];
    System.out.println();
    System.out.println(" B0 "+B0+" B1 "+B1+" B2 "+B2+" B3 "+B3);
    System.out.println("Y= X3" +B3+" X2 "+B2+" X1 "+B1+"+"+B0);
    System.out.println("Y="+(100*B3+70*B2+89*B1+B0));
    System.out.println("Y="+(200*B3+140*B2+90*B1+B0));
    System.out.println("Y="+(300*B3+170*B2+95*B1+B0));



}
else {
    System.out.println("3");

    Double B0=resultado3[0][0];
    Double B1=resultado3[1][0];
    Double B2=resultado3[2][0];
    System.out.println();
    System.out.println(" B0 "+B0+" B1 "+B1+" B2 "+B2);
    System.out.println("Y= X3" +" X2 "+B2+" X1 "+B1+"+"+B0);
    System.out.println("Y="+(70*B2+89*B1+B0));
    System.out.println("Y="+(140*B2+90*B1+B0));
    System.out.println("Y="+(170*B2+95*B1+B0));

}


    }
    }
