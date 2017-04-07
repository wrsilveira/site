/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "sala")
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "qtde_lugares", nullable = false)
    private int qtdeLugares;
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero", nullable = false, length = 45)
    private String numero;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private List<LocalProvaInscricao> localProvaInscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private List<MapaAlocacao> mapaAlocacaoList;
    @JoinColumn(name = "id_local", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Local local;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public Sala() {
    }

    public Sala(Integer id) {
        this.id = id;
    }

    public Sala(Integer id, int qtdeLugares, String numero, Date dtAtualizacao) {
        this.id = id;
        this.qtdeLugares = qtdeLugares;
        this.numero = numero;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQtdeLugares() {
        return qtdeLugares;
    }

    public void setQtdeLugares(int qtdeLugares) {
        this.qtdeLugares = qtdeLugares;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @XmlTransient
    public List<LocalProvaInscricao> getLocalProvaInscricaoList() {
        return localProvaInscricaoList;
    }

    public void setLocalProvaInscricaoList(List<LocalProvaInscricao> localProvaInscricaoList) {
        this.localProvaInscricaoList = localProvaInscricaoList;
    }

    @XmlTransient
    public List<MapaAlocacao> getMapaAlocacaoList() {
        return mapaAlocacaoList;
    }

    public void setMapaAlocacaoList(List<MapaAlocacao> mapaAlocacaoList) {
        this.mapaAlocacaoList = mapaAlocacaoList;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
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
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Sala[ id=" + id + " ]";
    }
    
}
