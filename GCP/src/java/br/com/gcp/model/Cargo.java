/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taxa_inscricao", precision = 10, scale = 2)
    private BigDecimal taxaInscricao;
    @Column(name = "qtde_questoes")
    private Integer qtdeQuestoes;
    @Column(name = "nota_corte")
    private Integer notaCorte;
    @Column(name = "vagas")
    private Integer vagas;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private List<Inscricao> inscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private List<Gabarito> gabaritoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private List<MapaAlocacao> mapaAlocacaoList;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Concurso concurso;
    @JoinColumn(name = "id_escolaridade", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Escolaridade escolaridade;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public Cargo() {
    }

    public Cargo(Integer id) {
        this.id = id;
    }

    public Cargo(Integer id, String descricao, Date dtAtualizacao) {
        this.id = id;
        this.descricao = descricao;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getTaxaInscricao() {
        return taxaInscricao;
    }

    public void setTaxaInscricao(BigDecimal taxaInscricao) {
        this.taxaInscricao = taxaInscricao;
    }

    public Integer getQtdeQuestoes() {
        return qtdeQuestoes;
    }

    public void setQtdeQuestoes(Integer qtdeQuestoes) {
        this.qtdeQuestoes = qtdeQuestoes;
    }

    public Integer getNotaCorte() {
        return notaCorte;
    }

    public void setNotaCorte(Integer notaCorte) {
        this.notaCorte = notaCorte;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @XmlTransient
    public List<Inscricao> getInscricaoList() {
        return inscricaoList;
    }

    public void setInscricaoList(List<Inscricao> inscricaoList) {
        this.inscricaoList = inscricaoList;
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

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
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
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}
