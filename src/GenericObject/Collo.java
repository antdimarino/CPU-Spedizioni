package GenericObject;

import Util.RandomString;

public class Collo{
    private String code;
    private String nomeMitt;
    private String nomeDest;
    private String emailM;
    private String emailD;
    private String viaM;
    private String viaD;
    private double peso;
    private String filialeP;
    private String filialeD;
    private String data;
    private String dataPartenza;


    public Collo(String nomeMitt, String nomeDest, String emailM, String emailD,
                 String viaM, String viaD, double peso, String filialeP, String filialeD, String data)
    {
        this.nomeMitt = nomeMitt;
        this.nomeDest = nomeDest;
        this.emailM = emailM;
        this.emailD = emailD;
        this.viaM = viaM;
        this.viaD = viaD;
        this.peso = peso;
        this.filialeP = filialeP;
        this.filialeD = filialeD;
        this.data = data;
        this.dataPartenza = null;

        this.code = RandomString.getAlphaNumericString(16);
    }

    public Collo(String code, String nomeMitt, String nomeDest, String emailM, String emailD,
                 String viaM, String viaD, double peso, String filialeP, String filialeD, String data){
        this.nomeMitt = nomeMitt;
        this.nomeDest = nomeDest;
        this.emailM = emailM;
        this.emailD = emailD;
        this.viaM = viaM;
        this.viaD = viaD;
        this.peso = peso;
        this.filialeP = filialeP;
        this.filialeD = filialeD;
        this.data = data;
        this.dataPartenza = null;

        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getEmailD() {
        return emailD;
    }

    public String getEmailM() {
        return emailM;
    }

    public String getFilialeD() {
        return filialeD;
    }

    public String getFilialeP() {
        return filialeP;
    }

    public String getNomeDest() {
        return nomeDest;
    }

    public String getNomeMitt() {
        return nomeMitt;
    }

    public double getPeso() {
        return peso;
    }

    public String getViaD() {
        return viaD;
    }

    public String getViaM() {
        return viaM;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }
}
