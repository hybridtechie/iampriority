package com.hybridtechie.iampriority

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.hybridtechie.iampriority.util.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RCSIGNIN = 324
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener { signIn() }
    }

    public override fun onStart() {
        super.onStart()


        if (!AppPreferences.firstRun) {
            AppPreferences.firstRun = true
            Log.d(TAG, "The value of our pref is: ${AppPreferences.firstRun}")
            val currentUser = auth.currentUser
            if (currentUser != null) {
                updateUI(currentUser)
                Toast.makeText(this, "Current User Found" + currentUser.email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "NO User Found", Toast.LENGTH_LONG).show()
            }
        } else {
            openHome()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RCSIGNIN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RCSIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                e.printStackTrace()
                // Google Sign In failed, update UI appropriately
                Log.e(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                    //Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Un Successful", Toast.LENGTH_LONG).show()
                }

            }
    }

    public override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //Do your Stuff
            Toast.makeText(this, "Hello ${user.displayName}", Toast.LENGTH_SHORT).show()

        }
    }

    private fun openHome() {
        AppPreferences.firstRun = true
        val intent = Intent(this, com.hybridtechie.iampriority.OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openOnboarding() {
        val intent = Intent(this, com.hybridtechie.iampriority.OnBoardingActivity::class.java)
        startActivity(intent)
        finish()

    }

}
