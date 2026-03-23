package com.example.gestaoestado.juros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestaoestado.calculos.calcularJuros
import com.example.gestaoestado.calculos.calcularMontante
import com.example.gestaoestado.components.GetCardResultado
import com.example.gestaoestado.components.GetTextField

@Composable
fun JurosScreen(
    modifier: Modifier = Modifier,
    jurosScreenViewModel: JurosScreenViewModel
) {
    val corApp = Color(136, 38, 199, 255)

    val capital by jurosScreenViewModel.capitalState.observeAsState("")

    val tempo by jurosScreenViewModel.tempoState.observeAsState("")

    val taxa by jurosScreenViewModel.taxaState.observeAsState("")

    val juros by jurosScreenViewModel.jurosState.observeAsState(0.0)

    val montante by jurosScreenViewModel.montanteState.observeAsState(0.0)

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = corApp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calculadora Juros Simples",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Dados do investimento",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        GetTextField(
                            label = "Valor investido",
                            placeholder = "Quanto deseja investir?",
                            value = capital,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            corApp = corApp,
                            atualizarValor = { jurosScreenViewModel.onCapitalChanged(it) }
                        )

                        GetTextField(
                            label = "Taxa de juros mensal",
                            placeholder = "Qual a taxa de juros mensal?",
                            value = taxa,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            atualizarValor = { jurosScreenViewModel.onTaxaChanged(it) },
                            corApp = corApp,
                        )

                        GetTextField(
                            label = "Período em meses",
                            placeholder = "Quanto tempo em meses?",
                            value = tempo,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            atualizarValor = { jurosScreenViewModel.onTempoChanged(it) },
                            corApp = corApp,
                        )

                        Button(
                            onClick = {
                                jurosScreenViewModel.calcularJurosInvestimento()

                                jurosScreenViewModel.calcularMontanteInvestimento()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                GetCardResultado(
                    juros = juros,
                    montante = montante
                )

            }
        }
    }
}