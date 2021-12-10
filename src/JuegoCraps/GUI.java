package JuegoCraps;

import com.sun.net.httpserver.Headers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view Craps Class
 * @autor Diego alvarez
 * @version V .1.0.0 date 9.12.2021
 */
public class GUI extends JFrame {
    private  static final String MENSAJE_INICIO = "Bienvenido a Craps \n"
             +"Oprime el boton lanzar para iniciar el juego"
             +"\n si tu tiro de salida es 7 u 11 ganas con natural"
             +"\n si tu tiro de salida es 2 , 3 u 12 pierdes con Craps"
             +"\n si sacas cualquier otro valor estableceras el punto"
             +"\n Estando en punto podras seguir lanzando los dados"
             +"\n pero ahora ganaras si sacas nuevamente el valor del punto"
             +"\n sin que previamente hayas sacado 7";
    private Header headerProject;
    private JLabel dado1,dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private  JTextArea mensajesSalida,resultadosDados;
    private JSeparator separator;
    private Escucha escucha;
    private ModelCraps modelCraps;


    public GUI(){
        initGUI();

        //Default JFrame configuration

        this.setTitle("juego Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    private void initGUI() {

        //create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //set up JComponents
        headerProject=new Header("Mesa juego Craps ...  ", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1=new JLabel(imageDado);
        dado2= new JLabel(imageDado);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        panelDados=new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        mensajesSalida = new JTextArea(7,31);
        mensajesSalida.setText(MENSAJE_INICIO);
       // mensajesSalida.setBorder(BorderFactory.createTitledBorder("Que debes  hacer  "));
        JScrollPane scroll=new JScrollPane(mensajesSalida);

        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes  hacer  "));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));



        this.add(panelResultados,BorderLayout.EAST);
        resultadosDados = new JTextArea(4,31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320,7));
        separator.setBackground(Color.BLUE);


    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console
     */
    public static void main (String[] args){
        EventQueue.invokeLater( () -> {
            GUI miProjectGUI=new GUI();

        } );
    }
/**
 * inner class that extends an adapter Class or implements Listeners used by GUI class

 */
private class Escucha implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        modelCraps.calcularTiro();
        int[] caras= modelCraps.getCaras();
        imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
        dado1.setIcon(imageDado);
        imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
        dado2.setIcon(imageDado);
        modelCraps.determinarJuego();

        panelResultados.removeAll();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados  "));
        panelResultados.add(resultadosDados);
        panelResultados.add(separator);
        panelResultados.add(mensajesSalida);
        resultadosDados.setText(modelCraps.getEstadoToString()[0]);
        mensajesSalida.setRows(4);
        mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
        revalidate();
        repaint();

    }

  }











}
