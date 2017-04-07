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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "nome", nullable = false, length = 90)
    private String nome;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nome_acesso", nullable = false, length = 20)
    private String nomeAcesso;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @NotNull
    @Column(name = "qtd_tentativas_fracassadas", nullable = false)
    private int qtdTentativasFracassadas;
    @NotNull
    @Column(name = "dt_ultima_autenticacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAutenticacao;
    @NotNull
    @Column(name = "dt_inclusao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInclusao;
    @JoinColumn(name = "id_usuario_inclusao", referencedColumnName = "id")    
    @ManyToOne
    private Usuario UsuarioInclusao;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioAtualizacao;  
    @NotNull
    @Column(name = "indicador_alterar_senha", nullable = false)
    private boolean indicadorAlterarSenha;
    @NotNull
    @Column(name = "indicador_forcar_senha", nullable = false)
    private boolean indicadorForcarSenha;
    @NotNull
    @Column(name = "indicador_ativo", nullable = false)
    private boolean indicadorAtivo;
    @NotNull
    @Column(name = "dt_alteracao_senha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAlteracaoSenha;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Recurso> recursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<TipoPublicacao> tipoPublicacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioInscricao")
    private List<Inscricao> inscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<LocalProvaInscricao> localProvaInscricaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Prova> provaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Local> localList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<PerfilAcesso> perfilAcessoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<PerfilAcessoUsuario> perfilAcessoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<PerfilAcessoUsuario> perfilAcessoUsuarioList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<NecessidadeEspecial> necessidadeEspecialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Gabarito> gabaritoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<MapaAlocacao> mapaAlocacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Escolaridade> escolaridadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Candidato> candidatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Cargo> cargoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Concurso> concursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<TipoProva> tipoProvaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Resultado> resultadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Publicacao> publicacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Sala> salaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAtualizacao")
    private List<Pagamento> pagamentoList;
  

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String nome, String senha, String nomeAcesso, String email, int qtdTentativasFracassadas, Date dtUltimaAutenticacao, Date dtInclusao, Usuario usuarioInclusao, Date dtAtualizacao, Usuario usuarioAtualizacao, boolean indicadorAlterarSenha, boolean indicadorForcarSenha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.nomeAcesso = nomeAcesso;
        this.email = email;
        this.qtdTentativasFracassadas = qtdTentativasFracassadas;
        this.dtUltimaAutenticacao = dtUltimaAutenticacao;
        this.dtInclusao = dtInclusao;
        this.UsuarioInclusao = usuarioInclusao;
        this.dtAtualizacao = dtAtualizacao;
        this.usuarioAtualizacao = usuarioAtualizacao;
        this.indicadorAlterarSenha = indicadorAlterarSenha;
        this.indicadorForcarSenha = indicadorForcarSenha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeAcesso() {
        return nomeAcesso;
    }

    public void setNomeAcesso(String nomeAcesso) {
        this.nomeAcesso = nomeAcesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQtdTentativasFracassadas() {
        return qtdTentativasFracassadas;
    }

    public void setQtdTentativasFracassadas(int qtdTentativasFracassadas) {
        this.qtdTentativasFracassadas = qtdTentativasFracassadas;
    }

    public Date getDtUltimaAutenticacao() {
        return dtUltimaAutenticacao;
    }

    public void setDtUltimaAutenticacao(Date dtUltimaAutenticacao) {
        this.dtUltimaAutenticacao = dtUltimaAutenticacao;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Usuario getUsuarioInclusao() {
        return UsuarioInclusao;
    }

    public void setUsuarioInclusao(Usuario UsuarioInclusao) {
        this.UsuarioInclusao = UsuarioInclusao;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public boolean getIndicadorAlterarSenha() {
        return indicadorAlterarSenha;
    }

    public void setIndicadorAlterarSenha(boolean indicadorAlterarSenha) {
        this.indicadorAlterarSenha = indicadorAlterarSenha;
    }

    public boolean getIndicadorForcarSenha() {
        return indicadorForcarSenha;
    }

    public void setIndicadorForcarSenha(boolean indicadorForcarSenha) {
        this.indicadorForcarSenha = indicadorForcarSenha;
    }
    
    public boolean getIndicadorAtivo() {
        return indicadorAtivo;
    }

    public void setIndicadorAtivo(boolean indicadorAtivo) {
        this.indicadorAtivo = indicadorAtivo;
    }    

    @XmlTransient
    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    @XmlTransient
    public List<TipoPublicacao> getTipoPublicacaoList() {
        return tipoPublicacaoList;
    }

    public void setTipoPublicacaoList(List<TipoPublicacao> tipoPublicacaoList) {
        this.tipoPublicacaoList = tipoPublicacaoList;
    }

    @XmlTransient
    public List<Inscricao> getInscricaoList() {
        return inscricaoList;
    }

    public void setInscricaoList(List<Inscricao> inscricaoList) {
        this.inscricaoList = inscricaoList;
    }

    @XmlTransient
    public List<LocalProvaInscricao> getLocalProvaInscricaoList() {
        return localProvaInscricaoList;
    }

    public void setLocalProvaInscricaoList(List<LocalProvaInscricao> localProvaInscricaoList) {
        this.localProvaInscricaoList = localProvaInscricaoList;
    }

    @XmlTransient
    public List<Prova> getProvaList() {
        return provaList;
    }

    public void setProvaList(List<Prova> provaList) {
        this.provaList = provaList;
    }

    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @XmlTransient
    public List<PerfilAcesso> getPerfilAcessoList() {
        return perfilAcessoList;
    }

    public void setPerfilAcessoList(List<PerfilAcesso> perfilAcessoList) {
        this.perfilAcessoList = perfilAcessoList;
    }

    @XmlTransient
    public List<PerfilAcessoUsuario> getPerfilAcessoUsuarioList() {
        return perfilAcessoUsuarioList;
    }

    public void setPerfilAcessoUsuarioList(List<PerfilAcessoUsuario> perfilAcessoUsuarioList) {
        this.perfilAcessoUsuarioList = perfilAcessoUsuarioList;
    }

    @XmlTransient
    public List<PerfilAcessoUsuario> getPerfilAcessoUsuarioList1() {
        return perfilAcessoUsuarioList1;
    }

    public void setPerfilAcessoUsuarioList1(List<PerfilAcessoUsuario> perfilAcessoUsuarioList1) {
        this.perfilAcessoUsuarioList1 = perfilAcessoUsuarioList1;
    }

    @XmlTransient
    public List<NecessidadeEspecial> getNecessidadeEspecialList() {
        return necessidadeEspecialList;
    }

    public void setNecessidadeEspecialList(List<NecessidadeEspecial> necessidadeEspecialList) {
        this.necessidadeEspecialList = necessidadeEspecialList;
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

    @XmlTransient
    public List<Escolaridade> getEscolaridadeList() {
        return escolaridadeList;
    }

    public void setEscolaridadeList(List<Escolaridade> escolaridadeList) {
        this.escolaridadeList = escolaridadeList;
    }

    @XmlTransient
    public List<Candidato> getCandidatoList() {
        return candidatoList;
    }

    public void setCandidatoList(List<Candidato> candidatoList) {
        this.candidatoList = candidatoList;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @XmlTransient
    public List<Concurso> getConcursoList() {
        return concursoList;
    }

    public void setConcursoList(List<Concurso> concursoList) {
        this.concursoList = concursoList;
    }

    @XmlTransient
    public List<TipoProva> getTipoProvaList() {
        return tipoProvaList;
    }

    public void setTipoProvaList(List<TipoProva> tipoProvaList) {
        this.tipoProvaList = tipoProvaList;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Sala> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    public Date getDtAlteracaoSenha() {
        return dtAlteracaoSenha;
    }

    public void setDtAlteracaoSenha(Date dtAlteracaoSenha) {
        this.dtAlteracaoSenha = dtAlteracaoSenha;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isIndicadorAlterarSenha() {
        return indicadorAlterarSenha;
    }

    public boolean isIndicadorForcarSenha() {
        return indicadorForcarSenha;
    }

    public boolean isIndicadorAtivo() {
        return indicadorAtivo;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNomeAcesso();
    }
    
}
