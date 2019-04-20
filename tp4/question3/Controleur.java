package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addFocusListener(new FocusListener()
                {public void focusGained(FocusEvent a) {
                    donnee.setText("");
                }
                public void focusLost(FocusEvent a) {
                   
                }
            });
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  
        push.addActionListener(
            new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                    try{
                        pile.empiler(operande());
                    actualiserInterface();
                    pile.notifyObservers();

                }
                        catch (NumberFormatException ne){}
                        catch(PilePleineException pp){}
                }});
                    
        boutons.add(add);   
        add.addActionListener( new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                    try{
                        pile.empiler(pile.depiler()+pile.depiler());
                    actualiserInterface();}
                        catch (NumberFormatException ne){}
                        catch(PilePleineException pp){}
                        catch(PileVideException pv){}

                }});
        
        boutons.add(sub);  
        sub.addActionListener( new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                    try{
                        int p1=pile.depiler();
                        int p2=pile.depiler();
                        int p3=p1-p2;
                        pile.empiler(p3);
                    actualiserInterface();}
                        catch (NumberFormatException ne){}
                        catch(PilePleineException pp){}
                        catch(PileVideException pv){}

                }});
        
        boutons.add(mul);  
        mul.addActionListener( new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                    try{
                        pile.empiler(pile.depiler()*pile.depiler());
                    actualiserInterface();}
                        catch (NumberFormatException ne){}
                        catch(PilePleineException pp){}
                        catch(PileVideException pv){}

                }});
        
        boutons.add(div);  
        div.addActionListener( new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                   
                    try{
                        int d1=pile.depiler();
                        int d2=pile.depiler();
                        if(d1==0){pile.empiler(d2);pile.empiler(d1);}
                        else {pile.empiler(d2/d1);}
                        actualiserInterface();}
                        catch (NumberFormatException ne){}
                        catch(PilePleineException pp){}
                        catch(PileVideException pv){}

                }});
                
                
                
        boutons.add(clear);
        clear.addActionListener(new ActionListener()
                {public void actionPerformed(ActionEvent a) {
                     while(!pile.estVide()){
                    try{
                        pile.depiler();
                    } 
                    
            catch(PileVideException e){}

                }
                actualiserInterface();                       
               pile.notifyObservers();                      
            }});
        
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
       if(pile.estPleine()){donnee.setEnabled(false);push.setEnabled(false);}
       else{donnee.setEnabled(true);push.setEnabled(true);}
       if(pile.taille()>=2){sub.setEnabled(true);add.setEnabled(true);mul.setEnabled(true);div.setEnabled(true);}
       else
       {sub.setEnabled(false);add.setEnabled(false);mul.setEnabled(false);div.setEnabled(false);}
       if(pile.taille()==0){clear.setEnabled(false);}
       else{clear.setEnabled(true);}
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    
    
    
    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)

}
