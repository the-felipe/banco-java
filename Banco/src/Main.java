import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    //Banco ($) em Java

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<User> listaUsuarios = new ArrayList<User>();

        mostraMenu();
        mostraComandos();

        boolean running = true;
        while (running) {

            System.out.println("");
            System.out.print("Comando >> ");
            String comando = br.readLine();
            System.out.println("");

            switch (comando) {
                case ("1"): // verifica se o ID é inexistente, se sim, um novo usuário poderá ser adicionado com aquele ID
                    System.out.print("ID >> ");
                    int ID;

                    // esse modelo tenta passar o ID para inteiro, assim evitando erros
                    try {
                        ID = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int indiceAdd = encontraIndicePessoa(listaUsuarios, ID);
                    if (indiceAdd == -1 && ID > 0) {
                        System.out.print("Nome >> ");
                        String nome = br.readLine();
                        System.out.print("Sobrenome >> ");
                        String sobrenome = br.readLine();
                        float saldo = 0;
                        User usuario = new User(nome, sobrenome, ID, saldo);
                        listaUsuarios.add(usuario);
                        System.out.println("Usuário " + nome + "(" + ID + ")" + " adicionado!");
                    } else {
                        System.out.println("ID inválido!");
                    }

                    break;
                case ("2"): // busca pelo ID, e o remove o usuario da lista de users
                    System.out.print("ID do usuário >> ");
                    int IDremove;
                    try {
                        IDremove = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }
                    int indiceRemove = encontraIndicePessoa(listaUsuarios, IDremove);
                    if (indiceRemove >= 0) {
                        System.out.println("Usuário " + listaUsuarios.get(indiceRemove).getNome() + "(" + listaUsuarios.get(indiceRemove).getID()+ ")" + " removido!");
                        listaUsuarios.remove(listaUsuarios.get(indiceRemove));
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;
                case ("3"): // remove de um, e adiociona em outro, verificando saldos, evitando assim, quantias que os usuarios não possuem
                    System.out.print("ID do REMETENTE >> ");
                    int IDrem;
                    try {
                        IDrem = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }
                    System.out.print("ID do DESTINATÁRIO >> ");
                    int IDdes;
                    try {
                        IDdes = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int indiceRemetente = encontraIndicePessoa(listaUsuarios, IDrem);
                    int indiceDestinatario = encontraIndicePessoa(listaUsuarios, IDdes);

                    if (indiceRemetente < 0 || indiceDestinatario < 0 || indiceRemetente == indiceDestinatario) {
                        System.out.println("Falha na transação!");
                        break;
                    }

                    User remetente = listaUsuarios.get(indiceRemetente);
                    User destinatario = listaUsuarios.get(indiceDestinatario);

                    System.out.print(listaUsuarios.get(indiceRemetente).getNome() + "(" + listaUsuarios.get(indiceRemetente).getID()+ ")" + " => " + listaUsuarios.get(indiceDestinatario).getNome() + "(" + listaUsuarios.get(indiceDestinatario).getID()+ ")" + " -- Digite a quantia >> ");

                    float quantia = 0.0f;
                    try {
                        quantia = Float.parseFloat(br.readLine());
                    } catch (Exception e) {
                        System.out.println("Valor inválido!");
                        break;
                    }
                    if (quantia < 0) {
                        System.out.println("Valor inválido!");
                    } else {
                        if (remetente.getSaldo() >= quantia) {

                            remetente.setSaldo(remetente.getSaldo() - quantia);
                            destinatario.setSaldo(destinatario.getSaldo() + quantia);

                            System.out.println("Transação concluída!");
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                    }

                    break;
                case ("4"): // adiciona valor ao id passado, fazendo os devidos testes
                    System.out.print("ID do usuário >> ");
                    int IDadd;
                    try {
                        IDadd = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int indexUsuario = encontraIndicePessoa(listaUsuarios, IDadd);
                    if (indexUsuario < 0) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    User useradd = listaUsuarios.get(indexUsuario);

                    System.out.print(listaUsuarios.get(indexUsuario).getNome() + "(" + listaUsuarios.get(indexUsuario).getID()+ ")" + " -- Digite a quantia >> ");

                    float valorAdd = 0.0f;

                    try {
                        valorAdd = Float.parseFloat(br.readLine());
                    } catch (Exception e) {
                        System.out.println("Valor inválido!");
                        break;
                    }
                    if (valorAdd < 0) {
                        System.out.println("Valor inválido!");
                    } else {
                        useradd.setSaldo(useradd.getSaldo() + valorAdd);
                        System.out.println("Quantia adicionada!");
                    }

                    break;
                case ("5"): // remove valor do id passado, fazendo os devidos testes
                    System.out.print("ID do usuário >> ");
                    int IDretira;
                    try {
                        IDretira = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int retiraUsuario = encontraIndicePessoa(listaUsuarios, IDretira);
                    if (retiraUsuario < 0) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }
                    User userretira = listaUsuarios.get(retiraUsuario);

                    System.out.print(listaUsuarios.get(retiraUsuario).getNome() + "(" + listaUsuarios.get(retiraUsuario).getID()+ ")" + " -- Digite a quantia >> ");

                    float valorRetira = 0.0f;

                    try {
                        valorRetira = Float.parseFloat(br.readLine());
                    } catch (Exception e) {
                        System.out.println("Valor inválido!");
                        break;
                    }
                    if (valorRetira < 0) {
                        System.out.println("Valor inválido!");
                    } else {
                        userretira.setSaldo(userretira.getSaldo() - valorRetira);
                        System.out.println("Quantia retirada!");
                    }

                    break;
                case ("6"):
                    if (listaUsuarios.size() == 0){
                        System.out.println("Nenhum usuário foi adicionado ainda!");
                    } else {
                        for (User Usuario : listaUsuarios) {
                            System.out.println("Nome: " + Usuario.getNome());
                            System.out.println("Sobrenome: " + Usuario.getSobrenome());
                            System.out.printf("Saldo: R$%.2f\n", Usuario.getSaldo());
                            System.out.println("ID: " + Usuario.getID() + "\n");
                        }
                    }
                    break;
                case ("7"): // troca o nome do usuario passado
                    mostraComandos();
                    break;
                case ("8"):
                    System.out.print("ID do usuário >> ");
                    int IDtrocaNome;
                    try {
                        IDtrocaNome = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int numUsernome = encontraIndicePessoa(listaUsuarios, IDtrocaNome);
                    if (numUsernome < 0) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    User userNome = listaUsuarios.get(numUsernome);
                    System.out.println("Nome atual >> " + userNome.getNome());

                    System.out.print("Digite o novo nome >> ");
                    String newNome = br.readLine();

                    userNome.setNome(newNome);
                    System.out.println("Nome alterado!");

                    break;
                case ("9"): // troca o sobrenome do usuario passado
                    System.out.print("ID do usuário >> ");
                    int IDtrocaSobrenome;
                    try {
                        IDtrocaSobrenome = Integer.parseInt(br.readLine());
                    } catch (Exception e) {
                        System.out.println("O ID deve ser um número inteiro!");
                        continue;
                    }

                    int numUserSobrenome = encontraIndicePessoa(listaUsuarios, IDtrocaSobrenome);
                    if (numUserSobrenome < 0) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    User userSobreome = listaUsuarios.get(numUserSobrenome);
                    System.out.println("Sobrenome atual >> " + userSobreome.getSobrenome());

                    System.out.print("Digite o novo sobrenome >> ");
                    String newSobrenome = br.readLine();

                    userSobreome.setNome(newSobrenome);
                    System.out.println("Sobrenome alterado!");

                    break;
                case ("10"): // encerra o loop que lê comandos, consequentemente o programa
                    System.out.println("Encerrando...");
                    running = false;
                    break;
                default: // sout padrao para erros do usuário
                    System.out.println("Comando não encontrado!\nDigite 7 para ver os comandos disponíveis");
            }
        }
    }

// função que acha o id passado, senão retorna -1 (recebe um id e a lista)
    static int encontraIndicePessoa(ArrayList<User> usuarios, int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getID() == id) return i;
        }
        return -1;
    }

    static void mostraComandos() {
        System.out.println(
                "1 >> Adicionar usuário\n" +
                "2 >> Excluir usuário\n" +
                "3 >> Transferir quantia\n" +
                "4 >> Adicionar quantia\n" +
                "5 >> Remover quantia\n" +
                "6 >> Listar usuários\n" +
                "7 >> Listar comandos\n" +
                "8 >> Trocar nome\n" +
                "9 >> Trocar sobrenome\n" +
                "10 >> Sair"
        );
    }

    static void mostraMenu() {
        System.out.println("-----------------\n  BANCO EM JAVA  \n-----------------");
    }

}

