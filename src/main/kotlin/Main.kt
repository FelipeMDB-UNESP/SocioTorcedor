import enums.TipoSocio
import modelos.Socio
import  java.util.Scanner
fun main(args: Array<String>) {

    var listaSocios = mutableListOf<Socio>()

    val reader = Scanner(System.`in`)
    var charRecebido : Char? = null
    while(charRecebido != '0'){


        println("\nO que deseja fazer?")
        println("0 - Sair")
        println("1 - Cadastras sócio")
        println("2 - Remover sócio")
        println("3 - Listar determinado sócio")

        charRecebido = reader.next().single()

        if(charRecebido == '1'){ // Adicionar Socio
            charRecebido = 'S' // Inicializa
            while (charRecebido == 'S'){
                var i = 0
                println("Digite o Nome:")
                reader.nextLine() // Consome o '\n' deixado pelo print, evitando que nome receba esse '\n'
                val nome = reader.nextLine()
                println("Digite o CPF:")
                val cpf: String = reader.nextLine()
                println("Qual o plano desejado? (1 - Normal, 2 - VIP, 3 - PLUS)")
                val plano: Int = reader.nextInt()
                while(plano > 3 || plano < 1){
                    println("Selecione um número entre 1 e 3!")
                    println("Qual o plano desejado? (1 - Normal, 2 - VIP, 3 - PLUS)")
                    var plano: Int = reader.nextInt()
                }
                var socio : Socio
                if(plano == 1){
                    socio = Socio(nome, cpf, TipoSocio.NORMAL)
                }else if(plano == 2){
                    socio = Socio(nome, cpf, TipoSocio.VIP)
                }else{
                    socio = Socio(nome, cpf, TipoSocio.PLUS)
                }
                listaSocios.add(socio)
                if(plano == 2 || plano == 3){
                    println("Você deseja adicionar um dependente a este sócio? S ou N?")
                    charRecebido = reader.next().single()
                    if (charRecebido == 'S'){
                        println("Digite o Nome do Dependente:")
                        reader.nextLine()
                        val nome = reader.nextLine()
                        println("Digite o CPF do Dependente:")
                        val cpf: String = reader.nextLine()
                        var dependente : Socio
                        dependente = Socio(nome, cpf, TipoSocio.DEPENDENTE)
                        socio.adicionarDependente(dependente)
                        listaSocios.add(dependente)

                        if(plano == 3){
                            println("Você deseja adicionar mais algum dependente a este sócio? S ou N?")
                            charRecebido = reader.next().single()
                            while (charRecebido == 'S'){
                                println("Digite o Nome do Dependente:")
                                reader.nextLine()
                                val nome = reader.nextLine()
                                println("Digite o CPF do Dependente:")
                                val cpf: String = reader.nextLine()
                                val dependente = Socio(nome, cpf, TipoSocio.DEPENDENTE)
                                socio.adicionarDependente(dependente)
                                listaSocios.add(dependente)
                                charRecebido = 'N'
                                if (socio.getListaSociosDependentes().size < 3){
                                    println("Você deseja adicionar mais algum dependente a este sócio? S ou N?")
                                    charRecebido = reader.next().single()
                                }
                            }
                        }
                    }
                }
                println("Você deseja adicionar mais algum sócio? S ou N?")
                charRecebido = reader.next().single()
            }
        }
        else if(charRecebido == '2') {
            if(listaSocios.isNotEmpty()){
                println("Qual o CPF do Sócio que deseja remover?")
                val cpfSocio = reader.next()
                var texto = "O sócio informado não está cadastrado\n\n"
                for(socio in listaSocios){
                    if(socio.getCpf() == cpfSocio){
                        while(socio.getListaSociosDependentes().isNotEmpty()){  // Remove seus dependentes
                            listaSocios.remove(socio.getListaSociosDependentes().first())
                            socio.removerDependente()
                        }
                        listaSocios.remove(socio)
                        texto = "O sócio ${socio.getNome()} de CPF ${socio.getCpf()} foi removido do sistema\n\n"

                        break
                    }
                }
                println(texto)

            } else { println("Não há sócios cadastrados\n\n") }
        }
        else if(charRecebido == '3'){
            if(listaSocios.isNotEmpty()){
                println("Qual o CPF do Sócio que deseja listar?")
                val cpfSocio : String = reader.next()
                var texto = "O sócio informado não está cadastrado\n\n"
                for(s in listaSocios){
                    if(s.getCpf() == cpfSocio){
                        s.imprimirDados()
                    }
                }

            } else { println("Não há sócios cadastrados\n\n") }
        }
    }

    /*
    // Cadastros de teste!
    var socio1 = Socio("Ryan", "32225698004", TipoSocio.NORMAL)
    var socio2 = Socio("André", "3243564076", TipoSocio.VIP)
    var socio3 = Socio("Felipe", "5687958221", TipoSocio.PLUS)
    // Dependentes de testes!
    var socio4 = Socio("Gabriel", "32225698004", TipoSocio.DEPENDENTE)
    var socio5 = Socio("Leonardo", "32225698004", TipoSocio.DEPENDENTE)
    var socio6 = Socio("José", "32225698004", TipoSocio.DEPENDENTE)
    var socio7 = Socio("Josephs", "32225698004", TipoSocio.DEPENDENTE)


    // Caso de Testes!

    socio1.imprimirDados()
    socio2.imprimirDados()
    socio3.imprimirDados()
    println("")
    socio1.adicionarDependente(socio2)
    println("")
    socio2.adicionarDependente(socio4)
    socio2.adicionarDependente(socio5)
    socio4.imprimirDados()
    socio2.removerDependente()
    socio4.imprimirDados()
    println("")
    socio3.adicionarDependente(socio4)
    socio3.adicionarDependente(socio5)
    socio3.adicionarDependente(socio6)
    socio3.adicionarDependente(socio7)
    socio3.imprimirDados()
    println("")
    socio3.removerDependente()
    socio3.removerDependente(socio6)
    socio3.removerDependente(socio1)
    socio3.removerDependente()


     */

}