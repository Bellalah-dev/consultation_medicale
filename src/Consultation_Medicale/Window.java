package Consultation_Medicale;
import org.jdom2.JDOMException;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketOption;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;


class window extends JFrame {

    private static final long serialVersionUID = 1L;
    JLabel background;
    JLabel vide=new JLabel();

    JLabel ncin=new JLabel("  numéro cin");
    JTextField ncinfield =new JTextField();

    JLabel nom=new JLabel("  Votre Nom");
    JTextField nomfield =new JTextField();

    JLabel prenom=new JLabel("  Votre Prenom");
    JTextField prenomfield =new JTextField();

    JRadioButton Homme = new JRadioButton("Homme");
    JRadioButton Femme =new JRadioButton("Femme");
    ButtonGroup btngroup = new ButtonGroup();

    JTextField Adressefield =new JTextField();

    JTextField emailfield =new JTextField();


    private window() {
        super("consultation medicale");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        ImageIcon img=new ImageIcon("con-med.jpg");
        background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,400);
        this.add(background);


        //apply two j pannels
        JPanel contentpane=new JPanel();
        contentpane.setLayout(new FlowLayout());
        JPanel pan1 = new JPanel();
        pan1.setLayout(new GridLayout(0,2));



        pan1.add(nom);
        pan1.add(nomfield);

        pan1.add(prenom);
        pan1.add(prenomfield);

        pan1.add(ncin);
        pan1.add(ncinfield);

        JLabel Sexe =new JLabel("  sexe:");
        pan1.add(Sexe);

        btngroup.add(Homme);
        btngroup.add(Femme);
        pan1.add(Homme);
        pan1.add(vide);
        pan1.add(Femme);

        JLabel Adresse=new JLabel("  Adresse");
        pan1.add(Adresse);
        pan1.add(Adressefield);

        JLabel email=new JLabel("  e_mail");
        pan1.add(email);
        pan1.add(emailfield);




///////////////////options ajout button////////////////
        JButton btnAjouter = new JButton("Ajouter");
        //btnAjouter.setPreferredSize(new Dimension(160,40));
        btnAjouter.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                try {
                    bntAjouterlistener(e);
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (JDOMException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        contentpane.add(btnAjouter);

/////////options supp button///////////////////

        JButton btnExit = new JButton("EXIT");
        //btnExit.setPreferredSize(new Dimension(160,40));
        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnExitlistener(e);


            }});

        contentpane.add(btnExit);
        //////////////////options annul button/////////////////////
        JButton btnannuler=new JButton("Clear");
        //btnannuler.setPreferredSize(new Dimension(160,40));
        btnannuler.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnannulerlistener(e);



            }
        });
        contentpane.add(btnannuler);

        ////////////////: boutton supprimer options///////////////////////////////////

        JButton btnsupprimer=new JButton("supprimer");
        //btnsupprimer.setPreferredSize(new Dimension(160,40));
        btnsupprimer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    btnsupprimerlistener(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JDOMException ex) {
                    ex.printStackTrace();
                }


            }
        });
        contentpane.add(btnsupprimer);

        /////////////////////// options bouton rechercher/////////////////////////
        JButton btnrech=new JButton("Rechercher");
        btnrech.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    btnrechlistener(event);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JDOMException e) {
                    e.printStackTrace();
                }

            }
        });
        contentpane.add(btnrech);
        ///////////////////////////options bouton modifier////////////////////////////
        JButton btnmodif=new JButton("Modifier");
        btnmodif.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                btnmodiflistener(event);

            }
        });
        contentpane.add(btnmodif);


        this.getContentPane().add(pan1, BorderLayout.NORTH);
        this.getContentPane().add(contentpane, BorderLayout.SOUTH);

    }




    ///////////////////////////////*******listeners*******////////////////////////////////////
    private void btnmodiflistener(ActionEvent event){
        String n=nomfield.getText();
        String p=prenomfield.getText();
        String cin=ncinfield.getText();
        String a=Adressefield.getText();
        String e=emailfield.getText();
        if( n.equals("") || p.equals("") || cin.equals("") || e.equals("") || a.equals("") || Homme.isSelected()==false && Femme.isSelected()==false){
            JOptionPane jop =new JOptionPane();

            jop.showMessageDialog(null,"incomplete information !","Message",JOptionPane.WARNING_MESSAGE);}

        else{

            if (Homme.isSelected()){
                String s="homme";

                try {
                    Test.Modifier(n,p,cin,e,s,a);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JDOMException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Data updated successfully");


            }
            if (Femme.isSelected())
            {
                String s="femme";
                try {
                    Test.Modifier(n,p,cin,e,s,a);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JDOMException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Data updated successfully");

            }}


    }

    private void btnrechlistener(ActionEvent event) throws IOException, JDOMException {
        String cin=ncinfield.getText();
        Test.Rechercher(cin);


    }

    private void btnsupprimerlistener(ActionEvent e) throws IOException, JDOMException {
        String cin=ncinfield.getText();
        Test.Supprimer(cin);


    }

    private void btnannulerlistener(ActionEvent event){
        //int yesorno =JOptionPane.showConfirmDialog(null,"you sure you want to clear window ?","clear window",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
            nomfield.setText("");
            prenomfield.setText("");
            Adressefield.setText("");
            emailfield.setText("");
            ncinfield.setText("");
            Homme.setSelected(false);
            Femme.setSelected(false);
            btngroup.clearSelection();


    }

      private void bntAjouterlistener(ActionEvent event) throws ParserConfigurationException, JDOMException, IOException {
        String n=nomfield.getText();
        String p=prenomfield.getText();
        String cin=ncinfield.getText();
        String a=Adressefield.getText();
        String e=emailfield.getText();

        if( n.equals("") || p.equals("") || cin.equals("") || e.equals("") || a.equals("") || Femme.isSelected()==false && Homme.isSelected()==false){
              JOptionPane jop =new JOptionPane();

              jop.showMessageDialog(null,"incomplete information !","Message",JOptionPane.WARNING_MESSAGE);}

              else{

            if (Homme.isSelected()){
            String s="homme";

            Test.Ajouter(n,p,cin,e,s,a);
            System.out.println("Data added successfully");


            }
            if (Femme.isSelected())
            {
                String s="femme";
                Test.Ajouter(n,p,cin,e,s,a);
                System.out.println("Data added successfully");

                }}

        }










    private void btnExitlistener(ActionEvent event) {
        System.exit(0);
    }


/////////////////////////////main/////////////////////////////////

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        //apply a look'n feel
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        //creation et set visible de l'interface graphique
        window window=new window();
        window.setVisible(true);

    }}