
public void chamaOrdenacao(char opcao, int[] vetor){

    Lista lista = new Lista();
    lista.arrayToList(vetor,lista);

    switch(opcao){
        case 'A':
            lista.InsercaoDireta();
            break;
        case 'B':
            lista.InsercaoBinaria();
            break;
        case 'C':
            lista.selecaoDireta();
            break;
        case 'D':
            lista.bolha();
            break;
        case 'E':
            lista.shakeSort();
            break;
        case 'F':
            lista.heap();
            break;
        case 'G':
            lista.shellsort();
            break;
        case 'H':
            lista.quickComPivo();
            break;
        case 'I':
            lista.quickSemPivo();
            break;
        case 'M':
            lista.countingSort();
            break;

    }
    lista.exibirLista();

}

void main(){

    //int[] vetor = {6,4,2,9,1}; //8 Elementos
    int[] vetor = {6,4,7,8,3,2,9,1,13,17,19,28,18,30,35,32,98,95,94};
    char opcao='1';
    Scanner input = new Scanner(System.in);

    while(opcao!='0'){
        System.out.println("---- Metodos de Ordenação ----");
        System.out.println("A...Inserçao Direta");
        System.out.println("B...Inserção binária");
        System.out.println("C...Seleção Direta");
        System.out.println("D...Bolha");
        System.out.println("E...Shake");
        System.out.println("F...Heap Sort");
        System.out.println("G...Shell Sort");
        System.out.println("H...Quick");
        System.out.println("I...Quick com pivo");
        System.out.println("J...Fusao Direta");
        System.out.println("K...Merge");
        System.out.println("L...Merge");
        System.out.println("M...CoutingSort");
        System.out.println("N...Bucket");
        System.out.println("O...Radix");
        System.out.println("P...Comb");
        System.out.println("Q...Gnome");
        System.out.println("R...Tim");
        System.out.println("0...Sair");
        System.out.println("Opcao: ");
        opcao = input.next().charAt(0);
        opcao = Character.toUpperCase(opcao);
        chamaOrdenacao(opcao, vetor);
    }



}
