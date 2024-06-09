package com.example.sccalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sccalculator.UiEvent.*
import com.example.sccalculator.ui.theme.Christine
import com.example.sccalculator.ui.theme.Pink40
import com.example.sccalculator.ui.theme.Purple40
import com.example.sccalculator.ui.theme.PurpleGrey40
import com.example.sccalculator.util.CalculatorButton


@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel){
    val input = viewModel.inputExpression.value
    val output = viewModel.output.value

    Column(modifier = Modifier.fillMaxSize()
        .padding(bottom = 40.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(2f)){
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
                Text(
                    text = input,
                    maxLines = 6,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.End
                    )
                )
                Text(
                    text = output,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 60.sp,
                        textAlign = TextAlign.End
                    )
                )
            }
        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )

        val buttonList = listOf(
            "sin","cos","tan","π",
            "ln", "log","e","√",
            "(",")","←","÷",
            "7","8","9","x",
            "4","5","6","+",
            "1","2","3","-",
            ".","0","=","+"
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            ){
            items(buttonList){
                CalculatorButton(
                    symbol = it,
                    color = getColor(it),
                    onClick = {
                    viewModel.onUiEvent(getUiEvent(it, viewModel))
                    },
                    onLongClick  = {
                        viewModel.onUiEvent(getLongInteraction(it))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}
fun getColor(symbol : String) : Color{
    if(symbol == "π" || symbol == "e"|| symbol == "√" || symbol == "sin" || symbol == "cos" || symbol == "tan" || symbol == "ln" || symbol == "log")
        return Color(PurpleGrey40.value)
    if(symbol == "=")
        return Color(0xFFF44336)
    if(symbol == "(" || symbol == ")" || symbol == "←")
        return Color(Pink40.value)
    if(symbol == "÷" || symbol == "x" || symbol == "+" || symbol == "-" )
        return Color(Christine.value)
    return Color(Purple40.value)
}

fun getUiEvent(symbol : String, viewModel: CalculatorViewModel) : UiEvent{
    val input = viewModel.inputExpression.value

    if(symbol == "0") return On0Click
    if(symbol == "1") return On1Click
    if(symbol == "2") return On2Click
    if(symbol == "3") return On3Click
    if(symbol == "4") return On4Click
    if(symbol == "5") return On5Click
    if(symbol == "6") return On6Click
    if(symbol == "7") return On7Click
    if(symbol == "8") return On8Click
    if(symbol == "9") return On9Click
    if(symbol == ".") return OnDotClick

    if(symbol == "sin") return OnSinClick
    if(symbol == "cos") return OnCosClick
    if(symbol == "tan") return OnTanClick
    if(symbol == "ln") return OnLnClick
    if(symbol == "log") return OnLogClick
    if(symbol == "π") return OnPiClick
    if(symbol == "e") return OnEClick
    if(symbol == "√") return OnSquareRootClick
    if(symbol == "(") return OnOpenBracketClick
    if(symbol == ")") return OnCloseBracketClick
    if(symbol == "←") return OnClearClick
    if(symbol == "÷") return OnDivideClick
    if(symbol == "x") return OnMultiplyClick
    if(symbol == "+") return OnAddClick
    if(symbol == "-") return OnSubtractClick

    if(symbol == "="){ if (input.isNotEmpty()) return OnEqualClick
                                                else return OnClearAllClick
    }

    return OnNoAction
}

fun getLongInteraction(symbol: String) : UiEvent{
    if(symbol == "←") return OnClearAllClick
   return OnNoAction
}