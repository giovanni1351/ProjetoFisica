/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Janela;
import view.MenuExercicio1;

/**
 *
 * @author Givas
 */
public class Controller {
    private Janela janela;
    private MenuExercicio1 menuEx1;
    public double variavel= 0.05;
    public double variavel2 = 3.0;
    public int reps = 3;
    public double valorMaximoEixo =2.0;
    public int altura;
    public int largura;
    public int n=1;
    public int nInicial=1;
    public int nFinal=1;
    double h = 6.62607015 * Math.pow(10, -34); // constante de Planck
    double m = 9.10938356 * Math.pow(10, -31); // massa do elétron
    double e = 1.602176634 * Math.pow(10, -19); // carga do elétron
    double c = 3.0 * Math.pow(10, 8); // velocidade da luz

    private int N1=1,N2=1;
    public Controller(Janela janela, MenuExercicio1 menuEx1) {
        this.janela = janela;
        this.menuEx1 = menuEx1;
    }

    public Janela getJanela() {
        return janela;
    }
    public void setJanela(Janela janela) {
        this.janela = janela;
    }
    public void animacao(){
       // g.create(WIDTH, WIDTH, WIDTH, HEIGHT);
        Graphics g = janela.g;
        Color cor = new Color(0,240,0);
        g.setColor(cor);
        altura = janela.preferredSize().height;
        largura = janela.preferredSize().width;
        int limiteVertical = altura/60;
        int y = -limiteVertical;
        boolean subindo = true;
        int cont = 0;

        while(cont<=reps){
            int x0=0;
            int y0=(altura/4);
            g.setColor(Color.BLACK);
            
            
            double primeiro = valorMaximoEixo;
            double intervaloDosEixos = valorMaximoEixo/(double)limiteVertical;
//            g.drawLine(largura/2,0, largura/2,altura);
//            for(int x = 0 ;x < altura;x+=altura/(limiteVertical*2)){
//                double saida = primeiro*10;
//                saida = (int)saida;
//                saida = saida/10.0;
//                String str = String.valueOf(saida);
//
//                g.drawString(str, (largura/2)+5, x);
//                g.drawOval((largura/2)-1, x-1, 2, 2);
//
//                primeiro -= intervaloDosEixos;
//            }        
            //primeiro = -valorMaximoEixo;
            primeiro = 0;
            g.drawLine(largura/2, 0, largura/2, altura);
            g.drawLine(0,(altura/4), largura,(altura/4));
            g.drawLine(0,(altura/2), largura,(altura/2));
            g.drawLine(0,(3*altura/4), largura,(3*altura/4));
            //Numerando os eixos
            for(int x = 0 ;x < largura/2;x+=largura/(limiteVertical)){
                double saida = primeiro;
                //saida = (int)saida;
                //saida = saida/10.0;
                String str = String.format("%.1f",saida);
                if(str.equals("0.9")){
                    g.drawString("1", x+(largura/2), altura/4);
                    g.drawString("1", x, altura/4);
                    g.drawString("1", x+(largura/2), 3*altura/4);
                    g.drawString("1", x, 3*altura/4);
                }else{
                    g.drawString(str, x+(largura/2), altura/4);
                    g.drawString(str, x, altura/4);
                    g.drawString(str, x+(largura/2), 3*altura/4);
                    g.drawString(str, x, 3*altura/4);
                }
                //g.drawOval(x-1, altura/2-1, 2, 2);

                primeiro += intervaloDosEixos;
            }
            g.setColor(Color.GREEN);
            //Primeiro grafico
            double NPrimeiroGrafico = variavel2/N1;
            for(double x = 0 ; x < largura/2;x+=0.1){
                int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x)/NPrimeiroGrafico)));
                //int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x)/2)));
                //(altura/janela.variavel2)
                g.drawLine((int)x,y1+(altura/4), (int)x0, y0+(altura/4));
                y0=y1;
                x0=(int)x;
               // g.drawLine(x,x, x-1,x-1 );
            }
            //Segundo grafico
            double NSegundoGrafico = variavel2/N2;

            for(double x = largura/2 ; x < largura;x+=0.1){
                int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x-largura/2)/NSegundoGrafico)));
                //int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x-largura/2)/3)));
                //(altura/janela.variavel2)
                g.drawLine((int)x,y1+(altura/4), (int)x0, y0+(altura/4));
                y0=y1;
                x0=(int)x;
               // g.drawLine(x,x, x-1,x-1 );
            }
            

            
            try {
                sleep(60);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.clearRect(0, 0, largura, altura);
            if(subindo){
                y++;
                if(y==limiteVertical){
                    subindo = false;
                    cont++;
                    y--;
                }
            }
            else{
                y--;
                if(y==-limiteVertical){
                   subindo = true;    
                   y++;
                }
            }
        }
    }
    public void clear(){
        janela.g.clearRect(0, 0, largura, altura);
    }


    public void getRepsFromMenu(){
        try{
            reps = Integer.parseInt(menuEx1.getTxtReps().getText());
        }
        catch(Exception e){
            menuEx1.getTxtReps().setText("Digite um inteiro");
        }
    }

    public void calcularExercicioUm(){
        String entradaA = menuEx1.getTxtA().getText();
        String entradaB = menuEx1.getTxtB().getText();
        String larguraCaixa = menuEx1.getTxtLarguraCaixa().getText();
        String numeroQuanticoInicial = menuEx1.getTxtNumeroQuanticoInicial().getText();
        String numeroQuanticoFinal = menuEx1.getTxtNumeroQuanticoFinal().getText();
        double A=0.0,B=0.0,larguraCaixaDouble=0;
        int nQuanticoInicial=1,nQuanticoFinal=0;
        try{
            A = Double.parseDouble(entradaA);
            System.out.println("A:"+A);
        }catch(Exception e){
            menuEx1.getTxtA().setText("Deu ruim");
        }
        try{
            B = Double.parseDouble(entradaB);
            System.out.println("B:"+B);

        }catch(Exception e){
            menuEx1.getTxtB().setText("Deu ruim");
        }
        try{
            larguraCaixaDouble = Double.parseDouble(larguraCaixa)* Math.pow(10, -9);
            System.out.println("Largura"+larguraCaixaDouble);
        }
        catch(Exception e){
            menuEx1.getTxtLarguraCaixa().setText("Deu ruim");
        }
        try{
            nQuanticoInicial = Integer.parseInt(numeroQuanticoInicial);
            System.out.println("Numero Quantico Inicial = "+nQuanticoInicial);
        }
        catch(Exception e){
            menuEx1.getTxtNumeroQuanticoInicial().setText("Deu ruim");
        }
        try{
            nQuanticoFinal = Integer.parseInt(numeroQuanticoFinal);
            System.out.println("Numero Quantico Final = "+nQuanticoFinal);

        }catch(Exception e){
            menuEx1.getTxtNumeroQuanticoFinal().setText("Deu ruim");
        }
        
// Cálculo da função de onda
        if(nQuanticoInicial !=0&& nQuanticoFinal == 0){
            n = nQuanticoInicial;
        }
        double k = n * Math.PI / larguraCaixaDouble;
        String linha1 = "Função de onda quântica no SI dos dois níveis: (x) =" +
                Math.sqrt(2 / larguraCaixaDouble) + " sin(" + k + "x)";
        
// Cálculo da energia
        //double E = h * h / (8 * m * L * L);
        double E = n * n * h * h / (8 * m * larguraCaixaDouble * larguraCaixaDouble);
        //double E = n * n * Math.PI * Math.PI * h * h / (2 * m * L * L);
        String linha2 = String.format("Energia do nível quântico inicial e final %f %f",E,E/e);
//"A energia do elétron e: " + E + " J = " + E / e + " eV";
        



// Cálculo da velocidade
        double v = Math.sqrt(2 * E / m);
        String linha3 = "A velocidade da partícula e: " + v + " m/s";
     
        



// Cálculo do comprimento de onda de De Broglie
        double lambda = h / (m * v);
        String linha4 = "O comprimento de onda de De Broglie da partícula e: " + lambda + " m";
         // Cálculo da probabilidade
        //altere os nm / m das particulas se precisar (x1 e x2)
        //double x1 = 0.07 * Math.pow(10, -9); // Converte para metros
       // double x2 = 0.14 * Math.pow(10, -9); // Converte para metros
        double x1 = A*1e-9;
        double x2 = B*1e-9;
        double L = larguraCaixaDouble;
        double integral_n = (x2 - x1) - (Math.sin(2 * Math.PI * n * x2 / L) -
                Math.sin(2 * Math.PI * n * x1 / L)) / (2 * Math.PI * n / L);
        double P_n = integral_n * 2.0 / (L * L);
        //System.out.println(P_n);
        System.out.println(integral_n);
        String linha5 = "A probabilidade de encontrar a particula entre e: " + P_n;
        
        
        int n_inicial = (int)nQuanticoInicial;
        int n_final = (int)nQuanticoFinal;
        double E_inicial = n_inicial * n_inicial * h * h / (8 * m *
                larguraCaixaDouble * larguraCaixaDouble); // Energia do estado inicial
        double E_final = n_final * n_final * h * h / (8 * m *
                larguraCaixaDouble * larguraCaixaDouble); // Energia do estado final
        double E_foton = E_final - E_inicial; // Energia do fóton absorvido
        String linha6 = "A energia do foton absorvido e: " + E_foton + 
                " J = " + E_foton / e + " eV";
         // Cálculo do comprimento de onda do fóton
        double lambda_foton = h * c / E_foton;
        String linha7 = "O comprimento de onda do foton e: " + lambda_foton + " m";
        // Cálculo da frequência do fóton
        double f_foton = c / lambda_foton;
        String linha8 ="A frequência do foton e: " + f_foton + " Hz";
        menuEx1.getTxtResposta().setText(
                linha1+"\n"+
                linha2+"\n"+
                linha3+"\n"+
                linha4+"\n"+
                linha5+"\n"+
                linha6+"\n"+
                linha7+"\n"+
                linha8+"\n");
        N1= nQuanticoInicial;
        N2= nQuanticoFinal;
        n=1;
        try {
            sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        animacao();
    }
    
    
    
}
