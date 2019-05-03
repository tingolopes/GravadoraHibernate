package br.edu.ifms.controller;

import br.edu.ifms.dao.DaoGenerico;
import br.edu.ifms.model.Usuario;
import br.edu.ifms.view.UsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class UsuarioController {

    private Usuario usuario;
    private UsuarioView usuarioView;

    public UsuarioController(Usuario usuario, UsuarioView usuarioView) {
        this.usuario = usuario;
        this.usuarioView = usuarioView;
        this.usuarioView.addButtonListener(new ButtonListener());
        DefaultTableModel model = (DefaultTableModel) this.usuarioView.jTableUsuarios.getModel();
        this.usuarioView.jTableUsuarios.setRowSorter(new TableRowSorter(model));
        fillTable();

    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) usuarioView.jTableUsuarios.getModel();
        model.setNumRows(0);
        DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

        for (Usuario u : daoUsuario.listaTodos(Usuario.class)) {
            model.addRow(new Object[]{
                u.getId(),
                u.getUsuario(),
                u.getLogin()
            });
        }
    }

    public void limparCampos() {
        usuarioView.setUsuario("");
        usuarioView.setLogin("");
        usuarioView.setSenha("");
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == usuarioView.btnCadastrar) {
                usuario = new Usuario();
                DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

                if (usuarioView.getUsuario().isEmpty() || usuarioView.getLogin().isEmpty() || usuarioView.getSenha().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {

                    usuario.setUsuario(usuarioView.getUsuario());
                    usuario.setLogin(usuarioView.getLogin());
                    usuario.setSenha(usuarioView.getSenha());

                    daoUsuario.saveOrUpdate(usuario);

                    limparCampos();
                    fillTable();

                }

            }

            if (ae.getSource() == usuarioView.btnEditar) {
                System.out.println("EDITAR");
            }

            if (ae.getSource() == usuarioView.btnDeletar) {
                System.out.println("DELETAR");
            }

            if (ae.getSource() == usuarioView.btnLimpar) {
                limparCampos();
            }
        }
    }
}
