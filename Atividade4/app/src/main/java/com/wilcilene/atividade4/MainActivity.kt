package com.wilcilene.atividade4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var informarPosicao : EditText
    private lateinit var buscarValor : Button
    private lateinit var exibirValor : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        informarPosicao = findViewById(R.id.editTextPosicao)
        buscarValor = findViewById(R.id.buttonBuscarValor)
        exibirValor = findViewById(R.id.textViewValor)

        buscarValor.setOnClickListener(){
            var posicaoInformada = informarPosicao.text.toString()
            if (posicaoInformada.isNotEmpty()){
                exibirValor.text = buscarValorFibonacci(posicaoInformada.toInt()).toString()
                informarPosicao.text = null
            }
            else informarPosicao.error = "Informe posição."
        }
    }

    fun buscarValorFibonacci(posicao : Int) : Int {
        if (posicao < 2) {
            return posicao
        }
        return buscarValorFibonacci(posicao - 1) + buscarValorFibonacci(posicao - 2)
    }
}