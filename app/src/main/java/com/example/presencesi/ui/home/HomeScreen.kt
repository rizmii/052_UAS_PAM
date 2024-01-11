package com.example.presencesi.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presencesi.model.Presensi
import com.example.presencesi.ui.AppBarPresensi
import com.example.presencesi.ui.DestinasiNavigasi
import com.example.presencesi.ui.PenyediaViewModel

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Presensi"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBarPresensi(
                title = "Presensi",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateAbsen by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemPresensi = uiStateAbsen.listPresensi,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onAbsenClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemPresensi: List<Presensi>,
    modifier: Modifier = Modifier,
    onAbsenClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPresensi.isEmpty()) {
            Text(
                text = "Anda belum presensi",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPresensi(
                itemPresensi = itemPresensi,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onAbsenClick(it.NIM) }
            )
        }
    }
}

@Composable
fun ListPresensi(
    itemPresensi: List<Presensi>,
    modifier: Modifier = Modifier,
    onItemClick: (Presensi) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemPresensi, key = { it.NIM }) { presensi ->
            DataPresensi(
                presensi = presensi,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(presensi) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataPresensi(
    presensi: Presensi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = presensi.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = presensi.NIM,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = presensi.tanggal,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = presensi.keterangan,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}