/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "mapa_alocacao")
public class MapaAlocacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "qtde_candidato", nullable = false)
    private int qtdeCandidato;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    private int dtAtualizacao;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "id_local", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Local local;
    @JoinColumn(name = "id_prova", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Prova prova;
    @JoinColumn(name = "id_sala", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Sala sala;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public MapaAlocacao() {
    }

    public MapaAlocacao(Integer id) {
        this.id = id;
    }

    public MapaAlocacao(Integer id, int qtdeCandidato, int dtAtualizacao) {
        this.id = id;
        this.qtdeCandidato = qtdeCandidato;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQtdeCandidato() {
        return qtdeCandidato;
    }

    public void setQtdeCandidato(int qtdeCandidato) {
        this.qtdeCandidato = qtdeCandidato;
    }

    public int getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(int dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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
        if (!(object instanceof MapaAlocacao)) {
            return false;
        }
        MapaAlocacao other = (MapaAlocacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.MapaAlocacao[ id=" + id + " ]";
    }
    
}
