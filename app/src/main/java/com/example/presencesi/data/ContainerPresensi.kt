package com.example.presencesi.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val repositoryPresensi : RepositoryPresensi
}
class ContainerPresensi : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryPresensi: RepositoryPresensi by lazy {
        RepositoryPresensiImpl(firestore)
    }

}