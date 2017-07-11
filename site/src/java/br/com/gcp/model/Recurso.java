/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gcp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "recurso")
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(name = "nro_questao", nullable = false)
    private int nroQuestao;
    @NotNull
    @Column(name = "dt_recurso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRecurso;
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "texto_recurso", nullable = false, length = 2000)
    private String textoRecurso;
    @Size(max = 2000)
    @Column(name = "texto_resposta", length = 2000)
    private String textoResposta;
    @Column(name = "dt_resposta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtResposta;
    @Column(name = "dt_para_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtParaEnvio;
    @Column(name = "dt_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEnvio;
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

    public Recurso() {
    }

    public Recurso(Long id) {
        this.id = id;
    }

    public Recurso(Long id, int nroQuestao, Date dtRecurso, String textoRecurso, Date dtAtualizacao) {
        this.id = id;
        this.nroQuestao = nroQuestao;
        this.dtRecurso = dtRecurso;
        this.textoRecurso = textoRecurso;
        this.dtAtualizacao = dtAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNroQuestao() {
        return nroQuestao;
    }

    public void setNroQuestao(int nroQuestao) {
        this.nroQuestao = nroQuestao;
    }

    public Date getDtRecurso() {
        return dtRecurso;
    }

    public void setDtRecurso(Date dtRecurso) {
        this.dtRecurso = dtRecurso;
    }

    public String getTextoRecurso() {
        return textoRecurso;
    }

    public void setTextoRecurso(String textoRecurso) {
        this.textoRecurso = textoRecurso;
    }

    public String getTextoResposta() {
        return textoResposta;
    }

    public void setTextoResposta(String textoResposta) {
        this.textoResposta = textoResposta;
    }

    public Date getDtResposta() {
        return dtResposta;
    }

    public void setDtResposta(Date dtResposta) {
        this.dtResposta = dtResposta;
    }

    public Date getDtParaEnvio() {
        return dtParaEnvio;
    }

    public void setDtParaEnvio(Date dtParaEnvio) {
        this.dtParaEnvio = dtParaEnvio;
    }

    public Date getDtEnvio() {
        return dtEnvio;
    }

    public void setDtEnvio(Date dtEnvio) {
        this.dtEnvio = dtEnvio;
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
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Recurso[ id=" + id + " ]";
    }
    
}
