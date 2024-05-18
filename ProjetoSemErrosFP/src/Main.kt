var personagensLidas = false
var ids = emptyArray<Int>()
var nomes = emptyArray<String>()
var generos = emptyArray<String>()
var cabelos = emptyArray<String>()
var cor = emptyArray<String>()
var olhos = emptyArray<String>()
var idPersonagem = 0
var idsEmJogo = emptyArray<Boolean>()
val invalida = "Pergunta invalida."
var sair = false
val strcabelo = "cabelo"
val strolhos = "olhos"
val strgenero = "genero"


fun gerarPersonagem(): Int {
    val idEscolhido = ids.random()
    return idEscolhido
}

fun inicio() {
    personagensLidas = false
    sair = false
    idPersonagem=0
    idsEmJogo = Array(ids.size) { true }

    do {
        println(obterMenu())
        val opcao = readln().toIntOrNull()

        when (opcao) {
            1 -> {

                if (personagensLidas) {
                    println("Bem vindo. Eu selecionei uma personagem. Tente adivinhar quem e fazendo perguntas sobre " +
                            "suas caracteristicas.")
                    println(obterMenuJogo())
                    menuEscolhas()
                } else {
                    println("Antes de iniciar o jogo tem de ler as personagens.\n(prima enter para voltar ao menu)")
                    readln()

                }
            }

            2 -> {
                lerPersonagens()
                println("Personagens lidas com sucesso.\n(prima enter para voltar ao menu)")
                personagensLidas = true
                readln()

            }

            0 -> {
                println("Ate logo!")
                return

            }

            else -> {
                println("Opcao invalida, tente novamente.")

            }
        }
    } while (opcao != 0)
}


fun menuEscolhas() {

    do {
        println("O que pretende fazer?")

        val opcao2 = readln()

        when (opcao2) {
            "sair" ->{

                idPersonagem=0
                idsEmJogo = Array(ids.size) { true }
                sair = true
            }

            "personagens" -> {
                println(mostrarPersonagens())
                menuEscolhas()

            }

            "perguntar" -> {

                println("Identifique o atributo e a sua designacao, por exemplo, \"genero masculino\".")
                val pergunta = readln()
                val remocoes = perguntar(pergunta, idPersonagem)

                if (remocoes == -1) {
                    println(invalida)
                    println(mostrarPersonagens())
                    menuEscolhas()
                } else {
                    if (remocoes == 1) {
                        println("Foi retirada do jogo $remocoes personagem.")
                        if (quantidadePersonagensEmJogo() == 1) {
                            println("Parabens! A personagem e o/a " + nomes[idPersonagem - 1] + ".")
                            idPersonagem=0
                            idsEmJogo = Array(ids.size) { true }
                            sair = true



                        } else {
                            println(mostrarPersonagens())
                            println(menuEscolhas())
                        }

                    } else {
                        println("Foram retiradas do jogo $remocoes personagens.")
                        if (quantidadePersonagensEmJogo() == 1) {
                            println("Parabens! A personagem e o/a " + nomes[idPersonagem - 1] + ".")
                            idPersonagem=0
                            idsEmJogo = Array(ids.size) { true }
                            sair = true

                        } else {
                            println(mostrarPersonagens())
                            menuEscolhas()
                        }


                    }
                }


            }

            else -> {
                println("Opcao invalida.")
                println(obterMenuJogo())
                menuEscolhas()


            }
        }
    } while (sair == false)
}

fun quantidadePersonagensEmJogo(): Int {
    var quantidade = 0
    for (i in idsEmJogo.indices) {
        if (idsEmJogo[i]) {
            quantidade++
        }
    }
    return quantidade
}

fun obterMenu(): String {

    return "\n##### QUEM E QUEM!!! #####\n\n" +
            "Escolha uma opcao:\n" +
            "1 - Jogar\n2 - Ler personagens\n0 - Sair\n"


}

fun obterMenuJogo(): String {
    return "\nEscolha uma opcao:\nperguntar   - para perguntar retirando personagens do jogo\npersonagens - para consultar as personagens em jogo\n" +
            "sair        - para sair do jogo\n"
}

fun lerPersonagens(): Boolean {

    val castanho = "castanho"
    ids = arrayOf(1, 2, 3, 4)
    nomes = arrayOf("Joao", "Mario", "Sofia", "Joana")
    generos = arrayOf("masculino", "masculino", "feminino", "feminino")
    cabelos = arrayOf("liso", "careca", "ondulado", "liso")
    cor = arrayOf(castanho, "-", castanho, castanho)
    olhos = arrayOf("castanhos", "azuis", "castanhos", "verdes")
    idsEmJogo = Array(ids.size) { true }
    idPersonagem = gerarPersonagem()
    return true
}

fun mostrarPersonagens(): String {
    var maxNomeLength = 4
    var maxGeneroLength = 7
    var maxCabeloLength = 6
    var maxCorLength = 3
    var maxOlhosLength = 5

    for (i in ids.indices) {
        if (idsEmJogo[i]) {
            maxNomeLength = maxOf(maxNomeLength, nomes[i].length)
            maxGeneroLength = maxOf(maxGeneroLength, generos[i].length)
            maxCabeloLength = maxOf(maxCabeloLength, cabelos[i].length)
            maxCorLength = maxOf(maxCorLength, cor[i].length)
            maxOlhosLength = maxOf(maxOlhosLength, olhos[i].length)
        }
    }

    val header = "# Nome".padEnd(maxNomeLength + 3) +
            "| Genero".padEnd(maxGeneroLength + 3) +
            "| Cabelo".padEnd(maxCabeloLength + 3) +
            "| Cor".padEnd(maxCorLength + 3) +
            "| Olhos".padEnd(maxOlhosLength + 2) + "\n"

    var personagensEmJogo = "Personagens em jogo:\n$header"

    var count = 0
    for (i in ids.indices) {
        if (idsEmJogo[i]) {
            val nome = nomes[i].padEnd(maxNomeLength)
            val genero = generos[i].padEnd(maxGeneroLength)
            val cabelo = cabelos[i].padEnd(maxCabeloLength)
            val corCabelo = cor[i].padEnd(maxCorLength)
            val olho = olhos[i].padEnd(maxOlhosLength)

            personagensEmJogo += "$count $nome | $genero | $cabelo | $corCabelo | $olho\n"
            count++
        }
    }

    return personagensEmJogo
}


fun verIds(id: Int): Int {
    for (i in 0..ids.size - 1) {
        if (id == ids[i]) {
            return i
        }
    }
    return -1
}

fun perguntar(pergunta: String, id: Int): Int {
    val partes = pergunta.split(" ")

    if (partes.size != 2) {
        return -1
    }

    val atributo = partes[0]
    val caracteristica = partes[1]
    var remocoes = 0

    val index = verIds(id)
    if (index == -1) {
        return -1
    }

    val atributoPersonagem = when (atributo) {
        strcabelo -> cabelos[index]
        strgenero -> generos[index]
        "cor" -> cor[index]
        strolhos -> olhos[index]
        else -> return -1
    }

    if (atributoPersonagem == caracteristica) {
        // Retirar personagens que não possuem a característica
        for (i in ids.indices) {
            if (idsEmJogo[i] && when (atributo) {
                        strcabelo -> cabelos[i]
                        strgenero -> generos[i]
                        "cor" -> cor[i]
                        strolhos -> olhos[i]
                        else -> ""
                    } != caracteristica) {
                retirarDeJogo(ids[i])
                remocoes++
            }
        }
    } else {
        // Retirar personagens que possuem a característica
        for (i in ids.indices) {
            if (idsEmJogo[i] && when (atributo) {
                        strcabelo -> cabelos[i]
                        strgenero -> generos[i]
                        "cor" -> cor[i]
                        strolhos -> olhos[i]
                        else -> ""
                    } == caracteristica) {
                retirarDeJogo(ids[i])
                remocoes++
            }
        }
    }

    return remocoes
}


fun retirarDeJogo(id: Int) {
    val posicao = verIds(id)
    if (posicao != -1) {
        idsEmJogo[posicao] = false
    }
}

fun main() {
    inicio()
}