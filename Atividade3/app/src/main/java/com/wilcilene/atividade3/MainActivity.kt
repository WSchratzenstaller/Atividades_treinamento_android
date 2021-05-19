package com.wilcilene.atividade3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.util.*
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    private lateinit var nome:EditText
    private lateinit var nascimento:TextView
    private lateinit var presente:EditText
    private lateinit var cadastrar: Button
    private lateinit var mensagem:TextView
    private lateinit var nomeC:EditText
    private lateinit var consultar: Button
    private lateinit var listarTodos: Button
    private val MESES: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    var pessoasCadastradas = mutableListOf<Pessoa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nome = findViewById(R.id.editTextNome)
        nascimento = findViewById(R.id.textViewNascimento)
        presente = findViewById(R.id.editTextPresente)
        cadastrar = findViewById(R.id.buttonCadastrar)
        mensagem = findViewById(R.id.textViewMensagem)
        nomeC = findViewById(R.id.editTextNomeC)
        consultar = findViewById(R.id.buttonConsultar)
        listarTodos = findViewById(R.id.buttonListarTodos)

        //calendario
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //acessar
        nascimento.setOnClickListener {
            val escolha = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view: DatePicker?, mAno: Int, mMes: Int, mDia: Int ->
                        nascimento.setText("" + mDia + "/" + MESES[mMes] + "/" + mAno)
                    },
                    ano,
                    mes,
                    dia
            )
            escolha.show()
        }

        cadastrar.setOnClickListener {
            val nomeInformado = nome.text.toString()
            val dataInformada = nascimento.text.toString()
            val presenteInformado = presente.text.toString()

            if (nomeInformado.isNotEmpty()) {
                if (dataInformada.isNotEmpty()) {
                    if (presenteInformado.isNotEmpty()) {
                        adicionarNaLista(nomeInformado, dataInformada, presenteInformado)
                        nome.text = null
                        nascimento.text = null
                        presente.text = null
                    }
                    else presente.error = "Informe um presente."
                }
                else nascimento.error = "Informe uma Data de nascimento."
            }
            else nome.error = "Informe um nome."
        }
        consultar.setOnClickListener {
            val nomeConsultado = nomeC.text.toString()
            if (nomeConsultado.isNotEmpty()) {
                mensagem.text = mensagemConsulta(nomeConsultado)
            } else nomeC.error = "Informe um nome para consultar."
        }

        listarTodos.setOnClickListener {
            exibirLista()
        }
    }

    fun adicionarNaLista(nomeInformado: String, dataInformada: String, presenteInformado: String) {
        var pessoa = Pessoa()

        pessoa.nome = nomeInformado
        pessoa.nascimento = LocalDate.parse(dataInformada, DateTimeFormatter.ofPattern("d/M/yyyy"))
        pessoa.presente = presenteInformado

        pessoasCadastradas.add(pessoa)
        exibirLista()
    }

    fun exibirLista(){
        var concatenaResultado = "Nome\t - Nascimento\t - Presente\n"
        for (p in pessoasCadastradas) {
            concatenaResultado+= p.nome + "\t - " + p.nascimento + "\t - " + p.presente + "\n"
        }
        mensagem.text = concatenaResultado
    }

    fun mensagemConsulta(nomeInformado: String): CharSequence? {
        for (p in pessoasCadastradas) {
            if (p.nome.toUpperCase() == nomeInformado.toUpperCase()) {
                val hoje = LocalDate.now()
                var proximoAniversario =
                        LocalDate.of(
                                hoje.year,
                                p.nascimento.month,
                                p.nascimento.dayOfMonth
                        )
                var x = ChronoUnit.DAYS.between(hoje, proximoAniversario)
                if (x.toInt() <= 0) {
                    proximoAniversario = LocalDate.of(
                            hoje.year + 1,
                            p.nascimento.month,
                            p.nascimento.dayOfMonth
                    )
                    x = ChronoUnit.DAYS.between(hoje, proximoAniversario)
                }
                return "Olá, faltam $x dias para o aniversário de ${p.nome} ela(e) deseja um(a) ${p.presente}!!"
            }
        }
        return "$nomeInformado não encontrada(o) na lista. Se deseja consultar no futuro, cadastre essa pessoa."
    }
}