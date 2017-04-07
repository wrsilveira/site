/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "prova")
public class Prova implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prova")
    private List<LocalProvaInscricao> localProvaInscricaoList;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Concurso concurso;
    @JoinColumn(name = "id_tipo_prova", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TipoProva tipoProva;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prova")
    private List<Local> localList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prova")
    private List<Gabarito> gabaritoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prova")
    private List<MapaAlocacao> mapaAlocacaoList;

    public Prova() {
    }

    public Prova(Integer id) {
        this.id = id;
    }

    public Prova(Integer id, Date dtAtualizacao) {
        this.id = id;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public TipoProva getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(TipoProva tipoProva) {
        this.tipoProva = tipoProva;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

  
    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @XmlTransient
    public List<Gabarito> getGabaritoList() {
        return gabaritoList;
    }

    public void setGabaritoList(List<Gabarito> gabaritoList) {
        this.gabaritoList = gabaritoList;
    }

    @XmlTransient
    public List<MapaAlocacao> getMapaAlocacaoList() {
        return mapaAlocacaoList;
    }

    public void setMapaAlocacaoList(List<MapaAlocacao> mapaAlocacaoList) {
        this.mapaAlocacaoList = mapaAlocacaoList;
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
        if (!(object instanceof Prova)) {
            return false;
        }
        Prova other = (Prova) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Prova[ id=" + id + " ]";
    }
    
}
