package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var celular: EditText
    private lateinit var rgOpcoes: RadioGroup
    private lateinit var rbPessoal: RadioButton
    private lateinit var rbProfissional: RadioButton
    private lateinit var referencia: EditText
    private lateinit var email: EditText
    private lateinit var btnSalvar: Button
    private lateinit var pesquisar: EditText
    private lateinit var btnPesquisar: ImageButton
    private lateinit var contatos: TextView
    private lateinit var btnContatos: Button
    private lateinit var tipoContato: String
    var contatosPessoaisCadastrados = mutableListOf<Pessoal>()
    var contatosProfissionaisCadastrados = mutableListOf<Profissional>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        bindViews()
    }

    private fun bindViews() {
        nome = findViewById(R.id.editTextNome)
        celular = findViewById(R.id.editTextCelular)
        rgOpcoes = findViewById(R.id.radioGroupOpcoes)
        rbPessoal = findViewById(R.id.radioButtonPessoal)
        rbProfissional = findViewById(R.id.radioButtonProfissional)
        referencia = findViewById(R.id.editTextReferencia)
        email = findViewById(R.id.editTextEmail)
        btnSalvar = findViewById(R.id.buttonSalvar)
        pesquisar = findViewById(R.id.editTextPesquisar)
        btnPesquisar = findViewById(R.id.imageButtonPesquisar)
        contatos = findViewById(R.id.textViewContatos)
        btnContatos = findViewById(R.id.buttonContatos)

        rgOpcoes.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonPessoal) {
                tipoContato = "Pessoal"
                email.visibility = View.INVISIBLE
                referencia.visibility = View.VISIBLE
            }
            if (checkedId == R.id.radioButtonProfissional) {
                tipoContato = "Profissional"
                referencia.visibility = View.INVISIBLE
                email.visibility = View.VISIBLE
            }
        }

        btnSalvar.setOnClickListener {
            val nomeInformado = nome.text.toString()
            val celularInformado = celular.text.toString()
            if (tipoContato == "Pessoal") {
                val referenciaInformada = referencia.text.toString()
                adicionarNaLista(nomeInformado, celularInformado, referenciaInformada)
            } else {
                val emailInformado = email.text.toString()
                adicionarNaLista(nomeInformado, celularInformado, emailInformado)
            }
            nome.text = null
            celular.text = null
            referencia.text = null
            email.text = null
        }

        btnPesquisar.setOnClickListener{
            val nomePesquisado = pesquisar.text.toString()
            pesquisarContato(nomePesquisado)
        }

        btnContatos.setOnClickListener{
            exibirListaOrdenada()
        }
    }

    fun adicionarNaLista(
        nomeInformado: String,
        celularInformado: String,
        referenciaOuEmail: String
    ) {
        if (tipoContato == "Pessoal") {
            var contatoPessoal = Pessoal(nomeInformado, celularInformado, referenciaOuEmail)
            contatosPessoaisCadastrados.add(contatoPessoal)
            exibirListaOrdenada()
        } else {
            var contatoProfissional = Profissional(nomeInformado, celularInformado, referenciaOuEmail)
            contatosProfissionaisCadastrados.add(contatoProfissional)
            exibirListaOrdenada()
        }
    }

    fun exibirListaOrdenada(){
        contatosPessoaisCadastrados.sortBy { it.getNome() }
        var concatenaResultado = ""
        if (contatosPessoaisCadastrados.size > 0) {
            concatenaResultado = "CONTATOS PESSOAIS:\nNome\t-\tCelular\t-\tReferência\n"
            for (contato in contatosPessoaisCadastrados) {
                concatenaResultado += contato.getNome() + "\t-\t" + contato.getCelular() + "\t-\t" + contato.getReferencia() + "\n"
            }
        }

        if (contatosProfissionaisCadastrados.size > 0) {
            contatosProfissionaisCadastrados.sortBy { it.getNome() }
            concatenaResultado += "CONTATOS PROFISSIONAIS:\nNome\t-\tCelular\t-\tEmail\n"
            for (contato in contatosProfissionaisCadastrados) {
                concatenaResultado += contato.getNome() + " - " + contato.getCelular() + " - " + contato.getEmail() + "\n"
            }
        }
        contatos.text = concatenaResultado
    }

    fun pesquisarContato(nomeInformado: String){
        var saida = "Não encontrado, cadastre e pesquise novamente."
        for (contato in contatosPessoaisCadastrados) {
            if (contato.getNome().toUpperCase() == nomeInformado.toUpperCase()) {
                saida =
                    "Nome: " + contato.getNome() + "\tCelular: " + contato.getCelular() + "\nReferência: " + contato.getReferencia()
            }
        }
        for (contato in contatosProfissionaisCadastrados) {
            if (contato.getNome().toUpperCase() == nomeInformado.toUpperCase()) {
                saida = "Nome: " + contato.getNome() + "\tCelular: " + contato.getCelular() + "\nEmail: " + contato.getEmail()
            }
        }
        contatos.text = saida
    }
}