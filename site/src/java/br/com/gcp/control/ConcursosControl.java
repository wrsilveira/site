/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.gcp.model.Concurso;
import br.com.gcp.repository.TodosConcursos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author willi
 * Controle dos concursos listados no site
 */

@ManagedBean
@RequestScoped
public class ConcursosControl implements Serializable{
    
    @EJB
    TodosConcursos todosConcursos;
    private List<Concurso> listaConcurso = new ArrayList<Concurso>();
    
    @PostConstruct
    public void listarConcursos(){
        List<Concurso> lista = new ArrayList<Concurso>();
        listaConcurso = todosConcursos.findAll();
    }

    public TodosConcursos getTodosConcursos() {
        return todosConcursos;
    }

    public void setTodosConcursos(TodosConcursos todosConcursos) {
        this.todosConcursos = todosConcursos;
    }

    public List<Concurso> getListaConcurso() {
        return listaConcurso;
    }

    public void setListaConcurso(List<Concurso> listaConcurso) {
        this.listaConcurso = listaConcurso;
    }
}
