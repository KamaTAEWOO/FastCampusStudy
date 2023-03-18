package fastcampus.part1.chapter7

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.chapter7.databinding.ActivityMainBinding
import java.nio.file.Files.delete

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private val updateAddWordResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false
        if(result.resultCode == RESULT_OK && isUpdated) {
            updateAddWord()
        }
    }
    private val updateEditWordResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val editWord = result.data?.getParcelableExtra<Word>("editWord")
        if(result.resultCode == RESULT_OK && editWord != null) {
            updateEditWord(editWord)
        }
    }
    private var selectedWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addButton.setOnClickListener{
            Intent(this, AddActivity::class.java).let{
                updateAddWordResult.launch(it)
            }
        }

        binding.deleteImageView.setOnClickListener{
            delete()
        }

        binding.editImageView.setOnClickListener{
            edit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        Thread{
            val list = AppDataBase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            wordAdapter.list.addAll(list)
            runOnUiThread{ wordAdapter.notifyDataSetChanged() }
        }.start()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAddWord() {
        Thread {
            AppDataBase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread{ wordAdapter.notifyDataSetChanged() }
            }
        }
    }

    private fun updateEditWord(word: Word) {
        val index = wordAdapter.list.indexOfFirst{ it.id == word.id }
        wordAdapter.list[index] = word
        runOnUiThread {
            selectedWord = word
            wordAdapter.notifyDataSetChanged()
            binding.textTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun delete() {
        if(selectedWord == null) return

        Thread {
            selectedWord?.let { word ->
                AppDataBase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""
                }
                selectedWord = null
            }
        }.start()
    }

    private fun edit() {
        if(selectedWord == null) return

        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditWordResult.launch(intent)
    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean
    }
}