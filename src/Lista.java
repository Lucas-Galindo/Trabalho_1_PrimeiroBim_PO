public class Lista {
    public int info;
    public Lista prox;
    public Lista ant;
    public Lista inicio;

    public Lista(){

    }
    public Lista(int elemento, Lista prox, Lista ant){
        this.info = elemento;
        this.prox = prox;
        this.ant = ant;
    }


    public void arrayToList(int[] vetor,Lista lista){

        Lista  aux;

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
            pPos = pi;
            while(pPos.getAnt() != null && aux<pPos.getAnt().getInfo()){
                pPos.setInfo(pPos.getAnt().getInfo());
                pPos = pPos.getAnt();
            }
            pPos.setInfo(aux);
            pi = pi.getProx();
        }
    }

    public int retornaTam(Lista lista){
        int qtde = 0;
        while(lista!=null){
            lista = lista.getProx();
            qtde++;
        }
        return qtde;
    }

    public void InsercaoBinaria(){
        int tam = retornaTam(inicio);
        for(int i = 1; i < tam; i++){
            Lista nodoI = posicionaLista(0, i, inicio);
            int aux = nodoI.getInfo();
            int pos = buscaBinaria(aux, i);
            for(int j = i; j > pos; j--){
                Lista nodoJ = posicionaLista(0, j, inicio);
                nodoJ.setInfo(nodoJ.getAnt().getInfo());
            }
            Lista nodoPos = posicionaLista(0, pos, inicio);
            nodoPos.setInfo(aux);
        }
    }

    public int buscaBinaria(int aux, int tam){
        int posInicio = 0;
        int posFim = tam - 1;
        int posMeio;
        while(posInicio <= posFim){
            posMeio = (posInicio + posFim) / 2;
            Lista nodo = posicionaLista(0, posMeio, inicio);
            if(nodo.getInfo() == aux)   return posMeio;
            else if(aux < nodo.getInfo()) posFim = posMeio - 1;
            else                          posInicio = posMeio + 1;
        }
        return posInicio;
    }

    public Lista posicionaLista(int posInicial, int posDesejada, Lista lista){
        int posAtual = posInicial;
        //Lista lista = cabeca;

        while(posAtual < posDesejada && lista != null){
            posAtual++;
            lista = lista.getProx();
        }

        return lista;
    }

    public void selecaoDireta(){

        Lista lista = inicio, aux;
        Lista menor;
        int posMenor;
        for(int i=0;i<retornaTam(inicio)-1;i++){
            menor = posicionaLista(0,i,inicio);

            posMenor = i;
            for(int j = i+1; j<retornaTam(inicio);j++)
            {
                aux = posicionaLista(0,j,inicio);
                if(aux.getInfo() < menor.getInfo())
                {
                    menor = aux;
                    posMenor = j;
                }
            }

            // Corrigido: sobrescrevia 'menor' antes de usa-lo; swap seguro com int tmp
            aux = posicionaLista(0,i,inicio);
            int tmp = aux.getInfo();
            aux.setInfo(menor.getInfo());
            menor.setInfo(tmp);
        }
    }


    //Essa ordenação funciona somente para casos em que se SABE a PRIORI a qual
    // os elementos já estão quase em ORDEM
    public void shakeSort(){
        int aux;
        Lista lista = inicio;
        Lista ini = inicio, fim = retornaFimLista(lista);
        boolean flag = true;
        while(ini != fim && flag){
            flag = false;
            Lista cur = ini;
            while(cur != fim){
                if(cur.getInfo() > cur.getProx().getInfo()){
                    aux = cur.getInfo();
                    cur.setInfo(cur.getProx().getInfo());
                    cur.getProx().setInfo(aux);
                    flag = true;
                }
                cur = cur.getProx();
            }
            fim = fim.getAnt();

            if(flag){
                flag = false;
                // Corrigido: idem, percorre por ponteiro direto
                cur = fim;
                while(cur != ini){
                    if(cur.getInfo() < cur.getAnt().getInfo()){
                        aux = cur.getInfo();
                        cur.setInfo(cur.getAnt().getInfo());
                        cur.getAnt().setInfo(aux);
                        flag = true;
                    }
                    cur = cur.getAnt();
                }
                ini = ini.getProx();
            }
        }

    }

    public int retornaPosLista(Lista procurado){
        Lista lista = inicio;
        int pos = 0;
        while(lista != null && lista != procurado){
            lista = lista.getProx();
            pos++;
        }
        if(lista == procurado)
            return pos;
        return -1;

    }

    public void shellsort(){
        int pos, pos_dist, dist = 1;
        int TL = retornaTam(inicio);
        Lista lista = inicio, auxLista, auxListaPosDist;
        while(dist<TL)
            dist = dist * 3 + 1;
        dist = dist/3;
        while(dist>0){
            for(int i = dist; i< TL; i++){

                lista = posicionaLista(0,i,inicio);
                auxLista = buscaExaustiva(lista.getInfo());
                pos = i;
                //pos_dist = pos - dist;

                lista = posicionaLista(0,pos - dist,inicio);
                while(pos >= dist && auxLista.getInfo() < lista.getInfo()){

                    lista = posicionaLista(0,pos,lista);
                    auxListaPosDist = posicionaLista(0,pos - dist,lista);
                    lista.setInfo(auxListaPosDist.getInfo());

                    lista = posicionaLista(0,pos - dist,inicio);
                    pos = pos - dist;

                }
                lista = posicionaLista(0,pos,inicio);
                lista.setInfo(auxLista.getInfo());
            }
            dist = dist/3;
        }
    }

    public Lista buscaExaustiva(int chave){
        Lista lista = inicio;
        while(lista!=null && lista.getInfo()!=chave)
            lista = lista.getProx();
        if(lista.getInfo()==chave)
            return lista;
        return null;
    }

    public boolean nula(Lista lista){
        return lista == null;
    }
    public void heap(){
        int tl = retornaTam(inicio);
        Lista pai, F1,F2,maiorF = null,aux=null,fim=null;
        int posPai,posF1,posF2,posMaiorF,auxElemento;
        Lista lista = inicio;
        while(tl>1){
            for(posPai = tl/2-1;posPai>=0;posPai--){
                posF1 = 2*posPai+1;
                posF2 = posF1+1;
                posMaiorF = posF1;

                F1 = posicionaLista(0,posF1,inicio);
                F2 = posicionaLista(0,posF2,inicio);
                if(nula(F1) || nula(F2))
                    System.out.println("F1 ou F2 é NULL");

                if(posF2 < tl && F1.getInfo() < F2.getInfo())
                {
                    //maiorF.setInfo(F2.getInfo());
                    //maiorF = F2;
                    posMaiorF = posF2;
                }
                maiorF = posicionaLista(0,posMaiorF,inicio);
                pai = posicionaLista(0,posPai,inicio);
                if(maiorF.getInfo() > pai.getInfo()){
                    auxElemento = maiorF.getInfo();


                    pai.setInfo(auxElemento);

                }
            }
            tl--;
            Lista inicioAux = posicionaLista(0,0,inicio);
            aux = inicioAux;
            //aux.setInfo(inicio.getInfo());
            fim = posicionaLista(0,tl,inicio);
            inicioAux.setInfo(fim.getInfo());
            fim.setInfo(aux.getInfo());
        }

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
                    // Corrigido: estava faltando; sem isso o laco externo encerrava apos 1 passagem
                    flag = true;
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