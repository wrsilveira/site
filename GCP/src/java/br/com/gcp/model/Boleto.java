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
@Table(name = "boleto")

public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "nosso_numero", nullable = false)
    private long nossoNumero;
    @NotNull
    @Column(name = "valor", nullable = false)
    private long valor;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @NotNull
    @Column(name = "dt_geracao", nullable = false)
    private int dtGeracao;
    @JoinColumn(name = "id_inscricao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Inscricao inscricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boleto")
    private List<Pagamento> pagamentoList;

    public Boleto() {
    }

    public Boleto(Integer id) {
        this.id = id;
    }

    public Boleto(Integer id, long nossoNumero, long valor, int dtGeracao) {
        this.id = id;
        this.nossoNumero = nossoNumero;
        this.valor = valor;
        this.dtGeracao = dtGeracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getDtGeracao() {
        return dtGeracao;
    }

    public void setDtGeracao(int dtGeracao) {
        this.dtGeracao = dtGeracao;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
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
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Long.toString(this.nossoNumero);
    }
    
}
