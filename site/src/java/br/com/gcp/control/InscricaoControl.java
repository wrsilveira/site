/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.control;

import br.com.gcp.repository.TodosCargos;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author willi
 */
@ManagedBean
public class InscricaoControl implements Serializable {

    @EJB
    private TodosCargos todosCargos;

}
