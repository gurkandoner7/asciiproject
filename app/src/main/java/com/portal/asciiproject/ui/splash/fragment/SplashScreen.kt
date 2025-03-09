import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portal.asciiproject.R
import com.portal.asciiproject.ui.montserratFontFamily
import com.portal.asciiproject.ui.splash.viewmodel.SplashScreenViewModel
import com.portal.asciiproject.utilities.customviews.CustomButton

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    onLanguageSelected: (String) -> Unit,
    onGetStartedClicked: () -> Unit
) {
    val context = LocalContext.current
    var selectedLanguage by remember {
        mutableStateOf(viewModel.getLanguageFromSharedPreferences(context))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 324.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.teaser_coffee_text),
                style = TextStyle(color = Color.White, fontSize = 24.sp),
                fontFamily = montserratFontFamily,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.the_best_grain),
                style = TextStyle(color = Color.LightGray, fontSize = 16.sp),
                fontFamily = montserratFontFamily,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )
        }

        CustomRadioButtonGroup(
            options = listOf("EN", "TR"),
            selectedOption = selectedLanguage.uppercase(),
            onOptionSelected = {
                selectedLanguage = it.lowercase()
                onLanguageSelected(it.lowercase())
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 32.dp)
        )

        CustomButton(
            onClick = onGetStartedClicked,
            hintText = stringResource(id = R.string.get_started),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 64.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        viewModel = SplashScreenViewModel(),
        onLanguageSelected = {},
        onGetStartedClicked = {})
}