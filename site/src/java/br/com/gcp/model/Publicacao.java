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
@Table(name = "publicacao")
public class Publicacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50,message = "Informe o título")
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;
    @NotNull(message = "Informe a dt início")
    @Column(name = "dt_inicio_publicacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicioPublicacao;
    @Column(name = "dt_fim_publicacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFimPublicacao;
    @Basic(optional = false)
    @NotNull(message="Informe o link")
    @Size(min = 1, max = 200,message="Informe o link")
    @Column(name = "link", nullable = false, length = 200)
    private String link;
    @NotNull(message="Informe o campo imdicador de arquivo")
    @Column(name = "indicador_arquivo", nullable = false)
    private boolean indicadorArquivo;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @NotNull
    @Column(name = "indicador_protegida", nullable = false)
    private boolean indicadorProtegida;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Concurso concurso;
    @NotNull(message="Informe o tipo de publicação.")
    @JoinColumn(name = "id_tipo_publicacao", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private TipoPublicacao tipoPublicacao;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @ManyToOne
    private Cargo cargo;    

    public Publicacao() {
    }

    public Publicacao(Integer id) {
        this.id = id;
    }

    public Publicacao(Integer id, String titulo, Date dtInicioPublicacao, String link, boolean indicadorArquivo, Date dtAtualizacao, boolean indicadorProtegida) {
        this.id = id;
        this.titulo = titulo;
        this.dtInicioPublicacao = dtInicioPublicacao;
        this.link = link;
        this.indicadorArquivo = indicadorArquivo;
        this.dtAtualizacao = dtAtualizacao;
        this.indicadorProtegida = indicadorProtegida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDtInicioPublicacao() {
        return dtInicioPublicacao;
    }

    public void setDtInicioPublicacao(Date dtInicioPublicacao) {
        this.dtInicioPublicacao = dtInicioPublicacao;
    }

    public Date getDtFimPublicacao() {
        return dtFimPublicacao;
    }

    public void setDtFimPublicacao(Date dtFimPublicacao) {
        this.dtFimPublicacao = dtFimPublicacao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean getIndicadorArquivo() {
        return indicadorArquivo;
    }

    public void setIndicadorArquivo(boolean indicadorArquivo) {
        this.indicadorArquivo = indicadorArquivo;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public boolean getIndicadorProtegida() {
        return indicadorProtegida;
    }

    public void setIndicadorProtegida(boolean indicadorProtegida) {
        this.indicadorProtegida = indicadorProtegida;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public TipoPublicacao getTipoPublicacao() {
        return tipoPublicacao;
    }

    public void setTipoPublicacao(TipoPublicacao tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        if (!(object instanceof Publicacao)) {
            return false;
        }
        Publicacao other = (Publicacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Publicacao[ id=" + id + " ]";
    }
    
}
