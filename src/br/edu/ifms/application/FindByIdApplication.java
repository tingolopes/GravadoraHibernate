package br.edu.ifms.application;

import br.edu.ifms.dao.DaoGenerico;
import br.edu.ifms.model.Usuario;


public class FindByIdApplication {

    public static void main(String[] args) {


        DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

        Usuario usuario = daoUsuario.findById(Usuario.class, 2L);

        System.out.println("### Entidade Usuario encontrada ###");
        System.out.println("ID: " + usuario.getId());
        System.out.println("NOME: " + usuario.getUsuario());

        System.out.println("");

//        System.out.println("### Entidade Carro encontrada ###");
//        System.out.println("ID: " + usuario.getId());
//        System.out.println("MODELO: " + usuario.getModelo());

        //tres pessoas que comecam com Raphael!!! Deu certo!!!
//        System.out.println(daoPessoa.findByNome(Pessoa.class, "Raphael", "nome").toString());
//        for (Pessoa p : daoPessoa.findByNome(Pessoa.class, "Raphael", "nome")) {
//            //tras a lista de pessoas com esse nome
//            System.out.println(p.getId() + " " + p.getNome() + " " + p.getIdade());
//        }

        for (Usuario u : daoUsuario.findByNome(Usuario.class, "admin", "login")) {
            //tras a lista de carros com esse nome
            System.out.println(u.getId() + " " + u.getUsuario()+ " " + u.getLogin());
        }

    }

}
