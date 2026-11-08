package es.uva.inf5g.psm.hpapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.uva.inf5g.psm.hpapp.servicios.MusicService
import es.uva.inf5g.psm.hpapp.ui.theme.HPAppTheme
import es.uva.inf5g.psm.hpapp.vistas.HPApp

class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        startService(Intent(this, MusicService::class.java))
    }

    override fun onStop() {
        stopService(Intent(this, MusicService::class.java))
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HPAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HPApp(innerPadding)
                }
            }
        }
    }
}
