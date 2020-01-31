package db;

import FactoryMethod.Veicolo;
import GenericObject.Collo;

import java.util.List;

public interface ColloDAO {

    String queryMittente(String code);

    void insertSqlCollo(Collo c);

    //void insertSqlCustodisci(String code, String filialeP);

    List<Collo> queryColloDataNull(Veicolo v);

    void updateDataPartenza(String code, String data );

    String queryData(String code, String filialeP);
}
