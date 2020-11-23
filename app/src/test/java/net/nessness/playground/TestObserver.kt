package net.nessness.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals

fun <T> LiveData<T>.observeForTest(): TestObserver<T> = TestObserver<T>().apply { observeForever(this) }

class TestObserver<T> : Observer<T> {

    val values = mutableListOf<T>()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertNoValues() {
        assertSize(0)
    }

    fun assertValueAt(i: Int, test: (T) -> Unit) {
        test(values[i])
    }

    fun assertValueAt(i: Int, expected: T) {
        assertEquals(values[i], expected)
    }

    fun assertSize(size: Int) {
        assertEquals(size, values.size)
    }
}
