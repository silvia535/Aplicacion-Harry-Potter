package es.uva.inf5g.psm.hpapp.vistas


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.uva.inf5g.psm.hpapp.HogwartsBlue
import es.uva.inf5g.psm.hpapp.R

@Composable
fun TitleScreen(
    onNavigateToMainMenu: () -> Unit
) {
    val harryPotterFont = remember {
        FontFamily(Font(R.font.harry_potter_font))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HogwartsBlue)
            .clickable { onNavigateToMainMenu() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(R.drawable.hp_logo),
                contentDescription = "Harry Potter Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pulsa para empezar",
                fontSize = 28.sp,
                fontFamily = harryPotterFont,
                color = Color.White
            )
        }
    }
}
