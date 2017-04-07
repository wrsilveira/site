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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wrssilva
 */
@Entity
@Table(name = "concurso")
public class Concurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @NotNull
    @Column(name = "dt_inscricao_inicio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInscricaoInicio;
    @NotNull
    @Column(name = "dt_inscricao_fim", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInscricaoFim;
    @NotNull
    @Column(name = "dt_venc_boleto", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtVencBoleto;
    @Size(max = 20)
    @Column(name = "referencia", length = 20)
    private String referencia;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concurso")
    private List<Inscricao> inscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concurso")
    private List<Prova> provaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concurso")
    private List<Cargo> cargoList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concurso")
    private List<Publicacao> publicacaoList;
    @Column(name = "inscricao_online")
    private boolean inscricaoOnline;
    @Column(name = "inscricao_presencial")
    private boolean inscricaoPresencial;
    @Column(name = "exibir_opcao_afro")
    private boolean exibirOpcaoAfro;
    @Column(name = "id_situacao")
    private Integer idSituacao;
    @Column(name = "nro_ini_inscricao_online")
    private Integer nroIniInscricaoOnline;    
    
    public Concurso() {
    }

    public Concurso(Integer id) {
        this.id = id;
    }

    public Concurso(Integer id, String titulo, Date dtInscricaoInicio, Date dtInscricaoFim, Date dtVencBoleto, Date dtAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.dtInscricaoInicio = dtInscricaoInicio;
        this.dtInscricaoFim = dtInscricaoFim;
        this.dtVencBoleto = dtVencBoleto;
        this.dtAtualizacao = dtAtualizacao;
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

    public Date getDtInscricaoInicio() {
        return dtInscricaoInicio;
    }

    public void setDtInscricaoInicio(Date dtInscricaoInicio) {
        this.dtInscricaoInicio = dtInscricaoInicio;
    }

    public Date getDtInscricaoFim() {
        return dtInscricaoFim;
    }

    public void setDtInscricaoFim(Date dtInscricaoFim) {
        this.dtInscricaoFim = dtInscricaoFim;
    }

    public Date getDtVencBoleto() {
        return dtVencBoleto;
    }

    public void setDtVencBoleto(Date dtVencBoleto) {
        this.dtVencBoleto = dtVencBoleto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
    public List<Prova> getProvaList() {
        return provaList;
    }

    public void setProvaList(List<Prova> provaList) {
        this.provaList = provaList;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }

    public boolean isInscricaoOnline() {
        return inscricaoOnline;
    }

    public void setInscricaoOnline(boolean inscricaoOnline) {
        this.inscricaoOnline = inscricaoOnline;
    }

    public boolean isInscricaoPresencial() {
        return inscricaoPresencial;
    }

    public void setInscricaoPresencial(boolean inscricaoPresencial) {
        this.inscricaoPresencial = inscricaoPresencial;
    }

    public boolean isExibirOpcaoAfro() {
        return exibirOpcaoAfro;
    }

    public void setExibirOpcaoAfro(boolean exibirOpcaoAfro) {
        this.exibirOpcaoAfro = exibirOpcaoAfro;
    }

    public Integer getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(Integer idSituacao) {
        this.idSituacao = idSituacao;
    }

    public Integer getNroIniInscricaoOnline() {
        return nroIniInscricaoOnline;
    }

    public void setNroIniInscricaoOnline(Integer nroIniInscricaoOnline) {
        this.nroIniInscricaoOnline = nroIniInscricaoOnline;
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
        if (!(object instanceof Concurso)) {
            return false;
        }
        Concurso other = (Concurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gcp.model.Concurso[ id=" + id + " ]";
    }

}
