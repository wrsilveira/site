/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "resultado")
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "nota_teorica", nullable = false, precision = 10, scale = 2)
    private BigDecimal notaTeorica;
    @NotNull
    @Column(name = "nota_pratica", nullable = false, precision = 10, scale = 2)
    private BigDecimal notaPratica;
    @NotNull
    @Column(name = "nota_titulos", nullable = false, precision = 10, scale = 2)
    private BigDecimal notaTitulos;
    @NotNull
    @Column(name = "nota_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal notaFinal;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @JoinColumn(name = "id_inscricao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Inscricao inscricao;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public Resultado() {
    }

    public Resultado(Integer id) {
        this.id = id;
    }

    public Resultado(Integer id, BigDecimal notaTeorica, BigDecimal notaPratica, BigDecimal notaTitulos, BigDecimal notaFinal, Date dtAtualizacao) {
        this.id = id;
        this.notaTeorica = notaTeorica;
        this.notaPratica = notaPratica;
        this.notaTitulos = notaTitulos;
        this.notaFinal = notaFinal;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getNotaTeorica() {
        return notaTeorica;
    }

    public void setNotaTeorica(BigDecimal notaTeorica) {
        this.notaTeorica = notaTeorica;
    }

    public BigDecimal getNotaPratica() {
        return notaPratica;
    }

    public void setNotaPratica(BigDecimal notaPratica) {
        this.notaPratica = notaPratica;
    }

    public BigDecimal getNotaTitulos() {
        return notaTitulos;
    }

    public void setNotaTitulos(BigDecimal notaTitulos) {
        this.notaTitulos = notaTitulos;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
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
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Resultado[ id=" + id + " ]";
    }
    
}
