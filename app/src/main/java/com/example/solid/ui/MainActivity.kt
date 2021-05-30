package com.example.solid.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.solid.R
import com.example.solid.database.entity.UserEntity
import com.example.solid.factory.user.UserFactory
import com.example.solid.factory.user.UserViewModelFactory

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    private val userViewModelFactory: UserViewModelFactory by lazy {
        UserViewModelFactory(this, userViewModel)
    }

    private val userFactory: UserFactory by lazy {
        UserFactory(modelFactory = userViewModelFactory)
    }

    private lateinit var buttonLogin: Button
    private lateinit var recycleView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var myAdapter: MyAdapter
    private var list = ArrayList<UserEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        myAdapter = MyAdapter(list)
        buttonLogin = findViewById(R.id.buttonLogin)
        progressBar = findViewById(R.id.progressbar)
        recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.apply {
            adapter = myAdapter
        }

        buttonLogin.let { button ->
            button.setOnClickListener {
                saveUser()
            }
        }
        updateAdapter()
    }

    private fun saveUser() {
        val entity = UserEntity(id = null, nome = "Ednei", password = "123456")
        progressBar.visibility = View.VISIBLE
        userFactory.save(entity, success = { updateAdapter() }, failure = {
            progressBar.visibility = View.GONE
            Log.e(TAG, it)
        })
    }

    private fun updateAdapter() {
        userFactory.list(success = {
            recycleView.smoothScrollToPosition(list.size)
            myAdapter.update(it)
        })
        progressBar.visibility = View.GONE
    }
}

class MyAdapter(private val list: ArrayList<UserEntity>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.textViewLista)

        fun add(entity: UserEntity) {
            name.text = entity.id.toString() + "-"+ entity.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.add(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(entitys: List<UserEntity>) {
        this.list.clear()
        this.list.addAll(entitys)
        this.notifyDataSetChanged()
    }
}
