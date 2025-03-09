package com.portal.asciiproject.utilities.customviews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.portal.asciiproject.R
import com.portal.asciiproject.ui.customTextStyle

@Composable
fun CustomButton(onClick: () -> Unit, hintText: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .background(
                color = colorResource(R.color.ascii_brown_button), shape = RoundedCornerShape(4.dp)
            )
            .padding(ButtonDefaults.ContentPadding)
    ) {
        Text(
            text = hintText,
            color = Color.White,
            style = customTextStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(
        onClick = {}, hintText = "Başlayalım", modifier = Modifier
    )
}