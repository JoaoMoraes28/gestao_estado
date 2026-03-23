package com.example.gestaoestado.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gestaoestado.calculos.calcularJuros
import com.example.gestaoestado.calculos.calcularMontante

class JurosScreenViewModel {
    private val _capitalState = MutableLiveData<String>()
    var capitalState: LiveData<String> = _capitalState

    private val _tempoState = MutableLiveData<String>()
    var tempoState: LiveData<String> = _tempoState

    private val _taxaState = MutableLiveData<String>()
    var taxaState: LiveData<String> = _taxaState

    private val _jurosState = MutableLiveData<Double>()
    var jurosState: LiveData<Double> = _jurosState

    private val _montanteState = MutableLiveData<Double>()
    var montanteState: LiveData<Double> = _montanteState

    fun onCapitalChanged(newCapital: String) {
        _capitalState.value = newCapital
    }

    fun onTaxaChanged(newTaxa: String) {
        _taxaState.value = newTaxa
    }

    fun onTempoChanged(newTempo: String) {
        _tempoState.value = newTempo
    }

    fun onJurosChanged(newJuros: Double) {
        _jurosState.value = newJuros
    }

    fun onMontanteChanged(newMontante: Double) {
        _montanteState.value = newMontante
    }

    fun calcularJurosInvestimento() {
        _jurosState.value = calcularJuros(
            capital = _capitalState.value!!.toDouble(),
            taxa = _taxaState.value!!.toDouble(),
            tempo = _tempoState.value!!.toDouble()
        )
    }

    fun calcularMontanteInvestimento() {
        _montanteState.value = calcularMontante(
            _capitalState.value!!.toDouble(),
            _jurosState.value!!
        )
    }
}