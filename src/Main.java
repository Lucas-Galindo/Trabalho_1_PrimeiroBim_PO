
public void chamaOrdenacao(char opcao, int[] vetor){

    Lista lista = new Lista();
    lista.arrayToList(vetor,lista);
    lista.exibirLista();
    if(opcao=='A')
        lista.InsercaoDireta();

    lista.exibirLista();

}

void main(){

    int[] vetor = {6,4,7,8,3,2,9,1}; //8 Elementos
    char opcao='1';
    Scanner input = new Scanner(System.in);

    while(opcao!='0'){
        System.out.println("---- Metodos de Ordenação ----");
        System.out.println("A...Inserçao Direta");
        System.out.println("B...Inserção binária");
        System.out.println("C...Seleção Direta");
        System.out.println("D...Bolha");
        System.out.println("E...Shake");
        System.out.println("0...Sair");
        System.out.println("Opcao: ");
        opcao = input.next().charAt(0);
        Character.toUpperCase(opcao);
        chamaOrdenacao(opcao, vetor);
    }



}
