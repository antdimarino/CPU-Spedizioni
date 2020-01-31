package Facade;

import GenericObject.Collo;
import GenericObject.Grafo;
import Gestione.Smistamento;
import Util.Email;
import Visitor.VisitorCalcoloPercorso;
import db.ColloDAO;
import db.ColloDAOImp;


public class SpedizioneFacade {
    private Collo c;

    public SpedizioneFacade( Collo c ){
        this.c = c;
    }

    public void richiestaSpedizione(Grafo grafo){
        ColloDAO op = new ColloDAOImp();
        op.insertSqlCollo( this.c );

        grafo.accept( new VisitorCalcoloPercorso(), this.c );

        Smistamento.determinaVeicolo( c );

        String[] emails = { c.getEmailM(), c.getEmailD()};
        Email.sendEmail(emails, c.getCode());
    }

}
