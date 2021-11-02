package com.noiid.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.noiid.githubusers.databinding.ActivityDetailAccountBinding
import com.noiid.githubusers.dataclass.Account

class DetailAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val account = intent.getParcelableExtra<Account>(EXTRA_ACCOUNT) as Account
        supportActionBar?.title = "Detail User @"+account.username

        binding.tvFollowers.text = getString(R.string.text_follower, account.follower)
        binding.tvFollowing.text = getString(R.string.text_following, account.following)
        binding.tvName.text = account.name
        binding.tvCompany.text = account.company
        binding.tvLocation.text = account.location
        binding.tvRepo.text = getString(R.string.text_repository, account.repository)

        Glide.with(this.applicationContext)
            .load(account.avatar)
            .circleCrop()
            .into(binding.imageAccount)

    }

    companion object {
        const val EXTRA_ACCOUNT = "extra_account"
    }
}