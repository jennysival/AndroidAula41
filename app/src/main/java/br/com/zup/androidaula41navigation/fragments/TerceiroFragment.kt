package br.com.zup.androidaula41navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import br.com.zup.androidaula41navigation.CAMPO_OBR
import br.com.zup.androidaula41navigation.R
import br.com.zup.androidaula41navigation.databinding.FragmentTerceiroBinding

class TerceiroFragment : Fragment() {
    private lateinit var binding: FragmentTerceiroBinding
    private lateinit var booleanRecebido: String
    private lateinit var numeroRecebido: String
    private lateinit var textocriado: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTerceiroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receberExibirArgs()

        binding.botaoIrPrimeiro.setOnClickListener {
            enviarDadosPrimeiro()
        }

        binding.botaoIrSegundo.setOnClickListener {
            enviarDadosSegundo()
        }


    }

    private fun receberDados(): String{
        this.numeroRecebido = binding.etNum.text.toString()
        this.booleanRecebido = binding.etBoolean.text.toString()
        return booleanRecebido
    }

    private fun enviarDadosPrimeiro(){
        receberDados()
        if(!verificarCampos()){
            if(!verificarBoolean(receberDados())){
                transformarBooleanEmTexto()
                view?.findNavController()?.navigate(TerceiroFragmentDirections.actionTerceiroFragmentToPrimeiroFragment(textocriado))
                limparCampos()
            }
        }

    }

    private fun enviarDadosSegundo(){
        receberDados()
        if(!verificarCampos()){
            if(!verificarBoolean(receberDados())){
                view?.findNavController()?.navigate(TerceiroFragmentDirections.actionTerceiroFragmentToSegundoFragment(this.numeroRecebido.toInt(),this.booleanRecebido.toBoolean()))
                limparCampos()
            }
        }

    }

    private fun verificarCampos():Boolean{
        return when{
            this.numeroRecebido.isEmpty() -> {
                binding.etNum.error = CAMPO_OBR
                true
            }
            this.booleanRecebido.isEmpty() -> {
                binding.etBoolean.error = CAMPO_OBR
                true
            }
            else -> {
                false
            }
        }
    }

    private fun verificarBoolean(recebido: String):Boolean{
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

    private fun transformarBooleanEmTexto(){
        textocriado = "Seu ~$booleanRecebido~ acabou de virar essa String linda!"
    }

    private fun receberExibirArgs(){
        val args = TerceiroFragmentArgs.fromBundle(requireArguments())

        val parametro = buildString {
            append("Double: ")
            append(args.doubleRecebida.toDouble().toString())
        }

        binding.tvParametro3.text = parametro
    }

    private fun limparCampos(){
        binding.etBoolean.text.clear()
        binding.etNum.text.clear()
    }

}