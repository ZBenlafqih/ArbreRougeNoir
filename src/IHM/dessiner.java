package IHM;

import Arbre.ArbreRougeNoir;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Arbre.Data;

import java.awt.Color;

@SuppressWarnings("serial")
public class dessiner extends JFrame {
	 private javax.swing.JButton btn_ajouer;
	    private javax.swing.JButton btn_supp;
	    private javax.swing.JButton btn_valider;
	    private javax.swing.JScrollPane container;
	    private javax.swing.JPanel footer;
	    private javax.swing.JPanel header;
	    private javax.swing.JLabel lbl_text;
	    private javax.swing.JTextField txtVal;
	    private PageDessin centre;
	    private ArbreRougeNoir a;
	    private int addORsupp = 1;
    
    public dessiner() throws IOException {
        a = new ArbreRougeNoir();
        initComponents();
        centre = new PageDessin(a, container.getWidth(), container.getHeight());
        btn_valider.requestFocus();
    }
    public void afficher() {
    	txtVal.requestFocus();
        container.setViewportView(centre);
    }


    private void initComponents() throws IOException {
    	
        header = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        btn_ajouer = new javax.swing.JButton();
        btn_ajouer.setBounds(10, 10, 100, 22);
        btn_supp = new javax.swing.JButton();
        btn_supp.setBounds(10, 37, 100, 22);
        container = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arbres Rouge Noir");
        setBounds(new java.awt.Rectangle(100, 70, 0, 0));
        setResizable(false);
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        BufferedImage img_centre_gauche = ImageIO.read(new File("img/header.png"));
		header = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img_centre_gauche, 0, 0, null);
			}

		};

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 92));

        footer.setBackground(new java.awt.Color(204, 204, 255));
        footer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        footer.setLayout(null);

        btn_ajouer.setBackground(new java.awt.Color(255, 255, 255));
        btn_ajouer.setForeground(Color.BLACK);
        btn_ajouer.setText("Ajouter");
        btn_ajouer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ajouerMouseEntered(evt);
            }
        });
        btn_ajouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajouerActionPerformed(evt);
            }
        });
        footer.add(btn_ajouer);

        btn_supp.setBackground(new java.awt.Color(255, 255, 255));
        btn_supp.setForeground(Color.BLACK);
        btn_supp.setText("Supprimer");
        btn_supp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suppActionPerformed(evt);
            }
        });
        footer.add(btn_supp);

        getContentPane().add(footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 800, 70));
        lbl_text = new JLabel();
        lbl_text.setBounds(220, 10, 164, 50);
        footer.add(lbl_text);
        
                lbl_text.setText("Entrer la valeur à ajouter :");
                txtVal = new JTextField();
                txtVal.setBounds(394, 24, 55, 23);
                footer.add(txtVal);
                
                       txtVal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                       btn_valider = new javax.swing.JButton();
                       btn_valider.setBounds(459, 24, 96, 23);
                       footer.add(btn_valider);
                       
                               btn_valider.setBackground(new java.awt.Color(255, 255, 255));
                               btn_valider.setText("Valider");
                               btn_valider.addActionListener(new java.awt.event.ActionListener() {
                                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                                       btn_validerActionPerformed(evt);
                                   }
                               });
                               btn_valider.addKeyListener(new java.awt.event.KeyAdapter() {
                                   public void keyReleased(java.awt.event.KeyEvent evt) {
                                       btn_validerKeyReleased(evt);
                                   }
                               });
                       txtVal.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                               txtValActionPerformed(evt);
                           }
                       });
                       txtVal.addKeyListener(new java.awt.event.KeyAdapter() {
                           public void keyReleased(java.awt.event.KeyEvent evt) {
                               txtValKeyReleased(evt);
                           }
                       });

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 92, 800, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValActionPerformed

    private void btn_ajouerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajouerMouseEntered

    }//GEN-LAST:event_btn_ajouerMouseEntered

    private void btn_ajouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajouerActionPerformed
        addORsupp = 1;
        lbl_text.setText("Entrer la valeur à ajouter :");
        
        txtVal.requestFocus();
    }//GEN-LAST:event_btn_ajouerActionPerformed

    private void btn_suppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suppActionPerformed
        addORsupp = 2;
        lbl_text.setText("Entrer la valeur à supprimer :");
        
        txtVal.requestFocus();
    }//GEN-LAST:event_btn_suppActionPerformed

    private void btn_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validerActionPerformed
        switch (addORsupp) {
            case 1:
                try {
                    if (txtVal.getText().trim().length() != 0) {
                        a.ajout(new Data(Integer.parseInt(txtVal.getText().trim())));
                       centre.paint(container.getGraphics());
                        txtVal.setText("");
                        txtVal.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(this, "Merci de saisir la valeur à jouter ");
                        txtVal.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    if (txtVal.getText().trim().length() != 0) {
                        if (!centre.rechercher(container.getGraphics(), Integer.parseInt(txtVal.getText().trim()))) {
                            JOptionPane.showMessageDialog(this, "Cette valeur n'existe pas");
                        }
                        a.supprimer(new Data(Integer.parseInt(txtVal.getText().trim())));
                       centre.paint(container.getGraphics());
                        txtVal.setText("");
                        txtVal.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(this, "Merci d'entrer une valeur a supprimer ");
                        txtVal.requestFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void btn_validerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_validerKeyReleased

    }
    
    private void txtValKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_validerActionPerformed(null);
        }
        if(txtVal.getText().trim().length()>3){
            txtVal.setText(txtVal.getText().substring(0, 3));
        }
    }//GEN-LAST:event_txtValKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                try {
					dessiner darn = new dessiner();
					darn.setVisible(true);
	                darn.afficher();
	                
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
    }

   
   
}
