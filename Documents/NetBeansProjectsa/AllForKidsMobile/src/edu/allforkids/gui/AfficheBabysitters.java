/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.allforkids.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import edu.allforkids.entities.User;
import edu.allforkids.service.ListeBabysitterCrud;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AfficheBabysitters {
   
    Form f;
    SpanLabel lb;
  
    public AfficheBabysitters() throws IOException {
        
        f = new Form("Liste des Babysitter");
        lb = new SpanLabel("");
        f.add(lb);
        
        ListeBabysitterCrud bb=new ListeBabysitterCrud();
        ArrayList<User> lis=bb.getList2();
        for (User l : lis){
            Container c1= new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nom= new Label(l.getUsername());
            Label age=new Label(""+l.getAge());
            Label Etat=new Label(l.getEtat());
            Label Ville = new Label(l.getVille());
            Label adresse = new Label(l.getAdresse());
            Label nbr = new Label(""+l.getNbrAnneeExp());
            Label num = new Label(""+l.getNumTe());
            Label sexe = new Label(l.getSexe());
            Label email = new Label(l.getEmail());
            ImageViewer img=new ImageViewer();
            Image placeholder = Image.createImage(200,200,0xbfc92d);
            EncodedImage encImage=EncodedImage.createFromImage(placeholder, false);
            Image imgs= URLImage.createToStorage(encImage, l.getNom_image(),"http://localhost/PI4/PI4/web/images/"+l.getNom_image(),URLImage.RESIZE_SCALE);
            img.setImage(imgs);
            c1.add(img);
            c1.add(nom);
            c1.add(age);
            c1.add(Etat);
            c1.add(Ville);
            c1.add(adresse);
            c1.add(nbr);
            c1.add(num);
            c1.add(sexe);
            c1.add(email);
            Button mail = new Button("Envoyer mail");
            Button avis = new Button("Avis");
            c1.add(mail);
            c1.add(avis);
            
            avis.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form h2 = new Form(l.getUsername(),new BoxLayout(BoxLayout.Y_AXIS));
                   Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   ImageViewer img2;
                   Label nom2= new Label(l.getUsername());
                   Label age2=new Label(""+l.getAge());
                   Label Etat2=new Label(l.getEtat());
                   Label Ville2 = new Label(l.getVille());
                   Label adresse2 = new Label(l.getAdresse());
                   Label nbr2 = new Label(""+l.getNbrAnneeExp());
                   Label num2 = new Label(""+l.getNumTe());
                   Label sexe2 = new Label(l.getSexe());
                   Label email2 = new Label(l.getEmail());
                   img2= new ImageViewer();
                   Image placeholder2 = Image.createImage(200,200,0xbfc92d);
                   EncodedImage encImage2=EncodedImage.createFromImage(placeholder2, false);
                   Image imgss= URLImage.createToStorage(encImage2, l.getNom_image(),"http://localhost/PI4/PI4/web/images/"+l.getNom_image(),URLImage.RESIZE_SCALE);
                   img2.setImage(imgss);
                    TextArea monavis=new TextArea(5, 10);
                    monavis.getStyle().setFgColor(0xff0000);
                    monavis.getStyle().setBgColor(0xf5deb3);
                    Button ajouterav =new Button("Ajouter");
                   c3.add(img2);
                   c3.add(nom2);
                   c3.add(age2);
                   c3.add(Etat2);
                   c3.add(Ville2);
                   c3.add(adresse2);
                   c3.add(nbr2);
                   c3.add(num2);
                   c3.add(sexe2);
                   c3.add(email2);
                   c2.add(monavis);
                   c2.add(ajouterav);
                   c3.add(c2);
                   h2.add(c3);
                   h2.show();
                   ajouterav.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                        }
                    });
                }
            });
            
            
            
            f.add(c1);
            
            
                   
            
        }
        
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


   
    
}
