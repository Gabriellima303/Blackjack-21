import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    private ArrayList<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();

        String[] naipes = {"Copas", "Ouros", "Espadas", "Paus"};
        String[] valores = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                cartas.add(new Carta(naipe, valor));
            }
        }
    }


    public void embaralhar(){
        Collections.shuffle(cartas);
    }
    //O metodo comprarCarta serve para comprar a carta e tirar ela do baralho
    public Carta comprarCarta(){
        if(!cartas.isEmpty()){
            return cartas.remove(0);

        }
        return null;
    }
    //Retorna quantas cartas ainda tem no baralho
    public int tamanho(){
        return cartas.size();
    }
}
