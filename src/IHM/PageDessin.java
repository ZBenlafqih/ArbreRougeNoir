package IHM;

import Arbre.ArbreRougeNoir;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import Arbre.Noeud;

@SuppressWarnings("serial")
public class PageDessin extends JPanel {
	ArbreRougeNoir a;
    int containerWidth, containerHeight;
    public PageDessin(ArbreRougeNoir a, int cw, int ch) {
        this.a = a;
        containerWidth = cw;
        containerHeight = ch;
    }

    public void dessinerNoeud(Graphics g, Noeud n) {
        if (!n.getGauche().isSentinelle()) {
            int nfg = n.getGauche().getNGauche();
            g.setColor(Color.BLACK);
            g.drawLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() - 30 * nfg + 17, n.getDessinY() + 50 + 17);
            n.getGauche().setDessinX(n.getDessinX() - 30 * nfg);
            n.getGauche().setDessinY(n.getDessinY() + 50);
        }
        if (!n.getDroit().isSentinelle()) {
            int nfd = n.getDroit().getNDroit();
            g.setColor(Color.BLACK);
            g.drawLine(n.getDessinX() + 17, n.getDessinY() + 17, n.getDessinX() + 30 * nfd + 17, n.getDessinY() + 50 + 17);
            n.getDroit().setDessinX(n.getDessinX() + 30 * nfd);
            n.getDroit().setDessinY(n.getDessinY() + 50);
        }
        g.setColor(n.getCouleur());
        g.fillOval(n.getDessinX(), n.getDessinY(), 34, 34);
        g.setColor(Color.WHITE);
        g.drawString(n.getInfo().getValue() + "", 
                n.getInfo().getValue() < 10 ? n.getDessinX() + 14 : 
                        n.getInfo().getValue() < 100 ? n.getDessinX() + 10 : n.getDessinX() + 6, n.getDessinY() + 21);
        if (!n.getGauche().isSentinelle()) {
            dessinerNoeud(g, n.getGauche());
        }
        if (!n.getDroit().isSentinelle()) {
            dessinerNoeud(g, n.getDroit());
        }
    }

    public void paint(Graphics g) {
        Noeud n = a.getRacine();
        n.setDessinX(390);
        n.setDessinY(10);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, containerWidth, containerHeight);
        if (!a.getRacine().isSentinelle())
            dessinerNoeud(g,n);
    }

    public boolean rechercher(Graphics g, int val) {
        Noeud n = a.rechercher(val);
        if (n != null) {
            g.setColor(Color.BLUE);
            g.drawOval(n.getDessinX() - 5, n.getDessinY() - 5, 44, 44);
            return true;
        } else {
            return false;
        }
    }
}
