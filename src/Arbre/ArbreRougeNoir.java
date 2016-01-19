package Arbre;

import java.awt.Color;

public class ArbreRougeNoir {

    static Noeud sentinelle;

    static {
    	ArbreRougeNoir.sentinelle = new Noeud(null, Color.BLACK, null, null, null);
    }
    Noeud racine, noeudAjoute;

    public Noeud getRacine() {
        return racine;
    }

    public ArbreRougeNoir() {
        racine = ArbreRougeNoir.sentinelle;
    }

    public void ajout(Data o) {
        racine = ajout(o, racine, null);
        reOrganiser(noeudAjoute);
    }

    public Noeud rechercher(int val) {
        return rechercher(getRacine(), new Data(val));
    }

    private Noeud rechercher(Noeud n, Data o) {
        if (n.isSentinelle()) {
            return null;
        }
        if (o.getValue() < n.getInfo().getValue()) {
            return rechercher(n.getGauche(), o);
        } else if (o.getValue() == n.getInfo().getValue()) {
            return n;
        } else {
            return rechercher(n.getDroit(), o);
        }
    }

    private Noeud ajout(Data o, Noeud r, Noeud p) {
        // p est le parent de r 
        if (r.isSentinelle()) {
            r = noeudAjoute = new Noeud(o, Color.RED, r, r, p);
        } else if (o.compareTo(r.info) < 0) {
            r.gauche = ajout(o, r.gauche, r);
        } else {
            r.droit = ajout(o, r.droit, r);
        }
        return r;
    }

    private void rotationGauche(Noeud n) {
        Noeud y = n.droit;
        n.droit = y.gauche;
        if (!y.gauche.isSentinelle()) {
            y.gauche.parent = n;
        }
        y.parent = n.parent;
        if (n.parent == null) {
            racine = y;
        } else if (n.parent.gauche == n) {
            n.parent.gauche = y;
        } else {
            n.parent.droit = y;
        }
        y.gauche = n;
        n.parent = y;
    }

    private void rotationDroite(Noeud n) {
        Noeud y = n.gauche;
        n.gauche = y.droit;
        if (!y.droit.isSentinelle()) {
            y.droit.parent = n;
        }
        y.parent = n.parent;
        if (n.parent == null) {
            racine = y;
        } else if (n.parent.droit == n) {
            n.parent.droit = y;
        } else {
            n.parent.gauche = y;
        }
        y.droit = n;
        n.parent = y;
    }

    private void reOrganiser(Noeud n) {
        while (n != racine && n.parent.couleur == Color.RED) {
            if (n.parent == n.parent.parent.gauche) {
                Noeud y = n.parent.parent.droit;
                if (y.couleur == Color.RED) {
                    n.parent.couleur = Color.BLACK;
                    y.couleur = Color.BLACK;
                    n.parent.parent.couleur = Color.RED;
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.droit) {
                        n = n.parent;
                        rotationGauche(n);
                    }
                    n.parent.couleur = Color.BLACK;
                    n.parent.parent.couleur = Color.RED;
                    rotationDroite(n.parent.parent);
                }
            } else {
                Noeud y = n.parent.parent.gauche;
                if (y.couleur == Color.RED) {
                    n.parent.couleur = Color.BLACK;
                    y.couleur = Color.BLACK;
                    n.parent.parent.couleur = Color.RED;
                    n = n.parent.parent;
                } else {
                    if (noeudAjoute == n.parent.gauche) {
                        n = n.parent;
                        rotationDroite(n);
                    }
                    n.parent.couleur = Color.BLACK;
                    n.parent.parent.couleur = Color.RED;
                    rotationGauche(n.parent.parent);
                }
            }
        }
        racine.couleur = Color.BLACK; // la racine est toujours noire 
    }

    //Suppression -------------------------------------------------------

    public void supprimer(Data o) {
        supprimer(racine, o);
    }

    private Noeud supprimer(Noeud r, Data o) {
        if (r.isSentinelle()) {
            return r; // Pas trouvé
        }
        if (o.compareTo(r.info) == 0) {
            r = supprimer(r);
        } else if (o.compareTo(r.info) < 0) {
            supprimer(r.gauche, o);
        } else {
            supprimer(r.droit, o);
        }
        return r;
    }
    private Noeud supprimer(Noeud z) {
        Noeud y, x;
        if (z.gauche.isSentinelle() || z.droit.isSentinelle()) {
            y = z;
        } else {
            y = arbreSuccesseur(z);
        }
        if (!y.gauche.isSentinelle()) {
            x = y.gauche;
        } else {
            x = y.droit;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            racine = x;
        } else if (y == y.parent.gauche) {
            y.parent.gauche = x;
        } else {
            y.parent.droit = x;
        }
        if (y != z) {
            z.info = y.info;
        }
   // si le noeud supprimé est un noeud rouge, il n’y a rien à
        // faire, l’arbre conserve ses propriétés
        // en revanche si le nœud supprimé est noir, 
        // il faut reorganiser l’arbre
        if (y.couleur == Color.black) {
            reOrganiserSuppression(x);
        }
        return y;
    }

    private Noeud arbreSuccesseur(Noeud x) {
   // le noeud successseur de x dans l'arbre,
        // sentinelle si c'est le plus grand
        if (!x.droit.isSentinelle()) {
            return arbreMinimum(x.droit);
        }
        Noeud y = x.parent;
        while (!y.isSentinelle() && x == y.droit) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    private Noeud arbreMinimum(Noeud x) {
        while (!x.gauche.isSentinelle()) {
            x = x.gauche;
        }
        return x;
    }

    private void reOrganiserSuppression(Noeud n) {
        // re organisation de l'arbre, en remontant vers la racine
        while (n != racine && n.couleur == Color.black) {
            if (n == n.parent.gauche) {
                Noeud y = n.parent.droit;
                if (y.couleur == Color.red) {
                    y.couleur = Color.black;
                    n.parent.couleur = Color.red;
                    rotationGauche(n.parent);
                    y = n.parent.droit;
                }
                if (y.gauche.couleur == Color.black && y.droit.couleur == Color.black) {
                    y.couleur = Color.red;
                    n = n.parent;
                } else {
                    if (y.droit.couleur == Color.black) {
                        y.gauche.couleur = Color.black;
                        y.couleur = Color.red;
                        rotationDroite(y);
                        y = n.parent.droit;
                    }
                    y.couleur = n.parent.couleur;
                    n.parent.couleur = Color.black;
                    y.droit.couleur = Color.black;
                    rotationGauche(n.parent);
                    break;
                }
            } else {
                Noeud y = n.parent.gauche;
                if (y.couleur == Color.red) {
                    y.couleur = Color.black;
                    n.parent.couleur = Color.red;
                    rotationDroite(n.parent);
                    y = n.parent.gauche;
                }
                if (y.droit.couleur == Color.black && y.gauche.couleur == Color.black) {
                    y.couleur = Color.red;
                    n = n.parent;
                } else {
                    if (y.gauche.couleur == Color.black) {
                        y.droit.couleur = Color.black;
                        y.couleur = Color.red;
                        rotationGauche(y);
                        y = n.parent.gauche;
                    }
                    y.couleur = n.parent.couleur;
                    n.parent.couleur = Color.black;
                    y.gauche.couleur = Color.black;
                    rotationDroite(n.parent);
                    break;
                }
            }
        }
        n.couleur = Color.black;
    }
}
