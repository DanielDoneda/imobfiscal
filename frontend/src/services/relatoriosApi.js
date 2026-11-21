import axios from 'axios'

const relatoriosApi = axios.create({
  baseURL: import.meta.env.VITE_REPORTS_API_URL
    || (import.meta.env.DEV
      ? 'http://localhost:8090'
      : 'https://imobfiscal-relatorios.onrender.com'),
  timeout: 15000,
})

relatoriosApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('imobfiscal_token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

function getImobiliariaId() {
  return localStorage.getItem('imobfiscal_imobiliaria_id')
}

export function buscarResumoGerencial() {
  return relatoriosApi.get(`/api/relatorios/${getImobiliariaId()}/resumo`)
}

export function baixarResumoCsv() {
  return relatoriosApi.get(`/api/relatorios/${getImobiliariaId()}/resumo.csv`, {
    responseType: 'blob',
  })
}

