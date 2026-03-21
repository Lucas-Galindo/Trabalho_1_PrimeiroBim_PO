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
            if(nodo.getInfo() == aux)
                return posMeio;
            else if(aux < nodo.getInfo())
                posFim = posMeio - 1;
            else
                posInicio = posMeio + 1;
        }
        return posInicio;
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

//    //Releitura de selecaoDireta
//    public void SelecaoDireta(){
//        int menor;
//        Lista lista = inicio,aux,posMenor;
//        while(lista.getProx() != null){
//            menor = lista.getInfo();
//            posMenor = lista;
//            aux = lista.getProx();
//            while(aux.getProx() != null){
//                if(aux.getInfo() < menor) {
//                    // O da frente é menor que o de tras?
//                    menor = aux.getInfo();
//                    posMenor = aux;
//                }
//                aux.getProx();
//            }
//
//            posMenor.setInfo(lista.getInfo());
//            lista.setInfo(menor);
//
//            lista.getProx();
//        }
//    }


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


//    public void ShakeSort(){
//        int aux;
//        Lista ini = inicio, fim = retornaFimLista(inicio);
//        boolean flag = true;
//        while(ini != fim && flag){
//            flag = false;
//            Lista atual = ini;
//            while(atual != fim){
//                if(atual.getInfo() > atual.getProx().getInfo()){
//                    aux = atual.getInfo();
//                    atual.setInfo(atual.getProx().getInfo());
//                    atual.getProx().setInfo(aux);
//                    flag= true;
//                }
//                atual = atual.getProx();
//            }
//            fim = fim.getAnt();
//
//            if(flag){
//                atual = fim;
//                while(atual != ini ){
//                    if(atual.getInfo()< atual.getAnt().getInfo()){
//                        aux = atual.getInfo();
//                        atual.setInfo(atual.getAnt().getInfo());
//                        atual.getAnt().setInfo(aux);
//                        flag = true;
//                    }
//                    atual = atual.getAnt();
//                }
//                ini = ini.getProx();
//            }
//        }
//    }

    public void shellsort(){
        int pos, pos_dist, dist = 1,ele;
        int TL = retornaTam(inicio);
        Lista lista = inicio, auxLista, auxPosDist = new Lista();
        while(dist<TL)
            dist = dist * 3 + 1;
        dist = dist/3;
        while(dist>0){
            for(int i = dist; i< TL; i++){

                auxLista = posicionaLista(0,i,inicio);
                ele = auxLista.getInfo();
                pos = i;
                auxPosDist = posicionaLista(0,pos-dist,inicio);
                while(pos - dist >=0 && ele < auxPosDist.getInfo()){
                    auxLista = posicionaLista(0,pos,inicio);
                    auxLista.setInfo(auxPosDist.getInfo());

                    pos = pos - dist;
                    if(pos-dist >= 0)
                        auxPosDist = posicionaLista(0, pos-dist,inicio);
                }
                auxLista = posicionaLista(0,pos,inicio);
                auxLista.setInfo(ele);
            }
            dist = dist/3;
        }
    }


    public void heap(){
      int posFim, tl = retornaTam(inicio);
      posFim = tl;
      int posPai, posFE, posFD, posMaiorF, auxEle;
      Lista FE = new Lista();
      Lista FD = new Lista();
      Lista maiorF = new Lista();
      Lista pai = new Lista();
      Lista fim = new Lista();

      while(tl > 1){
          posPai = tl/2-1;
          while(posPai>=0){
              posFE = 2*posPai+1;
              posFD = posFE+1;
              if(posFD<tl){
                  FE = posicionaLista(0,posFE,inicio);
                  FD = posicionaLista(0,posFD,inicio);
                  if(FE.getInfo() > FD.getInfo())
                    posMaiorF = posFE;
                  else posMaiorF = posFD;

              }
              else posMaiorF = posFE;

              pai = posicionaLista(0,posPai,inicio);
              maiorF = posicionaLista(0,posMaiorF,inicio);
              if(pai.getInfo() < maiorF.getInfo()){
                  auxEle = pai.getInfo();
                  pai.setInfo(maiorF.getInfo());
                  maiorF.setInfo(auxEle);
              }
              posPai--;
          }
          tl--;
          fim = posicionaLista(0,posFim-1,inicio);
          auxEle = fim.getInfo();
          fim.setInfo(inicio.getInfo());
          inicio.setInfo(auxEle);
          posFim--;
      }


    }


    public void bolha(){
        int aux;
        //Lista lista = inicio;

        boolean flag = true;
        Lista pInicio,pFim = retornaFimLista(inicio);
        while(pFim!=inicio && flag == true){
            flag = false;
            pInicio= inicio;
            while(pInicio!=pFim){

                if(pInicio.getInfo() > pInicio.getProx().getInfo()){
                    aux = pInicio.getInfo();
                    pInicio.setInfo(pInicio.getProx().getInfo());
                    pInicio.getProx().setInfo(aux);
                    flag = true;
                }
                pInicio = pInicio.getProx();
            }
            pFim = pFim.getAnt();

        }
    }

    public void quickSemPivo(){
        quickSP(0,retornaTam(inicio)-1);
    }
    public void quickSP(int ini, int fim){
        int i = ini, j = fim;
        Lista listaI = new Lista();
        Lista listaJ = new Lista();
        boolean flag = true;
        while(i<j){

            listaI = posicionaLista(0,i,inicio);
            listaJ = posicionaLista(0,j,inicio);
            if(flag)
            {
                while(i<j && listaI.getInfo() <= listaJ.getInfo())
                {
                    i++;
                    listaI = posicionaLista(0,i,inicio);
                }

            }
            else {
                while(i<j && listaI.getInfo() <= listaJ.getInfo())
                {
                    j--;
                    listaJ = posicionaLista(0,j,inicio);
                }
            }
            int aux = listaI.getInfo();
            listaI.setInfo(listaJ.getInfo());
            listaJ.setInfo(aux);
            flag = !flag;
        }
        if(ini < i-1)
            quickSP(ini,i-1);
        if(j+1<fim)
            quickSP(j+1,fim);
    }

    public void quickComPivo(){
        quickP(0,retornaTam(inicio)-1);
    }
    public void quickP(int ini, int fim){
        int i = ini, j = fim, pivo = (ini+fim)/2;
        int aux;
        Lista listaI = new Lista();
        Lista listaJ = new Lista();
        Lista listaPivo = new Lista();
        while(i<j){

            listaI = posicionaLista(0,i,inicio);
            listaJ = posicionaLista(0,j,inicio);
            listaPivo = posicionaLista(0,pivo,inicio);
            while(listaI.getInfo()<listaPivo.getInfo()){
                i++;
                listaI = posicionaLista(0,i,inicio);
            }
            while(listaJ.getInfo()>listaPivo.getInfo()){
                j--;
                listaJ = posicionaLista(0,j,inicio);
            }
            if(i<=j){
                aux = posicionaLista(0,i,inicio).getInfo();
                listaI.setInfo(listaJ.getInfo());
                listaJ.setInfo(aux);
                i++;
                j--;
            }
            if(ini<j)
                quickP(ini,j);
            if(i<fim)
                quickP(i,fim);
        }
    }


    public void countingSort(){
        int i,j;
        Lista lista = inicio, maior = lista;
        lista = lista.getProx();

        //Encontra o maior valor
        while(lista!=null){
            if(lista.getInfo() > maior.getInfo())
                maior = lista;
            lista = lista.getProx();
        }

        //Vetor destinado a contagem de cada elemento
        int[] contagem = new int[maior.getInfo()];
        lista = inicio;

        //Adicionando a quantidade de vezes que o elemento aparece no lugar
        while(lista!=null){
            contagem[lista.getInfo() - 1] = contagem[lista.getInfo() - 1]+1;
            lista = lista.getProx();
        }

        //Fazendo somatorio dentro do vetor
        for(i=1;i<contagem.length;i++)
            contagem[i] = contagem[i]+contagem[i-1];

        //Cria um vetor do tamanho da lista
        int[] grandeVetor = new int[retornaTam(inicio)];

        //Vai posicionando cada elemento em seu respectivo lugar em vetorGrande
        lista = retornaFimLista(inicio);
        while(lista!=null){
            grandeVetor[contagem[lista.getInfo()-1]-1] = lista.getInfo();
            contagem[lista.getInfo()-1] = contagem[lista.getInfo()-1]-1;
            lista=lista.getAnt();
        }
        for(i=0,lista=inicio;lista!=null;i++,lista=lista.getProx())
            lista.setInfo(grandeVetor[i]);

    }


    public void combSort(){
        int TL = retornaTam(inicio);
        int mov = TL, aux;
        boolean troca = true;
        Lista listaI, listaJ;

        while(troca || mov > 1){
            if(mov > 1)
                mov = (int)(mov / 1.3);

            troca = false;

            for(int i = 0; i + mov < TL; i++){
                listaI = posicionaLista(0,i,inicio);
                listaJ = posicionaLista(0,i+mov,inicio);

                if(listaI.getInfo() > listaJ.getInfo()){
                    aux = listaI.getInfo();
                    listaI.setInfo(listaJ.getInfo());
                    listaJ.setInfo(aux);
                    troca = true;
                }
            }
        }
    }

    public void radixSort(){
        int maior = buscaMaior();

        for(int exp = 1; maior/exp > 0; exp *= 10)
            counting(exp);
    }

    public void counting(int exp){
        int i, TL = retornaTam(inicio);
        int[] aux = new int[TL];
        int[] cont = new int[10];
        Lista nodo;

        for(i = 0; i < 10; i++)
            cont[i] = 0;

        for(i = 0; i < TL; i++){
            nodo = posicionaLista(0,i,inicio);
            cont[(nodo.getInfo() / exp) % 10]++;
        }

        for(i = 1; i < 10; i++)
            cont[i] = cont[i] + cont[i - 1];

        for(i = TL - 1; i >= 0; i--){
            nodo = posicionaLista(0,i,inicio);
            aux[cont[(nodo.getInfo() / exp) % 10] - 1] = nodo.getInfo();
            cont[(nodo.getInfo() / exp) % 10]--;
        }

        for(i = 0; i < TL; i++){
            nodo = posicionaLista(0,i,inicio);
            nodo.setInfo(aux[i]);
        }
    }


    public void bucketSort(){
        int TL = retornaTam(inicio);
        int numBuckets = 5;
        int[][] buckets = new int[numBuckets][TL];
        int[] bucketSizes = new int[numBuckets];
        Lista aux = inicio;
        int maior = buscaMaior();

        while(aux != null){
            int index = (int)((aux.getInfo() / (double) maior) * numBuckets);

            if(index >= numBuckets)
                index = numBuckets - 1;

            buckets[index][bucketSizes[index]] = aux.getInfo();
            bucketSizes[index]++;
            aux = aux.getProx();
        }

        for(int i = 0; i < numBuckets; i++)
            insercaoDiretaBucket(buckets[i], bucketSizes[i]);

        aux = inicio;
        for(int i = 0; i < numBuckets; i++){
            for(int j = 0; j < bucketSizes[i]; j++){
                aux.setInfo(buckets[i][j]);
                aux = aux.getProx();
            }
        }
    }


    public void insercaoDiretaBucket(int[] vet, int tl){
        int i, j, aux;
        for(i = 1; i < tl; i++){
            aux = vet[i];
            j = i - 1;
            while(j >= 0 && aux < vet[j]){
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = aux;
        }
    }



    public void insereTim(int left, int right){
        int aux, j;
        Lista nodoI, nodoJ, nodoJ1;

        for(int i = left + 1; i <= right; i++){
            nodoI = posicionaLista(0,i,inicio);
            aux = nodoI.getInfo();
            j = i - 1;

            while(j >= left){
                nodoJ = posicionaLista(0,j,inicio);
                if(nodoJ.getInfo() > aux){
                    nodoJ1 = posicionaLista(0,j + 1,inicio);
                    nodoJ1.setInfo(nodoJ.getInfo());
                    j--;
                }
                else
                    break;
            }

            nodoJ1 = posicionaLista(0,j + 1,inicio);
            nodoJ1.setInfo(aux);
        }
    }




    public void timSort(){
        int TL = retornaTam(inicio);
        int run = 32;
        int mid, right;

        for(int i = 0; i < TL; i += run){
            insereTim(i, Math.min(i + 31, TL - 1));
        }

        for(int tam = run; tam < TL; tam = 2 * tam){
            for(int esq = 0; esq < TL; esq += 2 * tam){
                mid = esq + tam - 1;
                right = Math.min(esq + 2 * tam - 1, TL - 1);

                if(mid < right)
                    merge(esq, mid, right);
            }
        }
    }

    public void merge(int esq, int meio, int dir){
        int tam1 = meio - esq + 1;
        int tam2 = dir - meio;
        int[] vet1 = new int[tam1];
        int[] vet2 = new int[tam2];
        Lista nodo;
        int i, j, k;

        for(i = 0; i < tam1; i++){
            nodo = posicionaLista(0, esq + i, inicio);
            vet1[i] = nodo.getInfo();
        }

        for(i = 0; i < tam2; i++){
            nodo = posicionaLista(0, meio + 1 + i, inicio);
            vet2[i] = nodo.getInfo();
        }

        i = 0;
        j = 0;
        k = esq;

        while(i < tam1 && j < tam2){
            nodo = posicionaLista(0, k, inicio);

            if(vet1[i] <= vet2[j]){
                nodo.setInfo(vet1[i]);
                i++;
            }
            else{
                nodo.setInfo(vet2[j]);
                j++;
            }
            k++;
        }

        while(i < tam1){
            nodo = posicionaLista(0, k, inicio);
            nodo.setInfo(vet1[i]);
            i++;
            k++;
        }

        while(j < tam2){
            nodo = posicionaLista(0, k, inicio);
            nodo.setInfo(vet2[j]);
            j++;
            k++;
        }
    }

    public void gnomeSort(){
        int TL = retornaTam(inicio);
        int i = 1, aux;
        Lista nodoI, nodoAnt;

        while(i < TL){
            nodoI = posicionaLista(0,i,inicio);
            nodoAnt = posicionaLista(0,i-1,inicio);

            if(i != 0 && nodoI.getInfo() < nodoAnt.getInfo()){
                aux = nodoI.getInfo();
                nodoI.setInfo(nodoAnt.getInfo());
                nodoAnt.setInfo(aux);
                i--;
            }
            else
                i++;
        }
    }



    public int buscaMaior(){
        int TL = retornaTam(inicio);
        int maior = inicio.getInfo();
        Lista nodo;

        for(int i = 1; i < TL; i++){
            nodo = posicionaLista(0,i,inicio);
            if(nodo.getInfo() > maior)
                maior = nodo.getInfo();
        }

        return maior;
    }


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
        //Lista lista = cabeca;

        while(posAtual < posDesejada && lista != null){
            posAtual++;
            lista = lista.getProx();
        }

        return lista;
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


    public Lista retornaFimLista(Lista lista){
        Lista ant = null;
        while(lista!=null){
            ant = lista;
            lista = lista.getProx();
        }
        return ant;
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