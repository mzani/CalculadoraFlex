package br.com.zani.calculadoraflex.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global.getString
import android.widget.Toast
import br.com.zani.calculadoraflex.R
import br.com.zani.calculadoraflex.extensions.format
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent.extras == null)
        {
            Toast.makeText(this, "Nao foi possivel realizar a operação",
                Toast.LENGTH_LONG).show()
        }else{
            calculate()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun calculate() {
        val gasPrice = intent.extras?.getDouble("GAS_PRICE", 0.0) ?: 1.0
        val ethanolPrice = intent.extras?.getDouble("ETHANOL_PRICE", 0.0) ?: 0.0
        val gasAverage = intent.extras?.getDouble("GAS_AVERAGE", 0.0) ?: 1.0
        val ethanolAverage = intent.extras?.getDouble("ETHANOL_AVERAGE", 0.0) ?: 0.0
        val performanceOfMyCar = ethanolAverage / gasAverage
        val priceOfFuelIndice = ethanolPrice / gasPrice
        if (priceOfFuelIndice <= performanceOfMyCar) {
            tvSuggestion.text = getString(R.string.ethanol)
        } else {
            tvSuggestion.text = getString(R.string.gasoline)
        }
        tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)
        tvGasAverageResult.text = (gasPrice / gasAverage).format(2)
        tvFuelRatio.text = getString(R.string.label_fuel_ratio, performanceOfMyCar.format(2))
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


