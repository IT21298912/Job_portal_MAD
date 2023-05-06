package com.example.bottomnavyt
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bottomnavyt.database.AppDatabase
import com.example.bottomnavyt.database.models.User
import com.example.bottomnavyt.database.repositeries.UserRepositiry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivitySignupTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: UserRepositiry

    @Before
    fun setUp() {
        appDatabase = AppDatabase.getDatabase(ApplicationProvider.getApplicationContext())
        repository = UserRepositiry(appDatabase)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun `test writeData inserts a new user`() {
        // Given
        val name = "John"
        val email = "john@example.com"
        val password = "password"
        val repassword = "password"

        // When
        runBlocking {
            val user = User(name, email, password, repassword, null, null, null, null, null)
            repository.insert(user)
        }

        // Then
        val user2 = runBlocking { repository.getuserdata(email) }
   //     assertEquals(1, user2)
        assertEquals(name, user2.name)
        assertEquals(email, user2.email)
        assertEquals(password, user2.password)
        assertEquals(repassword, user2.repassword)
    }
}