package modelos

import enums.TipoSocio
import sun.util.calendar.BaseCalendar
import java.util.*

class Socio(private var nome: String, private var cpf: String, private var plano: TipoSocio?) {
    // Outros métodos e propriedades devem ser adicionados aqui

    private val dataCriacao: Date
    init {
        require(nome.isNotEmpty()) { "O nome do sócio não pode estar vazio" }
        require(cpf.isNotEmpty()) { "O CPF do sócio não pode estar vazio" }
        dataCriacao = Calendar.getInstance().time
    }

    constructor(nome: String, cpf: String) : this(nome, cpf, null)


    private var listaSociosDependentes = mutableListOf<Socio>()

    fun adicionarDependente(dependente: Socio){
        if (this.plano == TipoSocio.NORMAL){
            println("O plano de $nome não permite adicionar dependentes!")
        }else{
            if(this.plano == TipoSocio.VIP){
                if (listaSociosDependentes.size == 0){
                dependente.plano = TipoSocio.DEPENDENTE
                listaSociosDependentes.add(dependente)
                println("${dependente.nome} foi adicionado como dependente de $nome!")
                }else{
                println("O plano de $nome permite apenas um dependente! ${listaSociosDependentes[0].nome} é seu dependente atual")
                }
            }
            if (this.plano == TipoSocio.PLUS){
                if(this.listaSociosDependentes.size < 3) {
                    dependente.plano = TipoSocio.DEPENDENTE
                    listaSociosDependentes.add(dependente)
                    println("${dependente.nome} foi adicionado como seu dependente!")
                }else{
                    println("O plano de $nome permite apenas 3 dependentes!")
                }
            }
        }
    }
    fun removerDependente(dependente: Socio){
        if (listaSociosDependentes.contains(dependente)){
            listaSociosDependentes.remove(dependente)
            dependente.plano = TipoSocio.NORMAL
            println("${dependente.nome} foi removido dos dependentes de $nome")
        }else{
            println("${dependente.nome} não é dependente de $nome!")
        }
    }
    fun removerDependente(){ // Caso ele não passe qual, removerá o primeiro da lista!
        if (listaSociosDependentes.isNotEmpty()){
            val dependente = listaSociosDependentes.first()
            listaSociosDependentes.remove(dependente)
            println("${dependente.nome} foi removido dos dependentes de $nome")
        }else{
            println("O sócio $nome não possui dependentes!")
        }
    }

    fun imprimirDados(){
        val a: Int = 3
        val b = 2
        val valorPlano = this.plano?.valorPlano ?: 0
        var texto: String
        var dependentes = ""
        for(dependente in listaSociosDependentes){
            dependentes += dependente.getNome() + " |"
        }
        texto = """Segue abaixo os dados do sócio:
                            |Nome: $nome
                            |CPF: $cpf
                            |Plano: $plano
                            |Valor a ser pago: R$${valorPlano}
                            |Data de criação do Sócio: $dataCriacao
                            |Dependentes: $dependentes
                        """.trimMargin()
        println(texto)
    }

    fun getNome(): String {
        return nome
    }

    fun setNome(novoNome: String) {
        nome = novoNome
    }

    fun getCpf(): String {
        return cpf
    }

    fun setCpf(novoCpf: String) {
        cpf = novoCpf
    }

    fun getPlano(): TipoSocio? {
        return plano
    }

    fun setPlano(novoPlano: TipoSocio?) {
        plano = novoPlano
    }

    fun getListaSociosDependentes(): List<Socio> {

        return listaSociosDependentes
    }

    fun getDataCriacao(): Date {
        return dataCriacao
    }

}