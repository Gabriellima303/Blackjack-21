public class Carta {

  private String naipe;
  private String valor;
  //Cria atributos para a classe Cartas

  public Carta(String naipe , String valor){
      this.naipe = naipe;
      this.valor = valor;
  }
  //Esse construtor vai atribuir valor a essas atributos


  public String getNaipe(){
      return naipe;
  }

  public String getValor(){
      return valor;
  }



  @Override
    public String toString(){
      return valor + " de " + naipe;
  }

  public int getValorNumerico(){
        switch (valor) {
            case "Ás":
                return 1;
            case "Valete":
            case "Dama":
            case "Rei":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }
}
