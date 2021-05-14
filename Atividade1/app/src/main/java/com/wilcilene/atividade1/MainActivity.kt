// Exibir uma mensagem com uso de variável.
// Os códigos comentados permitem incluir data de diferente formato e forma em outro campo.
// Não há tratamento de exception, ou aplicação de UX, ou padrões.

package com.wilcilene.atividade1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    lateinit var texto : TextView
    //    lateinit var hoje : TextView
    lateinit var dia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById(R.id.tv_texto_hw)
//        hoje = findViewById(R.id.tv_data)
        dia = findViewById(R.id.tv_dia)

        var nome : String = "Wilcilene"
        texto.text = mensagem(nome)
//        hoje.text = agora()
        agora2()
    }
    fun mensagem(p_nome : String) : String {
        var sobrenome: String = "Schratzenstaller"
        val hoje = Calendar.getInstance().time
        return "Bem vinda, $p_nome $sobrenome hoje é: "
    }
    /*    fun agora(): String {
            //val hoje = java.text.DateFormat.getDateInstance().format()
            val hoje = Calendar.getInstance().time
            return "$hoje"
        }*/
    fun agora2(){
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dia.text = dateTimeFormat.format(date)}
}
