import { useCallback, useEffect, useState } from 'react'
import { baixarResumoCsv, buscarResumoGerencial } from '../services/relatoriosApi.js'

function formatarMoeda(valor) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(valor || 0)
}

function Indicador({ icone, titulo, valor, detalhe, cor = 'primary' }) {
  return (
    <div className="col-sm-6 col-xl-3">
      <div className="card border-0 shadow-sm h-100 relatorio-indicador">
        <div className="card-body">
          <div className={`relatorio-icone text-${cor}`}>
            <i className={`bi ${icone}`}></i>
          </div>
          <div className="relatorio-valor">{valor}</div>
          <div className="fw-semibold">{titulo}</div>
          {detalhe && <div className="small text-muted mt-1">{detalhe}</div>}
        </div>
      </div>
    </div>
  )
}

function RelatoriosPage() {
  const [resumo, setResumo] = useState(null)
  const [carregando, setCarregando] = useState(true)
  const [exportando, setExportando] = useState(false)
  const [erro, setErro] = useState('')

  const carregar = useCallback(async () => {
    setCarregando(true)
    setErro('')
    try {
      const resposta = await buscarResumoGerencial()
      setResumo(resposta.data)
    } catch (falha) {
      const status = falha.response?.status
      if (status === 401) {
        setErro('Sua autenticação expirou ou não foi aceita pelo serviço de relatórios.')
      } else if (status === 403) {
        setErro('Seu usuário não pode consultar relatórios desta imobiliária.')
      } else {
        setErro('O serviço Python de relatórios está indisponível. Confirme se ele está rodando na porta 8090.')
      }
    } finally {
      setCarregando(false)
    }
  }, [])

  useEffect(() => {
    carregar()
  }, [carregar])

  async function exportarCsv() {
    setExportando(true)
    setErro('')
    try {
      const resposta = await baixarResumoCsv()
      const url = URL.createObjectURL(resposta.data)
      const link = document.createElement('a')
      link.href = url
      link.download = `imobfiscal-relatorio-${resumo?.geradoEm || 'gerencial'}.csv`
      document.body.appendChild(link)
      link.click()
      link.remove()
      URL.revokeObjectURL(url)
    } catch {
      setErro('Não foi possível exportar o relatório. Tente novamente.')
    } finally {
      setExportando(false)
    }
  }

  return (
    <div className="container py-4">
      <div className="d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4">
        <div>
          <h4 className="mb-1">
            <i className="bi bi-bar-chart-line me-2 text-primary"></i>
            Relatórios gerenciais
          </h4>
          <small className="text-muted">
            Indicadores calculados pelo módulo Python com os dados da sua imobiliária
          </small>
        </div>
        <div className="d-flex gap-2">
          <button type="button" className="btn btn-outline-primary" onClick={carregar} disabled={carregando}>
            <i className="bi bi-arrow-clockwise me-1"></i>
            Atualizar
          </button>
          <button type="button" className="btn btn-primary" onClick={exportarCsv} disabled={!resumo || exportando}>
            <i className="bi bi-download me-1"></i>
            {exportando ? 'Exportando...' : 'Exportar CSV'}
          </button>
        </div>
      </div>

      {erro && (
        <div className="alert alert-warning d-flex align-items-start gap-2" role="alert">
          <i className="bi bi-exclamation-triangle-fill mt-1"></i>
          <div>
            <strong>Relatório não carregado.</strong>
            <div>{erro}</div>
          </div>
        </div>
      )}

      {carregando ? (
        <div className="text-center py-5">
          <div className="spinner-border text-primary" role="status"></div>
          <p className="mt-2 text-muted">Calculando indicadores...</p>
        </div>
      ) : resumo && (
        <>
          <div className="row g-3 mb-4">
            <Indicador
              icone="bi-buildings"
              titulo="Imóveis cadastrados"
              valor={resumo.imoveis.total}
              detalhe={`${resumo.imoveis.disponiveis} disponíveis`}
            />
            <Indicador
              icone="bi-house-check"
              titulo="Taxa de ocupação"
              valor={`${resumo.imoveis.taxaOcupacaoPercentual.toLocaleString('pt-BR')}%`}
              detalhe={`${resumo.imoveis.ocupados} imóveis ocupados`}
              cor="success"
            />
            <Indicador
              icone="bi-file-earmark-check"
              titulo="Contratos ativos"
              valor={resumo.contratos.ativos}
              detalhe={`${resumo.contratos.vencendoEm30Dias} vencendo em 30 dias`}
            />
            <Indicador
              icone="bi-cash-stack"
              titulo="Receita mensal prevista"
              valor={formatarMoeda(resumo.contratos.receitaMensalPrevista)}
              detalhe="Contratos com status ativo"
              cor="success"
            />
          </div>

          <div className="row g-3">
            <div className="col-lg-7">
              <div className="card border-0 shadow-sm h-100">
                <div className="card-body">
                  <h6 className="mb-4">Situação dos boletos</h6>
                  <div className="row g-3 text-center">
                    <div className="col-4">
                      <div className="fs-2 fw-bold text-success">{resumo.boletos.pagos}</div>
                      <div className="small text-muted">Pagos</div>
                    </div>
                    <div className="col-4 border-start border-end">
                      <div className="fs-2 fw-bold text-warning">{resumo.boletos.pendentes}</div>
                      <div className="small text-muted">Pendentes</div>
                    </div>
                    <div className="col-4">
                      <div className="fs-2 fw-bold text-danger">{resumo.boletos.vencidos}</div>
                      <div className="small text-muted">Vencidos</div>
                    </div>
                  </div>
                  <hr />
                  <div className="d-flex justify-content-between mb-2">
                    <span className="text-muted">Valor pendente</span>
                    <strong>{formatarMoeda(resumo.boletos.valorPendente)}</strong>
                  </div>
                  <div className="d-flex justify-content-between">
                    <span className="text-muted">Valor vencido</span>
                    <strong className="text-danger">{formatarMoeda(resumo.boletos.valorVencido)}</strong>
                  </div>
                </div>
              </div>
            </div>

            <div className="col-lg-5">
              <div className="card border-0 shadow-sm h-100">
                <div className="card-body">
                  <h6 className="mb-3">Resumo da carteira</h6>
                  <dl className="row mb-0 relatorio-lista">
                    <dt className="col-7">Locadores cadastrados</dt>
                    <dd className="col-5 text-end">{resumo.locadores.total}</dd>
                    <dt className="col-7">Total de contratos</dt>
                    <dd className="col-5 text-end">{resumo.contratos.total}</dd>
                    <dt className="col-7">Total de boletos</dt>
                    <dd className="col-5 text-end">{resumo.boletos.total}</dd>
                    <dt className="col-7">Data de referência</dt>
                    <dd className="col-5 text-end">{new Date(`${resumo.geradoEm}T12:00:00`).toLocaleDateString('pt-BR')}</dd>
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
    </div>
  )
}

export default RelatoriosPage

