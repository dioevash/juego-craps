package JuegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayaout extends JFrame {
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
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private  JTextArea mensajesSalida,resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUIGridBagLayaout(){
        initGUI();


        //Default JFrame configuration


        this.setTitle("juego Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initGUI() {
        // set up JFrame Container`s Layaout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //set up JComponents
        headerProject=new Header("Mesa juego Craps ...  ", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);
        ayuda = new JButton("  ?  ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);
        salir=new JButton("salir");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);


        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1=new JLabel(imageDado);
        dado2= new JLabel(imageDado);

        panelDados=new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);

        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        add(panelDados,constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder(" Resultados "));
        resultadosDados.setText("Debes lanzar los dados ");
      //  resultadosDados.setBackground(new Color(255,255,255,0));
        resultadosDados.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(resultadosDados,constraints);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar,constraints);

        mensajesSalida = new JTextArea(4,31);
        mensajesSalida.setText("Usa el boton (?) para ver las reglas del juego ");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Mensajes "));
        mensajesSalida.setBackground(null);
        mensajesSalida.setEnabled(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajesSalida,constraints);







    }




    public static void main (String[] args){
        EventQueue.invokeLater( () -> {
            GUIGridBagLayaout miProjectGUI=new GUIGridBagLayaout();

        } );
    }

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==lanzar){
                modelCraps.calcularTiro();
                int[] caras= modelCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imageDado);
                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadoToString()[0]);
                mensajesSalida.setText(modelCraps.getEstadoToString()[1]);

            }else{
                if (e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }

        }

    }

}
















