package com.example.sccalculator

abstract sealed class UiEvent {
    object On0Click: UiEvent()
    object On1Click : UiEvent()
    object On2Click: UiEvent()
    object On3Click: UiEvent()
    object On4Click: UiEvent()
    object On5Click: UiEvent()
    object On6Click: UiEvent()
    object On7Click: UiEvent()
    object On8Click: UiEvent()
    object On9Click: UiEvent()
    object OnDotClick: UiEvent()

    object OnSinClick: UiEvent()
    object OnCosClick: UiEvent()
    object OnTanClick: UiEvent()
    object OnPiClick: UiEvent()
    object OnLnClick: UiEvent()
    object OnLogClick: UiEvent()
    object OnEClick: UiEvent()
    object OnSquareRootClick: UiEvent()
    object OnOpenBracketClick: UiEvent()
    object OnCloseBracketClick: UiEvent()

    object OnDivideClick: UiEvent()
    object OnMultiplyClick: UiEvent()
    object OnSubtractClick: UiEvent()
    object OnAddClick: UiEvent()

    object OnClearClick : UiEvent()
    object OnClearAllClick : UiEvent()
    object OnEqualClick : UiEvent()

    object OnNoAction : UiEvent()
}