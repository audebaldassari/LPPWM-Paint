


import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;

class Dessin extends java.applet.Applet implements MouseListener , MouseMotionListener
{
  private ButtonPanel Boutons;			//zone qui contient les boutons
  private Panel Affichage;			//zone d'affichage
  public int temoin=0;
  public int temMain=0;
  public int temCarre=0;
  public int temCercle=0;
  public int X1;
  public int Y1;
  public int X2;
  public int Y2;
  public int X3;
  public int Y3;
  public int trace;
  public Color varcoul;

	public void init()
		{
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
                setLayout(new BorderLayout());
		Boutons=new ButtonPanel();
		add(BorderLayout.NORTH, Boutons);
		temMain=0;
	        temCarre=0;
	        temCercle=0;
		Affichage=new Panel();
		Affichage.setBackground(Color.white);
		Affichage.setForeground(Color.black);
		add(Affichage);
		}
	public void start()
		{
			if (temoin != 0)
				repaint();
			temoin=1;
		}
	public void stop()
		{
			repaint();
		}
	public void destroy()
		{
			repaint();
		}
	public void paint(Graphics g)
		{
			g.drawRect(0,0,this.getSize().width -1, this.getSize().height -1);
		}

	public void mousePressed(MouseEvent evt)
		{
		 X1=evt.getX();		//on recupere les coordonnées de la souris
		 Y1=evt.getY();
		}

	public void mouseDragged(MouseEvent evt)
		{
		 X3=evt.getX();
		 Y3=evt.getY();
		 if (temMain==1)
		 {
		   Graphics g = getGraphics ();
		   g.setColor(varcoul);
		   g.drawLine(X1,Y1,X3,Y3);
		   X1=X3;
		   Y1=Y3;
		 }
		}

	public void mouseReleased(MouseEvent evt)
		{
		X2=evt.getX();
		Y2=evt.getY();
		if (temCarre==1)
			{
			Graphics g = getGraphics ();
      		        g.setColor(varcoul);
			g.drawRect(X1,Y1,X2-X1,Y2-Y1);
			}
		if (temCercle==1)
			{
			Graphics g = getGraphics ();
			g.setColor(varcoul);
			 g.drawOval(X1,Y1,X2-X1,Y2-Y1);
			}
		}

	//quand on utilise mouselistener on doit implémenter toutes les fonctions
	public void mouseEntered(MouseEvent evt){}
	public void mouseExited(MouseEvent evt){}
	public void mouseClicked(MouseEvent evt){}
	public void mouseMoved(MouseEvent evt){}

class ButtonPanel extends Panel implements ActionListener
{
	public Button Main= new Button ("Main Levée"); //l'objet creer joue le role d'ecouteur
	public Button Carre= new Button ("Carré");
	public Button Cercle= new Button ("Cercle");
	public Choice Couleur=new Choice();

	public ButtonPanel()
	{
		Main.addActionListener(this);
		Carre.addActionListener(this);
		Cercle.addActionListener(this);

		//Ajout des couleurs dans la liste
		Couleur.addItem("Noir");
		Couleur.addItem("Bleu");
		Couleur.addItem("Rouge");
		Couleur.addItem("Jaune");

		//Création du panel, on y met les objets
		Panel unPanel= new Panel();
		unPanel.setLayout(new GridLayout(1,4));
		unPanel.add(Main);
		unPanel.add(Carre);
		unPanel.add(Cercle);
		unPanel.add(Couleur);
		add (unPanel);
	}

	public void actionPerformed (ActionEvent e)  //ActionPerformed est une methode de ActionListener
		{
		  Object Source = e.getSource();
		  int coul;
		  coul=this.Couleur.getSelectedIndex();

		  if (Source == Main)
			{
			 temMain=1;
			 temCercle=0;
			 temCarre=0;
			}
		  if (Source == Carre)
			{
			 temCarre=1;
			 temCercle=0;
			 temMain=0;
			}

		  if (Source == Cercle)
			{
			 temCercle=1;
			 temMain=0;
			 temCarre=0;
			}
		  if (coul==1)
		  {
		   varcoul=Color.blue;
		  }
		  if (coul==2)
		  {
		    varcoul=Color.red;
		 }
 		  if (coul==0)
		  {
		    varcoul=Color.black;
		  }
		  if (coul==3)
		  {
		    varcoul=Color.yellow;
		 }

	 }
  }
}