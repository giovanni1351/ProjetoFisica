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
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author Givas
 */
public class Controller {
    private Janela janela;
    private final MenuExercicio1 menuEx1;
    public double variavel= 0.10;
    public double variavel2 = 3.0;
    public int reps = 2;
    public double valorMaximoEixo =4.0;
    public int altura;
    public int largura;
    public int n=1;
    public int nInicial=1;
    public int nFinal=1;
    double h = 6.62607015 * Math.pow(10, -34); // constante de Planck
    double massaEletron = 9.10938356 * Math.pow(10, -31); // massa do elétron
    double e = 1.602176634 * Math.pow(10, -19); // carga do elétron
    double c = 3.0 * Math.pow(10, 8); // velocidade da luz
    double massaProton = 1.672 * Math.pow(10, -27);
    private int N1=1,N2=1;
    double L = 0;
    ArrayList<Integer> posicoesX = new ArrayList<>();
    ArrayList<Integer> posicoesY = new ArrayList<>();
    Graphics g;
    double larguraTela;
    double alturaTela;
    public Controller(Janela janela, MenuExercicio1 menuEx1) {
        Locale.setDefault(Locale.US);
        this.janela = janela;
        this.menuEx1 = menuEx1;
        
        larguraTela = janela.getWidth();
        alturaTela = janela.getHeight();
        
        //Linhas Verticais
        posicoesX.add((int)larguraTela/4);
        posicoesX.add((int)(3*larguraTela/4));
        
        //Linhas horizontais
        posicoesY.add((int)alturaTela/8);//Limite superior 0
        posicoesY.add((int)alturaTela/8+20);//E5 1
        //posicoesY.add((int)(2*alturaTela/8)); 2
        posicoesY.add((int)(3*alturaTela/8));//E4 2
        posicoesY.add((int)(4*alturaTela/8));//E3 3
        posicoesY.add((int)(5*alturaTela/8));//E2 4
        posicoesY.add((int)(6*alturaTela/8-20));//E1 5
        posicoesY.add((int)(6*alturaTela/8));//Limite inferior 6
        
    }

    public Janela getJanela() {
        return janela;
    }
    public void setJanela(Janela janela) {
        this.janela = janela;
    }
    public void simular(){
                        //0 1 2 3 4 5 6 
        int correcao[] = {6,5,4,3,2,1,0};
        for(int x = N1; x <= N2;x++){
            //enis.add(correcao[x]); 
            boolean ultimo = (x==N2)? true:false;
            animaBolaSubir(posicoesY.get(correcao[x]),ultimo);
        }
        for(int x = N2; x > 1;x--){
           // enisReverso.add(correcao[x]);       
            animaBolaDescer(posicoesY.get(correcao[x]),posicoesY.get(correcao[x-1]));
        }

    }
    public void desenhaDisplay(){
        g = janela.g;
        g.setColor(Color.BLACK);
        
        //Quadrado principal
        //vertical
        g.drawLine((int)posicoesX.get(0),(int)posicoesY.getFirst(), (int)posicoesX.get(0), (int)posicoesY.getLast());
        g.drawLine((int)posicoesX.get(1),(int)posicoesY.getFirst(), (int)posicoesX.get(1), (int)posicoesY.getLast());
        
        // Horizontal com os numeros.
        int difX = Math.abs(posicoesX.getFirst()-posicoesX.getLast());
        difX = difX/5;
        int cont = 1;
        for(int variY =posicoesY.getFirst();variY <= posicoesY.getLast();variY +=(posicoesY.getLast()-posicoesY.getFirst()) ){
            g.drawLine((int)posicoesX.get(1),(int)variY, (int)posicoesX.get(0), (int)variY);
            g.drawString("0",posicoesX.getFirst(), variY-3);

            for(int x  = posicoesX.getFirst(); x < posicoesX.getLast();x+=20){
                if(x % difX >0 && x %difX < 20){
                    String str = String.valueOf(cont);
                    g.drawString(str,x, variY-3);
                    cont++;
                }
                g.drawLine(x, variY-3, x, variY+3);            
            }
            cont = 1;
        }
        
        //Vertical com os numeros e os E's
        int difY = posicoesY.getLast()-posicoesY.getFirst();
        difY/=4;
        double Eev =0.1;
        g.drawString("E1", posicoesX.getLast()+30, posicoesY.get(posicoesY.size()-2));
        for(int y = posicoesY.getLast(); y >posicoesY.getFirst(); y-= 20 ){
            if(y % difY > 0 && y %difY<20){
                String str = String.format("%.1f",Eev);
                String str2 = String.format("E%.0f",Eev*10+1);
                g.drawString(str, posicoesX.getFirst()-30, y);
                g.drawString(str2, posicoesX.getLast()+30, y);
                Eev += 0.1;
            }
            g.drawLine(posicoesX.getFirst()+3, y, posicoesX.getFirst()-3, y);
            g.drawLine(posicoesX.getLast()+3, y, posicoesX.getLast()-3, y);
        }
        

        // Agora as linhas de dentro
        
        g.setColor(Color.blue);
        int Px = posicoesX.getFirst();
        int Ux = posicoesX.getLast();
        for(int y = 1; y < posicoesY.size()-1;y++){
            int atualY = posicoesY.get(y);
            g.drawLine(Px, atualY, Ux, atualY);
        }
        
        
        
        
    }
    public void animaBolaSubir(int yAtual,boolean ultimo){
        
        //Da esquerda para a direita
        for(int x = posicoesX.getFirst();x<posicoesX.getLast();x+=10){
            desenhaDisplay();
            g.setColor(Color.green);
            g.fillOval(x, yAtual-12, 25, 25);
            esperar(40);
            g.clearRect(0, 0, (int)larguraTela, (int)alturaTela);
        }
        
        //Posição inicial do foton
        //Ele só vai aparecer quando eletron ou proton estiver indo para a esquerda

        int xFoton = 0; 
        int yFoton = yAtual-12;
        double dif = (double)posicoesX.getFirst();
        dif /= ((posicoesX.getLast()-posicoesX.getFirst())/10);
        
        //Animando o proton da direita para a esquerda e o foton da esquerda para a direita
        for(int x = posicoesX.getLast();x>posicoesX.getFirst();x-=10){
            desenhaDisplay();
            g.setColor(Color.green);
            g.fillOval(x, yAtual-12, 25, 25);
            g.setColor(Color.red); 
            if(!ultimo){
                g.fillOval(xFoton, yFoton, 25, 25);
                xFoton+=(int)dif;
            }
            esperar(40);
            g.clearRect(0, 0, (int)larguraTela, (int)alturaTela);
            
        }
    }
    public void animaBolaDescer(int yAtual,int yProx){
        
        //Da esquerda para a direita
        for(int x = posicoesX.getFirst();x<posicoesX.getLast();x+=10){
            desenhaDisplay();
            g.setColor(Color.green);
            g.fillOval(x, yAtual-12, 25, 25);
            esperar(40);
            g.clearRect(0, 0, (int)larguraTela, (int)alturaTela);
        }
        
        //Posição inicial do foton
        //Ele só vai aparecer quando eletron ou proton estiver indo para a esquerda

        int xFoton = posicoesX.getLast(); 
        int yFoton = yAtual-12;
        double dif = (double)larguraTela - posicoesX.getLast();
        dif /= ((posicoesX.getLast()-posicoesX.getFirst())/10);
        
        //Animando o proton da direita para a esquerda e o foton da esquerda para a direita
        for(int x = posicoesX.getLast();x>posicoesX.getFirst();x-=10){
            desenhaDisplay();
            g.setColor(Color.green);
            g.fillOval(x, yProx-12, 25, 25);
            g.setColor(Color.red);            
            g.fillOval(xFoton, yFoton, 25, 25);
            xFoton+=(int)dif;
            esperar(40);
            g.clearRect(0, 0, (int)larguraTela, (int)alturaTela);
            
        }
    }
    public void animGrafico(){
       // g.create(WIDTH, WIDTH, WIDTH, HEIGHT);
        g = janela.g;
        Color cor = new Color(0,240,0);
        g.setColor(cor);
        altura = janela.preferredSize().height;
        largura = janela.preferredSize().width;
        int limiteVertical = altura/60;
        int y = -limiteVertical;
        boolean subindo = true;
        int cont = 0;
        g.clearRect(0, 0, largura, altura);

        int x0=0;
        int y0=(3*altura/4);
 //Terceiro grafico
        for(double x = 0 ; x <= largura/2 ;x+=0.1){
            double xDoGrafico = x/(largura/2);
            double y1 = -1* (2/L)*Math.pow(Math.sin(xDoGrafico*3*N1),2)*1e-8;
            //int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x-largura/2)/3)));
            //(altura/janela.variavel2)
            g.drawLine((int)x,(int)y1+(3*altura/4), (int)x0, (int)y0+(3*altura/4));
            y0=(int)y1;
            x0=(int)x;
        }
        //Quarto grafico

        for(double x = largura/2 ; x < largura ;x+=0.1){
            double xDoGrafico = (x-largura/2)/(largura/2);
            double y1 = -1* (2/L)*Math.pow(Math.sin(xDoGrafico*3*N2),2)*1e-8;
            //int y1 = (int)((y/variavel)*(Math.sin(Math.toRadians(x-largura/2)/3)));
            //(altura/janela.variavel2)
            g.drawLine((int)x,(int)y1+(3*altura/4), (int)x0, (int)y0+(3*altura/4));
            y0=(int)y1;
            x0=(int)x;
           // g.drawLine(x,x, x-1,x-1 );
        }
        while(cont<=reps){
            x0=0;
            y0=(altura/4);
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
            

            esperar();

            g.clearRect(0, 0, largura, altura/2);
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
        clear();
    }
    public void clear(){
        g.clearRect(0, 0, largura, altura);
    }
    public void esperar(){
        try {
            sleep(60);
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void esperar(int tempo){
        try {
            sleep(tempo);
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            if(nQuanticoInicial < 1 || nQuanticoInicial > 5) {
                throw new Exception("Número quântico inicial deve estar entre 1 e 5");
            }
            N1 = nQuanticoInicial;
            System.out.println("Número Quântico Inicial = "+nQuanticoInicial);
        }
        catch(Exception e){
            menuEx1.getTxtNumeroQuanticoInicial().setText("Deu ruim: " + e.getMessage());
        }
        try{
            nQuanticoFinal = Integer.parseInt(numeroQuanticoFinal);
            if(nQuanticoFinal < 1 || nQuanticoFinal > 5) {
                throw new Exception("Número quântico final deve estar entre 1 e 5");
            }
            N2 = nQuanticoFinal;
            System.out.println("Número Quântico Final = "+nQuanticoFinal);
        }
        catch(Exception e){
            menuEx1.getTxtNumeroQuanticoFinal().setText("Deu ruim: " + e.getMessage());
        }
        boolean eletronIsSelected= menuEx1.getRbEletron().isSelected();
        double massa = (eletronIsSelected)? massaEletron:massaProton;
        String tipo = ( eletronIsSelected )?  "Eletron":"Proton";
        
        
        
        
        
// Cálculo da função de onda
        if(nQuanticoInicial !=0&& nQuanticoFinal == 0){
            n = nQuanticoInicial;
        }
        double k1 = nQuanticoInicial * Math.PI / larguraCaixaDouble;
        double k2 = nQuanticoFinal * Math.PI / larguraCaixaDouble;
        String linha1 = String.format("Função de onda quântica no SI do n = %d: (x) = %.3e  sin( %.3e x)",nQuanticoInicial,
                Math.sqrt(2 / larguraCaixaDouble),k1 );
        String linha1_1 = String.format("Função de onda quântica no SI do n = %d: (x) = %.3e  sin( %.3e x)",nQuanticoFinal,
                Math.sqrt(2 / larguraCaixaDouble),k2 );
        
        
// Cálculo da energia
        //double E = h * h / (8 * m * L * L);
        double E1 = N1 * N1 * h * h / (8 * massa * larguraCaixaDouble * larguraCaixaDouble);
        double E2 = N2 * N2 * h * h / (8 * massa * larguraCaixaDouble * larguraCaixaDouble);
        //double E = n * n * Math.PI * Math.PI * h * h / (2 * m * L * L);
        String linha2 = String.format("A energia do %s no n = %d e: %.3e J = %.3e eV",tipo,N1,E1,E1/e);
        String linha2_2 = String.format("A energia do %s no n = %d e: %.3e J = %.3e eV",tipo,N2,E2,E2/e);

        



// Cálculo da velocidade
        double v1 = Math.sqrt(2 * E1 / massa);
        double v2= Math.sqrt(2 * E2 / massa);
        String linha3 = String.format("A velocidade da partícula  n = %d e: %.3e m/s",N1, v1);
        String linha3_3 = String.format("A velocidade da partícula  n = %d e: %.3e m/s",N2, v2);
     
        



// Cálculo do comprimento de onda de De Broglie
        double lambda1 = h / (massa * v1);
        double lambda2= h / (massa * v2);
        String linha4 = String.format("O comprimento de onda de De Broglie da partícula n = %d e: %.3e m",N1, lambda1);
        String linha4_4 = String.format("O comprimento de onda de De Broglie da partícula n = %d e: %.3e m",N2, lambda2);
      
        
// Cálculo da probabilidade
        //altere os nm / m das particulas se precisar (x1 e x2)
        //double x1 = 0.07 * Math.pow(10, -9); // Converte para metros
       // double x2 = 0.14 * Math.pow(10, -9); // Converte para metros
        double x1 = A*1e-9;
        double x2 = B*1e-9;
        L = larguraCaixaDouble;
        double P_n = integrate(x1,x2,10000);
        double P_n2 = integrate2(x1,x2,10000);
//System.out.println(P_n);
        String linha5 = String.format("A probabilidade de encontrar a particula entre e:nivel %d %.3e nivel %d %.3e",N1, P_n,N2,P_n2);
        //String linha5 = String.format("A probabilidade de encontrar a particula entre e: %.3e ", P_n);
        
        
        
        //Calculo energia do foton absorvido
        int n_inicial = (int)nQuanticoInicial;
        int n_final = (int)nQuanticoFinal;
        double E_inicial = n_inicial * n_inicial * h * h / (8 * massa *
                larguraCaixaDouble * larguraCaixaDouble); // Energia do estado inicial
        double E_final = n_final * n_final * h * h / (8 * massa *
                larguraCaixaDouble * larguraCaixaDouble); // Energia do estado final
        double E_foton = E_final - E_inicial; // Energia do fóton absorvido
        String linha6 = String.format("A energia do foton absorvido e: %.3e J = %.3e eV", + E_foton,E_foton / e );
         // Cálculo do comprimento de onda do fóton
        double lambda_foton = Math.abs(h * c / E_foton);
        //String linha7 = "O comprimento de onda do foton e: " + lambda_foton + " m";
        String linha7 = String.format("O comprimento de onda do foton e: %.3e m ",lambda_foton);
        // Cálculo da frequência do fóton
        double f_foton = Math.abs(c / lambda_foton);
        String linha8 =String.format("A frequência do foton e: %.3e Hz",f_foton );
        menuEx1.getTxtResposta().setText(
                tipo+"\n"+
                linha1+"\n"+
                linha1_1+"\n"+
                linha2+"\n"+
                linha2_2+"\n"+
                linha3+"\n"+
                linha3_3+"\n"+
                linha4+"\n"+
                linha4_4+"\n"+
                linha5+"\n"+
                linha6+"\n"+
                linha7+"\n"+
                linha8+"\n");
        N1= nQuanticoInicial;
        N2= nQuanticoFinal;
        n=1;
        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //animacao();
    }
    
    public void calcularExercicioDois(){
        
        double A=0,K=0,x=0;
        
        try{
            A = Double.parseDouble(menuEx1.getTxtA1().getText());
        }catch(Exception e){
            menuEx1.getTxtA1().setText("Deu ruim");
        }
        try{
            K = Double.parseDouble(menuEx1.getTxtK().getText());
        }catch(Exception e){
            menuEx1.getTxtK().setText("Deu ruim");
        }
        try{
            x =  Double.parseDouble(menuEx1.getTxtX().getText());
        }
        catch(Exception e){
            menuEx1.getTxtX().setText("Deu ruim");
        }
        double largura = 2 / (Math.pow(A , 2));
        String linha1 = String.format("A largura do poco eh %.3e\n" , largura);
        
        double nivel = (K * largura) / Math.PI;
        String linha2 = String.format("O nivel do estado do eletron eh %.0f\n" , nivel);

        
        double estado = Math.sqrt(2 / largura) * Math.sin( ((nivel * Math.PI) / largura) * x * largura);
        String linha3 = String.format("A probabilidade de encontra-lo na posicao x eh %.3e\n" , Math.pow(estado , 2));
        menuEx1.getTxtResposta1().setText(linha1+"\n"+
                linha2+"\n"+
                linha3+"\n");
    }

    public double integrand1(double x) {        
        return Math.pow(Math.abs((Math.sqrt(2 / L) * Math.sin(((N1 * Math.PI)/L) * (x)))),2);
    }
    public double integrand2(double x) {
        
        return Math.pow(Math.abs(Math.sqrt(2 / L) * Math.sin((N2 * Math.PI / L) * x)), 2);
    }
    // Método do trapézio para realizar a integração numérica
    public double integrate(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (integrand1(a) + integrand1(b));

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += integrand1(x);
        }

        return h * sum;
    }
    public double integrate2(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (integrand2(a) +integrand2(b));

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += integrand2(x);
        }

        return h * sum;
    }
}
