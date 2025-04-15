// MyCFunc.kt

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun MutableStateInsideComposableWithoutRemember() {
    val checkedState = mutableStateOf(false) // Убрали remember
    val checkedValue = checkedState.value

    Column {
        Row(verticalAlignment = Alignment.Top) {
            Checkbox(
                checked = checkedValue,
                onCheckedChange = { value -> checkedState.value = value }
            )
            //пересобирается функция, checkedState изменил свое значение !!!
            Text(text = "Текст ...", fontSize = 18.sp)
        }

        if (checkedValue) {
            Text(text = "Много какого-то текста ...")
        }
    }
}
