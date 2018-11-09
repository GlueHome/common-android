package com.gluehome.common.data.extensions

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.SetOptions
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Updates the document at the given [DocumentReference] (receiver) with a set of new values specified
 * in a map with the fields (Strings of names of the fields to be updated) and the new values of any type
 * supported by firestore as the map's value. Returns a completable which completes if the
 * operation is successful or calls onError otherwise.
 *
 * @param updatedValues [Map] of field names (keys) and updated values (values)
 */
suspend fun DocumentReference.updateDocumentCoroutines(updatedValues: Map<String, Any>): Boolean {
    return suspendCoroutine { cont ->
        update(updatedValues)
                .addOnSuccessListener { cont.resume(true) }
                .addOnFailureListener { cont.resumeWithException(it) }
    }
}

suspend fun DocumentReference.setDocumentAndMergeCoroutines(updatedValues: Map<String, Any>): Boolean {
    return suspendCoroutine { cont ->
        set(updatedValues, SetOptions.merge())
                .addOnSuccessListener { cont.resume(true) }
                .addOnFailureListener { cont.resumeWithException(it) }
    }
}
