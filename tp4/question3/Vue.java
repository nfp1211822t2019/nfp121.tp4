package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Vue extends JPanel implements Observer{// à compléter

    private JLabel etatPile;
    private PileModele<Integer> pile;

    public Vue(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.etatPile = new JLabel("entrez des nombres entiers");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(etatPile);
        setBackground(Color.green);
        pile.addObserver(this);
        // inscription auprès du modèle comme observateur
    }

    public void update(Observable obs, Object arg) {
        if(pile.estVide()){this.etatPile.setText("entrez des nombres entiers");}

        etatPile.setText(pile.toString()); // ou obs.toString()
    }

}
