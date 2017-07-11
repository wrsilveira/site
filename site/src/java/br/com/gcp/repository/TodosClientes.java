/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.repository;

import br.com.gcp.model.Cliente;
import javax.ejb.Stateless;

/**
 *
 * @author willi
 */
@Stateless
public class TodosClientes extends AbstractRepository<Cliente> {

    public TodosClientes() {
        super(Cliente.class);
    }
}