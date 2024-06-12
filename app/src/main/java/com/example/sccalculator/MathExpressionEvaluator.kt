package com.example.sccalculator

import kotlin.math.*

class MathExpressionEvaluator {

    private val pi = (355/113).toDouble()

    fun evaluate(expression: String): String {
        return try {
            calculate(expression).toString()
        } catch (e: Exception) {
            "Error"
        }
    }

    private fun calculate(expression: String): Double {
        val postfix = infixToPostfix(expression)
        val stack = mutableListOf<Double>()

        for (token in postfix) {
            when {
                token.isNumeric() -> stack.push(token.toDouble())
                token.isConstant() -> stack.push(token.toConstantValue())
                token.isOperator() || token.isFunction() -> {
                    when (token) {
                        "+" -> {
                            val b = stack.pop()
                            val a = stack.pop()
                            stack.push(a + b)
                        }
                        "-" -> {
                            val b = stack.pop()
                            val a = stack.pop()
                            stack.push(a - b)
                        }
                        "x" -> {
                            val b = stack.pop()
                            val a = stack.pop()
                            stack.push(a * b)
                        }
                        "/" -> {
                            val b = stack.pop()
                            val a = stack.pop()
                            stack.push(a / b)
                        }
                        "^" -> {
                            val b = stack.pop()
                            val a = stack.pop()
                            stack.push(a.pow(b))
                        }
                        "sin" ->{
                            val y = sin(stack.pop())
                            val z = y.toString()
                            if (z.contains(".0")||z.contains(".1")||
                                z.contains(".9")||z.contains(".2")){
                            stack.push(Math.round(y).toDouble())
                            }
                            else stack.push(y)
                        }
                        "cos" ->{
                            val y = cos(stack.pop())
                            val z = y.toString()
                            if (z.contains(".0")||z.contains(".1")||
                                z.contains(".9")||z.contains(".2")){
                                stack.push(Math.round(y).toDouble())
                            }
                            else stack.push(y)
                        }
                        "tan" ->{
                            val y = tan(stack.pop())
                            val z = y.toString()
                            if (z.contains(".0")||z.contains(".1")||
                                z.contains(".9")||z.contains(".2")){
                                stack.push(Math.round(y).toDouble())
                            }
                            else stack.push(y)
                        }
                        "log" -> stack.push(log10(stack.pop()))
                        "ln" -> stack.push(ln(stack.pop()))
                        "√" -> stack.push(sqrt(stack.pop()))
                        else -> throw IllegalArgumentException("Invalid operator or function: $token")
                    }
                }
            }
        }

        return stack.pop()
    }

    private fun infixToPostfix(expression: String): List<String> {
        val output = mutableListOf<String>()
        val stack = mutableListOf<String>()

        val tokens = expression.split(Regex("(?<=[+\\-x/^()])|(?=[+\\-x/^()])|(?<=\\b(cos|sin|tan|log|ln|√|π|e)\\b)|(?=\\b(cos|sin|tan|log|ln|√|π|e)\\b)"))

        for (token in tokens) {
            when {
                token.isNumeric() -> output.add(token)
                token.isConstant() -> output.add(token)
                token.isFunction() -> stack.push(token)
                token == "(" -> stack.push(token)
                token == ")" -> {
                    while (stack.isNotEmpty() && stack.last() != "(") {
                        output.add(stack.pop())
                    }
                    if (stack.isNotEmpty() && stack.last() == "(") {
                        stack.pop() // Discard the opening parenthesis
                    }
                    if (stack.isNotEmpty() && stack.last().isFunction()) {
                        output.add(stack.pop()) // Pop function if exists before '('
                    }
                }
                token.isOperator() -> {
                    while (stack.isNotEmpty() && stack.last().isOperator() &&
                        (precedence(token) <= precedence(stack.last()))
                    ) {
                        output.add(stack.pop())
                    }
                    stack.push(token)
                }
            }
        }

        while (stack.isNotEmpty()) {
            output.add(stack.pop())
        }

        return output
    }

    private fun String.isNumeric(): Boolean {
        return try {
            toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun String.isOperator(): Boolean {
        return this in setOf("+", "-", "x", "/", "^")
    }

    private fun String.isFunction(): Boolean {
        return this in setOf("sin", "cos", "tan", "log", "ln", "√")
    }

    private fun String.isConstant(): Boolean {
        return this in setOf("π", "e")
    }

    private fun String.toConstantValue(): Double {
        return when (this) {
            "π" -> pi
            "e" -> E
            else -> throw IllegalArgumentException("Invalid constant: $this")
        }
    }

    private fun precedence(operator: String): Int {
        return when (operator) {
            "+", "-" -> 1
            "x", "/" -> 2
            "^" -> 3
            else -> 0
        }
    }

    private fun <T> MutableList<T>.push(element: T) {
        add(element)
    }

    private fun <T> MutableList<T>.pop(): T {
        return removeAt(lastIndex)
    }
}