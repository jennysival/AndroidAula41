package br.com.zup.androidaula41navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.androidaula41navigation.CAMPO_OBR
import br.com.zup.androidaula41navigation.CHAVE_DOUBLE
import br.com.zup.androidaula41navigation.CHAVE_LISTA
import br.com.zup.androidaula41navigation.R
import br.com.zup.androidaula41navigation.databinding.FragmentPrimeiroBinding

class PrimeiroFragment : Fragment() {

    private lateinit var binding: FragmentPrimeiroBinding
    private lateinit var numeroRecebido: String
    private lateinit var booleanRecebido: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrimeiroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receberExibirArgs()

        binding.botaoIrSegundo.setOnClickListener {
            enviarDadosSegundoFrag()
        }

        binding.botaoIrTerceiro.setOnClickListener {
            enviarDadosTerceiroFrag()
        }
    }

    private fun receberDados(): String{
        this.numeroRecebido = binding.etNum.text.toString()
        this.booleanRecebido = binding.etBoolean.text.toString()
        return booleanRecebido
    }

    private fun enviarDadosSegundoFrag(){
        //int e boolean
        receberDados()
        if(!verificarCampos()){
            if(!verificarBoolean(receberDados())){
                val intRecebida = this.numeroRecebido.toInt()
                val booleanRecebida = this.booleanRecebido.toBoolean()
                view?.findNavController()?.navigate(PrimeiroFragmentDirections.actionPrimeiroFragmentToSegundoFragment(intRecebida,booleanRecebida))
                limparCampos()
            }
        }

    }

    private fun enviarDadosTerceiroFrag(){
        //double
        receberDados()
        if (!verificarCampos()){
            val doubleRecebido = this.numeroRecebido.toDouble()
            view?.findNavController()?.navigate(PrimeiroFragmentDirections.actionPrimeiroFragmentToTerceiroFragment(doubleRecebido.toFloat()))
            limparCampos()
        }
    }

    private fun verificarCampos(): Boolean{

        when{
            this.numeroRecebido.isEmpty() -> {
                binding.etNum.error = CAMPO_OBR
                return true
            }
            this.booleanRecebido.isEmpty() -> {
                binding.etBoolean.error = CAMPO_OBR
                return true
            }
        }
        return false
    }

    private fun verificarBoolean(recebido: String): Boolean{
        return when(recebido){
            "true" -> {
                false
            }
            "false" -> {
                false
            }
            else -> {
                binding.etBoolean.error = "Digite apenas true ou false"
                true
            }
        }
    }

    private fun limparCampos(){
        binding.etNum.text.clear()
        binding.etNum.text.clear()
    }

    private fun receberExibirArgs(){
        val args = PrimeiroFragmentArgs.fromBundle(requireArguments())

        val parametro = buildString {
            append("String: ")
            append(args.stringRecebida)
        }

        binding.tvParametro.text = parametro
    }
}
