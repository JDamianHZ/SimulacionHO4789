public class PolynomialLinearRegression extends DataB{
   public PolynomialLinearRegression (double x3,double y3){
        super(x3,y3);}


    public static void main(String[]args){

        DataB.datospolinomial();
        metodo();

    }

    public static void metodo(){
        int columnas = 3;
        double x = 0, x2= 0, x3= 0,x4=0, x2pory= 0, xpory= 0, y= 0;
        for (DataB elemento : datosxy) {
            x += elemento.x3;
            x2 += elemento.x3 * elemento.x3;
            x3 += elemento.x3 *elemento.x3 * elemento.x3;
            x4 += elemento.x3 *elemento.x3 * elemento.x3* elemento.x3;
            x2pory +=(elemento.x3*elemento.x3)* elemento.y3 ;
            xpory += elemento.y3*(elemento.x3);
            y += elemento.y3;
        }
            double[][] matrizinicial = new double[3][columnas];
            double[][] matrizy = new double[3][columnas];


            matrizinicial[0][0] = x4;
            matrizinicial[0][1] = x3;
            matrizinicial[0][2] = x2;
            matrizinicial[1][0] = x3;
            matrizinicial[1][1] = x2;
            matrizinicial[1][2] = x;
            matrizinicial[2][0] = x2;
            matrizinicial[2][1] = x;
            matrizinicial[2][2] = datosxy.size();

            matrizy[0][0]=x2pory;
            matrizy[1][0]=xpory;
            matrizy[2][0]=y;

// sacar transpuesta
        double[][] Trans = new double[matrizinicial[0].length][matrizinicial.length];
        for (int i = 0; i < matrizinicial[0].length; i++) {
            for (int j = 0; j < matrizinicial.length; j++) {
                Trans[j][i]=matrizinicial[i][j];
            }



        }
       // System.out.println();
       // System.out.println("Transpuesta");
       // mostrarMatriz(Trans);

        //multiplicar transpuesta por la matriz
        double[][] MpT=new double[matrizinicial.length][matrizy[0].length];
        for (int i = 0; i < matrizinicial.length; i++) {
            for (int j = 0; j < matrizy[0].length; j++) {
                double v=0;
                for (int k=0; k< matrizinicial[0].length;k++){

                    v+=Trans[i][k]*matrizinicial[k][j];
                }
                MpT[i][j]=v;
            }

        }
        //System.out.println();
        //mostrarMatriz(MpT);

        //se agregan los 10
       // System.out.println( "Se agregan 0 y 1");


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
        //System.out.println();
        //mostrarMatriz(matrizCompleta);

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

        //mostrarMatriz(matrizCompleta);
        for(int i=0; i<MpT.length;i++){
            for (int j=0; j<MpT.length;j++){
                Resultado1[i][j]=matrizCompleta[i][j+MpT.length];


            }
        }
        //System.out.println();
        //mostrarMatriz(Resultado1);

        double[][] resultado = new double[columnas][1];
        for (int i = 0; i < matrizinicial[0].length; i++) {
            double sum = 0;
            for (int j = 0; j < matrizinicial.length; j++) {
                sum += Trans[i][j] * matrizy[j][0];
            }
            resultado[i][0] = sum;
        }
        System.out.println("Matriz de multiplicacion");
        //mostrarMatriz(resultado);



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
        //System.out.println();
      //  System.out.println("Multiplicacion de inversa por transpuesta resultado");
      //  mostrarMatriz(resultado3);

      double  primerdato=resultado3[0][0];
      double segundodato=resultado3[1][0];
      double tercerdato=resultado3[2][0];


        System.out.println("La ecuacion cuadratica es: y =  "+primerdato +"  x^2+  "+segundodato+"  x+ "+tercerdato );

        System.out.println("Predicciones: ");
        System.out.println("La ecuacion cuadratica es: y =  "+(primerdato +8*8+segundodato+8+tercerdato) );
        System.out.println("La ecuacion cuadratica es: y =  "+(primerdato +4*4+segundodato+4+tercerdato) );
        System.out.println("La ecuacion cuadratica es: y =  "+(primerdato +3*3+segundodato+3+tercerdato) );
}
    public static void mostrarMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
