package com.wilcilene.atividade2


import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    private lateinit var nome:EditText
    private lateinit var nascimento:TextView
    private lateinit var presente:EditText
    private lateinit var ok: Button
    private lateinit var mensagem:TextView
    private val MESES: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nome = findViewById(R.id.editTextNome)
        nascimento = findViewById(R.id.textViewNascimento)
        presente = findViewById(R.id.editTextPresente)
        ok = findViewById(R.id.buttonOk)
        mensagem = findViewById(R.id.textViewMensagem)

        //calendario
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //acessar
        nascimento.setOnClickListener{
            val escolha = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view: DatePicker?, mAno: Int, mMes: Int, mDia: Int ->
                nascimento.setText("" + mDia + "/" + MESES[mMes] + "/"+mAno)
            },ano,mes,dia)
            escolha.show()
        }

        ok.setOnClickListener{
            val nomeInformado = nome?.text.toString()
            val dataInformada = nascimento?.text.toString()
            val presenteInformado = presente?.text.toString()

            mensagem.text = escreverMensagem(nomeInformado,dataInformada, presenteInformado)
        }
    }

    fun escreverMensagem(nomeInformado: String, dataInformada: String, presenteInformado: String): CharSequence? {
        val hoje = LocalDate.now()
        val l = LocalDate.parse(dataInformada, DateTimeFormatter.ofPattern("d/M/yyyy"))
        var proximoAniversario = LocalDate.of(hoje.year, l.month, l.dayOfMonth)
        var x = ChronoUnit.DAYS.between(hoje, proximoAniversario)

        if (x.toInt()<=0){
            proximoAniversario = LocalDate.of(hoje.year+1, l.month, l.dayOfMonth)
            x = ChronoUnit.DAYS.between(hoje, proximoAniversario)
        }

        return "Olá, $nomeInformado, faltam $x dias para o seu aniversário! Esperamos que você ganhe um(a) $presenteInformado!!"
    }
}
