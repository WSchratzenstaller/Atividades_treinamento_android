package com.example.minhaagenda

open class Contato (private val nome: String,
                    private val celular: String){

    open fun getNome(): String {
        return nome
    }

    // Trocar para var e adicionar set caso queira campos editáveis.
    // Obs: Não é tratado como editável em nenhuma parte da atividade. Errou, remove e insere de novo.
    //open fun setNome(nomeRecebido: String){
    //    nome = nomeRecebido
    //}

    open fun getCelular(): String {
        return celular
    }
}

