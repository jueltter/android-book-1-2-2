package ec.dev.samagua.android_book_1_2_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ec.dev.samagua.android_book_1_2_2.ui.theme.Androidbook122Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidbook122Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SaveRestoreScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SaveRestoreScreen(modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var discountCodeConfirmation by remember { mutableStateOf("") }
    var discountCode by remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val (headerText, firstNameField, lastNameField, emailField, discountButton, discountConfirmationText, discountCodeText) = createRefs()
        val guideline = createGuidelineFromStart(0.5f) // 50% of screen width

        Text(
            text = stringResource(R.string.header_text),
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .constrainAs(headerText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(text = stringResource(R.string.first_name_label)) },
            modifier = Modifier
                .padding(start = 24.dp)
                .constrainAs(firstNameField) {
                    start.linkTo(parent.start)
                    end.linkTo(guideline)
                    top.linkTo(headerText.bottom, margin = 16.dp)

                }
                .fillMaxWidth(0.45f), // Takes up to 45% of width

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 20.sp),
            singleLine = true
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = stringResource(R.string.last_name_label)) },
            modifier = Modifier
                .padding(end = 24.dp)
                .constrainAs(lastNameField) {
                    start.linkTo(guideline)
                    end.linkTo(parent.end)
                    top.linkTo(firstNameField.top)
                }
                .fillMaxWidth(0.45f), // Takes up to 45% of width,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 20.sp),
            singleLine = true

        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email_label)) },
            modifier = Modifier
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .constrainAs(emailField) {
                    top.linkTo(firstNameField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(fontSize = 20.sp),
            singleLine = true

        )

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,  // button color
                contentColor = Color.White  // Text color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
                .constrainAs(discountButton) {
                    top.linkTo(emailField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = stringResource(R.string.discount_code_button),
                fontSize = 20.sp
            )

        }

        Text(
            text = discountCodeConfirmation,
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .constrainAs(discountConfirmationText) {
                    top.linkTo(discountButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = discountCode,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .constrainAs(discountCodeText) {
                    top.linkTo(discountConfirmationText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


    }
}

@Preview(showBackground = true)
@Composable
fun SaveRestoreScreenPreview() {
    Androidbook122Theme {
        SaveRestoreScreen()
    }
}