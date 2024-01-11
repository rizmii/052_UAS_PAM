package com.example.presencesi.data

import android.content.ContentValues
import android.util.Log
import com.example.presencesi.model.Presensi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.lang.Exception

interface RepositoryPresensi {
    fun getAll(): Flow<List<Presensi>>
    suspend fun save(presensi: Presensi ): String
    suspend fun update(presensi: Presensi)
    suspend fun delete(presensiId: String)
    fun getAbsenById(presensiId: String): Flow<Presensi>
}

class RepositoryPresensiImpl(private val firestore: FirebaseFirestore) : RepositoryPresensi {
    override fun getAll(): Flow<List<Presensi>> = flow {
        val snapshot = firestore.collection("Presensi")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val kontak = snapshot.toObjects(Presensi::class.java)
        emit(kontak)
    }.flowOn(Dispatchers.IO)
    override suspend fun save(presensi: Presensi): String {
        return try {
            val documentReference = firestore.collection("Presensi").add(presensi).await()

            firestore.collection("Presensi").document(documentReference.id)
                .set(presensi.copy(NIM = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(presensi: Presensi) {
        firestore.collection("Presensi").document(presensi.NIM).set(presensi).await()
    }

    override suspend fun delete(presensiId: String) {
        firestore.collection("Presensi").document(presensiId).delete().await()
    }

    override fun getAbsenById(presensiId: String): Flow<Presensi> {
        return flow {
            val snapshot = firestore.collection("Presensi").document(presensiId).get().await()
            val presensi = snapshot.toObject(Presensi::class.java)
            emit(presensi!!)
        }.flowOn(Dispatchers.IO)
    }

}