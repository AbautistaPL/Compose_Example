package com.raiserdev.compose_example

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                MessageCard(Message("Alejandro", "Bautista"))
            }
        }
    }

    data class  Message(val author: String, val lastName : String)

    @Composable
    fun MessageCard( msg : Message){
        Row(
            modifier = Modifier.padding(all = 8.dp)
        )
        {

            Image(
                painter =  painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = "Imagen de ejemplo",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .align(CenterVertically)
                    .padding(2.0.dp)
                    .border(
                        2.5.dp,
                        MaterialTheme.colors.secondary,
                        CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))
            
            Column{
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp, color = Color.Cyan) {
                    Text(
                        text = msg.lastName,
                        color = Color.Black,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp)
                    )
                }

            }
        }


    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewCard(){
        MaterialTheme() {
            MessageCard(msg = Message("Otro Alex", "Otro LN"))
        }
    }
}

