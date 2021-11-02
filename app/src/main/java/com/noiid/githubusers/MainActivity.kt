package com.noiid.githubusers

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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

    private val list = ArrayList<Account>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title)

        binding.rvAccount.setHasFixedSize(true)

        mainViewModel.listUser.observe(this, { listUser ->
            setListUser(listUser)
        })

        showRecyclerList()
    }

    private fun showRecyclerList(){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvAccount.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvAccount.layoutManager = LinearLayoutManager(this)
        }

    }

    private fun setListUser(listUser: List<ListUser>) {
        val listUser = ArrayList<Users>()
        for (user in listUser){
            val user = Users(user.username,user.avatar)
            listUser.add(user)
        }
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