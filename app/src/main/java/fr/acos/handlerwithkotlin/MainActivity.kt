package fr.acos.handlerwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    var handler = MonHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("XXX","GO")
    }

    /**
     * Fonction contenant le traitement long numéro 1.
     */
    fun onClickTraitementLong1(view: View)
    {
        Log.i("XXX","onClickTraitementLong1")

        Thread({
            val msgDebut = Message()
            msgDebut.what = 1
            msgDebut.arg1 = 1
            //Envoi d'un message au handler pour lui indiquer que le traitement commence
            handler.sendMessage(msgDebut)

            //Execute 20 fois
            for (i in 1..20)
            {
                //Fait dormir le thread contenant le traitement long
                Thread.sleep(1000)
                if(i == 10) {
                    val msgMiChemin = Message()
                    msgMiChemin.what = 2
                    msgMiChemin.arg1 = 1
                    //Envoi d'un message au handler pour lui indiquer que le traitement est à la moitié de son exécution
                    handler.sendMessage(msgMiChemin)
                }
            }
            val msgFin = Message()
            msgFin.what = 3
            msgFin.arg1 = 1
            //Envoi d'un message au handler pour lui indiquer que le traitement est terminé
            handler.sendMessage(msgFin)
        }).start()
    }

    /**
     * Fonction contenant le traitement long numéro 2.
     */
    fun onClickTraitementLong2(view: View)
    {
        Log.i("XXX","onClickTraitementLong2")
        Thread({
            val msgDebut = Message()
            msgDebut.what = 1
            msgDebut.arg1 = 2
            //Envoi d'un message au handler pour lui indiquer que le traitement commence
            handler.sendMessage(msgDebut)
            //Execute 15 fois
            for (i in 1..15)
            {
                //Fait dormir le thread contenant le traitement long
                Thread.sleep(1000)
                if(i == 8) {
                    val msgMiChemin = Message()
                    msgMiChemin.what = 2
                    msgMiChemin.arg1 = 2
                    //Envoi d'un message au handler pour lui indiquer que le traitement est à la moitié de son exécution
                    handler.sendMessage(msgMiChemin)
                }
            }
            val msgFin = Message()
            msgFin.what = 3
            msgFin.arg1 = 2
            //Envoi d'un message au handler pour lui indiquer que le traitement est terminé
            handler.sendMessage(msgFin)
        }).start()
    }

    /**
     * Fonction contenant le traitement long  numéro 3.
     */
    fun onClickTraitementLong3(view: View)
    {
        Log.i("XXX","onClickTraitementLong3")
        Thread({
            val msgDebut = Message()
            msgDebut.what = 1
            msgDebut.arg1 = 3
            //Envoi d'un message au handler pour lui indiquer que le traitement commence
            handler.sendMessage(msgDebut)
            //Execute 10 fois
            for (i in 1..10)
            {
                //Fait dormir le thread contenant le traitement long
                Thread.sleep(1000)
                if(i == 5) {
                    val msgMiChemin = Message()
                    msgMiChemin.what = 2
                    msgMiChemin.arg1 = 3
                    //Envoi d'un message au handler pour lui indiquer que le traitement est à la moitié de son exécution
                    handler.sendMessage(msgMiChemin)
                }
            }
            val msgFin = Message()
            msgFin.what = 3
            msgFin.arg1 = 3
            //Envoi d'un message au handler pour lui indiquer que le traitement est terminé
            handler.sendMessage(msgFin)
        }).start()
    }

    /**
     * Fonction permettant de voir la réactivité de l'IHM
     */
    fun onClickCoucou(view: View)
    {
        //Affichage d'un Toast
        Toast.makeText(this,"Coucou", Toast.LENGTH_LONG).show();
    }


    fun information(message:String)
    {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    inner class MonHandler : Handler()
    {
        override fun handleMessage(msg: Message) {
            Log.i("XXX","Wololo ${msg.what}")
            super.handleMessage(msg)
            when (msg.what)
            {
                1 -> information("Un traitement long numéro ${msg.arg1} vient de commencer")
                2 -> information("le traitement long numéro ${msg.arg1} est à mi-chemin")
                3 -> information("Un traitement long numéro ${msg.arg1} est terminé")
            }
        }
    }
}
