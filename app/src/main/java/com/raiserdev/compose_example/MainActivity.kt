package com.raiserdev.compose_example

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raiserdev.compose_example.data.SampleData

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                //MessageCard(SampleData.Message("Alejandro", "Bautista"))
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }

    @Composable
    fun Conversation(messages: List<SampleData.Message>){
        LazyColumn{
            items(messages) {
                message ->
                MessageCard(msg = message)
            }
        }
    }

    @Composable
    fun MessageCard( msg : SampleData.Message){
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

            var isExpanded by remember{ mutableStateOf(false)}
            val surfaceColor : Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondary
            )
            Column(modifier = Modifier.clickable { isExpanded =! isExpanded }){
                Text(
                    text = msg.title,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        color = Color.Black,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
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
            MessageCard(msg = SampleData.Message("Otro Alex", "Otro LN"))
        }
    }

    @Preview
    @Composable
    fun PreviewConversation(){
        MaterialTheme{
            Conversation(messages = SampleData.conversationSample)
        }
    }
}

