package br.com.zup.androidaula41navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import br.com.zup.androidaula41navigation.CAMPO_OBR
import br.com.zup.androidaula41navigation.R
import br.com.zup.androidaula41navigation.databinding.FragmentSegundoBinding

class SegundoFragment : Fragment() {
    private lateinit var binding: FragmentSegundoBinding
    private lateinit var textoRecebido: String
    private lateinit var doubleRecebido: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSegundoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receberExibirArgs()

        binding.botaoIrPrimeiro.setOnClickListener {
            enviarDadosPrimeiro()
        }

        binding.botaoIrTerceiro.setOnClickListener {
            enviarDadosTerceiro()
        }



    }

    private fun receberDdos(){
        this.textoRecebido = binding.etString.text.toString()
        this.doubleRecebido = binding.etNum.text.toString()
    }

    private fun enviarDadosPrimeiro(){
        receberDdos()
        if(!validarCampos()){
            view?.findNavController()?.navigate(SegundoFragmentDirections.actionSegundoFragmentToPrimeiroFragment(textoRecebido))
            limparCampos()
        }

    }

    private fun enviarDadosTerceiro(){
        receberDdos()
        if(!validarCampos()){
            view?.findNavController()?.navigate(SegundoFragmentDirections.actionSegundoFragmentToTerceiroFragment(doubleRecebido.toDouble().toFloat()))
            limparCampos()
        }

    }

    private fun validarCampos(): Boolean{
        return when{
            textoRecebido.isEmpty() -> {
                binding.etString.error = CAMPO_OBR
                true
            }
            doubleRecebido.isEmpty() -> {
                binding.etNum.error = CAMPO_OBR
                true
            }
            else -> {
                false
            }
        }
    }

    private fun receberExibirArgs(){
        val args = SegundoFragmentArgs.fromBundle(requireArguments())

        val parametro = buildString {
            append("Int: ")
            append(args.intRecebida.toString())
            append(" | Boolean: ")
            append(args.booleanRecebida.toString())
        }

        binding.tvParametro2.text = parametro
    }

    private fun limparCampos(){
        binding.etNum.text.clear()
        binding.etString.text.clear()
    }

}