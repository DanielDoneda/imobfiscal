import axios from 'axios'

const IMOBILIARIA_ID = '11111111-1111-1111-1111-111111111111'

function getImobiliariaId() {
  return localStorage.getItem('imobfiscal_imobiliaria_id') || IMOBILIARIA_ID
}

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || '/api',
})

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('imobfiscal_token')

  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }

  return config
})

export function login(email, senha) {
  return api.post('/auth/login', { email, senha })
}

export function cadastrar(nome, email, senha) {
  return api.post('/auth/cadastro', {
    nome,
    email,
    senha,
    imobiliariaId: IMOBILIARIA_ID,
  })
}

export function listarImoveis() {
  return api.get(`/imobiliarias/${getImobiliariaId()}/imoveis`)
}

export function buscarImovel(id) {
  return api.get(`/imobiliarias/${getImobiliariaId()}/imoveis/${id}`)
}

export function criarImovel(dados) {
  return api.post(`/imobiliarias/${getImobiliariaId()}/imoveis`, dados)
}

export function atualizarImovel(id, dados) {
  return api.put(`/imobiliarias/${getImobiliariaId()}/imoveis/${id}`, dados)
}

export function excluirImovel(id) {
  return api.delete(`/imobiliarias/${getImobiliariaId()}/imoveis/${id}`)
}

export const listarLocadores = () =>
  api.get(`/imobiliarias/${getImobiliariaId()}/locadores`)

export const buscarLocador = (id) =>
  api.get(`/imobiliarias/${getImobiliariaId()}/locadores/${id}`)

export const criarLocador = (dados) =>
  api.post(`/imobiliarias/${getImobiliariaId()}/locadores`, dados)

export const atualizarLocador = (id, dados) =>
  api.put(`/imobiliarias/${getImobiliariaId()}/locadores/${id}`, dados)

export const excluirLocador = (id) =>
  api.delete(`/imobiliarias/${getImobiliariaId()}/locadores/${id}`)

export const listarContratos = () =>
  api.get(`/imobiliarias/${getImobiliariaId()}/contratos`)

export const buscarContrato = (id) =>
  api.get(`/imobiliarias/${getImobiliariaId()}/contratos/${id}`)

export const criarContrato = (dados) =>
  api.post(`/imobiliarias/${getImobiliariaId()}/contratos`, dados)

export const atualizarStatusContrato = (id, status) =>
  api.patch(`/imobiliarias/${getImobiliariaId()}/contratos/${id}/status?status=${status}`)

export const calcularImposto = (dados) =>
  api.post('/motor-tributario/calcular', dados)

export const listarBoletos = () =>
  api.get(`/imobiliarias/${getImobiliariaId()}/boletos`)

export const gerarBoleto = (dados) =>
  api.post(`/imobiliarias/${getImobiliariaId()}/boletos/gerar`, dados)

export const buscarBoleto = (id) =>
  api.get(`/imobiliarias/${getImobiliariaId()}/boletos/${id}`)

export default api
