import static java.awt.SystemColor.info;

public class Lista {
    public Info objetoInfo;
    public Lista prox;
    public Lista ant;
    public Lista inicio;

    public Lista(){

    }
    public Lista(int elemento, Lista prox, Lista ant){
        Info info = new Info(elemento);
        this.objetoInfo = info;
        this.prox = prox;
        this.ant = ant;
    }


    public void arrayToList(int[] vetor,Lista lista){

        Lista  aux = null;

        for(int i = 0; i < vetor.length; i++){
            Lista novo = new Lista();
            novo.setInfo(vetor[i]);
            novo.setProx(null);
            novo.setAnt(null);

            if(lista.inicio == null){
                lista.inicio = novo;

            } else {
                aux = lista.inicio;
                while(aux.getProx() != null){
                    aux = aux.getProx();
                }
                novo.setAnt(aux);
                aux.setProx(novo);

            }
        }
    }

    public void exibirLista(){
        Lista aux = this;
        while(aux!=null){
            System.out.println(aux.getObjetoInfo());
            aux = aux.getProx();
        }
    }

    public void InsercaoDireta(){
        Lista pi = inicio.getProx(),pPos;
        int aux;
        while(pi!=null){
            aux = pi.getObjetoInfo().getInfo();
            //Aqui o pPos ta apontando pro mesmo endereco de memoria
            pPos = pi;
            while(pPos!=null && aux<pPos.getAnt().getObjetoInfo().getInfo()){
                pPos.setInfo(pPos.getAnt().getObjetoInfo().getInfo());
                pPos = pPos.getAnt();
            }
        }
    }

    public void InsercaoBinaria(){
        Lista pPos, pi = this, pos;
        int i;
        int tam = retornaTam(this);
        int aux;
        for(i=1;i<tam;i++){
            aux = pi.getObjetoInfo().getInfo();
            pos = buscaBinaria(aux);

            for(int j = i;pos!=null;j--){
                pos.setInfo(pos.getAnt().getObjetoInfo().getInfo());
                pos = pos.getAnt();
            }
        }
    }

    public int retornaTam(Lista lista){
        int qtde = 1;
        while(lista!=null){
            lista = lista.getProx();
            qtde++;
        }
        return qtde;
    }

    public Lista posicionaLista(int posInicial, int posDesejada,Lista lista){
        int posAtual = posInicial;
        while(posAtual <= posDesejada){
            posAtual++;
            lista = lista.getProx();
        }
        return lista;
    }
    public Lista buscaBinaria(int aux){

        int posInicio, posFim, posMeio,tam;
        Lista lista = this.inicio;
        tam = retornaTam(lista);
        posInicio = 0;
        posFim = tam - 1;
        posMeio = tam/2;
        while(posInicio<posFim && aux!=lista.getObjetoInfo().getInfo()){
            if(aux<lista.getObjetoInfo().getInfo())
                posFim = posMeio - 1;
            else
                posInicio = posMeio + 1;
            posMeio = (posInicio+posFim)/2;
            lista = posicionaLista(posInicio, posMeio,lista);

        }
        lista = posicionaLista(posInicio, posMeio,lista);
        if(aux>lista.getObjetoInfo().getInfo())
            return lista;
        return null;
    }

    public void selecaoDireta(){

        Lista lista = inicio, aux,aux2;
        Lista menor;
        int posMenor;

        for(int i=0;i<retornaTam(inicio);i++){
            posMenor = i;
            menor = posicionaLista(0,i,lista);
            for(int j = i+1; j<retornaTam(inicio);j++)
            {
                aux = posicionaLista(i,j,lista);
                if(aux.getObjetoInfo().getInfo() < menor.getObjetoInfo().getInfo())
                {
                    posMenor = j;
                    menor.setInfo(aux.getObjetoInfo().getInfo());
                }

            }

            //vet[i]
            aux = posicionaLista(0,i,lista);

            //vet[posMenor]
            //aux2 = posicionaLista(i,posMenor,lista);

            lista = posicionaLista(0,i,lista);
            lista.setInfo(menor.getObjetoInfo().info);

            lista = posicionaLista(0,posMenor,lista);
            lista.setInfo(aux.getObjetoInfo().getInfo());




        }
    }


    public Info getObjetoInfo() {
        return objetoInfo;
    }

    public void setInfo(int elemento) {
        this.objetoInfo = new Info(elemento);
    }

    public Lista getProx() {
        return prox;
    }

    public void setProx(Lista prox) {
        this.prox = prox;
    }

    public Lista getAnt() {
        return ant;
    }

    public void setAnt(Lista ant) {
        this.ant = ant;
    }
}
