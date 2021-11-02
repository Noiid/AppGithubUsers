package com.noiid.githubusers

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.noiid.githubusers.adapter.ListAccountAdapter
import com.noiid.githubusers.databinding.ActivityMainBinding
import com.noiid.githubusers.dataclass.Account
import com.noiid.githubusers.dataclass.Users
import com.noiid.githubusers.resource.ListUser
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title)



//        binding.rvAccount.setHasFixedSize(true)

        showRv()

        mainViewModel.listUser.observe(this, { listUser ->
            setListUser(listUser)
        })


    }

    private fun showRecyclerList(){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvAccount.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvAccount.layoutManager = LinearLayoutManager(this)
        }

    }

    private fun showRv() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvAccount.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvAccount.addItemDecoration(itemDecoration)


    }

    private fun setListUser(Users: List<ListUser>) {
        val listUser = ArrayList<Users>()
        for (user in Users){
            val user = Users(user.login,user.avatar_url)
            listUser.add(user)
        }
        Log.e("data",listUser.toString())
        val listAccountAdapter = ListAccountAdapter(listUser)
        binding.rvAccount.adapter = listAccountAdapter
        listAccountAdapter.setOnItemClickCallback(object : ListAccountAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Users) {
                showSelectedAccount(data)
            }
        })
    }

    private fun showSelectedAccount(user: Users) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailAccountActivity::class.java)
        moveWithObjectIntent.putExtra(DetailAccountActivity.EXTRA_ACCOUNT, user)
        startActivity(moveWithObjectIntent)
    }
}