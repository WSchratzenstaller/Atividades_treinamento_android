package com.example.minhaagenda

class Pessoal (
        nomePessoal: String,
        celularPessoal: String,
        private var referencia : String
) : Contato(
        nome = nomePessoal,
        celular = celularPessoal
) {
    fun getReferencia(): String {
        return referencia
    }
}