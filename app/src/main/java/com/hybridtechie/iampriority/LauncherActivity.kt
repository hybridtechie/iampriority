package com.hybridtechie.iampriority

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.hybridtechie.iampriority.util.AppPreferences
import com.hybridtechie.iampriority.util.Tools
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RCSIGNIN = 324
    private val TAG = "LauncherActivity"

    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private val titleArray = arrayOf("Personalised Alerts", "Location Based", "Government and Private", "Join Now")
    private val descriptionArray = arrayOf(
        "Personalised Alerts , Personalised Alerts, Personalised Alerts, Personalised Alerts",
        "Location Based Location Based Location Based Location Based Location Based",
        "Government and Private Government and Private Government and Private Government and Private",
        "Why Wait Join Now Why Wait Join Now Why Wait Join Now Why Wait Join Now Why Wait Join Now"
    )
    private val aboutImagesArray =
        intArrayOf(R.drawable.img_wizard_1, R.drawable.img_wizard_2, R.drawable.img_wizard_3, R.drawable.img_wizard_4)
    private val colorArray =
        intArrayOf(R.color.red_600, R.color.blue_grey_600, R.color.purple_600, R.color.deep_orange_600)

    //  viewpager change listener
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                bottomProgressDots(position)
                if (position == titleArray.size - 1) {
                    sign_in_button!!.visibility = View.VISIBLE
                } else {
                    sign_in_button!!.visibility = View.GONE
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        supportActionBar?.hide()
        initComponent()

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        Tools.setSystemBarTransparent(this)
    }

    private fun initComponent() {
        bottomProgressDots(0)

        myViewPagerAdapter = MyViewPagerAdapter()
        view_pager!!.adapter = myViewPagerAdapter
        view_pager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        (findViewById<Button>(R.id.btn_skip)).setOnClickListener { finish() }

        sign_in_button.setOnClickListener { signIn() }
    }

    public override fun onStart() {
        super.onStart()

        if (!AppPreferences.firstRun) {
            //AppPreferences.firstRun = true
            Log.d(TAG, "The value of our pref is: ${AppPreferences.firstRun}")
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Toast.makeText(this, "Current User Found" + currentUser.email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "NO User Found", Toast.LENGTH_LONG).show()
            }
            updateUI(currentUser)
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
            AppPreferences.userName = user.displayName
            AppPreferences.userEmail = user.email
            AppPreferences.userId = user.uid
            openOnBoarding()
        }
    }

    private fun openHome() {
        AppPreferences.firstRun = true
        val intent = Intent(this, com.hybridtechie.iampriority.OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openOnBoarding() {
        val intent = Intent(this, com.hybridtechie.iampriority.OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun bottomProgressDots(current_index: Int) {
        val dotsLayout = findViewById<LinearLayout>(R.id.layoutDots)
        val dots = arrayOfNulls<ImageView>(MAX_STEP)

        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val widthHeight = 15
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(widthHeight, widthHeight))
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle)
            dots[i]!!.setColorFilter(getColor(R.color.overlay_dark_30), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[current_index]!!.setImageResource(R.drawable.shape_circle)
            dots[current_index]!!.setColorFilter(getColor(R.color.grey_10), PorterDuff.Mode.SRC_IN)
        }
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return titleArray.size
        }

        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater!!.inflate(R.layout.item_stepper_wizard_color, container, false)
            (view.findViewById(R.id.title) as TextView).text = titleArray[position]
            (view.findViewById(R.id.description) as TextView).text = descriptionArray[position]
            (view.findViewById(R.id.image) as ImageView).setImageResource(aboutImagesArray[position])
            //(view.findViewById(R.id.lyt_parent) as RelativeLayout).setBackgroundColor(getColor(colorArray[position]))
            container.addView(view)

            return view
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

    companion object {
        private val MAX_STEP = 4
    }
}
