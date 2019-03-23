package com.csgames.mixparadise

import com.csgames.mixparadise.api.Api
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun Sha1IsCorrect() {
       assert(Api.sha1("Rosetta Code") == "48c98f7e5a6e736d790ab740dfc3f51a61abe2b5");
    }
}
