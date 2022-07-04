package com.example.hikayezarlari

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var nesneler: List<String>
    private lateinit var fiiller: List<String>
    private lateinit var hedefler: List<String>
    private lateinit var roller: List<String>
    private lateinit var isimler: List<String>
    private lateinit var yuklemler: List<String>
    private lateinit var sifatlar: List<String>
    private lateinit var iliskiler: List<String>
    private lateinit var aciklama: List<String>
    private lateinit var sohbetler: List<String>
    private lateinit var beklenmedik: List<String>
    private lateinit var korkelime: List<String>
    private lateinit var kodkelime: List<String>
    private lateinit var kadın: List<String>
    private lateinit var erkek: List<String>
    private lateinit var kelime: List<String>

    private var surge = 0

    private var ihtimal : List<Int> = listOf(60,70,90)
    private var spinnertext = "Giriş"



    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nesneler = application.assets.open("nesne.txt").bufferedReader().readLines()
        fiiller = application.assets.open("Fiiller.txt").bufferedReader().readLines()
        hedefler = application.assets.open("characterGoal.txt").bufferedReader().readLines()
        roller = application.assets.open("characterRole.txt").bufferedReader().readLines()
        isimler = application.assets.open("isimler.txt").bufferedReader().readLines()
        yuklemler = application.assets.open("yuklem.txt").bufferedReader().readLines()
        sifatlar = application.assets.open("Sıfatlar.txt").bufferedReader().readLines()
        iliskiler = application.assets.open("İlişki.txt").bufferedReader().readLines()
        aciklama = application.assets.open("characterDescriptor.txt").bufferedReader().readLines()
        sohbetler = application.assets.open("Sohbet.txt").bufferedReader().readLines()
        beklenmedik = application.assets.open("Unexpectedly.txt").bufferedReader().readLines()
        korkelime = application.assets.open("KorKelimeler.txt").bufferedReader().readLines()
        kodkelime = application.assets.open("KodKelimeler.txt").bufferedReader().readLines()
        erkek = application.assets.open("erkek.txt").bufferedReader().readLines()
        kadın = application.assets.open("kadın.txt").bufferedReader().readLines()
        kelime = application.assets.open("kelime.txt").bufferedReader().readLines()


        handle_spinner()

        handle_buttons()


        findViewById<TextView>(R.id.anatext).text = SimpleDateFormat("yyyy.MM.dd'\n'HH.mm").format(Date())
        findViewById<TextView>(R.id.anabaslik).text = "Hikaye Zarları:"

    }

    @SuppressLint("SetTextI18n")
    fun handle_buttons(){

        val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        findViewById<TextView>(R.id.tema).setOnClickListener {
            vibe.vibrate(100)
            val text = "${yuklemler.random().lowercase()}, ${nesneler.random().lowercase()}"
            findViewById<TextView>(R.id.anatext).text = text
            findViewById<TextView>(R.id.anabaslik).text = "Tema + Hareket:"
        }

        findViewById<TextView>(R.id.cumle).setOnClickListener {
            vibe.vibrate(100)
            val p1 = Random.nextBoolean()
            val p2 = Random.nextBoolean()

            val text1 = if (p1) isimler.random() else nesneler.random()
            val text2 = if (p2) fiiller.random() else yuklemler.random()

            val text = "${sifatlar.random()} $text1 $text2"
            findViewById<TextView>(R.id.anatext).text = text
            findViewById<TextView>(R.id.anabaslik).text = "Saçma Cümle:"
        }

        findViewById<TextView>(R.id.uckelime).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextBoolean()
            val text1 = if (p) kelime.random() else kodkelime.random()

            val text = "${korkelime.random()}, ${isimler.random()}, $text1"
            findViewById<TextView>(R.id.anatext).text = text.lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Üç Kelime:"
        }

        findViewById<TextView>(R.id.sohbet).setOnClickListener {
            vibe.vibrate(100)
            findViewById<TextView>(R.id.anatext).text = edit(sohbetler.random()).lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Sohbet:"
        }

        findViewById<TextView>(R.id.unlem).setOnClickListener {
            vibe.vibrate(100)
            findViewById<TextView>(R.id.anatext).text = edit(beklenmedik.random()).lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "!!!"
        }

        findViewById<TextView>(R.id.rol).setOnClickListener {
            vibe.vibrate(100)
            findViewById<TextView>(R.id.anatext).text = roller.random().lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Rolü:"
        }

        findViewById<TextView>(R.id.hedef).setOnClickListener {
            vibe.vibrate(100)
            findViewById<TextView>(R.id.anatext).text = hedefler.random().lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Hedefi:"
        }

        findViewById<TextView>(R.id.vasıf).setOnClickListener {
            vibe.vibrate(100)
            findViewById<TextView>(R.id.anatext).text = aciklama.random().lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Vasfı:"
        }

        findViewById<TextView>(R.id.tamcumle).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextBoolean()
            val text1 = if (p) "istiyor" else "peşinde"

            val text = "${aciklama.random()} ${roller.random()}, ${hedefler.random()} $text1"
            findViewById<TextView>(R.id.anatext).text = text.lowercase()
            findViewById<TextView>(R.id.anabaslik).text = "Tam Cümle:"
        }

        findViewById<TextView>(R.id.ad).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextBoolean()
            findViewById<TextView>(R.id.anatext).text = if(p) erkek.random() else kadın.random()
            findViewById<TextView>(R.id.anabaslik).text = "Adı:"
        }

        findViewById<TextView>(R.id.kesin).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextInt(100)
            val text1 = if(p<10) "Hayır" else "Evet"
            val text2 = text2()

            findViewById<TextView>(R.id.anatext).text = text1 + text2
            findViewById<TextView>(R.id.anabaslik).text = "$spinnertext + Kesin:"
        }

        findViewById<TextView>(R.id.olasi).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextInt(100)
            val text1 = if(p<25) "Hayır" else "Evet"
            val text2 = text2()

            findViewById<TextView>(R.id.anatext).text = text1 + text2
            findViewById<TextView>(R.id.anabaslik).text = "$spinnertext + Olası:"
        }

        findViewById<TextView>(R.id.ortada).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextInt(100)
            val text1 = if(p<50) "Hayır" else "Evet"
            val text2 = text2()

            findViewById<TextView>(R.id.anatext).text = text1 + text2
            findViewById<TextView>(R.id.anabaslik).text = "$spinnertext + Ortada:"
        }

        findViewById<TextView>(R.id.dusuk).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextInt(100)
            val text1 = if(p<75) "Hayır" else "Evet"
            val text2 = text2()

            findViewById<TextView>(R.id.anatext).text = text1 + text2
            findViewById<TextView>(R.id.anabaslik).text = "$spinnertext + Düşük İhtimal:"
        }

        findViewById<TextView>(R.id.imkansiz).setOnClickListener {
            vibe.vibrate(100)
            val p = Random.nextInt(100)
            val text1 = if(p<90) "Hayır" else "Evet"
            val text2 = text2()

            findViewById<TextView>(R.id.anatext).text = text1 + text2
            findViewById<TextView>(R.id.anabaslik).text = "$spinnertext + İmkansız:"
        }
    }

    fun text2(): String{

        val p = Random.nextInt(100) + surge

        val text = if (p<ihtimal[0]){
            surge +=2
            ""
        }else if (p<ihtimal[1]){
            surge = 0
            " ve"
        }else if (p<ihtimal[2]){
            surge = 0
            " ama"
        }else{
            surge = 0
            "!!!"
        }

        return text
    }

    fun edit(giris:String): String {

        var text = giris

        if (text.contains("[")) {
            val startindex = text.indexOf("[")
            val endindex = text.indexOf("]")

            if (text.substring(startindex + 1, endindex) == "iliski") {
                text = text.substring(0,startindex) + iliskiler.random() + text.substring(endindex+1,text.length)
                return text
            }
            if (text.substring(startindex + 1, endindex) == "characterDescriptor") {
                text = text.substring(0,startindex) + aciklama.random() + text.substring(endindex+1,text.length)
                return text
            }
            if (text.substring(startindex + 1, endindex) == "KorKelimeler") {
                text = text.substring(0,startindex) + korkelime.random() + text.substring(endindex+1,text.length)
                return text
            }
        }
        return text
    }

    fun handle_spinner(){
        val spiner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.bolumler, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.adapter = adapter

        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                ihtimal = when (position) {
                    0 -> listOf(60,70,90)
                    1 -> listOf(68,88,96)
                    else -> listOf(60,96,98)
                }

                spinnertext = when(position){
                    0 -> "Giriş"
                    1 -> "Gelişme"
                    else -> "Sonuç"
                }

                findViewById<TextView>(R.id.spinnertext).text = spinnertext
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}