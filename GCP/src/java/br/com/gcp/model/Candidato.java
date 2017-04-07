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
@Table(name = "candidato")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 14)
    @Column(name = "cpf", length = 14)
    private String cpf;
    @Size(max = 14)
    @Column(name = "rg", length = 14)
    private String rg;
    @Size(max = 50)
    @Column(name = "endereco", length = 50)
    private String endereco;
    @Size(max = 8)
    @Column(name = "numero", length = 8)
    private String numero;
    @Size(max = 30)
    @Column(name = "complemento", length = 30)
    private String complemento;
    @Size(max = 40)
    @Column(name = "bairro", length = 40)
    private String bairro;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "email", nullable = false, length = 120)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "telefone_fixo", nullable = false, length = 16)
    private String telefoneFixo;
    @Size(max = 16)
    @Column(name = "telefone_celular", length = 16)
    private String telefoneCelular;
    @NotNull
    @Column(name = "indicador_funcao_jurado", nullable = false)
    private boolean indicadorFuncaoJurado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indicador_afro", nullable = false)
    private boolean indicadorAfro;
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "senha", nullable = false, length = 8)
    private String senha;
    @NotNull
    @Column(name = "dt_atualizacao_candidato", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacaoCandidato;
    @NotNull
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @NotNull
    @Column(name = "dt_ult_acesso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAcesso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidato")
    private List<Inscricao> inscricaoList;
    @JoinColumn(name = "id_deficiencia", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Deficiencia deficiencia;
    @JoinColumn(name = "id_escolaridade", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Escolaridade escolaridade;
    @JoinColumn(name = "id_usuario_atualizacao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAtualizacao;

    public Candidato() {
    }

    public Candidato(Long id) {
        this.id = id;
    }

    public Candidato(Long id, String cidade, String uf, String cep, String email, String telefoneFixo, boolean indicadorFuncaoJurado, boolean indicadorAfro, String senha, Date dtAtualizacaoCandidato, Date dtAtualizacao, Date dtUltAcesso) {
        this.id = id;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
        this.telefoneFixo = telefoneFixo;
        this.indicadorFuncaoJurado = indicadorFuncaoJurado;
        this.indicadorAfro = indicadorAfro;
        this.senha = senha;
        this.dtAtualizacaoCandidato = dtAtualizacaoCandidato;
        this.dtAtualizacao = dtAtualizacao;
        this.dtUltAcesso = dtUltAcesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public boolean getIndicadorFuncaoJurado() {
        return indicadorFuncaoJurado;
    }

    public void setIndicadorFuncaoJurado(boolean indicadorFuncaoJurado) {
        this.indicadorFuncaoJurado = indicadorFuncaoJurado;
    }

    public boolean getIndicadorAfro() {
        return indicadorAfro;
    }

    public void setIndicadorAfro(boolean indicadorAfro) {
        this.indicadorAfro = indicadorAfro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtAtualizacaoCandidato() {
        return dtAtualizacaoCandidato;
    }

    public void setDtAtualizacaoCandidato(Date dtAtualizacaoCandidato) {
        this.dtAtualizacaoCandidato = dtAtualizacaoCandidato;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Date getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(Date dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    @XmlTransient
    public List<Inscricao> getInscricaoList() {
        return inscricaoList;
    }

    public void setInscricaoList(List<Inscricao> inscricaoList) {
        this.inscricaoList = inscricaoList;
    }

    public Deficiencia getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Deficiencia deficiencia) {
        this.deficiencia = deficiencia;
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
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
