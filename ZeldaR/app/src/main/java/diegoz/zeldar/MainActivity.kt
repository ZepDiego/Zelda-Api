package diegoz.zeldar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import diegoz.zeldar.api.RetrofitFactory

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: ViewAdapter
    private val itemViewModel:ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler=findViewById(R.id.RVapi)
        adapter= ViewAdapter(itemViewModel.elementos)
        recycler.adapter=adapter

        recycler.layoutManager= LinearLayoutManager(this)
        buscar()

    }

    private fun buscar(){

        lifecycleScope.launch {
            try {
                val call= RetrofitFactory.getRetrofit()
                val salida=call.extraerDatos("")
                for (i in 0..salida.data.size-1){
                    itemViewModel.elementos.add(Item(salida.data[i].image,salida.data[i].name,salida.data[i].category))
                    println(salida.data[i].image)
                }
                adapter.notifyDataSetChanged()
            }catch (ex:Exception){
                Toast.makeText(this@MainActivity,"${ex.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}