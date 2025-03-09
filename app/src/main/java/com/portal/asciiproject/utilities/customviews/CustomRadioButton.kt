import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portal.asciiproject.R

@Composable
fun CustomRadioButton(
    selected: Boolean, onClick: () -> Unit, text: String
) {
    val backgroundColor =
        if (selected) colorResource(id = R.color.ascii_brown_button) else Color.Transparent
    val textColor = Color.White
    val borderColor = colorResource(id = R.color.white)

    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(30.dp)
            .background(
                color = backgroundColor, shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun CustomRadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        options.forEach { option ->
            CustomRadioButton(
                selected = selectedOption.equals(option, ignoreCase = true),
                onClick = { onOptionSelected(option) },
                text = option
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomRadioButtonGroupPreview() {
    var selectedLanguage by remember { mutableStateOf("EN") }
    val options = listOf("EN", "TR")

    CustomRadioButtonGroup(
        options = options,
        selectedOption = selectedLanguage,
        modifier = Modifier,
        onOptionSelected = { selectedLanguage = it })
}