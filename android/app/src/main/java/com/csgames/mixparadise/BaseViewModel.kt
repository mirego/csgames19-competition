package com.csgames.mixparadise

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    private var _subscriptions = CompositeDisposable()
    protected val subscriptions: CompositeDisposable
        get() {
            if (_subscriptions.isDisposed) _subscriptions = CompositeDisposable()
            return _subscriptions
        }

    override fun onCleared() {
        super.onCleared()
        Log.d(this::class.java.simpleName, "ViewModel cleared")
        subscriptions.dispose()
    }
}