package com.example.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var celular:EditText
    private lateinit var groupOpcoes: RadioGroup
    private lateinit var radioPessoal: RadioButton
    private lateinit var radioProfissional: RadioButton
    private lateinit var referencia:EditText
    private lateinit var email:EditText
    private lateinit var btnSalvar: Button
    private lateinit var pesquisar:EditText
    private lateinit var btnPesquisar:ImageButton
    private lateinit var contatos:TextView
    private lateinit var btnContatos:Button
    private lateinit var tipoContato: String
    var contatosPessoaisCadastrados = mutableListOf<Pessoal>()
    var contatosProfissionaisCadastrados = mutableListOf<Profissional>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
    }
    private fun bindViews() {
        nome = findViewById(R.id.editTextNome)
        celular = findViewById(R.id.editTextCelular)
        groupOpcoes = findViewById(R.id.radioGroupOpcoes)
        radioPessoal = findViewById(R.id.radioButtonPessoal)
        radioProfissional = findViewById(R.id.radioButtonProfissional)
        referencia = findViewById(R.id.editTextReferencia)
        email = findViewById(R.id.editTextEmail)
        btnSalvar = findViewById(R.id.buttonSalvar)
        pesquisar = findViewById(R.id.editTextPesquisar)
        btnPesquisar = findViewById(R.id.imageButtonPesquisar)
        contatos = findViewById(R.id.textViewContatos)
        btnContatos = findViewById(R.id.buttonContatos)

        groupOpcoes.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonPessoal){
                tipoContato = "Pessoal"
                email.visibility = View.INVISIBLE
                referencia.visibility = View.VISIBLE}
            if (checkedId == R.id.radioButtonProfissional){
                tipoContato = "Profissional"
                referencia.visibility = View.INVISIBLE
                email.visibility = View.VISIBLE}
        }

        btnSalvar.setOnClickListener {
            val nomeInformado = nome.text.toString()
            val celularInformado = celular.text.toString()
            val tipoContatoInformado = tipoContato
            if (nomeInformado.isNotEmpty()) {
                if (celularInformado.isNotEmpty()) {
                    if (tipoContatoInformado == "Pessoal") {
                        var referenciaInformada = referencia.text.toString()
                        contatosPessoaisCadastrados.add(Pessoal(nomeInformado,celularInformado,referenciaInformada))
                        referencia.text = null
                    }
                    if (tipoContatoInformado == "Profissional") {
                        var emailInformado = email.text.toString()
                        contatosProfissionaisCadastrados.add(Profissional(nomeInformado,celularInformado,emailInformado))
                        email.text = null
                    }
                    nome.text = null
                    celular.text = null
                }
                else celular.error = "Informe um celular."
            }
            else nome.error = "Informe um nome."

            exibirLista()
        }
    }

    private fun exibirLista() {
        var concatenaResultado = "Nome\t - Celular\t - E-mail ou referência\n"
        for (c in contatosPessoaisCadastrados) {
            //if (c.javaClass.name == "Pessoal" )
            concatenaResultado+= c.getNome() + "\t - " + c.getCelular() + "\t - " + c.getReferencia() + "\n"
        }
        for (c in contatosProfissionaisCadastrados) {
            concatenaResultado+= c.getNome() + "\t - " + c.getCelular() + "\t - " + c.getEmail() + "\n"
        }
        contatos.text = concatenaResultado
    }
}