/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "gabarito")
public class Gabarito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 160)
    @Column(name = "resposta", nullable = false, length = 160)
    private String resposta;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "id_prova", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Prova prova;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public Gabarito() {
    }

    public Gabarito(Integer id) {
        this.id = id;
    }

    public Gabarito(Integer id, String resposta, Date dtAtualizacao) {
        this.id = id;
        this.resposta = resposta;
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gabarito)) {
            return false;
        }
        
        Gabarito other = (Gabarito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }        
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Gabarito[ idGabarito=" + id + " ]";
    }
    
}
