import { useEffect, useState } from 'react'
import Breadcrumb from '../components/Breadcrumb.jsx'
import { listarAuditoria } from '../services/api.js'

const ROTULOS_ACAO = {
  LOGIN: 'Acesso',
  CRIACAO: 'Criação',
  ALTERACAO: 'Alteração',
  EXCLUSAO: 'Exclusão',
}

const CLASSES_ACAO = {
  LOGIN: 'bg-info text-dark',
  CRIACAO: 'bg-success',
  ALTERACAO: 'bg-warning text-dark',
  EXCLUSAO: 'bg-danger',
}

function formatarData(dataHora) {
  if (!dataHora) return '—'
  return new Intl.DateTimeFormat('pt-BR', {
    dateStyle: 'short',
    timeStyle: 'medium',
  }).format(new Date(dataHora))
}

function recursoDaRota(rota) {
  const partes = rota?.split('/').filter(Boolean) || []
  const indiceImobiliaria = partes.indexOf('imobiliarias')
  return indiceImobiliaria >= 0 ? (partes[indiceImobiliaria + 2] || 'Geral') : 'Sistema'
}

function AuditoriaPage() {
  const [pagina, setPagina] = useState(0)
  const [resultado, setResultado] = useState({ content: [], totalPages: 0, totalElements: 0 })
  const [carregando, setCarregando] = useState(true)
  const [erro, setErro] = useState(null)

  useEffect(() => {
    carregar(pagina)
  }, [pagina])

  async function carregar(numeroPagina) {
    setCarregando(true)
    setErro(null)
    try {
      const resposta = await listarAuditoria(numeroPagina, 20)
      setResultado(resposta.data)
    } catch (e) {
      if (e.response?.status === 403) {
        setErro('A consulta de auditoria é permitida somente para administradores e gerentes.')
      } else {
        setErro('Não foi possível carregar os registros de auditoria.')
      }
    } finally {
      setCarregando(false)
    }
  }

  return (
    <div className="container">
      <Breadcrumb pagina="Auditoria" />

      <div className="d-flex justify-content-between align-items-center mb-3">
        <div>
          <h4 className="mb-1">
            <i className="bi bi-clock-history me-2 text-primary"></i>
            Atividades dos usuários
          </h4>
          <p className="text-muted mb-0">Histórico de acessos e alterações realizadas no sistema.</p>
        </div>
        <button className="btn btn-outline-primary" onClick={() => carregar(pagina)} disabled={carregando}>
          <i className="bi bi-arrow-clockwise me-1"></i>Atualizar
        </button>
      </div>

      {carregando && (
        <div className="text-center py-5">
          <div className="spinner-border text-primary" role="status"></div>
          <p className="mt-2 text-muted">Carregando atividades...</p>
        </div>
      )}

      {erro && <div className="alert alert-danger">{erro}</div>}

      {!carregando && !erro && resultado.content.length === 0 && (
        <div className="text-center py-5 text-muted bg-white rounded">
          <i className="bi bi-journal-text display-1 text-primary"></i>
          <p className="mt-3 mb-1">Nenhuma atividade registrada.</p>
          <small>Os eventos aparecerão aqui após a ativação do banco de auditoria.</small>
        </div>
      )}

      {!carregando && !erro && resultado.content.length > 0 && (
        <>
          <div className="table-responsive">
            <table className="table table-hover align-middle bg-white">
              <thead className="table-dark">
                <tr>
                  <th>Data e hora</th>
                  <th>Usuário</th>
                  <th>Ação</th>
                  <th>Recurso</th>
                  <th>Resultado</th>
                  <th>IP</th>
                </tr>
              </thead>
              <tbody>
                {resultado.content.map((log) => (
                  <tr key={log.id}>
                    <td className="text-nowrap">{formatarData(log.dataHora)}</td>
                    <td>{log.usuarioEmail}</td>
                    <td><span className={`badge ${CLASSES_ACAO[log.acao] || 'bg-secondary'}`}>{ROTULOS_ACAO[log.acao] || log.acao}</span></td>
                    <td className="text-capitalize">{recursoDaRota(log.rota)}</td>
                    <td><span className={`badge ${log.statusHttp < 400 ? 'bg-success' : 'bg-danger'}`}>{log.statusHttp}</span></td>
                    <td className="text-muted small">{log.enderecoIp || '—'}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          <div className="d-flex justify-content-between align-items-center">
            <small className="text-muted">Total: {resultado.totalElements} atividade(s)</small>
            <div className="btn-group">
              <button className="btn btn-outline-secondary" disabled={pagina === 0} onClick={() => setPagina(pagina - 1)}>Anterior</button>
              <span className="btn btn-outline-secondary disabled">Página {pagina + 1} de {resultado.totalPages}</span>
              <button className="btn btn-outline-secondary" disabled={pagina + 1 >= resultado.totalPages} onClick={() => setPagina(pagina + 1)}>Próxima</button>
            </div>
          </div>
        </>
      )}
    </div>
  )
}

export default AuditoriaPage
