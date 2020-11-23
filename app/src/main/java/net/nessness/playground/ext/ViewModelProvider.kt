package net.nessness.playground.ext

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Provide ViewModel with key
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.keyedViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline keyProducer: () -> String
): Lazy<VM> {
    return lazy { ViewModelProvider(ownerProducer()).get(keyProducer(), VM::class.java) }
}
