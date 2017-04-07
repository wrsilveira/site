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
@Table(name = "inscricao")
public class Inscricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(name = "indicador_internet", nullable = false)
    private boolean indicadorInternet;
    @Column(name = "indicador_ausente")
    private Boolean indicadorAusente;
    @NotNull
    @Column(name = "dt_inscricao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInscricao;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<Recurso> recursoList;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Candidato candidato;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Concurso concurso;
    @JoinColumn(name = "id_usuario_inscricao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioInscricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<LocalProvaInscricao> localProvaInscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<Boleto> boletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<Resultado> resultadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<InscricaoNecessidadeEspecial> inscricaoNecessidadeEspecialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscricao")
    private List<Pagamento> pagamentoList;

    public Inscricao() {
    }

    public Inscricao(Long id) {
        this.id = id;
    }

    public Inscricao(Long id, boolean indicadorInternet, Date dtInscricao, Date dtAtualizacao) {
        this.id = id;
        this.indicadorInternet = indicadorInternet;
        this.dtInscricao = dtInscricao;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIndicadorInternet() {
        return indicadorInternet;
    }

    public void setIndicadorInternet(boolean indicadorInternet) {
        this.indicadorInternet = indicadorInternet;
    }

    public Boolean getIndicadorAusente() {
        return indicadorAusente;
    }

    public void setIndicadorAusente(Boolean indicadorAusente) {
        this.indicadorAusente = indicadorAusente;
    }

    public Date getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(Date dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @XmlTransient
    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public Usuario getUsuarioInscricao() {
        return usuarioInscricao;
    }

    public void setUsuarioInscricao(Usuario usuarioInscricao) {
        this.usuarioInscricao = usuarioInscricao;
    }

    @XmlTransient
    public List<LocalProvaInscricao> getLocalProvaInscricaoList() {
        return localProvaInscricaoList;
    }

    public void setLocalProvaInscricaoList(List<LocalProvaInscricao> localProvaInscricaoList) {
        this.localProvaInscricaoList = localProvaInscricaoList;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @XmlTransient
    public List<InscricaoNecessidadeEspecial> getInscricaoNecessidadeEspecialList() {
        return inscricaoNecessidadeEspecialList;
    }

    public void setInscricaoNecessidadeEspecialList(List<InscricaoNecessidadeEspecial> inscricaoNecessidadeEspecialList) {
        this.inscricaoNecessidadeEspecialList = inscricaoNecessidadeEspecialList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
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
        if (!(object instanceof Inscricao)) {
            return false;
        }
        Inscricao other = (Inscricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Inscricao[ id=" + id + " ]";
    }
    
}
