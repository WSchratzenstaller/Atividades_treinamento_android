package com.example.minhaagenda

class Profissional (
        nomeProfissional: String,
        celularProfissional: String,
        private var email : String
) : Contato(
        nome = nomeProfissional,
        celular = celularProfissional
) {
    fun getEmail(): String {
        return email
    }
}