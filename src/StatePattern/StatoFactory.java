package StatePattern;

class StatoFactory {

    static Stato costruisciStato(String stato){

        if( stato == null){
            return null;
        }

        if( stato.equalsIgnoreCase("Pronto")){
            return new StatoPronto();
        }
        else if( stato.equalsIgnoreCase("In Viaggio")){
            return new StatoInViaggio();
        }

        return null;
    }
}
