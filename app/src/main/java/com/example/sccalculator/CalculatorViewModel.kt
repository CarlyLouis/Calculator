package com.example.sccalculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel:ViewModel() {
    val inputExpression = mutableStateOf("")
    val output = mutableStateOf("0")
    val evaluator = MathExpressionEvaluator()


    fun onUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.On0Click -> updateInput("0")
            is UiEvent.On1Click -> updateInput("1")
            is UiEvent.On2Click -> updateInput("2")
            is UiEvent.On3Click -> updateInput("3")
            is UiEvent.On4Click -> updateInput("4")
            is UiEvent.On5Click -> updateInput("5")
            is UiEvent.On6Click -> updateInput("6")
            is UiEvent.On7Click -> updateInput("7")
            is UiEvent.On8Click -> updateInput("8")
            is UiEvent.On9Click -> updateInput("9")
            is UiEvent.OnTanClick -> updateInput(" tan(")
            is UiEvent.OnOpenBracketClick -> updateInput("(")
            is UiEvent.OnCloseBracketClick -> updateInput(")")
            is UiEvent.OnSinClick -> updateInput("sin(")
            is UiEvent.OnCosClick -> updateInput("cos(")
            is UiEvent.OnDotClick -> updateInput(".")
            is UiEvent.OnEClick -> updateInput("e")
            is UiEvent.OnLnClick -> updateInput("ln(")
            is UiEvent.OnLogClick -> updateInput("log(")
            is UiEvent.OnPiClick -> updateInput("π")
            is UiEvent.OnSquareRootClick -> updateInput("√(")

            is UiEvent.OnAddClick -> updateInput("+")
            is UiEvent.OnDivideClick -> updateInput("/")
            is UiEvent.OnMultiplyClick -> updateInput("x")
            is UiEvent.OnSubtractClick -> updateInput("-")

            is UiEvent.OnClearClick -> clearInput()
            is UiEvent.OnClearAllClick -> clearAll()
            is UiEvent.OnEqualClick -> calculate()
            is UiEvent.OnNoAction -> noChange()
        }
    }

    private fun updateInput(character: String) {
        if (output.value != "0" && character in "(eπ+-x/" ){
            inputExpression.value = output.value + character
            output.value = "0"
        }

        else if (inputExpression.value.isEmpty( )&& character in "0+x/-") inputExpression.value = ""

        else if (inputExpression.value.isNotEmpty() &&
            inputExpression.value.last() == '(' && character in "/+x")
            inputExpression.value == inputExpression.value

        else if (inputExpression.value.isNotEmpty() &&
            inputExpression.value.last() == '.' &&
            character !in "0123456789")
            inputExpression.value == inputExpression.value



        else if (inputExpression.value.isNotEmpty() &&
            inputExpression.value.last() in "/+x-" &&
            character in "/+x-")
            inputExpression.value = inputExpression.value.dropLast(1) + character


        else inputExpression.value += character
    }

    private fun clearInput() {
        if(inputExpression.value.endsWith("sin(") ||
            inputExpression.value.endsWith("cos(") ||
            inputExpression.value.endsWith("tan(") ||
            inputExpression.value.endsWith("log(")
            )
            inputExpression.value= inputExpression.value.dropLast(4)
        else if (inputExpression.value.endsWith("ln(")
                )
            inputExpression.value= inputExpression.value.dropLast(3)
        else if (inputExpression.value.endsWith("√(")
        )
            inputExpression.value= inputExpression.value.dropLast(2)

        else
            inputExpression.value= inputExpression.value.dropLast(1)
    }

    private fun calculate(){
        var result = evaluator.evaluate(inputExpression.value) // Call evaluate on the instance
        //return result  // Return the calculated result
        if (result.endsWith(".0")){ result = result.replace(".0", "") }
        output.value = result
    }

    private fun clearAll() {
        inputExpression.value = ""
        output.value = "0"
    }

    private fun noChange() {
        inputExpression.value = inputExpression.value
        output.value = output.value
    }
}