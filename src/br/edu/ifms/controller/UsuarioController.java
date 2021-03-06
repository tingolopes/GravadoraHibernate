package br.edu.ifms.controller;

import br.edu.ifms.dao.DaoGenerico;
import br.edu.ifms.model.Usuario;
import br.edu.ifms.view.UsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        this.usuarioView.addTableListener(new TableListener());
        //DefaultTableModel model = (DefaultTableModel) this.usuarioView.jTableUsuarios.getModel();
        DefaultTableModel model = (DefaultTableModel) this.usuarioView.getTableM();
        
        //this.usuarioView.jTableUsuarios.setRowSorter(new TableRowSorter(model));
        this.usuarioView.getTable().setRowSorter(new TableRowSorter(model));
        fillTable();

    }

    public void fillTable() {
       // DefaultTableModel model = (DefaultTableModel) usuarioView.jTableUsuarios.getModel();
        DefaultTableModel model = (DefaultTableModel) usuarioView.getTableM();
        model.setNumRows(0);
        DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

        for (Usuario u : daoUsuario.listaTodos(Usuario.class)) {
            model.addRow(new Object[]{
                u.getId(),
                u.getUsuario(),
                u.getLogin(),});
        }
    }

    public void clearFields() {
        usuarioView.setUsuario("");
        usuarioView.setLogin("");
        usuarioView.setSenha("");
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == usuarioView.btnCadastrar) {
                cadastrarUsuario();
            }

            if (ae.getSource() == usuarioView.btnEditar) {
                editarUsuario();
            }

            if (ae.getSource() == usuarioView.btnDeletar) {
                deletarUsuario();
            }

            if (ae.getSource() == usuarioView.btnLimpar) {
                clearFields();
            }
        }
    }

    public void cadastrarUsuario() {
        usuario = new Usuario();
        DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

        if (usuarioView.getUsuario().isEmpty() || usuarioView.getLogin().isEmpty() || usuarioView.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {

            usuario.setUsuario(usuarioView.getUsuario());
            usuario.setLogin(usuarioView.getLogin());
            usuario.setSenha(usuarioView.getSenha());
            daoUsuario.saveOrUpdate(usuario);

            clearFields();
            fillTable();

        }
    }

    public void editarUsuario() {

        //if (usuarioView.jTableUsuarios.getSelectedRow() != -1) {
        if (usuarioView.getTable().getSelectedRow() != -1) {
            usuario = new Usuario();
            DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();

            //Usuario u = daoUsuario.findById(Usuario.class, (Long) usuarioView.jTableUsuarios.getValueAt(usuarioView.jTableUsuarios.getSelectedRow(), 0));
            Usuario u = daoUsuario.findById(Usuario.class, (Long) usuarioView.getTable().getValueAt(usuarioView.getTable().getSelectedRow(), 0));

            if (usuarioView.getSenha().equals("")) {
                usuario.setSenha(u.getSenha());
            } else {
                usuario.setSenha(usuarioView.getSenha());
            }

            if (usuarioView.getLogin().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo login não pode estar vazio!");
            } else {
                usuario.setUsuario(usuarioView.getUsuario());
                usuario.setLogin(usuarioView.getLogin());

                //usuario.setId((long) usuarioView.jTableUsuarios.getValueAt(usuarioView.jTableUsuarios.getSelectedRow(), 0));
                usuario.setId((long) usuarioView.getTable().getValueAt(usuarioView.getTable().getSelectedRow(), 0));
                daoUsuario.saveOrUpdate(usuario);

                clearFields();
                fillTable();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum usuário!");
        }
    }

    public void deletarUsuario() {
        //if (usuarioView.jTableUsuarios.getSelectedRow() != -1) {
        if (usuarioView.getTable().getSelectedRow() != -1) {

            usuario = new Usuario();
            DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();
            //usuario.setId((long) usuarioView.jTableUsuarios.getValueAt(usuarioView.jTableUsuarios.getSelectedRow(), 0));
            usuario.setId((long) usuarioView.getTable().getValueAt(usuarioView.getTable().getSelectedRow(), 0));

            daoUsuario.remove(Usuario.class, usuario.getId());
            clearFields();
            fillTable();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum usuário para deletar!");
        }
    }

    public class TableListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            //if (usuarioView.jTableUsuarios == me.getSource()) {
            if (usuarioView.getTable() == me.getSource()) {
                //if (usuarioView.jTableUsuarios.getSelectedRow() != -1) {
                if (usuarioView.getTable().getSelectedRow() != -1) {
                    //int selected = usuarioView.jTableUsuarios.getSelectedRow();
                    int selected = usuarioView.getTable().getSelectedRow();

                    //usuarioView.setUsuario(usuarioView.jTableUsuarios.getValueAt(selected, 1).toString());
                    usuarioView.setUsuario(usuarioView.getTable().getValueAt(selected, 1).toString());
                    //usuarioView.setLogin(usuarioView.jTableUsuarios.getValueAt(selected, 2).toString());
                    usuarioView.setLogin(usuarioView.getTable().getValueAt(selected, 2).toString());
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //if (usuarioView.jTableUsuarios == me.getSource()) {
            if (usuarioView.getTable() == me.getSource()) {
                //if (usuarioView.jTableUsuarios.getSelectedRow() != -1) {
                if (usuarioView.getTable().getSelectedRow() != -1) {
                    //int selected = usuarioView.jTableUsuarios.getSelectedRow();
                    int selected = usuarioView.getTable().getSelectedRow();

                    //usuarioView.setUsuario(usuarioView.jTableUsuarios.getValueAt(selected, 1).toString());
                    usuarioView.setUsuario(usuarioView.getTable().getValueAt(selected, 1).toString());
                   // usuarioView.setLogin(usuarioView.jTableUsuarios.getValueAt(selected, 2).toString());
                    usuarioView.setLogin(usuarioView.getTable().getValueAt(selected, 2).toString());
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            System.out.println("");
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            System.out.println("");
        }

        @Override
        public void mouseExited(MouseEvent me) {
            System.out.println("");
        }

    }
}
