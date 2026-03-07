public class Lista {
    public int info;
    public Lista prox;
    public Lista ant;
    public Lista inicio;

    public Lista(){

    }
    public Lista(int elemento, Lista prox, Lista ant){
        //Info info = new Info(elemento);
        this.info = elemento;
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
        Lista aux = inicio;
        while(aux!=null){
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }

    public void InsercaoDireta(){
        Lista pi = inicio.getProx(),pPos;
        int aux;
        while(pi!=null){
            aux = pi.getInfo();
            //Aqui o pPos ta apontando pro mesmo endereco de memoria
            pPos = pi;
            while(pPos!=inicio && aux<pPos.getAnt().getInfo()){
                pPos.setInfo(pPos.getAnt().getInfo());
                pPos = pPos.getAnt();
            }
            pPos.setInfo(aux);
            pi = pi.getProx();
        }
    }

//    public void InsercaoBinaria(){
//        Lista aux = null, aux2 = null;
//        Lista lista = inicio;
//        int pos;
//        for(int i= 1 ;i<retornaTam(inicio); i++){
//            lista = posicionaLista(0,i,lista);
//            aux = lista;
//            pos = buscaBinaria(aux.getInfo(),i);
//            for(int j = i; j>pos; j--){
//                lista = posicionaLista(0,j,lista);
//                lista.setInfo(lista.getAnt().getInfo());
//
//            }
//
//            lista = posicionaLista(0,pos,lista);
//            lista.setInfo(aux.getInfo());
//
//        }
//    }

    public int retornaTam(Lista lista){
        int qtde = 0;
        while(lista!=null){
            lista = lista.getProx();
            qtde++;
        }
        return qtde;
    }

    public Lista posicionaLista(int posInicial, int posDesejada, Lista lista){
        int posAtual = posInicial;

        while(posAtual < posDesejada && lista != null){
            posAtual++;
            lista = lista.getProx();
        }

        return lista;
    }

//    public int buscaBinaria(int aux,int tam){
//
//        int posInicio, posFim, posMeio;
//        Lista lista = inicio;
//        posInicio = 0;
//        posFim = tam - 1;
//        posMeio = tam/2;
//
//    }


    public void selecaoDireta(){

        Lista lista = inicio , aux;
        Lista menor;
        int posMenor;
        for(int i=0;i<retornaTam(inicio)-1;i++){
            menor = posicionaLista(0,i,inicio);

            posMenor = i;
            for(int j = i+1; j<retornaTam(inicio);j++)
            {
                aux = posicionaLista(i,j,inicio);
                if(aux.getInfo() < menor.getInfo())
                {
                    menor = aux;
                    posMenor = j;
                    System.out.println("if" +menor.getInfo());
                }
            }

//            aux = posicionaLista(0,i,inicio);
//            lista = posicionaLista(0,posMenor,inicio);
//            lista.setInfo(aux.getInfo());
//
//            lista = posicionaLista(0,i,inicio);
//            lista.setInfo(menor.getInfo());

            aux = posicionaLista(0,i,inicio);
            int tmp = aux.getInfo();
            aux.setInfo(menor.getInfo());
            menor.setInfo(tmp);

        }
        lista.exibirLista();
    }


    //Essa ordenação funciona somente para casos em que se SABE a PRIORI a qual
    // os elementos já estão quase em ORDEM
    public void shakeSort(){
        Lista aux = null;
        Lista lista = inicio;
        Lista ini = inicio, fim = retornaFimLista(lista);
        boolean flag = true;
        while(ini != fim && flag){
            flag = false;
            for(int i= 0 ; i< retornaTam(lista); i++){
                lista = posicionaLista(0,i,lista);
                if(lista.getInfo() > lista.getProx().getInfo()){
                    aux = lista;
                    lista.setInfo(lista.getProx().getInfo());
                    lista.getProx().setInfo(aux.getInfo());
                    flag = true;

                }
            }
            fim = fim.getAnt();

            if(flag){
                flag = false;
                for(int i = retornaPosLista(fim); i > retornaPosLista(ini) ; i--){
                    lista = posicionaLista(0,i,lista);
                    if(lista.getInfo() < lista.getAnt().getInfo()){
                        aux = lista;
                        lista.setInfo(lista.getAnt().getInfo());
                        lista.getAnt().setInfo(aux.getInfo());
                        flag = true;
                    }
                }
                ini = ini.getProx();
            }
        }

    }

    public int retornaPosLista(Lista procurado){
        Lista lista = inicio;
        int pos = 0;
        while(lista.getInfo() != procurado.getInfo()) {
            lista = lista.getProx();
            pos++;
        }
        if(lista.getInfo() == procurado.getInfo())
            return pos;
        return -1;

    }

    public Lista retornaFimLista(Lista lista){
        Lista ant = null;
        while(lista!=null){
            ant = lista;
            lista = lista.getProx();
        }
        return ant;
    }


    public void bolha(){
        int aux;
        Lista lista = inicio;

        boolean flag = true;
        Lista pInicio,pFim = retornaFimLista(lista);
        while(pFim!=inicio && flag == true){
            flag = false;
            pInicio= inicio;
            while(pInicio!=pFim){

                if(pInicio.getInfo() > pInicio.getProx().getInfo()){
                    aux = pInicio.getInfo();
                    pInicio.setInfo(pInicio.getProx().getInfo());
                    pInicio.getProx().setInfo(aux);
                }
                pInicio = pInicio.getProx();
            }
            pFim = pFim.getAnt();

        }
    }


    public int getInfo() {
        return info;
    }

    public void setInfo(int elemento) {
        this.info = elemento;
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
